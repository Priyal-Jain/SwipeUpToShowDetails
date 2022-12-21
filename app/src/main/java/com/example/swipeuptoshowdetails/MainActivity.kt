package com.example.swipeuptoshowdetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback


class MainActivity : AppCompatActivity() {

    private var mBottomSheetBehavior: BottomSheetBehavior<View>? = null
    private var mTextViewState: TextView? = null
    private var img: ImageView? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomSheet: View = findViewById(R.id.bottom_sheet)

        mBottomSheetBehavior = BottomSheetBehavior.from<View>(bottomSheet)

        mTextViewState = findViewById(R.id.text_view_state)
        img = findViewById(R.id.img1)

        img!!.setOnTouchListener { view, motionEvent ->
            bottomSheet.visibility = View.VISIBLE
            mBottomSheetBehavior!!.setState(BottomSheetBehavior.STATE_EXPANDED)
            return@setOnTouchListener true
        }

        val buttonExpand: Button = findViewById(R.id.button_expand)
        val buttonCollapse: Button = findViewById(R.id.button_collapse)




        buttonExpand.setOnClickListener { mBottomSheetBehavior!!.setState(BottomSheetBehavior.STATE_EXPANDED) }

        buttonCollapse.setOnClickListener { mBottomSheetBehavior!!.setState(BottomSheetBehavior.STATE_COLLAPSED) }

        mBottomSheetBehavior!!.setBottomSheetCallback(object : BottomSheetCallback() {
            @SuppressLint("SetTextI18n")
            override fun onStateChanged(bottomSheet: View, newState: Int) {

                when (newState) {
                    BottomSheetBehavior.STATE_COLLAPSED -> mTextViewState!!.text = "Collapsed"
                    BottomSheetBehavior.STATE_DRAGGING -> mTextViewState!!.text = "Dragging..."
                    BottomSheetBehavior.STATE_EXPANDED -> mTextViewState!!.text = "Expanded"
                    BottomSheetBehavior.STATE_HIDDEN -> mTextViewState!!.text = "Hidden"
                    BottomSheetBehavior.STATE_SETTLING -> mTextViewState!!.text = "Settling..."
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                        mTextViewState!!.text = "STATE_HALF_EXPANDED..."
                    }
                }
            }

            @SuppressLint("SetTextI18n")
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                mTextViewState!!.text = "Sliding..."
            }
        })

    }
}