package com.example.handler
class MyLooper private constructor(){
    val queue: MyMessageQueue= MyMessageQueue()
    private var isRunning =true
    companion object{
        private val threadLocal= ThreadLocal<MyLooper>()
        fun myLooper():MyLooper?=threadLocal.get()
        fun prepare(){
            if(threadLocal.get()!=null){
                throw RuntimeException("Only one Looper may be created per thread")
            }
            val looper= MyLooper()
            threadLocal.set(looper)
        }
        fun loop(){
            val me=myLooper()?:
            throw RuntimeException("No Looper;Looper.prepare()wasn't called on this thread")
            val queue=me.queue
            while (true){
                val msg=queue.next()?:continue
                try {
                    msg.callback?.run()
                }catch (e: Exception){
                    e.printStackTrace()
                }finally {

                }
            }
        }
    }
}
