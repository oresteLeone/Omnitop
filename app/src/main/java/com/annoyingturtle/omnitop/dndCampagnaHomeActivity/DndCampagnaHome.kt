package com.annoyingturtle.omnitop.dndCampagnaHomeActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import coil.load
import com.annoyingturtle.omnitop.R
import com.google.android.material.tabs.TabLayout
import dndData.viewModel.CampagnaViewModel
import kotlinx.android.synthetic.main.activity_dnd_campagna_home.*
import kotlinx.android.synthetic.main.activity_home.addBtn1
import kotlinx.android.synthetic.main.activity_home.cardBtn1
import kotlinx.android.synthetic.main.activity_home.diceBtn1
import kotlinx.android.synthetic.main.activity_home.gridBtn1
import kotlinx.android.synthetic.main.activity_home.noteBtn1
import fabPackage.AbsFab
import kotlinx.android.synthetic.main.activity_dnd_campagna_home.myToolbar
import kotlinx.android.synthetic.main.activity_dnd_campagna_home.tabNav
import kotlinx.android.synthetic.main.activity_dnd_campagna_home_alter.*

class DndCampagnaHome : AppCompatActivity() {

    lateinit var mCampagnaViewModel: CampagnaViewModel
    var idCampagna: Int = -1
    lateinit var mytablayout: TabLayout
    lateinit var extras: Bundle
    var isExtendedToolbar: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dnd_campagna_home)

        /**Action Bar */
        setSupportActionBar(myToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        extras = intent.extras!!
        idCampagna = extras!!.getInt("idCampagna")

        mCampagnaViewModel = ViewModelProvider(this).get(CampagnaViewModel::class.java)
        mCampagnaViewModel.getCampagnaFromID(idCampagna)
        mCampagnaViewModel.getSingleLiveData().observe(this, Observer {
            supportActionBar?.title= it.titoloCampagna
            /*if(it.copertinaBitmap!= null){
                imageCampaignTopBar.load(it.copertinaBitmap)
            }*/
        })
        /*showBasicCampagnaData()*/




        /** Navigare fra le schede */
        val navController = findNavController(R.id.campagnafragmenthost)
        mytablayout = tabNav

        mytablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.text.toString() == "SCHEDE")
                    navController.navigate(R.id.dndCampagnaSchedeFragment)
                else if (tab?.text.toString() == "NOTE")
                    navController.navigate(R.id.dndCamapagnaNoteFragment)
                else
                    navController.navigate(R.id.dndCampagnaCompendiumFragment)
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })


        /********** FAB ***********/
        val fab = AbsFab(addBtn1, cardBtn1, gridBtn1, noteBtn1, diceBtn1, this, supportFragmentManager)
        fab.startListener(this)
    }

    private fun showBasicCampagnaData() {

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        var inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_risorse_campagna, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nuovaSchedaOpt -> startActivity(Intent(this, DndCampagnaNuovaScheda::class.java).putExtras(extras))

            R.id.nuovaNotaOpt -> startActivity(Intent(this, DndCampagnaHomeNuovaNota::class.java).putExtras(extras))

            R.id.nuovoElementoLibOpt -> Toast.makeText(this, "nuovoele", Toast.LENGTH_SHORT).show()

            R.id.infoCampagna -> startActivity(Intent(this, DndCampagnaHomeInfoDati::class.java).putExtras(extras))

            else -> onBackPressed()

        }

            return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.campagnafragmenthost)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }



}