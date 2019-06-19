package com.hungrok.tiltsensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

// 중점사항
// 1. 안드로이드 시스템서비스 : 알람, 알림, 센서
// 2. 이벤트 리스너 (콜백) 방법

class MainActivity : AppCompatActivity(), SensorEventListener{
    private val sensorManager by lazy {  // 필드 초기화 지연방법 - 코틀린 expression
        getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }
    //private var sm : SensorManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /* Just remark below JAVA ways - error as SensorManager does not have companion object
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE)
        */

        // Verify sensorManager is 초기화
        var str = sensorManager.toString()
        System.out.printf("sensorManager=%s \n",str)
    }


    override fun onResume() {
        System.out.printf("MainActivity-onResume \n")
        super.onResume()
        // 센서 callback 등록
        sensorManager.registerListener(this,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL)


    }

    override fun onPause() {
        System.out.printf("MainActivity-onPause \n")
        super.onPause()
        sensorManager.unregisterListener(this)
    }

   override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    // MainActivity 자체가 이벤트 리스너 임 - sensorManager.registerListener 시 등록됨 (this)
    override fun onSensorChanged(event: SensorEvent?) { // ? - nullable expression
       /* 센서값이 변경되면 호출됨 - 센서마다 values 는 다름
          TYPE_ACCELEROMETER 의 values
          values[0] : X 축값 : 위로 기울이면 -10~0 , 아래로 기울이면 0~10
          values[1] : Y 축값 : 왼쪽으로 기울이면 -10~0, 아래로 기울이면 0~10
          values[2] : Z 축값 : 미사용
        */

        var x = event!!.values[0] // 코틀린 expression : !! - null guaranty, x?.function() - null check
        var y = event!!.values[1]
        var z = event!!.values[2]
        System.out.printf("X=%f, Y=%f, Z=%f \n",x,y,z)

    }
}
