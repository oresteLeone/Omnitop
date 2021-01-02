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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /****** LISTNER PER I PULSANTI *******/

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        popolaListner()

        return inflater.inflate(R.layout.lancio_dadi_layout2, container, false)
    }

    /******* METODI PER LE TEXT VIEW ED I PULSANTI ********/



    fun appendOnExpression (string: String) {


            risultatoTextView.text =""
            displayCalcolatrice.append(string)

    }

    fun popolaListner(){
        /****** LISTNER PER I PULSANTI *******/
        var btn0 : Button = butt0
        var btn1 : Button = butt1
        var btn2 : Button = butt2
        var btn3 : Button = butt3
        var btn4 : Button = butt4
        var btn5 : Button = butt5
        var btn6 : Button = butt6
        var btn7 : Button = butt7
        var btn8 : Button = butt8
        var btn9 : Button = butt9

        var btnplus : Button = plus
        var btnminus : Button = minus
        var btnmultiply : Button = multiply
        var btndivide : Button = divide
        var btnopenParentesis : Button = openParentesis
        var btncloseParentesis : Button = closeParentesis
        var btnbackspace : Button = backspace
        //Numeri

        btn0.setOnClickListener(){appendOnExpression("0")}
        btn1.setOnClickListener(){appendOnExpression("1")}
        btn2.setOnClickListener(){appendOnExpression("2")}
        btn3.setOnClickListener(){appendOnExpression("3")}
        btn4.setOnClickListener(){appendOnExpression("4")}
        btn5.setOnClickListener(){appendOnExpression("5")}
        btn6.setOnClickListener(){appendOnExpression("6")}
        btn7.setOnClickListener(){appendOnExpression("7")}
        btn8.setOnClickListener(){appendOnExpression("8")}
        btn9.setOnClickListener(){appendOnExpression("9")}

        //Operatori

        btnplus.setOnClickListener(){appendOnExpression("+")}
        btnminus.setOnClickListener(){appendOnExpression("-")}
        btnmultiply.setOnClickListener(){appendOnExpression("×")}
        btndivide.setOnClickListener(){appendOnExpression("÷")}
        btnopenParentesis.setOnClickListener(){appendOnExpression("(")}
        btncloseParentesis.setOnClickListener(){appendOnExpression(")")}


        btnbackspace.setOnClickListener(){
            val string = displayCalcolatrice.text.toString()
            if(string.isNotEmpty()){
                displayCalcolatrice.text = string.substring(0, string.length-1)
                risultatoTextView.text = ""



            }

        }
    }

}