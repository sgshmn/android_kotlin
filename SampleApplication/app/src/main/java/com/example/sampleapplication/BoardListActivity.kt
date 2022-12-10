package com.example.sampleapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class BoardListActivity : AppCompatActivity() {

    lateinit var adapter : BoardAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_list)

        // id 초기화

        val btnWrite = findViewById<Button>(R.id.btnWrite)
        val lv = findViewById<ListView>(R.id.lv)

        // btnWrite 누르면 BoardWriteActivity로 이동
        // 명시적 인텐트 (시작점, 도착점 ::class.java)

        btnWrite.setOnClickListener {
            var intt = Intent(this, BoardWriteActivity::class.java)

            startActivity(intt)
        }


        // 1 임시 데이터 만들기
        val list = mutableListOf<BoardVO>()

//        list.add(BoardVO("haha"))
//        list.add(BoardVO("gg"))


        val db = Firebase.database
        val myRef = db.getReference("board")

        val postListener = object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {

                // 어떤 형태로 데이터를 받을까?
                // snapshot에 들어있는 데이터는 json

                // snapshot.children에 있는 데이터를 i에 넣기
                // snapshot.children의 크기만큼 반복

                list.clear() // 전에 가져온거 지우기

                for (i in snapshot.children) {
                    val item : BoardVO = i.getValue(BoardVO::class.java)!!
                    // null 이면 for 문이 안 돌아간다 그래서 !!

                    list.add(item)
                }

                adapter.notifyDataSetChanged()


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        } // Listener 끝

        // myRef라는 경로에서 db가져온다
        myRef.addValueEventListener(postListener)








        adapter = BoardAdapter(this, R.layout.listview_item, list)
        lv.adapter = adapter




    }
}