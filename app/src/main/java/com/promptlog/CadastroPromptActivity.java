package com.promptlog;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity para cadastro de novos prompts
 * Retorna dados via setResult para a ListagemActivity
 */
public class CadastroPromptActivity extends AppCompatActivity {
    
    // Variáveis de instância para os componentes UI
    private EditText etTextoPrompt;
    private EditText etTags;
    private RadioGroup rgPrioridade;
    private CheckBox cbFavorito;
    private CheckBox cbPublico;
    private Spinner spCategoria;
    private Button btnLimpar;
    private Button btnSalvar;
    private Button btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_prompt);
        
        // Configurar título da ActionBar
        setTitle("📝 Novo Prompt");
        
        // Habilitar botão de voltar na ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        
        // Inicializar componentes
        inicializarComponentes();
        
        // Configurar Spinner
        configurarSpinner();
        
        // Configurar listeners dos botões
        configurarListeners();
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Lidar com clique no botão voltar da ActionBar
        if (item.getItemId() == android.R.id.home) {
            setResult(RESULT_CANCELED);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    private void inicializarComponentes() {
        // EditTexts
        etTextoPrompt = findViewById(R.id.etTextoPrompt);
        etTags = findViewById(R.id.etTags);
        
        // RadioGroup
        rgPrioridade = findViewById(R.id.rgPrioridade);
        
        // CheckBoxes
        cbFavorito = findViewById(R.id.cbFavorito);
        cbPublico = findViewById(R.id.cbPublico);
        
        // Spinner
        spCategoria = findViewById(R.id.spCategoria);
        
        // Buttons
        btnLimpar = findViewById(R.id.btnLimpar);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnCancelar = findViewById(R.id.btnCancelar);
    }
    
    private void configurarSpinner() {
        // Criar adapter usando array de strings do resources
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categorias_array, android.R.layout.simple_spinner_item);
        
        // Especificar layout para dropdown
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        // Aplicar adapter ao spinner
        spCategoria.setAdapter(adapter);
    }
    
    private void configurarListeners() {
        // Listener para botão Limpar
        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparFormulario();
            }
        });
        
        // Listener para botão Salvar
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarPrompt();
            }
        });
        
        // Listener para botão Cancelar (caso exista no layout)
        if (btnCancelar != null) {
            btnCancelar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Cancelar e voltar sem salvar
                    setResult(RESULT_CANCELED);
                    finish();
                }
            });
        }
    }
    
    private void limparFormulario() {
        // Limpar EditTexts
        etTextoPrompt.setText("");
        etTags.setText("");
        
        // Desmarcar RadioButtons
        rgPrioridade.clearCheck();
        
        // Desmarcar CheckBoxes
        cbFavorito.setChecked(false);
        cbPublico.setChecked(false);
        
        // Resetar Spinner
        spCategoria.setSelection(0);
        
        // Mostrar Toast
        Toast.makeText(this, "✨ Formulário limpo!", Toast.LENGTH_SHORT).show();
    }
    
    private void salvarPrompt() {
        // Pegar valores dos campos
        String textoPrompt = etTextoPrompt.getText().toString().trim();
        String tags = etTags.getText().toString().trim();
        
        // Validar campos obrigatórios
        if (textoPrompt.isEmpty()) {
            Toast.makeText(this, "⚠️ Por favor, preencha o texto do prompt", 
                         Toast.LENGTH_SHORT).show();
            etTextoPrompt.requestFocus();
            return;
        }
        
        // Validar RadioGroup
        int prioridadeSelecionada = rgPrioridade.getCheckedRadioButtonId();
        if (prioridadeSelecionada == -1) {
            Toast.makeText(this, "⚠️ Por favor, selecione uma prioridade", 
                         Toast.LENGTH_SHORT).show();
            return;
        }
        
        // Pegar prioridade selecionada
        RadioButton rbPrioridade = findViewById(prioridadeSelecionada);
        String prioridade = rbPrioridade.getText().toString();
        
        // Pegar estado dos checkboxes
        boolean isFavorito = cbFavorito.isChecked();
        boolean isPublico = cbPublico.isChecked();
        
        // Pegar categoria do Spinner
        String categoria = spCategoria.getSelectedItem().toString();
        
        // Criar Intent de retorno com os dados
        Intent resultIntent = new Intent();
        resultIntent.putExtra("texto", textoPrompt);
        resultIntent.putExtra("categoria", categoria);
        resultIntent.putExtra("prioridade", prioridade);
        resultIntent.putExtra("favorito", isFavorito);
        resultIntent.putExtra("publico", isPublico);
        resultIntent.putExtra("tags", tags);
        
        // Definir resultado como OK e finalizar a Activity
        setResult(RESULT_OK, resultIntent);
        
        // Mostrar mensagem de sucesso rápida
        Toast.makeText(this, "✅ Salvando prompt...", Toast.LENGTH_SHORT).show();
        
        // Fechar a Activity e retornar para a ListagemActivity
        finish();
    }
}
