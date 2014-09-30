{: .circle-cont}
![build status](https://circleci.com/gh/wit-ai/picsou.png?circle-token=402928d80776c89e28c621d690201d1ff3b994e2)

# Introduction

Picsou is a Clojure library that parses text into structured data:

    “in two hours” => {:value "2014-06-09T13:24:06.634-07:00"
                       :grain :minute}

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

The core module (ie :en$core, :fr$core, :es$core, :cn$core...) will allow you to capture the following examples.

| time | "today"<br>"Monday, Feb 18"<br>"the 1st of march"<br>"last week"<br>"a quarter to noon"<br>"11:45am"<br>"three months ago"<br>"next 3 weeks"<br>"thanksgiving"<br>"Mother's Day"<br>"from 9:30 - 11:00 on Thursday<br>"the day before labor day 2015" |
| temperature | "70°F"<br>"72° Fahrenheit"<br>"thirty two celsius"<br>"65 degrees" |
| number | "eighteen"<br>"0.77"<br>"100K"<br>"33" |
| ordinal | "4th"<br>"first"<br>"seventh" |
| distance | "8miles"<br>"3 feet"<br>"2 inches"<br>"3''"<br>"4km"<br>"12cm" |
| volume | "250ml"<br>"2liters"<br>"1 gallon" |
| amount-of-money | "ten dollars"<br>"4 bucks"<br>"$20" |
| duration | "2 hours"<br>"4 days"<>"3 minutes" |
| email | "help@wit.ai" |
| url | "http://wit.ai"<br>"www.foo.com:8080/path"<br>"https://myserver?foo=bar"<br>"cnn.com/info"<br>"foo.com/path/path?ext=%23&foo=bla"<br>"localhost" |
| phone-number | "415-123-3444"<br>"+33 4 76095663"<br>"(650)-283-4757 ext 897" |


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
Loading module :fr$core
Loading module :en$core
Loading module :es$core
nil
```

Run the corpus and check that all the tests pass:

```
picsou.core=> (run)
:es$core: 294 examples, 0 failed.
:en$core: 355 examples, 0 failed.
:fr$core: 326 examples, 0 failed.
#'picsou.core/c
```

See the detailed parsing of a given string like "in two hours":

```
picsou.core=> (play :en$core "in two hours")
W ------------  11 | time      | in/after <duration>       | P = -3.4187 |  + <integer> <unit-o
W    ---        10 | volume    | number as volume          | P = -2.1172 | integer (0..19)
W    ---         9 | distance  | number as distance        | P = -2.2680 | integer (0..19)
W    ---         8 | temperature | number as temp            | P = -2.2409 | integer (0..19)
W    ---------   7 | duration  | <integer> <unit-of-duration> | P = -2.9592 | integer (0..19) + ho
     ---         6 | null      | number (as relative minutes) | P = -1.6507 | integer (0..19)
     ---         5 | time      | time-of-day (latent)      | P = -1.6351 | integer (0..19)
     ---         4 | time      | year (latent)             | P = -1.0804 | integer (0..19)
         -----   3 | unit-of-duration | hour (unit-of-duration)   | P = 0.0000 |
         -----   2 | cycle     | hour (cycle)              | P = 0.0000 |
W    ---         1 | number    | integer (0..19)           | P = -0.1866 |
  in two hours

6 winners:
number                    {:type "value", :value 2} {:integer true}
duration                  {:normalized {:value 7200, :unit "second"}, :unit :hour, :value 2, :hour 2} {}
temperature (latent)      {:type "value", :value 2} {}
distance (latent)         {:type "value", :value 2} {}
volume (latent)           {:type "value", :value 2} {}
time                      {:type "value", :value "2013-02-12T06:30:00.000-02:00", :grain :minute} {}
For further info: (details idx) where 1 <= idx <= 11
#'picsou.core/token
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
{
  :fr$core {:corpus ["fr.time"
                    "fr.numbers"
                    "fr.temperature"
                    "fr.measure"
                    "fr.finance"
                    "en.communication"
                    ]
            :rules ["fr.time"
                    "fr.numbers"
                    "fr.cycles"
                    "fr.duration"
                    "fr.temperature"
                    "fr.measure"
                    "en.finance"                             
                    "en.communication"
                    ]}                     
 :en$core {:corpus ["en.time"
                    "en.numbers"
                    "en.temperature"
                    "en.measure"
                    "en.finance"
                    "en.communication"]
           :rules  ["en.time"
                    "en.numbers"
                    "en.cycles"
                    "en.duration"
                    "en.temperature"
                    "en.measure"
                    "en.finance"
                    "en.communication"]}
:es$core {:corpus ["es.time"
                   "es.numbers"
                   "es.temperature"
                   "es.measure"
                   "es.finance"
                   "en.communication"
                  ]
            :rules ["es.time"
                    "es.numbers"
                    "es.cycles"
                    "es.duration"
                    "es.temperature"
                    "es.measure"
                    "en.finance"
                    "en.communication"
                    ]}
}
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
Loading module :fr$core
Loading module :en$core
Loading module :es$core
nil
```

Alternatively, to load Picsou without using a configuration file, you can define modules directly in the `load!` function arguments:

```clojure
(load! {:en$numbers {:corpus ["en.numbers"] :rules ["en.numbers"]}})
Loading module  :en$numbers
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
  (fn [_ token] (when-not 
                  (and
                    (= :number (:dim token))
                    (or (not (integer? value)) (:integer token))
                    (= (:value token) value))
                  [value (:value token)])))
