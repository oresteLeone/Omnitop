package com.annoyingturtle.omnitop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*
import utilPackage.AbsFab

class DndCampagnaHome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dnd_campagna_home)

        /**Action Bar */

        //actionBar.setBackgroundDrawable() quando aggiungeremo le immagini

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        /** Schermate */

        val navController = findNavController(R.id.dnd_campagna_navigation)

        bottomNavigationView.setupWithNavController(navController)
        /********** FAB ***********/
        val fab = AbsFab(addBtn1, cardBtn1, gridBtn1, noteBtn1, diceBtn1, this, supportFragmentManager)
        fab.startListener(this)
    }
    /***** Pulsante Ricerca *****/
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        var inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.actionbar_menu, menu)

        return true
    }
}