package com.example.saudefacil.ui.profissional

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.saudefacil.model.ProfissionalItem

class ProfissionalAdapter(
    private var profissionais: List<ProfissionalItem>,
    private val onItemClick: (ProfissionalItem) -> Unit
) : RecyclerView.Adapter<ProfissionalAdapter.ProfissionalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfissionalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(com.example.saudefacil.R.layout.item_profissional, parent, false)
        return ProfissionalViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfissionalViewHolder, position: Int) {
        val profissional = profissionais[position]
        holder.bind(profissional)
        holder.itemView.setOnClickListener { onItemClick(profissional) }
    }

    override fun getItemCount(): Int = profissionais.size

    fun updateList(newList: List<ProfissionalItem>) {
        profissionais = newList
        notifyDataSetChanged()
    }

    class ProfissionalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(com.example.saudefacil.R.id.nomeProfissional)

        fun bind(professional: ProfissionalItem) {
            nameTextView.text = professional.nome
        }
    }
}
