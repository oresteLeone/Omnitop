package com.annoyingturtle.omnitop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import absPackage.AbsFab
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.SearchView
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


        /************ Hamburger *******/

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        /******* Nome Sull'actionBar *******/

        supportActionBar?.title = "OmniTop"

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        var  inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.actionbar_menu, menu)

        /*menu?.findItem(R.id.idRicerca)?.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {



                var searchView  = menu?.findItem(R.id.idRicerca)
                val searchItem = menu.findItem(R.id.idRicerca)
                //searchView = searchItem.setActionView(this : View!) as SearchView
                searchView.setQueryHint("Search View Hint")                         /************ Capire come si mettono gli Hint******/


                return true
            }*/

        //fun MenuItem.OnActionExpandListener(menu: MenuItem){

       // }

        return true
    }

}