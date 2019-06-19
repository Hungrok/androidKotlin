package com.hungrok.bmicalcurator

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        resultButton.setOnClickListener {               // 이벤트 핸들러 구성
            // 이벤트 리스너 익명함수
            // 표준적인 사용방법

            if (weightEditText.text.toString() == null) {

            }
            else{

                var intent = Intent (this,ResultActivity::class.java)
                intent.putExtra("weight",weightEditText.text.toString())
                intent.putExtra("height",heightEditText.text.toString())
                startActivity(intent)
                //
                // 코틀린 AKNO 라이브러리 사용해서 단축 하기
                /*
                startActivity<ResultActivity>(
                    "weight" to weightEditText.text.toString(),
                 "height" to heightEditText.text.toString()
                )
                */
            }

         }

    }
}
