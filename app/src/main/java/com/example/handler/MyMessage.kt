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
    private var next: MyMessage?=null
   companion object{
        private var sPool: MyMessage?=null
        private var sPoolSize=0
        private const val MAX_POOL_SIZE=50
        fun obtain(): MyMessage{
                synchronized(sPool!!){
                    if(sPool!=null){
                    val m=sPool
                    sPool=m?.next
                    m?.next=null
                        m?.flags =0
                        sPoolSize--
                        return@synchronized m
                }
            }
            return MyMessage()
        }
        fun recycle(msg: MyMessage){
            synchronized(sPool!!){
                if(sPoolSize<MAX_POOL_SIZE){
                    msg.next=sPool
                    sPool=msg
                    sPoolSize++
                }
            }
        }
    }
    var flags=0
     val FLAG_IN_USE=1 shl 0
}