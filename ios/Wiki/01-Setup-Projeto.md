# 🛠️ Wiki 01 — Setup do Projeto

## 🎯 Objetivo

Ao final desta wiki, você terá o ambiente de desenvolvimento completo funcionando: Xcode instalado, repositório clonado e o app BabyTracker rodando no simulador de iPhone do seu Mac.

## 📋 Pré-requisitos

- Um **Mac** (o desenvolvimento iOS exige macOS).
- Pelo menos **30 GB livres** no disco (o Xcode é grande!).
- Um **Apple ID** (o mesmo que você usa no iPhone/App Store serve).
- Uma conta no **GitHub** já adicionada à organização `Escola-Nova-Era` (peça ao professor se ainda não foi adicionado).
- Nenhum conhecimento prévio — vamos passo a passo. 🙂

---

## 📖 Passo a passo

### Passo 1 — Instalar o Xcode

O Xcode é o programa da Apple onde escrevemos, compilamos e testamos apps iOS.

1. Abra a **App Store** no seu Mac.
2. Pesquise por **Xcode** e clique em **Obter / Instalar**.
3. ☕ O download é grande (mais de 10 GB) — pode demorar. Aproveite para seguir o Passo 2 enquanto baixa.
4. Quando terminar, abra o Xcode uma vez. Ele vai pedir para instalar componentes adicionais — aceite e aguarde.

> ⚠️ O projeto usa uma versão recente do iOS como alvo. Certifique-se de que seu Xcode está **atualizado** (App Store → Atualizações). Versões antigas do Xcode não conseguem compilar o projeto.

### Passo 2 — Verificar o Git

O Git é a ferramenta que usamos para versionar o código e trabalhar em equipe. No Mac, ele vem junto com as ferramentas do Xcode.

1. Abra o app **Terminal** (aperte `⌘ + Espaço`, digite "Terminal" e dê Enter).
2. Digite o comando abaixo e dê Enter:

```bash
git --version
```

- Se aparecer algo como `git version 2.x.x` → ótimo, siga em frente. ✅
- Se aparecer uma janela pedindo para instalar as "ferramentas de linha de comando" → clique em **Instalar** e aguarde.

### Passo 3 — Configurar sua identidade no Git

Todo commit que você fizer carrega seu nome e email. Configure uma única vez (troque pelos seus dados — o email deve ser **o mesmo da sua conta do GitHub**):

```bash
git config --global user.name "Seu Nome"
git config --global user.email "seu-email@exemplo.com"
```

Para conferir se funcionou:

```bash
git config --global user.name
git config --global user.email
```

### Passo 4 — Clonar o repositório

"Clonar" significa baixar uma cópia do projeto do GitHub para o seu Mac, já conectada ao repositório remoto.

1. No Terminal, vá para a pasta onde você guarda seus projetos (ou crie uma):

```bash
mkdir -p ~/Projetos
cd ~/Projetos
```

2. Clone o repositório:

```bash
git clone https://github.com/Escola-Nova-Era/BabyTracker.git
```

