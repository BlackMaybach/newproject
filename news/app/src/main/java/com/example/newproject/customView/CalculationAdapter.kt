package com.example.newproject.customView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newproject.R
import com.example.newproject.ui.api.models.creditCalculator.GetCalculationItem

class CalculationAdapter(private val array:ArrayList<GetCalculationItem>): RecyclerView.Adapter<CalculationAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val date: TextView = view.findViewById(R.id.date)
        val amount: TextView = view.findViewById(R.id.amount)
        val rate: TextView = view.findViewById(R.id.rate)
        val mainAmount: TextView = view.findViewById(R.id.mainAmount)
        val remainMainDebt: TextView = view.findViewById(R.id.remainMainDebt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val viewProducts = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_calculation, parent, false )
        return ViewHolder(viewProducts)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = array[position]
        holder.date.text = item.date
        holder.amount.text = item.amount.toString()
        holder.rate.text = item.rate.toString()
        holder.mainAmount.text = item.mainAmount.toString()
        holder.remainMainDebt.text = item.remainMainDebt.toString()
    }

    override fun getItemCount(): Int {
        return array.size
    }
}