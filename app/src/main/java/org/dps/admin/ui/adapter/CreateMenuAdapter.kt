package org.dps.admin.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.adapter_create_menu.view.*
import org.dps.admin.R
import org.dps.admin.mvvm.CreateMenuModel
import org.dps.admin.ui.create.*
import org.dps.admin.utils.startNewActivity


class CreateMenuAdapter(var list: List<CreateMenuModel> = listOf()) :
    RecyclerView.Adapter<CreateMenuAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_create_menu, parent, false)
    )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bindItems(model: CreateMenuModel) {
            itemView.apply {
                img.setImageResource(model.thumbnail)
                txt.text = model.title
            }.setOnClickListener {
                when (model.id) {
                    1 -> {
                        it.context.startNewActivity(ShowStudentActivity::class.java)
                    }
                    3 -> {
                        it.context.startNewActivity(AssignTeacherActivity::class.java)
                    }
                    4 -> {
                        it.context.startNewActivity(CreateStudentActivity::class.java)
                    }
                    5 -> {
                        it.context.startNewActivity(CreateTeacherActivity::class.java)
                    }
                    6 -> {
                        it.context.startNewActivity(CreateClassActivity::class.java)
                    }
                }
            }
        }

    }
}