// HomeActivity.kt
package com.example.saudefacil.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.LinearLayout
import com.example.saudefacil.R
import com.example.saudefacil.ui.laboratorio.LaboratorioActivity
import com.example.saudefacil.ui.profissional.ProfissionalActivity
import com.example.saudefacil.ui.agenda.AgendaActivity
import com.example.saudefacil.ui.login.LoginActivity
import com.example.saudefacil.ui.cadastro.CadastroActivity
import com.google.android.material.snackbar.Snackbar


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.saudefacil.R.layout.activity_home)

        if (intent.hasExtra("login")) {
            val mensagem = "Login Realizado com Sucesso!"
            Snackbar.make(findViewById(R.id.homeMain), mensagem!!, Snackbar.LENGTH_SHORT).show()
        }

        // Encontrando os LinearLayouts
        val btnLaboratorios: LinearLayout = findViewById(com.example.saudefacil.R.id.btnLaboratorios)
        val btnProfissionaisSaude: LinearLayout = findViewById(com.example.saudefacil.R.id.btnProfissionaisSaude)
        val btnAgenda: LinearLayout = findViewById(com.example.saudefacil.R.id.btnAgenda)
        val loginButton: LinearLayout = findViewById(com.example.saudefacil.R.id.loginButton)
        val cadastroButton: LinearLayout = findViewById(com.example.saudefacil.R.id.cadastroButton)

        // Configurando os listeners
        btnLaboratorios.setOnClickListener {
            val intent = Intent(this, LaboratorioActivity::class.java)
            startActivity(intent)
        }

        btnProfissionaisSaude.setOnClickListener {
            val intent = Intent(this, ProfissionalActivity::class.java)
            startActivity(intent)
        }

        btnAgenda.setOnClickListener {
            val intent = Intent(this, AgendaActivity::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        cadastroButton.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
    }
}
