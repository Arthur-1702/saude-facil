package com.example.saudefacil.ui.historico
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.saudefacil.R
import com.example.saudefacil.R.id.professional_list
import com.example.saudefacil.R.layout
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_profissional)

        val professionalList = findViewById<LinearLayout>(professional_list)

        // Dados fictícios para preencher a lista
        val professionals = listOf(
            "PROFISSIONAL" to "12/11/2011",
            "PROFISSIONAL" to "11/11/2011",
            "PROFISSIONAL" to "20/11/2011",
            "PROFISSIONAL" to "20/11/2011"
        )

        // Preenchendo a lista dinamicamente
        professionals.forEach { (name, date) ->
            val itemView = layoutInflater.inflate(layout.professionalItem, professionalList, false)

            val nameTextView = itemView.findViewById<TextView>(com.example.saudefacil.R.id.professional_name)
            val dateTextView = itemView.findViewById<TextView>(com.example.saudefacil.R.id.professional_date)

            nameTextView.text = name
            dateTextView.text = date

            professionalList.addView(itemView)
        }
    }
}


