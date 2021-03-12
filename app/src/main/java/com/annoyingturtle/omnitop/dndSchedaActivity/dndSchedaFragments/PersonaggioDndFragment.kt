package com.annoyingturtle.omnitop.dndSchedaActivity.dndSchedaFragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.annoyingturtle.omnitop.R
import com.annoyingturtle.omnitop.dndSchedaActivity.DndSchedaActivity
import dndData.entities.Scheda
import dndData.utilData.Statistiche
import dndData.viewModel.SchedaViewModel
import kotlinx.android.synthetic.main.activity_update_caratteristiche_scheda.*
import kotlinx.android.synthetic.main.fragment_personaggio.*


class PersonaggioDndFragment : Fragment() {

    var idScheda = -1
    private lateinit var mSchedaViewModel : SchedaViewModel
    var modificaAbilita= false
    var campagnaid =-1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_personaggio, container, false)
        /** Inizializzazione scheda*/

        idScheda = activity?.intent?.extras!!.getInt("idScheda")
        campagnaid = activity?.intent?.extras!!.getInt("idCampagna")
        mSchedaViewModel = ViewModelProvider(this).get(SchedaViewModel::class.java)
        if (idScheda > -1){
            mSchedaViewModel.getSchedaFromID(idScheda)
            showSchedaData()
            //coloreDadi()

        }
        return view
    }

    private fun coloreDadi() {
        var str = valoreForza.text.toString().toInt()
        var dex = valoreDestrezza.text.toString().toInt()
        var inte = valoreInt.text.toString().toInt()
        var wis = valoreSag.text.toString().toInt()
        var cha = valoreCha.text.toString().toInt()

        var bonus = profBonus.text.toString().toInt()
        /**Acrobazia*/
        coloreDadoAbilita(pulsanteAcrobazia,
            textView49,
            valoreAcrobazia.text.toString().toInt(),
            bonus, dex )

        /**AddestrareAnim*/
        coloreDadoAbilita(pulsanteAddestrareAnim,
            textView53,
            valoreAddestrareAnim.text.toString().toInt(),
            bonus, wis )

        /**Arcano*/
        coloreDadoAbilita(pulsanteArcano,
            textView57,
            valoreArcano.text.toString().toInt(),
            bonus, inte )

        /**Atletica*/
        coloreDadoAbilita(pulsanteAtletica,
            textView61,
            valoreAtletica.text.toString().toInt(),
            bonus, str )

        /**Furtività*/
        coloreDadoAbilita(pulsanteFurtività,
            textView65,
            valoreFurtività.text.toString().toInt(),
            bonus, dex )

        /**Indagare*/
        coloreDadoAbilita(pulsanteIndagare,
            textView69,
            valoreIndagare.text.toString().toInt(),
            bonus, inte )

        /**Inganno*/
        coloreDadoAbilita(pulsanteIngano,
            textView73,
            valoreInganno.text.toString().toInt(),
            bonus, cha )

        /**Intimidire*/
        coloreDadoAbilita(pulsanteIntimidire,
            textView77,
            valoreIntimidire.text.toString().toInt(),
            bonus, cha )

        /**intrattenere*/
        coloreDadoAbilita(pulsanteIntrattenere,
            textView81,
            valoreIntrattenere.text.toString().toInt(),
            bonus, cha )

        /**intuizione*/
        coloreDadoAbilita(pulsanteIntuizione,
            textView51,
            valoreIntuizione.text.toString().toInt(),
            bonus, wis )

        /**medicina*/
        coloreDadoAbilita(pulsanteMedicina,
            textView55,
            valoreMedicina.text.toString().toInt(),
            bonus, wis )

        /**natura*/
        coloreDadoAbilita(pulsanteNatura,
            textView59,
            valoreNatua.text.toString().toInt(),
            bonus, inte )

        /**percezione*/
        coloreDadoAbilita(pulsantePercezione,
            textView63,
            valorePercezione.text.toString().toInt(),
            bonus, wis )

        /**persuasione*/
        coloreDadoAbilita(pulsantePersuasione,
            textView67,
            valorePersuasione.text.toString().toInt(),
            bonus, cha )

        /**rapidita*/
        coloreDadoAbilita(pulsanteRapiditaDiMano,
            textView71,
            valoreRapiditaDiMano.text.toString().toInt(),
            bonus, dex )

        /**religione*/
        coloreDadoAbilita(pulsanteReligione,
            textView75,
            valoreReligione.text.toString().toInt(),
            bonus, inte )

        /**sopravvivenza*/
        coloreDadoAbilita(pulsanteSopravvivenza,
            textView79,
            valoreSopravvivenza.text.toString().toInt(),
            bonus, wis )

        /**storia*/
        coloreDadoAbilita(pulsanteStoria,
            textView83,
            valoreStoria.text.toString().toInt(),
            bonus, inte )

    }

    private fun coloreDadoAbilita(pulsante: ImageButton, text: TextView, valore: Int, bonus: Int, modCar: Int) {
        var tmp = valore - modCar
        var grado = tmp/bonus

        when(grado){
            0 -> {
                    pulsante.background.setTintList(null)
                    text.setTextColor(ContextCompat.getColor(requireContext(),R.color.guidaScritteHigh))
                }
            1 ->{
                pulsante.background.setTint(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                text.setTextColor(ContextCompat.getColor(requireContext(),R.color.guidaScritteHigh))
            }
            2 ->{
                pulsante.background.setTint(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                text.setTextColor(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
            }
        }

    }

    override fun onStart() {
        super.onStart()


            attivaListnerAbilità()
            attivaListnerCaratteristicheEts()

            editStatisticheBase.setOnClickListener(){
                editStatisticheBase.visibility = View.GONE
                editStatisticheBaseSALVA.visibility = View.VISIBLE

                pfAttuali.isClickable = true
                pfAttuali.isFocusableInTouchMode = true

                pfTotali.isClickable = true
                pfTotali.isFocusableInTouchMode = true

                classeArmatura.isClickable = true
                classeArmatura.isFocusableInTouchMode = true

                iniziativa.isClickable = true
                iniziativa.isFocusableInTouchMode = true

                velocità.isClickable = true
                velocità.isFocusableInTouchMode = true

                profBonus.isClickable = true
                profBonus.isFocusableInTouchMode = true

                dadiVita.isClickable = true
                dadiVita.isFocusableInTouchMode = true
            }

            editStatisticheBaseSALVA.setOnClickListener(){
                editStatisticheBase.visibility = View.VISIBLE
                editStatisticheBaseSALVA.visibility = View.GONE

                pfAttuali.isClickable = false
                pfAttuali.isFocusableInTouchMode = false

                pfTotali.isClickable = false
                pfTotali.isFocusableInTouchMode = false

                classeArmatura.isClickable = false
                classeArmatura.isFocusableInTouchMode = false

                iniziativa.isClickable = false
                iniziativa.isFocusableInTouchMode = false

                velocità.isClickable = false
                velocità.isFocusableInTouchMode = false

                profBonus.isClickable = false
                profBonus.isFocusableInTouchMode = false

                dadiVita.isClickable = false
                dadiVita.isFocusableInTouchMode = false

                salvaStatBase(1)
                (activity as DndSchedaActivity).chiudiKeyboard()

            }

            aumentaPf.setOnClickListener(){
                var modificaPf : Int = pfAttuali.text.toString().toInt()
                modificaPf++
                pfAttuali.text = Editable.Factory.getInstance().newEditable(modificaPf.toString())
                salvaStatBase(0)
            }

            diminuisciPF.setOnClickListener(){
                var modificaPf : Int = pfAttuali.text.toString().toInt()
                modificaPf--
                pfAttuali.text = Editable.Factory.getInstance().newEditable(modificaPf.toString())
                salvaStatBase(0)
            }

            editCaratteristiche.setOnClickListener(){
                startActivity(Intent(context, UpdateCaratteristicheScheda::class.java).putExtra("idScheda", idScheda).putExtra("idCampagna", campagnaid))
            }

            editAbilità.setOnClickListener(){
                startActivity(Intent(context, UpdateAbilita::class.java).putExtra("idScheda", idScheda).putExtra("idCampagna", campagnaid))
            }

        }


    fun showSchedaData(){
        mSchedaViewModel.getSingleLiveData().observe(viewLifecycleOwner, Observer {
            pfAttuali.text= Editable.Factory.getInstance().newEditable(it.statistiche?.puntiFeritaAttuali.toString())
            pfTotali.text= Editable.Factory.getInstance().newEditable(it.statistiche?.puntiFeritaTotali.toString())
            classeArmatura.text= Editable.Factory.getInstance().newEditable(it.statistiche?.classeArmatura.toString())
            iniziativa.text= Editable.Factory.getInstance().newEditable(it.statistiche?.iniziativa.toString())
            velocità.text= Editable.Factory.getInstance().newEditable(it.statistiche?.velocitaMov.toString())

            percezionePassiva.text = (10 + Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() *
                    Editable.Factory.getInstance().newEditable(it.statistiche?.abPercezione.toString()).toString().toInt() +
                    (calcolaModificatore(Editable.Factory.getInstance().newEditable(it.statistiche?.WIS.toString()).toString().toInt())).toString().toInt()).toString()

            profBonus.text= Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString())
            dadiVita.text= Editable.Factory.getInstance().newEditable(it.statistiche?.dadoVita.toString())

            valoreForza.text= calcolaModificatore((Editable.Factory.getInstance().newEditable(it.statistiche?.STR.toString()).toString().toInt())).toString()
            valoreDestrezza.text= calcolaModificatore((Editable.Factory.getInstance().newEditable(it.statistiche?.DEX.toString()).toString().toInt())).toString()
            valoreCostituzione.text= calcolaModificatore((Editable.Factory.getInstance().newEditable(it.statistiche?.CON.toString()).toString().toInt())).toString()
            valoreInt.text= calcolaModificatore((Editable.Factory.getInstance().newEditable(it.statistiche?.INT.toString()).toString().toInt())).toString()
            valoreSag.text= calcolaModificatore((Editable.Factory.getInstance().newEditable(it.statistiche?.WIS.toString()).toString().toInt())).toString()
            valoreCha.text= calcolaModificatore((Editable.Factory.getInstance().newEditable(it.statistiche?.CHA.toString()).toString().toInt())).toString()

            tsForzaCheck.isChecked = it.statistiche?.tiroSalvezzaSTR == true
            tsDestrezzaCheck.isChecked = it.statistiche?.tiroSalvezzaDEX == true
            tsCostituzioneCheck.isChecked = it.statistiche?.tiroSalvezzaCON == true
            tsIntCheck.isChecked = it.statistiche?.tiroSalvezzaINT == true
            tsSaggCheck.isChecked = it.statistiche?.tiroSalvezzaWIS == true
            tsChaCheck.isChecked = it.statistiche?.tiroSalvezzaCHA == true


            valoreAcrobazia.text =(Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() *
                    Editable.Factory.getInstance().newEditable(it.statistiche?.abAcrobazia.toString()).toString().toInt() +
                    (calcolaModificatore(Editable.Factory.getInstance().newEditable(it.statistiche?.DEX.toString()).toString().toInt())).toString().toInt()).toString()
            /*when (it.statistiche?.abAcrobazia) {
                1 -> {
                //    pulsanteAcrobazia.background.setTint(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                    textView49.setTextColor(ContextCompat.getColor(requireContext(),R.color.guidaScritteHigh))
                }
                2 -> {
                    //    pulsanteAcrobazia.background.setTint(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                    textView49.setTextColor(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                }
                0 -> {
                    textView49.setTextColor(ContextCompat.getColor(requireContext(),R.color.guidaScritteHigh))
                    //pulsanteAcrobazia.background.setTintList(null)
                }
            }*/


            valoreAddestrareAnim.text = (Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() *
                    Editable.Factory.getInstance().newEditable(it.statistiche?.abAddestrare.toString()).toString().toInt() +
                    (calcolaModificatore(Editable.Factory.getInstance().newEditable(it.statistiche?.WIS.toString()).toString().toInt())).toString().toInt()).toString()
            /*when (it.statistiche?.abAddestrare) {
                1 -> {
                    //pulsanteAddestrareAnim.background.setTint(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                    textView53.setTextColor(ContextCompat.getColor(requireContext(), R.color.guidaScritteHigh))
                }
                2 -> {
                    //pulsanteAddestrareAnim.background.setTint(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                    textView53.setTextColor(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                }
                0 -> {
                    textView53.setTextColor(ContextCompat.getColor(requireContext(),R.color.guidaScritteHigh))
                    //pulsanteAddestrareAnim.background.setTintList(null)
                }
            }*/


            valoreArcano.text = (Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() *
                    Editable.Factory.getInstance().newEditable(it.statistiche?.abArcano.toString()).toString().toInt() +
                    (calcolaModificatore(Editable.Factory.getInstance().newEditable(it.statistiche?.INT.toString()).toString().toInt())).toString().toInt()).toString()
            /*when (it.statistiche?.abArcano) {
                1 -> {
                   // pulsanteArcano.background.setTint(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                    textView57.setTextColor(ContextCompat.getColor(requireContext(), R.color.guidaScritteHigh))
                }
                2 -> {
                   // pulsanteArcano.background.setTint(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                    textView57.setTextColor(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                }
                0 -> {
                    textView57.setTextColor(ContextCompat.getColor(requireContext(), R.color.guidaScritteHigh))
                   // pulsanteArcano.background.setTintList(null)
                }
            }*/


            valoreAtletica.text = (Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() *
                    Editable.Factory.getInstance().newEditable(it.statistiche?.abAtletica.toString()).toString().toInt() +
                    (calcolaModificatore(Editable.Factory.getInstance().newEditable(it.statistiche?.STR.toString()).toString().toInt())).toString().toInt()).toString()
            /*when (it.statistiche?.abAtletica) {
                1 -> {
                   // pulsanteAtletica.background.setTint(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                    textView61.setTextColor(ContextCompat.getColor(requireContext(), R.color.guidaScritteHigh))
                }
                2 -> {
                   // pulsanteAtletica.background.setTint(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                    textView61.setTextColor(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                }
                0 -> {
                    //pulsanteAtletica.background.setTintList(null)
                    textView61.setTextColor(ContextCompat.getColor(requireContext(), R.color.guidaScritteHigh))
                }
            }*/



            valoreFurtività.text = (Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() *
                    Editable.Factory.getInstance().newEditable(it.statistiche?.abFurtivita.toString()).toString().toInt() +
                    (calcolaModificatore(Editable.Factory.getInstance().newEditable(it.statistiche?.DEX.toString()).toString().toInt())).toString().toInt()).toString()
            /*when (it.statistiche?.abFurtivita) {
                1 -> {
                    //pulsanteFurtività.background.setTint(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                    textView65.setTextColor(ContextCompat.getColor(requireContext(), R.color.guidaScritteHigh))
                }
                2 -> {
                    //pulsanteFurtività.background.setTint(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                    textView65.setTextColor(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                }
                0 -> {
                    //pulsanteFurtività.background.setTintList(null)
                    textView65.setTextColor(ContextCompat.getColor(requireContext(), R.color.guidaScritteHigh))
                }
            }*/



            valoreIndagare.text = ((Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() *
                    Editable.Factory.getInstance().newEditable(it.statistiche?.abIndagare.toString()).toString().toInt() +
                    (calcolaModificatore(Editable.Factory.getInstance().newEditable(it.statistiche?.INT.toString()).toString().toInt())).toString().toInt()).toString())     //// DA QUA
            /*when (it.statistiche?.abIndagare) {
                1 -> {
                    //pulsanteIndagare.background.setTint(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                    textView69.setTextColor(ContextCompat.getColor(requireContext(), R.color.guidaScritteHigh))
                }
                2 -> {
                  //  pulsanteIndagare.background.setTint(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                    textView69.setTextColor(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                }
                0 -> {
                  //  pulsanteIndagare.background.setTintList(null)
                    textView69.setTextColor(ContextCompat.getColor(requireContext(), R.color.guidaScritteHigh))
                }
            }*/



            valoreInganno.text = (Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() *
                    Editable.Factory.getInstance().newEditable(it.statistiche?.abInganno.toString()).toString().toInt() +
                    (calcolaModificatore(Editable.Factory.getInstance().newEditable(it.statistiche?.CHA.toString()).toString().toInt())).toString().toInt()).toString()
            /*when (it.statistiche?.abInganno) {
                1 -> {
                   // pulsanteIngano.background.setTint(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                    textView73.setTextColor(ContextCompat.getColor(requireContext(), R.color.guidaScritteHigh))
                }
                2 -> {
                   // pulsanteIngano.background.setTint(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                    textView73.setTextColor(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                }
                0 -> {
                    // pulsanteIngano.background.setTintList(null)
                    textView73.setTextColor(ContextCompat.getColor(requireContext(), R.color.guidaScritteHigh))
                }
            }*/


            valoreIntimidire.text = (Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() *
                    Editable.Factory.getInstance().newEditable(it.statistiche?.abIntimidire.toString()).toString().toInt() +
                    (calcolaModificatore(Editable.Factory.getInstance().newEditable(it.statistiche?.CHA.toString()).toString().toInt())).toString().toInt()).toString()
            /*when (it.statistiche?.abIntimidire) {
                1 -> {
                    // pulsanteIntimidire.background.setTint(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                    textView77.setTextColor(ContextCompat.getColor(requireContext(), R.color.guidaScritteHigh))
                }
                2 -> {
                    //  pulsanteIntimidire.background.setTint(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                    textView77.setTextColor(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                }
                0 -> {
                    //  pulsanteIntimidire.background.setTintList(null)
                    textView77.setTextColor(ContextCompat.getColor(requireContext(), R.color.guidaScritteHigh))
                }
            }*/



            valoreIntuizione.text = (Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() *
                    Editable.Factory.getInstance().newEditable(it.statistiche?.abIntuizione.toString()).toString().toInt() +
                    (calcolaModificatore(Editable.Factory.getInstance().newEditable(it.statistiche?.WIS.toString()).toString().toInt())).toString().toInt()).toString()
            /*when (it.statistiche?.abIntuizione) {
                1 -> {
                    // pulsanteIntuizione.background.setTint(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                    textView51.setTextColor(ContextCompat.getColor(requireContext(), R.color.guidaScritteHigh))
                }
                2 -> {
                    //pulsanteIntuizione.background.setTint(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                    textView51.setTextColor(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                }
                0 -> {
                    //pulsanteIntuizione.background.setTintList(null)
                    textView51.setTextColor(ContextCompat.getColor(requireContext(), R.color.guidaScritteHigh))
                }
            }*/



            valoreIntrattenere.text = (Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() *
                    Editable.Factory.getInstance().newEditable(it.statistiche?.abIntrattenere.toString()).toString().toInt() +
                    (calcolaModificatore(Editable.Factory.getInstance().newEditable(it.statistiche?.CHA.toString()).toString().toInt())).toString().toInt()).toString()
            /*when (it.statistiche?.abIntrattenere) {
                1 -> {
                    // pulsanteIntrattenere.background.setTint(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                    textView81.setTextColor(ContextCompat.getColor(requireContext(), R.color.guidaScritteHigh))
                }
                2 -> {
                    // pulsanteIntrattenere.background.setTint(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                    textView81.setTextColor(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                }
                0 -> {
                    // pulsanteIntrattenere.background.setTintList(null)
                    textView81.setTextColor(ContextCompat.getColor(requireContext(), R.color.guidaScritteHigh))
                }
            }*/


            valoreMedicina.text = (Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() *
                    Editable.Factory.getInstance().newEditable(it.statistiche?.abMedicina.toString()).toString().toInt() +
                    (calcolaModificatore(Editable.Factory.getInstance().newEditable(it.statistiche?.WIS.toString()).toString().toInt())).toString().toInt()).toString()
            /*when (it.statistiche?.abMedicina) {
                1 -> {
                    //pulsanteMedicina.background.setTint(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                    textView55.setTextColor(ContextCompat.getColor(requireContext(), R.color.guidaScritteHigh))
                }
                2 -> {
                    //pulsanteMedicina.background.setTint(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                    textView55.setTextColor(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                }
                0 -> {
                    //pulsanteMedicina.background.setTintList(null)
                    textView55.setTextColor(ContextCompat.getColor(requireContext(), R.color.guidaScritteHigh))
                }
            }*/


            valoreNatua.text = (Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() *
                    Editable.Factory.getInstance().newEditable(it.statistiche?.abNatura.toString()).toString().toInt() +
                    (calcolaModificatore(Editable.Factory.getInstance().newEditable(it.statistiche?.INT.toString()).toString().toInt())).toString().toInt()).toString()
            /*when (it.statistiche?.abNatura) {
                1 -> {
                    //pulsanteNatura.background.setTint(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                    textView59.setTextColor(ContextCompat.getColor(requireContext(), R.color.guidaScritteHigh))
                }
                2 -> {
                    //pulsanteNatura.background.setTint(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                    textView59.setTextColor(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                }
                0 -> {
                    //pulsanteNatura.background.setTintList(null)
                    textView59.setTextColor(ContextCompat.getColor(requireContext(), R.color.guidaScritteHigh))
                }
            }*/


            valorePercezione.text = (Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() *
                    Editable.Factory.getInstance().newEditable(it.statistiche?.abPercezione.toString()).toString().toInt() +
                    (calcolaModificatore(Editable.Factory.getInstance().newEditable(it.statistiche?.WIS.toString()).toString().toInt())).toString().toInt()).toString()
            /*when (it.statistiche?.abPercezione) {
                1 -> {
                    //pulsantePercezione.background.setTint(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                    textView63.setTextColor(ContextCompat.getColor(requireContext(), R.color.guidaScritteHigh))
                }
                2 -> {
                    // pulsantePercezione.background.setTint(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                    textView63.setTextColor(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                }
                0 -> {
                    // pulsantePercezione.background.setTintList(null)
                    textView63.setTextColor(ContextCompat.getColor(requireContext(), R.color.guidaScritteHigh))
                }
            }*/


            valorePersuasione.text = (Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() *
                    Editable.Factory.getInstance().newEditable(it.statistiche?.abPersuasione.toString()).toString().toInt() +
                    (calcolaModificatore(Editable.Factory.getInstance().newEditable(it.statistiche?.CHA.toString()).toString().toInt())).toString().toInt()).toString()
            /*when (it.statistiche?.abPersuasione) {
                1 -> {
                    // pulsantePersuasione.background.setTint(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                    textView67.setTextColor(ContextCompat.getColor(requireContext(), R.color.guidaScritteHigh))
                }
                2 -> {
                    // pulsantePersuasione.background.setTint(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                    textView67.setTextColor(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                }
                0 -> {
                    // pulsantePersuasione.background.setTintList(null)
                    textView67.setTextColor(ContextCompat.getColor(requireContext(), R.color.guidaScritteHigh))
                }
            }*/


            valoreRapiditaDiMano.text = (Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() *
                    Editable.Factory.getInstance().newEditable(it.statistiche?.abRapiditMano.toString()).toString().toInt() +
                    (calcolaModificatore(Editable.Factory.getInstance().newEditable(it.statistiche?.DEX.toString()).toString().toInt())).toString().toInt()).toString()
            /*when (it.statistiche?.abRapiditMano) {
                1 -> {
                    // pulsanteRapiditaDiMano.background.setTint(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                    textView71.setTextColor(ContextCompat.getColor(requireContext(), R.color.guidaScritteHigh))
                }
                2 -> {
                    // pulsanteRapiditaDiMano.background.setTint(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                    textView71.setTextColor(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                }
                0 -> {
                    // pulsanteRapiditaDiMano.background.setTintList(null)
                    textView71.setTextColor(ContextCompat.getColor(requireContext(), R.color.guidaScritteHigh))
                }
            }*/


            valoreReligione.text = (Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() *
                    Editable.Factory.getInstance().newEditable(it.statistiche?.abReligione.toString()).toString().toInt() +
                    (calcolaModificatore(Editable.Factory.getInstance().newEditable(it.statistiche?.INT.toString()).toString().toInt())).toString().toInt()).toString()
            /*when (it.statistiche?.abReligione) {
                1 -> {
                    // pulsanteReligione.background.setTint(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                    textView75.setTextColor(ContextCompat.getColor(requireContext(), R.color.guidaScritteHigh))
                }
                2 -> {
                    // pulsanteReligione.background.setTint(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                    textView75.setTextColor(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                }
                0 -> {
                    // pulsanteReligione.background.setTintList(null)
                    textView75.setTextColor(ContextCompat.getColor(requireContext(), R.color.guidaScritteHigh))
                }
            }*/


            valoreSopravvivenza.text = (Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() *
                    Editable.Factory.getInstance().newEditable(it.statistiche?.abSoprav.toString()).toString().toInt() +
                    (calcolaModificatore(Editable.Factory.getInstance().newEditable(it.statistiche?.WIS.toString()).toString().toInt())).toString().toInt()).toString()
            /*when (it.statistiche?.abSoprav) {
                1 -> {
                    // pulsanteSopravvivenza.background.setTint(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                    textView79.setTextColor(ContextCompat.getColor(requireContext(), R.color.guidaScritteHigh))
                }
                2 -> {
                    // pulsanteSopravvivenza.background.setTint(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                    textView79.setTextColor(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                }
                0 -> {
                    // pulsanteSopravvivenza.background.setTintList(null)
                    textView79.setTextColor(ContextCompat.getColor(requireContext(), R.color.guidaScritteHigh))
                }
            }*/


            valoreStoria.text = (Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() *
                    Editable.Factory.getInstance().newEditable(it.statistiche?.abStoria.toString()).toString().toInt() +
                    (calcolaModificatore(Editable.Factory.getInstance().newEditable(it.statistiche?.INT.toString()).toString().toInt())).toString().toInt()).toString()
            /*when (it.statistiche?.abStoria) {
                1 -> {
                    // pulsanteStoria.background.setTint(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                    textView83.setTextColor(ContextCompat.getColor(requireContext(), R.color.guidaScritteHigh))
                }
                2 -> {
                    // pulsanteStoria.background.setTint(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                    textView83.setTextColor(ContextCompat.getColor(requireContext(),R.color.secondaryLight65op))
                }
                0 -> {
                    // pulsanteStoria.background.setTintList(null)
                    textView83.setTextColor(ContextCompat.getColor(requireContext(), R.color.guidaScritteHigh))
                }
            }*/

        })

    }

    /** Funzione per attivare i listner sui pulsanti delle abilità (sia modifica che tiro) */

    fun attivaListnerAbilità(){

        pulsanteAcrobazia.setOnClickListener(){
            if(!modificaAbilita)
                lanciaDado("Acrobazia", valoreAcrobazia.text.toString().toInt())
        }

        pulsanteAddestrareAnim.setOnClickListener(){
            if(!modificaAbilita)
                lanciaDado("Addestrare Animali", valoreAddestrareAnim.text.toString().toInt())
        }

        pulsanteArcano.setOnClickListener(){
            if(!modificaAbilita)
                lanciaDado("Arcano", valoreArcano.text.toString().toInt())
        }

        pulsanteAtletica.setOnClickListener(){
            if(!modificaAbilita)
                lanciaDado("Atletica", valoreAtletica.text.toString().toInt())
        }

        pulsanteFurtività.setOnClickListener(){
            if(!modificaAbilita)
                lanciaDado("Furtività", valoreFurtività.text.toString().toInt())
        }

        pulsanteIndagare.setOnClickListener(){
            if(!modificaAbilita)
                lanciaDado("Indagare", valoreIndagare.text.toString().toInt())
        }

        pulsanteIngano.setOnClickListener(){
            if(!modificaAbilita)
                lanciaDado("Inganno", valoreArcano.text.toString().toInt())
        }

        pulsanteIntimidire.setOnClickListener(){
            if(!modificaAbilita)
                lanciaDado("Intimidire", valoreIntimidire.text.toString().toInt())
        }

        pulsanteIntrattenere.setOnClickListener(){
            if(!modificaAbilita)
                lanciaDado("Intrattenere", valoreIntrattenere.text.toString().toInt())
        }

        pulsanteIntuizione.setOnClickListener(){
            if(!modificaAbilita)
                lanciaDado("Intuizione", valoreIntuizione.text.toString().toInt())
        }

        pulsanteMedicina.setOnClickListener(){
            if(!modificaAbilita)
                lanciaDado("Medicina", valoreMedicina.text.toString().toInt())
        }

        pulsanteNatura.setOnClickListener(){
            if(!modificaAbilita)
                lanciaDado("Natura", valoreNatua.text.toString().toInt())
        }

        pulsantePercezione.setOnClickListener(){
            if(!modificaAbilita)
                lanciaDado("Percezione", valorePercezione.text.toString().toInt())
        }

        pulsantePersuasione.setOnClickListener(){
            if(!modificaAbilita)
                lanciaDado("Persuasione", valorePersuasione.text.toString().toInt())
        }

        pulsanteRapiditaDiMano.setOnClickListener(){
            if(!modificaAbilita)
                lanciaDado("Rapidità di mano", valoreRapiditaDiMano.text.toString().toInt())
        }

        pulsanteReligione.setOnClickListener(){
            if(!modificaAbilita)
                lanciaDado("Religione", valoreRapiditaDiMano.text.toString().toInt())
        }

        pulsanteSopravvivenza.setOnClickListener(){
            if(!modificaAbilita)
                lanciaDado("Sopravvivenza", valoreSopravvivenza.text.toString().toInt())
        }

        pulsanteStoria.setOnClickListener(){
            if(!modificaAbilita)
                lanciaDado("Storia", valoreStoria.text.toString().toInt())
        }

    }

    /** Funzione per lanciare i dadi con i bonus relativi */
    fun lanciaDado(dadoLanciato: String, bonus: Int){

        val operazione : String = if (bonus>0) "+" else ""

        val risultatoDado = (1..20).random()
        val risultatoTotale = risultatoDado + bonus

        Toast.makeText(requireContext(), "$dadoLanciato: $risultatoDado $operazione $bonus = $risultatoTotale", Toast.LENGTH_SHORT).show()

    }



    fun salvaStatBase(mostraToast: Int){
        mSchedaViewModel.getSingleLiveData().observe(viewLifecycleOwner, Observer {
            val pfA : Int = if (pfAttuali.text.isEmpty()) 0 else pfAttuali.text.toString().toInt()
            val pfT : Int = if (pfTotali.text.isEmpty()) 0 else pfTotali.text.toString().toInt()
            val ca : Int = if (classeArmatura.text.isEmpty()) 0 else classeArmatura.text.toString().toInt()
            val ini : Int = if (iniziativa.text.isEmpty()) 0 else iniziativa.text.toString().toInt()
            val vel : Double = if (velocità.text.isEmpty()) 0.0 else velocità.text.toString().toDouble()
            val prf : Int = if (profBonus.text.isEmpty()) 0 else profBonus.text.toString().toInt()
            val dv : Int = if (dadiVita.text.isEmpty()) 0 else dadiVita.text.toString().toInt()

            val personaggioNuovo = Statistiche(
                pfA, pfT, ca, ini, vel, prf, dv,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche?.STR, mSchedaViewModel.getSingleLiveData().value!!.statistiche?.DEX,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche?.CON, mSchedaViewModel.getSingleLiveData().value!!.statistiche?.INT,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche?.WIS, mSchedaViewModel.getSingleLiveData().value!!.statistiche?.CHA,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche?.tiroSalvezzaSTR, mSchedaViewModel.getSingleLiveData().value!!.statistiche?.tiroSalvezzaDEX,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche?.tiroSalvezzaCON, mSchedaViewModel.getSingleLiveData().value!!.statistiche?.tiroSalvezzaINT,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche?.tiroSalvezzaWIS, mSchedaViewModel.getSingleLiveData().value!!.statistiche?.tiroSalvezzaCHA,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche?.abAcrobazia, mSchedaViewModel.getSingleLiveData().value!!.statistiche?.abAddestrare,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche?.abArcano, mSchedaViewModel.getSingleLiveData().value!!.statistiche?.abAtletica,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche?.abFurtivita, mSchedaViewModel.getSingleLiveData().value!!.statistiche?.abIndagare,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche?.abInganno, mSchedaViewModel.getSingleLiveData().value!!.statistiche?.abIntimidire,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche?.abIntrattenere, mSchedaViewModel.getSingleLiveData().value!!.statistiche?.abIntuizione,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche?.abMedicina, mSchedaViewModel.getSingleLiveData().value!!.statistiche?.abNatura,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche?.abPercezione, mSchedaViewModel.getSingleLiveData().value!!.statistiche?.abPersuasione,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche?.abRapiditMano, mSchedaViewModel.getSingleLiveData().value!!.statistiche?.abReligione,
                mSchedaViewModel.getSingleLiveData().value!!.statistiche?.abSoprav, mSchedaViewModel.getSingleLiveData().value!!.statistiche?.abStoria
            )

            mSchedaViewModel.updateScheda(Scheda(
                mSchedaViewModel.getSingleLiveData().value!!.id,
                mSchedaViewModel.getSingleLiveData().value!!.Campagnaid,
                mSchedaViewModel.getSingleLiveData().value!!.nomePG,
                mSchedaViewModel.getSingleLiveData().value!!.tipoScheda,
                personaggioNuovo,
                mSchedaViewModel.getSingleLiveData().value!!.incantatore,
                mSchedaViewModel.getSingleLiveData().value!!.dettagli,
                mSchedaViewModel.getSingleLiveData().value!!.moneteTotali
            ))
        })

        if(mostraToast!= 0)
            Toast.makeText(context, "Modifica avvenuta con successo!", Toast.LENGTH_SHORT).show()

    }

    fun attivaListnerCaratteristicheEts(){
        pulsanteForza.setOnClickListener(){
            lanciaDado("Forza", valoreForza.text.toString().toInt())
        }

        pulsanteDestrezza.setOnClickListener(){
            lanciaDado("Destrezza", valoreDestrezza.text.toString().toInt() )
        }
        pulsanteCostituzione.setOnClickListener(){
            lanciaDado("Costituzione", valoreCostituzione.text.toString().toInt())
        }

        pulsanteInt.setOnClickListener(){
            lanciaDado("Intelligenza", valoreInt.text.toString().toInt())
        }

        pulsanteSag.setOnClickListener(){
            lanciaDado("Saggezza", valoreSag.text.toString().toInt())
        }
        pulsanteCha.setOnClickListener(){
            lanciaDado("Carisma", valoreCha.text.toString().toInt())
        }

        pulsanteTsForza.setOnClickListener(){
            mSchedaViewModel.getSingleLiveData().observe(viewLifecycleOwner, Observer {

                val bonus : Int = if(tsForzaCheck.isChecked) Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() + valoreForza.text.toString().toInt()
                else
                    valoreForza.text.toString().toInt()
                lanciaDado("Ts Forza", bonus)
            })
        }

        pulsanteTsDex.setOnClickListener(){
            mSchedaViewModel.getSingleLiveData().observe(viewLifecycleOwner, Observer {

                val bonus : Int = if(tsDestrezzaCheck.isChecked) Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() + valoreDestrezza.text.toString().toInt()
                else
                    valoreDestrezza.text.toString().toInt()
                lanciaDado("Ts Destrezza", bonus)
            })
        }

        pulsanteTsCos.setOnClickListener(){
            mSchedaViewModel.getSingleLiveData().observe(viewLifecycleOwner, Observer {

                val bonus : Int = if(tsCostituzioneCheck.isChecked) Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() + valoreCostituzione.text.toString().toInt()
                else
                    valoreCostituzione.text.toString().toInt()
                lanciaDado("Ts Costituzione", bonus)
            })
        }

        pulsanteTsInt.setOnClickListener(){
            mSchedaViewModel.getSingleLiveData().observe(viewLifecycleOwner, Observer {

                val bonus : Int = if(tsIntCheck.isChecked) Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() + valoreInt.text.toString().toInt()
                else
                    valoreInt.text.toString().toInt()
                lanciaDado("Ts Intelligenza", bonus)
            })
        }

        pulsanteTsWis.setOnClickListener(){
            mSchedaViewModel.getSingleLiveData().observe(viewLifecycleOwner, Observer {

                val bonus : Int = if(tsSaggCheck.isChecked) Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() + valoreSag.text.toString().toInt()
                else
                    valoreSag.text.toString().toInt()
                lanciaDado("Ts Saggezza", bonus)
            })
        }

        pulsanteTsCha.setOnClickListener(){
            mSchedaViewModel.getSingleLiveData().observe(viewLifecycleOwner, Observer {

                val bonus : Int = if(tsChaCheck.isChecked) Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() + valoreCha.text.toString().toInt()
                else
                    valoreCha.text.toString().toInt()
                lanciaDado("Ts Carisma", bonus)
            })
        }
    }


    fun calcolaModificatore(stat: Int): Int{

        if (stat<10)
            if(stat%2 !=0)
               return (stat-10)/2-1

        return  (stat-10)/2

    }


}