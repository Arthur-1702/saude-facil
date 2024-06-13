package com.example.saudefacil.ui.laboratorio

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.saudefacil.R
import com.example.saudefacil.databinding.ActivityLaboratorioDetalheBinding

class LaboratorioDetalheActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLaboratorioDetalheBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_laboratorio_detalhe)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityLaboratorioDetalheBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Retrieve the data from the intent
        val laboratorioName = intent.getStringExtra("laboratorio_name")
        val laboratorioCep = intent.getStringExtra("laboratorio_cep")
        val laboratorioAddress = intent.getStringExtra("laboratorio_address")
        val laboratorioPhone = intent.getStringExtra("laboratorio_phone")
        val laboratorioFuncionamento = intent.getStringExtra("laboratorio_funcionamento")

        // Encontre os TextViews no layout
        binding.nomeLab.text = laboratorioName ?: "N/A"
        binding.cep.text = laboratorioCep ?: "N/A"
        binding.enderecoLab.text = laboratorioAddress ?: "N/A"
        binding.telefoneLab.text = laboratorioPhone ?: "N/A"
        binding.funcionamento.text = laboratorioFuncionamento ?: "N/A"

        binding.voltar.setOnClickListener {
            finish()
        }
    }
}