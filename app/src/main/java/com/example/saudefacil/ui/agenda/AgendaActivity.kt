package com.example.saudefacil.ui.agenda

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.saudefacil.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.saudefacil.model.AgendaItem
import com.google.firebase.firestore.ktx.toObject
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import android.content.Intent
import android.widget.Button
import com.example.saudefacil.ui.historico.HistoricoActivity

class AgendaActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var agendaAdapter: AgendaAdapter
    private lateinit var agendaList: MutableList<AgendaItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agenda)

        recyclerView = findViewById(R.id.lista_agenda)
        recyclerView.layoutManager = LinearLayoutManager(this)
        agendaList = mutableListOf()
        agendaAdapter = AgendaAdapter(agendaList)
        recyclerView.adapter = agendaAdapter

        val historicoButton: Button = findViewById(R.id.historico_button)
        historicoButton.setOnClickListener {
            val intent = Intent(this, HistoricoActivity::class.java)
            startActivity(intent)
        }

        fetchAgendaData()
    }

    private fun fetchAgendaData() {
        val db = FirebaseFirestore.getInstance()
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return

        db.collection("agenda")
            .whereEqualTo("usuario", userId)
            .get()
            .addOnSuccessListener { documents ->
                val currentDate = Date()
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

                for (document in documents) {
                    val agenda = document.toObject(AgendaItem::class.java)
                    try {
                        val agendaDate = sdf.parse(agenda.data)
                        if (agendaDate != null && agendaDate.after(currentDate)) {
                            agendaList.add(agenda)
                        }
                    } catch (e: ParseException) {
                        e.printStackTrace()
                    }
                }
                agendaAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Log.w("AgendaActivity", "Error getting documents: ", exception)
            }
    }
}


