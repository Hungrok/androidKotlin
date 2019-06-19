package com.hungrok.gpsmap

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton

// 구글서비스 이용 App - 지도/위치, 광고
// 구글서비를 이용하기 위하여는 구글 API key 필요
class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    // 구글 맵서비스
    private lateinit var mMap: GoogleMap
    // 구글 위치서비스
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        // SupportMapFragment 객체가 정적으로 만들어져 있으며, SupportMapFragment class 는 구글 라이브러리것 이용
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this) // 프래그먼트에 Map 표현

        locationInit() // 위치서비스 init

    }

    val REQUEST_ACCESS_FINE_LOCATION = 1000  // 권한요청 code 로 사용
    val PER1 = Manifest.permission.ACCESS_FINE_LOCATION  // 편의상
    var permissionGranted = false ;

    private fun runtimePermission(){ // runtime permission 에 대한 권한요청
        // step1. 권한이 부여되었는지 확인
        if(ContextCompat.checkSelfPermission(this,PER1)!= PackageManager.PERMISSION_GRANTED){
            // 이전에 사용자가 권한요청을 거부했는지 확인 - true 시 권한거부이력
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,PER1))
            {   // ANKO 스타일 Dialog (alert) - 간편처리
                alert("위치정보를 얻으려면 권한이 필수로 필요합니다","권한필요이유"){
                    yesButton{ // 권한부여 하락
                        // 권한요청
                        ActivityCompat.requestPermissions(this@MapsActivity, arrayOf(PER1), REQUEST_ACCESS_FINE_LOCATION)
                    }
                    noButton{} // 권한거부 지속
                }.show()
            }
            else{
                // 권한요청
                ActivityCompat.requestPermissions(this, arrayOf(PER1), REQUEST_ACCESS_FINE_LOCATION)
            }
        }
        else{ // 권한이 부여 되었다면
            permissionGranted = true ;
        }

    }

    // runtime permission 요청에 따른 callback 이벤트
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_ACCESS_FINE_LOCATION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                permissionGranted = true;
            else
                permissionGranted = false ;
        }

    }


    @SuppressLint("MissingPermission")
    private fun addLocationListener(){
        // Location Listener 등록 - 위치권한 (runtime permission) 필요
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback,null)
    }

    override fun onResume() {
        super.onResume()
        runtimePermission()
        if(permissionGranted)
            addLocationListener()
    }

    override fun onPause() {
        super.onPause()
        //  현재 위치요청을 삭제
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)

    }


    override fun onMapReady(googleMap: GoogleMap) { // SupportMapFragment --> GoogleMap 객체전달
        mMap = googleMap
        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    private fun locationInit(){ // 위치서비스를 위한 초기화
        fusedLocationProviderClient = FusedLocationProviderClient(this) // client 객체
        locationCallback = MyLocationCallBack() // callback 객체
        locationRequest = LocationRequest() // 정보객체

        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 10000
        locationRequest.fastestInterval = 5000


    }

    inner class MyLocationCallBack : LocationCallback(){
        override fun onLocationResult(locationResult: LocationResult?) {
            super.onLocationResult(locationResult)
            val location = locationResult?.lastLocation
            location?.run{
                val lating = LatLng(latitude, longitude)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lating,17f))
            }
        }
    }
}
