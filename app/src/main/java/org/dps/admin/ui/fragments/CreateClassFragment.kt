package org.dps.admin.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_create_classes.*
import org.dps.admin.R
import org.dps.admin.mvvm.ClassViewModel
import org.dps.admin.utils.toast
import org.koin.android.viewmodel.ext.android.viewModel

class CreateClassFragment : Fragment() {

    private val viewModel: ClassViewModel by viewModel()
    var sectionArray = mutableListOf<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       return inflater.inflate(R.layout.fragment_create_classes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpUI()
        setupViewModel()
    }

    private fun setUpUI() {
        checkboxA.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                sectionArray.add("A")
            } else {
                sectionArray.remove("A")
            }
        }
        checkboxB.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                sectionArray.add("B")
            } else {
                sectionArray.remove("B")
            }
        }
        checkboxC.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                sectionArray.add("C")
            } else {
                sectionArray.remove("C")
            }
        }
        checkboxD.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                sectionArray.add("D")
            } else {
                sectionArray.remove("D")
            }
        }


        btnSubmit.setOnClickListener {
            val msg = edit_classes.text.toString()
            when {
                msg.isEmpty() -> {
                    context?.toast("Please enter classes")
                }
                sectionArray.isEmpty() -> {
                    context?.toast("Please select one section")
                }
                else -> {
                    hideShowProgress(true)
                    viewModel.createClass(msg, sectionArray)
                }
            }
        }
    }

    private fun setupViewModel() {
        viewModel.msg.observe(requireActivity(), Observer {
            hideShowProgress(false)
            clearField()
            context?.toast(it)
        })
    }

    fun clearField(){
        edit_classes.text?.clear()
        sectionArray.clear()
        checkboxA.isChecked=false
        checkboxB.isChecked=false
        checkboxC.isChecked=false
        checkboxD.isChecked=false
    }

    private fun hideShowProgress(flag: Boolean) {
        if (flag) progress_circular.visibility = View.VISIBLE else progress_circular.visibility = View.GONE
    }

}
