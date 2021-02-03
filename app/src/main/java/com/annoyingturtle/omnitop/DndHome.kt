package com.annoyingturtle.omnitop

import utilPackage.AbsFab
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import kotlinx.android.synthetic.main.activity_home.*

class DndHome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dnd_home)


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