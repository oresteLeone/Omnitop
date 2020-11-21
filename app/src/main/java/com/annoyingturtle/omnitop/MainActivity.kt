package com.annoyingturtle.omnitop

import absPackage.AbsFab
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({
            val welcome = Intent(this@MainActivity, AbsFab::class.java)
            startActivity(welcome)
            overridePendingTransition(R.anim.fade_in_anim, R.anim.fade_out_anim)
            finish()
        }, 2000)
    }
}
