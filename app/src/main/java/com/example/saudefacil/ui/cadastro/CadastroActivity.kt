package com.example.saudefacil.ui.cadastro

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.saudefacil.R
import com.example.saudefacil.databinding.ActivityCadastroBinding
import com.example.saudefacil.ui.home.HomeActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class CadastroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val name = binding.editTextNome.text.toString()
            val email = binding.editTextEmail.text.toString()
            val senha = binding.editTextSenha.text.toString()
            val cep = binding.editTextCEP.text.toString()
            val endereco = binding.editTextEndereco.text.toString()
            val complemento = binding.editTextComplemento.text.toString()

            var isToAdd = true

            if (name.isEmpty()) {
                binding.editTextNome.error =
                    getString(R.string.message_field_required, getString(R.string.name))
                isToAdd = false
            }
            if (email.isEmpty()) {
                binding.editTextEmail.error =
                    getString(R.string.message_field_required, getString(R.string.email))
                isToAdd = false
            }
            if (senha.isEmpty()) {
                binding.editTextSenha.error =
                    getString(R.string.message_field_required, getString(R.string.senha))
                isToAdd = false
            }
            if (cep.isEmpty()) {
                binding.editTextCEP.error =
                    getString(R.string.message_field_required, getString(R.string.cep))
                isToAdd = false
            }
            if (endereco.isEmpty()) {
                binding.editTextEndereco.error =
                    getString(R.string.message_field_required, getString(R.string.endereco))
                isToAdd = false
            }
            if (complemento.isEmpty()) {
                binding.editTextComplemento.error =
                    getString(R.string.message_field_required, getString(R.string.complemento))
                isToAdd = false
            }

            if (isToAdd) {
                CadastrarUsuario()
            }
        }
    }

    private fun CadastrarUsuario() {
        val email = binding.editTextEmail.text.toString()
        val senha = binding.editTextSenha.text.toString()

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Snackbar.make(findViewById(R.id.main), R.string.cadastro_sucesso, Snackbar.LENGTH_SHORT).show()

                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("Cadastrado", "Usuário Cadastrado com Sucesso")
                    startActivity(intent)
                } else {
                    Snackbar.make(findViewById(R.id.main), R.string.cadastro_erro, Snackbar.LENGTH_SHORT).show()
                }
            }
    }
}
