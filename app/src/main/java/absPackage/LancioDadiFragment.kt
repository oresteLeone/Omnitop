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

        popolaListner()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.lancio_dadi_layout2, container, false)
    }

    /******* METODI PER LE TEXT VIEW ED I PULSANTI ********/



    fun appendOnExpression (string: String) {


            risultatoTextView.text =""
            displayCalcolatrice.append(string)

    }

    fun popolaListner(){
        /****** LISTNER PER I PULSANTI *******/

        //Numeri

        butt0.setOnClickListener(){appendOnExpression("0")}
        butt1.setOnClickListener(){appendOnExpression("1")}
        butt2.setOnClickListener(){appendOnExpression("2")}
        butt3.setOnClickListener(){appendOnExpression("3")}
        butt4.setOnClickListener(){appendOnExpression("4")}
        butt5.setOnClickListener(){appendOnExpression("5")}
        butt6.setOnClickListener(){appendOnExpression("6")}
        butt7.setOnClickListener(){appendOnExpression("7")}
        butt8.setOnClickListener(){appendOnExpression("8")}
        butt9.setOnClickListener(){appendOnExpression("9")}

        //Operatori

        plus.setOnClickListener(){appendOnExpression("+")}
        minus.setOnClickListener(){appendOnExpression("-")}
        multiply.setOnClickListener(){appendOnExpression("×")}
        divide.setOnClickListener(){appendOnExpression("÷")}
        openParentesis.setOnClickListener(){appendOnExpression("(")}
        closeParentesis.setOnClickListener(){appendOnExpression(")")}


        backspace.setOnClickListener(){
            val string = displayCalcolatrice.text.toString()
            if(string.isNotEmpty()){
                displayCalcolatrice.text = string.substring(0, string.length-1)
                risultatoTextView.text = ""



            }

        }
    }

}