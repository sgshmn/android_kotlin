package com.example.ex20221004

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // insert, select기능 사용하기 위해 만드는 객체
        val manager = DBManager(this)


        // id 초기화
        val lv = findViewById<ListView>(R.id.lv)
        val etMsg = findViewById<EditText>(R.id.etMsg)
        val btnSend = findViewById<Button>(R.id.btnSend)

        // 1 ListView를 보여줄 위치 선정
        // main xml에 ListView를 추가
        
        // 2 ListView의 항목 Layout구성
        // 한 칸에 들어갈 내용 디자인
        // chat_list.xml
        
        // 3 ListView에 들어갈 데이터 만들기
        // 클래스(int, String), mutablelist
        // 채팅 데이터 만들기
        var chat = mutableListOf<String>()
//        chat.add("태양쌤 : 자연쌤, 인공지능사관학교는 어둡겠네요")
//        chat.add("자연쌤 : 왜죠?")
//        chat.add("영화쌤 : 왜여?")
//        chat.add("태양쌤 : 태양이 없어서")
//        chat.add("자연쌤이 대화방을 나갔습니다")
//        chat.add("영화쌤이 대화방을 나갔습니다")

        // ++++++++
        // select : listview에 템플릿과 데이터를 합쳐서 쌓기 전에 해야한다
        chat = manager.select() // ???

        // androidstudio 맨 위 막대에 있는 View
        // tool windows, device file explorer


        // ListView의 원리
        // 템플릿(chat_list.xml)에 데이터(chat)를 적어서 한줄 한줄 추가한다
        // Adapter의 역할 : 템플릿과 데이터를 합치고, ListView에 한칸 한칸 채워넣는다


        // 4 Adapter 생성
        // ArrayAdapter 또는 BaseAdapter를 상속받는 클래스

        // 안드로이드에서 기본적으로 제공하는 Adapter
        // ArrayAdapter<String>(context정보, ListView한칸에 들어갈 디자인, tvMsg의 id, 데이터)

        val adapter = ArrayAdapter<String>(
            this,
            R.layout.chat_list,
            R.id.tvMsg,
            chat
        )

        // 5 ListView에 adapter적용하기
        lv.adapter = adapter
        

        // 6 이벤트 달기

        // Button 눌렀을 때
        // EditText의 메세지 가져오기
        // msg 변수에 저장
        btnSend.setOnClickListener {
            var msg = etMsg.text.toString()


            // mutableList에 msg 추가하기
            chat.add(msg)

            // ++++++++
            // database에 msg추가
            // DBManager에 있는 insert로 msg를 보내기
            manager.insert(msg)

            // 메세지 추가하고 Adapter 새로고침
            // notifyDataSetChange

            adapter.notifyDataSetChanged()

        }


        // 1 휘발성 데이터
        // 변수, 배열에 잠깐 저장하고 다시 실행하면 사라진다

        // 2 비휘발성 데이터
        // 다시 실행해도 남아있는 데이터

            // 1 서버에 db를 구축하고 volley로 통신
                // 어플을 지워도 데이터가 서버에 남아있다
            // 2 firebase 얘도 일종의 db다
            // 3 내장 db (SQLite)
                // 어플을 지우면 데이터가 사라진다
                // 핸드폰 내장 db
            // 4 SharedPreference
                // 내장 db랑 비슷하지만 더 가볍다
                // key, value 저장
                // Fragment끼리 정보를 전달할 때
                // 어플 첫 실행 감지할 때


        // db연동 방법

        // 1 기능이 이미 있다
        // 그냥 갖다 쓰자

        // SQLiteOenHelper에 다~~~~ 있다

        // 2 DBHelper 클래스 만들기
        // Database 파일 생성
        // 테이블 생성

        // 3 DBManager 클래스 만들기
        // sql문 전송! (insert, select, delete등)






    }
}