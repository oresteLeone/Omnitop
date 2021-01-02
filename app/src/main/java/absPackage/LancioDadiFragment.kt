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
        
        //Numeri

        butt0.setOnClickListener(){appendOnExpression("0", true)}
        butt1.setOnClickListener(){appendOnExpression("1", true)}
        butt2.setOnClickListener(){appendOnExpression("2", true)}
        butt3.setOnClickListener(){appendOnExpression("3", true)}
        butt4.setOnClickListener(){appendOnExpression("4", true)}
        butt5.setOnClickListener(){appendOnExpression("5", true)}
        butt6.setOnClickListener(){appendOnExpression("6", true)}
        butt7.setOnClickListener(){appendOnExpression("7", true)}
        butt8.setOnClickListener(){appendOnExpression("8", true)}
        butt9.setOnClickListener(){appendOnExpression("9", true)}

        //Operatori

        plus.setOnClickListener(){appendOnExpression("+", false)}
        minus.setOnClickListener(){appendOnExpression("-", false)}
        multiply.setOnClickListener(){appendOnExpression("×", false)}
        divide.setOnClickListener(){appendOnExpression("÷", false)}
        openParentesis.setOnClickListener(){appendOnExpression("(", false)}
        closeParentesis.setOnClickListener(){appendOnExpression(")", false)}


        backspace.setOnClickListener(){
            val string = displayCalcolatrice.text.toString()
            if(string.isNotEmpty()){
                displayCalcolatrice.text = string.substring(0, string.length-1)
                risultatoTextView.text = ""



            }

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.lancio_dadi_layout2, container, false)
    }

    /******* METODI PER LE TEXT VIEW ED I PULSANTI ********/



    fun appendOnExpression (string: String, canClear : Boolean) {

        if(canClear)
        {
            risultatoTextView.text =""
            displayCalcolatrice.append(string)
        }
        else
        {
            displayCalcolatrice.append(risultatoTextView.text)
            displayCalcolatrice.append(string)
            risultatoTextView.text =""

        }

    }

    fun popolaListner(){
        /****** LISTNER PER I PULSANTI *******/

        //Numeri

        butt0.setOnClickListener(){appendOnExpression("0", true)}
        butt1.setOnClickListener(){appendOnExpression("1", true)}
        butt2.setOnClickListener(){appendOnExpression("2", true)}
        butt3.setOnClickListener(){appendOnExpression("3", true)}
        butt4.setOnClickListener(){appendOnExpression("4", true)}
        butt5.setOnClickListener(){appendOnExpression("5", true)}
        butt6.setOnClickListener(){appendOnExpression("6", true)}
        butt7.setOnClickListener(){appendOnExpression("7", true)}
        butt8.setOnClickListener(){appendOnExpression("8", true)}
        butt9.setOnClickListener(){appendOnExpression("9", true)}

        //Operatori

        plus.setOnClickListener(){appendOnExpression("+", false)}
        minus.setOnClickListener(){appendOnExpression("-", false)}
        multiply.setOnClickListener(){appendOnExpression("×", false)}
        divide.setOnClickListener(){appendOnExpression("÷", false)}
        openParentesis.setOnClickListener(){appendOnExpression("(", false)}
        closeParentesis.setOnClickListener(){appendOnExpression(")", false)}


        backspace.setOnClickListener(){
            val string = displayCalcolatrice.text.toString()
            if(string.isNotEmpty()){
                displayCalcolatrice.text = string.substring(0, string.length-1)
                risultatoTextView.text = ""



            }

        }
    }


}