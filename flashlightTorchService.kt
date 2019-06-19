package com.hungrok.flashlight

import android.app.Service
import android.content.Intent
import android.os.IBinder

class TorchService : Service() {

    private val torch: Torch by lazy { // Torch class 객체화 -- lazy 이용
        Torch(this)
    }

    // by Main Thread 에 의하여 Service context 호출
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        if(intent!!.action=="on")
            torch.flashOn()
        else
            torch.flashOff()

        return super.onStartCommand(intent, flags, startId)
    }
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}
