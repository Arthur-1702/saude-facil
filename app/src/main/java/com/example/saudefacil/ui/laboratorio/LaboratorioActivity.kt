package com.example.saudefacil.ui.laboratorio

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.saudefacil.R
import android.content.Intent
import android.widget.ImageView


class LaboratorioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laboratorio)

        findViewById<ImageView>(R.id.lab1).setOnClickListener {
            openLaboratorioDetalhe("MgqckzVrLux45bNrD0gP")
        }

        findViewById<ImageView>(R.id.lab2).setOnClickListener {
            openLaboratorioDetalhe("CnSDjscf4jRuqGW9UBoH")
        }

        findViewById<ImageView>(R.id.lab3).setOnClickListener {
            openLaboratorioDetalhe("kgk2WKHjstIOLCOoVyQW")
        }

        findViewById<ImageView>(R.id.lab4).setOnClickListener {
            openLaboratorioDetalhe("oZXUFoe0EyEAVb1NVZLi")
        }
    }

    private fun openLaboratorioDetalhe(labId: String) {
        val intent = Intent(this, LaboratorioDetalheActivity::class.java)
        intent.putExtra("LAB_ID", labId)
        startActivity(intent)
    }
}
