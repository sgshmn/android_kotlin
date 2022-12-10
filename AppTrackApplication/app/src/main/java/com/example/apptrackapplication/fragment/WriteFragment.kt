package com.example.apptrackapplication.fragment

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.apptrackapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class WriteFragment : Fragment() {

    lateinit var auth : FirebaseAuth
    lateinit var btnPlus : ImageView

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        
        // title, content, uid, time 을 firebase에 저장하자
        
        var view = inflater.inflate(R.layout.fragment_write, container, false)
        
        val etTitle = view.findViewById<EditText>(R.id.etTitle)
        val etContent = view.findViewById<EditText>(R.id.etContent)
        val btnWrite = view.findViewById<ImageView>(R.id.btnWrite)
        btnPlus = view.findViewById<ImageView>(R.id.btnPlus)
        
        auth = Firebase.auth
        
        val db = Firebase.database
        val myRef = db.getReference("board")
        
        // btnWrite를 눌렀을 때
        // 사용자 uid값을 토스트로 띄우자
        // auth.currentUser.uid -> auth 초기화 한 뒤에 하자
        btnWrite.setOnClickListener{
            
            // uid
            val uid = auth.currentUser?.uid.toString()
            Log.d("UID", uid)
            
            
            val title = etTitle.text.toString()
            val content = etContent.text.toString()

            // Firebase database 객체
            // getReference(key값)
            // 화면 생성할 때 설정 되어있어야 함 ????? 왜

            // time : 현재 시간을 가져올 수 있는 기능 : Calender
            // Instance ---> 클래스를 객체로 변화시킨 것
            // Calender 객체는 new 키워드로 생성 불가
            // getInstance() 메소드로 객체 생성

            // val time = "2022-10-12 11:34:18"

            // 쌤꺼
//            val currentTime = Calendar.getInstance().time
//            val time = SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA).format(currentTime)

            // 1) pattern 시간을 나타낼 형식
            // 2) 어느 위치의 시간을 가져올건지 설정

            val before = LocalDateTime.now(ZoneId.of("Asia/Seoul"))
//            val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Lacale.KOREA).format(currentTime)
            // 1) pattern: 시간을 나타낼 형식
            // 2) 어느 위치의 시간을 가져올건지 설정

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            val time = before.format(formatter)

            // 만든 key로 value 설정하기
            // board 키에 데이터 누적 : push()

            // uid값을 미리 받아오자
            // 올라가는 게시글 == 이미지의 이름 일치

            val key = myRef.push().key.toString()

            myRef.child(key).setValue(BoardVO(title, content, uid, time))

            // 이미지 업로드
            imgUpload(key)


            Toast.makeText(context, "글쓰기 완료", Toast.LENGTH_SHORT).show()
            
        }

        btnPlus.setOnClickListener{

            // Intent 를 사용해서 앨범으로 이동
            // 이동해서 데이터 가져오는 양뱡향 작업
            // 단방향 :  startActivity(intent)
            // 양방향(받아오는 데이터가 있다) : startActivityForResult
            // (intent, requestCode)
            // requestCode의 역할 : 내가 요청한 결과값이 맞는지 확인 용도

            val intt = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(intt, 1234)
        }
        
        
        return view
    }

//    OnActivityResult : startActivityForResult 를 통해서 받아온 결과값을 처리해주는 메서드(override)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // requestCode : 1234
        // resultCode : data를 보내주는 곳에서 보내는 신호
        // RESULT_OK
        // data -> img

        // requestCode가 1234가 맞는지?
        // resultCode가 RESULT_OK가 맞는지?
        if (requestCode == 1234 && resultCode == Activity.RESULT_OK){
            // btnPlus 를 받아온 이미지로 바꾸자~
            btnPlus.setImageURI(data?.data)

        }

    }

    private fun imgUpload(key: String) {

        // FireBaseStorage 사용은 imgUpload메서드 안에서만 사용함
        val storage = Firebase.storage

        // Create a storage reference from our app
        val storageRef = storage.reference  //저장한 경로

        // Create a reference to "mountains.jpg"
        val mountainsRef = storageRef.child("$key.png")
        // 해당 경로에 이미지 이름 설정

        // Get the data from an ImageView as bytes
        btnPlus.isDrawingCacheEnabled = true
        btnPlus.buildDrawingCache()
        val bitmap = (btnPlus.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        var uploadTask = mountainsRef.putBytes(data)
        uploadTask.addOnFailureListener {
            // Handle unsuccessful uploads
        }.addOnSuccessListener { taskSnapshot ->
            // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
            // ...
        }
    }
}