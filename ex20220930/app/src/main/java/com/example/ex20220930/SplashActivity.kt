package com.example.ex20220930

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // SplashActivity가 첫 화면으로 등장!

        // 등장 시간 3초

        // 3초가 지나면 MainActivity로 가즈아!

        // Handler() : MainThread로 메세지 전송
        // 메세지 : SubThread가 하는 동작을 실행하라는 요청

        //postDelayed({실행코드(Runnable)}, delay time(milli second))
        Handler().postDelayed(
            {
                // SplashActivity에서 MainActivity로 가기
                val intent = Intent(this, MainActivity::class.java)  // kotlin에서 쓰는 kclass를 java에서 쓰는 class로 바꿔야한다
                startActivity(intent)
                finish() // Activity는 Stack구조로 쌓인다. 여기 Activity를 Stack에서 제외한다는 뜻

            },3000
        )

    }
}