package com.annoyingturtle.omnitop.dndHomeActivity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import coil.ImageLoader
import coil.load
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.annoyingturtle.omnitop.R
import dndData.RuoloGiocatore
import dndData.entities.Campagna
import dndData.viewModel.CampagnaViewModel
import kotlinx.android.synthetic.main.activity_dnd_home_nuova_campagna.*
import kotlinx.coroutines.launch


class DndHomeNuovaCampagna : AppCompatActivity() {

    var copertinaCampagnaUri: Uri? = null
    private lateinit var mCampagnaViewModel: CampagnaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dnd_home_nuova_campagna)

        /** Action bar */
        setSupportActionBar(myToolbarNuovaCampagna)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)


        mCampagnaViewModel = ViewModelProvider(this).get(CampagnaViewModel::class.java)
        ButtonInsertDataAddCampagna.setOnClickListener {

            val titoloCampagna = titoloCam_et.text.toString()
            val ruoloCampagna = if (checkBoxDM.isChecked) RuoloGiocatore.DM
            else RuoloGiocatore.PG
            val descrizioneCampagna = descrizioneCam_et.text?.toString()

            if(!inputcheck(titoloCampagna)){
                lifecycleScope.launch{
                    val copertinaCampagna = getBitmapCampagna(copertinaCampagnaUri)
                    val newCampagna = Campagna(0, titoloCampagna, ruoloCampagna, descrizioneCampagna, copertinaCampagna )
                    insertCampagnaDataToDatabase(newCampagna)
                }
            }
            else{
                Toast.makeText(this, "Titolo Campagna non può essere vuoto!", Toast.LENGTH_SHORT).show()
            }


        }

        addImageBtn.setOnClickListener{
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            startActivityForResult(Intent.createChooser(intent, "Scegli Copertina Immagine"), 1)

        }

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

    private fun insertCampagnaDataToDatabase(newCampagna: Campagna) {
            mCampagnaViewModel.addCampagna(newCampagna)
            Toast.makeText(this, "Campagna aggiunta con successo!", Toast.LENGTH_SHORT).show()
            navigateUpTo(Intent(this, DndHome::class.java))
    }

    private fun inputcheck(titoloCampagna: String?): Boolean{

        return (TextUtils.isEmpty(titoloCampagna))

    }
}