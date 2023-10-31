package com.example.myapplication_test6_7_8_9_10_11_12.ch10_test

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.ProgressDialog.show

import android.app.TimePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Build.VERSION_CODES.N
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import androidx.core.content.ContextCompat
import com.example.myapplication_test6_7_8_9_10_11_12.R
import com.example.myapplication_test6_7_8_9_10_11_12.ch9_test.TestActivity_9
import com.example.myapplication_test6_7_8_9_10_11_12.databinding.ActivityTest10Binding
import kotlin.concurrent.thread

class TestActivity_10 : AppCompatActivity() {
    lateinit var activityTest10Binding: ActivityTest10Binding
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_test10)
        activityTest10Binding = ActivityTest10Binding.inflate(layoutInflater)
        setContentView(activityTest10Binding.root)

        // 허가 확인 여부 테스트
        val status = ContextCompat.checkSelfPermission(this@TestActivity_10, "android.permission.ACCESS_FINE_LOCATION")
        if (status == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this@TestActivity_10, "위치 권한 승인됨", Toast.LENGTH_SHORT).show()
            Log.d("KMK", "권한이 승인됨 : ${status}")
        } else {
            Toast.makeText(this@TestActivity_10, "위치 권한 승인 안됨", Toast.LENGTH_SHORT).show()
            Log.d("KMK", "권한이 승인 안됨 : ${status}")
        }

        // 후처리 : 인텐트에서 기본적인 사용법 단순 페이지 이동만 사용했음
        // 인텐트란? 시스템레 메세지를 전달하는 전달자
        // 예) 페이지 이동 같은 경우, 시스템에 요청해서 이동해씀
        // 예2) 특정앱에 접근을 해서, 데이터를 가져오는 작업(=후처리)

        // 설정1)
        val requestPermissionLauncher = registerForActivityResult(
            // 이부분이 시스템에서 정해준 함수들이 있음. 현재 허가를 확인하는 용도
            // 나중에 이미지 등 데이터에 접근해서 해당 데이터를 가지고 오는 용도로 사용할 예정
            ActivityResultContracts.RequestPermission()
        ) {
                isGranted ->
            if (isGranted){
                Log.d("KMK", "권한이 승인됨 : call back 후처리 요청")
            } else {
                Log.d("KMK", "권한이 승인 안됨 : call back 후처리 요청")
            }
        }

        // 이용 -> 호출 위에 설정
        requestPermissionLauncher.launch("android.permission.ACCESS_FINE_LOCATION")

        activityTest10Binding.testToastBtn?.setOnClickListener {
            // 기존 사용법
            // Toast.makeText(this@TestActivity_9, "후처리 확인중", Toast.LENGTH_LONG).show()
            // 콜백을 익명 클래스 추가해서 사라지거나 나타나거나 했을 경우 추가 로직 넣기
            val toast = Toast.makeText(this@TestActivity_10, "후처리 확인중", Toast.LENGTH_LONG)
            toast.addCallback(
                object : Toast.Callback(){
                    override fun onToastHidden() {
                        super.onToastHidden()
                        Log.d("KMK", "토스트 후처리 작업 : 사라질 경우")
                        val intent = Intent(this@TestActivity_10, TestActivity_10::class.java)
                        startActivity(intent)
                    }

                    override fun onToastShown() {
                        super.onToastShown()
                        Log.d("KMK", "토스트 후처리 작업 : 나타날 경우")
                    }
                }
            )
            toast.show()
        }

        // 날짜 다이얼 로그 출력해 보기
        activityTest10Binding.dateBtn?.setOnClickListener {
            DatePickerDialog(this@TestActivity_10, object : DatePickerDialog.OnDateSetListener{
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    Log.d("kmk", "년도 :  ${year}년, 월 : ${month+1}월, 일:${dayOfMonth}일")
                    Toast.makeText(this@TestActivity_10, "년도 :  ${year}년, 월 : ${month+1}월, 일:${dayOfMonth}일", Toast.LENGTH_SHORT).show()
                    activityTest10Binding.dateTextView?.text = "${year}년 ${month+1}월 ${dayOfMonth}일"
                }
            },2023,9,25).show()
        }

        // 시간 다이얼 로그 테스트
        activityTest10Binding.timeBtn?.setOnClickListener {
            TimePickerDialog(this@TestActivity_10, object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    Log.d("lsy","${hourOfDay}시, ${minute}분"  )
                    Toast.makeText(this@TestActivity_10,"${hourOfDay}시, ${minute}분"
                        , Toast.LENGTH_SHORT).show()
                    // 텍스트 뷰에 설정해보기.
                    activityTest10Binding.timeTextView?.text = "${hourOfDay}시, ${minute}분"
                }
            },14,21,true).show()
        }


        // 커스터마이징 한 다이얼로그 출력 : 기본값
        activityTest10Binding.customDialogBtn?.setOnClickListener {
            AlertDialog.Builder(this@TestActivity_10).run {
                setTitle("커스텀 다이얼로그")
                setIcon(android.R.drawable.ic_dialog_info)
                setMessage("테스트 할까요?")
                setPositiveButton("수락", null)
                setNegativeButton("취소", null)
                setNeutralButton("더보기", null)
                show()
            }
        }

        // 목록 요소 선택1
        val items = arrayOf<String>("사과", "바나나", "복숭아", "수박")
        activityTest10Binding.customDialogBtn2?.setOnClickListener {
            AlertDialog.Builder(this@TestActivity_10).run {
                setTitle("커스텀 다이얼로그2")
                setIcon(android.R.drawable.ic_dialog_info)
                //setMessage("테스트 할까요?")
                // 추가
                val objectListener = object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        Log.d("KMK", "선택한 과일 : ${items[which]}")
                    }
                }
                setItems(items, objectListener)

                setPositiveButton("수락", null)
                setNegativeButton("취소", null)
                setNeutralButton("더보기", null)
                show()
            }
        }

        // 목록 요소 선택2
        activityTest10Binding.customDialogBtn3?.setOnClickListener {
            AlertDialog.Builder(this@TestActivity_10).run {
                setTitle("커스텀 다이얼로그3")
                setIcon(android.R.drawable.ic_dialog_info)
                //setMessage("테스트 할까요?")
                // 추가
//                val objectListener = object : DialogInterface.OnClickListener {
//                    override fun onClick(dialog: DialogInterface?, which: Int) {
//                        Log.d("KMK", "선택한 과일 : ${items[which]}")
//                    }
//                }
                // 체크박스용 클릭 리스너 ,
                val objectListener = object : DialogInterface.OnMultiChoiceClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int, isChecked: Boolean) {
                        Log.d("lsy","${items[which]}이 ${if(isChecked) "선택됨"  else "선택해제됨"}")
                    }
                }
                // 목록요소1
                //setItems(items, objectListener)

                // 목록요소2 , 체크박스
                setMultiChoiceItems(items, booleanArrayOf(true,true,false,false),objectListener)


                setPositiveButton("수락", null)
                setNegativeButton("취소", null)
                setNeutralButton("더보기", null)
                show()
            }
        }

        // 목록 요소 선택3
        activityTest10Binding.customDialogBtn4?.setOnClickListener {
            AlertDialog.Builder(this@TestActivity_10).run {
                setTitle("커스텀 다이얼로그4")
                setIcon(android.R.drawable.ic_dialog_info)
                //setMessage("테스트 할까요?")
                // 추가
//                val objectListener = object : DialogInterface.OnClickListener {
//                    override fun onClick(dialog: DialogInterface?, which: Int) {
//                        Log.d("KMK", "선택한 과일 : ${items[which]}")
//                    }
//                }
                // 체크박스용 클릭 리스너 ,
//                val objectListener = object : DialogInterface.OnMultiChoiceClickListener {
//                    override fun onClick(dialog: DialogInterface?, which: Int, isChecked: Boolean) {
//                        Log.d("lsy","${items[which]}이 ${if(isChecked) "선택됨"  else "선택해제됨"}")
//                    }
//                }

                // 라디오용 클릭 리스너
                val objectListener = object :DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                      Log.d("KMK", "선택한 과일 : ${items[which]}")
                   }
                 }


                // 목록요소1
                //setItems(items, objectListener)

                // 목록요소2 , 체크박스
                //setMultiChoiceItems(items, booleanArrayOf(true,true,false,false),objectListener)

                // 목록요소3 , 라디오
                setSingleChoiceItems(items, 1, objectListener)


                setPositiveButton("수락", null)
                setNegativeButton("취소", null)
                setNeutralButton("더보기", null)

                //
                setCancelable(false)
                show()
                // 다이얼 로그창이 나타났을때 창 밖을 클릭 시 기본 알림창 닫기가 기본인데,
                // false면 안 닫힘
            }.setCanceledOnTouchOutside(false)
        }


        // 소리확인 테스트
        activityTest10Binding.soundBtn?.setOnClickListener {
            val notification:Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val rigntone = RingtoneManager.getRingtone(applicationContext, notification)
            rigntone.play()
        }


        // 버튼 요소를 선택해서 알림을 보내는 로직 추가
        activityTest10Binding.notificationBtn.setOnClickListener {
            // 알림추가 설정
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val builder : NotificationCompat.Builder

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                // 채널설정
                val channelId = "one-channel"
                val channelName = "My Channel One"
                val channel = NotificationChannel(
                    channelId,
                    channelName,
                    // 전달하려는 메시지 전달 강도(레벨)
                    NotificationManager.IMPORTANCE_HIGH
                )
                //채널의 정보 및 부가 옵션 설정
                channel.description = "My Channel One Description"
                // 알림 갯수 아이콘 표
                channel.setShowBadge(true)
                val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                Log.d("KMK", "uri 위치 : ${uri}" )
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()
                channel.setSound(uri, audioAttributes)
                channel.enableLights(true)
                channel.lightColor = Color.RED
                channel.enableVibration(true)
                // 진동
                channel.vibrationPattern = longArrayOf(100, 200, 100, 200)

                //NotificationManager 에 채널 등록
                manager.createNotificationChannel(channel)

                builder = NotificationCompat.Builder(this@TestActivity_10, channelId)
                } else {
                    // 26 이하 버전
                    builder = NotificationCompat.Builder(this@TestActivity_10)
                }

                builder.setSmallIcon(android.R.drawable.ic_notification_overlay)
                builder.setWhen(System.currentTimeMillis())
                builder.setContentTitle("알림 제목")
                builder.setContentText("알림 메시지")

            // 알림 메시지 창 클릭 시 페이지 이동,
            val intent = Intent(this@TestActivity_10, TestActivity_9::class.java)
            val pendingIntent = PendingIntent.getActivity(this@TestActivity_10, 10, intent, PendingIntent.FLAG_IMMUTABLE)
            //builder.setContentIntent(pendingIntent)

            // 특정 액션 추가 기능
            val actionIntent = Intent(this@TestActivity_10, OneReceiver::class.java)
            val actionPendingIntent = PendingIntent.getBroadcast(this@TestActivity_10, 20, actionIntent, PendingIntent.FLAG_IMMUTABLE)
            builder.addAction(
                NotificationCompat.Action.Builder(
                    android.R.drawable.stat_notify_more, "Action", actionPendingIntent
                ).build()
            )

            // 답장 추가 액션
            val KEY_TEXT_REPLY = "key_text_reply"
            var replyLabel : String = "답장"
            var remoteInput : RemoteInput = RemoteInput.Builder(KEY_TEXT_REPLY).run {
                setLabel(replyLabel)
                build()
            }

            val replyIntent = Intent(this@TestActivity_10, ReplyReceiver::class.java)
            val replyPendingIntent = PendingIntent.getBroadcast(this@TestActivity_10,30, replyIntent,PendingIntent.FLAG_MUTABLE)
            // 답장 액션 추가하기.
            builder.addAction(
                NotificationCompat.Action.Builder(
                    android.R.drawable.stat_notify_more, "답장",
                    replyPendingIntent
                ).addRemoteInput(remoteInput).build()
            )


            // progress 진행바 확인
//            builder.setProgress(100, 0, false)
//                thread {
//                    for(i in 1..100){
//                        builder.setProgress(100, i, false)
//                        manager.notify(11, builder.build())
//                        SystemClock.sleep(100)
//                    }
//                }

            // 큰 이미지를 첨부해서 알림 보내기
            val bigPicture = BitmapFactory.decodeResource(resources,R.drawable.programming4)
            val bigStyle = NotificationCompat.BigPictureStyle()
            bigStyle.bigPicture(bigPicture)
            builder.setStyle(bigStyle)


            // 긴 텍스트
            val bigTextStyle = NotificationCompat.BigTextStyle()
            bigTextStyle.bigText(resources.getString(R.string.long_text))
            builder.setStyle(bigTextStyle)

            // 박스 스타일
            val style = NotificationCompat.InboxStyle()
            style.addLine("1코스 - 1")
            style.addLine("2코스 - 2")
            style.addLine("3코스 - 3")
            style.addLine("4코스 - 4")
            builder.setStyle(style)


            // 알림 발생 시키기
            manager.notify(11, builder.build())










            }





    }
}