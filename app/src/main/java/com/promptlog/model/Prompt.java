package com.promptlog.model;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Classe de modelo para representar um Prompt
 * Entrega 2 - PromptLog
 * Entrega 5 - Adicionados Comparators para ordenação
 */
public class Prompt implements Serializable {
    // Atributos obrigatórios (mínimo 4, usando 7 para completude)
    private int id;
    private String texto;
    private String categoria;
    private String data;
    private String prioridade;
    private boolean favorito;
    private String tags;
    
    // Construtor vazio
    public Prompt() {
    }
    
    // Construtor completo com todos os parâmetros
    public Prompt(int id, String texto, String categoria, String data, 
                  String prioridade, boolean favorito, String tags) {
        this.id = id;
        this.texto = texto;
        this.categoria = categoria;
        this.data = data;
        this.prioridade = prioridade;
        this.favorito = favorito;
        this.tags = tags;
    }
    
    // Getters e Setters para todos os atributos
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getTexto() {
        return texto;
    }
    
    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    public String getCategoria() {
        return categoria;
    }
    
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public String getData() {
        return data;
    }
    
    public void setData(String data) {
        this.data = data;
    }
    
    public String getPrioridade() {
        return prioridade;
    }
    
    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }
    
    public boolean isFavorito() {
        return favorito;
    }
    
    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }
    
    public String getTags() {
        return tags;
    }
    
    public void setTags(String tags) {
        this.tags = tags;
    }
    
    // toString() override para debug e exibição
    @Override
    public String toString() {
        return "Prompt #" + id + ": " + 
               (texto != null && texto.length() > 30 ? 
                texto.substring(0, 30) + "..." : texto);
    }
    
    // Comparators para ordenação - Entrega 5
    /**
     * Comparator para ordenação crescente (A-Z) baseado no texto do prompt
     */
    public static Comparator<Prompt> ordenacaoCrescente = new Comparator<Prompt>() {
        @Override
        public int compare(Prompt p1, Prompt p2) {
            if (p1.getTexto() == null && p2.getTexto() == null) {
                return 0;
            }
            if (p1.getTexto() == null) {
                return 1;
            }
            if (p2.getTexto() == null) {
                return -1;
            }
            return p1.getTexto().compareToIgnoreCase(p2.getTexto());
        }
    };
    
    /**
     * Comparator para ordenação decrescente (Z-A) baseado no texto do prompt
     */
    public static Comparator<Prompt> ordenacaoDecrescente = new Comparator<Prompt>() {
        @Override
        public int compare(Prompt p1, Prompt p2) {
            if (p1.getTexto() == null && p2.getTexto() == null) {
                return 0;
            }
            if (p1.getTexto() == null) {
                return -1;
            }
            if (p2.getTexto() == null) {
                return 1;
            }
            return p2.getTexto().compareToIgnoreCase(p1.getTexto());
        }
    };
}
