package com.example.handler

import java.util.concurrent.locks.Condition
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

class MyMessageQueue {
    private val queue =mutableListOf<MyMessage>()
    private val lock: Lock= ReentrantLock()
    private val condition:Condition =lock.newCondition()
    fun enqueueMessage(message: MyMessage){
        lock.lock()
        try {
            queue.add(message)
            condition.signalAll()
        }finally {
            lock.unlock()
        }
    }
    fun next(): MyMessage?{
        lock.lock()
        try {
            while (queue.isEmpty()){
                condition.await()
            }
            return queue.removeAt(0)
        }catch (e: InterruptedException){
            e.printStackTrace()
        }finally {
            lock.unlock()
        }
        return null
    }
}