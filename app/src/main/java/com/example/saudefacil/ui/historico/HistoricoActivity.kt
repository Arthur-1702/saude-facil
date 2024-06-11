package com.example.saudefacil.ui.historico


import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.saudefacil.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.saudefacil.R.layout.activity_profissional)

        val professionalList = findViewById<LinearLayout>(R.id.professional_list)

        // Dados fictícios para preencher a lista
        val professionals = listOf(
            "PROFISSIONAL" to "12/11/2011",
            "PROFISSIONAL" to "11/11/2011",
            "PROFISSIONAL" to "20/11/2011",
            "PROFISSIONAL" to "20/11/2011"
        )

        // Preenchendo a lista dinamicamente
        professionals.forEach { (name, date) ->
            val itemView = layoutInflater.inflate(R.layout.professional_item, professionalList, false)

            val nameTextView = itemView.findViewById<TextView>(R.id.professional_name)
            val dateTextView = itemView.findViewById<TextView>(R.id.professional_date)

            nameTextView.text = name
            dateTextView.text = date

            professionalList.addView(itemView)
        }
    }
}


