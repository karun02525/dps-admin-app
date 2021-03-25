package org.dps.admin.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_assign_teacher.*
import org.dps.admin.R
import org.dps.admin.model.DataClasses
import org.dps.admin.model.TeacherData
import org.dps.admin.mvvm.ClassViewModel
import org.dps.admin.utils.toast
import org.koin.android.viewmodel.ext.android.viewModel

class AssignTeacherFragment : Fragment() {
    private val viewModel: ClassViewModel by viewModel()
    private var class_id = ""
    private var teacher_id = ""
    private var sectionName = ""
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       return inflater.inflate(R.layout.fragment_assign_teacher, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideShowProgress(true)
        setupViewModel()



        btnSubmit.setOnClickListener {
            hideShowProgress(true)
            viewModel.assignTeacherAsync(class_id,sectionName,teacher_id)
        }
    }

    private fun setupViewModel() {
        viewModel.classData.observe(requireActivity(), Observer {
            hideShowProgress(false)
            setSpClass(it)
        })
        viewModel.teacherList.observe(requireActivity(), Observer {
            hideShowProgress(false)
            parseTeacher(it)
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
                setSpSection(data.section)
            }

    }
    private fun parseTeacher(list: List<TeacherData>) {
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp_teacher.setAdapter(adapter)

        sp_teacher.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                val data: TeacherData = parent.adapter.getItem(position) as TeacherData
                teacher_id = data.id.toString()
            }

    }

    private fun setSpSection(section: List<String>) {
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, section)
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