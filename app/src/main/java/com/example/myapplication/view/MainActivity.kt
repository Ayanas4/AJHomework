package com.example.myapplication.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.MainAdapter
import com.example.myapplication.http.ApiGetQuery
import com.example.myapplication.http.HttpCallBack
import com.example.myapplication.http.JsonUtility
import com.example.myapplication.http.MyTypeAdapterFactory
import com.example.myapplication.interfaces.ItemElementClickListener
import com.example.myapplication.model.DataModel
import com.example.myapplication.model.time
import com.example.myapplication.utils.Constants.Companion.Authorization
import com.example.myapplication.utils.Constants.Companion.PREFS_NAME
import com.example.myapplication.utils.Constants.Companion.URL_WHEATHER
import com.example.myapplication.utils.Constants.Companion.format
import com.example.myapplication.utils.Constants.Companion.locationName
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    var adapter = MainAdapter()
    var data = ArrayList<time>()
    var time = ArrayList<time>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }


    fun init() {
        try {
            if (getUserInfo()) {
                Toast.makeText(
                    this@MainActivity,
                    "歡迎回來",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                saveUserInfo()
            }


            rsv_main.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
            )
            rsv_main.adapter = adapter
            adapter.addOnClickListener(object : ItemElementClickListener {
                override fun onItemClick(position: Int) {
                    val intent = Intent(this@MainActivity, SecondActivity::class.java)
                    intent.putExtra("data", time)
                    intent.putExtra("position", position)
                    this@MainActivity.startActivity(intent)
                }
            })
            getData()


        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun saveUserInfo() {
        val sharePre = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        val editor = sharePre.edit()
        editor.putBoolean("check", true)
        editor.commit() //提交修改
    }

    private fun getUserInfo(): Boolean {
        val sharePre = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        return sharePre.getBoolean("check", false)
    }


    fun getData() {
        ApiGetQuery(
            this,
            URL_WHEATHER + "Authorization=$Authorization&format=$format&locationName=$locationName",
            HashMap(),
            object : HttpCallBack {
                override fun onSuccess(jsonStr: String?) {
                    val success = JsonUtility.getResponseStatus(jsonStr)
                    var count = 0
                    var size = 0
                    if (success == "true") {
                        val gson =
                            GsonBuilder().registerTypeAdapterFactory(MyTypeAdapterFactory<Any?>())
                                .create()
                        val model = gson.fromJson(jsonStr, DataModel::class.java)
                        for (i in 0 until model.records.location.size) {
                            for (k in 0 until model.records.location[i].weatherElement.size) {
                                if (model.records.location[i].weatherElement[k].elementName == "MinT") {
                                    data.addAll(model.records.location[i].weatherElement[k].time)
                                    //data.addAll(model.records.location[i].weatherElement[k].time)
                                }
                            }
                        }


                        if (data.size % 2 == 0) {
                            size = data.size + (data.size / 2)
                        } else {
                            size = data.size + 1
                        }
                        for (k in 0 until size) {
                            if (k % 3 == 2) {
                                var fake = data[0]
                                fake.tpye = 1
                                time.add(fake)
                            } else {
                                time.add(data[count])
                                count++
                            }

                        }

                        adapter.updateData(time)


                    } else {
//                        SystemUtility.simpleToast(this, msg)
                    }
                }

                override fun onFail(msg: String?) {
                }
            })
    }


}