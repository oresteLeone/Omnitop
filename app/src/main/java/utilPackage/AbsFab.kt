package utilPackage

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.annoyingturtle.omnitop.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

open class AbsFab(addBtn1: FloatingActionButton, cardBtn1: FloatingActionButton, gridBtn1: FloatingActionButton, noteBtn1: FloatingActionButton, diceBtn1: FloatingActionButton, contesto : Context, fragmentManager: FragmentManager) {
    //class AbsFab constructor(addBtn1 : FloatingActionButton, cardBtn1 : FloatingActionButton, gridBtn1 : FloatingActionButton, noteBtn1 : FloatingActionButton, diceBtn1 : FloatingActionButton){

    /****** Variabili pulsanti FAB*****/
    var addBtn: FloatingActionButton = addBtn1
    var cardBtn: FloatingActionButton = cardBtn1
    var gridBtn: FloatingActionButton = gridBtn1
    var noteBtn: FloatingActionButton = noteBtn1
    var diceBtn: FloatingActionButton = diceBtn1


    /****** Variabili per la bottomSheet *******/
    var fragment : FragmentManager = fragmentManager
    val dadiBottomsheet = LancioDadiFragment()
    val noteBottomsheet = NoteFabFragment()
    val estrazioneCarteBottomsheet = EstrazioneCarteFragment()



    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(contesto, R.anim.rotate_open_anim) }
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(contesto, R.anim.rotate_close_anim) }
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(contesto, R.anim.from_bottom_anim) }
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(contesto, R.anim.to_bottom_anim) }

    private var clicked = false

    fun startListener (contesto: Context){



        addBtn.setOnClickListener {
            onAddButtonClicked()
        }

        cardBtn.setOnClickListener {
            //Toast.makeText(contesto, "Pulsante Carte", Toast.LENGTH_SHORT).show()
            onAddButtonClicked()
            estrazioneCarteBottomsheet.show(fragment, "Estrazione Carte")

        }

        gridBtn.setOnClickListener {
            Toast.makeText(contesto, "Pulsante Griglia", Toast.LENGTH_SHORT).show()
            onAddButtonClicked()

        }

        noteBtn.setOnClickListener {
            //Toast.makeText(contesto, "Pulsante Note", Toast.LENGTH_SHORT).show()
            onAddButtonClicked()
            noteBottomsheet.show(fragment, "Note")


        }

        diceBtn.setOnClickListener {
            //Toast.makeText(contesto, "Pulsante Dado", Toast.LENGTH_SHORT).show()
            onAddButtonClicked()
            dadiBottomsheet.show(fragment, "LancioDadi")


        }

    }


    private fun onAddButtonClicked() {
        setVisibility(clicked)
        if (!clicked){
        addBtn.setImageResource(R.drawable.add_icon)
        }
        else{
            addBtn.setImageResource(R.drawable.ic_more)
        }
        setAnimation(clicked)
        setClickable(clicked)
        clicked = !clicked
    }

    private fun setAnimation(clicked : Boolean) {
        if(!clicked)
        {
            addBtn.startAnimation(rotateOpen)
            diceBtn.startAnimation(fromBottom)
            cardBtn.startAnimation(fromBottom)
            gridBtn.startAnimation(fromBottom)
            noteBtn.startAnimation(fromBottom)
        }
        else
        {
            addBtn.startAnimation(rotateClose)
            diceBtn.startAnimation(toBottom)
            cardBtn.startAnimation(toBottom)
            gridBtn.startAnimation(toBottom)
            noteBtn.startAnimation(toBottom)
        }
    }

    private fun setVisibility(clicked : Boolean) {
        if(!clicked){
            diceBtn.visibility = View.VISIBLE
            cardBtn.visibility = View.VISIBLE
            gridBtn.visibility = View.VISIBLE
            noteBtn.visibility = View.VISIBLE
        }
        else
        {
            diceBtn.visibility = View.INVISIBLE
            cardBtn.visibility = View.INVISIBLE
            gridBtn.visibility = View.INVISIBLE
            noteBtn.visibility = View.INVISIBLE
        }

    }

    private fun setClickable(clicked: Boolean){
        if(!clicked)
        {
            diceBtn.isClickable=false
            cardBtn.isClickable=false
            gridBtn.isClickable=false
            diceBtn.isClickable=false
        }
        else
        {
            diceBtn.isClickable=true
            cardBtn.isClickable=true
            gridBtn.isClickable=true
            diceBtn.isClickable=true
        }
    }
}
