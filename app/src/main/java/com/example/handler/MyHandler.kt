package com.example.handler

 open class MyHandler(looper: MyLooper?) {
     private val queue: MyMessageQueue= looper!!.queue
     fun sendMessageDelayed(msg: MyMessage,delayMillis: Long){
         msg.mywhen=delayMillis
         queue.enqueueMessage(msg)
     }
     open fun sendMessage(msg: MyMessage){
         msg.what=0
         queue.enqueueMessage(msg)
     }
     fun post(runnable: Runnable){
         val msg= MyMessage.obtain()
         msg.callback=runnable
         sendMessage(msg)
     }
     fun postDelayed(runnable: Runnable,delayMillis: Long){
         val msg= MyMessage.obtain()
         msg.callback=runnable
         sendMessageDelayed(msg,delayMillis)
     }


}