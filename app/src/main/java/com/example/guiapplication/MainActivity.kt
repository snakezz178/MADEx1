package com.example.guiapplication

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var textSizeButton: Button
    private lateinit var textColorButton: Button
    private lateinit var BgColorButton: Button

    private var originalTextSize = 0f
    private var originalTextColor = 0
    private lateinit var mainLayout: ViewGroup
    private var currentTextSize = 0f
    private var clickCounter = 0
    private var originalBgColor = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        fun showToast(message: String) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

        textView = findViewById(R.id.textView)
        textSizeButton = findViewById(R.id.textsize)
        textColorButton = findViewById(R.id.textcolor)
        mainLayout = findViewById(R.id.main)
        val background = mainLayout.background as? ColorDrawable
        originalBgColor = background?.color ?: Color.WHITE




        originalTextSize = textView.textSize
        originalTextColor = textView.currentTextColor
        currentTextSize = originalTextSize
        BgColorButton = findViewById(R.id.background)


        // Change Text Size
        textSizeButton.setOnClickListener {
            clickCounter += 10

            if (clickCounter > 30) {
                currentTextSize = originalTextSize
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, currentTextSize)
                clickCounter = 0
            } else {
                currentTextSize *= 2
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, currentTextSize)
            }
            showToast("Text Size changed successfully")
        }

        // Change Text Color
        textColorButton.setOnClickListener {
            clickCounter += 10

            if (clickCounter > 30) {
                textView.setTextColor(originalTextColor)
                clickCounter = 0
            } else {
                // Set to any color you like (or cycle through a list)
                if (clickCounter == 10) {
                    textView.setTextColor(Color.RED)
                } else if (clickCounter == 20) {
                    textView.setTextColor(Color.BLUE)
                } else {
                    textView.setTextColor(Color.GREEN) /// Orange
                }
                showToast("Text color changed successfully")
            }
        }
        //Backgroundcolor
        //Backgroundcolor
        BgColorButton.setOnClickListener {
            clickCounter += 10

            if (clickCounter > 30) {
                findViewById<ViewGroup>(R.id.main).setBackgroundColor(Color.WHITE) // or originalBgColor if stored
                clickCounter = 0
            } else {
                val layout = findViewById<ViewGroup>(R.id.main)
                when (clickCounter) {
                    10 -> layout.setBackgroundColor(Color.YELLOW)
                    20 -> layout.setBackgroundColor(Color.MAGENTA)
                    else -> layout.setBackgroundColor(Color.CYAN)
                }
                showToast("Background color changed successfully")
            }
        }
    }

    private fun showToast(s: String) {

    }
}