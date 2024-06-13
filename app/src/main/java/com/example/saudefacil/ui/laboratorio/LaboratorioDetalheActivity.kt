package com.example.saudefacil.ui.laboratorio

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.saudefacil.R
import com.google.firebase.firestore.FirebaseFirestore


class LaboratorioDetalheActivity : AppCompatActivity() {
    private lateinit var firestore: FirebaseFirestore
    private var labEndereco: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laboratorio_detalhe)

        firestore = FirebaseFirestore.getInstance()

        val labId = intent.getStringExtra("LAB_ID")
        if (labId != null) {
            loadLabDetails(labId)
        }

        val button = findViewById<Button>(R.id.myButton)
        button.setOnClickListener {
            openMaps()
        }
    }

    private fun loadLabDetails(labId: String) {
        firestore.collection("laboratorios").document(labId).get()
            .addOnSuccessListener { document ->
                val nome = document.getString("nome")
                val endereco = document.getString("endereco")
                val telefone = document.getString("telefone")
                val horario = document.getString("horario")

                findViewById<TextView>(R.id.labNome).text = nome
                findViewById<TextView>(R.id.labEndereco).text = endereco
                findViewById<TextView>(R.id.labFone).text = telefone
                findViewById<TextView>(R.id.labHorario).text = horario

                // Armazena o endereço em uma variável de instância
                labEndereco = endereco
            }
            .addOnFailureListener { exception ->
                // Falha ao buscar documento
                throw exception
            }
    }

    private fun openMaps() {
        labEndereco?.let {
            // Cria o URI do endereço para o Google Maps
            val gmmIntentUri = Uri.parse("geo:0,0?q=${Uri.encode(it)}")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")

            // Verifica se há um aplicativo capaz de lidar com o Intent
            mapIntent.resolveActivity(packageManager)?.let {
                startActivity(mapIntent)
            }
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
    }
}
