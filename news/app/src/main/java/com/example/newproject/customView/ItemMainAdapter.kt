package com.example.newproject.customView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newproject.R
import com.example.newproject.databinding.ItemMainBinding
import com.example.newproject.ui.api.models.creditTest.creditTest

class ItemMainAdapter() : RecyclerView.Adapter<ItemMainAdapter.ViewHolder>() {

    private var items: List<creditTest> = listOf()

    inner class ViewHolder(val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemMainBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_main,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val current = items[position]
        holder.binding.let {
            it.testDebt.text = current.debt.toString()
            it.testPercent.text = current.percent.toString()
            it.testSum.text = current.sum.toString()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setList(items: List<creditTest>) {
        this.items = items
    }
}