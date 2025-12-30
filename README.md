# 🎮 Jogo da Forca - Android

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)

Um aplicativo clássico de **Jogo da Forca** desenvolvido para a plataforma Android para a disciplina de Desenvolvimento em Dispositivos Móveis. O desafio é adivinhar a palavra oculta antes que o boneco seja completamente desenhado!

## 📱 Sobre o Projeto

Este projeto foi desenvolvido em **Java** utilizando o Android Studio. Ele demonstra conceitos como manipulação de layouts (XML), gerenciamento de estados, lógica de jogo e interação com o usuário através de componentes de UI.

## 🕹️ Como Jogar

1. O sistema escolhe uma palavra aleatória.
2. O jogador deve clicar nas letras para tentar adivinhar a palavra.
3. Para cada erro, uma parte do corpo do boneco aparece na forca.
4. O jogo termina se o jogador completar a palavra (**Vitória**) ou se todas as partes do corpo forem desenhadas (**Derrota**).

## ✨ Funcionalidades

* **Palavras Aleatórias:** Diversas palavras para garantir a rejogabilidade.
* **Interface Intuitiva:** Teclado virtual personalizado dentro do app.
* **Feedback Visual:** Evolução visual da forca conforme os erros ocorrem.
* **Controle de Pontuação:** Reinicie o jogo rapidamente após vencer ou perder.

## 🛠️ Tecnologias e Ferramentas

* **Linguagem:** [Java](https://www.oracle.com/br/java/technologies/downloads/)
* **IDE:** [Android Studio](https://developer.android.com/studio)
* **Layout:** XML (ConstraintLayout / LinearLayout)

## 🚀 Como Rodar o Projeto

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/jotor-dev/jogo_forca_android.git](https://github.com/jotor-dev/jogo_forca_android.git)
    ```
2.  **Abra o Android Studio.**
3.  Vá em `File > Open` e selecione a pasta do projeto clonado.
4.  Aguarde o **Gradle** sincronizar as dependências.
5.  Conecte um dispositivo físico ou inicie um emulador (AVD).
6.  Clique no botão **Run (Play)** no topo do Android Studio.

## 📁 Estrutura Principal

* `app/src/main/java/`: Contém a lógica em Java (Activities e lógica do jogo).
* `app/src/main/res/layout/`: Contém os arquivos XML que definem a interface visual.
* `app/src/main/res/drawable/`: Contém as imagens/vetores das fases da forca.
