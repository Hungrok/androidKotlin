package com.hungrok.mywebbrowser

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*

// 4번째 연습 App 으로서 다음 사항을 중점으로 합니다
//1. WebView 를 통한 브라우저 사용하기
//2. 메뉴 : 옵션메뉴, 콘텍스트 메뉴 구성 및 이벤트 처리
//3. 암묵적 Intent 를 통한 다른 activity 호출
//
class MainActivity : AppCompatActivity() {

    val MY_ACTION_DIAL:String = "CallActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView.apply{// 코틀린 scope 함수 객체.apply - webView 객체에 대한 필드 직접접근
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient() // new 객체화
        }
        /* 참고 : Java style
        WebView myWebView = (WebView) view.findViewById(R.id.webview);
        WebVWebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl("https://www.daum.net");
        */

        webView.loadUrl("https://www.daum.net")
        registerForContextMenu(webView) // 콘텍스트 메뉴가 있음을 등록

        /* Java Style - 익명 class
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    doSomething();
                    return true;
                }
                return false;
            }
        });
        // Java Style - 람다식 익명함수
        editText.setOnEditorActionListener(v,actionId,event)-> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                doSomething();
                return true;
            }
            return false;
        };
        */
        // KOTLIN LAMBDA식 익명함수
        // (type) -> type = { -> }
        // { -> } // interface 가 지니는 추상함수의 parameter type 이 지정되어있으니 type 추론 가능
        urlEditText.setOnEditorActionListener{_, actionId,_ ->  // 입력매개변수 3개 로 -> 의 body 를 지닌다
            if (actionId ==  EditorInfo.IME_ACTION_SEARCH){
                webView.loadUrl(urlEditText.text.toString())
                true  // return 은 암묵적으로 사용이 안된다
            } else{
                false
            }
        }
    }

    override fun onBackPressed() { // 시스템 Navigation Bar 이벤트 - Activity 자체
        if (webView.canGoBack())    webView.goBack()
        else    super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean { // ActionBar 에 menu 화면 붙이기 callback
        menuInflater.inflate(R.menu.main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean { // 옵션메뉴 이벤트 리스너 - Activity 자체
        when (item?.itemId){
            R.id.action_google, R.id.action_home -> {
                webView.loadUrl("https://www.google.com")
                return true
            }
            R.id.action_naver -> {
                webView.loadUrl("https://www.naver.com")
                return true
            }
            R.id.action_daum -> {
                webView.loadUrl("https://www.daum.net")
                return true
            }
            R.id.action_call -> { // 암묵적 INTENT - 전화걸기

                //val intent = Intent(Intent.ACTION_DIAL) // Intent 객체화
                // intent.data = Uri.parse("tel:031-123-4567")
                val intent = Intent(Intent.ACTION_DIAL,Uri.parse("tel:031-123-4567"))
                if (intent.resolveActivity(packageManager) != null){
                    startActivity(intent)
                }
                return true
            }
            R.id.action_send_text -> { // 암묵적 INTENT - 문자보내기

                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("sms:031-123-4567")
                intent.putExtra("sms_body", "안녕하세요")

                if (intent.resolveActivity(packageManager) != null){
                    startActivity(intent)
                }
                return true
            }
            R.id.action_email -> {  // 암묵적 INTENT - EMail
                val emailIntent = Intent(Intent.ACTION_SEND)
                // The intent does not have a URI, so declare the "text/plain" MIME type
                emailIntent.type = "text/plain"
                emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("jon@example.com")) // recipients
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email subject")
                if (emailIntent.resolveActivity(packageManager) != null){
                    startActivity(emailIntent)
                }
                return true
            }

        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context,menu)
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean { // Context 메뉴 이벤트 리스너 - Activity 자체
        when (item?.itemId){

            R.id.action_share -> { // 암묵적 INTENT - 공유
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, "Hello") // recipients
                if (intent.resolveActivity(packageManager) != null){
                    startActivity(intent)
                }
                return true
            }
            R.id.action_browser -> { // 암묵적 INTENT - 기본 브라우저

                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("http://www.example.com")

                if (intent.resolveActivity(packageManager) != null){
                    startActivity(intent)
                }
                return true
            }
        }


        return super.onContextItemSelected(item)
    }


}
