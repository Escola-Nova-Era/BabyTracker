# 🔀 Wiki 03 — Fluxo de Contribuição

## 🎯 Objetivo

Ao final desta wiki, você vai saber fazer o ciclo completo de contribuição do time: criar uma branch a partir da `main`, fazer commits, subir para o GitHub, abrir um Pull Request e responder a code review. É o momento em que você faz sua primeira contribuição de verdade (de treino).

---

## 📋 Pré-requisitos

- [Wiki 01 — Setup do Projeto](01-Setup-Projeto.md) e [Wiki 02 — Arquitetura e Padrões](02-Arquitetura-e-Padroes.md) concluídas.
- Sua conta do GitHub já adicionada à organização `Escola-Nova-Era`.

---

## 📖 Conteúdo

### 1. Por que não commitamos direto na `main`

A `main` é a versão "oficial" do app, que todos compartilham. Se cada um commitasse direto nela, o projeto viraria bagunça e um erro seu quebraria o trabalho de todo mundo. Por isso todo trabalho acontece em uma **branch** (uma linha do tempo paralela e isolada), e só entra na `main` depois de passar por um **Pull Request** revisado.

Pense em branch como um espaço seguro só seu para mexer sem medo de quebrar nada.

### 2. O ciclo completo, passo a passo

Vamos supor que você vai fazer uma tarefa. O ciclo é sempre este:

#### Passo 1 — Garanta que sua `main` local está atualizada

Antes de criar sua branch, puxe as últimas mudanças do time:

```bash
git checkout main
git pull origin main
```

- `checkout main` te leva para a branch main
- `pull` baixa as últimas mudanças do GitHub

#### Passo 2 — Crie sua branch com nome descritivo

O time usa nomes de branch com um **prefixo** que indica o tipo de trabalho. Isso facilita organizar e filtrar branches no GitHub:

| Prefixo | Quando usar | Exemplo |
|---------|-------------|---------|
| `feature/` | Funcionalidade nova | `feature/android-home-screen` |
| `fix/` | Correção de bug | `fix/android-bottom-bar-crash` |
| `docs/` | Documentação ou wiki | `docs/android-setup-guide` |
| `refactor/` | Refatoração de código (sem novos recursos) | `refactor/android-viewmodel-cleanup` |
| `chore/` | Tarefas de manutenção (deps, config) | `chore/android-update-gradle` |
| `training/` | Tarefas de treino das Wikis | `training/seu-nome/primeiro-pr` |

Como esta é uma tarefa de treino, use:

```bash
git checkout -b training/seu-nome/primeiro-pr
```

O `-b` cria a branch e já te move para ela. Confirme onde você está:

```bash
git branch
```

A branch atual vai ter um `*` na frente.

#### Passo 3 — Faça sua mudança

Abra o Android Studio e mude o que a tarefa pede. Por exemplo, pode ser adicionar um `Text()` novo em uma tela.

Depois de salvar, veja o que o Git detectou:

```bash
git status
```

Mostra os arquivos que mudaram (em vermelho). Para ver exatamente o que mudou linha a linha:

```bash
git diff
```

#### Passo 4 — Prepare e commit

O commit é um "ponto de salvamento" com uma mensagem explicando o que você fez.

```bash
git add app/src/main/java/com/escolanovaeratech/babytracker/theme/Color.kt
git commit -m "feat(android-theme): adiciona cor para novo card"
```

> 💡 `git add .` adiciona **tudo** que mudou de uma vez. No começo, prefira adicionar arquivo por arquivo para ter controle.

**Padrão de mensagem do time** (Conventional Commits):

```
<tipo>(<escopo>): <descrição no imperativo>
```

- **tipos**: `feat` (novo), `fix` (correção), `docs` (doc), `refactor`, `chore` (manutenção)
- **escopo**: `android`, ou `android-login`, `android-theme`, etc.
- **descrição**: verbo no imperativo, minúsculo, sem ponto final

**Exemplos bons:**
```
feat(android): cria screen de home
fix(android-navigation): corrige rota do timeline
docs(android): adiciona guia de setup
```

**Exemplos ruins:**
```
fix: arrumar
adicionado novo componente
atualizado codigo
```

#### Passo 5 — Suba a branch para o GitHub

```bash
git push -u origin training/seu-nome/primeiro-pr
```

O `-u origin` conecta sua branch local à do GitHub na primeira vez. Nos próximos pushes, basta `git push`.

#### Passo 6 — Abra o Pull Request

