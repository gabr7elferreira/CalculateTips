package com.gabriel.betrend.calculadoradegorjetas

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val numeroDePessoasNaMesa = findViewById<TextInputLayout>(R.id.number_person)
        val mesaTotal = numeroDePessoasNaMesa.editText
        val valorDaCompraTotal = findViewById<TextInputLayout>(R.id.til_total)
        val compraRecebe = valorDaCompraTotal.editText
        val calcularValor = findViewById<Button>(R.id.btn_calculate)

        calcularValor.setOnClickListener {

            val nPessoas = mesaTotal?.text.toString().toFloatOrNull()
            val nTotal = compraRecebe?.text.toString().toFloatOrNull()

            if (nPessoas != null && nTotal != null) {
                val soma = nPessoas * nTotal
                Toast.makeText(this, "O resultado é: $soma ", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Valor Invalido!", Toast.LENGTH_SHORT).show()
            }

        }

    }
}