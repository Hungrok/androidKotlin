package com.hungrok.flashlight

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val torchServiceMode = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(torchServiceMode)  // startService 에 의한 기능 also 위젯 구조를 위함
            torchByService()
        else
            torchByActivity()
    }



    private fun torchByActivity(){
        val torch = Torch(this) // Torch 객체화, this --> Context 객체 type casting
        // 이벤트 리스너 등록 방법2 (별도 리스너 객체 등록 - 구현 class, 익명 class, 익명함수 (람다식))
        flashSwitch.setOnCheckedChangeListener{ buttonView, isChecked -> //람다식
            if(isChecked)
                torch.flashOn()
            else
                torch.flashOff()
        }


    }

    private fun torchByService(){

        // 이벤트 리스너 등록 방법2 (별도 리스너 객체 등록 - 구현 class, 익명 class, 익명함수 (람다식))
        flashSwitch.setOnCheckedChangeListener{ buttonView, isChecked ->  // 람다식
            val intent = Intent(this, TorchService::class.java) // 명시적 INTENT 내용 (대상확정)
            if(isChecked)
                intent.action = "on"
            else
                intent.action = "off"
            startService(intent)
        }
    }
}
