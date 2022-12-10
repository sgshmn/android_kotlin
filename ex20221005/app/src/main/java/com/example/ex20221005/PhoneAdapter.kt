package com.example.ex20221005

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.net.URI

class PhoneAdapter
    (context: Context, layout: Int, data: MutableList<PhoneVO>): BaseAdapter() {

    // 클래스 내부에서 쓰기 위해 필드를 만들자

    val context = context
    val layout = layout
    val data = data

    // inflate : 눈에 보이는 화면으로 바꾸는 기능
    // inflater : 그거 하는 거
    // 근데 그거는 Activity에서만 할 수 있어서 Activity에서 받아야 함
    
    val inflater : LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    
    // as : 형변환 키워드
    // getSystemService() 에 여러 서비스가 있다
    // 조도센서. 수평센서, 온도센서 등
    // 근데 Any로 돌려준다
    // 그래서 형변환을 해야한다
    
    
    // 항목 개수
    override fun getCount() = data.size

    // 항목 정보
    override fun getItem(p0: Int) = data[p0]
    // position > i > p0
    // 항목의 위치, 인덱스
    

    // 항목의 아이디. 거의 안씀
    override fun getItemId(p0: Int): Long = p0.toLong()

    
    // 가장 중요
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        
        // getView에서 사용하는 매개변수의 뜻
        // p0 : 항목의 번호 position 또는 index
        // p1 : 전에 만든 View가 있는 지 (View : xml을 눈에 보이는 형태로 바꾼 것)
        // p2 : 모든 view를 담고있는 ListView의 정보
        
        
        // phone_list.xml 을 눈에 보이는 View객체로 바꾸기 : inflate
        // inflater로 할 수 있당
        
        // ListView를 담은 어플에서 스크롤할 때 가끔 버벅인다
        // 항목을 만들 때 마다 inflate를 하면 이런일이 발생한다
        
        // clean code : 한 번만 inflate하고 그 뒤로는 전에 만든거를 재활용한다
        // recyclerView가 그런 일을 한다??? 왠지 아닌 듯 하다

        // 앨비스 연산자 (elvis) ?:
        val p1 = p1 ?: inflater.inflate(layout, p2, false) // attachToRoot 속성을 같게 만들거냐???? 뭔 소리냐 대체
        // p1이 null이면 이전에 xml코드를 View 객체로 만든 적이 없으면 p1에 inflate한 View 객체를 담자
        // p1으로 phone_list.xml에 있는 button, textView에 접근할 수 있다

        // View 객체에 데이터를 합치자

        // 이름
        val tvName = p1.findViewById<TextView>(R.id.tvName)
        tvName.text = data[p0].name

        // 전화번호, 이미지도 하자
        val tvTel = p1.findViewById<TextView>(R.id.tvTel)
        tvTel.text = data[p0].tel

        val imgView = p1.findViewById<ImageView>(R.id.imgView)
        imgView.setImageResource(data[p0].imgId)

        // PhoneAdapter의 getView()에서 phone_list에 접근한다
        // 1 버튼의 아이디 가져오기

        val btnCall = p1.findViewById<Button>(R.id.btnCall)

        // 2 버튼을 눌렀을 때
        // 3 묵시적 인텐트 (action, 전화번호 데이터)
        // ACTION_DIAL
        // 전화번호   "tel:010-0000-0000" 꼴로
        // 4 권한설정 <uses-permission .... CALL_PHONE>
        // 5 intent
        btnCall.setOnClickListener {

            var intt = Intent(
                Intent.ACTION_DIAL,
                Uri.parse("tel:${data[p0].tel}") // tvTel.text.toString()을 ${}대신 쓸 수 있다
            )

            // 기존 stack에는 activity만 쌓을 수 있다
            // adapter class를 쌓는 stack을 만들자
            intt.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            context.startActivity(intt)

        }

        // inflate한 View를 리턴해서 ListView에 쌓아라
        // p1 : 템플릿 + 데이터
        return p1
        
        
    }
}