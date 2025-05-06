 package com.example.handler

import android.R.id.input
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.handler.MyLooper.Companion.myLooper
import java.lang.Thread.currentThread

 class MainActivity : ComponentActivity() {
    private lateinit var button: Button
    private lateinit var text: TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_layout)
        button=findViewById(R.id.button)
        text=findViewById(R.id.text)
        MyLooper.prepare()
        var mHandler= myHandler(MyLooper.myLooper())
        button.setOnClickListener{
            val downLoadThread = MyThread(mHandler)
            downLoadThread.start()
        }
     }
     override fun onDestroy() {
         super.onDestroy()
     }
     class MyThread(private val mHandler: myHandler) : Thread() {
         override fun run() {
             super.run()
             Log.d("thread", currentThread().name)
             for (i in 1..100) {
                 try {
                     sleep(100)
                 } catch (e: InterruptedException) {
                     e.printStackTrace()
                 }
                 val msg = MyMessage()
                 msg.what=1
                 val bundle = Bundle()
                 bundle.putInt("progress", i)
                 msg.data = bundle
                 mHandler.sendMessage(msg)
             }
         }
     }
     inner class myHandler(myLooper: MyLooper?) : MyHandler(MyLooper.myLooper()) {
        override fun sendMessage(msg: MyMessage) {
             super.sendMessage(msg)
             Log.d("thread", currentThread().name)
            Log.d("progress", msg.data.toString())
             when (msg.what) {
                 1 -> {
                     val progress = msg.data?.getInt("progress")
                     text.setText(progress.toString())
                     if (progress == 100) {
                         text.setText("下载完成")
                     }
                 }
             }
         }
     }
 }




