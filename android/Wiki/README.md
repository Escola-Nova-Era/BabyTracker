# 📚 Wiki — BabyTracker Android

Bem-vindo(a) ao time Android do **BabyTracker**! 👶

O BabyTracker é um app para pais acompanharem a rotina do bebê (alimentação, sono, fraldas, etc.). O projeto é desenvolvido pelos alunos da Escola Nova Era e é dividido em três times: **Backend**, **iOS** e **Android**. Esta Wiki é o guia do time Android.

---

## 🧭 Comece por aqui

Se você acabou de entrar no time, siga a trilha abaixo **na ordem**. Cada wiki ensina uma etapa e termina com uma tarefa prática de treino. Ao final da trilha, você terá passado por todo o ciclo de contribuição: configurar o ambiente → entender o projeto → criar uma branch → codar seguindo os padrões → abrir um Pull Request.

| # | Wiki | O que você vai aprender | Status |
|---|------|------------------------|--------|
| 01 | [Setup do Projeto](01-Setup-Projeto.md) | Instalar Android Studio, clonar o repositório e rodar o app no emulador | ✅ Disponível |
| 02 | [Arquitetura e Padrões](02-Arquitetura-e-Padroes.md) | Como o código é organizado, MVVM Flat, padrões Kotlin e Compose | ✅ Disponível |
| 03 | [Fluxo de Contribuição](03-Fluxo-de-Contribuicao.md) | Branch, commit, Pull Request e code review na prática | ✅ Disponível |
| 04 | [Criação de UI](04-Criacao-de-UI.md) | Criar componentes Compose, usar tokens de design, estruturar screens | ✅ Disponível |
| 05 | [Nova Funcionalidade](05-Nova-Funcionalidade.md) | Criar uma screen completa do zero (ViewModel → UI → Navegação) | ✅ Disponível |

---

## 🎓 Como funcionam as tarefas de treino

Cada wiki termina com uma **tarefa fake**, um exercício prático que simula uma contribuição real, mas sem afetar o código de produção. As regras são:

- **Branches de treino** seguem o padrão: `training/<seu-nome>/<exercicio>`
  - Exemplo: `training/joao/setup-projeto`

- **Pull Requests de treino** têm o título começando com `[TREINO]`
  - Exemplo: `[TREINO] João — Exercício de setup`

- **PRs de treino nunca são mergeados** — eles existem para você praticar e receber feedback. Depois do review, eles são fechados.

---

## 📬 Como entregar as tarefas

Cada wiki tem uma **Issue de entrega** aberta neste repositório (procure em Issues pelo título "Entrega — Wiki 01", "Entrega — Wiki 02", etc.). Ao concluir a tarefa de treino:

1. Abra a Issue de entrega da wiki correspondente
2. Poste **um comentário** com:
   - As respostas das perguntas solicitadas
   - Os prints pedidos (arraste a imagem para o campo de comentário que o GitHub anexa automaticamente)
3. O instrutor responde no próprio comentário com feedback

Assim toda a turma consegue ver as entregas e aprender com as dúvidas dos colegas. A partir da Wiki 03, algumas entregas passam a ser Pull Requests de treino (a própria wiki avisa quando for o caso).

---

## 🗺️ Mapa do repositório

Ao clonar o repositório, você vai ver três projetos. **Você só vai trabalhar dentro de `android/`** — pode ignorar completamente as outras pastas.

```
BabyTracker/                      (raiz do repositório)
├── backend/                       ← API do projeto (time Backend — ignore)
├── ios/                           ← App iOS (time iOS — ignore)
└── android/                       ← SEU MUNDO É AQUI
    ├── Wiki/                      ← você está aqui 📍
    ├── app/                       ← código-fonte do app Android
    ├── build.gradle.kts           ← configuração de build
    └── README.md                  ← documentação do Android
```

---

## 🆘 Travou? Precisa de ajuda?

1. **Releia a seção "Erros comuns"** da wiki que você está seguindo — provavelmente sua dúvida está lá.

2. **Pesquise a mensagem de erro exata no Google** — isso também é uma habilidade de dev! Adicione "Kotlin" ou "Compose" para resultados mais relevantes.

3. **Pergunte no grupo do time** — descreva:
   - O que você tentou fazer
   - O que esperava
   - O que aconteceu (print da tela ajuda muito)

4. **Leia a documentação oficial**:
   - [Kotlin Documentation](https://kotlinlang.org/docs/)
   - [Jetpack Compose](https://developer.android.com/develop/ui/compose)
   - [Android Developers](https://developer.android.com/)

---

## ⚡ Ritmo recomendado

- **Wiki 01 a 03**: 1 semana (setup, arquitetura, fluxo)
- **Wiki 04 a 05**: 2 semanas (prática com UI e features)
- **Total**: ~3 semanas até estar pronto(a) para tarefas reais

Esse é um ritmo sugerido, não obrigatório. Se precisar de mais tempo, é totalmente ok. O importante é aprender bem.

---

## 🚀 Depois da Wiki 05

Após completar toda a trilha, você:

- ✅ Consegue rodar e entender o projeto Android
- ✅ Sabe como o código é organizado (MVVM, Compose, estrutura de pastas)
- ✅ Conhece o fluxo completo de contribuição (Git, PR, code review)
- ✅ Consegue criar componentes visuais usando padrões do time
- ✅ Consegue implementar uma feature inteira (dados → lógica → UI)

A partir daí, você receberá tarefas reais do [backlog do Android](../BACKLOG.md) e continuará evoluindo com mentoria do time.

---

## 📋 Próximos passos quando ficar expert

- Aprender **testes unitários e de UI** com Compose
- Integrar com **APIs reais** (Retrofit, autenticação)
- Trabalhar com **banco de dados local** (Room)
- Implementar **injeção de dependência** (Hilt)
- Contribuir com tarefas do backlog

Mas por enquanto, foco nas 5 wikis. 🎯

---

## 📚 Recursos adicionais

- [Guia de Contribuição](../CONTRIBUTING.md) — padrões de commit e PR
- [Arquitetura do Projeto](../README.md) — documentação técnica completa
- [Backlog Android](../BACKLOG.md) — tarefas prontas para contribuir
- [Planning & Status](../ANDROID_STATUS_REAL.md) — estado atual do projeto

---

**Boa jornada! Você vai se surpreender com o que consegue construir.** 🚀

Qualquer dúvida, pergunte. A comunidade está aqui para ajudar.