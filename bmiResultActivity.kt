package com.hungrok.bmicalcurator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() { // AppCompatActivity 를 상속한다
    // 클래스는 필드 (전역 변수,상수) 와 펑션 으로 멤버 구성
    // 클래스를 상속받으면 부모 및 조부모가 가진 모든 재산(멤버) 을 사용가능
    // 클래스는 두가지로 사용가능 하다
    // 1) 객체화 하여 사용
    // 2) 정적 (static) 인 멤버는 객체화 하지않고도 직접 사용 가능
    // setContentView() 는 상속받은 펑션을 호출하는 것이다
    // 펑션은 함수 혹은 메소드로서 선언 과 {메인바디} 로 구성
    // 펑션 선언 : 접근지정자 fun 펑션명 (입력매개변수): 반환 DataType
    // 펑션 메인바디는 지역변수 와 statements (문) 으로 구성
    // 지역변수는 펑션 내부에서만 사용되며 임시적으로 사용되어진다


    override fun onCreate(savedInstanceState: Bundle?) { // 상속받은 펑션을 대체한다
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // 전달받은 키와 몸무게
        val height = intent.getStringExtra("height").toInt()
        val weight = intent.getStringExtra("weight").toInt()

        // 비만도 계산
        val bmi = weight / Math.pow (height/100.0,2.0)

        // 결과를 TextView 에 표시한다
        when { // C 및 Java 의 switch 에 해당
            bmi >=35 -> resultTextView.text = "고도 비만"
            bmi >=30 -> resultTextView.text = "2단계 비만"
            bmi >=25 -> resultTextView.text = "1단계 비만"
            bmi >=23 -> resultTextView.text = "과체중"
            bmi >=18.5 -> resultTextView.text = "정상"
            else -> resultTextView.text = "저체중"
        }

        // 결과를 ImageView 에 표시한다
        when{
            bmi >=23 -> imageView.setImageResource(R.drawable.ic_sentiment_very_dissatisfied_black_24dp)
            bmi >=18.5 -> imageView.setImageResource(R.drawable.ic_sentiment_satisfied_black_24dp)
            else -> imageView.setImageResource(R.drawable.ic_sentiment_dissatisfied_black_24dp)
        }

        // printf 대신 간이 다이알로그 이용한 결과표시
        /*
        toast("$bmi") // ANKO style
        */
        // Java style
        Toast.makeText(this, "$bmi", Toast.LENGTH_SHORT).show()


    }
}
