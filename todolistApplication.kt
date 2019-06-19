package com.hungrok.todolist

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration



class MyApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        System.out.printf("MyApplication-onCreate , Realm init done\n")
        // KHR comment - need below as migration exception we have
        val realmConfig = RealmConfiguration.Builder()
            .name("todo.realm")
            .schemaVersion(0)
            .build()
        Realm.setDefaultConfiguration(realmConfig)

    }
}