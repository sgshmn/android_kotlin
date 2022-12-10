package com.example.ex20221004

import android.content.Context
import android.database.sqlite.SQLiteDatabase

// Kotlin 생성자
// class 클래스 이름 (바로 초기화할 필드)

class DBManager(context: Context) {


    var helper = DBHelper(context)

    // insert : MainActivity에서 msg데이터 받아서 db에 저장한다

    fun insert(msg: String) { // 일부러 오류내서 만드는 방법 : refactory기법?

        // db와 통로를 이어주는 SQLiteDatabase가 없다
        // sql을 보내려면 통로가 필요하다

        var db : SQLiteDatabase = helper.writableDatabase

        db.execSQL("insert into chat values('$msg')")
    }




    // select : 어플 실행할 때 모든 msg데이터를 받는다

    fun select(): MutableList<String> {

        // select한 데이터를 담을 공간

        var chat = mutableListOf<String>()

        var db : SQLiteDatabase = helper.readableDatabase

        var cursor = db.rawQuery("select * from chat", null)
        // selectionArgs : 조건을 만족하는 데이터를 select할 때 조건(? 모른다는게 아니고 진짜 물음표. 근데 이 물음표가 뭘 말하는 지 모름)을 채우는 역할


        // cursor.moveToNext() : Boolean타입
        if (cursor.moveToNext()) {
            var msg = cursor.getString(0)
            chat.add(msg)
        }

        // cursor.moveToNext() : cursor를 한 칸 움직인다
        // 움직이면서 데이터가 있는 지 없는 지를 Boolean으로 알려준다

        // cursor는 데이터가 있는 동안 계속 한 칸씩 움직여야한다
        // while을 쓰면 가능하다
        // 데이터가 있으면 cursor가 가리키는 데이터를 chat에 담는다

        return chat


    }


}