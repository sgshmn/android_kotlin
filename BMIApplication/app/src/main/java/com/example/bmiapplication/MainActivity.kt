package com.example.bmiapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.LogPrinter
import android.widget.Button
import android.widget.EditText
import com.example.bmiapplication.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. findViewById하기!

        var etWeight = findViewById<EditText>(R.id.etWeight)
        var etHeight = findViewById<EditText>(R.id.etHeight)
        var btnSend = findViewById<Button>(R.id.btnSend)


        // 2. Button에 클릭 이벤트 달아주기!

        btnSend.setOnClickListener {
            // 2-1) Button을 클릭했을 때 EditText에 적힌 키, 몸무게
            // 값을 가져와서 저장 (height, weight 변수에 담아주기)
            var weight = etWeight.text.toString()
            var height = etHeight.text.toString()

//            Log.d("w", weight)
//            Log.d("h", height)

            // 2-2) ResultActivity로 이동하는 Intent 생성
            var intt = Intent(this, ResultActivity::class.java)

            // 2-3) 생성된 Intent에 height랑 weight 담아주기
            // intent.putExtra(키 값, Value값 )
            intt.putExtra("w", weight)
            intt.putExtra("h", height)




            // 2-4) Intent 실행 ~
            startActivity(intt)
        }







    }
}