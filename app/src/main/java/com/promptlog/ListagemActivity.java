package com.promptlog;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.promptlog.adapter.PromptAdapter;
import com.promptlog.model.Prompt;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

import com.promptlog.adapter.PromptAdapter;
import com.promptlog.model.Prompt;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Activity principal da Entrega 4
 * Exibe lista de prompts cadastrados dinamicamente
 * Implementa menu de opções e menu de ação contextual (CAB)
 * Entrega 5 - Implementação de SharedPreferences e Internacionalização
 */
public class ListagemActivity extends AppCompatActivity {
    
    // SharedPreferences - Entrega 5
    public static final String ARQUIVO_PREFERENCIAS = "com.promptlog.PREFERENCIAS";
    public static final String KEY_ORDENACAO_ASCENDENTE = "ORDENACAO_ASCENDENTE";
    public static final boolean PADRAO_ORDENACAO_ASCENDENTE = true;
    private boolean ordenacaoAscendente = PADRAO_ORDENACAO_ASCENDENTE;
    private MenuItem menuItemOrdenacao;
    
    // Componentes da UI
    private ListView listViewPrompts;
    private TextView tvListaVazia;
    
    // Dados
    private ArrayList<Prompt> listaPrompts;
    private PromptAdapter adapter;
    
    // Launcher para resultado da Activity de Cadastro
    private ActivityResultLauncher<Intent> cadastroLauncher;
    private ActivityResultLauncher<Intent> edicaoLauncher;
    
