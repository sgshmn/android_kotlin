package com.example.sampleapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class BoardAdapter(context: Context , layout: Int, data: MutableList<BoardVO>) : BaseAdapter() {

    // 1 BaseAdapter 상속받아서 Adapter기능 가져오자
    // 2 매개변수 설정 (context, layout, data)
    // 3 unimplement method 구현 오버라이딩

    val context = context
    val layout = layout
    val data = data

    val inflater : LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    override fun getCount() = data.size

    override fun getItem(p0: Int) = data[0]

    override fun getItemId(p0: Int) = p0.toLong()

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        val p1 = p1 ?: inflater.inflate(layout, p2, false)
        var textView = p1.findViewById<TextView>(R.id.textView)
        textView.text = data[p0].text

        return p1


    }

}
