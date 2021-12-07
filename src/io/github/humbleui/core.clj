(ns io.github.humbleui.core
  (:import
   [io.github.humbleui.jwm App]))

(defrecord Point [x y])

(defrecord Rect [left top width height])

(defrecord Size [width height])

(defn init []
  (App/init))

(defn start []
  (App/start))

(defn terminate []
  (App/start))

(defmacro doui-async [& forms]
  `(let [p# (promise)]
     (App/runOnUIThread #(deliver p# (try ~@forms (catch Throwable t# t#))))
     p#))

(defmacro doui [& forms]
  `(let [res# (deref (doui-async ~@forms))]
     (if (instance? Throwable res#)
       (throw res#)
       res#)))