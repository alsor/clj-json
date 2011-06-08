(ns clj-json.binary-test
  (:use clojure.test
        clj-json.binary))

(deftest test-binary
  (let [obj {"int" 3 "long" 52001110638799097 "bigint" 9223372036854775808
             "double" 1.23 "boolean" true "nil" nil "string" "string"
             "vec" [1 2 3] "map" {"a" "b"} "list" (list "a" "b")}]
    (is (= obj (parse-binary (generate-binary obj))))))

(deftest test-key-coercion
  (is (= {"foo" "bar" "1" "bat" "2" "bang" "3" "biz"}
         (parse-binary
           (generate-binary
             {:foo "bar" 1 "bat" (long 2) "bang" (bigint 3) "biz"})))))

(deftest test-keywords
  (is (= {:foo "bar" :bat 1}
         (parse-binary
           (generate-binary {:foo "bar" :bat 1})
           true))))
