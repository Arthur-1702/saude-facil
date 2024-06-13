package com.example.saudefacil.ui.profissional

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.saudefacil.model.ProfissionalItem
import com.example.saudefacil.databinding.ActivityProfissionalBinding
import com.google.firebase.firestore.FirebaseFirestore

class ProfissionalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfissionalBinding
    private lateinit var adapter: ProfissionalAdapter
    private val db = FirebaseFirestore.getInstance()

    private val cities = listOf("Todas", "São Paulo", "Rio de Janeiro", "Recife", "Olinda")
    private val specialties = listOf("Todas", "Cardiologia", "Dermatologia", "Fisioterapeuta","Nutricionista")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfissionalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupSpinners()
        fetchProfissionais()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ProfissionalAdapter(emptyList()) { profissional ->
            val intent = Intent(this, ProfissionalDetalheActivity::class.java).apply {
                putExtra("professional_name", profissional.nome)
                putExtra("professional_city", profissional.cidade)
                putExtra("professional_specialty", profissional.especialidade)
                putExtra("professional_address", profissional.endereco)
                putExtra("professional_phone", profissional.telefone)
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

    private fun fetchProfissionais() {
        db.collection("profissionais")
            .get()
            .addOnSuccessListener { result ->
                val profissionais = result.map { document ->
                    document.toObject(ProfissionalItem::class.java)
                }
                adapter.updateList(profissionais)
                filterProfissionais()
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }

    private fun filterProfissionais() {
        val selectedCity = binding.spinner1.selectedItem.toString()
        val selectedSpecialty = binding.spinner2.selectedItem.toString()

        fetchProfissionais()

        val filteredProfissionais = adapter.getProfissionais().filter {
            (selectedCity == "Todas" || it.cidade == selectedCity) &&
                    (selectedSpecialty == "Todas" || it.especialidade == selectedSpecialty)
        }
        adapter.updateList(filteredProfissionais)
    }

    companion object {
        private const val TAG = "ProfissionalActivity"
    }
}
