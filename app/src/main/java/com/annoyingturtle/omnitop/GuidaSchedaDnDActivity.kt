package com.annoyingturtle.omnitop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_dnd_home.*
import kotlinx.android.synthetic.main.activity_guida_scheda_dn_d.*

class GuidaSchedaDnDActivity : AppCompatActivity() {

    var schermataAttuale = 0
    var extras: Bundle? = null
    var idScheda: Int =-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guida_scheda_dn_d)

        /** Action Bar */
        setSupportActionBar(myToolbarGuidaDnd)

        extras= intent.extras
        idScheda= extras!!.getInt("idScheda")

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)


        nextBtn.setOnClickListener(){
            next()
            if (schermataAttuale == 10) schermataAttuale = 0 else schermataAttuale++
        }



    }


    fun next(){
        when (schermataAttuale) {
            0 -> {capitoloTextView.text = getString(R.string.sceltaRazza); spiegazioneTextView.text = getString(R.string.infoRazza)}
            1 -> {capitoloTextView.text = getString(R.string.sceltaBG); spiegazioneTextView.text = getString(R.string.infoBG)}
            2 -> {capitoloTextView.text = getString(R.string.sceltaCaratteristiche); spiegazioneTextView.text = getString(R.string.infoCaratteristiche)}
            3 -> {capitoloTextView.text = getString(R.string.sceltaModificatori); spiegazioneTextView.text = getString(R.string.infoModificatori)}
            4 -> {capitoloTextView.text = getString(R.string.puntiIspirazione); spiegazioneTextView.text = getString(R.string.infoPuntiIspirazione)}
            5 -> {capitoloTextView.text = getString(R.string.profBonus); spiegazioneTextView.text = getString(R.string.infoProfBonus)}
            6 -> {capitoloTextView.text = getString(R.string.tiriSalvezza); spiegazioneTextView.text = getString(R.string.infoTiriSalvezza)}
            7 -> {capitoloTextView.text = getString(R.string.sceltaAbilità); spiegazioneTextView.text = getString(R.string.infoAbilità)}
            8 -> {capitoloTextView.text = getString(R.string.percezionePassiva); spiegazioneTextView.text = getString(R.string.infoPercezionePassiva)}
            9 -> {capitoloTextView.text = getString(R.string.sceltaAllineamento); spiegazioneTextView.text = getString(R.string.infoAllineamento)}

            10 -> {capitoloTextView.text = getString(R.string.sceltaClasse); spiegazioneTextView.text = getString(R.string.infoClasse)}
        }
    }

    override fun onSupportNavigateUp(): Boolean {

        return navigateUpTo(Intent(this, DndSchedaActivity::class.java).putExtra("idScheda", idScheda ))
    }
}