# 📚 Wiki — BabyTracker iOS

Bem-vindo(a) ao time iOS do **BabyTracker**! 👶

O BabyTracker é um app para pais acompanharem a rotina do bebê (alimentação, sono, fraldas etc.). O projeto é desenvolvido pelos alunos da Escola Nova Era e é dividido em três times: **Backend**, **Android** e **iOS**. Esta Wiki é o guia do time iOS.

## 🧭 Comece por aqui

Se você acabou de entrar no time, siga a trilha abaixo **na ordem**. Cada wiki ensina uma etapa e termina com uma tarefa prática de treino. Ao final da trilha, você terá passado por todo o ciclo de contribuição: configurar o ambiente → entender o projeto → criar uma branch → codar seguindo os padrões → abrir um Pull Request.

| # | Wiki | O que você vai aprender | Status |
|---|------|------------------------|--------|
| 01 | [Setup do Projeto](01-Setup-Projeto.md) | Instalar as ferramentas, clonar o repositório e rodar o app no simulador | ✅ Disponível |
| 02 | [Arquitetura e Padrões](02-Arquitetura-e-Padroes.md) | Como o código é organizado e quais padrões seguimos | ✅ Disponível |
| 03 | Fluxo de Contribuição | Branch, commit, Pull Request e code review na prática | 🚧 Em breve |
| 04 | Criação de UI | Criar componentes visuais usando o tema do app | 🚧 Em breve |
| 05 | Nova Funcionalidade | Criar uma tela completa do zero (Model → ViewModel → View) | 🚧 Em breve |

## 🎓 Como funcionam as tarefas de treino

Cada wiki termina com uma **tarefa fake**: um exercício prático que simula uma contribuição real, mas sem afetar o código de produção. As regras são:

- Branches de treino seguem o padrão: `training/<seu-nome>/<exercicio>`
  - Exemplo: `training/maria/setup-projeto`
- Pull Requests de treino têm o título começando com `[TREINO]`
  - Exemplo: `[TREINO] Maria — Exercício de setup`
- PRs de treino **nunca são mergeados** — eles existem para você praticar e receber feedback. Depois do review, eles são fechados.

## 📬 Como entregar as tarefas

Cada wiki tem uma **Issue de entrega** aberta neste repositório (procure em Issues pelo título "Entrega — Wiki 01", "Entrega — Wiki 02" etc.). Ao concluir a tarefa de treino:

1. Abra a Issue de entrega da wiki correspondente.
2. Poste **um comentário** com: as respostas das perguntas + os prints pedidos (arraste a imagem para o campo de comentário que o GitHub anexa sozinho).
3. O instrutor responde no próprio comentário com o feedback.

Assim toda a turma pode ver as entregas e aprender com as dúvidas dos colegas. A partir da Wiki 03, algumas entregas passam a ser Pull Requests de treino (a própria wiki avisa quando for o caso).

## 🗺️ Mapa do repositório

Ao clonar o repositório, você vai ver três projetos. **Você só vai trabalhar dentro de `ios/`** — pode ignorar completamente as outras pastas.

```
BabyTracker/               (raiz do repositório)
├── backend/               ← API do projeto (time Backend — ignore)
├── android/               ← App Android (time Android — ignore)
└── ios/                   ← SEU MUNDO É AQUI
    ├── Wiki/              ← você está aqui 📍
    ├── BabyTracker.xcodeproj   ← arquivo que você abre no Xcode
    └── BabyTracker/       ← código-fonte do app iOS
```

## 🆘 Travou? Precisa de ajuda?

1. Releia a seção **"Erros comuns"** da wiki que você está seguindo — provavelmente sua dúvida está lá.
2. Pesquise a mensagem de erro exata no Google (isso também é uma habilidade de dev!).
3. Pergunte no grupo do time — descreva **o que você tentou fazer**, **o que esperava** e **o que aconteceu** (print da tela ajuda muito).

Boa jornada! 🚀
