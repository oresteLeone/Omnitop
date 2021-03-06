package com.annoyingturtle.omnitop.dndSchedaActivity.dndSchedaFragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.annoyingturtle.omnitop.R
import dndData.entities.Scheda
import dndData.utilData.Statistiche
import dndData.viewModel.SchedaViewModel
import kotlinx.android.synthetic.main.activity_update_caratteristiche_scheda.*
import kotlinx.android.synthetic.main.fragment_personaggio.*


class PersonaggioDndFragment : Fragment() {

    var idScheda = -1
    private lateinit var mSchedaViewModel : SchedaViewModel
    var modificaAbilita= false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personaggio, container, false)
    }

    override fun onStart() {
        super.onStart()

        /** Inizializzazione scheda*/

        idScheda = activity?.intent?.extras!!.getInt("idScheda")

        mSchedaViewModel = ViewModelProvider(this).get(SchedaViewModel::class.java)
        if (idScheda > -1){
            mSchedaViewModel.getSchedaFromID(idScheda)
            showSchedaData()

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

                profBonus.isClickable = false
                profBonus.isFocusableInTouchMode = false

                dadiVita.isClickable = false
                dadiVita.isFocusableInTouchMode = false

                salvaStatBase(1)

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
                startActivity(Intent(context, UpdateCaratteristicheScheda::class.java).putExtra("idScheda", idScheda))
            }

            editAbilità.setOnClickListener(){
                startActivity(Intent(context, UpdateAbilita::class.java).putExtra("idScheda", idScheda))
            }

        }



    }


    fun showSchedaData(){
        mSchedaViewModel.getSingleLiveData().observe(this, Observer {
            pfAttuali.text= Editable.Factory.getInstance().newEditable(it.statistiche?.puntiFeritaAttuali.toString())
            pfTotali.text= Editable.Factory.getInstance().newEditable(it.statistiche?.puntiFeritaTotali.toString())
            classeArmatura.text= Editable.Factory.getInstance().newEditable(it.statistiche?.classeArmatura.toString())
            iniziativa.text= Editable.Factory.getInstance().newEditable(it.statistiche?.iniziativa.toString())
            velocità.text= Editable.Factory.getInstance().newEditable(it.statistiche?.velocitaMov.toString())

            percezionePassiva.text = (10 + Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() *
                    Editable.Factory.getInstance().newEditable(it.statistiche?.abPercezione.toString()).toString().toInt() +
                    Editable.Factory.getInstance().newEditable(it.statistiche?.WIS.toString()).toString().toInt()).toString()

            profBonus.text= Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString())
            dadiVita.text= Editable.Factory.getInstance().newEditable(it.statistiche?.dadoVita.toString())

            valoreForza.text= ((Editable.Factory.getInstance().newEditable(it.statistiche?.STR.toString()).toString().toInt() -10)/2).toString()
            valoreDestrezza.text= ((Editable.Factory.getInstance().newEditable(it.statistiche?.DEX.toString()).toString().toInt() -10)/2).toString()
            valoreCostituzione.text= ((Editable.Factory.getInstance().newEditable(it.statistiche?.CON.toString()).toString().toInt() -10)/2).toString()
            valoreInt.text= ((Editable.Factory.getInstance().newEditable(it.statistiche?.INT.toString()).toString().toInt() -10)/2).toString()
            valoreSag.text= ((Editable.Factory.getInstance().newEditable(it.statistiche?.WIS.toString()).toString().toInt() -10)/2).toString()
            valoreCha.text= ((Editable.Factory.getInstance().newEditable(it.statistiche?.CHA.toString()).toString().toInt() -10)/2).toString()

            tsForzaCheck.isChecked = it.statistiche?.tiroSalvezzaSTR == true
            tsDestrezzaCheck.isChecked = it.statistiche?.tiroSalvezzaDEX == true
            tsCostituzioneCheck.isChecked = it.statistiche?.tiroSalvezzaCON == true
            tsIntCheck.isChecked = it.statistiche?.tiroSalvezzaINT == true
            tsSaggCheck.isChecked = it.statistiche?.tiroSalvezzaWIS == true
            tsChaCheck.isChecked = it.statistiche?.tiroSalvezzaCHA == true


            valoreAcrobazia.text =(Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() *
                    Editable.Factory.getInstance().newEditable(it.statistiche?.abAcrobazia.toString()).toString().toInt() +
                    ((Editable.Factory.getInstance().newEditable(it.statistiche?.DEX.toString()).toString().toInt() -10)/2).toString().toInt()).toString()

            valoreAddestrareAnim.text = (Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() *
                    Editable.Factory.getInstance().newEditable(it.statistiche?.abAddestrare.toString()).toString().toInt() +
                    ((Editable.Factory.getInstance().newEditable(it.statistiche?.WIS.toString()).toString().toInt() -10)/2).toString().toInt()).toString()

            valoreArcano.text = (Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() *
                    Editable.Factory.getInstance().newEditable(it.statistiche?.abArcano.toString()).toString().toInt() +
                    ((Editable.Factory.getInstance().newEditable(it.statistiche?.INT.toString()).toString().toInt() -10)/2).toString().toInt()).toString()

            valoreAtletica.text = (Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() *
                    Editable.Factory.getInstance().newEditable(it.statistiche?.abAtletica.toString()).toString().toInt() +
                    ((Editable.Factory.getInstance().newEditable(it.statistiche?.STR.toString()).toString().toInt() -10)/2).toString().toInt()).toString()

            valoreFurtività.text = (Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() *
                    Editable.Factory.getInstance().newEditable(it.statistiche?.abFurtivita.toString()).toString().toInt() +
                    ((Editable.Factory.getInstance().newEditable(it.statistiche?.DEX.toString()).toString().toInt() -10)/2).toString().toInt()).toString()

            valoreIndagare.text = ((Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() *
                    Editable.Factory.getInstance().newEditable(it.statistiche?.abIndagare.toString()).toString().toInt() +
                    ((Editable.Factory.getInstance().newEditable(it.statistiche?.INT.toString()).toString().toInt() -10)/2).toString().toInt()).toString())

            valoreInganno.text = (Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() *
                    Editable.Factory.getInstance().newEditable(it.statistiche?.abInganno.toString()).toString().toInt() +
                    ((Editable.Factory.getInstance().newEditable(it.statistiche?.CHA.toString()).toString().toInt() -10)/2).toString().toInt()).toString()

            valoreIntimidire.text = (Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() *
                    Editable.Factory.getInstance().newEditable(it.statistiche?.abIntimidire.toString()).toString().toInt() +
                    ((Editable.Factory.getInstance().newEditable(it.statistiche?.CHA.toString()).toString().toInt() -10)/2).toString().toInt()).toString()

            valoreIntuizione.text = (Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() *
                    Editable.Factory.getInstance().newEditable(it.statistiche?.abIntuizione.toString()).toString().toInt() +
                    ((Editable.Factory.getInstance().newEditable(it.statistiche?.WIS.toString()).toString().toInt() -10)/2).toString().toInt()).toString()

            valoreMedicina.text = (Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() *
                    Editable.Factory.getInstance().newEditable(it.statistiche?.abMedicina.toString()).toString().toInt() +
                    ((Editable.Factory.getInstance().newEditable(it.statistiche?.WIS.toString()).toString().toInt() -10)/2).toString().toInt()).toString()

            valoreNatua.text = (Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() *
                    Editable.Factory.getInstance().newEditable(it.statistiche?.abNatura.toString()).toString().toInt() +
                    ((Editable.Factory.getInstance().newEditable(it.statistiche?.INT.toString()).toString().toInt() -10)/2).toString().toInt()).toString()

            valorePercezione.text = (Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() *
                    Editable.Factory.getInstance().newEditable(it.statistiche?.abPercezione.toString()).toString().toInt() +
                    ((Editable.Factory.getInstance().newEditable(it.statistiche?.WIS.toString()).toString().toInt() -10)/2).toString().toInt()).toString()

            valorePersuasione.text = (Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() *
                    Editable.Factory.getInstance().newEditable(it.statistiche?.abPersuasione.toString()).toString().toInt() +
                    ((Editable.Factory.getInstance().newEditable(it.statistiche?.CHA.toString()).toString().toInt() -10)/2).toString().toInt()).toString()

            valoreRapiditaDiMano.text = (Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() *
                    Editable.Factory.getInstance().newEditable(it.statistiche?.abRapiditMano.toString()).toString().toInt() +
                    ((Editable.Factory.getInstance().newEditable(it.statistiche?.DEX.toString()).toString().toInt() -10)/2).toString().toInt()).toString()

            valoreReligione.text = (Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() *
                    Editable.Factory.getInstance().newEditable(it.statistiche?.abReligione.toString()).toString().toInt() +
                    ((Editable.Factory.getInstance().newEditable(it.statistiche?.INT.toString()).toString().toInt() -10)/2).toString().toInt()).toString()

            valoreSopravvivenza.text = (Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() *
                    Editable.Factory.getInstance().newEditable(it.statistiche?.abSoprav.toString()).toString().toInt() +
                    ((Editable.Factory.getInstance().newEditable(it.statistiche?.WIS.toString()).toString().toInt() -10)/2).toString().toInt()).toString()

            valoreStoria.text = (Editable.Factory.getInstance().newEditable(it.statistiche?.bonusCompetenza.toString()).toString().toInt() *
                    Editable.Factory.getInstance().newEditable(it.statistiche?.abStoria.toString()).toString().toInt() +
                    ((Editable.Factory.getInstance().newEditable(it.statistiche?.INT.toString()).toString().toInt() -10)/2).toString().toInt()).toString()

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



}