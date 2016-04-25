(ns duckling.resource
  (:require [clojure.java.io :as io])
  (:import [java.io File]
           [java.net URL]
           [java.util.jar JarEntry JarFile]))

(defn dir?
  "Whether filename is a directory."
  [^String filename]
  (.endsWith filename "/"))

(defn direct-child?
  "Whether filename is a direct child of prefix."
  [^String prefix ^String filename]
  (let [slashes (->> [filename prefix]
                     (map #(->> % (filter (partial = \/)) count))
                     (apply -))
        is-dir? (dir? filename)]
    (or (and (not is-dir?) (= 1 slashes))
        (and is-dir? (= 2 slashes)))))

(defn direct-child-name
  "Returns the name of entry if direct child of prefix."
  [^String prefix ^JarEntry entry]
  (let [name (.getName entry)]
    (when (and (.startsWith name prefix) (direct-child? prefix name))
      (subs name (inc (count prefix))))))

(defn jar-url->entries
  "Returns entries from jar-url."
  [^URL jar-url]
  (-> jar-url io/file (JarFile.) (.entries) enumeration-seq))

(defn file-url->child-names
  "Lists the files under file url.
   Appends a slash to directories."
  [^URL file-url]
  (->> file-url
       io/file
       (.listFiles)
       (map (fn [^File file]
              (cond-> (.getName file)
                (.isDirectory file) (str "/"))))))

(defn resource->ls
  "Lists files for url (locally or in jar)"
  [^URL url]
  (let [^String path (.getPath url)
        idx (.lastIndexOf path "!")
        prefix (subs path (+ 2 idx))]
    (if (< -1 idx)
      (let [jar-url (URL. (subs path 0 idx))]
        (->> (jar-url->entries jar-url)
             (keep (partial direct-child-name prefix))))
      (file-url->child-names url))))

(defn ^URL get-resource
  "Finds first available resource with path."
  [^String path]
  (-> (Thread/currentThread)
      (.getContextClassLoader)
      (.getResources path)
      enumeration-seq
      first))

(defn rm-trailing-slash
  "Removes trailing slash, if present."
  [^String path]
  (if (.endsWith path "/")
    (subs path 0 (dec (count path)))
    path))

(defn ls
  "Lists files and subdirectories of resource path."
  [^String path]
  (->> path rm-trailing-slash get-resource resource->ls))

(defn get-subdirs
  "Lists subdirectories of resource path."
  [^String path]
  (->> (ls path) (filter dir?) (map rm-trailing-slash)))

(defn get-files
  "Lists files of resource path."
  [^String path]
  (->> (ls path) (filter (comp not dir?))))