    // ActionMode para menu contextual
    private ActionMode actionMode;
    private int posicaoSelecionada = -1;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem);
        
        // Título da activity
        setTitle(getString(R.string.title_listing));
        
        // Inicializar componentes
        inicializarComponentes();
        
        // Ler preferências do usuário - Entrega 5
        lerPreferencias();
        
        // Configurar launcher para resultado
        configurarLauncher();
        
        // Inicializar lista vazia (não carrega mais de resources)
        listaPrompts = new ArrayList<>();
        
        // Configurar adapter customizado
        adapter = new PromptAdapter(this, listaPrompts);
        listViewPrompts.setAdapter(adapter);
        
        // Configurar listeners
        configurarClickListeners();
        
        // Verificar se lista está vazia
        verificarListaVazia();
        
        // Aplicar ordenação inicial se houver itens
        if (!listaPrompts.isEmpty()) {
            ordenarLista();
        }
    }
    
    /**
     * Inicializa todos os componentes da UI
     */
    private void inicializarComponentes() {
        listViewPrompts = findViewById(R.id.listViewPrompts);
        tvListaVazia = findViewById(R.id.tvListaVazia);
    }
    
    /**
     * Configura o launcher para receber resultado da Activity de Cadastro
     */
    private void configurarLauncher() {
        // Launcher para adicionar novo prompt
        cadastroLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    // Verificar se o resultado é OK
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Intent data = result.getData();
                        
                        // Extrair dados do Intent
                        String texto = data.getStringExtra("texto");
                        String categoria = data.getStringExtra("categoria");
                        String prioridade = data.getStringExtra("prioridade");
                        boolean favorito = data.getBooleanExtra("favorito", false);
                        String tags = data.getStringExtra("tags");
                        
                        // Gerar data atual
                        String dataAtual = new SimpleDateFormat("dd/MM/yyyy", 
                            Locale.getDefault()).format(new Date());
                        
                        // Criar novo objeto Prompt
                        int novoId = listaPrompts.size() + 1;
                        Prompt novoPrompt = new Prompt(
                            novoId,
                            texto,
                            categoria,
                            dataAtual,
                            prioridade,
                            favorito,
                            tags
                        );
                        
                        // Adicionar à lista
                        listaPrompts.add(novoPrompt);
                        
                        // Aplicar ordenação - Entrega 5
                        ordenarLista();
                        
                        // Notificar adapter da mudança
                        adapter.notifyDataSetChanged();
                        
                        // Verificar se lista não está mais vazia
                        verificarListaVazia();
                        
                        // Mostrar confirmação
                        Toast.makeText(ListagemActivity.this,
                            getString(R.string.msg_prompt_saved),
                            Toast.LENGTH_SHORT).show();
                    }
                }
            }
        );
        
        // Launcher para editar prompt existente
        edicaoLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Intent data = result.getData();
                        
                        // Obter posição e ID originais
                        int position = data.getIntExtra("prompt_position", -1);
                        int id = data.getIntExtra("prompt_id", -1);
                        
                        if (position >= 0 && position < listaPrompts.size()) {
                            // Atualizar prompt existente
                            Prompt promptExistente = listaPrompts.get(position);
                            promptExistente.setTexto(data.getStringExtra("texto"));
                            promptExistente.setCategoria(data.getStringExtra("categoria"));
                            promptExistente.setPrioridade(data.getStringExtra("prioridade"));
                            promptExistente.setFavorito(data.getBooleanExtra("favorito", false));
                            promptExistente.setTags(data.getStringExtra("tags"));
                            
                            // Aplicar ordenação caso o texto tenha mudado - Entrega 5
                            ordenarLista();
                            
                            // Notificar adapter
                            adapter.notifyDataSetChanged();
                            
                            Toast.makeText(ListagemActivity.this,
                                getString(R.string.msg_prompt_updated),
                                Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        );
    }
    
    /**
     * Configura todos os listeners de click
     */
    private void configurarClickListeners() {
        // Click em item da lista
        listViewPrompts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtém o prompt clicado
                Prompt prompt = listaPrompts.get(position);
                
                // Pega os primeiros 60 caracteres do texto para identificação
                String textoPrompt = prompt.getTexto();
                String textoResumido = textoPrompt.length() > 60 ? 
                    textoPrompt.substring(0, 57) + "..." : textoPrompt;
                
                // Mensagem identificando exatamente qual item foi clicado
                String mensagem = String.format(
                    getString(R.string.msg_item_clicked),
                    prompt.getId(),
                    textoResumido,
                    prompt.getCategoria(),
                    prompt.getPrioridade(),
                    prompt.getData()
                );
                
                // Mostra Toast com duração longa
                Toast.makeText(ListagemActivity.this, 
                              mensagem, 
                              Toast.LENGTH_LONG).show();
            }
        });
        
        // Long click para ativar menu de ação contextual (CAB)
        listViewPrompts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                posicaoSelecionada = position;
                
                // Destacar item selecionado
                view.setSelected(true);
                
                // Iniciar modo de ação contextual
                if (actionMode == null) {
                    actionMode = startActionMode(actionModeCallback);
                }
                
                return true;
            }
        });
    }
    
    /**
     * Verifica se a lista está vazia e mostra/esconde mensagem apropriada
     */
    private void verificarListaVazia() {
        if (listaPrompts.isEmpty()) {
            tvListaVazia.setVisibility(View.VISIBLE);
            listViewPrompts.setVisibility(View.GONE);
        } else {
            tvListaVazia.setVisibility(View.GONE);
            listViewPrompts.setVisibility(View.VISIBLE);
        }
    }
    
    /**
     * Lê as preferências salvas do usuário
     * Entrega 5 - SharedPreferences
     */
    private void lerPreferencias() {
        SharedPreferences prefs = getSharedPreferences(ARQUIVO_PREFERENCIAS, Context.MODE_PRIVATE);
        ordenacaoAscendente = prefs.getBoolean(KEY_ORDENACAO_ASCENDENTE, PADRAO_ORDENACAO_ASCENDENTE);
    }
    
    /**
     * Salva a preferência de ordenação do usuário
     * Entrega 5 - SharedPreferences
     */
    private void salvarPreferenciaOrdenacao(boolean ascendente) {
        SharedPreferences prefs = getSharedPreferences(ARQUIVO_PREFERENCIAS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(KEY_ORDENACAO_ASCENDENTE, ascendente);
        editor.apply(); // Usar apply() para melhor performance
        
        // Atualizar variável de estado
        ordenacaoAscendente = ascendente;
    }
    
    /**
     * Restaura as configurações padrão
     * Entrega 5 - SharedPreferences
     */
    private void restaurarPadroes() {
        SharedPreferences prefs = getSharedPreferences(ARQUIVO_PREFERENCIAS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear(); // Limpar todas as preferências
        editor.apply();
        
        // Restaurar valores padrão
        ordenacaoAscendente = PADRAO_ORDENACAO_ASCENDENTE;
        
        // Atualizar ícone e reordenar lista
        atualizarIconeOrdenacao();
        ordenarLista();
        
        // Mostrar confirmação
        Toast.makeText(this, getString(R.string.msg_settings_restored), Toast.LENGTH_SHORT).show();
    }
    
    /**
     * Ordena a lista de prompts conforme preferência do usuário
     * Entrega 5 - Ordenação persistente
     */
    private void ordenarLista() {
        if (listaPrompts != null && !listaPrompts.isEmpty()) {
            if (ordenacaoAscendente) {
                Collections.sort(listaPrompts, Prompt.ordenacaoCrescente);
            } else {
                Collections.sort(listaPrompts, Prompt.ordenacaoDecrescente);
            }
            
            // Notificar adapter da mudança
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        }
    }
    
    /**
     * Atualiza o ícone do menu de ordenação
     * Entrega 5 - Ícone dinâmico
     */
    private void atualizarIconeOrdenacao() {
        if (menuItemOrdenacao != null) {
            if (ordenacaoAscendente) {
                menuItemOrdenacao.setIcon(R.drawable.ic_sort_ascending);
            } else {
                menuItemOrdenacao.setIcon(R.drawable.ic_sort_descending);
            }
        }
    }
    
    /**
     * Cria o menu de opções da Activity
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_listagem, menu);
        return true;
    }
    
    /**
     * Prepara o menu de opções antes de ser exibido
     * Entrega 5 - Atualiza ícone de ordenação
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // Encontrar item de ordenação
        menuItemOrdenacao = menu.findItem(R.id.action_ordenacao);
        
        // Atualizar ícone baseado na preferência atual
        atualizarIconeOrdenacao();
        
        return super.onPrepareOptionsMenu(menu);
    }
    
    /**
     * Trata seleção de item do menu de opções
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        
        if (id == R.id.action_adicionar) {
            // Abrir tela de cadastro
            Intent intent = new Intent(ListagemActivity.this, CadastroPromptActivity.class);
            cadastroLauncher.launch(intent);
            return true;
        } else if (id == R.id.action_sobre) {
            // Abrir tela sobre
            Intent intent = new Intent(ListagemActivity.this, SobreActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_ordenacao) {
            // Toggle ordenação - Entrega 5
            ordenacaoAscendente = !ordenacaoAscendente;
            
            // Salvar preferência
            salvarPreferenciaOrdenacao(ordenacaoAscendente);
            
            // Atualizar ícone
            atualizarIconeOrdenacao();
            
            // Reordenar lista
            ordenarLista();
            
            // Mostrar feedback
            String mensagem = ordenacaoAscendente ? 
                getString(R.string.settings_ascending) : 
                getString(R.string.settings_descending);
            Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
            
            return true;
        } else if (id == R.id.action_restaurar) {
            // Restaurar configurações padrão - Entrega 5
            restaurarPadroes();
            return true;
        }
        
        return super.onOptionsItemSelected(item);
    }
    
    /**
     * Callback para o modo de ação contextual (CAB)
     */
    private ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflar menu contextual
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.menu_contextual, menu);
            
            // Atualizar título
            mode.setTitle(getString(R.string.action_select));
            
            return true;
        }
        
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }
        
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            int id = item.getItemId();
            
            if (id == R.id.action_editar) {
                // Editar prompt selecionado
                if (posicaoSelecionada >= 0) {
                    Prompt prompt = listaPrompts.get(posicaoSelecionada);
                    
                    Intent intent = new Intent(ListagemActivity.this, CadastroPromptActivity.class);
                    intent.putExtra("modo_edicao", true);
                    intent.putExtra("prompt_id", prompt.getId());
                    intent.putExtra("prompt_position", posicaoSelecionada);
                    intent.putExtra("texto", prompt.getTexto());
                    intent.putExtra("categoria", prompt.getCategoria());
                    intent.putExtra("prioridade", prompt.getPrioridade());
                    intent.putExtra("favorito", prompt.isFavorito());
                    intent.putExtra("tags", prompt.getTags());
                    
                    edicaoLauncher.launch(intent);
                }
                
                mode.finish();
                return true;
                
            } else if (id == R.id.action_excluir) {
                // Excluir prompt selecionado
                if (posicaoSelecionada >= 0) {
                    Prompt promptRemovido = listaPrompts.remove(posicaoSelecionada);
                    adapter.notifyDataSetChanged();
                    verificarListaVazia();
                    
                    Toast.makeText(ListagemActivity.this,
                        getString(R.string.msg_prompt_deleted),
                        Toast.LENGTH_SHORT).show();
                }
                
                mode.finish();
                return true;
            }
            
            return false;
        }
        
        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
            posicaoSelecionada = -1;
            
            // Limpar seleção visual
            for (int i = 0; i < listViewPrompts.getChildCount(); i++) {
                listViewPrompts.getChildAt(i).setSelected(false);
            }
        }
    };
}