1. Vá para [github.com/Escola-Nova-Era/BabyTracker](https://github.com/Escola-Nova-Era/BabyTracker)
2. Aparecerá um aviso amarelo "Compare & pull request" para sua branch. Clique nele.
3. Confira:
   - **Base branch** (onde vai entrar): `main`
   - **Compare branch** (de onde vem): sua branch (`training/seu-nome/primeiro-pr`)
4. Título do PR: comece com `[TREINO]`, ex.: `[TREINO] Maria — Exercício de PR`
5. Na descrição, explique o que você fez e por quê. Anexe um print se for mudança visual (arrasta a imagem pro campo de texto que o GitHub anexa automaticamente).
6. Clique em **Create pull request**

#### Passo 7 — Responda ao code review

Um colega ou instrutor vai revisar. Se pedir ajustes ("Request changes"):

1. Faça as correções no Android Studio, **na mesma branch**
2. Commit e push de novo:

```bash
git add .
git commit -m "fix(android): ajusta espaçamento conforme review"
git push
```

3. As mudanças aparecem **automaticamente no mesmo PR**. Não cria outro PR. Responda no comentário do revisor avisando que ajustou.

Quando o PR for aprovado (no caso de treino, ele será fechado sem merge — é só para praticar feedback).

### 3. Quando seu PR mostrar arquivos que você não mexeu

Se o GitHub mostra arquivos que não são seus no PR, quase sempre é porque sua branch nasceu de uma `main` desatualizada. Para sincronizar:

```bash
git checkout main
git pull origin main
git checkout training/seu-nome/primeiro-pr
git merge main
```

Isso traz o que faltava e o PR volta a mostrar só o que é seu.

### 4. Dicas ninja

**Não commitei na branch errada por acaso?**

Se você commitou na `main` sem querer e ainda não fez `push`:

```bash
git branch minha-branch    # salva o trabalho em uma branch
git checkout main
git reset --hard origin/main  # volta a main para o estado remoto
```

Na dúvida, **pare e peça ajuda antes de dar `push`**.

**Escrevi uma mensagem de commit errada?**

Se ainda não deu push:

```bash
git commit --amend -m "nova mensagem"
```

**Como ver em que branch estou?**

```bash
git branch         # mostra todas, a atual tem *
git status         # primeira linha diz em qual branch você está
```

---

## 🚑 Erros comuns

| Problema | Solução |
|----------|---------|
| Commitei na `main` sem querer | Se não fez push, crie uma branch com seu trabalho (`git branch minha-branch`), depois volta a main com `git reset --hard origin/main`. |
| `git push` foi rejeitado ("rejected") | A branch remota tem algo que você não tem. Rode `git pull` na sua branch, resolva e tenta de novo. |
| Meu PR mostra commits de outras pessoas | Sua branch nasceu de uma `main` desatualizada. Sincronize com `git merge main` (na sua branch). |
| Não consigo abrir PR no GitHub | Você criou a branch mas não fez `git push -u origin ...`? Sobe a branch primeiro. |
| "You don't have permission to push to this repository" | Você não tem acesso. Veja se sua conta GitHub está na organização `Escola-Nova-Era`. |

---

## 🧪 Tarefa de treino

Sua primeira contribuição de verdade:

1. **Saia da `main` atualizada** (`git checkout main && git pull origin main`)
2. **Crie a branch** `training/seu-nome/primeiro-pr`
3. **Faça uma mudança pequena**: abra um arquivo `.kt` qualquer e adicione um comentário seu, ex.:

```kotlin
// Mudança de treino - seu nome aqui
```

4. **Commit e push**:
```bash
git add .
git commit -m "chore(android): adiciona comentário de treino"
git push -u origin training/seu-nome/primeiro-pr
```

5. **Abra o Pull Request** no GitHub com título `[TREINO] Seu Nome — Primeiro PR`
6. **Na descrição**, explique: "Esta é minha primeira contribuição ao BabyTracker Android como parte do treino de Wiki 03"

---

## 📬 Como entregar

Diferente das wikis anteriores, aqui a **sua entrega é o próprio Pull Request**. Poste o **link do seu PR** num comentário na Issue **"[Android] Entrega — Wiki 03: Fluxo de Contribuição"**.

O feedback virá como um code review no seu PR. **Pratique respondê-lo**: faça ajustes, commit e push, e veja o PR atualizar sozinho.

---

## ✅ Checklist de conclusão

- [ ] Criei uma branch `training/seu-nome/...` a partir da `main` atualizada
- [ ] Fiz uma mudança pequena (comentário é suficiente)
- [ ] Fiz commit com mensagem no padrão `tipo(escopo): descrição`
- [ ] Subi a branch com `git push -u origin ...`
- [ ] Abri um PR com título `[TREINO] ...` 
- [ ] Escrevi uma descrição explicando a mudança
- [ ] Recebi o review, fiz um ajuste e vi o PR atualizar

---

**Próxima parada:** Wiki 04 — Criação de UI *(em breve)*, onde você vai construir um componente visual do zero usando tokens do app.

Você está indo bem! 🚀