```

So that the test becomes just `(number 0)`, which is easy to read and reusable.

Picsou will frequently generate several possible results for a given input.
In this case, each result is tested by the test function.
If the function returns true for at least one result, then the test passes.

Once you’ve added your tests, reload your module (see above) and run the corpus:

```
picsou.core=> (run :en$core)
O0 FAIL "nil"
    Expected null
:en$core: 356 examples, 1 failed.
#'picsou.core/c
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
 {:dim number :integer true :value 0})   ; _production_ token, it can be any map
```

When the pattern is matched, the production token is produced. Picsou adds this new token to its collection of tokens,
which is called the “stash”. Then other rules can try to match this token and produce other tokens that are added
to the stash, and so on. All rules are tried again and again until no more token is produced.

Here is an illustration of this process, with a stash containing 11 tokens:

```
picsou.core=> (play :en$core "in two hours")
W ------------  11 | time      | in/after <duration>       | P = -3.4187 |  + <integer> <unit-o
W    ---        10 | volume    | number as volume          | P = -2.1172 | integer (0..19)
W    ---         9 | distance  | number as distance        | P = -2.2680 | integer (0..19)
W    ---         8 | temperature | number as temp            | P = -2.2409 | integer (0..19)
W    ---------   7 | duration  | <integer> <unit-of-duration> | P = -2.9592 | integer (0..19) + ho
     ---         6 | null      | number (as relative minutes) | P = -1.6507 | integer (0..19)
     ---         5 | time      | time-of-day (latent)      | P = -1.6351 | integer (0..19)
     ---         4 | time      | year (latent)             | P = -1.0804 | integer (0..19)
         -----   3 | unit-of-duration | hour (unit-of-duration)   | P = 0.0000 |
         -----   2 | cycle     | hour (cycle)              | P = 0.0000 |
W    ---         1 | number    | integer (0..19)           | P = -0.1866 |
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
(fn [token] (and (= :number (:dim token)) (= 0 (:value token))))
```

**Protip:** These patterns are very close, but should not be confused with Corpus test patterns.
We might merge them later.

#### Helpers

Like for corpus test functions, you’ll find yourself using the same patterns again and again.
 We use helpers that produce pattern functions. For instance

```clojure
(number 3) ; => (fn [token] (and (= :number (:dim token)) (= 3 (:value token))))

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
 #"degrees?|°"]  ; second slot is the string "degree", "degrees" or "°"" in the input string
```

### Production

Once a rule’s pattern matches, Picsou creates a token and adds it to the stash.

In its simplest form, the production is just the token to produce:

```clojure
{:dim :number
 :integer true
 :value 0}
```

But what if the product token is a function of a token matched by the pattern?
You can use %1, %2, ... %S to represent the tokens matched in the S slots:

```clojure
“<n> degrees"                ; label
[(dim :number) #”degrees?”]  ; pattern (2 slots)
{:dim :temperature           ; production
 :degrees (:value %1)}
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
W ----------   7 | temperature | <latent temp> degrees     | P = -1.9331 | number as temp +
W --           6 | volume    | number as volume          | P = -1.8094 | integer (numeric)
W --           5 | distance  | number as distance        | P = -1.6120 | integer (numeric)
  --           4 | temperature | number as temp            | P = -1.9331 | integer (numeric)
  --           3 | null      | number (as relative minutes) | P = -0.9374 | integer (numeric)
W --           2 | time      | year (latent)             | P = -1.0603 | integer (numeric)
W --           1 | number    | integer (numeric)         | P = -0.1665 |
  45 degrees

5 winners:
[...]
```

Each line represents a token in the stash. The input string is at the bottom.

Columns:

1. `W` indicates a winner token
2. The `--` represent the span in the text input
3. Token index (starting at 1, since the input string itself is token 0)
4. :dim
5. Label of the rule that produced the token (that’s why labeling your rules clearly is important)
6. Probability (the higher the most probable -- and it's actually the log of the probabily, hence the negative value)
7. Labels of the rules that produced the tokens in the slots below

If you need more information about a specific token, call the `details` function with the token index:

```
picsou.core=> (details 7)
<latent temp> degrees (-1.9331200116060705)
|-- number as temp (-1.9331200116060705)
|   `-- integer (numeric) (-0.16649651564955764)
|       `-- text: 45 (0)
`-- text: degrees (0)
nil
```

If you really need to examine token 7 in depth, you can get the full map with `(token 7)`.

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
