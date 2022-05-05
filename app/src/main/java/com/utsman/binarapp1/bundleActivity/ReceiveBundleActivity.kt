package com.utsman.binarapp1.bundleActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.utsman.binarapp1.MainActivity
import com.utsman.binarapp1.R

class ReceiveBundleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receive_bundle)

        val bundle = intent.extras
        val name = bundle?.getString(MainActivity.INTENT_KEY)
        val textView: TextView = findViewById(R.id.tv_receivebundle_hellowrold)
        textView.text = "greeting my world\n" + name
    }
}