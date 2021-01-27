package absPackage

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.annoyingturtle.omnitop.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.estrazione_carte_layout.*

class EstrazioneCarteFragment() : BottomSheetDialogFragment() {

    var mazzoScelto = 0     //0 = carte napoletane, 1 = carte francesi, 2 = tarocchi
    var carteNapoletane : IntArray = IntArray(40)
    var carteFrancesi : IntArray = IntArray(54)
    var tarocchi : IntArray = IntArray(22)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.estrazione_carte_layout, container, false)
    }
    override fun onStart() {
        super.onStart()
        popolaListner()
    }

    /**** Funzione per attivare i listner sui pulsanti ****/

    fun popolaListner(){

        btn_change.setOnClickListener(){
            if (mazzoScelto == 0){
                mazzoScelto++
                nomeMazzo.text = getString(R.string.CarteFrancesi)
            }
            else if(mazzoScelto == 1)
            {
                mazzoScelto++
                nomeMazzo.text = getString(R.string.CarteTarocchi)

            }
            else
            {
                mazzoScelto = 0
                nomeMazzo.text = getString(R.string.CarteNapoletane)

            }
        }

        btn_estrai.setOnClickListener(){
            if(mazzoScelto == 0) carteNapoletane = pescaCarta(carteNapoletane, carteNapoletane.size)
            else if(mazzoScelto == 1) carteFrancesi = pescaCarta(carteFrancesi, carteFrancesi.size)
            else tarocchi = pescaCarta(tarocchi, tarocchi.size)
        }

    }

    /**** Funzioni per estrarre le carte dai mazzi ****/

    fun pescaCarta(mazzo: IntArray, n : Int) : IntArray {
        var carta = (1..n).random()

        mazzo[carta]=1

        mostraCarta(mazzo.size, carta)
        return  mazzo
    }

    fun mostraCarta(tMazzo : Int, carta: Int){

    }


}