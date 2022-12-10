package com.example.bmiapplication

//import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result2)

        // 1. findViewById() 하기

        var tvResult = findViewById<TextView>(R.id.tvResult)
        var imgStatus = findViewById<ImageView>(R.id.imgStatus)
        var btnBack = findViewById<Button>(R.id.btnBack)

        // 2. MainActivity에서 보내준 데이터 꺼내기
        // 사용할 함수 = getStringExtra(키값 )

        var weight = intent.getStringExtra("w")!!.toDouble()
        var height = intent.getStringExtra("h")!!.toDouble()

//        Log.d("zz", "" + weight + height)

        // 3. BMI 계산식 : 자신의 몸무게를 키의 제곱으로 나눈것
        // weight/Math.pow(height/100.0,2.0)

        var bmi = weight*100*100/height/height

        // 4. bmi 수치에 따른 결과값을 tvResult에 표시
        // 판단을 한다! ---> 조건식
        // when문 사용하기
        //                   tvResult    img바꾸기
        // 수치가 35 이상이면 "고도비만" ---> bad
        // 수치가 30 이상이면 "2단계비만" ---> bad
        // 수치가 25 이상이면 "1단계비만" ---> bad
        // 수치가 23 이상이면 "과체중" ---> normal
        // 수치가 18.5 이상이면 "정상" ---> satisfied
        // 수치가 18.5 미만이면 "저체중" ---> bad
        when {

            bmi >= 35 -> tvResult.text = "고도비만"
            bmi >= 30 -> tvResult.text = "2단계비만"
            bmi >= 25 -> tvResult.text = "1단계비만"
            bmi >= 33 -> tvResult.text = "과체중"
            bmi >= 18.5 -> tvResult.text = "정상"
            bmi < 18.5 -> tvResult.text = "저체중"

        }

        when {

            bmi >= 35 -> imgStatus.setImageResource(R.drawable.bad)
            bmi >= 30 -> imgStatus.setImageResource(R.drawable.bad)
            bmi >= 25 -> imgStatus.setImageResource(R.drawable.bad)
            bmi >= 33 -> imgStatus.setImageResource(R.drawable.soso)
            bmi >= 18.5 -> imgStatus.setImageResource(R.drawable.good)
            bmi < 18.5 -> imgStatus.setImageResource(R.drawable.bad)

        }

        btnBack.setOnClickListener {
            var backintt = Intent(this, MainActivity::class.java)
            startActivity(backintt)

            finish()

        }





    }
}