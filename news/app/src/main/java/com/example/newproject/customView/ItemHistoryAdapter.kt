package com.example.newproject.customView

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ScrollCaptureCallback
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newproject.R
import com.example.newproject.databinding.ItemHistoryBinding
import com.example.newproject.ui.api.models.creditTest.creditTestHisory

class ItemHistoryAdapter(private var listener: OnClick) :
    RecyclerView.Adapter<ItemHistoryAdapter.ViewHolder>() {

    private var items: List<creditTestHisory> = listOf()


    inner class ViewHolder(val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemHistoryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_history,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val current = items[position]

        holder.binding.let {
            it.testDate.text = current.date
            it.testStatus.text = current.status
            it.testProduct.text = current.product
            it.testSum.text = current.sum.toString()
            it.testPercent.text = current.percent
            it.testTime.text = current.time
            when (current.status) {
                "На рассмотрении" -> {
                    it.testStatus.setTextColor(Color.parseColor("#8D8D8D"))
                }
                "Погашен" -> {
                    it.testStatus.setTextColor(Color.parseColor("#F2A900"))
                }
                "Отклонён" -> {
                    it.testStatus.setTextColor(Color.parseColor("#B23939"))
                }
                else -> {}
            }
        }

        holder.itemView.setOnClickListener {
            listener.onClicked(holder, position)
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    interface OnClick {
        fun onClicked(holder: ViewHolder, position: Int)
    }

    fun getItem(position: Int): creditTestHisory? {
        return if (position != null) {
            items[position]
        } else
            null
    }

    fun setList(items: List<creditTestHisory>) {
        this.items = items
    }


}