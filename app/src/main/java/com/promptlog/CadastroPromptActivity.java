package com.promptlog;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity para cadastro e edição de prompts
 * Suporta modo de criação e edição via Intent extras
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
    
    // Variáveis para modo edição
    private boolean modoEdicao = false;
    private int promptId = -1;
    private int promptPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_prompt);
        
        // Habilitar botão de voltar na ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        
        // Inicializar componentes
        inicializarComponentes();
        
        // Configurar Spinner
        configurarSpinner();
        
        // Verificar se está em modo de edição
        verificarModoEdicao();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflar menu de cadastro
        getMenuInflater().inflate(R.menu.menu_cadastro, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        
        // Lidar com clique no botão Up
        if (id == android.R.id.home) {
            setResult(RESULT_CANCELED);
            finish();
            return true;
        }
        // Ação Salvar
        else if (id == R.id.action_salvar) {
            salvarPrompt();
            return true;
        }
        // Ação Limpar (apenas em modo criação)
        else if (id == R.id.action_limpar) {
            if (!modoEdicao) {
                limparFormulario();
            } else {
                Toast.makeText(this, "⚠️ Não é possível limpar em modo de edição", 
                             Toast.LENGTH_SHORT).show();
            }
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
    
    private void verificarModoEdicao() {
        // Obter extras da Intent
        Intent intent = getIntent();
        
        // Verificar se está em modo de edição
        modoEdicao = intent.getBooleanExtra("modo_edicao", false);
        
        if (modoEdicao) {
            // Alterar título da Activity
            setTitle("✏️ Editar Prompt");
            
            // Recuperar dados do prompt para edição
            promptId = intent.getIntExtra("prompt_id", -1);
            promptPosition = intent.getIntExtra("prompt_position", -1);
            
            // Preencher campos com dados existentes
            String texto = intent.getStringExtra("texto");
            String categoria = intent.getStringExtra("categoria");
            String prioridade = intent.getStringExtra("prioridade");
            boolean favorito = intent.getBooleanExtra("favorito", false);
            boolean publico = intent.getBooleanExtra("publico", false);
            String tags = intent.getStringExtra("tags");
            
            // Preencher EditTexts
            if (texto != null) {
                etTextoPrompt.setText(texto);
            }
            if (tags != null) {
                etTags.setText(tags);
            }
            
            // Selecionar categoria no Spinner
            if (categoria != null) {
                ArrayAdapter<CharSequence> adapter = (ArrayAdapter<CharSequence>) spCategoria.getAdapter();
                if (adapter != null) {
                    int posicaoCategoria = adapter.getPosition(categoria);
                    if (posicaoCategoria >= 0) {
                        spCategoria.setSelection(posicaoCategoria);
                    }
                }
            }
            
            // Selecionar prioridade no RadioGroup
            if (prioridade != null) {
                if (prioridade.equals("Alta")) {
                    rgPrioridade.check(R.id.rbAlta);
                } else if (prioridade.equals("Média")) {
                    rgPrioridade.check(R.id.rbMedia);
                } else if (prioridade.equals("Baixa")) {
                    rgPrioridade.check(R.id.rbBaixa);
                }
            }
            
            // Marcar CheckBoxes
            cbFavorito.setChecked(favorito);
            cbPublico.setChecked(publico);
            
        } else {
            // Modo criação - configurar título normal
            setTitle("📝 Novo Prompt");
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
        
        // Focar no primeiro campo
        etTextoPrompt.requestFocus();
        
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
        
        // Se estiver em modo de edição, incluir ID e posição
        if (modoEdicao) {
            resultIntent.putExtra("prompt_id", promptId);
            resultIntent.putExtra("prompt_position", promptPosition);
            resultIntent.putExtra("modo_edicao", true);
            
            // Mensagem específica para edição
            Toast.makeText(this, "✅ Atualizando prompt...", Toast.LENGTH_SHORT).show();
        } else {
            // Mensagem para novo prompt
            Toast.makeText(this, "✅ Salvando prompt...", Toast.LENGTH_SHORT).show();
        }
        
        // Definir resultado como OK e finalizar a Activity
        setResult(RESULT_OK, resultIntent);
        
        // Fechar a Activity e retornar para a ListagemActivity
        finish();
    }
}
