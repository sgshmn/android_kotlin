package com.example.apptrackapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.apptrackapplication.R

// companion object -> 여기서 쓰는 변수, 안쓰니까 주석
// private const val ARG_PARAM1 = "param1"
// private const val ARG_PARAM2 = "param2"


class HomeFragment : Fragment() {
    
    
    // 번들에서 쓰는 변수
//    private var param1: String? = null
//    private var param2: String? = null
//
    // Bundle? -> 가로모드
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Inflate한 layout을 저장
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        
        // fragment는 activity가 아니다
        // fragment에 접근하려면 xml코드를 view로 바꿔야한다
        // inflater가 그거 한다
        // view의 내용을 바꾸고 마지막에 리턴한다
        
        // id
        val wv = view.findViewById<WebView>(R.id.wv)
        
        // WebView에 웹페이지 띄우기
        // 1 주소
        val address = "https://gj-aischool.or.kr/"
        
        // 2 설정 변경
        // JavaScript를 사용할 수 있게 바꾸자
        // JavaScript 못 쓰는 상태가 default이다
        // WebView.settings는 웹뷰의 설정을 바꾸는 기능이 담겨있다
        // 리스트의 내용을 바꾸듯이 값을 대입하면 바뀐다
        val ws = wv.settings
        ws.javaScriptEnabled = true

        // 3 클라이언트 설정
        wv.webViewClient = WebViewClient()

        // 4 주소 적용
        wv.loadUrl(address)
        
        
        return view
    }

    // 세로 -> 가로모드 전환할 때 데이터 유지 방법
//    companion object {
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            HomeFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}