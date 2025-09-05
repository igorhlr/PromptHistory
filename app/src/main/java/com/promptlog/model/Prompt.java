package com.promptlog.model;

import java.io.Serializable;

/**
 * Classe de modelo para representar um Prompt
 * Entrega 2 - PromptLog
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
}
