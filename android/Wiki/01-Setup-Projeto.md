# 🛠️ Wiki 01 — Setup do Projeto

## 🎯 Objetivo

Ao final desta wiki, você terá o ambiente de desenvolvimento completo funcionando: Android Studio instalado, repositório clonado, e o app BabyTracker rodando no emulador do seu computador. Nada de mistério — só ferramentas funcionando e seu primeiro "Hello World" no nosso projeto.

---

## 📋 Pré-requisitos

- Um computador com **Windows, Mac ou Linux** (Android Studio funciona em tudo).
- Pelo menos **8 GB de RAM** (Android Studio + emulador não são leves).
- **20 GB de espaço em disco** (Android Studio, SDK, emulador, projeto).
- Uma conta no **GitHub** já adicionada à organização `Escola-Nova-Era` (peça ao professor se ainda não foi).
- **Nenhum conhecimento prévio** — vamos passo a passo. 🙂

> ⚠️ **Nota para Mac**: Se você tem Mac com chip Apple (M1/M2/M3), tudo funciona perfeitamente — o Android Studio tem suporte nativo. Alguns passos ficarão ligeiramente diferentes, vou avisar quando.

---

## 📖 Passo a passo

### Passo 1 — Instalar o Android Studio

O Android Studio é o programa oficial da Google para desenvolver apps Android. Aqui é onde você vai escrever código, rodar testes e ver o app funcionar.

