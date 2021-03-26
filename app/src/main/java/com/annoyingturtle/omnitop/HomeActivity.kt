package com.annoyingturtle.omnitop

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.annoyingturtle.omnitop.dndHomeActivity.DndHome
import kotlinx.android.synthetic.main.activity_home.*
import fabPackage.AbsFab
import fabPackage.AdapterRecyclerListaGiochi
import fabPackage.GrigliaDIBattagliaActivity
import fabPackage.ItemListaGiochi
import kotlinx.android.synthetic.main.activity_home.addBtn1
import kotlinx.android.synthetic.main.activity_home.cardBtn1
import kotlinx.android.synthetic.main.activity_home.diceBtn1
import kotlinx.android.synthetic.main.activity_home.gridBtn1
import kotlinx.android.synthetic.main.activity_home.noteBtn1


class HomeActivity : AppCompatActivity(), AdapterRecyclerListaGiochi.OnItemClickListnerInteface {

    lateinit var toggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setSupportActionBar(myToolbarHome)

        /************ Menu a scorrimento *********/
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.openDrawer, R.string.closeDrawer)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        /************ Hamburger *******/

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        /******* Nome Sull'actionBar *******/

        supportActionBar?.title = "OmniTop"

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.idImpostazioni -> Toast.makeText(applicationContext, "Pulsante impostazioni", Toast.LENGTH_SHORT).show()
                R.id.idChiSiamo -> Toast.makeText(applicationContext, "Pulsante Chi Siamo", Toast.LENGTH_SHORT).show()
                R.id.idGuida -> Toast.makeText(applicationContext, "Pulsante guida", Toast.LENGTH_SHORT).show()
                R.id.idInformazioniLegali -> Toast.makeText(applicationContext, "Pulsante Informazioni legali", Toast.LENGTH_SHORT).show()
            }
            true
        }


        /********** FAB ***********/
        val fab = AbsFab(addBtn1, cardBtn1, gridBtn1, noteBtn1, diceBtn1, this, supportFragmentManager, Intent(this, GrigliaDIBattagliaActivity::class.java))
        fab.startListener(this)


        /******Lista di giochi *****/
        val lista = generaLista()


        idListaGiochi.adapter = AdapterRecyclerListaGiochi(lista, this)
        idListaGiochi.layoutManager = LinearLayoutManager(this)
        idListaGiochi.setHasFixedSize(true)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {       //Funzione per il menu a scorrimento
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

/*

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        var inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.actionbar_menu, menu)



        return true
    }
*/



    /****** Metodo per generare la lista di oggetti da mostrare a schermo *****/

    private fun generaLista(): List<ItemListaGiochi> {

        return listOf(
                ItemListaGiochi(
                        imageResources = R.drawable.bannerdnd),
                ItemListaGiochi(
                        imageResources = R.drawable.banner_pathfinder
                )
        )

}

    /***** Metodo per gestire la transizione fra due attività***/
    override fun onItemClick(position: Int) {
        if(position == 0)
        {
            //val welcome = Intent(this@MainActivity, HomeActivity::class.java)
            startActivity(Intent(this, DndHome::class.java) )
        }
        else
        Toast.makeText(this, "Hai premuto l'oggetto ${position}", Toast.LENGTH_SHORT).show()
    }

}