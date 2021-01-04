package absPackage

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.annoyingturtle.omnitop.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.lancio_dadi_layout2.*

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


        risultatoTextView.text = ""
        displayCalcolatrice.append(string)

    }

    fun popolaListner() {
        /****** LISTNER PER I PULSANTI *******/
        val btn0: Button = butt0
        val btn1: Button = butt1
        val btn2: Button = butt2
        val btn3: Button = butt3
        val btn4: Button = butt4
        val btn5: Button = butt5
        val btn6: Button = butt6
        val btn7: Button = butt7
        val btn8: Button = butt8
        val btn9: Button = butt9

        val btnplus: Button = plus
        val btnminus: Button = minus
        val btnmultiply: Button = multiply
        val btndivide: Button = divide
        val btnopenParentesis: Button = openParentesis
        val btncloseParentesis: Button = closeParentesis
        val btnbackspace: Button = backspace
        //Numeri

        btn0.setOnClickListener { appendOnExpression("0") }
        btn1.setOnClickListener { appendOnExpression("1") }
        btn2.setOnClickListener { appendOnExpression("2") }
        btn3.setOnClickListener { appendOnExpression("3") }
        btn4.setOnClickListener { appendOnExpression("4") }
        btn5.setOnClickListener { appendOnExpression("5") }
        btn6.setOnClickListener { appendOnExpression("6") }
        btn7.setOnClickListener { appendOnExpression("7") }
        btn8.setOnClickListener { appendOnExpression("8") }
        btn9.setOnClickListener { appendOnExpression("9") }

        //Operatori

        btnplus.setOnClickListener { appendOnExpression("+") }
        btnminus.setOnClickListener { appendOnExpression("-") }
        btnmultiply.setOnClickListener { appendOnExpression("×") }
        btndivide.setOnClickListener { appendOnExpression("÷") }
        btnopenParentesis.setOnClickListener { appendOnExpression("(") }
        btncloseParentesis.setOnClickListener { appendOnExpression(")") }


        btnbackspace.setOnClickListener {
            val string = displayCalcolatrice.text.toString()
            if (string.isNotEmpty()) {
                displayCalcolatrice.text = string.substring(0, string.length - 1)
                risultatoTextView.text = ""


            }

        }
    }

}