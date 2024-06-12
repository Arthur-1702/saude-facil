package com.example.saudefacil.ui.agenda

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.saudefacil.R
import com.example.saudefacil.model.AgendaItem

class AgendaAdapter(private val agendaList: List<AgendaItem>) :
    RecyclerView.Adapter<AgendaAdapter.AgendaViewHolder>() {

    class AgendaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profissionalTextView: TextView = itemView.findViewById(R.id.tv_profissional)
        val dataTextView: TextView = itemView.findViewById(R.id.tv_data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgendaViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_agenda, parent, false)
        return AgendaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AgendaViewHolder, position: Int) {
        val currentItem = agendaList[position]
        holder.profissionalTextView.text = currentItem.profissional
        holder.dataTextView.text = currentItem.data
    }

    override fun getItemCount() = agendaList.size
}
