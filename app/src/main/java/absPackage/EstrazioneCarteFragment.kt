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
            when (mazzoScelto) {
                0 -> {
                    mazzoScelto++
                    nomeMazzo.text = getString(R.string.CarteFrancesi)
                }
                1 -> {
                    mazzoScelto++
                    nomeMazzo.text = getString(R.string.CarteTarocchi)

                }
                else -> {
                    mazzoScelto = 0
                    nomeMazzo.text = getString(R.string.CarteNapoletane)

                }
            }
        }

        btn_estrai.setOnClickListener(){

           when (mazzoScelto) {
                0 -> carteNapoletane = pesca(carteNapoletane)
                1 -> carteFrancesi = pesca(carteFrancesi)
                else -> tarocchi = pesca(tarocchi)
            }
        }

        btn_mischia.setOnClickListener(){
            when (mazzoScelto) {
                0 -> carteNapoletane = mischia(carteNapoletane)
                1 -> carteFrancesi = mischia(carteFrancesi)
                else -> tarocchi = mischia(tarocchi)
            }
        }

    }

    /**** Funzioni per estrarre le carte dai mazzi ****/

    private fun pesca(mazzo: IntArray) : IntArray {
        var trovato = false
        var carta = 0
        var count = 0

        while (!trovato) {
            carta = (1..mazzo.size).random()
            if(mazzo[carta] == 0)
                trovato = true
            else
            {
                count = 0
                for (i in 0..mazzo.size)
                {
                    if (mazzo[i] == 1)
                        count++
                }
                if (count == mazzo.size) {  // il mazzo viene mischiato in questo modo in quanto non è possibile riassegnare il valore mazzo e quindi invocare la funzione mischia
                    for (i in 0..mazzo.size)
                        mazzo[0] = 0

                    Toast.makeText(activity, "Il mazzo è stato mischiato", Toast.LENGTH_SHORT).show()
                }
            }
        }

        mazzo[carta]=1

        mostraCarta(mazzo.size, carta)
        return  mazzo
    }

    /**** Funzione per mischiare il mazzo ****/

    private fun mischia(mazzo : IntArray): IntArray {
        for(i in 0..mazzo.size)
            mazzo[0] = 0

        Toast.makeText(activity, "Il mazzo è stato mischiato", Toast.LENGTH_SHORT).show()
        return mazzo
    }

    /**** Funzione per mostrare a schermo la carta estratta ****/

    fun mostraCarta(tMazzo : Int, carta: Int){
        when (tMazzo) {
            40 -> {
                when (carta) {
                    0 -> cartaEstratta.text = getString(R.string.AssoBastoni)
                    1 -> cartaEstratta.text = getString(R.string.DueBastoni)
                    2 -> cartaEstratta.text = getString(R.string.TreBastoni)
                    3 -> cartaEstratta.text = getString(R.string.QuattroBastoni)
                    4 -> cartaEstratta.text = getString(R.string.CinqueBastoni)
                    5 -> cartaEstratta.text = getString(R.string.SeiBastoni)
                    6 -> cartaEstratta.text = getString(R.string.SetteBastoni)
                    7 -> cartaEstratta.text = getString(R.string.OttoBastoni)
                    8 -> cartaEstratta.text = getString(R.string.NoveBastoni)
                    9 -> cartaEstratta.text = getString(R.string.DieciBastoni)

                    10 -> cartaEstratta.text = getString(R.string.AssoSpade)
                    11 -> cartaEstratta.text = getString(R.string.DueSpade)
                    12 -> cartaEstratta.text = getString(R.string.TreSpade)
                    13 -> cartaEstratta.text = getString(R.string.QuattroSpade)
                    14 -> cartaEstratta.text = getString(R.string.CinqueSpade)
                    15 -> cartaEstratta.text = getString(R.string.SeiSpade)
                    16 -> cartaEstratta.text = getString(R.string.SetteSpade)
                    17 -> cartaEstratta.text = getString(R.string.OttoSpade)
                    18 -> cartaEstratta.text = getString(R.string.NoveSpade)
                    19 -> cartaEstratta.text = getString(R.string.DieciSpade)

                    20 -> cartaEstratta.text = getString(R.string.AssoCoppe)
                    21 -> cartaEstratta.text = getString(R.string.DueCoppe)
                    22 -> cartaEstratta.text = getString(R.string.TreCoppe)
                    23 -> cartaEstratta.text = getString(R.string.QuattroCoppe)
                    24 -> cartaEstratta.text = getString(R.string.CinqueCoppe)
                    25 -> cartaEstratta.text = getString(R.string.SeiCoppe)
                    26 -> cartaEstratta.text = getString(R.string.SetteCoppe)
                    27 -> cartaEstratta.text = getString(R.string.OttoCoppe)
                    28 -> cartaEstratta.text = getString(R.string.NoveCoppe)
                    29 -> cartaEstratta.text = getString(R.string.DieciCoppe)

                    30 -> cartaEstratta.text = getString(R.string.AssoDenari)
                    31 -> cartaEstratta.text = getString(R.string.DueDenari)
                    32 -> cartaEstratta.text = getString(R.string.TreDenari)
                    33 -> cartaEstratta.text = getString(R.string.QuattroDenari)
                    34 -> cartaEstratta.text = getString(R.string.CinqueDenari)
                    35 -> cartaEstratta.text = getString(R.string.SeiDenari)
                    36 -> cartaEstratta.text = getString(R.string.SetteDenari)
                    37 -> cartaEstratta.text = getString(R.string.OttoDenari)
                    38 -> cartaEstratta.text = getString(R.string.NoveDenari)
                    39 -> cartaEstratta.text = getString(R.string.DieciDenari)
                }
            }
            54 -> {
                when (carta) {
                    0 -> cartaEstratta.text = getString(R.string.AssoPicche)
                    1 -> cartaEstratta.text = getString(R.string.DuePicche)
                    2 -> cartaEstratta.text = getString(R.string.TrePicche)
                    3 -> cartaEstratta.text = getString(R.string.QuattroPicche)
                    4 -> cartaEstratta.text = getString(R.string.CinquePicche)
                    5 -> cartaEstratta.text = getString(R.string.SeiPicche)
                    6 -> cartaEstratta.text = getString(R.string.SettePicche)
                    7 -> cartaEstratta.text = getString(R.string.OttoPicche)
                    8 -> cartaEstratta.text = getString(R.string.NovePicche)
                    9 -> cartaEstratta.text = getString(R.string.DieciPicche)
                    10 -> cartaEstratta.text = getString(R.string.FantePicche)
                    11 -> cartaEstratta.text = getString(R.string.ReginaPicche)
                    12 -> cartaEstratta.text = getString(R.string.RePicche)

                    13 -> cartaEstratta.text = getString(R.string.AssoFiori)
                    14 -> cartaEstratta.text = getString(R.string.DueFiori)
                    15 -> cartaEstratta.text = getString(R.string.TreFiori)
                    16 -> cartaEstratta.text = getString(R.string.QuattroFiori)
                    17 -> cartaEstratta.text = getString(R.string.CinqueFiori)
                    18 -> cartaEstratta.text = getString(R.string.SeiFiori)
                    19 -> cartaEstratta.text = getString(R.string.SetteFiori)
                    20 -> cartaEstratta.text = getString(R.string.OttoFiori)
                    21 -> cartaEstratta.text = getString(R.string.NoveFiori)
                    22 -> cartaEstratta.text = getString(R.string.DieciFiori)
                    23 -> cartaEstratta.text = getString(R.string.FanteFiori)
                    24 -> cartaEstratta.text = getString(R.string.ReginaFiori)
                    25 -> cartaEstratta.text = getString(R.string.ReFiori)

                    26 -> cartaEstratta.text = getString(R.string.AssoQuadri)
                    27 -> cartaEstratta.text = getString(R.string.DueQuadri)
                    28 -> cartaEstratta.text = getString(R.string.TreQuadri)
                    29 -> cartaEstratta.text = getString(R.string.QuattroQuadri)
                    30 -> cartaEstratta.text = getString(R.string.CinqueQuadri)
                    31 -> cartaEstratta.text = getString(R.string.SeiQuadri)
                    32 -> cartaEstratta.text = getString(R.string.SetteQuadri)
                    33 -> cartaEstratta.text = getString(R.string.OttoQuadri)
                    34 -> cartaEstratta.text = getString(R.string.NoveQuadri)
                    35 -> cartaEstratta.text = getString(R.string.DieciQuadri)
                    36 -> cartaEstratta.text = getString(R.string.FanteQuadri)
                    37 -> cartaEstratta.text = getString(R.string.ReginaQuadri)
                    38 -> cartaEstratta.text = getString(R.string.ReQuadri)

                    39 -> cartaEstratta.text = getString(R.string.AssoCuori)
                    40 -> cartaEstratta.text = getString(R.string.DueCuori)
                    41 -> cartaEstratta.text = getString(R.string.TreCuori)
                    42 -> cartaEstratta.text = getString(R.string.QuattroCuori)
                    43 -> cartaEstratta.text = getString(R.string.CinqueCuori)
                    44 -> cartaEstratta.text = getString(R.string.SeiCuori)
                    45 -> cartaEstratta.text = getString(R.string.SetteCuori)
                    46 -> cartaEstratta.text = getString(R.string.OttoCuori)
                    47 -> cartaEstratta.text = getString(R.string.NoveCuori)
                    48 -> cartaEstratta.text = getString(R.string.DieciCuori)
                    49 -> cartaEstratta.text = getString(R.string.FanteCuori)
                    50 -> cartaEstratta.text = getString(R.string.ReginaCuori)
                    51 -> cartaEstratta.text = getString(R.string.ReCuori)

                    52 -> cartaEstratta.text = getString(R.string.MattoRosso)
                    53 -> cartaEstratta.text = getString(R.string.MattoNero)
                }
            }
            22 -> {
                when (carta) {
                    0 -> cartaEstratta.text = getString(R.string.Mago)
                    1 -> cartaEstratta.text = getString(R.string.Papessa)
                    2 -> cartaEstratta.text = getString(R.string.Imperatrice)
                    3 -> cartaEstratta.text = getString(R.string.Imperatore)
                    4 -> cartaEstratta.text = getString(R.string.Papa)
                    5 -> cartaEstratta.text = getString(R.string.Amanti)
                    6 -> cartaEstratta.text = getString(R.string.Carro)
                    7 -> cartaEstratta.text = getString(R.string.Forza)
                    8 -> cartaEstratta.text = getString(R.string.Eremita)
                    9 -> cartaEstratta.text = getString(R.string.Ruota)

                    10 -> cartaEstratta.text = getString(R.string.Giustizia)
                    11 -> cartaEstratta.text = getString(R.string.Appeso)
                    12 -> cartaEstratta.text = getString(R.string.Morte)
                    13 -> cartaEstratta.text = getString(R.string.Temperanza)
                    14 -> cartaEstratta.text = getString(R.string.Diavolo)
                    15 -> cartaEstratta.text = getString(R.string.Torre)
                    16 -> cartaEstratta.text = getString(R.string.Stella)
                    17 -> cartaEstratta.text = getString(R.string.Luna)
                    18 -> cartaEstratta.text = getString(R.string.Sole)
                    19 -> cartaEstratta.text = getString(R.string.Giudizio)

                    20 -> cartaEstratta.text = getString(R.string.ZAWARUDO)
                    21 -> cartaEstratta.text = getString(R.string.Matto)
                }
            }
        }
    }
}
