package com.Gabriel.beTrend.tipcalculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/*

PROGRAMA PARA CALCULAR AS GORGETAS

ETAPA 01:
- Criar um titulo
- criar um subtitulo
- criar um campo para add o valor da refeicao
- criar um campo para add o numero de pessoas que comeram
- adicionar as opcoes com o percentual para a gorgeta: ex 15% 20% 30%
- adicionar o button para limpar os dados inseridos
- adiconar o button para calcular o valor da gorgeta
----------------------------------------------------------

ETAPA 02:

- Recuperar as Views do Layout XML
    - findViewByID
    - ViewBinding

*/

class MainActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }



}