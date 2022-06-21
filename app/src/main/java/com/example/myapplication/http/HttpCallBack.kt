package com.example.myapplication.http

interface HttpCallBack {
    fun onSuccess(jsonStr: String?)
    fun onFail(msg: String?)
}