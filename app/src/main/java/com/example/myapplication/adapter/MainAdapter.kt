package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.interfaces.ItemElementClickListener
import com.example.myapplication.R
import com.example.myapplication.holder.MainHolder
import com.example.myapplication.holder.SecondHolder
import com.example.myapplication.model.time
import com.example.myapplication.http.runOnUI
import org.chromium.base.Log

const val VIEW_TYPE_SECTION = 1
const val VIEW_TYPE_ITEM = 2

class MainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var data = arrayListOf<time>()
    var newpostion = 0
    var check = false

    private lateinit var mContext: Context
    private lateinit var itemElementClickListener: ItemElementClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        mContext = parent.context
        if (viewType == VIEW_TYPE_SECTION) {
            return MainHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.holder_first_page, parent, false)
            )

        }
        return SecondHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.holder_second_page, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        try {
            val model = data[position]
            if (holder is MainHolder) {
                holder.tvStartTime.text = model.startTime.replace("-", "/")
                holder.tvEndTime.text = model.endTime.replace("-", "/")
                holder.tvTemp.text = model.parameter.parameterName + "C"
                holder.layCon.setOnClickListener {
                    itemElementClickListener.onItemClick(position)
                }

            } else if (holder is SecondHolder) {
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int {
//        if (data.size % 2 != 0) {
//            return data.size + 1
//        } else {
//            return data.size + (data.size / 2)
//        }
        return data.size

    }

    override fun getItemViewType(position: Int): Int {
        if (position % 3 == 2) {
            return VIEW_TYPE_ITEM
        }
        return VIEW_TYPE_SECTION

    }

    fun updateData(data: ArrayList<time>) {
        runOnUI {
            this.data = data
            notifyDataSetChanged()
        }


    }

    fun addOnClickListener(itemElementClickListener: ItemElementClickListener) {
        this.itemElementClickListener = itemElementClickListener
    }
}