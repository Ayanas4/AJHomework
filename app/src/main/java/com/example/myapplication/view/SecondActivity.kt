package com.example.myapplication.view

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.model.time
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_second.*
import java.util.*


class SecondActivity : AppCompatActivity() {

    var position = 0
    var data = ArrayList<time>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        init()
    }


    fun init() {
        try {
            position = intent.getIntExtra("position", 0)
            data = intent.getSerializableExtra("data") as ArrayList<time>

            tv_startTime.text = data[position].startTime.replace("-", "/")
            tv_endTime.text = data[position].endTime.replace("-", "/")
            tv_temp.text = data[position].parameter.parameterName + "C"
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}