package org.dps.admin.ui.create

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_create_student.*
import org.dps.admin.R
import org.dps.admin.model.DataClasses
import org.dps.admin.model.ParentData
import org.dps.admin.mvvm.ClassViewModel
import org.dps.admin.utils.hideSoftKeyboard
import org.dps.admin.utils.messToast
import org.dps.admin.utils.toast
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.collections.HashMap

class CreateStudentActivity : AppCompatActivity() {
    private val viewModel: ClassViewModel by viewModel()
    private var class_id = ""
    private var parent_id = ""
    private var className = ""
    private var dob = ""
    private var postOffice = ""
    private var policeStation = ""
    private var distc = ""
    private var state = ""
    private var country = ""
    private var gender = "Male"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_student)
        hideShowProgress(true)
        setupViewModel()

        setUpSpinner()
        viewModel.getParents()
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

    private fun setUpSpinner() {
        //----------PostOffice
        val list = arrayOf("Fakhrabad", "Kudra", "Mohania")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
        sp_postoffice.setAdapter(adapter)
        sp_postoffice.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                postOffice = list[position]
            }
        //----------Police Station
        val listPolice = arrayOf("Kudra", "Bhabua", "Mohania", "Nuwo")
        val adapterP = ArrayAdapter(this, android.R.layout.simple_spinner_item, listPolice)
        sp_police_station.setAdapter(adapterP)
        sp_police_station.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                policeStation = listPolice[position]
            }

        //----------Dist
        val listDist = arrayOf("Kaimur(Bhuabua)", "Rohtash", "Buxer", "Siwan")
        val adapterD = ArrayAdapter(this, android.R.layout.simple_spinner_item, listDist)
        sp_dist.setAdapter(adapterD)
        sp_dist.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                distc = listDist[position]
            }

        //----------State---
        val listState = arrayOf("Bihar", "UP")
        val adapterS = ArrayAdapter(this, android.R.layout.simple_spinner_item, listState)
        sp_state.setAdapter(adapterS)
        sp_state.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                state = listState[position]
            }

        //----------Country---
        val listCountry = arrayOf("India", "USA")
        val adapterC = ArrayAdapter(this, android.R.layout.simple_spinner_item, listCountry)
        sp_country.setAdapter(adapterC)
        sp_country.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                country = listCountry[position]
            }


    }

    private fun isCheckUI() {
        val fname = edit_fname.text.toString()
        val lname = edit_lname.text.toString()
        val sname = edit_sname.text.toString()
        val mobile = edit_mobile.text.toString()
        val email = edit_email.text.toString()
        val address = edit_address.text.toString()
        val pincode = edit_pincode.text.toString()
        val fatherFname = edit_father_fname.text.toString()
        val fatherLname = edit_father_lname.text.toString()
        val fatherSname = edit_father_sname.text.toString()

        val motherFname = edit_mother_fname.text.toString()
        val motherLname = edit_mother_lname.text.toString()
        val motherSname = edit_mother_sname.text.toString()
        val parentPhone = edit_parent_phone.text.toString()
        val parentOccupation = edit_parent_occupation.text.toString()

        when {
            parent_id.isBlank() -> {
                mess("Please select Parent")
            }
            class_id.isBlank() -> {
                mess("Please select class")
            }
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
            mobile.isEmpty() -> {
                mess("Please enter mobile no")
            }
            email.isEmpty() -> {
                mess("Please enter email id")
            }
            address.isEmpty() -> {
                mess("Please enter address")
            }
            postOffice.isEmpty() -> {
                mess("Please enter post office")
            }
            policeStation.isEmpty() -> {
                mess("Please enter police station")
            }
            distc.isEmpty() -> {
                mess("Please enter district")
            }
            state.isEmpty() -> {
                mess("Please enter state")
            }
            country.isEmpty() -> {
                mess("Please enter country")
            }
            pincode.isEmpty() -> {
                mess("Please enter valid pin code")
            }
            fatherFname.isEmpty() -> {
                mess("Please enter father first name")
            }
            fatherLname.isEmpty() -> {
                mess("Please enter father last name")
            }
            fatherSname.isEmpty() -> {
                mess("Please enter father surname")
            }
            motherFname.isEmpty() -> {
                mess("Please enter mother first name")
            }
            motherLname.isEmpty() -> {
                mess("Please enter mother last name")
            }
            motherSname.isEmpty() -> {
                mess("Please enter mother surname")
            }
            parentPhone.isEmpty() -> {
                mess("Please enter parent phone")
            }
            parentOccupation.isEmpty() -> {
                mess("Please enter occupation")
            }
            else -> {
                val p= HashMap<String, Any>()
                p["class_id"]=class_id
                p["fname"]=fname.trim()
                p["lname"]=lname
                p["surname"]=sname
                p["gender"]=gender
                p["dob"]=dob
                p["phone"]=mobile
                p["email"]=email
                p["address"]=address
                p["post_office"]=postOffice
                p["police_station"]=policeStation
                p["dist"]=distc
                p["state"]=state
                p["country"]=country
                p["pincode"]=pincode
                p["father_fname"]=fatherFname
                p["father_lname"]=fatherLname
                p["father_sname"]=fatherSname
                p["mother_fname"]=motherFname
                p["mother_lname"]=motherLname
                p["mother_sname"]=motherSname
                p["parent_phone"]=parentPhone
                p["parent_occupation"]=parentOccupation
                p["parent_id"]=parent_id
                p["student_picture"]="avatar"
                hideShowProgress(true)
                viewModel.createStudentAsync(p)
            }
        }
    }

    private fun mess(s: String) {
      hideSoftKeyboard()
      messToast(s)
    }

    private fun setupViewModel() {
        viewModel.classData.observe(this, Observer {
            hideShowProgress(false)
            setSpClass(it)
        })
        viewModel.parentsData.observe(this, Observer {
            hideShowProgress(false)
            setSpParent(it)
        })
        viewModel.success.observe(this, Observer {
            hideShowProgress(false)
            toast(it)
        })
        viewModel.msg.observe(this, Observer {
            hideShowProgress(false)
            toast(it)
        })
    }

    private fun setSpClass(list: List<DataClasses>) {
        //Class dropdown
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp_classes.setAdapter(adapter)

        sp_classes.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                val data: DataClasses = parent.adapter.getItem(position) as DataClasses
                class_id = data.id.toString()
                className = data.classname.toString()
            }
    }

    private fun setSpParent(list: List<ParentData>) {
        //parents dropdown
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp_parent.setAdapter(adapter)

        sp_parent.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                val data: ParentData = parent.adapter.getItem(position) as ParentData
                parent_id = data.parentId.toString()
               // className = data.classname.toString()
            }
    }

    private fun hideShowProgress(flag: Boolean) {
        if (flag) progress_circular.visibility = View.VISIBLE else progress_circular.visibility =
            View.GONE
    }
}