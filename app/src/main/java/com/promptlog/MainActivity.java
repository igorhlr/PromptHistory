package com.promptlog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Configurar botão para abrir CadastroPromptActivity
        Button btnCadastro = findViewById(R.id.btnAbrirCadastro);
        if (btnCadastro != null) {
            btnCadastro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, CadastroPromptActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}