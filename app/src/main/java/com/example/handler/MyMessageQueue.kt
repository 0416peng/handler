package com.example.handler

import android.util.Log
import java.util.LinkedList
import java.util.concurrent.locks.Condition
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
class MyMessageQueue {
   private val message= LinkedList<MyMessage>()
    @Synchronized
    fun enqueueMessage(msg: MyMessage){
        msg.mywhen= System.currentTimeMillis()+msg.mywhen
        message.add(msg)
        (this as java.lang.Object).notifyAll()
    }
    @Synchronized
    fun next(): MyMessage?{
        while (message.isEmpty()){
            try {
                (this as java.lang.Object).wait()
            }catch (e: InterruptedException){
                e.printStackTrace()
            }
        }
        val msg=message.removeFirst()
        val delay=msg.mywhen- System.currentTimeMillis()
        if(delay>0){
            try {
                (this as java.lang.Object).wait(delay)
            }catch (e: InterruptedException){
                e.printStackTrace()
            }
        }
        return msg
    }
    @Synchronized
    fun clearMessages(){
        message.clear()
        (this as java.lang.Object).notifyAll()
    }


}