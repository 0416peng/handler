package com.example.handler

import android.os.Bundle

class MyMessage {
    var what: Int=0
    var arg1: Int=0
    var arg2: Int=0
    var obj: Any?=null
    var mywhen: Long=0
    var data: Bundle?=null
    var callback: Runnable?=null
    companion object{
        fun obtain(): MyMessage{
            return MyMessage()
        }


    }
}