package com.annoyingturtle.omnitop.dndCampagnaHomeActivity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import coil.ImageLoader
import coil.load
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.annoyingturtle.omnitop.R
import dndData.LvlCompetenza
import dndData.TipoScheda
import dndData.entities.Scheda
import dndData.utilData.Dettagli
import dndData.utilData.Incantatore
import dndData.utilData.Money
import dndData.utilData.Statistiche
import dndData.viewModel.SchedaViewModel
import kotlinx.android.synthetic.main.activity_dnd_campagna_nuova_scheda.*
import kotlinx.android.synthetic.main.activity_dnd_home_nuova_campagna.*
import kotlinx.coroutines.launch
import java.lang.Exception

class DndCampagnaNuovaScheda : AppCompatActivity() {

    var idCampagna: Int = -1
    lateinit var mSchedaViewModel: SchedaViewModel
    var extras: Bundle? = null
    var imgSchedaUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dnd_campagna_nuova_scheda)

        /** Action bar */
        setSupportActionBar(myToolbarNuovaScheda)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        extras = intent.extras
        idCampagna = extras!!.getInt("idCampagna")

        mSchedaViewModel = ViewModelProvider(this).get(SchedaViewModel::class.java)
        ButtonInsertDataAddScheda.setOnClickListener(){
            val nomePersonaggio = NomePersonaggio_et.text.toString()
            val tipoScheda: TipoScheda = if(checkBoxPNG.isChecked) TipoScheda.PNG
            else
                TipoScheda.PG
            var imgScheda: Bitmap? = null

            if(!inputCheck(nomePersonaggio)){

                lifecycleScope.launch {
                    /**Valore per le statistiche della scheda*/
                    val statistiche = Statistiche(
                        0, 0, 0, 0, 0.0, 2, 0, 0,
                        0, 0, 0, 0, 0, false, false, false, false, false, false,
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0
                    )

                    /**Valore per Incantatore */
                    var incantatore = Incantatore(
                        "", "", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0
                    )

                    /** Valore per dettagli*/

                    val dettagli = Dettagli("", "", "", if(tipoScheda== TipoScheda.PNG) TipoScheda.PNG.toString() else "", "", 0, 0, "")

                    /** Valore per le monete della scheda*/
                    val moneteTotali = Money(0, 0, 0, 0, 0)

                    if(imgSchedaUri!=null) imgScheda = getBitmapScheda(imgSchedaUri)
                    else imgScheda = null

                    val newScheda = Scheda(0, idCampagna, nomePersonaggio, tipoScheda, statistiche, incantatore, dettagli, moneteTotali, imgScheda)
                    insertSchedaDataToDatabase(newScheda)
                }
            }
            else
                Toast.makeText(this, "Riempi i campi necessari!", Toast.LENGTH_SHORT).show()

        }

        addImageBtnNuovaScheda.setOnClickListener{
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            startActivityForResult(Intent.createChooser(intent, "Scegli Immagine Scheda"), 1)

        }
    }

    private fun insertSchedaDataToDatabase(scheda: Scheda) {

            mSchedaViewModel.addScheda(scheda)
            Toast.makeText(this, "La scheda è stata salvata con successo", Toast.LENGTH_SHORT).show()
            navigateUpTo(Intent(this, DndCampagnaHome::class.java).putExtra("idCampagna", idCampagna ))

    }

    private fun inputCheck(nomePersonaggio: String?): Boolean {

        return TextUtils.isEmpty(nomePersonaggio)
    }

    override fun onSupportNavigateUp(): Boolean {

       return navigateUpTo(Intent(this, DndCampagnaHome::class.java).putExtra("idCampagna", idCampagna ))
    }


    private suspend fun getBitmapScheda(imgSchedaUri: Uri?): Bitmap {

        val loading: ImageLoader = ImageLoader(this)
        val request: ImageRequest = ImageRequest.Builder(this)
            .data(imgSchedaUri)
            .build()

        val result: Drawable = (loading.execute(request) as SuccessResult).drawable
        return (result as BitmapDrawable).bitmap
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var selectedImageUri : Uri? = null
        if (resultCode== RESULT_OK && requestCode == 1){

            selectedImageUri = data?.data
            imageNuovaScheda.load(selectedImageUri)
            imgSchedaUri = selectedImageUri

        }
    }

}