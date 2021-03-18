package org.dps.admin

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.dps.admin.utils.toast
import java.util.*


class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSpClass()

        val checkedRadioButtonId = radioGroup.checkedRadioButtonId // Returns View.NO_ID if nothing is checked.
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            // Responds to child RadioButton checked/unchecked
        }


// To listen for a radio button's checked/unchecked state changes
        radioGroup.setOnCheckedChangeListener { buttonView, isChecked ->
            // Responds to radio button being checked/unchecked
        }

        dobBtn.setOnClickListener {
            val calendar = Calendar.getInstance(Locale.getDefault())
            val datePickerDialog = DatePickerDialog(this,AlertDialog.THEME_HOLO_LIGHT,
                    { view, year, month, dayOfMonth ->
                        dobBtn.setText("$dayOfMonth/${month+1}/$year")
                    }, calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH])
            datePickerDialog.show()
        }
    }

    private fun setSpClass() {
        val classes = arrayOf("UKG", "LKG", "1st", "2nd", "3rd", "4th")
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, classes)
        sp_classes.setAdapter(adapter)
        sp_classes.onItemClickListener = AdapterView.OnItemClickListener { parent, arg1, position, id ->
            toast(classes[position])
            setSpSection()
        }
    }

    private fun setSpSection() {
        val section = arrayOf("A", "B", "C")
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, section)
        sp_section.setAdapter(adapter)
        sp_section.onItemClickListener = AdapterView.OnItemClickListener { parent, arg1, position, id ->
            toast(section[position])
        }
    }


}