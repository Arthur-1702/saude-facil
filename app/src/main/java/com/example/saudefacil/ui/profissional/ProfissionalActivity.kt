package com.example.saudefacil.ui.profissional

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.saudefacil.model.ProfissionalItem
import com.example.saudefacil.databinding.ActivityProfissionalBinding


class ProfissionalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfissionalBinding
    private lateinit var adapter: ProfissionalAdapter

    private val profissionais = listOf(
        ProfissionalItem("Dr. João", "São Paulo", "Cardiologia"),
        ProfissionalItem("Dra. Maria", "Rio de Janeiro", "Dermatologia"),
        ProfissionalItem("Dr. Pedro", "São Paulo", "Pediatria")
    )

    private val cities = listOf("Todas", "São Paulo", "Rio de Janeiro")
    private val specialties = listOf("Todas", "Cardiologia", "Dermatologia", "Pediatria")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfissionalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupSpinners()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ProfissionalAdapter(profissionais) { profissional ->
            val intent = Intent(this, ProfissionalDetalheActivity::class.java).apply {
                putExtra("professional_name", profissional.nome)
                putExtra("professional_city", profissional.cidade)
                putExtra("professional_specialty", profissional.especialidade)
                putExtra("professional_address", "Exemplo de Endereço")
                putExtra("professional_phone", "1234-5678")
            }
            startActivity(intent)
        }
        binding.recyclerView.adapter = adapter
    }

    private fun setupSpinners() {
        val cityAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cities)
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner1.adapter = cityAdapter

        val specialtyAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, specialties)
        specialtyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner2.adapter = specialtyAdapter

        binding.spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                filterProfissionais()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        binding.spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                filterProfissionais()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun filterProfissionais() {
        val selectedCity = binding.spinner1.selectedItem.toString()
        val selectedSpecialty = binding.spinner2.selectedItem.toString()

        val filteredProfissionais = profissionais.filter {
            (selectedCity == "Todas" || it.cidade == selectedCity) &&
                    (selectedSpecialty == "Todas" || it.especialidade == selectedSpecialty)
        }
        adapter.updateList(filteredProfissionais)
    }
}
