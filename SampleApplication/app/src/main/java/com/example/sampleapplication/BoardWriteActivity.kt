package com.example.sampleapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class BoardWriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_write)

        val btnUpload = findViewById<Button>(R.id.btnUpload)
        val etContent = findViewById<EditText>(R.id.etContent)

        btnUpload.setOnClickListener {

            val text =  etContent.text.toString()

            // db기능 가져오기
            val db = Firebase.database

            // db에 앱이 직접 저장 x
            // 인텐트, SQLite 처럼 매개체를 이용
            // databaseReference

            val myRef = db.getReference("board")
//            myRef.setValue(text) // 내용을 누적할 수 없다. 수정만 할 수 있다
            // 데이터를 쌓고 싶다
//            myRef.push().setValue(text)

//            val vo = BoardVO(text) // 안드로이드에서는 코드를 대게 한번에 쓴다. 이 방법은 잘 안 쓴다
            myRef.push().setValue(BoardVO(text))
            // 데이터를 json으로 저장한다 key : value

            finish()

        }


    }
}