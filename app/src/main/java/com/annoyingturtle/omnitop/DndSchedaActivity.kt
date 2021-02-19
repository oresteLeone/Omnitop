package com.annoyingturtle.omnitop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_dnd_campagna_home.*
import kotlinx.android.synthetic.main.activity_home.addBtn1
import kotlinx.android.synthetic.main.activity_home.cardBtn1
import kotlinx.android.synthetic.main.activity_home.diceBtn1
import kotlinx.android.synthetic.main.activity_home.gridBtn1
import kotlinx.android.synthetic.main.activity_home.noteBtn1
import fabPackage.AbsFab

class DndSchedaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dnd_scheda)


        /**Action Bar */

        setSupportActionBar(myToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        /** Schermate */

        val navController = findNavController(R.id.schedafragment)

        bottomNavigationView.setupWithNavController(navController)
        /********** FAB ***********/
        val fab = AbsFab(addBtn1, cardBtn1, gridBtn1, noteBtn1, diceBtn1, this, supportFragmentManager)
        fab.startListener(this)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        var inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.scheda_dnd_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        startActivity(Intent(this, GuidaSchedaDnDActivity::class.java))

        return super.onOptionsItemSelected(item)
    }
}