package com.example.saudefacil.ui.historico

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.saudefacil.R
import com.example.saudefacil.model.AgendaItem
import com.example.saudefacil.ui.agenda.AgendaAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import android.util.Log
import android.widget.Button

class HistoricoActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var historicoAdapter: AgendaAdapter
    private lateinit var historicoList: MutableList<AgendaItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historico)

        recyclerView = findViewById(R.id.lista_historico)
        recyclerView.layoutManager = LinearLayoutManager(this)
        historicoList = mutableListOf()
        historicoAdapter = AgendaAdapter(historicoList)
        recyclerView.adapter = historicoAdapter

        val agendaButton: Button = findViewById(R.id.agenda_button)
        agendaButton.setOnClickListener {
            val intent = Intent(this, HistoricoActivity::class.java)
            startActivity(intent)
        }

        fetchHistoricoData()
    }

    private fun fetchHistoricoData() {
        val db = FirebaseFirestore.getInstance()
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return

        Log.d("HistoricoActivity", "Fetching historico data for user: $userId")

        db.collection("agenda")
            .whereEqualTo("usuario", userId)
            .get()
            .addOnSuccessListener { documents ->
                val currentDate = Date()
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

                for (document in documents) {
                    Log.d("HistoricoActivity", "Document found: ${document.data}")
                    val agenda = document.toObject(AgendaItem::class.java)
                    try {
                        val agendaDate = sdf.parse(agenda.data)
                        if (agendaDate != null && agendaDate.before(currentDate)) {
                            historicoList.add(agenda)
                            Log.d("HistoricoActivity", "Added agenda: $agenda")
                        }
                    } catch (e: ParseException) {
                        e.printStackTrace()
                        Log.e("HistoricoActivity", "Date parse error: ${e.message}")
                    }
                }
                historicoAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Log.w("HistoricoActivity", "Error getting documents: ", exception)
            }
    }
}
