package com.example.apptrackapplication.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.apptrackapplication.MainActivity
import com.example.apptrackapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 1 id
        // 뷰 + 어느페이지 + 나타내는 값(기능)
        val btnLoginLogin = findViewById<Button>(R.id.btnLoginLogin)
        val btnLoginJoin = findViewById<Button>(R.id.btnLoginJoin)
        val etLoginEmail = findViewById<EditText>(R.id.etLoginEmail)
        val etLoginPw = findViewById<EditText>(R.id.etLoginPw)

        // 2 auth
        auth = Firebase.auth




        btnLoginJoin.setOnClickListener {
            val intt = Intent(this, JoinActivity::class.java)
            startActivity(intt)
        }

        // btnLoginLogin : 로그인하기 버튼 눌렀을 때
        // 1) EditText에 적혀있는 id, pw값 가져오기
        // 2) auth -> SignInWithEmailAndPassword로 email,pw
        // 보내기 task로 결과값 받아와서 판단하기
        // 로그인성공 -> 토스트로 "로그인성공"
        // MainActivity로 이동!
        // 로그인실패 -> 토스토로 "로그인실패"


        btnLoginLogin.setOnClickListener {

            val email = etLoginEmail.text.toString()
            val pw = etLoginPw.text.toString()

            auth.signInWithEmailAndPassword(email, pw).addOnCompleteListener(this) { task ->

                if (task.isSuccessful) {
                    Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                    var intt = Intent(this, MainActivity::class.java)
                    startActivity(intt)
                } else {
                    Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
                }

            }

        }

    }
}