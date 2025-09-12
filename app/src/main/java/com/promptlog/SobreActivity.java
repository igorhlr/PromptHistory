package com.promptlog;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity que exibe informações sobre o aplicativo e seu autor
 * Mostra dados como nome do desenvolvedor, curso, e-mail e descrição do app
 */
public class SobreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);
        
        // Configurar título da ActionBar
        setTitle(getString(R.string.title_about));
        
        // Habilitar botão de voltar na ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Lidar com clique no botão voltar da ActionBar
        if (item.getItemId() == android.R.id.home) {
            finish(); // Fecha a activity e volta para a anterior
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
