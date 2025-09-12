package com.promptlog.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.promptlog.R;
import com.promptlog.model.Prompt;

import java.util.ArrayList;

/**
 * Adapter customizado para exibir lista de Prompts
 * Implementa ViewHolder pattern para melhor performance
 * Entrega 2 - PromptLog
 */
public class PromptAdapter extends ArrayAdapter<Prompt> {
    
    private Context context;
    private ArrayList<Prompt> prompts;
    
    /**
     * Construtor do adapter
     * @param context Contexto da aplicação
     * @param prompts Lista de prompts a serem exibidos
     */
    public PromptAdapter(Context context, ArrayList<Prompt> prompts) {
        super(context, R.layout.item_prompt, prompts);
        this.context = context;
        this.prompts = prompts;
    }
    
    /**
     * ViewHolder pattern para otimizar performance
     * Mantém referências aos views do item para evitar findViewById repetitivo
     */
    private static class ViewHolder {
        TextView tvTexto;
        TextView tvCategoria;
        TextView tvData;
        TextView tvPrioridade;
        ImageView ivFavorito;
        TextView tvTags;
        
        ViewHolder(View view) {
            tvTexto = view.findViewById(R.id.tvTextoPrompt);
            tvCategoria = view.findViewById(R.id.tvCategoria);
            tvData = view.findViewById(R.id.tvData);
            tvPrioridade = view.findViewById(R.id.tvPrioridade);
            ivFavorito = view.findViewById(R.id.ivFavorito);
            tvTags = view.findViewById(R.id.tvTags);
        }
    }
    
    /**
     * Cria ou reutiliza a view de cada item da lista
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        
        // Verifica se a view pode ser reutilizada
        if (convertView == null) {
            // Infla o layout do item
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_prompt, parent, false);
            
            // Cria novo ViewHolder
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            // Reutiliza o ViewHolder existente
            holder = (ViewHolder) convertView.getTag();
        }
        
        // Obtém o prompt da posição atual
        Prompt prompt = prompts.get(position);
        
        // Preenche os dados nos views
        if (prompt != null) {
            // Texto principal do prompt (limita a 100 caracteres para não poluir)
            String textoDisplay = prompt.getTexto();
            if (textoDisplay != null && textoDisplay.length() > 100) {
                textoDisplay = textoDisplay.substring(0, 97) + "...";
            }
            holder.tvTexto.setText(textoDisplay);
            
            // Categoria
            holder.tvCategoria.setText("📁 " + prompt.getCategoria());
            
            // Data
            holder.tvData.setText("📅 " + prompt.getData());
            
            // Prioridade com cores diferentes
            String prioridade = prompt.getPrioridade();
            holder.tvPrioridade.setText("⚡ " + prioridade);
            
            // Define cor baseada na prioridade usando recursos para comparação
            // Obtém os valores localizados das prioridades
            String highPriority = context.getString(R.string.priority_high);
            String mediumPriority = context.getString(R.string.priority_medium);
            String lowPriority = context.getString(R.string.priority_low);
            
            if (highPriority.equalsIgnoreCase(prioridade) || "High".equalsIgnoreCase(prioridade) || "Alta".equalsIgnoreCase(prioridade)) {
                holder.tvPrioridade.setTextColor(Color.parseColor("#D32F2F")); // Vermelho
            } else if (mediumPriority.equalsIgnoreCase(prioridade) || "Medium".equalsIgnoreCase(prioridade) || "Média".equalsIgnoreCase(prioridade)) {
                holder.tvPrioridade.setTextColor(Color.parseColor("#F57C00")); // Laranja
            } else {
                holder.tvPrioridade.setTextColor(Color.parseColor("#388E3C")); // Verde
            }
            
            // Ícone de favorito
            if (prompt.isFavorito()) {
                holder.ivFavorito.setImageResource(android.R.drawable.star_on);
                holder.ivFavorito.setColorFilter(Color.parseColor("#FFD700")); // Dourado
            } else {
                holder.ivFavorito.setImageResource(android.R.drawable.star_off);
                holder.ivFavorito.setColorFilter(Color.GRAY);
            }
            
            // Tags (mostra apenas as primeiras 3)
            String tags = prompt.getTags();
            if (tags != null && !tags.isEmpty()) {
                String[] tagArray = tags.split(",");
                StringBuilder tagDisplay = new StringBuilder("🏷️ ");
                for (int i = 0; i < Math.min(3, tagArray.length); i++) {
                    tagDisplay.append(tagArray[i].trim());
                    if (i < Math.min(3, tagArray.length) - 1) {
                        tagDisplay.append(", ");
                    }
                }
                if (tagArray.length > 3) {
                    tagDisplay.append("...");
                }
                holder.tvTags.setText(tagDisplay.toString());
                holder.tvTags.setVisibility(View.VISIBLE);
            } else {
                holder.tvTags.setVisibility(View.GONE);
            }
        }
        
        return convertView;
    }
}
