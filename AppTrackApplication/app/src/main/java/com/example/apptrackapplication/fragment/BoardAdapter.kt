package com.example.apptrackapplication.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.fragment.app.FragmentActivity

class BoardAdapter(context : FragmentActivity?, layout : Int, data : MutableList<BoardVO>) : BaseAdapter() {

    // 필드
    val context = context
    val layout = layout
    val data = data

    // context의 힘을 빌려 inflater 찾아오기
    // getSystemService
    val inflater : LayoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    // 리시트 뷰 항목 개수
    override fun getCount() = data.size

    // position 인덱스 데이터
    override fun getItem(position: Int) = data[position]

    // position 인덱스 데이터의 아이디
    override fun getItemId(position: Int) = position.toLong()

    // 템플릿 + 데이터
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        // 위에서 찾아온 inflater를 사용해서 inflate
        // 전에 만든 view가 없으면 인플레이터로 가져와야한다
        var convertView = convertView ?: inflater.inflate(layout, parent, false)

        


        return convertView

    }
}