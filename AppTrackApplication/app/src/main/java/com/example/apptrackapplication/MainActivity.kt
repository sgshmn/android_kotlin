package com.example.apptrackapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import com.example.apptrackapplication.fragment.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLogout = findViewById<Button>(R.id.btnLogout)
        val fl = findViewById<FrameLayout>(R.id.fl)
        val bnv = findViewById<BottomNavigationView>(R.id.bnv)


        // Fragment를 구현하는 방법
        // NavigationView에서 선택한 메뉴에 따라 FrameLayout에 표시할 Fragment를 바꾼다



        bnv.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.tab1 -> {
//                    Toast.makeText(this, "1", Toast.LENGTH_SHORT).show()
                    // fragment 갈아 끼우기. 나머지도 똑같다
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl, // 어디에 fragment를 끼울지
                        HomeFragment() // 무슨 fragment를 넣을지
                    ).commit()


                }
                R.id.tab2 -> {
//                    Toast.makeText(this, "2", Toast.LENGTH_SHORT).show()

                    supportFragmentManager
                }
                R.id.tab3 -> {
//                    Toast.makeText(this, "3", Toast.LENGTH_SHORT).show()
                }
                R.id.tab4 -> {
//                    Toast.makeText(this, "4", Toast.LENGTH_SHORT).show()
                }
            }
            
            // false : event 처리가 안 끝났음
            // true : event 끝나서 다른거 누를 수 있음
            true
            
        }
        



    }
}