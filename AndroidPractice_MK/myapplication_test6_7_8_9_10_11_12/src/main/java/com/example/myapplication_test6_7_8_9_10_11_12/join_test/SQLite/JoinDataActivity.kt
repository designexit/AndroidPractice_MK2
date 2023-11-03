package com.example.myapplication_test6_7_8_9_10_11_12.join_test.SQLite

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.FileProvider
import com.example.myapplication_test6_7_8_9_10_11_12.R
import com.example.myapplication_test6_7_8_9_10_11_12.join_test.SQLite.DatabaseHelper
import com.example.myapplication_test6_7_8_9_10_11_12.databinding.ActivityJoinDataBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

class JoinDataActivity : AppCompatActivity() {
    lateinit var binding : ActivityJoinDataBinding
    lateinit var filePath : String
    // 전역으로 선언만 했지, 할당을 안했음.
    // 그래서, onCreate 라는 함수에서 , 최초 1회 실행시.
    // 할당을 하는 구조.
    var myDB: DatabaseHelper? = null

    var editTextName: EditText? = null
    var editTextPW: EditText? = null
    var editTextEmail: EditText? = null
    var editTextID: EditText? = null
    var buttonInsert: Button? = null
    var buttonView: Button? = null
    var buttonUpdate: Button? = null
    var buttonDelete: Button? = null
    var resultUserImage: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
// 전역에 선언된 변수들을 할당하는 구조.
        binding = ActivityJoinDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setContentView(R.layout.activity_main)
        //DatabaseHelper 클래스 를 사용한다.
        // 객체 생성한다. ->
        myDB = DatabaseHelper(this)

        // 자바 버전에 코드 -> 코틀린 변경.
        // findViewById ->  바인딩 기법으로 사용했음.
        editTextName = binding.editTextName
        editTextPW = binding.editTextPW
        editTextEmail = binding.editTextEmail
        editTextID = binding.editTextID
        buttonInsert = binding.buttonInsert
        buttonView = binding.buttonView
        buttonUpdate = binding.buttonUpdate
        //buttonDelete = findViewById(R.id.buttonDelete)
        buttonDelete = binding.buttonDelete
        resultUserImage = binding.resultUserImage


        // 최초 1회 실행시, 직접 만든 함수를 호출하는 부분.
        AddData()
        viewAll()
        UpdateData()
        DeleteData()


