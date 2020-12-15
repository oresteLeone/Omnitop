package com.annoyingturtle.omnitop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import absPackage.AbsFab
import absPackage.AdapterRecyclerListaGiochi
import absPackage.ItemListaGiochi
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager
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





        /******Lista di giochi *****/
        val lista  = generaLista(2)


        idListaGiochi.adapter = AdapterRecyclerListaGiochi(lista)
        idListaGiochi.layoutManager = LinearLayoutManager(this)     //Errore su questa linea, il layout utilizzato è un drawerlayout, ma non trovo la funzione giusta da usare
        idListaGiochi.setHasFixedSize(true)
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

    /****** Metodo per generare la lista di oggetti da mostrare a schermo *****/

    private fun generaLista(size: Int): List<ItemListaGiochi>{
        val list = ArrayList<ItemListaGiochi>()

        for (i in 0 until size){
            val drawable = when (i % 2){
                0 -> R.drawable.bannerdnd
                else -> R.drawable.banner_pathfinder

            }

            val item = ItemListaGiochi(drawable)
            list += item
        }

        return list
    }

}