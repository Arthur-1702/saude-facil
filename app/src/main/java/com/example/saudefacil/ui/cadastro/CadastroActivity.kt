package com.example.saudefacil.ui.cadastro

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.saudefacil.R
import com.example.saudefacil.databinding.ActivityCadastroBinding

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
            val endereco = binding.editTextENDEREO.text.toString()
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
                binding.editTextENDEREO.error =
                    getString(R.string.message_field_required, getString(R.string.endereco))
                isToAdd = false
            }
            if (complemento.isEmpty()) {
                binding.editTextComplemento.error =
                    getString(R.string.message_field_required, getString(R.string.complemento))
                isToAdd = false
            }

            if (isToAdd) {
            }
        }
    }
}