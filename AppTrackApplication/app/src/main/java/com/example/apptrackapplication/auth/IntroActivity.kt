package com.example.apptrackapplication.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.apptrackapplication.MainActivity
import com.example.apptrackapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class IntroActivity : AppCompatActivity() {

    lateinit var auth : FirebaseAuth // 선언만 하고 초기화는 나중에 함

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        auth = Firebase.auth

        // id
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnJoin = findViewById<Button>(R.id.btnJoin)
        val btnNo = findViewById<Button>(R.id.btnNo)

        btnJoin.setOnClickListener {
            val intt = Intent(this, JoinActivity::class.java)
            startActivity(intt)
        }

        btnLogin.setOnClickListener {
            val intt = Intent(this, LoginActivity::class.java)
            startActivity(intt)
        }


        btnNo.setOnClickListener {

            auth.signInAnonymously().addOnCompleteListener(this) {task ->
                if (task.isSuccessful) {
//                    Toast.makeText(this, "익명 로그인 성공", Toast.LENGTH_SHORT).show()

                    val intt = Intent(this, MainActivity::class.java)
                    startActivity(intt)

                } else {
//                    Toast.makeText(this, "익명 로그인 실패", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}