> 💡 Na primeira vez, o Git pode pedir login do GitHub. O GitHub não aceita senha comum pelo terminal — se pedir senha, você precisará de um **Personal Access Token** (GitHub → Settings → Developer settings → Personal access tokens) ou pode instalar o [GitHub Desktop](https://desktop.github.com) e clonar por lá.

3. Entre na pasta do projeto:

```bash
cd BabyTracker
```

### Passo 5 — Conhecer o que você baixou (e o que ignorar)

O repositório contém **três projetos** — o backend, o app Android e o app iOS. Isso é normal em times grandes e se chama *monorepo*.

```
BabyTracker/
├── backend/    ← ignore (time Backend)
├── android/    ← ignore (time Android)
└── ios/        ← você só vai trabalhar AQUI
```

**Não se assuste com a quantidade de arquivos.** Nesta wiki você não precisa entender nenhum código — só rodar o app.

### Passo 6 — Abrir o projeto no Xcode

1. No Terminal, digite:

```bash
open ios/BabyTracker.xcodeproj
```

> 💡 Alternativa: no Finder, navegue até `Projetos → BabyTracker → ios` e dê dois cliques em `BabyTracker.xcodeproj` (ícone azul).

2. O Xcode vai abrir e, na primeira vez, baixar automaticamente as dependências do projeto — você verá **"Resolving Package Dependencies"** na barra superior e o nome **Alamofire** aparecer no painel esquerdo. Aguarde terminar — precisa de internet.

> 💡 **O que é Alamofire?** É uma biblioteca externa que o app usa para se comunicar com a internet (chamadas HTTP). Você **não precisa baixar nem instalar nada**: o próprio Xcode baixa sozinho, através do Swift Package Manager. Também não precisa entender como ela funciona agora — isso será visto na wiki de arquitetura.

### Passo 7 — Rodar o app no simulador 🚀

1. Na barra superior do Xcode, ao lado do nome "BabyTracker", há um seletor de dispositivo. Clique nele e escolha um simulador, por exemplo **iPhone 17 Pro** (qualquer iPhone serve).
2. Aperte `⌘ + R` (ou o botão ▶️ no canto superior esquerdo).
3. A primeira compilação demora alguns minutos. Depois, uma janela de iPhone virtual abre com o app BabyTracker rodando.

**🎉 Se você chegou até aqui, seu ambiente está pronto!**

---

## 🚑 Erros comuns

| Problema | Solução |
|----------|---------|
| Abri o Xcode e não acho o projeto | Você precisa abrir o arquivo `BabyTracker.xcodeproj` que está **dentro da pasta `ios/`** — não a pasta raiz do repositório. |
| "Resolving Package Dependencies" falhou | Verifique sua internet e tente: menu **File → Packages → Reset Package Caches**. |
| Erro `No such module 'Alamofire'` | O download das dependências não terminou (ou falhou). Aguarde a barra superior do Xcode ficar livre e tente de novo; se persistir, **File → Packages → Reset Package Caches**. |
| O simulador pede para baixar o iOS | Normal na primeira vez. Clique em baixar (é o "sistema operacional" do iPhone virtual) e aguarde. |
| Erro de "Signing" ao rodar | Para rodar **no simulador** não precisa de conta paga. Selecione o projeto no painel esquerdo → aba **Signing & Capabilities** → marque "Automatically manage signing" e escolha seu Apple ID como Team. |
| Build demorou muito | A primeira build sempre é lenta (compila tudo do zero). As próximas serão rápidas. |
| `git clone` pediu senha e recusou | GitHub não aceita senha no terminal. Use um Personal Access Token ou o GitHub Desktop (veja o Passo 4). |

---

## 🧪 Tarefa de treino

Agora que o app roda, vamos praticar um pouco de navegação (sem alterar nenhum código):

1. Rode o app em **dois simuladores diferentes** (ex.: um iPhone grande e um iPhone SE) e observe como o layout se adapta.
2. Com o app aberto, navegue pelas **abas do app** (Home, Tracking, Insights, Profile) e anote: qual componente visual se repete em mais de uma tela?
3. No Terminal, dentro da pasta do projeto, rode os comandos abaixo e leia a saída com calma:

```bash
git status
git log --oneline -5
```

- `git status` mostra o estado atual dos seus arquivos (deve dizer que não há nada modificado).
- `git log --oneline -5` mostra os últimos 5 commits do projeto — o histórico de quem fez o quê.

## ✅ Checklist de conclusão

- [ ] Xcode instalado e atualizado
- [ ] `git --version` funciona no Terminal
- [ ] Nome e email configurados no Git
- [ ] Repositório clonado em uma pasta sua
- [ ] App rodando no simulador com `⌘ + R`
- [ ] Naveguei pelas 4 abas do app
- [ ] Rodei `git status` e `git log` e entendi o que mostram

**Próxima parada:** Wiki 02 — Arquitetura e Padrões *(em breve)*, onde você vai entender como o código do app é organizado.
