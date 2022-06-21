package com.example.myapplication.holder

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvStartTime: TextView = itemView.findViewById(R.id.tv_startTime)
    val tvEndTime: TextView = itemView.findViewById(R.id.tv_endTime)
    val tvTemp: TextView = itemView.findViewById(R.id.tv_temp)
    val layCon: ConstraintLayout = itemView.findViewById(R.id.lay_con)


}