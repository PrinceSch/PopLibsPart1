package com.example.poplibspart1

import android.widget.Button

class MainPresenter(private val view: MainView) {
    private val model = CountersModel()

    fun counterClick(button: Button) {
        when (button.id) {
            BUTTON_ONE_ID -> {
                view.setButtonOneText(model.next(0).toString())
            }
            BUTTON_TWO_ID -> {
                view.setButtonTwoText(model.next(1).toString())
            }
            BUTTON_THREE_ID -> {
                view.setButtonThreeText(model.next(2).toString())
            }
        }
    }
}