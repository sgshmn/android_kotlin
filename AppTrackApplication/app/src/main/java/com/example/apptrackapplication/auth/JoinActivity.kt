package com.example.apptrackapplication.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.apptrackapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class JoinActivity : AppCompatActivity() {

    // Firebase에서 제공하는 auth를 저장할 변수
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        // 1 id
        val etJoinEmail = findViewById<EditText>(R.id.etJoinEmail)
        val etJoinPw1 = findViewById<EditText>(R.id.etJoinPw1)
        val etJoinPw2 = findViewById<EditText>(R.id.etJoinPw2)
        val btnJoinJoin = findViewById<Button>(R.id.btnJoinJoin)

        // 2 auth
        auth = Firebase.auth




        // 3 btnJoinJoin 눌렀을 때
        btnJoinJoin.setOnClickListener {

            var isJoin = true // 회원가입이 되는 조건을 충족했는지 저장하는 변수
            // firebase의 기준을 만족했는지 여부를 여기서 미리 확인한다

            // 3-1 id, pw1, pw2를 가져온다

            val email = etJoinEmail.text.toString()
            val pw1 = etJoinPw1.text.toString()
            val pw2 = etJoinPw2.text.toString()

            // 3-2) EditText가 null인지 확인
            //    if(email.isEmpty())  // true or false : isJoin = False(firebase 저장X)
            // 토스트로 "email을 입력하세요"
            // 3-3) pw1랑 pw2가 다르면 토스로 "비밀번호를 똑같이 입력해주세요."  isJoin = False(firebase 저장X)
            // 3-4) password가 6글자 이상인지 확인
            //      변수명.length "비밀번호를 6자리 이상으로 입력해주세요."
            // 4) isJoin = true firebase에 이메일 & 비밀번호 전송(저장)
            // createUserWithEmailAndPassword

//            if (email.isEmpty()) {
//                isJoin = false
//            }

//            Log.d("hhh", pw1) // 아무것도 입력하지 않았을 때 오류가 안 뜬다. 공백을 출력하지만. D/hhh:
//            Log.d("hhh", pw1.javaClass.simpleName) // D/hhh: String
            // 그래서 isEmpty는 안 해도 된다
            // 공백이 나머지 조건을 만족할 리가 없기 때문이다

            if (pw1.length < 6) {
                isJoin = false
                Toast.makeText(this, "비밀번호는 5글자보다 길어야해요", Toast.LENGTH_SHORT).show()
            } else if (pw1 != pw2) {// java와 다르게 equals를 안써도 된다.
//                Log.d("same", "not")
                isJoin = false
                Toast.makeText(this, "비밀번호가 서로 달라요", Toast.LENGTH_SHORT).show()
            }


            if (isJoin) {
                auth.createUserWithEmailAndPassword(email, pw1).addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this, "이메일 형식이 틀렸어요", Toast.LENGTH_SHORT).show()
                    }
                }
            }


        }



    }
}