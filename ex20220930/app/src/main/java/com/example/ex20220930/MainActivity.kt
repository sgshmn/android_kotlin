package com.example.ex20220930

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import kotlin.math.log

// ImageView가 담길 수 있는 크기만 정해진 Array를 만들자
// 처음에는 null로
var l = 9
var imgArray = arrayOfNulls<ImageView>(l)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // id 한번에 초기화 for 문으로
        // 명시적 intent
        // Handler
        // Toast 띄우기 makeToast

        // 가로세로 120dp


        // 1. ImageView를 한번에 findViewById()를 해보자
        // 1-1) ImageView의 id값이 담길 배열 만들기
        //      단, 배열을 만들고 나서 나중에 id값을 넣을 예정
        //      그렇다면 어떤 배열이 필요할까요 ?



        // 1-2) id값만 추출할 수 있는 기능
        //      : resources.getIdentifier(아이디이름, "id", 현재 패키지 이름)

        for (i in 0 until l) {
            // R.id.img1~9
            var getId = resources.getIdentifier("img${i+1}", "id", packageName)

            // findViewById(img1~9)
            imgArray[i] = findViewById(getId)

            // imgArray에 9개의 값이 들어갔는지 확인
//            imgArray[i]?.visibility = View.INVISIBLE // 다 사라지는 거 확인함

            // imgArray에 setOnclick 달자
            imgArray[i]?.setOnClickListener {
                // i번째 ImageView의 Tag를 Toast에 띄우기

                Toast.makeText(this@MainActivity, imgArray[i]?.tag.toString(), Toast.LENGTH_SHORT).show()

            }

        }



        // 1-3) 배열을 통해서 findViewById() 진행



        // 1-4) ImageView들의 ID값이 초기화 되었는지 확인하기
        //      : 안보이게 만들기 (속성 : visibility )
        // 1-5) 9개의 ImageView 속성 중에서 tag라는 속성에 소주 이름
        //      달아주기 --> xml 파일에서 진행하고 돌아오기
        // 1-6) ImageView를 클릭 했을때!
        // 1-7) ImageView에 달려있는 Tag를 토스트 창에 띄워 보자

    }
}