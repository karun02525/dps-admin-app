package org.dps.admin.ui.create

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_assign_teacher.btnSubmit
import kotlinx.android.synthetic.main.activity_assign_teacher.progress_circular
import kotlinx.android.synthetic.main.activity_assign_teacher.sp_classes
import kotlinx.android.synthetic.main.activity_assign_teacher.sp_section
import kotlinx.android.synthetic.main.activity_show_student.*
import kotlinx.android.synthetic.main.custom_toolbar.*
import org.dps.admin.R
import org.dps.admin.model.DataClasses
import org.dps.admin.mvvm.ClassViewModel
import org.dps.admin.mvvm.CreateMenuModel
import org.dps.admin.ui.adapter.ShowStudentsAdapter
import org.dps.admin.utils.toast
import org.koin.android.viewmodel.ext.android.viewModel

class ShowStudentActivity : AppCompatActivity() {
    private val viewModel: ClassViewModel by viewModel()
    private val mAdapter by lazy { ShowStudentsAdapter() }

    private var class_id = ""
    private var sectionName = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_student)
        hideShowProgress(true)
        setupViewModel()


        btnSubmit.setOnClickListener {
            hideShowProgress(true)
           // viewModel.assignTeacherAsync(class_id,sectionName)
        }



        btn_back.setOnClickListener { onBackPressed() }

        tv_toolbar.text="List of Students"

        val list: ArrayList<CreateMenuModel> = arrayListOf()
        list.run {
            add(CreateMenuModel(1, "See Students", R.drawable.ic_user))
            add(CreateMenuModel(2, "See Teacher", R.drawable.ic_open_book))
            add(CreateMenuModel(3, "Assign Teacher", R.drawable.ic_noticeboard))
            add(CreateMenuModel(4, "Create Student", R.drawable.ic_attendance))
            add(CreateMenuModel(5, "Create Teacher", R.drawable.ic_report))
            add(CreateMenuModel(6, "Create Class", R.drawable.ic_wallet))
        }
        mAdapter.list = list
        rv_show_students.adapter = mAdapter
        mAdapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        viewModel.classData.observe(this, Observer {
            hideShowProgress(false)
            setSpClass(it)
        })
        viewModel.msg.observe(this, Observer {
            hideShowProgress(false)
           toast(it)
        })
    }

    private fun setSpClass(list: List<DataClasses>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp_classes.setAdapter(adapter)

        sp_classes.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                val data: DataClasses = parent.adapter.getItem(position) as DataClasses
                class_id = data.id.toString()
                setSpSection(data.section)
            }
    }

    private fun setSpSection(section: List<String>) {
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, section)
        sp_section.setAdapter(adapter)
        sp_section.onItemClickListener =
            AdapterView.OnItemClickListener { parent, arg1, position, id ->
                sectionName = section[position]
            }
    }

    private fun hideShowProgress(flag: Boolean) {
        if (flag) progress_circular.visibility = View.VISIBLE else progress_circular.visibility =
            View.GONE
    }
}