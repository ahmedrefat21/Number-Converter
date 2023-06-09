package com.example.numberconverter

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat



class MainActivity : AppCompatActivity() {
    lateinit var editTextDecimal: EditText
    lateinit var editTextBinary: EditText
    lateinit var editTextOctal: EditText
    lateinit var editTextHexa: EditText
    lateinit var buttonClear: Button
    lateinit var value: String
    lateinit var onFocusChangeListener: OnFocusChangeListener
    var focusedViewId :Int=0
    lateinit var textWatcher: TextWatcher
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        setContentView(R.layout.activity_main)
        init()
        callBack()
    }



    private fun init(){
        editTextDecimal = findViewById(R.id.etvDecimal)
        editTextBinary = findViewById(R.id.etvBinary)
        editTextHexa = findViewById(R.id.etvHexa)
        editTextOctal = findViewById(R.id.etvOctal)
        buttonClear = findViewById(R.id.btnClear)
    }

    private fun callBack(){
        buttonClear.setOnClickListener { clearFields() }

        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence?, i: Int, i1: Int, i2: Int) {
                value = charSequence.toString().trim()
                if (value!!.length > 0) {
                    convertNumber()
                }
            }
            override fun afterTextChanged(editable: Editable) {}
        }

        onFocusChangeListener = OnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                focusedViewId = v.id
                (findViewById<View>(focusedViewId) as EditText).addTextChangedListener(textWatcher)
                val gradientDrawable = GradientDrawable(
                    GradientDrawable.Orientation.TR_BL,
                    intArrayOf(Color.parseColor("#374053"), Color.parseColor("#374053"))
                )
                gradientDrawable.shape = GradientDrawable.RECTANGLE
                if (focusedViewId == R.id.etvDecimal) {
                    gradientDrawable.setStroke(
                        8,
                        ContextCompat.getColor(applicationContext, android.R.color.background_dark)
                    )
                } else {
                    gradientDrawable.setStroke(
                        8,
                        ContextCompat.getColor(applicationContext, android.R.color.background_dark)
                    )
                }
                v.background = gradientDrawable
            } else {
                (findViewById<View>(focusedViewId) as EditText).removeTextChangedListener(
                    textWatcher
                )
                if (focusedViewId == R.id.etvDecimal) {
                    v.background =
                        ContextCompat.getDrawable(applicationContext, R.drawable.focusview_design)
                } else {
                    v.background =
                        ContextCompat.getDrawable(applicationContext, R.drawable.focusview_design)
                }
            }
        }
    }

    private fun clearFields() {
        editTextDecimal.text.clear()
        editTextBinary.text.clear()
        editTextHexa.text.clear()
        editTextOctal.text.clear()
    }

    private fun convertNumber() {
        try {
            var num: Long = 0
            when (focusedViewId) {
                R.id.etvDecimal -> {
                    num = value!!.toLong()
                    editTextBinary!!.setText(java.lang.Long.toBinaryString(num).toString())
                    editTextOctal!!.setText(java.lang.Long.toOctalString(num).toString())
                    editTextHexa!!.setText(java.lang.Long.toHexString(num).toString())
                }
                R.id.etvBinary -> {
                    num = value!!.toLong(2)
                    editTextDecimal!!.setText(num.toString())
                    editTextOctal!!.setText(java.lang.Long.toOctalString(num).toString())
                    editTextHexa!!.setText(java.lang.Long.toHexString(num).toString())
                }
                R.id.etvOctal -> {
                    num = value!!.toLong(8)
                    editTextDecimal!!.setText(num.toString())
                    editTextBinary!!.setText(java.lang.Long.toBinaryString(num).toString())
                    editTextHexa!!.setText(java.lang.Long.toHexString(num).toString())
                }
                R.id.etvHexa -> {
                    num = value!!.toLong(16)
                    editTextDecimal!!.setText(num.toString())
                    editTextBinary!!.setText(java.lang.Long.toBinaryString(num).toString())
                    editTextOctal!!.setText(java.lang.Long.toOctalString(num).toString())
                }
            }
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}