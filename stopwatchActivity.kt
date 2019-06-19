package com.hungrok.stopwatch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.timer


/* 3번째 App 으로서 다음사항을 중점적으로 다룹니다
   1. ScrollView 특징 : 동적 contents, Container - LinearLayout 밑에 view 구성
   2. timer 기능이해 (Java framework 제공)
 */
class MainActivity : AppCompatActivity() {
    // 필드멤버 구성
    private var time = 0;
    public var isRunning = false
    private var timerTask: Timer? = null // nullable 표현
    private var lap = 1 // labtime 기록시 index number 로 사용


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //var fab1 = new FloatingActionButton(this)
        //var isRunning2 = false



        fab.setOnClickListener {  // 이벤트 핸들러 구성
            // 이벤트 리스너 익명클래스 , 익명함수 - onClick 오버라이드 펑션
            isRunning = !isRunning
            if(isRunning) startTimer()  // 내가 가진 멤버중 start 라는 이름을 지닌 펑션을 호출
            else pauseTimer()
            // 참고 - 익명함수도 중첩 class 임으로 outer class 의 멤버를 접근할 수 있다
        }


        resetFab.setOnClickListener {  // 이벤트 핸들러 구성
            // 이벤트 리스너 익명함수
             reset()
        }

        lapButton.setOnClickListener{
            recordLapTime()
        }
    }

    public fun pauseTimer(){ // 타이머 중지
        fab.setImageResource(R.drawable.ic_play_arrow_black_24dp)
        timerTask?.cancel() // timerTask 객체가 널이 아니면 cancel() 실행

    }

    public fun startTimer(){ // 타이머 start - KOTLIN style
        fab.setImageResource(R.drawable.ic_pause_black_24dp)
        timerTask = timer(period=10){ // 익명함수 - 타이머 쓰레드 run
            // timer thread 의 run 객체
            time++   // 1,2,3,4,,,,,every 10ms, 100 times in a second (total 1000ms)
            val sec = time/100
            val milli = time % 100 // 1000ms 를 1/10로 표현 (1..99,0)
            runOnUiThread { // 익명함수 - 메인 쓰레드 에서 실행할 익명함수
                secTextView.text = "$sec"
                milliTextView.text = "$milli"
            }
        }

    }

    public fun startTimer2() { // 자바 표준 style 참고용
        fab.setImageResource(R.drawable.ic_pause_black_24dp)
        val timer:Timer = Timer()
        val timerTask = MyTimerTask()
        timer.schedule(timerTask,0, 10)
        // Timer 객체는 soft timer 로서
        // thread 하나를 소유하여 TimerTask 가 지니는 schedule 에 맞추어
        // TimerTask 객체가 지니는 run 을 수행한다
    }

    private fun recordLapTime(){
        val lapTime = this.time
        val textView = TextView(this) // TextView 라는 클래스를 객체화 - 계속추가...
        textView.text = "$lap LAB: ${lapTime/100}.${lapTime%100}"

        // 맨위에 렙타입 추가 : scrollview - linearlayout 에 textview 동적추가
        lapLayout.addView(textView,0)
        lap++

    }

    private fun reset(){ // reset FAB 이벤트 리스너
        timerTask?.cancel() // timerTask null check (if not null then do)

        // 모든변수 초기화
        time = 0
        isRunning = false
        fab.setImageResource(R.drawable.ic_play_arrow_black_24dp)
        secTextView.text = "0"
        milliTextView.text = "00"

        lapLayout.removeAllViews() // scrollview 내부에 객체들 전부 remove
        lap= 1

    }

    inner class MyTimerTask: TimerTask(){ // 자바 표준 style 참고용

        override fun run(){
            //System.out.printf("TimerTask called \n")
            time++   // 1,2,3,4,,,,,every 10ms, 100 times in a second
            val sec = time/100
            val milli = time % 100
            runOnUiThread { // 익명함수 - 메인 쓰레드 에서 실행할 함수
                secTextView.text = "$sec"
                milliTextView.text = "$milli"
            }
        }
    }
}

// 참고..리스너 객체를 표준적으로 만드는 예
// 이것을 익명 class 로 함축1차
// 익명 class 를 lambda 식으로 함축2차
class FabOnClickListener: View.OnClickListener{ // implements View.OnClickListener

    override fun onClick(v: View?) {
        var isRunning = false ;
        var obj1 = MainActivity()
        isRunning = obj1.isRunning

        isRunning = !isRunning
        if(isRunning) obj1.startTimer()  // 내가 가진 멤버중 start 라는 이름을 지닌 펑션을 호출
        else obj1.pauseTimer()
    }


    // 익명 class 방법
        var listener = View.OnClickListener(){
            fun onClick(v:View){
                var isRunning = false ;
                var obj1 = MainActivity()
                isRunning = obj1.isRunning

                isRunning = !isRunning
                if(isRunning) obj1.startTimer()  // 내가 가진 멤버중 start 라는 이름을 지닌 펑션을 호출
                else obj1.pauseTimer()
            }
        };


}

