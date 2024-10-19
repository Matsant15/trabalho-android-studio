package com.stackmobile.barbershopsp.view

import android.graphics.Color
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.stackmobile.barbershopsp.databinding.ActivityAgendamentoBinding
import java.util.Calendar

class Agendamento : AppCompatActivity() {

    private lateinit var binding: ActivityAgendamentoBinding
    private val calendar: Calendar = Calendar.getInstance()
    private var data: String = ""
    private var hora: String = ""

    @RequiresApi(VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgendamentoBinding.inflate(layoutInflater)


        setContentView(binding.root)

        supportActionBar?.hide()
        val nome = intent.extras?.getString("nome").toString()

        val datePicker = binding.datePicker
        datePicker.setOnDateChangedListener { _, year, monthOfYear, dayOfmonth ->

            calendar.set(Calendar.YEAR,year)
            calendar.set(Calendar.MONTH,monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH,dayOfmonth)

            var dia = dayOfmonth.toString()
            val mes: String

            if (dayOfmonth < 10){
                dia = "0$dayOfmonth"
            }
            if (monthOfYear < 10){
                mes = "" + (monthOfYear+1)
            }else{
                mes = (monthOfYear +1).toString()
            }

            data = "$dia / $mes / $year"
        }

        binding.timePacker.setOnTimeChangedListener { _, hourOfDay, minute ->

            val minuto: String

            if (minute < 10){
                minuto = "0$minute"
            }else{
                minuto = minute.toString()
            }

            hora = "$hourOfDay:$minuto"
        }
        binding.timePacker.setIs24HourView(true)

        binding.btAgendar.setOnClickListener {
            val barbeiro1 = binding.barbeiro1
            val barbeiro2 = binding.barbeiro2
            val barbeiro3 = binding.barbeiro3

            when{
                hora.isEmpty() -> {
                    mensagem(it,"Preencha o horário!","FF0000")

                }
                hora < "8:00" && hora > "19:00" -> {
                    mensagem(it,"Barber Shop esta fechado - horário de atendimento das 08:00 as 19:00!","FF0000")

                }
                data.isEmpty() -> {
                    mensagem(it,"Coloque uma data!","FF0000")

                }
                barbeiro1.isChecked && data.isNotEmpty() && hora.isNullOrEmpty() -> {
                    mensagem(it,"Agendamento realizado com sucesso!","FF03DAC5")

                }
                barbeiro2.isChecked && data.isNotEmpty() && hora.isNullOrEmpty() -> {
                    mensagem(it,"Agendamento realizado com sucesso!","FF03DAC5")

                }
                barbeiro3.isChecked && data.isNotEmpty() && hora.isNullOrEmpty() -> {
                    mensagem(it,"Agendamento realizado com sucesso!","FF03DAC5")

                }
                else -> {
                    mensagem(it,"Escolha um barbeiro!","FF0000")

                }
            }
        }
    }

    private fun mensagem(view: View, mensagem: String, cor: String){
        val snackbar = Snackbar.make(view,mensagem,Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(Color.parseColor(cor))
        snackbar.setTextColor(Color.parseColor("FFFFFF"))
        snackbar.show()

    }

}
