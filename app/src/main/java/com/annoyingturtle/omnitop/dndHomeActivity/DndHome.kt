package com.annoyingturtle.omnitop.dndHomeActivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.annoyingturtle.omnitop.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_dnd_home.*
import kotlinx.android.synthetic.main.activity_home.addBtn1
import kotlinx.android.synthetic.main.activity_home.cardBtn1
import kotlinx.android.synthetic.main.activity_home.diceBtn1
import kotlinx.android.synthetic.main.activity_home.gridBtn1
import kotlinx.android.synthetic.main.activity_home.noteBtn1
import fabPackage.AbsFab
import fabPackage.GrigliaDIBattagliaActivity

class DndHome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dnd_home)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navController = findNavController(R.id.hostfragment)
        bottomNavigationView.setupWithNavController(navController)

        /** Action Bar */
        setSupportActionBar(myToolbarHomeDnd)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)


        addCampagnaIcon.setOnClickListener {
            findNavController(R.id.hostfragment).navigate(R.id.dndAddCampagna)

        }


        /********** FAB ***********/
        val fab = AbsFab(addBtn1, cardBtn1, gridBtn1, noteBtn1, diceBtn1, this, supportFragmentManager, Intent(this, GrigliaDIBattagliaActivity::class.java))
        fab.startListener(this)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.hostfragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }







}