package com.hungrok.mytoolbar

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        if (intent.getAction() == Intent.ACTION_TIMEZONE_CHANGED) {
            System.out.printf("Received ACTION_TIMEZONE_CHANGED \n")
        }
    }
}
