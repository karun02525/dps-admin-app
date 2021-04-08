package org.dps.admin.ui.create

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_create_student.*
import org.dps.admin.R
import org.dps.admin.mvvm.ClassViewModel
import org.dps.admin.utils.hideSoftKeyboard
import org.dps.admin.utils.messToast
import org.dps.admin.utils.toast
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.collections.HashMap

class CreateParentActivity : AppCompatActivity() {
    private val viewModel: ClassViewModel by viewModel()
    private var dob = ""
    private var gender = "Male"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_parent)
        setupViewModel()

        btnSubmit.setOnClickListener {
            isCheckUI()
        }

        dobBtn.setOnClickListener {
            val calendar = Calendar.getInstance(Locale.getDefault())
            val datePickerDialog = DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT,
                { _, year, month, dayOfMonth ->
                    dob = "$dayOfMonth-${month + 1}-$year"
                    dobBtn.setText(dob)
                }, calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH])
            datePickerDialog.show()
        }

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radio: RadioButton = group.findViewById(checkedId)
            gender = radio.text.toString()
        }

    }


    private fun isCheckUI() {
        val fname = edit_fname.text.toString()
        val lname = edit_lname.text.toString()
        val sname = edit_sname.text.toString()

        when {
            fname.isBlank() -> {
                mess("Please enter first name")
            }
            lname.isBlank() -> {
                mess("Please enter last name")
            }
            sname.isBlank() -> {
                mess("Please enter surname")
            }
            dob.isEmpty() -> {
                mess("Please select date of birth")
            }
            else -> {
                val p= HashMap<String, Any>()
                p["fname"]=fname.trim()
                p["lname"]=lname
                p["surname"]=sname
                p["gender"]=gender
                p["dob"]=dob
                hideShowProgress(true)
                viewModel.createParent(p)
            }
        }
    }

    private fun mess(s: String) {
      hideSoftKeyboard()
      messToast(s)
    }

    private fun setupViewModel() {
        viewModel.success.observe(this, Observer {
            hideShowProgress(false)
            toast(it)
        })
        viewModel.msg.observe(this, Observer {
            hideShowProgress(false)
            toast(it)
        })
    }


    private fun hideShowProgress(flag: Boolean) {
        if (flag) progress_circular.visibility = View.VISIBLE else progress_circular.visibility =
            View.GONE
    }
}