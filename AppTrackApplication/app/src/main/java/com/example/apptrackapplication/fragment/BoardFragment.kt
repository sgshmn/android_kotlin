package com.example.apptrackapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.apptrackapplication.R
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class BoardFragment : Fragment() {

    lateinit var adapter: BoardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_board, container, false)
        val lv = view.findViewById<ListView>(R.id.lv)

        // 1 ListView의 위치 설정
        // 2 ListView 한 칸에 들어갈 디자인 (템플릿)
        // 3 Data
        val list = mutableListOf<BoardVO>()

        val db = Firebase.database
        val myRef = db.getReference("board")


        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BoardFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BoardFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}