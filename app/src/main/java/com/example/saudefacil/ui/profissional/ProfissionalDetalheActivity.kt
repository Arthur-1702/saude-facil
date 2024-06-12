package com.example.saudefacil.ui.profissional

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.saudefacil.databinding.ActivityProfissionalDetalheBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class ProfissionalDetalheActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfissionalDetalheBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfissionalDetalheBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar Firestore
        firestore = FirebaseFirestore.getInstance()

        // Inicializar FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Retrieve the data from the intent
        val professionalName = intent.getStringExtra("professional_name")
        val professionalCity = intent.getStringExtra("professional_city")
        val professionalSpecialty = intent.getStringExtra("professional_specialty")
        val professionalAddress = intent.getStringExtra("professional_address")
        val professionalPhone = intent.getStringExtra("professional_phone")

        // Encontrar os TextViews no layout
        binding.nomeProf.text = professionalName ?: "N/A"
        binding.cidade.text = professionalCity ?: "N/A"
        binding.especialidade.text = professionalSpecialty ?: "N/A"
        binding.endereco.text = professionalAddress ?: "N/A"
        binding.telefone.text = professionalPhone ?: "N/A"

        // Configurar o DatePickerDialog para o EditText
        binding.dateEditText.setOnClickListener {
            showDatePickerDialog()
        }

        // Configurar o botão "Marcar consulta"
        binding.myButton.setOnClickListener {
            val selectedDate = binding.dateEditText.text.toString()
            if (selectedDate.isNotEmpty()) {
                val userId = auth.currentUser?.uid
                if (userId != null) {
                    saveAppointment(professionalName ?: "N/A", selectedDate, userId)
                } else {
                    Toast.makeText(this, "Usuário não autenticado", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Selecione uma data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                binding.dateEditText.setText(selectedDate)
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    private fun saveAppointment(professionalName: String, date: String, userId: String) {
        val appointment = hashMapOf(
            "profissional" to professionalName,
            "data" to date,
            "usuario" to userId
        )

        firestore.collection("agenda")
            .add(appointment)
            .addOnSuccessListener {
                Toast.makeText(this, "Consulta marcada com sucesso!", Toast.LENGTH_SHORT).show()
                finish() // Finaliza a activity após salvar a consulta
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Erro ao marcar consulta: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
