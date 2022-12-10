package com.example.ex20221004

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


// 상속관계 , 코틀린은 콜론(:)으로 상속한다
// 부모에게 생성자가 있으면 자식도 생성자가 있어야한다

// SQLiteOpenHelper 생성자의 파라미터 4개
// 1 context : 화면 정보
// 2 name : db 파일 이름
// 3 factory : null  커서의 종류
// 4 version : DB버전. 이번에는 1version을 사용한다

class DBHelper(context: Context):
    SQLiteOpenHelper(context, "chatDatabase.db", null, 1) { // 생성자를 따로 만들지 않아도 된다. 이게 생성자니까
    // SQLiteOpenHelper(context, "dbname.db"(확장자도 써야한다), 커서의 종류, 버전)

    // context : 화면정보, Activity만 context가 있다
    // Activity에서 받자!

    // MainActivity에서 DBManager 객체를 생성하면서 context를 보내고
    // DBManager에서 DBHelper 객체를 생성하면서 context를 보낸다



    // DBHelper의 기능 : db 파일 생성하고 db 연결하고, 테이블 생성

    override fun onCreate(p0: SQLiteDatabase?) {

        // 테이블 생성 메소드
        // SQLiteDatabase : 명령어를 전송하는 통로
        // 여기에서 Create문을 전송

        // String -> varchar

        var sql = "create table chat(msg varchar(50))"
        p0?.execSQL(sql)


    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

        // Database의 버전이 업데이트가 되면 호출하는 메소드
        // 예를 들면 테이블의 구조가 바뀔 때 호출한다


        // 테이블을 수정하고 싶은 데 어플을 이미 설치했다
        // 어플을 지우고 다시 설치하지 않고 테이블을 수정하는 메소드이다

        var sql = "drop table chat"
        p0?.execSQL(sql) // 테이블 삭제

        onCreate(p0) // 테이블 다시 생성


    }


}
//