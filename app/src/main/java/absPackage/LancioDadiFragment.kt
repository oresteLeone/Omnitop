package absPackage

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.annoyingturtle.omnitop.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.lancio_dadi_layout2.*
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class LancioDadiFragment : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.lancio_dadi_layout2, container, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onStart() {
        super.onStart()
        popolaListner()
    }

    /******* METODI PER LE TEXT VIEW ED I PULSANTI ********/


    private fun appendOnExpression(string: String) {

        if(risultatoTextView.text.isNotEmpty())  {
            displayCalcolatrice.text = risultatoTextView.text
            risultatoTextView.text = ""
        }


        risultatoTextView.text = ""
        displayCalcolatrice.append(string)

    }

    fun popolaListner() {
        /****** LISTNER PER I PULSANTI *******/
        //Numeri

        butt0.setOnClickListener { appendOnExpression("0") }
        butt1.setOnClickListener { appendOnExpression("1") }
        butt2.setOnClickListener { appendOnExpression("2") }
        butt3.setOnClickListener { appendOnExpression("3") }
        butt4.setOnClickListener { appendOnExpression("4") }
        butt5.setOnClickListener { appendOnExpression("5") }
        butt6.setOnClickListener { appendOnExpression("6") }
        butt7.setOnClickListener { appendOnExpression("7") }
        butt8.setOnClickListener { appendOnExpression("8") }
        butt9.setOnClickListener { appendOnExpression("9") }

        //Operatori

        plus.setOnClickListener { appendOnExpression("+") }
        minus.setOnClickListener { appendOnExpression("-") }
        multiply.setOnClickListener { appendOnExpression("*") }
        divide.setOnClickListener { appendOnExpression("/") }
        openParentesis.setOnClickListener { appendOnExpression("(") }
        closeParentesis.setOnClickListener { appendOnExpression(")") }

        //dadi
        d20.setOnClickListener{appendOnExpression("d20")}
        d2.setOnClickListener{appendOnExpression("d2")}
        d4.setOnClickListener{appendOnExpression("d4")}
        d6.setOnClickListener{appendOnExpression("d6")}
        d8.setOnClickListener{appendOnExpression("d8")}
        d10.setOnClickListener{appendOnExpression("d10")}
        d12.setOnClickListener{appendOnExpression("d12")}
        d100.setOnClickListener{appendOnExpression("d100")}
        dx.setOnClickListener{appendOnExpression("d")}

        //Pulsante Roll

        buttRoll.setOnClickListener(){



            try{

                var espressione  = ExpressionBuilder(sostituisciD(displayCalcolatrice.text.toString())).build()
                var result = espressione.evaluate()
                var longResult = result.toLong()
                if (result == longResult.toDouble()) risultatoTextView.text = longResult.toString()
                    else
                    risultatoTextView.text = result.toString()
            }catch (e : Exception){
                Log.d("Eccezione Calcolatrice", "message:" + e.message)
            }
        }


        backspace.setOnClickListener {
            val string = displayCalcolatrice.text.toString()
            if (string.isNotEmpty()) {
                displayCalcolatrice.text = string.substring(0, string.length - 1)
                risultatoTextView.text = ""


            }

        }
    }


    fun sostituisciD (str : String) : String
    {
        var i = 0
        var nDadi : Int
        var tDadi : Int
        var string = str
        var rDadi = 0

        var temp = ""
        var res = ""
        var operatori : CharArray = CharArray(4)

        operatori.set(0, '+')
        operatori.set(1, '-')
        operatori.set(2, '*')
        operatori.set(3, '/')

        while (string.isNotEmpty())
        {
            if(string.contains('+') || string.contains('-') || string.contains('*') || string.contains('/')) {
                i = string.indexOfAny(operatori)

                temp = string.subSequence(0, i + 1).toString()

                if (temp.contains("d")) {

                    nDadi = temp.subSequence(0, temp.indexOf('d')).toString().toInt()
                    tDadi = temp.subSequence(temp.indexOf('d') + 1, temp.lastIndex).toString().toInt()

                    for (y in 0..nDadi) {
                        rDadi += (1..tDadi).random()
                    }
                    var op = temp[temp.lastIndex]
                    temp=rDadi.toString()
                    temp+=op
                }
                res += temp
                string=string.drop(i+1)
            }
            else
            {
                if (string.contains("d")) {

                    nDadi = string.subSequence(0, string.indexOf('d')).toString().toInt()
                    tDadi = string.subSequence(string.indexOf('d') + 1, string.lastIndex).toString().toInt()

                    for (y in 0..nDadi) {
                        rDadi += (1..tDadi).random()
                    }
                temp=rDadi.toString()
                }
                res += string
                string=string.drop(string.length)
            }
        }
        println(res)
        return res
    }
}