package com.example.ex20221005

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // inflate하는 대표적 예시?

        val lv = findViewById<ListView>(R.id.lv)

        // ListView를 만들 때 필요한 것


        // 1 템플릿 : phone_list.xml
        // 2 데이터 : 이미지, 이름, 전화번호
            // 3개의 정보를 한 묶음으로 저장할 수 있는 새로운 데이터 타입을 만들자 : PhoneVO (Value Object)

        // 데이터를 담는 mutablList 만들기
        val data = mutableListOf<PhoneVO>()

        // 추가
        data.add(PhoneVO(R.drawable.img, "조자연", "010-1111-1111"))
        data.add(PhoneVO(R.drawable.img, "강성관", "010-2222-2222"))
        data.add(PhoneVO(R.drawable.img, "박승현", "010-3333-3333"))
        data.add(PhoneVO(R.drawable.img, "오지영", "010-4444-4444"))
        data.add(PhoneVO(R.drawable.img, "이영웅", "010-5555-5555"))


        // 3 Adapter : 얘도 내 데이터에 맞게 바꿔야 한다
        // BaseAdapter 상속받아서 하면 된다
        // PhoneAdapter
        val adapter = PhoneAdapter(this, R.layout.phone_list, data)
        // PhoneAdapter(context, 템플릿, 데이터, )

        lv.adapter = adapter


    }
}