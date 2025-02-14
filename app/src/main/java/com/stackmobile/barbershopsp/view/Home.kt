package com.stackmobile.barbershopsp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.stackmobile.barbershopsp.R
import com.stackmobile.barbershopsp.adapter.ServicosAdapter

import com.stackmobile.barbershopsp.databinding.ActivityHomeBinding
import com.stackmobile.barbershopsp.model.Servicos

private lateinit var servicosAdapter: ServicosAdapter
private val listaServicos: MutableList<Servicos> = mutableListOf()


class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val nome = intent.extras?.getString("nome")

        binding.txtNomeUsuario.text = "Bem Vindo,$nome"
        val recyclerViewServicos = binding.recylerViewServicos
        recyclerViewServicos.layoutManager = GridLayoutManager(this,2)
        servicosAdapter = ServicosAdapter(this, listaServicos)
        recyclerViewServicos.setHasFixedSize(true)
        recyclerViewServicos.adapter = servicosAdapter
        getServicos()

        binding.btAgendar.setOnClickListener {
            val intent = Intent(this,Agendamento::class.java)
            intent.putExtra("nome",nome)
            startActivity(intent)
        }

        }

    private fun getServicos(){

        val servico1 = Servicos(R.drawable.img1,"Corte de cabelo")
        listaServicos.add(servico1)

        val servico2 = Servicos(R.drawable.img2,"Corte de barba")
        listaServicos.add(servico2)

        val servico3 = Servicos(R.drawable.img3,"Lavagem de cabelo")
        listaServicos.add(servico3)

        val servico4 = Servicos(R.drawable.img4,"Tratamento de cabelo")
        listaServicos.add(servico4)
    }
}