        //profile image
        val requestGalleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            try {
                val calRatio = calculateInSampleSize(
                    it.data!!.data!!,
                    resources.getDimensionPixelSize(R.dimen.profile_img_width),
                    resources.getDimensionPixelSize(R.dimen.profile_img_height),
                )
                val options = BitmapFactory.Options()
                options.inSampleSize = calRatio

                var inputStream = contentResolver.openInputStream(it.data!!.data!!)

                val bitmap = BitmapFactory.decodeStream(inputStream,null,options)
                inputStream!!.close()
                inputStream = null

                binding.resultUserImage.setImageBitmap(bitmap)

                Log.d("lsy","갤러리에서 선택된 사진의 크기 비율 calRatio : $calRatio")

            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("lsy", "사진 출력 실패")

            }

        }

        // 버튼클릭시 : 갤러리 앱 호출,
        binding.galleryBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            requestGalleryLauncher.launch(intent)
        }

        val requestCameraFileLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            val calRatio = calculateInSampleSize(
                Uri.fromFile(File(filePath)),
                resources.getDimensionPixelSize(R.dimen.profile_img_width),
                resources.getDimensionPixelSize(R.dimen.profile_img_height),
            )
            val options = BitmapFactory.Options()
            options.inSampleSize = calRatio
            val bitmap = BitmapFactory.decodeFile(filePath,options)
            binding.resultUserImage.setImageBitmap(bitmap)

        }

        binding.cameraBtn.setOnClickListener {
            val timeStamp : String =
                SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)

            val file = File.createTempFile(
                "JPEG_${timeStamp}_",
                ".jpg",
                storageDir
            )

            filePath = file.absolutePath
            Log.d("kmk","file.absolutePath : $filePath")

            val photoURI : Uri = FileProvider.getUriForFile(
                this,
                "com.example.test1234",
                file
            )
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            requestCameraFileLauncher.launch(intent)
        }

    }

    //데이터베이스 추가하기
    fun AddData() {
        buttonInsert!!.setOnClickListener {
            val isInserted = myDB!!.insertData(
                editTextName!!.text.toString(),
                editTextPW!!.text.toString(),
                editTextEmail!!.text.toString()

            )
            if (isInserted == true)
                Toast.makeText(this@JoinDataActivity, "데이터추가 성공", Toast.LENGTH_LONG)
                    .show()
            else Toast.makeText(this@JoinDataActivity, "데이터추가 실패", Toast.LENGTH_LONG).show()
        }
    }

    // 데이터베이스 읽어오기
    fun viewAll() {
        buttonView!!.setOnClickListener(View.OnClickListener {
            val res = myDB!!.allData
            if (res.count == 0) {
                ShowMessage("실패", "데이터를 찾을 수 없습니다.")
                return@OnClickListener
            }

            val buffer = StringBuffer()
            while (res.moveToNext()) {
                buffer.append(
                    """
    ID: ${res.getString(0)}
    
    """.trimIndent()
                )
                buffer.append(
                    """
    이름: ${res.getString(1)}
    
    """.trimIndent()
                )
                buffer.append(
                    """
    비밀번호: ${res.getString(2)}
    
    """.trimIndent()
                )
                buffer.append(
                    """
    이메일: ${res.getString(3)}
    
    
    """.trimIndent()
                )
            }
            ShowMessage("데이터", buffer.toString())
        })
    }

    //데이터베이스 수정하기
    fun UpdateData() {
        buttonUpdate!!.setOnClickListener {
            val isUpdated = myDB!!.updateData(
                editTextID!!.text.toString(),
                editTextName!!.text.toString(),
                editTextPW!!.text.toString(),
                editTextEmail!!.text.toString(),
                filepath
            )
            if (isUpdated == true)
                Toast.makeText(this@JoinDataActivity, "데이터 수정 성공", Toast.LENGTH_LONG)
                    .show()
            else Toast.makeText(this@JoinDataActivity, "데이터 수정 실패", Toast.LENGTH_LONG)
                .show()
        }
    }

    // 데이터베이스 삭제하기
    fun DeleteData() {
        buttonDelete!!.setOnClickListener {
            val deleteRows = myDB!!.deleteData(editTextID!!.text.toString())
            if (deleteRows > 0)
                Toast.makeText(this@JoinDataActivity, "데이터 삭제 성공", Toast.LENGTH_LONG)
                    .show()
            else Toast.makeText(this@JoinDataActivity, "데이터 삭제 실패", Toast.LENGTH_LONG)
                .show()
        }
    }

    fun ShowMessage(title: String?, Message: String?) {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(Message)
        builder.show()
    }


    private fun calculateInSampleSize (fileUri : Uri, reqWidth: Int, reqHeight: Int) :Int {
        // 매개변수 1: 사진의 위치 , 2: 요구하는 가로크기 , 3: 요구하는 세로크기
        // 함수의 반환값 : 적절한 비율의 단위
        // 비율은 크기가 클수록, 사이즈를 더 작게 만들어줌. 마치, 썸네일 이미지와 비슷함.
        // 이 함수의 내용은 크게 몰라도 됨.
        // 적당히, 원본사진의 비율을 줄여 나가면서, 적당한 크기의 상수를 구하는 로직.
        // 로직을 이해하면, 이 함수를 통째로 사용할 것임.
        //
        // 안드로이드에서 이미지의 타입을 비트맵으로 정하는데, 거기에 옵션을 넣는 인스턴스.
        val options = BitmapFactory.Options()
        // 사진의 영향을 안주고, 옵션만 제공을 하겠다.
        options.inJustDecodeBounds = true

        // 계산을 하기 위해서, 원본 사진을 읽는 과정이 있어서, 여기에서도 , IO 발생
        // 그래서, try ~ catch 구문이 필요함,.
        try {
            // fileUri : 사진이 들어있는 위치, 파일을 바이트로 읽었음.
            // inputStream : 사진의 읽은 값이 바이트 단위로 들어 있음.
            var inputStream = contentResolver.openInputStream(fileUri)

            // 변환 작업.
            BitmapFactory.decodeStream(inputStream,null,options)
            inputStream!!.close()
            inputStream = null
        } catch (e:Exception) {
            e.printStackTrace()
            Log.d("lsy", "사진 크기 비율 계산 실패 ")
        }
        // 비율 계산
        val (height : Int, width: Int) = options.run { outHeight to outWidth }
        // 비율 1, 원본 사이즈 크기
        var inSampleSize = 1
        // 원본 사진의 크기 height x width 예를 3000 x 2000 px
        // reqHeight : 150 , reqWidth : 150
        if (height > reqHeight || width > reqWidth) {

            // 반으로 접기
            val halfHeight : Int = height / 2
            val halfWidth : Int = width / 2

            while ( halfHeight / inSampleSize >= reqHeight &&
                halfWidth / inSampleSize >= reqWidth ) {
                // 비율 2배씩 증가, 값이 증가하면, 사이즈의 크기는 줄어듬.
                inSampleSize *= 2
            }
        }

        return  inSampleSize

    }

}