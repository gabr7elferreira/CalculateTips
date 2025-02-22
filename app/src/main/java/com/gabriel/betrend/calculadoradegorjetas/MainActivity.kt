package com.gabriel.betrend.calculadoradegorjetas

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.gabriel.betrend.calculadoradegorjetas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //valor total da conta
    //numero de pessoas
    //percentual da gojeta
    //calcular
    //limpar

    //recuperar as views do layout
    //find view by id
    //viewBinding

    //recuperar os radios buttons

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var percentage = 0

        binding.rbOptionOne.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                percentage = 10
            }

        }
        binding.rbOptionTwo.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                percentage = 15
            }
        }
        binding.rbOptionThree.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                percentage = 25
            }
        }

        binding.btnClean.setOnClickListener {
            binding.tilTotal.editText?.text?.clear()
            binding.numberPerson.editText?.text?.clear()
            binding.rgParcentage.clearCheck()
            binding.tvResultado.visibility = View.GONE
            binding.tvValor.visibility = View.GONE
            binding.tvPessoas.visibility = View.GONE
            binding.guidelineView.visibility = View.GONE
            binding.guidelineViewH.visibility = View.GONE
            binding.btnSlideBack.visibility = View.GONE

            Toast.makeText(this, "Campos Limpos", Toast.LENGTH_SHORT).show()


        }

        binding.btnCalculate.setOnClickListener{

            val nTotalGeral = binding.tilTotal.editText?.text.toString().toFloatOrNull()
            val nPessoas = binding.numberPerson.editText?.text.toString().toFloatOrNull()


            if (nTotalGeral != null && nPessoas !=  null && nTotalGeral != 0f && nPessoas != 0f){
                //calculo das gorjetas
                val totalTemp = (nTotalGeral * percentage) / 100
                val totalComGorjeta = (nTotalGeral + totalTemp) / nPessoas

                //Animacao do lado esquerdo
                val animator = ObjectAnimator.ofFloat(binding.root, "translationX", 0f, -500f)
                animator.duration = 500
                animator.start()
                Toast.makeText(this,"Resultado da conta: $totalComGorjeta", Toast.LENGTH_SHORT).show()

                //Animacao para escrita do resultado
                val inserirAnimacaoGorjeta = ValueAnimator.ofFloat(0f, totalComGorjeta)
                inserirAnimacaoGorjeta.duration = 2000 // 2segundos para o numero aparecer
                inserirAnimacaoGorjeta.addUpdateListener { animation ->
                    val animacaoGorjeta = animation.animatedValue as Float
                    binding.tvResultado.text = "R$ ${String.format("%.2f", animacaoGorjeta)}"
                }

                val inserirAnimacaoValor = ValueAnimator.ofFloat(0f, nTotalGeral)
                inserirAnimacaoValor.duration = 2000
                inserirAnimacaoValor.addUpdateListener { animation ->
                    val animacaoValor = animation.animatedValue as Float
                    binding.tvValor.text = "R$ ${String.format("%.2f", animacaoValor)}"
                }

                val inserirAnimacaoPessoas = ValueAnimator.ofFloat(0f, nPessoas)
                inserirAnimacaoPessoas.duration = 2000
                inserirAnimacaoPessoas.addUpdateListener { animation ->
                    val animacaoPessoas = animation.animatedValue as Float
                    binding.tvPessoas.text = "PESSOAS: ${String.format("%.0f", animacaoPessoas)}"
                }


                //animacao para voltar
                binding.btnSlideBack.setOnClickListener {
                    val animacaoBack = ObjectAnimator.ofFloat(binding.root, "translationX", -500f, 0f)
                    animacaoBack.duration = 500
                    animacaoBack.start()

                }

                inserirAnimacaoValor.start()
                inserirAnimacaoPessoas.start()
                inserirAnimacaoGorjeta.start()
                binding.tvResultado.visibility = View.VISIBLE
                binding.tvValor.visibility = View.VISIBLE
                binding.tvPessoas.visibility = View.VISIBLE
                binding.guidelineView.visibility = View.VISIBLE
                binding.guidelineViewH.visibility = View.VISIBLE
                binding.btnSlideBack.visibility = View.VISIBLE

            }else{
                Toast.makeText(this,"Valor Invalido!", Toast.LENGTH_SHORT).show()
            }



        }
    }
}