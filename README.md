# 📝 PromptLog

<div align="center">
  
  **Sistema de Gerenciamento de Prompts para Android**
  
  [![Android](https://img.shields.io/badge/Android-5.0+-3DDC84?style=flat-square&logo=android)](https://developer.android.com)
  [![Java](https://img.shields.io/badge/Java-11-007396?style=flat-square&logo=java)](https://www.java.com)
  [![Languages](https://img.shields.io/badge/Languages-3-blue?style=flat-square)](README.md)
  [![License](https://img.shields.io/badge/License-MIT-yellow.svg?style=flat-square)](LICENSE)
  
  [Sobre](#-sobre) • [Funcionalidades](#-funcionalidades) • [Screenshots](#-screenshots) • [Instalação](#-instalação) • [Internacionalização](#-internacionalização) • [Tecnologias](#-tecnologias) • [Estrutura](#-estrutura)
</div>

---

## 📋 Sobre

**PromptLog** é um aplicativo Android nativo desenvolvido como projeto acadêmico para demonstrar conceitos avançados de desenvolvimento mobile, incluindo:
- **🌍 Internacionalização (i18n)** com suporte a múltiplos idiomas
- **💾 Persistência de dados** com SharedPreferences
- **🔄 Ordenação dinâmica** com preferências do usuário
- Implementação de Activities e navegação
- Uso de menus e Contextual Action Bar (CAB)
- Gerenciamento de estado e ciclo de vida
- Interface responsiva com Material Design
- Padrão MVC (Model-View-Controller)

O aplicativo permite criar, visualizar, editar e excluir prompts de forma intuitiva, categorizando-os por tipo, prioridade e tags.

## ✨ Funcionalidades

### 🎯 Funcionalidades Principais
- **➕ Criar Prompts**: Adicione novos prompts com texto, categoria, prioridade e tags
- **📋 Listar Prompts**: Visualize todos os prompts cadastrados de forma organizada
- **✏️ Editar Prompts**: Modifique prompts existentes mantendo seu ID original
- **🗑️ Excluir Prompts**: Remova prompts com feedback visual imediato
- **⭐ Favoritar**: Marque prompts importantes como favoritos
- **🏷️ Categorizar**: Organize por Work, Personal, Study, Creative, Other
- **🚨 Priorizar**: Defina prioridades High, Medium ou Low
- **🔖 Tags**: Adicione palavras-chave para facilitar organização

### 🌍 Internacionalização (Novo na v5.0)
- **3 Idiomas Suportados**: 
  - 🇺🇸 English (padrão)
  - 🇵🇹 Português
  - 🇧🇷 Português Brasil
- **Mudança Automática**: Segue idioma do sistema
- **100% Traduzido**: Todos os textos, menus e mensagens
- **Categorias Localizadas**: Work/Trabalho, Personal/Pessoal, etc.
- **Prioridades Adaptadas**: High/Alta, Medium/Média, Low/Baixa

### 💾 Persistência e Configurações
- **SharedPreferences**: Salva preferências do usuário
- **Ordenação Persistente**: Mantém ordem escolhida (A-Z ou Z-A)
- **Toggle de Ordenação**: Ícone dinâmico ↑↓ no menu
- **Restaurar Padrões**: Volta configurações ao estado inicial
- **Dados Mockados**: 10 prompts de exemplo para demonstração

### 🎨 Interface e Navegação
- **Material Design**: Interface moderna seguindo diretrizes do Google
- **Menu de Opções**: Acesso rápido às funcionalidades principais
- **Menu Contextual (CAB)**: Long press para editar/excluir
- **Navegação Intuitiva**: Up navigation e fluxo consistente
- **Feedback Visual**: Toasts informativos em múltiplos idiomas
- **Tema Roxo**: Visual distintivo e profissional
- **Ícones Dinâmicos**: Mudam conforme estado da aplicação

## 📸 Screenshots

<div align="center">
  <table>
    <tr>
      <td width="25%">
        <img src="docs/img/lista.png" alt="Tela de Listagem">
        <p align="center"><b>Listagem de Prompts</b></p>
      </td>
      <td width="25%">
        <img src="docs/img/criar.png" alt="Tela de Criação">
        <p align="center"><b>Novo Prompt</b></p>
      </td>
      <td width="25%">
        <img src="docs/img/editar.png" alt="Tela de Edição">
        <p align="center"><b>Editar Prompt</b></p>
      </td>
      <td width="25%">
        <img src="docs/img/sobre.png" alt="Tela Sobre">
        <p align="center"><b>Sobre o App</b></p>
      </td>
    </tr>
  </table>
</div>

### 📱 Demonstração das Funcionalidades

#### Listagem de Prompts
- Exibe todos os prompts cadastrados
- Mostra categoria, data, prioridade e tags
- Ícone de estrela para favoritos
- Long press ativa menu contextual
- Mensagem quando lista está vazia

#### Cadastro/Edição
- Campo de texto multilinha para o prompt
- Spinner dropdown para categorias
- RadioButtons para prioridade
- CheckBoxes para favorito/público
- Validação de campos obrigatórios
- Menu com ações Salvar e Limpar

## 🌍 Internacionalização

### Idiomas Suportados

O PromptLog oferece suporte completo para 3 idiomas:

| Idioma | Código | Status | Exemplo |
|--------|--------|--------|---------|
| 🇺🇸 English | en | Padrão | Work, High Priority |
| 🇵🇹 Português | pt | Completo | Trabalho, Prioridade Alta |
| 🇧🇷 Português Brasil | pt-BR | Completo | Trabalho, Prioridade Alta |

### Como Mudar o Idioma

1. **Configurações do Android** → **Sistema** → **Idiomas e entrada**
2. **Idiomas** → **Adicionar um idioma**
3. Selecione **Português (Brasil)** ou **Português**
4. Arraste para o topo da lista
5. O app mudará automaticamente

### Elementos Traduzidos

- ✅ Todas as Activities (Main, Listagem, Cadastro, Sobre)
- ✅ Menus e itens de menu
- ✅ Labels e hints dos campos
- ✅ Mensagens toast e validação
- ✅ Categorias (Work→Trabalho, Study→Estudo, etc.)
- ✅ Prioridades (High→Alta, Medium→Média, Low→Baixa)
- ✅ Textos da tela Sobre
- ✅ Arrays de dados mockados

## 💾 Persistência de Dados

### SharedPreferences

O app utiliza SharedPreferences para salvar configurações do usuário:

```java
// Arquivo de preferências
ARQUIVO_PREFERENCIAS = "com.promptlog.PREFERENCIAS"

// Chaves salvas
KEY_ORDENACAO_ASCENDENTE = true/false  // Ordem da lista
```

### Funcionalidades de Persistência

- **Ordenação Salva**: A-Z ou Z-A persiste entre sessões
- **Restaurar Padrões**: Limpa todas as preferências
- **Aplicação Automática**: Configurações aplicadas ao iniciar

### Dados Mockados

Para demonstração, o app carrega 10 prompts de exemplo:
- Textos em português sobre programação, criatividade e análise
- Categorias variadas demonstrando tradução
- Prioridades mistas para testar ordenação
- Tags relevantes para cada prompt

## 🚀 Instalação

### Pré-requisitos
- Android Studio Arctic Fox ou superior
- JDK 11 ou superior
- Android SDK API 21+ (Android 5.0 Lollipop)
- Gradle 8.0+

### Passos para Instalação

1. **Clone o repositório**
```bash
git clone https://github.com/seu-usuario/PromptLog.git
cd PromptLog
```

2. **Abra no Android Studio**
- File → Open → Selecione a pasta do projeto
- Aguarde a sincronização do Gradle

3. **Execute o projeto**
- Conecte um dispositivo ou inicie um emulador
- Click em "Run" (▶️) ou use `Shift + F10`

### Build via Terminal
```bash
# Debug build
./gradlew assembleDebug

# Release build
./gradlew assembleRelease
```

## 🛠️ Tecnologias

### Linguagens e Frameworks
- **Java 11**: Linguagem principal
- **Android SDK**: API 21+ (Lollipop)
- **Material Components**: Biblioteca de UI
- **AndroidX**: Bibliotecas de suporte modernas

### Ferramentas de Build
- **Gradle 8.13**: Sistema de build
- **Android Gradle Plugin 8.8.0**: Plugin do Android

### Componentes Android
- **AppCompatActivity**: Base para activities
- **ActionBar**: Barra de ações superior
- **Contextual Action Mode**: Menu contextual
- **ListView**: Lista de prompts
- **ScrollView**: Scroll no formulário
- **Material Theme**: Tema visual

## 📂 Estrutura do Projeto

```
PromptLog/
├── app/
│   └── src/
│       └── main/
│           ├── java/com/promptlog/
│           │   ├── MainActivity.java           # Tela inicial
│           │   ├── ListagemActivity.java      # Lista com SharedPreferences
│           │   ├── CadastroPromptActivity.java # Criar/Editar
│           │   ├── SobreActivity.java         # Informações do app
│           │   ├── adapter/
│           │   │   └── PromptAdapter.java     # Adapter com i18n
│           │   └── model/
│           │       └── Prompt.java            # Model com Comparators
│           └── res/
│               ├── layout/                     # Layouts XML
│               ├── menu/                       # Menus XML
│               ├── drawable/                   # Ícones (sort ascending/descending)
│               ├── values/                     # EN - Inglês (padrão)
│               │   ├── strings.xml            # Textos em inglês
│               │   ├── arrays.xml             # Arrays com dados mockados
│               │   ├── colors.xml             # Cores do tema
│               │   └── themes.xml             # Tema roxo
│               ├── values-pt/                  # PT - Português
│               │   ├── strings.xml            # Textos em português
│               │   └── arrays.xml             # Arrays traduzidos
│               └── values-pt-rBR/              # PT-BR - Português Brasil
│                   ├── strings.xml            # Textos em português brasileiro
│                   └── arrays.xml             # Arrays traduzidos
├── docs/
│   ├── img/                                   # Screenshots do app
│   └── teste-i18n-instrucoes.md              # Guia de teste de idiomas
├── gradle/
├── .gitignore
├── build.gradle
├── settings.gradle
└── README.md
```

### 🏗️ Arquitetura

O projeto segue o padrão **MVC** (Model-View-Controller):

- **Model**: Classe `Prompt` representa os dados
- **View**: Layouts XML e componentes visuais
- **Controller**: Activities gerenciam a lógica

### 📝 Principais Classes

#### Prompt.java
```java
public class Prompt {
    private int id;
    private String texto;
    private String categoria;
    private String data;
    private String prioridade;
    private boolean favorito;
    private String tags;
    
    // Comparators para ordenação (Entrega 5)
    public static final Comparator<Prompt> ordenacaoCrescente = 
        (p1, p2) -> p1.texto.compareToIgnoreCase(p2.texto);
    
    public static final Comparator<Prompt> ordenacaoDecrescente = 
        (p1, p2) -> p2.texto.compareToIgnoreCase(p1.texto);
}
```

#### ListagemActivity.java
```java
public class ListagemActivity extends AppCompatActivity {
    // SharedPreferences (Entrega 5)
    private static final String ARQUIVO_PREFERENCIAS = "com.promptlog.PREFERENCIAS";
    private static final String KEY_ORDENACAO_ASCENDENTE = "ORDENACAO_ASCENDENTE";
    
    private void carregarDadosMockados() {
        // Carrega 10 prompts de exemplo dos arrays
    }
    
    private void ordenarLista() {
        // Aplica ordenação baseada na preferência salva
    }
}
```

#### PromptAdapter.java
Adapter customizado com suporte a múltiplos idiomas e comparação robusta de prioridades.

## 🎮 Como Usar

### Configurar Idioma
1. Vá em **Configurações** do Android
2. **Sistema** → **Idiomas e entrada** → **Idiomas**
3. Adicione **Português (Brasil)** ou **Português**
4. Arraste para o topo da lista
5. O app mudará automaticamente

### Ordenar Lista de Prompts
1. Na tela de listagem, toque no ícone de ordenação (↑ ou ↓)
2. A lista alternará entre ordem A-Z e Z-A
3. A preferência será salva automaticamente
4. Permanece mesmo após reiniciar o app

### Restaurar Configurações Padrão
1. Na lista de prompts, abra o menu (⋮)
2. Selecione **"Restaurar Padrões"**
3. A ordenação voltará para A-Z (crescente)

### Criar um Novo Prompt
1. Na tela principal, clique em **"Cadastrar Novo Prompt"**
2. Preencha o texto do prompt (obrigatório)
3. Selecione categoria e prioridade
4. Adicione tags separadas por vírgula
5. Marque como favorito se desejar
6. Clique no ícone 💾 para salvar

### Editar um Prompt
1. Na lista, faça um **long press** no item desejado
2. Selecione **"Editar"** no menu contextual
3. Modifique os campos necessários
4. Salve as alterações

### Excluir um Prompt
1. **Long press** no item
2. Selecione **"Excluir"**
3. O item será removido imediatamente

## 📊 Versões e Entregas

### v5.0 - Internacionalização e SharedPreferences (Atual)
- 🌍 Suporte a 3 idiomas (EN, PT, PT-BR)
- 💾 Persistência de configurações
- 🔄 Ordenação dinâmica com ícones
- 📝 10 prompts mockados para demonstração
- ✅ 100% dos textos traduzidos

### v4.0 - Menus e Navegação Profissional
- 📱 Menu de opções completo
- 🎯 Contextual Action Bar (CAB)
- 🔄 Edição de prompts existentes
- 🗑️ Exclusão com feedback visual
- 📄 Tela Sobre com informações do app

### v3.0 - Cadastro Dinâmico
- ➕ Adicionar prompts dinamicamente
- 📋 Integração com lista principal
- ✅ Validação de campos

### v2.0 - Lista com Dados Mockados
- 📋 ListView com adapter customizado
- 🎨 ViewHolder pattern
- 📊 10 prompts de exemplo

### v1.0 - Estrutura Base
- 🏗️ Activities principais
- 🎨 Tema roxo customizado
- 📱 Layouts responsivos

## 🤝 Contribuindo

Contribuições são bem-vindas! Para contribuir:

1. Faça um Fork do projeto
2. Crie sua Feature Branch (`git checkout -b feature/MinhaFeature`)
3. Commit suas mudanças (`git commit -m 'feat: adiciona nova funcionalidade'`)
4. Push para a Branch (`git push origin feature/MinhaFeature`)
5. Abra um Pull Request

### 📋 Convenções
- Commits seguem [Conventional Commits](https://www.conventionalcommits.org/)
- Código em Java segue convenções Android
- Nomes de recursos em snake_case
- Comentários em português
- Strings internacionalizadas em 3 idiomas

## 🧪 Testando

### Testes de Internacionalização
1. Mude o idioma do sistema para PT-BR
2. Verifique se todos os textos mudam
3. Teste as categorias e prioridades traduzidas
4. Confirme que as cores das prioridades funcionam

### Testes de Persistência
1. Mude a ordenação para Z-A
2. Force Stop no app
3. Reabra e confirme que mantém Z-A
4. Teste o botão "Restaurar Padrões"

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 🙏 Agradecimentos

- Professor da disciplina de Desenvolvimento Mobile
- Colegas de turma pelos feedbacks
- Comunidade Android pela documentação
- Equipe de i18n do Android

## 📚 Recursos Úteis

- [Documentação Android i18n](https://developer.android.com/guide/topics/resources/localization)
- [SharedPreferences Guide](https://developer.android.com/training/data-storage/shared-preferences)
- [Material Design](https://material.io/design)
- [Conventional Commits](https://www.conventionalcommits.org/)

---

<div align="center">
  Desenvolvido com ❤️ para fins acadêmicos
  
  **Entrega 5 - Internacionalização e SharedPreferences**
  
  🌍 3 Idiomas | 💾 Persistência | 🎨 Material Design
  
  📚 Curso de Especialização em JAVA - Módulo Android
  
  🏫 [UTFPR](https://www.utfpr.edu.br) - Universidade Tecnológica Federal do Paraná
  
  👤 Igor Henrique Lima Rozalem
  
  📧 igorrozalem@utfpr.edu.br
  
  © 2025 - Todos os direitos reservados
</div>
