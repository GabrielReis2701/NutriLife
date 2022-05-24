package com.example.nutrilife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    private TextView tv_resultado;
    private Button bt_voltar;
    private String classificacao,riscos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv_resultado = findViewById(R.id.tv_resultado);
        bt_voltar = findViewById(R.id.bt_voltar);

        Intent anterior = getIntent();
        if(anterior != null){
            Bundle params = anterior.getExtras();
            String nome = params.getString("nome");
            String sexo = params.getString("sexo");
            double imc = params.getDouble("imc");

            if (imc<=16 && imc<=16.9 && sexo=="Feminino"){
                classificacao = "Muito abaixo do peso";
                riscos = "Queda de cabelo, infertilidade, ausência menstrual";
            }else if(imc<=16 && imc<=16.9 && sexo=="Masculino"){
                classificacao = "Muito abaixo do peso";
                riscos = "Queda de cabelo, infertilidade";
            }else if(imc >=17 && imc<=18.4){
                classificacao = "Abaixo do peso";
                riscos = "Fadiga, Stress, ansiedade";
            }else if(imc>=18.5 && imc<=24.9){
                classificacao = "Peso normal";
                riscos = "Menor risco de doenças cardiacas e vasculares";
            }else if (imc>25 && imc<=29.99){
                classificacao = "Acima do peso";
                riscos = "Fadiga, má circulação, varizes";
            }else if (imc>=30 && imc<=34.9){
                classificacao = "Obesidade Grau I";
                riscos = "Diabetes, angina, infarto, aterosclerose";
            }else if(imc >35 && imc<=40){
                classificacao = "Obesidade Grau II";
                riscos = "Apneia do sono, falta de ar";
            }else if(imc>40){
                classificacao = "Obesidade Grau III";
                riscos = "Refluxo, dificuldade para se mover, escaras, diabetes, infarto, AVC";
            }

            tv_resultado.setText("Olá, "+nome+"."+"\nSeu IMC é: "+imc+"\nClassificação: "+classificacao+"\n\nAbaixo estão os riscos associados ao seu resultado:\n"
            +riscos);


        }
        bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent voltar = new Intent(MainActivity2.this,MainActivity.class);
                startActivity(voltar);
            }
        });

    }
}