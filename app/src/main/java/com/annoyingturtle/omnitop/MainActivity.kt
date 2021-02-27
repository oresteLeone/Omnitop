package com.annoyingturtle.omnitop

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.annoyingturtle.omnitop.fragment.noteActivity.ModificaNota

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        Handler().postDelayed({
            val welcome = Intent(this@MainActivity, HomeActivity::class.java)
            startActivity(welcome)
            //overridePendingTransition(R.anim.fade_in_anim, R.anim.fade_out_anim)
            finish()
        }, 2000)
    }
}
