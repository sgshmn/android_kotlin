package com.example.ex20220928

import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() { // : 왼쪽이 오른 쪽을 상속받는다 java의 extends랑 같은듯
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Kotlin에서 findViewById 하기

        // 1 <> 제네릭 이용
        val btnDial = findViewById<Button>(R.id.btnDial)

        // 2 자료형 명시
        val btnWeb : Button = findViewById(R.id.btnWeb)

        val btnGoogle = findViewById<Button>(R.id.btnGoogle)
        val btnSearch = findViewById<Button>(R.id.btnSearch)
        val btnSms = findViewById<Button>(R.id.btnSms)
        val btnPhoto = findViewById<Button>(R.id.btnPhoto)


        // onclick()
        // jdk 1.8 버전 이상에서는 lambda expression을 제공한다
        btnDial.setOnClickListener {
            // 매개변수로 사용하고 있는 view : btnDial의 정보

            // 전화어플이 켜지고 해당 전화번호로 전화를 바로 걸 수 있도록 번호를 입력한 상태로 보낸다

            // Intent 사용용도

            // 1 액션 , 데이터 : 묵시적
            // 2 내가만든 Activity로 이동 : 명시적

            var uri = Uri.parse("tel:010-1234-5678") // http: ftp: 등과 같은 프로토콜
            var intent = Intent(Intent.ACTION_DIAL, uri)
            startActivity(intent)

        }

        // btnWeb -> 인터넷 주소
        // 데이터 uri타입 http:
        btnWeb.setOnClickListener {

            // web : 액션, 데이터
            // 액션에 필요한 데이터 만들기

            var uri = Uri.parse("http://www.google.com")
            var intent = Intent(Intent.ACTION_VIEW, uri) // 구글 앱?

            startActivity(intent)

        }

        // btnGoogle : 구글 맵에 대성학원 위치 보여주기
        // get, post
        // www.naver.com/?
        // http://google.com/map?q=35.1467014,126.922156
        btnGoogle.setOnClickListener {


            var uri = Uri.parse(
                "https://www.google.com/maps?q="
                        + 35.14670147841655 + "," + 126.92215633785938)
            var intent = Intent(Intent.ACTION_VIEW, uri)

            startActivity(intent)
        }

        // btnSearch
        btnSearch.setOnClickListener {
            // 액션 : 검색하고 싶은 데이터를 얹어서
            var intent = Intent(Intent.ACTION_WEB_SEARCH)
            intent.putExtra(SearchManager.QUERY, "안드로이드")

            startActivity(intent)


        }

        // btnSms
        btnSms.setOnClickListener {

            var intent = Intent(Intent.ACTION_SENDTO)

            // 문자 메시지의 문구는 putExtra
            intent.putExtra("sms_body", "안녕하세요")

            // 누구한테 보낼건지에 대한 데이터를 Uri parse
            // intent.data = smsto:010-1234-5678
            intent.data = Uri.parse("smsto:010-1234-5678")

            startActivity(intent)

        }

        // btnPhoto
        btnPhoto.setOnClickListener {
            var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(intent)
        }

    }}