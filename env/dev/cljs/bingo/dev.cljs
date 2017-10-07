(ns ^:figwheel-no-load bingo.dev
  (:require
    [bingo.core :as core]
    [devtools.core :as devtools]))

(devtools/install!)

(enable-console-print!)

(core/init!)
