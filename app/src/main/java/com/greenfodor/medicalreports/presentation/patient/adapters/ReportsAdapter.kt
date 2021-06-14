package com.greenfodor.medicalreports.presentation.patient.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.greenfodor.medicalreports.R
import com.greenfodor.medicalreports.databinding.ItemReportBinding
import com.greenfodor.medicalreports.model.responses.GetPatientReportsResponse

typealias OnReportClickListener = (GetPatientReportsResponse) -> Unit

class ReportsAdapter(private val listener: OnReportClickListener) : ListAdapter<GetPatientReportsResponse, ReportsAdapter.ReportsViewHolder>
    (object : DiffUtil.ItemCallback<GetPatientReportsResponse>() {
    override fun areItemsTheSame(oldItem: GetPatientReportsResponse, newItem: GetPatientReportsResponse) = oldItem.reportNo == newItem.reportNo


    override fun areContentsTheSame(oldItem: GetPatientReportsResponse, newItem: GetPatientReportsResponse) =
        oldItem.reportNo == newItem.reportNo && oldItem.authorName == newItem.authorName
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ReportsViewHolder(ItemReportBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: ReportsViewHolder, position: Int) {
        if (position == RecyclerView.NO_POSITION) return
        holder.bind(getItem(position))
    }

    inner class ReportsViewHolder(private val binding: ItemReportBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                if (bindingAdapterPosition == RecyclerView.NO_POSITION) return@setOnClickListener
                listener.invoke(getItem(bindingAdapterPosition))
            }
        }

        fun bind(report: GetPatientReportsResponse) {
            binding.apply {
                reportNoTv.text = root.context.getString(R.string.report_no, report.reportNo)
                authorNameTv.text = root.context.getString(R.string.author_name, report.authorName)
            }
        }
    }
}