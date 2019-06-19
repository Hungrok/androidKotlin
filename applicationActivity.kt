package com.hungrok.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var buttonCount: Int = 0  // 필드 - 전역변수
    val sangsoo: Int = 100 // 필드 - 전역상수
    var buttonCount2 = "acc" // 암묵적 data type





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        /*
        clickButton.setOnClickListener{
            textView.text = "버튼을 눌렀습니다"
        }
        */

        var obj1: Button = findViewById(R.id.clickButton)


        clickButton.setOnClickListener{// 익명함수
            buttonCount = ++buttonCount // = buttonCount+1
            System.out.printf("even=%d \n",buttonCount)
            if(buttonCount%2==1)
                secTextView.text = "버튼을 홀수번 눌렀습니다"
            else
                secTextView.text = "버튼을 짝수번 눌렀습니다"
        }

        /* 참고 - 익명 class 방법
        var listener = View.OnClickListener(){
            fun onClick(v:View){
                buttonCount = ++buttonCount // = buttonCount+1
            System.out.printf("even=%d \n",buttonCount)
            if(buttonCount%2==1)
                secTextView.text = "버튼을 홀수번 눌렀습니다"
            else
                secTextView.text = "버튼을 짝수번 눌렀습니다"

            }
        };
        clickButton.setOnClickListener(listener)
        */



    }
}


