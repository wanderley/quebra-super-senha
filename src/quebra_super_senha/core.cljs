(ns quebra-super-senha.core
    (:require
              [reagent.core :as reagent :refer [atom]]
              [reagent.dom :as rd]
              [quebra-super-senha.words :refer [WORDS]]))

(enable-console-print!)


;;; App State

(defonce initial-state
  {:words WORDS
   :letter-1-options "abcdefghijklmnopqrstuvwxyz"
   :letter-2-options "abcdefghijklmnopqrstuvwxyz"
   :letter-3-options "abcdefghijklmnopqrstuvwxyz"
   :letter-4-options "abcdefghijklmnopqrstuvwxyz"
   :letter-5-options "abcdefghijklmnopqrstuvwxyz"
   :excluded-letters ""
   :included-letters ""})

(defonce app-state (atom initial-state))

;; TODO Use webworkers to avoid blocking UI
(defn compute-new-words
  "Returns a new states with only valid words given letters' restrictions."
  [app-state]
  (assoc app-state
         :words
         (filterv
          (fn [p]
            (and
             (some (set (get p 0))
                   (set (:letter-1-options app-state)))
             (some (set (get p 1))
                   (set (:letter-2-options app-state)))
             (some (set (get p 2))
                   (set (:letter-3-options app-state)))
             (some (set (get p 3))
                   (set (:letter-4-options app-state)))
             (some (set (get p 4))
                   (set (:letter-5-options app-state)))
             (not (some (set p)
                        (set (:excluded-letters app-state))))
             (or
              (empty? (:included-letters app-state))
              (every? (set p)
                      (set (:included-letters app-state))))))
          WORDS)))


;;;; Actions

(defn update-letter-options!
  "Updates the `letter-key` with given `value`."
  [letter-key value]
  (reset! app-state
        (-> @app-state
            (assoc letter-key value)
            (compute-new-words))))

(defn reset-to-initial-state!
  "Restarts the program."
  []
  (reset! app-state initial-state))


;;; UI Components

(defn input-letter-options [label options on-change]
  [:div {:class "mb-3"}
   [:label {:for label
            :class "form-label"}
    label]
   [:input {:type "text"
            :class "form-control"
            :style {:letter-spacing "2px"
                    :font-family "monospace"}
            :id "label"
            :value options
            :spell-check "false"
            :on-change #(on-change (.. % -target -value))}]])

(defn quebra-super-senha []
  [:div {:class "container"
         :style {:margin-top "20px"}}
   [:h1 "Quebra Super Senha"]
   [:p
    "Este é um solucionador para o desafio "
    [:a {:href "https://vini.me/supersenha/"} "Super Senha"]
    " que funciona da seguinte forma:"]
   [:ul
    [:li
     "Entre um dos chutes possíveis no Super Senha"]
    [:li
     "Para cada letra verde, atualize seu respectivo chute. Por exemplo, se você
      acertou o chute na primeira posição (um C), então substitua o campo
      \"Chutes para letra 1\" pelo valor \"C\"."]
    [:li
     "Entre as letras em cinza no campo \"Letras que não estão na palavra\"."]
    [:li
     "Entre as letras em amarelo no campo \"Letras que estão em algum lugar da
      palavra\"."]
    [:li
     "Note que a lista de possíveis chutes é atualizada após cada alteração."]]
   [:div {:class "card-group"}
    [:div {:class "card"}
     [:div {:class "card-body text-success"}
      [:h5 {:class "card-title"}
       "Próximo chutes possíveis"]
      [:div {:class "card-text"}
       (into
        [:ul]
        (for [p (take 3 (:words @app-state))]
          [:li p]))]]]
    [:div {:class "card"}
     [:div {:class "card-body text-danger"}
      [:h5 {:class "card-title"}
       "Número de palavras restantes"]
      [:p {:class "card-text"}
       (count (:words @app-state))]
      [:input
       {:type "button"
        :value "Começar novamente"
        :on-click #(reset-to-initial-state!)}]]]]
   [:div {:class "row align-items-start"}
    [:div {:class "col"}
     (input-letter-options "Chutes para letra 1"
                           (:letter-1-options @app-state)
                           (partial update-letter-options! :letter-1-options))
     (input-letter-options "Chutes para letra 2"
                           (:letter-2-options @app-state)
                           (partial update-letter-options! :letter-2-options))
     (input-letter-options "Chutes para letra 3"
                           (:letter-3-options @app-state)
                           (partial update-letter-options! :letter-3-options))
     (input-letter-options "Chutes para letra 4"
                           (:letter-4-options @app-state)
                           (partial update-letter-options! :letter-4-options))
     (input-letter-options "Chutes para letra 5"
                           (:letter-5-options @app-state)
                           (partial update-letter-options! :letter-5-options))]
    [:div {:class "col"}
     (input-letter-options "Letras que não estão na palavra"
                           (:excluded-letters @app-state)
                           (partial update-letter-options! :excluded-letters))
     (input-letter-options "Letras que estão em algum lugar da palavra"
                           (:included-letters @app-state)
                           (partial update-letter-options! :included-letters))]]])

(rd/render [quebra-super-senha]
           (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
