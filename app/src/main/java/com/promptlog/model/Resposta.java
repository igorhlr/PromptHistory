package com.promptlog.model;

import java.io.Serializable;

/**
 * Classe de modelo para representar uma Resposta de IA
 * Entrega 2 - PromptLog
 */
public class Resposta implements Serializable {
    private int id;
    private String texto;
    private int promptId;
    private String data;
    
    // Construtor vazio
    public Resposta() {
    }
    
    // Construtor completo
    public Resposta(int id, String texto, int promptId, String data) {
        this.id = id;
        this.texto = texto;
        this.promptId = promptId;
        this.data = data;
    }
    
    // Getters e Setters
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
    
    public int getPromptId() {
        return promptId;
    }
    
    public void setPromptId(int promptId) {
        this.promptId = promptId;
    }
    
    public String getData() {
        return data;
    }
    
    public void setData(String data) {
        this.data = data;
    }
    
    // toString() override
    @Override
    public String toString() {
        return "Resposta #" + id + " para Prompt #" + promptId;
    }
}
