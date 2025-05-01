package com.example.handler
object MyLooper {
    private val sThreadLocal = ThreadLocal<MyLooper?>()
    val queue= MyMessageQueue()
    fun prepare(){
        if (sThreadLocal.get()!=null){
            throw RuntimeException("false")
        }
        sThreadLocal.set(this)
    }
    fun getLooper(): MyLooper{
        val looper = sThreadLocal.get()
        if (looper==null){
            throw RuntimeException("No Looper;Looper.prepare()wasn't called on this thread.")
        }
        return looper
    }
    fun loop(){
        val me =getLooper()
        val queue=me.queue
        while (true){
            val msg=queue.next()
            msg?.target?.handleMessage(msg)
        }
    }

}
