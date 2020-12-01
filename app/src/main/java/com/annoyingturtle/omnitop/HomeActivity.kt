package com.annoyingturtle.omnitop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import absPackage.AbsFab
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        /************ Menu a scorrimento *********/
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.openDrawer, R.string.closeDrawer)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        /************ questo funziona solo con l'action bar standard android *******/

        //supportActionBar?.setDisplayHomeAsUpEnabled(true)

        menubtn.setOnClickListener{ /*************** Finire di implementare la funzione ***********/

        }

        navView.setNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.idImpostazioni -> Toast.makeText(applicationContext, "Pulsante impostazioni", Toast.LENGTH_SHORT).show()
                R.id.idChiSiamo-> Toast.makeText(applicationContext, "Pulsante Chi Siamo", Toast.LENGTH_SHORT).show()
                R.id.idGuida -> Toast.makeText(applicationContext, "Pulsante guida", Toast.LENGTH_SHORT).show()
                R.id.idInformazioniLegali -> Toast.makeText(applicationContext, "Pulsante Informazioni legali", Toast.LENGTH_SHORT).show()
            }
            true
        }




        /********** FAB ***********/
        val fab = AbsFab (addBtn1, cardBtn1, gridBtn1, noteBtn1, diceBtn1, this)
        fab.startListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {       //Funzione per il menu a scorrimento
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}