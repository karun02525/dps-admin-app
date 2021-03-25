package org.dps.admin.ui.fragments

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_create_student.*
import kotlinx.android.synthetic.main.fragment_create_student.btnSubmit
import kotlinx.android.synthetic.main.fragment_create_student.progress_circular
import kotlinx.android.synthetic.main.fragment_create_student.sp_classes
import org.dps.admin.R
import org.dps.admin.model.DataClasses
import org.dps.admin.mvvm.ClassViewModel
import org.dps.admin.utils.hideSoftKeyboard
import org.dps.admin.utils.messToast
import org.dps.admin.utils.toast
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class CreateStudentFragment : Fragment() {

    private val viewModel: ClassViewModel by viewModel()
    private var class_id = ""
    private var dob = ""
    private var postOffice = ""
    private var policeStation = ""
    private var distc = ""
    private var state = ""
    private var country = ""
    private var pincode = ""
    private var gender = "Male"


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_student, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideShowProgress(true)
        setupViewModel()

        setUpSpinner()

        btnSubmit.setOnClickListener {
            isCheckUI()
            //hideShowProgress(true)
            // viewModel.assignTeacherAsync(class_id,sectionName,teacher_id)
        }

        dobBtn.setOnClickListener {
            val calendar = Calendar.getInstance(Locale.getDefault())
            val datePickerDialog = DatePickerDialog(requireContext(), AlertDialog.THEME_HOLO_LIGHT,
                    { _, year, month, dayOfMonth ->
                        dob = "$dayOfMonth/${month + 1}/$year"
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
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, list)
        sp_postoffice.setAdapter(adapter)
        sp_postoffice.onItemClickListener =
                AdapterView.OnItemClickListener { _, _, position, _ ->
                    postOffice = list[position]
                }
        //----------Police Station
        val listPolice = arrayOf("Kudra", "Bhabua", "Mohania", "Nuwo")
        val adapterP = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, listPolice)
        sp_police_station.setAdapter(adapterP)
        sp_police_station.onItemClickListener =
                AdapterView.OnItemClickListener { _, _, position, _ ->
                    policeStation = listPolice[position]
                }

        //----------Dist
        val listDist = arrayOf("Kaimur(Bhuabua)", "Rohtash", "Buxer", "Siwan")
        val adapterD = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, listDist)
        sp_dist.setAdapter(adapterD)
        sp_dist.onItemClickListener =
                AdapterView.OnItemClickListener { _, _, position, _ ->
                    distc = listDist[position]
                }

        //----------State---
        val listState = arrayOf("Kaimur(Bhuabua)", "Rohtash", "Buxer", "Siwan")
        val adapterS = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, listState)
        sp_state.setAdapter(adapterS)
        sp_state.onItemClickListener =
                AdapterView.OnItemClickListener { _, _, position, _ ->
                    state = listState[position]
                }

        //----------Country---
        val listCountry = arrayOf("India", "USA")
        val adapterC = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, listCountry)
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
        val rollno = edit_rollno.text.toString()
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

        when {
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
            rollno.isEmpty() -> {
                mess("Please enter roll no")
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
            else -> {
                mess("Done")
            }
        }
    }

    private fun mess(s: String) {
        context?.hideSoftKeyboard()
        context?.messToast(s)
    }

    private fun setupViewModel() {
        viewModel.classData.observe(requireActivity(), Observer {
            hideShowProgress(false)
            setSpClass(it)
        })
        viewModel.msg.observe(requireActivity(), Observer {
            hideShowProgress(false)
            context?.toast(it)
        })
    }

    private fun setSpClass(list: List<DataClasses>) {
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp_classes.setAdapter(adapter)

        sp_classes.onItemClickListener =
                AdapterView.OnItemClickListener { parent, _, position, _ ->
                    val data: DataClasses = parent.adapter.getItem(position) as DataClasses
                    class_id = data.id.toString()
                }

    }

    private fun hideShowProgress(flag: Boolean) {
        if (flag) progress_circular.visibility = View.VISIBLE else progress_circular.visibility =
                View.GONE
    }
}