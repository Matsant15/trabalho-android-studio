package com.stackmobile.barbershopsp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.stackmobile.barbershopsp.databinding.ActivityMainBinding
import com.stackmobile.barbershopsp.view.Home

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btLogin.setOnClickListener {

            val nome = binding.editNome.text.toString()
            val senha = binding.editSenha.text.toString()

            when{
                nome.isEmpty() -> {
                    mensagem(it,"Coloque o seu nome!")
                }senha.isEmpty() -> {
                    mensagem(it,"Preencha a senha!")
                }senha.length <=5 -> {
                    mensagem(it,"A sua senha precisa ter pelo menos 6 carácteres")
                }else -> {
                    navegarPraHome(nome)

                }
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun mensagem(view: View,mensagem:String){
        val snackbar = Snackbar.make(view,mensagem,Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(Color.parseColor("FF0000"))
        snackbar.setTextColor(Color.parseColor("FFFFFF"))
        snackbar.show()

    }

    private fun navegarPraHome(nome: String){
        val intent = Intent(this,Home::class.java)
        intent.putExtra("nome",nome)
        startActivity(intent)
    }
}