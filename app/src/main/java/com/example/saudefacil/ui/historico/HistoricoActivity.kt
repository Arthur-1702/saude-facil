package com.example.saudefacil.ui.historico

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.saudefacil.R
import com.example.saudefacil.databinding.ActivityHistoricoBinding


data class HistoricoItem(val nome: String, val data: String)
class HistoricoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoricoBinding
    private lateinit var adapter: HistoricoAdapter

    private val historico = listOf(
        HistoricoItem(nome = "Dra. Daniele", data = "19.01.2024"),
        HistoricoItem(nome = "Dr. João", data = "25.01.2024"),
        HistoricoItem(nome = "Dr. Mario", data = "25.02.2024")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoricoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        adapter = HistoricoAdapter(historico)


        binding.recyclerViewHistorico.adapter = adapter
        binding.recyclerViewHistorico.layoutManager = LinearLayoutManager(this)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    inner class HistoricoAdapter(private val historicoList: List<HistoricoItem>) : RecyclerView.Adapter<HistoricoAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val textView = TextView(parent.context)
            return ViewHolder(textView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = historicoList[position]
            holder.bind(item)
        }

        override fun getItemCount(): Int {
            return historicoList.size
        }

       inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val textNome: TextView = itemView.findViewById(com.example.saudefacil.R.id.profissional)
            private val textData: TextView = itemView.findViewById(com.example.saudefacil.R.id.data)

           fun bind(historicoItem: HistoricoItem) {
               textNome.text = historicoItem.nome
               textData.text = historicoItem.data
           }
        }
    }
}



