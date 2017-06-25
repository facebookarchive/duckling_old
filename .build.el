;; project settings
(setq ent-project-home (file-name-directory (if load-file-name load-file-name buffer-file-name)))
(setq ent-project-name "clj-duckling")
(setq ent-clean-regexp "~$\\|\\.tex$")
(setq ent-project-config-filename "CljDuckling.org")

;; local functions

(defvar project-version)

(setq project-version (ent-get-version))


;; tasks

(load ent-init-file)

(task 'doc '() "build the project documentation" '(lambda (&optional x) "lein codox"))

(task 'format '() "format the project" '(lambda (&optional x) "lein cljfmt fix"))

(task 'check '() "check the project" '(lambda (&optional x) "lein with-profile +check checkall"))

(task 'tree '() "tree dependencies" '(lambda (&optional x) "lein do clean, deps :tree"))

(task 'tests '() "run tests" '(lambda (&optional x) "lein do clean, test"))

(task 'libupdate () "update project libraries" '(lambda (&optional x) "lein ancient :no-colors"))

(task 'package '() "package the library" '(lambda (&optional x) "lein do clean, uberjar"))


;; Local Variables:
;; no-byte-compile: t
;; no-update-autoloads: t
;; End:
