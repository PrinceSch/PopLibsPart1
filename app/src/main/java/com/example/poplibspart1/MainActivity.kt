package com.example.poplibspart1

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.poplibspart1.databinding.ActivityMainBinding

var BUTTON_ONE_ID: Int? = 0
var BUTTON_TWO_ID: Int? = 0
var BUTTON_THREE_ID: Int? = 0

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var vb: ActivityMainBinding
    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)

        val listener = View.OnClickListener {
            presenter.counterClick(it as Button)
        }

        BUTTON_ONE_ID = vb.btnCounter1.id
        BUTTON_TWO_ID = vb.btnCounter2.id
        BUTTON_THREE_ID = vb.btnCounter3.id

        vb.btnCounter1.setOnClickListener(listener)
        vb.btnCounter2.setOnClickListener(listener)
        vb.btnCounter3.setOnClickListener(listener)
    }

    override fun setButtonOneText(text: String) {
        vb.btnCounter1.text = text
    }

    override fun setButtonTwoText(text: String) {
        vb.btnCounter2.text = text
    }

    override fun setButtonThreeText(text: String) {
        vb.btnCounter3.text = text
    }

}