package absPackage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.Toast
import com.annoyingturtle.omnitop.R
import kotlinx.android.synthetic.main.activity_abs_fab.*
import kotlin.Lazy
//import kotlinx.android.synthetic.main.activity_main.*

class AbsFab : AppCompatActivity() {

    private val rotateOpen : Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim)}
    private val rotateClose : Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_close_anim)}
    private val fromBottom : Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim)}
    private val toBottom : Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim)}

    private var clicked = false

    /*private val addBtn = findViewById<Button>(R.id.addBtn) as Button
    private val diceBtn = findViewById<Button>(R.id.diceBtn) as Button
    private val cardBtn = findViewById<Button>(R.id.cardBtn) as Button
    private val gridBtn = findViewById<Button>(R.id.gridBtn) as Button
    private val noteBtn = findViewById<Button>(R.id.noteBtn) as Button

    private val addBtn: Button = findViewById<Button>(R.id.addBtn)
    private val diceBtn: Button = findViewById<Button>(R.id.diceBtn)
    private val cardBtn: Button = findViewById<Button>(R.id.cardBtn)
    private val noteBtn: Button = findViewById<Button>(R.id.noteBtn)
    private val gridBtn: Button = findViewById<Button>(R.id.gridBtn)*/



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_abs_fab)


        addBtn.setOnClickListener{
            onAddButtonClicked()
        }

        cardBtn.setOnClickListener{
            Toast.makeText(this,"Pulsante Carte", Toast.LENGTH_SHORT)
        }

        gridBtn.setOnClickListener{
            Toast.makeText(this,"Pulsante Griglia", Toast.LENGTH_SHORT)
        }

        noteBtn.setOnClickListener{
            Toast.makeText(this,"Pulsante Note", Toast.LENGTH_SHORT)
        }

        diceBtn.setOnClickListener{
            Toast.makeText(this,"Pulsante Dado", Toast.LENGTH_SHORT)
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