**Windows ou Linux:**
1. Vá para [developer.android.com/studio](https://developer.android.com/studio)
2. Clique em **Download Android Studio**
3. Aceite os termos e baixe o instalador
4. Execute o instalador (`.exe` no Windows, `.sh` no Linux) e siga os passos padrão
5. Quando pedir para instalar **Android SDK, Emulator e Android Virtual Device**, deixe marcado (é o que você precisa)

**Mac:**
1. Mesmo link: [developer.android.com/studio](https://developer.android.com/studio)
2. Escolha a versão para seu chip (Apple Silicon se for M1+, ou Intel se for mais antigo)
3. Abra o `.dmg` e arraste o Android Studio para Applications
4. Abra o Applications, dê clique duplo em Android Studio
5. Na primeira execução, ele pede para instalar o SDK — deixe fazer

> ☕ A instalação é grande (vários GB) e pode demorar. Aproveite para seguir o Passo 2 enquanto baixa.

### Passo 2 — Verificar o Git

Você já tem Git se desenvolveu em qualquer linguagem. Vamos conferir:

1. Abra o **Terminal** ou **Command Prompt** (Windows: `Win + R`, digite `cmd`; Mac/Linux: `⌘ + Espaço`, digite "Terminal")

2. Digite:
```bash
git --version
```

- Se aparecer algo como `git version 2.x.x` → ótimo, siga em frente. ✅
- Se der erro dizendo que não encontrou Git → você precisa instalar:
  - **Windows/Mac/Linux**: vá para [git-scm.com](https://git-scm.com) e baixe
  - Instale com as opções padrão

### Passo 3 — Configurar sua identidade no Git

Todo commit que você fizer vai ter seu nome. Configure uma vez (troque pelos seus dados — o email deve ser **o mesmo da sua conta do GitHub**):

```bash
git config --global user.name "Seu Nome"
git config --global user.email "seu-email@github.com"
```

Para conferir se funcionou:
```bash
git config --global user.name
git config --global user.email
```

### Passo 4 — Clonar o repositório

"Clonar" significa baixar uma cópia do projeto do GitHub para o seu computador, já conectada ao repositório remoto.

1. Abra o Terminal/Command Prompt e vá para a pasta onde você quer guardar seus projetos:

```bash
cd ~/Documentos
# ou qualquer pasta sua. Se não existir, crie:
mkdir Projetos
cd Projetos
```

2. Clone o repositório:

```bash
git clone https://github.com/Escola-Nova-Era/BabyTracker.git
```

> 💡 Na primeira vez, o Git pode pedir login do GitHub. O GitHub não aceita senha comum pelo terminal — se pedir, você pode usar um **Personal Access Token** (GitHub → Settings → Developer settings → Personal access tokens → Generate new token) ou instalar o [GitHub Desktop](https://desktop.github.com) e clonar por lá. Ou simplesmente deixar em branco e a autenticação vai funcionar via browser.

3. Entre na pasta do projeto:

```bash
cd BabyTracker
```

### Passo 5 — Conhecer o que você baixou (e ignorar o resto)

O repositório tem **três projetos** — backend, Android e iOS. Isso é normal em times grandes e se chama *monorepo*. Mas você só vai mexer em `android/`.

```
BabyTracker/
├── backend/    ← ignore (time Backend)
├── ios/        ← ignore (time iOS)
└── android/    ← você só trabalha AQUI
    ├── app/                ← código do app
    ├── build.gradle.kts    ← configuração de build
    ├── local.properties    ← seu setup local (não commita)
    └── README.md           ← docs do Android
```

Não se assuste com a quantidade de arquivos. Você não precisa entender tudo agora — só rodar o app.

### Passo 6 — Abrir o projeto no Android Studio

1. Abra o Android Studio
2. Na tela inicial, clique em **Open** (ou File → Open)
3. Navegue até a pasta `BabyTracker/android` que você clonou, **não a raiz do repositório**
4. Clique em **Open**

> 💡 **Primeira vez é lenta**: na primeira abertura, o Android Studio baixa Gradle, dependências, SDK — pode demorar 10-15 minutos. Deixa rodar.

Na barra inferior, você vai ver algo como "Resolving dependencies" ou "Downloading Android SDK". Quando virar verde e disser "Build successful", bora pro próximo passo.

### Passo 7 — Criar um emulador (ou usar um device de verdade)

O emulador é um telefone Android virtual que roda no seu computador.

**Opção A — Emulador (recomendado para começar):**

1. No Android Studio, vá em **Tools → Device Manager**
2. Clique em **Create Virtual Device**
3. Escolha um modelo de telefone (qualquer um serve, ex.: **Pixel 6**)
4. Clique em **Next**
5. Escolha uma versão do Android (recomendo **API 34** ou mais recente)
6. Se não baixou ainda, clique em **Download** e aguarde
7. Clique em **Next** e depois **Finish**
8. De volta no Device Manager, clique o play (▶️) do dispositivo que você criou

O emulador vai abrir — é uma janela grande com a tela de um celular Android.

**Opção B — Device de verdade:**

Se você tem um Android de verdade (celular, tablet), pode usá-lo:
1. Ative o **Developer Mode**: Settings → About phone → toque 7 vezes em "Build number"
2. Ative **USB Debugging**: Settings → Developer options → USB Debugging
3. Conecte via USB

Quando conectar, o Android Studio reconhece sozinho.

### Passo 8 — Rodar o app 🚀

1. Certifique-se de que o emulador (ou device) está ligado e conectado
2. No Android Studio, clique no botão ▶️ verde no topo (ou aperte `Shift + F10` no Windows / `Control + R` no Mac)
3. Se pediu para escolher device, escolha o emulador que você criou
4. A primeira compilação é lenta (alguns minutos). Nas próximas é rápido.
5. Quando terminar, o app abre no emulador: é a tela de Home do BabyTracker com as 4 abas (Home, Timeline, Insights, Profile)

**🎉 Se você chegou até aqui, seu ambiente está pronto!**

---

## 🚑 Erros comuns

| Problema | Solução |
|----------|---------|
| Android Studio demora muito na primeira vez | Normal. Está baixando SDK, Gradle, todas as dependências. Deixa rodar. Na próxima é rápido. |
| "Gradle sync failed" | Pode ser problema de internet ou versão do Java. Tente: **File → Sync Now** de novo, ou **File → Invalidate Caches → Invalidate and Restart**. |
| Emulador não abre | Verifique se sua máquina tem virtualização habilitada (BIOS settings no Windows). Mac normalmente já vem ok. |
| "No connected devices" | O emulador que você criou não está rodando. Volte ao Device Manager e clique ▶️ nele. |
| `gradle-build-tools` download falha | Problema de internet ou repositório fora. Tente de novo. Se persistir, vá em **Settings → Appearance & Behavior → System Settings → Android SDK** e clique **Show Package Details** — baixe manualmente a versão que está faltando. |
| App compila mas emulador mostra tela branca/crash | Vá em **Build → Clean Project** e depois tenta rodar de novo. |
| No Mac: "Android Studio não pode ser aberto porque é de desenvolvedor não verificado" | Clique com botão direito no Android Studio, escolha **Open** e clique **Open** de novo. Depois fica normal. |

---

## 🧪 Tarefa de treino

Agora que o app roda, bora explorar um pouco (sem mexer em código):

1. **Rode o app em dois emuladores de tamanho diferente** (ex.: um Pixel 6 e um Pixel Fold) — observe como o layout se adapta. Anote uma diferença que você notar.

2. **Navegue pelas 4 abas** (Home, Timeline, Insights, Profile) — qual componente visual você vê em mais de uma tela?

3. **Abra o Terminal dentro da pasta do projeto** (`BabyTracker/android`) e rode:

```bash
git status
git log --oneline -5
```

- `git status` mostra se há mudanças (deve estar limpo)
- `git log` mostra os últimos 5 commits — o histórico do que o time fez

Leia a saída com calma e anote um autor que você vê frequentemente.

---

## 📬 Como entregar

Poste um comentário na Issue **"[Android] Entrega — Wiki 01: Setup do Projeto"** com:

1. Um **print do app rodando no emulador** (no emulador, aperte `Ctrl + Shift + S` para screenshot no Windows, ou `Cmd + Shift + S` no Mac)
2. Sua resposta: qual componente visual se repete em mais de uma tela?
3. Um **print do Terminal** mostrando a saída de `git log --oneline -5`
4. Qual autor apareceu mais frequentemente nos commits?

O instrutor vai responder com feedback na própria Issue.

---

## ✅ Checklist de conclusão

- [ ] Android Studio instalado e funcionando
- [ ] `git --version` funciona no Terminal
- [ ] Nome e email configurados no Git (testei com `git config --global user.name`)
- [ ] Repositório clonado em uma pasta minha
- [ ] Criei um emulador Android (ou conectei um device)
- [ ] App rodando no emulador/device com o botão ▶️
- [ ] Naveguei pelas 4 abas (Home, Timeline, Insights, Profile)
- [ ] Rodei `git status` e `git log` no Terminal

---

**Próxima parada:** Wiki 02 — Arquitetura e Padrões *(em breve)*, onde você vai entender como o código do app Android é organizado (MVVM, Kotlin, Compose, estrutura de pastas).

Bom trabalho! 🚀