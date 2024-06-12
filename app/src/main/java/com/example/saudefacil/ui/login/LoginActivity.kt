package com.example.saudefacil.ui.login

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.saudefacil.R
import com.example.saudefacil.ui.cadastro.CadastroActivity
import com.example.saudefacil.ui.home.HomeActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var editEmail: EditText
    private lateinit var editSenha: EditText
    private lateinit var btEntrar: Button
    private val mensagens = arrayOf("Preencha todos os campos", "Login efetuado com sucesso")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        IniciarComponentes()

        btEntrar.setOnClickListener { v ->
            val email = editEmail.text.toString()
            val senha = editSenha.text.toString()

            if (email.isEmpty() || senha.isEmpty()) {
                val snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.BLACK)
                snackbar.setTextColor(Color.WHITE) // Corrigido para Color.WHITE
                snackbar.show()
            } else {
                AutenticarUsuario()
            }
        }
    }

    private fun IniciarComponentes() {
        editEmail = findViewById(R.id.editEmail)
        editSenha = findViewById(R.id.editSenha)
        btEntrar = findViewById(R.id.btEntrar)
    }

    private fun AutenticarUsuario() {
        val email = editEmail.text.toString()
        val senha = editSenha.text.toString()

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Login bem-sucedido
                    val user = FirebaseAuth.getInstance().currentUser
                    Snackbar.make(findViewById(R.id.main), mensagens[1], Snackbar.LENGTH_SHORT).show()

                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("logado", "Usuário Logado com Sucesso")
                    startActivity(intent)
                } else {
                    // Falha no login
                    Snackbar.make(findViewById(R.id.main), "Falha na autenticação.", Snackbar.LENGTH_SHORT).show()
                }
            }
    }

}
