package fabPackage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.annoyingturtle.omnitop.R
import com.annoyingturtle.omnitop.dndSchedaActivity.GuidaSchedaDnDActivity
import dndData.entities.Scheda
import kotlinx.android.synthetic.main.activity_griglia_d_i_battaglia.*
import kotlinx.android.synthetic.main.activity_griglia_d_i_battaglia.addBtn1
import kotlinx.android.synthetic.main.activity_griglia_d_i_battaglia.cardBtn1
import kotlinx.android.synthetic.main.activity_griglia_d_i_battaglia.diceBtn1
import kotlinx.android.synthetic.main.activity_griglia_d_i_battaglia.gridBtn1
import kotlinx.android.synthetic.main.activity_griglia_d_i_battaglia.noteBtn1
import kotlinx.android.synthetic.main.activity_home.*

class GrigliaDIBattagliaActivity : AppCompatActivity() {

    var zoom = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_griglia_d_i_battaglia)

        /**Action Bar */

        setSupportActionBar(myToolbarGriglia)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        /********** FAB ***********/
        val fab =
            AbsFab(addBtn1, cardBtn1, gridBtn1, noteBtn1, diceBtn1, this, supportFragmentManager)
        fab.startListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        var inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_griglia, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.idPedine -> Toast.makeText(this, "Pulsante Pedine", Toast.LENGTH_SHORT).show()

            R.id.idImmagine -> Toast.makeText(this, "Pulsante Cambia Mappa", Toast.LENGTH_SHORT).show()

            else -> onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }
}