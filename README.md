{: .circle-cont}
![build status](https://circleci.com/gh/wit-ai/picsou.png?circle-token=402928d80776c89e28c621d690201d1ff3b994e2)

# Introduction

Picsou is a Clojure library that parses text into structured data:

    “in two hours” => {:value "2014-06-09T13:24:06.634-07:00"
                       :grain :hour}

Picsou is shipped with modules that parse temporal expressions in English, Spanish, French and Chinese (experimental). It recognizes dates and times described in many ways:

- *today at 5pm*
- *2014-10-01*
- *the last Tuesday of October 2012*
- *twenty five minutes ago*
- *the day before labor day 2020*
- *June 10-11* (interval)

Picsou is:

- **Agnostic**: it makes no assumption on the kind of data you want to extract, or the language. You can train it with a combination of examples and rules for any task that takes a string as input and produces a map as output.
- **Probabilistic**: in the real world, a given input string may produce dozens of potential results. Picsou assigns a probability on each result. It decides which results are more probable by using the corpus of examples given in the configuration. Owing to that (and unlike, say, regular expressions or formal grammars), rules can afford to be extremely loose. It makes them much easier to write, and much more robust to user input in the wild.
- **Extensible**: we tried our best to make Picsou easy to extend. It leverages the power of Clojure's "code is data" philosophy.

If you know NLP, Picsou is “almost” a [Probabilistic Context Free Grammar](http://en.wikipedia.org/wiki/Stochastic_context-free_grammar). But not exactly! It tries to be more flexible and easier to configure than a formal PCFG. It also tries to learn better from examples.

These are good alternatives if you only have to deal with English, and your text input is somewhat less noisy:

- Stanford NLP's [SUTime](http://nlp.stanford.edu/software/sutime.shtml) (in Java), is a deterministric rule-based system that supports English. Written by Angel Chang.
- [Natty](http://natty.joestelmach.com/) (in Java) is based on ANTLR and supports English. It's written by Joel Stelmach.

**This is an alpha release. We have been using it internally in production at [Wit.ai](https://wit.ai) for more than a year, but the API and organizational structure are subject to change. Comments and suggestions are much appreciated.**

# Limitations

Known limitations of the temporal module include:

- Intervals (like *June 12-13*) are still experimental. Simple ones work well, but not the most sophisticated. We're working on it.
- Timezones are parsed, but they don't impact the time value (will be fixed shortly).
- The support for Chinese and Italian is not well tested yet.
- Only contiguous times are supported: periodic things like *every Tuesday* or *Monday and Tuesday from 6am to noon* are not supported yet (but we plan to support these, and it should be just a question of adding the rights rules)

# Getting started

Leiningen dependency (Clojars): `[wit/picsou "0.1.1"]`

To use Picsou in your project, you just need two functions: `load!` to load the default configuration, and `extract` to parse a string.

```clojure
(ns myproject.core
  (:require [picsou.core :as p]))

(p/load!) ;; Load default configuration

(p/extract "wake me up the last Monday of January 2015 at 6am"
           :en$core ;; core configuration for English ; see also :fr$core, :es$core, :cn$core
           [:time]) ;; We are interested in :time expressions only ; see also :duration, :temperature, etc.

;; => [{:label :time
        :start 15
        :end 49
        :value {:type "value", :value "2015-01-26T06:00:00.000-02:00", :grain :hour}
        :body "last Monday of January 2015 at 6am"}]
```

# Extending Picsou

You can add or modify the shipped modules to improve the parsing of date and times, but also create new modules that parse just any kind of data you want.

## Walkthrough

Launch a REPL from the project directory:

```bash
→ lein repl
nREPL server started on port 59436
REPL-y 0.1.9
Clojure 1.5.1
    Exit: Control+D or (exit) or (quit)
Commands: (user/help)
    Docs: (doc function-name-here)
          (find-doc "part-of-name-here")
  Source: (source function-name-here)
          (user/sourcery function-name-here)
 Javadoc: (javadoc java-object-or-class-here)
Examples from clojuredocs.org: [clojuredocs or cdoc]
          (user/clojuredocs name-here)
          (user/clojuredocs "ns-here" "name-here")
picsou.core=>
```

Load Picsou with the default configuration file:

```clojure
picsou.core=> (load!)
INFO: Loading module  :fr$core
INFO: Loading module  :en$core
INFO: Loading module  :es$core
nil
```

Run the corpus and check that all the tests pass:

```
picsou.core=> (run)
OK  "ahora"
OK  "ya"
OK  "ahorita"
OK  "hoy"
OK  "en este momento"
OK  "ayer"
OK  "anteayer"
OK  "antier"
OK  "mañana"
OK  "pasado mañana"
OK  "lunes"
OK  "lu"
OK  "lun."
OK  "este lunes"
OK  "martes"
[...]
282 examples, 0 failed.
Results :
:es$core: 269 examples, 0 failed.
:en$core: 264 examples, 0 failed.
:fr$core: 282 examples, 0 failed.
Global Failed count:  0
nil
```

See the detailed parsing of a given string like "in two hours":

```
picsou.core=> (play :en$core "in two hours")
------------  11 | time      | in/after <duration>       | P = -3.2070 |  + <integer> <unit-of-duration>
   ---        10 | distance  | number as distance        | P = -2.2455 | integer (0..19)
   ---         9 | temperature | number as temp            | P = -2.2501 | integer (0..19)
   ---------   8 | duration  | <integer> <unit-of-duration> | P = -2.7215 | integer (0..19) + hour (unit-of-duration)
   ---         7 | null      | number (as relative minutes) | P = -1.7314 | integer (0..19)
   ---         6 | time      | <integer> (latent time-of-day) | P = -1.3509 | integer (0..19)
   ---         5 | time      | month (numeric)           | P = -1.0430 | integer (0..19)
   ---         4 | time      | day of month (numeric)    | P = -1.5284 | integer (0..19)
       -----   3 | unit-of-duration | hour (unit-of-duration)   | P = 0.0000 |
       -----   2 | cycle     | hour (cycle)              | P = 0.0000 |
   ---         1 | number    | integer (0..19)           | P = -0.1957 |
12 tokens in stash
[...]
```


## Workflow

When we extend Picsou's coverage, we follow a typical TDD workflow:

1. Load Picsou
2. Add tests to the corpus
3. Run the corpus: the new tests don’t pass
4. Add or modify rules until the corpus tests pass

The following sections detail these steps.

## Loading

### Configuration file

The default configuration file `resources/default-config.clj` defines three modules (`fr$core`, `en$core` and `es$core`):

```clojure
{:fr$core {:corpus ["fr.time"
                    "fr.numbers"
                    "fr.temperature"
                    "fr.finance"
                    "en.communication"]
           :rules ["fr.time"
                   "fr.numbers"
                   "fr.cycles"
                   "fr.duration"
                   "fr.temperature"
                   "en.finance"
                   "en.communication"]}
 :en$core {:corpus ["en.time"
                    "en.numbers"
                    "en.temperature"
                    "en.finance"
                    "en.communication"]
           :rules ["en.time"
                   "en.numbers"
                   "en.cycles"
                   "en.duration"
                   "en.temperature"
                   "en.finance"
                   "en.communication"]}
 :es$core {:corpus ["es.time"
                    "es.numbers"
                    "es.temperature"
                    "es.finance"
                    "en.communication"]
           :rules ["es.time"
                   "es.numbers"
                   "es.cycles"
                   "es.duration"
                   "es.temperature"
                   "en.finance"
                   "en.communication"]}}
```

### Modules

Each module has a name (`en$core`), with which it is referred to when you want to use it at runtime, or reload it.

Each module refers to a set of corpus files and rules files (more on this in the following sections).

Each module is run by Picsou in a separate “sandbox”, so for example, rules in module A cannot expect to match
tokens created by rules in module B.
There’s typically one module per language, but nothing prevents you to use several modules for a given language,
as long as these modules don't need to interact with each other.

### Loading modules

To load Picsou with all the modules defined in the default configuration file `resources/default-config.clj`:

```
picsou.core=> (load!)
INFO: Loading module  :fr$core
INFO: Loading module  :en$core
INFO: Loading module  :es$core
nil
```

Alternatively, to load Picsou without using a configuration file, you can define modules directly in the `load!` function arguments:

```clojure
(load! {:en$location {:corpus ["en.location"] :rules ["en.location"]}})
INFO: Loading module  :en$location
```

## Corpus

Corpus files are located in `resources/corpus`. You can either edit existing files or create new files.
If you create new files, don’t forget to load them by referencing them in your configuration file or `load!`
command (see above). **Once you’ve modified corpus files, you must reload to take the changes into account.**

Here is an example corpus file with two test groups:

```clojure
(
  {} ; Context map

  "0"
  "naught"
  "zero"
  (number 0)

  "1"
  "one"
  (number 1)
)
```

Each test group is described by one or more strings and a function.
To run the group Picsou will take each string one by one, analyze it, a call the function on the output.
The test passes if the function returns true (or a truthy value).

For instance, to test that “0”, “naught” and "zero" will all produce the output `{:dim :number :value 0}`, we can use:

```clojure
“0”
“naught”
“zero”
(fn [token context] (and (= :number (:dim token)) (= 0 (:value token))))
```

For now, the context is just used for date and times, in order to solve relative dates like “tomorrow”.
You can provide a context map at the beginning of your corpus file, and this map will be provided to the test function.
In most cases, you shouldn’t need to use context.

In practice, we use helpers to generate easy to read test functions.
In the previous example, we use a helper `number` defined in `src/picsou/corpus.clj`:

```clojure
(defn number
  "check if the token is a number equal to value.
  If value is integer, it also checks :integer true"
  [value]
  (fn [token _] (and
                  (= :number (:dim token))
                  (or (not (integer? value)) (:integer token))
                  (= (:val token) value))))
```

So that the test becomes just `(number 0)`, which is easy to read and reusable.

Picsou will frequently generate several possible results for a given input.
In this case, each result is tested by the test function.
If the function returns true for at least one result, then the test passes.

Once you’ve added your tests, reload your module (see above) and run the corpus:

```
picsou.core=> (run :en$core)
OK  "now"
OK  "right now"
OK  "just now"
[...]
OK  "0"
OK  "naught"
OK  "nought"
OK  "zero"
FAIL"boule a zero" none of the 0 winners did pass the test
OK  "1"
OK  "one"
[...]
265 examples, 1 failed.
[:en$core 265 1]
```

Make sure the tests don’t pass anymore (if they do, either you’re very lucky and the existing rules actually
cover your new tests, or you did not reload the corpus -- usually it’s the latter!). Now you’re ready to write rules.

## Rules

Rules files are located in `resources/rules`. You can either edit existing files or create new files.
If you create new files, don’t forget to load them by referencing them in the configuration file or `load!` command.
**Once you’ve modified rules files, you must reload to take the changes into account.**

Here is an example file with just one rule:

```clojure
("zero"                                ; _label_ of the rule, useful for debugging
 #”0|zero|naught”                      ; _pattern_, here it’s a simple regex
 {:dim number :integer true :val 0})   ; _production_ token, it can be any map
```

When the pattern is matched, the production token is produced. Picsou adds this new token to its collection of tokens,
which is called the “stash”. Then other rules can try to match this token and produce other tokens that are added
to the stash, and so on. All rules are tried again and again until no more token is produced.

Here is an illustration of this process, with a stash containing 11 tokens:

```
picsou.core=> (play :en$core "in two hours")
------------  11 | time      | in/after <duration>       | P = -3.2070 |  + <integer> <unit-of-duration>
   ---        10 | distance  | number as distance        | P = -2.2455 | integer (0..19)
   ---         9 | temperature | number as temp            | P = -2.2501 | integer (0..19)
   ---------   8 | duration  | <integer> <unit-of-duration> | P = -2.7215 | integer (0..19) + hour (unit-of-duration)
   ---         7 | null      | number (as relative minutes) | P = -1.7314 | integer (0..19)
   ---         6 | time      | <integer> (latent time-of-day) | P = -1.3509 | integer (0..19)
   ---         5 | time      | month (numeric)           | P = -1.0430 | integer (0..19)
   ---         4 | time      | day of month (numeric)    | P = -1.5284 | integer (0..19)
       -----   3 | unit-of-duration | hour (unit-of-duration)   | P = 0.0000 |
       -----   2 | cycle     | hour (cycle)              | P = 0.0000 |
   ---         1 | number    | integer (0..19)           | P = -0.1957 |
in two hours
[...]
```

### Patterns

#### Base patterns

There are two types of base patterns:
- regular expressions that try to match the input text
- functions that try to match tokens in the stash

Any function accepting one token as argument (a Clojure map) can work as a pattern.
It must return true when the token matches. For example:

```clojure
; this pattern will match a token with :dim :number whose :val is 0
(fn [token] (and (= :number (:dim token)) (= 0 (:val token))))
```

**Protip:** These patterns are very close, but should not be confused with Corpus test patterns.
We might merge them later.

#### Helpers

Like for corpus test functions, you’ll find yourself using the same patterns again and again.
 We use helpers that produce pattern functions. For instance

```clojure
(number 3) ; => (fn [token] (and (= :number (:dim token)) (= 3 (:val token))))

(dim :number) ; => (fn [token] (= :number (:dim token)))
```

You should reuse existing helpers or define your own as much as possible, as it makes the rules much easier to read.

**Protip:** Using (dim :number) is better than a regex like #”\d+”,
because if will match any number even “twenty”, “minus six”, “2M”, etc.
You actually leverage other Picsou rules that are just responsible to recognize numbers.

#### Slots

Let’s say you want to parse something like “10 degrees”, “twenty degrees”, and “30°”.
The right approach is to look for a token of `:dim :number`, immediately followed by a word like “degrees” or “°”.
In this case, we say the pattern has two *slots*. It is written like this:

```clojure
[(dim :number)   ; first slot is a token with :dim :number
 #”degrees?|°”]  ; second slot is the string "degree", "degrees" or "°"" in the input string
```

### Production

Once a rule’s pattern matches, Picsou creates a token and adds it to the stash.

In its simplest form, the production is just the token to produce:

```clojure
{:dim :number
 :integer true
 :val 0}
```

But what if the product token is a function of a token matched by the pattern?
You can use %1, %2, ... %S to represent the tokens matched in the S slots:

```clojure
“<n> degrees"                ; label
[(dim :number) #”degrees?”]  ; pattern (2 slots)
{:dim :temperature           ; production
 :degrees (:val %1)}
```

**Protip:** Internally, the production form is expanded with `#(...)`.
It becomes a function, which is called with the matching tokens as arguments.

**Warning:** If the pattern has S slots, you MUST use `%S` (even if you don't need it) if you need any %i.
That will set the right arity to the production function.

#### Special case of regex patterns

If the base pattern is a regex and you need to use the groups matched by the regex in the production, you use the `:groups` key:

```clojure
“international phone number”
#”\+(\d+) (\d+)” ; regex capturing two groups
{:dim :phone-number
 :country-code (-> %1 :groups first)
 :number (-> %1 :groups second)}
```

# Debugging

When a corpus test doesn’t pass and you don’t understand why, you can have a closer look at what happens with `play`:

```
picsou.core=> (play :en$core "45 degrees")
----------   5 | temperature | <latent temp> degrees     | P = -1.9167 | number as temp +
--           4 | distance  | number as distance        | P = -1.5638 | integer (numeric)
--           3 | temperature | number as temp            | P = -1.9167 | integer (numeric)
--           2 | null      | number (as relative minutes) | P = -1.1749 | integer (numeric)
--           1 | number    | integer (numeric)         | P = -0.1501 |
45 degrees
6 tokens in stash
```

Each line represents a token in the stash. The input string is at the bottom.

Columns:

1. The `--` represent the span in the text input
2. Token index (starting at 1, since the input string itself is token 0)
3. :dim
4. Label of the rule that produced the token (that’s why labeling your rules clearly is important)
5. Probability (the higher the most probable -- and it's actually the log of the probabily, hence the negative value)
6. Labels of the rules that produced the tokens in the slots below

If you need more information about a specific token, call the `details` function with the token index:

```
picsou.core=> (details 5)
<latent temp> degrees (-1.916684190532246)
|-- number as temp (-1.916684190532246)
|   `-- integer (numeric) (-0.15006069457573323)
|       `-- text: 45 (0)
`-- text: degrees (0)
```

If you really need to examine token 5 in depth, you can get the full map with `(token 5)`.

# Contributing

There are many ways you can help the project:

- Use the widget in this page, and leaving some feedback when the result is not good.
- One step further: If you notice something that does not work, you can add it in the corpus and submit a pull request.
- One step further (we'll send you a T-Shirt): Add it in the corpus AND add/modify the rules to make it pass.
- One step further (we'll invite you for a beer, at least!): Extend Picsou to new domains and/or new languages.

# FAQ

### How to add a new language?

To add support for a new language, follow these steps:

1. Create at least one corpus file in `resources/corpus` (you might duplicate an existing file to get started).
2. Create at least one rules file in `resources/rules`.
3. Edit the configuration file `resources/default-config.clj` and add your module. Have your module refer to your corpus and rules files.
4. Reload (see above)
5. Run your module corpus, add tests, see them fail, add rules until everything passes.
