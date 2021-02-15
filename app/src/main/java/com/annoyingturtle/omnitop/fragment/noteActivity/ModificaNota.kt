package com.annoyingturtle.omnitop.fragment.noteActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.annoyingturtle.omnitop.R
import kotlinx.android.synthetic.main.activity_modifica_nota.*
import kotlinx.android.synthetic.main.activity_nuova_nota.*

class ModificaNota : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modifica_nota)

        setSupportActionBar(myToolbarModificaNota)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

    }
}