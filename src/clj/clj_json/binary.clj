(ns clj-json.binary
  (:import (clj_json JsonExt) 
           (org.codehaus.jackson.smile SmileFactory SmileGenerator$Feature)
           (java.io ByteArrayOutputStream))
  (:use (clojure.contrib [def :only (defvar-)])))

(defvar- ^SmileFactory factory
  (doto (SmileFactory.)
    (.configure SmileGenerator$Feature/ENCODE_BINARY_AS_7BIT false)))

(defn generate-binary
  {:tag (Class/forName "[B") ;; I don't know why, but using either "bytes" or "Byte/TYPE" here causing problems
                             ;; in usages like (alength (generate-binary {:a "abc"}))
   :doc "Returns a byte[] with SMILE-encoded JSON for the given Clojure object"}
  [obj]
  (let [os (ByteArrayOutputStream.)
        generator (.createJsonGenerator factory os)]
    (JsonExt/generate generator obj)
    (.flush generator)
    (.toByteArray os)))

(defn parse-binary
  "Returns the Clojure object corresponding to the given SMILE-encoded JSON in byte[]"
  [^bytes bin & [keywords]]
  (JsonExt/parse 
    (.createJsonParser factory bin)
    true (or keywords false) nil))
