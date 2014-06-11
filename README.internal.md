#Deployment on our private s3 repository

export you AWS/IAM key_id && access key:

```
LEIN_PASSPHRASE=RNGJuL********
LEIN_USERNAME=AKIA****
```
Deploy using lein from your picsou folder.

```bash
lein deploy private
```

# Usage

Load nlp.picsou.core

	(use 'picsou.core :reload-all)

It loads the rules into `the-rules` and the corpus into `the-corpus`.

Run the corpus with `(show-corpus)`

    (show-corpus)
    OK   "aujourd'hui"
    OK   "ce jour"
    OK   "dans la journée"
    OK   "hier"
    OK   "avant-hier"
    OK   "demain"
    OK   "après-demain"
    OK   "lundi"
    OK   "lun."
    OK   "ce lundi"
    OK   "mardi"
    OK   "mercredi"
    OK   "ce week-end"
    OK   "dimanche dernier"
    OK   "dimanche la semaine dernière"
    OK   "dimanche de la semaine derniere"
    OK   "dimanche la semaine passée"
    OK   "mardi dernier"
    OK   "le 1er mars"
    OK   "premier mars"
    OK   "le premier mars 2013"
    OK   "le 1 mars"
    OK   "le 2 mars"
    OK   "2 mars"
    OK   "le 3 mars"
    OK   "3 mars"
    OK   "le 5 avril"
    OK   "5 avril"
    OK   "le 3 mars 2015"
    OK   "3 mars 2015"
    FAIL "3/3/2015" the winner did not pass the test
    FAIL "2015-3-3" the winner did not pass the test
    FAIL "2015-03-03" the winner did not pass the test
    OK   "le 15 février"
    OK   "15 février"
    OK   "le 15"
    OK   "15/02/2013"

Analyze a single sentence and get a detail view of the "stash" with `(play "mercredi en 15")`. When found, the global and final datetime interval is printed at the end.

    (play "4 mars")
    ------   9 | time      | two time tokens in a row  | P = -24.1059 | month (numeric) + janvier
    -----    8 | time      | two time tokens in a row  | P = -28.5359 | month (numeric) + mardi
    ------   7 | time      | two time tokens in a row  | P = -23.6054 | day in month (numeric) + janvier
    -----    6 | time      | two time tokens in a row  | P = -28.0354 | day in month (numeric) + mardi
    -        5 | time      | month (numeric)           | P = -10.8148 | number (numeric)
    -        4 | time      | day in month (numeric)    | P = -11.1027 | number (numeric)
      ----   3 | time      | janvier                   | P = -0.8430 |
      ---    2 | time      | mardi                     | P = -2.3026 |
    -        1 | integer   | number (numeric)          | P = -4.3567 |
    4 mars

    Winners: Time [#<DateTime 2013-03-04T00:00:00.000Z> #<DateTime 2013-03-05T00:00:00.000Z>]

    For further info: (details idx) where 0 < idx < 9

# Current limitations and discussions

## Limitations

**Forward vs. Backward** Currently the date lookup can be either backward or forward (non deterministic). Must implement something in the context to determine if we are looking backward or forward, and more generally the target range.

**"ce matin" we want 8am, but "3h ce matin" we want 3am.** So what range should "matin" have ? The general solution is to be able to customize resolution: "matin" should be a wide range, resolving to 8am (or whatever the wit instance wants). Right now, "3h ce matin" won't work.

**Split entitties** "Ce matin réveille moi à 8h"

## Discussions

**Information consumption** "3/3/2013" has a tendency to be analyzed as march/march/2013 (if we don't set a dd/mm/yyyy rule). To humans, it seems obvious that the same information (the month) will not be given twice. How could we have the system leverage this ?

**Semantically invalid tokens** "3/4" could be analyzed as day-in-month:3 + day-in-month:4, which obviously produces an empty interval at resolution. We should eliminate these token if we realize they parasite winners.

**"2014" and "'14"...** Cannot have in one rule both a regex and a Clojure condition func. So we have to have one regex rule, and then one func rule. But we lose some format information, for instance leading 0s in the case of integers. Maybe we need a special pattern regex+func. Add the func in the regex's meta ?

# Probabilistic parsing

1. Learn

    a. Build dataset

    The goal of this phase is to build a proper Machine Learning dataset from a corpus of sentences with expected result (interval of time). There is one separate ML model per rule: each rule is seen as a boolean classifier who has to decide, given a 'route' of tokens filling its slots, the probability that it's a good idea for the rule to fire.

    The global dataset is a map `'rule name' => dataset` for the rule

    The local dataset for a rule is a vector of samples. A general sample is just a vector `[features output]`

        * `features` could be anything "seen" by the rule (more on that later). For the moment, it's just the text filling the slots of the rule.

        * `output` is a boolean, `true` if the rule has matched "successfully" (ie, leading to the right result at the top of the parse tree) for this sample).

    b. Train model

    Once the dataset has been built, different ML techniques can try to build the best ML model for it. It should be clearly separated.

2. Run

	At runtime, the model predicts the local probability of each token by running the model of the rule who built the token on its 'route'. The global probability of the token is the product of its local prob by the local probs of the tokens of its route.

3. Features

   Feature extraction is used both during learning and runtime. It must be done by a separate function. The same dataset building strategy can be tried with different features approaches.

