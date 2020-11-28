package absPackage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.annoyingturtle.omnitop.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_abs_fab.*

open class AbsFab(addBtn1: FloatingActionButton, cardBtn1: FloatingActionButton, gridBtn1: FloatingActionButton, noteBtn1: FloatingActionButton, diceBtn1: FloatingActionButton) : AppCompatActivity() {
    //class AbsFab constructor(addBtn1 : FloatingActionButton, cardBtn1 : FloatingActionButton, gridBtn1 : FloatingActionButton, noteBtn1 : FloatingActionButton, diceBtn1 : FloatingActionButton){

    var addBtn: FloatingActionButton = addBtn1
    var cardBtn: FloatingActionButton = cardBtn1
    var gridBtn: FloatingActionButton = gridBtn1
    var noteBtn: FloatingActionButton = noteBtn1
    var diceBtn: FloatingActionButton = diceBtn1


    /*init {
            this.addBtn = addBtn1
            this.cardBtn = cardBtn1
            this.gridBtn = gridBtn1
            this.noteBtn = noteBtn1
            this.diceBtn = diceBtn1
        }*/
    //   }

    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim) }
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_close_anim) }
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim) }
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim) }

    private var clicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
    //setContentView(R.layout.activity_abs_fab)



        addBtn.setOnClickListener {
            onAddButtonClicked()
        }

        cardBtn.setOnClickListener {
            Toast.makeText(this, "Pulsante Carte", Toast.LENGTH_SHORT).show()
        }

        gridBtn.setOnClickListener {
            Toast.makeText(this, "Pulsante Griglia", Toast.LENGTH_SHORT).show()
        }

        noteBtn.setOnClickListener {
            Toast.makeText(this, "Pulsante Note", Toast.LENGTH_SHORT).show()
        }

        diceBtn.setOnClickListener {
            Toast.makeText(this, "Pulsante Dado", Toast.LENGTH_SHORT).show()
        }

    }


    private fun onAddButtonClicked() {
        setVisibility(clicked)
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
