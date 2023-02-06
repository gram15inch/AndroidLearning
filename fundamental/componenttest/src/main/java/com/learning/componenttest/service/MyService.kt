package com.learning.componenttest.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*
import java.io.FileDescriptor
import java.io.PrintWriter
import java.util.*


class MyService : Service(){
    var coroutinesNum=0
    val jobs: Queue<Job> = LinkedList()
    override fun onCreate() {
        super.onCreate()
        Log.d("myServiceLog","onCreate")
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("myServiceLog","onStartCommand")
        CoroutineScope(Dispatchers.IO).launch {
            coroutinesNum++
            val n = coroutinesNum
            while(true){
                delay(2000)
                Log.d("myServiceLog","coroutinesNum[$n] runnig")
            }
        }.run { jobs.add(this) }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        repeat(jobs.size){
            jobs.poll()?.cancel()
        }
        Log.d("myServiceLog","onDestroy")
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d("myServiceLog","onBind")

        return null
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d("myServiceLog","onUnbind")
        return super.onUnbind(intent)
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        Log.d("myServiceLog","onRebind")
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        Log.d("myServiceLog","onTaskRemoved")
    }

    override fun dump(fd: FileDescriptor?, writer: PrintWriter?, args: Array<out String>?) {
        super.dump(fd, writer, args)
        Log.d("myServiceLog","dump")
    }
}