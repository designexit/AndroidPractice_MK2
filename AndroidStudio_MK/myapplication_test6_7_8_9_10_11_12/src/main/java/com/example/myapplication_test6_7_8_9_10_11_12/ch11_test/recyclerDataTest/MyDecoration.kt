package com.example.myapplication_test6_7_8_9_10_11_12.ch11_test.recyclerDataTest

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication_test6_7_8_9_10_11_12.R

// 리사이클러 뷰에 구분선 넣기, 이미지 목록 요소 기준으로 전 후 넣기
class MyDecoration(val context: Context): RecyclerView.ItemDecoration() {

    // 목록 요소가 출력되기 전에 이미지를 먼저 출력함 (배경으로 사용)
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        c.drawBitmap( // 안드로이드 이미지 타입을 비트맵 또틑 드로우어블 타입으로 지정
            // 배경으로 사용할 이미지 하나
            BitmapFactory.decodeResource(context.getResources(), R.drawable.noro1),
            0f,
            0f,
            null
        );
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        //뷰 사이즈 계산
        val width = parent.width
        val height = parent.height
        //이미지 사이즈 계산
        val dr: Drawable? =
            // 포그라운드로 사용할 이미지 하나
            ResourcesCompat.getDrawable(context.getResources(), R.drawable.text_noro, null)
        val drWidth = dr?.intrinsicWidth
        val drHeight = dr?.intrinsicHeight
        //이미지가 그려질 위치 계산
        val left = width / 2 - drWidth?.div(2) as Int
        val top = height / 2 - drHeight?.div(2) as Int
        c.drawBitmap(
            BitmapFactory.decodeResource(context.getResources(), R.drawable.text_noro),
            left.toFloat(),
            top.toFloat(),
            null
        )

    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val index = parent.getChildAdapterPosition(view) + 1

        if (index % 3 == 0) //left, top, right, bottom
            outRect.set(10, 10, 10, 60)
        else
            outRect.set(10, 10, 10, 0)

        view.setBackgroundColor(Color.WHITE)
        ViewCompat.setElevation(view, 20.0f)

    }
}