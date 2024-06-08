package com.example.saudefacil.ui.profissional

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.saudefacil.databinding.ActivityProfissionalDetalheBinding
import android.widget.TextView

class ProfissionalDetalheActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfissionalDetalheBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfissionalDetalheBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve the data from the intent
        val professionalName = intent.getStringExtra("professional_name")
        val professionalCity = intent.getStringExtra("professional_city")
        val professionalSpecialty = intent.getStringExtra("professional_specialty")
        val professionalAddress = intent.getStringExtra("professional_address")
        val professionalPhone = intent.getStringExtra("professional_phone")

        // Encontre os TextViews no layout
        binding.nomeProf.text = professionalName ?: "N/A"
        binding.cidade.text = professionalCity ?: "N/A"
        binding.especialidade.text = professionalSpecialty ?: "N/A"
        binding.endereco.text = professionalAddress ?: "N/A"
        binding.telefone.text = professionalPhone ?: "N/A"
    }
}
