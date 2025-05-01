package com.example.handler

abstract class MyHandler {
    private val looper = MyLooper.getLooper()
    private val queue=looper.queue
    fun sendMessage(message: MyMessage){
        message.target=this
        queue.enqueueMessage(message)
    }
    abstract fun handleMessage(message: MyMessage)
}