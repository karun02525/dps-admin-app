package org.dps.admin.ui.create

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_assign_section.*
import org.dps.admin.R
import org.dps.admin.model.DataClasses
import org.dps.admin.model.StudentData
import org.dps.admin.mvvm.ClassViewModel
import org.dps.admin.utils.toast
import org.koin.android.viewmodel.ext.android.viewModel

class AssignSectionActivity : AppCompatActivity() {
    private val viewModel: ClassViewModel by viewModel()
    private var class_id = ""
    private var student_id = ""
    private var sectionName = ""
    private var className = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assign_section)
        hideShowProgress(true)
        setupViewModel()
        
        btnSubmit.setOnClickListener {
            hideShowProgress(true)
            viewModel.assignSectionAsync(class_id,className,student_id,sectionName)
        }
    }

    private fun setupViewModel() {
        viewModel.classData.observe(this, Observer {
            hideShowProgress(false)
            setSpClass(it)
        })
        viewModel.studentsList.observe(this, Observer {
            hideShowProgress(false)
            parseStudent(it)
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
                className = data.classname.toString()
                viewModel.getStudentDataAsync(class_id)
                setSpSection(data.section)
            }

    }
    private fun parseStudent(list: List<StudentData>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp_student.setAdapter(adapter)

        sp_student.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                val data: StudentData = parent.adapter.getItem(position) as StudentData
                student_id = data.id.toString()
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