package com.gabriel.betrend.calculadoradegorjetas

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
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


     private var musicEfeito : MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var percentage = 0


        //iniciar musicEfeito
        musicEfeito = MediaPlayer.create(this, R.raw.moneycounter)

        //coloca de novo a gorjeta vertical
        fun showGorjetaDeVoltaComDelay(){
            val fadeInGorjeta = ObjectAnimator.ofFloat(binding.tvMarqueGorjeta, "alpha",0f, 1f)
            fadeInGorjeta.duration = 500
            fadeInGorjeta.start()
            binding.tvMarqueGorjeta.visibility = View.VISIBLE

        }

        //animacaoDescendo
        fun showAnimationDown() {

            binding.tvMarqueGorjeta.visibility = View.VISIBLE

            val inserirAnimacaoDescendo = ObjectAnimator.ofFloat(binding.tvMarqueGorjeta, "translationY", -100f, 0f)
            inserirAnimacaoDescendo.duration = 500

            val descendoSumindo = ObjectAnimator.ofFloat(binding.tvMarqueGorjeta, "alpha", 1f, 0f)
            descendoSumindo.duration = 500
            descendoSumindo.startDelay = 500

            val animandoJuntos = AnimatorSet()
            animandoJuntos.playTogether(inserirAnimacaoDescendo, descendoSumindo)
            animandoJuntos.start()



        }

        binding.rbOptionOne.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                percentage = 2
                showAnimationDown()
            }

        }
        binding.rbOptionTwo.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                percentage = 3
                showAnimationDown()
            }
        }
        binding.rbOptionThree.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                percentage = 5
                showAnimationDown()
            }
        }
        binding.rbOptionFour.setOnCheckedChangeListener{ _, isChecked ->
            if (isChecked) {
                percentage = 10
                showAnimationDown()
            }
        }
        binding.rbOptionFive.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                percentage = 15
                showAnimationDown()
            }

        }
        binding.rbOptionSix.setOnCheckedChangeListener{ _, isChecked ->
            if (isChecked) {
                percentage = 20
                showAnimationDown()

            }

        }

        binding.btnClean.setOnClickListener {
            binding.tilTotal.editText?.text?.clear()
            binding.numberPerson.editText?.text?.clear()
            binding.rgParcentage.clearCheck()
            percentage = 0
            binding.tvResultado.visibility = View.GONE
            binding.tvValor.visibility = View.GONE
            binding.tvPessoas.visibility = View.GONE
            binding.guidelineView.visibility = View.GONE
            binding.guidelineViewH.visibility = View.GONE
            binding.btnSlideBack.visibility = View.GONE
            binding.titleLeft.visibility = View.GONE
            showGorjetaDeVoltaComDelay()

            Toast.makeText(this, "Todos os Campos foram Limpos", Toast.LENGTH_SHORT).show()


        }

        binding.btnCalculate.setOnClickListener{

            val nTotalGeral = binding.tilTotal.editText?.text.toString().toFloatOrNull()
            val nPessoas = binding.numberPerson.editText?.text.toString().toFloatOrNull()


            if (nTotalGeral != null && nPessoas !=  null && nTotalGeral != 0f && nPessoas != 0f){
                //calculo das gorjetas
                val totalTemp = (nTotalGeral * percentage) / 100
                val totalComGorjetas = nTotalGeral + totalTemp
                val totalComGorjeta = totalComGorjetas / nPessoas


                //Animacao do lado esquerdo
                val animator = ObjectAnimator.ofFloat(binding.root, "translationX", 0f, -500f)
                animator.duration = 500
                animator.start()
                musicEfeito?.start()
                Handler().postDelayed({
                    musicEfeito?.pause()
                },2443)


                //Animacao para escrita do resultado
                val inserirAnimacaoGorjeta = ValueAnimator.ofFloat(0f, totalComGorjeta)
                inserirAnimacaoGorjeta.duration = 2000 // 2segundos para o numero aparecer
                inserirAnimacaoGorjeta.addUpdateListener { animation ->
                    val animacaoGorjeta = animation.animatedValue as Float
                    binding.tvResultado.text = "Valor por\n Pessoas\n R$ ${String.format("%.2f\n", animacaoGorjeta)}"
                }


                val inserirAnimacaoValor = ValueAnimator.ofFloat(0f, nTotalGeral)
                inserirAnimacaoValor.duration = 2000
                inserirAnimacaoValor.addUpdateListener { animation ->
                    val animacaoValor = animation.animatedValue as Float
                    binding.tvValor.text = "Valor da Conta\n R$ ${String.format("%.2f\n", animacaoValor)}"
                }

                val inserirAnimacaoPessoas = ValueAnimator.ofFloat(0f, nPessoas)
                inserirAnimacaoPessoas.duration = 2000
                inserirAnimacaoPessoas.addUpdateListener { animation ->
                    val animacaoPessoas = animation.animatedValue as Float
                    binding.tvPessoas.text = "\nQuantidade de\n Pessoas\n ${String.format("%.0f\n", animacaoPessoas)}"
                }


                //animacao para voltar
                binding.btnSlideBack.setOnClickListener {
                    val animacaoBack = ObjectAnimator.ofFloat(binding.root, "translationX", -500f, 0f)
                    animacaoBack.duration = 500
                    animacaoBack.start()

                    //coloca novamente a imagem
                    binding.imgTips.visibility = View.VISIBLE

                    //esconde os resultados
                    binding.tvResultado.visibility = View.GONE
                    binding.tvValor.visibility = View.GONE
                    binding.tvPessoas.visibility = View.GONE

                    //limpa os campos de porcentagem
                    binding.rgParcentage.clearCheck()
                    percentage = 0

                    //voltar gorjeta de volta
                    showGorjetaDeVoltaComDelay()


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
                binding.titleLeft.visibility = View.VISIBLE
                binding.imgTips.visibility = View.GONE
                binding.tvMarqueGorjeta.visibility = View.GONE


            }else{
                Toast.makeText(this,"Valor Invalido!", Toast.LENGTH_SHORT).show()
            }



        }
    }
}