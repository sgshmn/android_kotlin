package com.example.apptrackapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.apptrackapplication.auth.IntroActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // SplashActivity에서 IntroActivity로 3초되에 넘어가자
        // 3초 지연
        // SubThread -> "Handler"(강조) -> MainThread

        Handler().postDelayed({
            val intt = Intent(this, IntroActivity::class.java)
            startActivity(intt)

            finish()
        }, 3000)
    }
}