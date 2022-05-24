package com.example.nutrilife;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText et_nome,et_peso,et_altura;
    private TextView tv_teste;
    private RadioGroup rg_grupo;
    private Button bt_calcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tv_teste = findViewById(R.id.tv_teste);
        et_nome = findViewById(R.id.et_nome);
        et_peso = findViewById(R.id.et_peso);
        et_altura = findViewById(R.id.et_altura);
        rg_grupo = findViewById(R.id.rg_grupo);
        bt_calcular = findViewById(R.id.bt_calcular);

        bt_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome="",sexo="";
                double peso=0,altura=0,imc=0;
                nome = et_nome.getText().toString();
                int op = rg_grupo.getCheckedRadioButtonId();

                if(op == R.id.rb_masc){
                    sexo = "Masculino";
                }
                else if (op == R.id.rb_femi){
                    sexo = "Feminino";
                }
                try{
                    peso = Double.parseDouble(et_peso.getText().toString());
                    altura = Double.parseDouble(et_altura.getText().toString());
                }catch (Exception erro){
                    peso=0.0;
                    altura=0.0;
                }

                if(nome==""||sexo==""||peso==0||altura==0){
                    AlertDialog.Builder janela = new AlertDialog.Builder(MainActivity.this);
                    janela.setTitle("NutriLife");
                    janela.setMessage("Todos os campos devem ser preenchidos!");
                    janela.setNeutralButton("Ok",null);
                    janela.show();
                }else{
                    if(altura>3){
                        altura= altura/100;
                    }
                    imc = peso/(Math.pow(altura,2));
                    AlertDialog.Builder janela = new AlertDialog.Builder(MainActivity.this);
                    janela.setTitle("NutriLife");
                    janela.setMessage("Todos os dados estão corretos?");
                    String finalSexo = sexo;
                    double finalImc = imc;
                    janela.setPositiveButton("sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                            Bundle params = new Bundle();
                            params.putString("nome",et_nome.getText().toString());
                            params.putString("sexo", finalSexo);
                            params.putDouble("imc", finalImc);
                            intent.putExtras(params);
                            startActivity(intent);
                        }
                    });
                    janela.setNegativeButton("Não",null);
                    janela.show();
                }

            }
        });

    }
}