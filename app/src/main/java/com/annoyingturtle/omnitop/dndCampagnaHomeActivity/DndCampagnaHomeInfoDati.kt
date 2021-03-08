package com.annoyingturtle.omnitop.dndCampagnaHomeActivity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore.Images.Media.getBitmap
import android.text.Editable
import android.text.TextUtils
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import coil.ImageLoader
import coil.load
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.annoyingturtle.omnitop.dndHomeActivity.DndHome
import com.annoyingturtle.omnitop.R
import dndData.RuoloGiocatore
import dndData.entities.Campagna
import dndData.viewModel.CampagnaViewModel
import kotlinx.android.synthetic.main.activity_dnd_campagna_home_info_dati.*
import kotlinx.android.synthetic.main.activity_dnd_campagna_home_info_dati.addImageBtn
import kotlinx.android.synthetic.main.activity_dnd_campagna_home_info_dati.checkBoxDM
import kotlinx.android.synthetic.main.activity_dnd_campagna_home_info_dati.imageCampaign
import kotlinx.android.synthetic.main.activity_dnd_home_nuova_campagna.*
import kotlinx.coroutines.launch
import java.lang.Exception

class DndCampagnaHomeInfoDati : AppCompatActivity() {

    private var copertinaCampagnaUri: Uri? = null
    var idCampagna: Int = -1
    lateinit var mCampagnaViewModel: CampagnaViewModel
    lateinit var extras: Bundle
    lateinit var campagnaToDelete: Campagna


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dnd_campagna_home_info_dati)

        /**Action Bar */
        setSupportActionBar(myToolbarCampagnaDati)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        extras = intent.extras!!
        idCampagna = extras!!.getInt("idCampagna")

        mCampagnaViewModel = ViewModelProvider(this).get(CampagnaViewModel::class.java)
        mCampagnaViewModel.getCampagnaFromID(idCampagna)
        showCampagnaData()
        mCampagnaViewModel.getSingleLiveData().observe(this, Observer {
            campagnaToDelete = Campagna(idCampagna,it.titoloCampagna,it.ruoloCampagna,it.descrizione,it.copertinaBitmap)
        })

        ButtonUpdateDataCampagna.setOnClickListener(){
            UpdateCampagnaDataToDatabase()
        }

        addImageBtn.setOnClickListener{
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            startActivityForResult(Intent.createChooser(intent, "Scegli Copertina Campagna"), 1)

        }
    }

    private fun UpdateCampagnaDataToDatabase() {
        val titoloCampagna = NomeCampagna_et.text.toString()
        val descrizioneCampagna = DescrizioneCampagna_et.text.toString()
        val ruoloCampagna : RuoloGiocatore = if(checkBoxDM.isChecked)   RuoloGiocatore.DM
                                            else
                                                RuoloGiocatore.PG

        if (!inputCheck(titoloCampagna)){

            mCampagnaViewModel.getSingleLiveData().observe(this, Observer{
                 lifecycleScope.launch{
                     val campagnaUpdate = Campagna(idCampagna,titoloCampagna,ruoloCampagna,descrizioneCampagna,
                         if (copertinaCampagnaUri == null) it.copertinaBitmap
                         else getBitmapCampagna(copertinaCampagnaUri))

                     mCampagnaViewModel.updateCampagna(campagnaUpdate)
                 }
            })
            Toast.makeText(this, "La Campagna è stata aggiornata con successo", Toast.LENGTH_SHORT).show()
            navigateUpTo(Intent(this, DndCampagnaHome::class.java).putExtra("idCampagna", idCampagna ))
        }
        else{
            Toast.makeText(this, "Nome Campagna vuoto!", Toast.LENGTH_SHORT).show()
        }

    }

    private fun inputCheck(titoloCampagna: String?): Boolean {
        return (TextUtils.isEmpty(titoloCampagna))
    }

    private fun showCampagnaData() {
        mCampagnaViewModel.getSingleLiveData().observe(this, Observer {
            NomeCampagna_et.text = Editable.Factory.getInstance().newEditable(it.titoloCampagna)
            DescrizioneCampagna_et.text = Editable.Factory.getInstance().newEditable(it.descrizione)
            checkBoxDM.isChecked = it.ruoloCampagna == RuoloGiocatore.DM
            imageCampaign.load(it.copertinaBitmap)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        var inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_con_pulsante_cancella, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.cancellaNotaBtn -> deleteCampagna(campagnaToDelete)

            else -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteCampagna(campagnaToDelete: Campagna) {
        val builder = AlertDialog.Builder(this)
        builder.setPositiveButton("Si"){ _, _ ->
            mCampagnaViewModel.getSingleLiveData().removeObservers(this)
            mCampagnaViewModel.deleteCampagna(campagnaToDelete)
            Toast.makeText(this, "Campagna eliminata con successo!", Toast.LENGTH_SHORT).show()
            navigateUpTo(Intent(this, DndHome::class.java))
        }
        builder.setNegativeButton("No"){ _, _ ->

        }

        builder.setTitle("Eliminazione Campagna ${campagnaToDelete.titoloCampagna}")
        builder.setMessage("Sei sicuro di voler cancellare la campagna ${campagnaToDelete.titoloCampagna}?")
        builder.create().show()

    }

    override fun onSupportNavigateUp(): Boolean {

        return navigateUpTo(Intent(this, DndCampagnaHome::class.java).putExtra("idCampagna", idCampagna ))
    }

    private suspend fun getBitmapCampagna(copertinaCampagnaUri: Uri?): Bitmap {

        val loading: ImageLoader = ImageLoader(this)
        val request: ImageRequest = ImageRequest.Builder(this)
            .data(copertinaCampagnaUri)
            .build()

        val result: Drawable = (loading.execute(request) as SuccessResult).drawable
        return (result as BitmapDrawable).bitmap
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var selectedImageUri : Uri? = null
        if (resultCode== RESULT_OK && requestCode == 1){

            selectedImageUri = data?.data
            imageCampaign.load(selectedImageUri)
            copertinaCampagnaUri = selectedImageUri
            /*try {
                var inputStream: InputStream? = data?.data?.let {
                    contentResolver.openInputStream(
                        it
                    )
                }

                copertinaCampagna= BitmapFactory.decodeStream(inputStream)
                imageCampaign.setImageBitmap(copertinaCampagna)

            }catch (e: FileNotFoundException){
                e.printStackTrace()
            }*/

        }
    }
}