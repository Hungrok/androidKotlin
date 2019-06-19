package com.hungrok.xylophone

import android.media.SoundPool
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    // 객체생성 및 초기화 - 빌더 디자인 패턴
    private var soundPool : SoundPool = SoundPool.Builder().setMaxStreams(8).build()
    // 데이타 (객체는 data 의 set) 조작 - 목록화
    private val sounds = listOf( // JAVA - ArrayList
        Pair(R.id.do1, R.raw.do1), // JAVA - HashMap <View ID, raw file >
        Pair(R.id.re, R.raw.re),
        Pair(R.id.mi, R.raw.mi),
        Pair(R.id.fa, R.raw.fa),
        Pair(R.id.sol, R.raw.sol),
        Pair(R.id.la, R.raw.la),
        Pair(R.id.si, R.raw.si),
        Pair(R.id.do2, R.raw.do2)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sounds.forEach{ tune(it)} // for 문 축약문 - JAVA 제공
    }

    private fun tune(pitch: Pair<Int,Int>){
        val soundId = soundPool.load(this, pitch.second,1)

        // 각 TextView 에 대한 이벤트 리스너(객체) 등록
        findViewById<TextView>(pitch.first).setOnClickListener {
            soundPool.play(soundId,1.0f,1.0f,0,0,1.0f)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        soundPool.release()
    }
}
