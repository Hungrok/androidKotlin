package com.hungrok.mygallery

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton

// 본 App 에서는 아래 내용을 이해 합니다
// 1. runtime 에 획득하는 권한 (permission) 처리 방법
// 2. Content Provider 기능
// 3. ViewPager 와 Fragment 이해

class MainActivity : AppCompatActivity() , PhotoFragment.OnFragmentInteractionListener {


    val REQUEST_READ_EXTERNAL_STORAGE = 1000  // 권한요청 code 로 사용
    val PER1 = Manifest.permission.READ_EXTERNAL_STORAGE  // 편의상
    var permissionGranted = false ;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        runtimePermission()
        if(permissionGranted)
            getAllPhotos()


    }


    private fun runtimePermission(){ // runtime permission 에 대한 권한요청
        // step1. 권한이 부여되었는지 확인
        if(ContextCompat.checkSelfPermission(this,PER1)!= PackageManager.PERMISSION_GRANTED){
            // 이전에 사용자가 권한요청을 거부했는지 확인 - true 시 권한거부이력
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,PER1))
            {   // ANKO 스타일 Dialog (alert) - 간편처리
                alert("사진정보를 얻으려면 외부 저장소 권한이 필수로 필여합니다","권한필요이유"){
                    yesButton{ // 권한부여 하락
                        // 권한요청
                        ActivityCompat.requestPermissions(this@MainActivity, arrayOf(PER1), REQUEST_READ_EXTERNAL_STORAGE)
                    }
                    noButton{} // 권한거부 지속
                }.show()
            }
            else{
                // 권한요청
                ActivityCompat.requestPermissions(this, arrayOf(PER1), REQUEST_READ_EXTERNAL_STORAGE)
            }
        }
        else{ // 권한이 부여 되었다면
            permissionGranted = true ;
        }

    }

    // runtime permission 요청에 따른 callback 이벤트
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_READ_EXTERNAL_STORAGE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                permissionGranted = true;
            else
                permissionGranted = false ;
        }

    }

    private fun getAllPhotos(){

        // 시스템 App (미디어저장) 이 지니는 COntent Provider 에서 image 가져오기
        val cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            null,null,null,
            MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC")

        val fragments = ArrayList<Fragment>() // Fragment 객체를 담는 ArrayList 생성

        if (cursor != null){
            while(cursor.moveToNext()) {
                val uri = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                System.out.printf("getAllPhotos, uri=%s \n",uri)
                fragments.add (PhotoFragment.newInstance(uri)) // fragment 객체생성

            }
            cursor.close()
        }

        // ViewPager 사용은 항상 아답터와 같이 사용된다
        // 아답터 (FragmentPagerAdapter) 와 연결
        val adapter = MyPagerAdapter(supportFragmentManager) // 아답터 생성
        adapter.updateFragments(fragments) // 아답터에 만들어진 객체전달
        viewPager.adapter = adapter   // ViewPager 와 아답터 연결

    }

    override fun onFragmentInteraction(uri: Uri) {

    }
}
