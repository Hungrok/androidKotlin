package com.hungrok.flashlight

import android.content.Context
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager

// 기능의 모듈화 - 객체적 사용
class Torch (context: Context) { // () 은 constructor 매개변수와 class 의 field 를 축약한다

    private var cameraId: String? = null
    private val cameraManager = context.getSystemService(Context.CAMERA_SERVICE)
                                    as CameraManager   // 객체 type casting , Object --> CameraManager
    private val cm = cameraManager // 편의상

    init{ // Instance 초기화 방법 - JAVA 도 유사하게 존재 blank {}
        cameraId = getCameraId()
    }

    fun flashOn(){
        cm.setTorchMode(cameraId,true)
    }
    fun flashOff(){
        cm.setTorchMode(cameraId,false)
    }

    private fun getCameraId(): String?{ // camera 정보획득 --> 후방 camera ID 획득
        val cameraIds = cm.cameraIdList
        for (id in cameraIds){
            val info = cm.getCameraCharacteristics(id)
            val flashAvailable = info.get(CameraCharacteristics.FLASH_INFO_AVAILABLE)
            val lensFacing = info.get(CameraCharacteristics.LENS_FACING)
            if(flashAvailable !=null
                && flashAvailable
                && lensFacing !=null
                && lensFacing == CameraCharacteristics.LENS_FACING_BACK){
                return id
            }
        }
        return null
    }

}