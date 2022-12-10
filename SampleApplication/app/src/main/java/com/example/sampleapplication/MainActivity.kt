package com.example.sampleapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    // lateinit : 선언만 하고 초기화는 나중에
    lateinit var auth : FirebaseAuth

    // 1 인증 시스템을 지원한다
    // 인증은 FireBase에서 로그인을 담당한다
    // 직접 개발하려면 보안처리가 복잡하다 (아이디 찾기, 비밀번호 바꾸기, 이메일 인증... 귀찮)
    // 그냥 갖다 쓰자

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // id 초기화
        val btnJoin = findViewById<Button>(R.id.btnJoin)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPW = findViewById<EditText>(R.id.etPW)

        auth = Firebase.auth
        // FirebaseAuth 기능

        // createUserWithEmailAndPassword   회원가입
        // signInWithEmailAndPassword       로그인
        // updateEmail                      회원가입한 아이디 이메일 변경
        // updatePassword                   회원가입한 아이디의 패스워드 변경
        // sendPasswordResetEmail           회원가입한 비밀번호 재설정
        // delete                           회원가입 아이디 삭제



        // 이전에 회원가입한 아이디의 uid를 띄운다
        // 회원가입하면서 동시에 로그인도 하기 때문이다
        Toast.makeText(this, auth.currentUser?.uid.toString(), Toast.LENGTH_SHORT).show()

        btnJoin.setOnClickListener {
            // EditText에 있는 Email, password 가져와서 FireBase에 데이터를 저장하자 (로그인 데이터?)
            val email = etEmail.text.toString()
            val pw = etPW.text.toString()

            // FireBase
            auth.createUserWithEmailAndPassword(email, pw)
                .addOnCompleteListener(this){task ->

                    // task : 성공했는지 실패했는지
                    // task가 successful을 받았나?
                    if (task.isSuccessful) {
                        Toast.makeText(this, "성공", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "실패", Toast.LENGTH_SHORT).show()
                    }
                }



        } // btnJoin 끝

        btnLogout.setOnClickListener {

            auth.signOut() // 로그아웃
            Toast.makeText(this, auth.currentUser?.uid.toString(), Toast.LENGTH_SHORT).show()

        }

        // btnLogin

        btnLogin.setOnClickListener {

            // 1 EditText email, pw 가져오기
            // 2 로그인 기능으로 전달

            val email = etEmail.text.toString()
            val pw = etPW.text.toString()

            auth.signInWithEmailAndPassword(email, pw)
                .addOnCompleteListener(this) {task ->

                    if(task.isSuccessful){
                        Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()

                        // 로그인 성공하면 게시판으로 가자

                        var intt = Intent(this, BoardListActivity::class.java)

                        startActivity(intt)


                    } else {
                        Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
                    }

                }

        }


    }

    // firebase 사용하는 방법
    //




}