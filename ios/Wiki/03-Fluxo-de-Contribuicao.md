# 🔀 Wiki 03 — Fluxo de Contribuição

## 🎯 Objetivo

Ao final desta wiki, você vai saber fazer o ciclo completo de contribuição do time: criar uma branch a partir da `main`, fazer commits, subir para o GitHub, abrir um Pull Request e responder a um code review. É o momento em que você faz sua primeira contribuição de verdade (de treino).

## 📋 Pré-requisitos

- [Wiki 01 — Setup do Projeto](01-Setup-Projeto.md) e [Wiki 02 — Arquitetura e Padrões](02-Arquitetura-e-Padroes.md) concluídas.
- Sua conta do GitHub já adicionada à organização `Escola-Nova-Era`.

---

## 📖 Conteúdo

### 1. Por que não commitamos direto na `main`

A `main` é a versão "oficial" do app, que todos compartilham. Se cada um commitasse direto nela, o projeto viraria bagunça e um erro seu quebraria o trabalho dos outros. Por isso todo trabalho acontece em uma **branch** (uma linha do tempo paralela e isolada), e só entra na `main` depois de passar por um **Pull Request** revisado.

### 2. O ciclo completo, passo a passo

Vamos supor que você vai fazer uma tarefa. O ciclo é sempre este:

#### Passo 1 — Parta da `main` atualizada

Antes de criar sua branch, garanta que sua `main` local está igual à do GitHub:

```bash
git checkout main
git pull origin main
```

`checkout main` te leva para a branch main; `pull` baixa as últimas mudanças do time.

#### Passo 2 — Crie sua branch

O time usa nomes de branch com um prefixo que indica o tipo de trabalho:

| Prefixo | Quando usar | Exemplo |
|---------|-------------|---------|
| `feature/ios-` | funcionalidade nova | `feature/ios-baby-weight` |
| `fix/ios-` | correção de bug | `fix/ios-home-crash` |
| `docs/ios-` | documentação | `docs/ios-readme` |
| `training/` | tarefas de treino da Wiki | `training/maria/summary-card` |

Como esta é uma tarefa de treino, use o prefixo `training/`:

```bash
git checkout -b training/seu-nome/primeiro-pr
```

O `-b` cria a branch e já te move para ela. Confirme onde você está:

```bash
git branch
```

A branch atual aparece com um `*` na frente.

#### Passo 3 — Faça sua mudança e veja o que mudou

Edite o que a tarefa pede (no Xcode ou no editor). Depois, veja o que o Git detectou:

```bash
git status
```

Os arquivos modificados aparecem em vermelho. Para ver exatamente o que mudou linha a linha:

```bash
git diff
```

#### Passo 4 — Prepare e faça o commit

O commit é um "ponto de salvamento" com uma mensagem que explica o que você fez.

```bash
git add BabyTracker/Screens/Home/ViewModels/HomeViewModel.swift
git commit -m "feat(ios): adiciona card de caminhada na Home"
```

> 💡 `git add .` adiciona **tudo** que mudou de uma vez. No começo, prefira adicionar arquivo por arquivo para ter controle do que está entrando no commit.

**Padrão de mensagem do time** (mesmo dos commits do projeto): `tipo(escopo): descrição no imperativo`.
- Tipos comuns: `feat` (novo), `fix` (correção), `docs` (documentação), `refactor`, `chore`.
- Exemplo: `fix(ios): corrige cor do texto no campo de senha`

#### Passo 5 — Suba a branch para o GitHub

```bash
git push -u origin training/seu-nome/primeiro-pr
```

O `-u origin` liga sua branch local à do GitHub na primeira vez. Nos próximos pushes desta branch, basta `git push`.

#### Passo 6 — Abra o Pull Request

1. Acesse o repositório no GitHub. Vai aparecer um aviso amarelo "Compare & pull request" para a branch que você acabou de subir. Clique nele. (Ou vá na aba **Pull requests** → **New pull request**.)
2. Confira: **base** deve ser `main` e **compare** a sua branch.
3. Título do PR: comece com `[TREINO]`, ex.: `[TREINO] Maria — Card de caminhada na Home`.
4. Na descrição, diga o que você fez e por quê. Anexe um print se for mudança visual.
5. Clique em **Create pull request**.

#### Passo 7 — Responda ao review

Um colega ou instrutor vai revisar e pode pedir ajustes ("Request changes"). Quando isso acontecer:

1. Faça as correções no seu editor, na **mesma branch**.
2. Commit e push de novo:

```bash
git add .
git commit -m "fix(ios): ajusta espaçamento conforme review"
git push
```

3. As mudanças **aparecem sozinhas no mesmo PR**. Não precisa abrir outro. Responda no comentário do revisor avisando que ajustou.

Quando o PR for aprovado, ele pode ser mergeado na `main`. No seu caso de treino, o PR será fechado sem merge depois do feedback (releia a seção de tarefas de treino no [índice](README.md)).

### 3. "Meu PR mostra arquivos que eu não mexi!"

Se o seu PR mostrar mudanças que não são suas, quase sempre é porque sua branch nasceu de uma `main` desatualizada ou de outra branch. Para resolver, atualize sua branch com a `main`:

```bash
git checkout main
git pull origin main
git checkout training/seu-nome/primeiro-pr
git merge main
```

Isso traz o que faltava e o PR volta a mostrar só o que é seu.

### 4. Erros comuns

| Problema | Solução |
|----------|---------|
| Commitei na `main` sem querer | Não deu push ainda? `git branch minha-branch` (salva seu trabalho numa branch) e depois `git checkout main` + `git reset --hard origin/main`. Na dúvida, **pare e peça ajuda antes de dar push.** |
| `git push` foi rejeitado ("rejected") | A branch remota tem algo que você não tem. Rode `git pull` na sua branch, resolva o que aparecer e faça push de novo. |
| Escrevi a mensagem de commit errada | Se ainda não deu push: `git commit --amend -m "nova mensagem"`. |
| Não sei em que branch estou | `git branch` (a atual tem `*`) ou `git status` (a primeira linha diz). |

---

## 🧪 Tarefa de treino

Sua primeira contribuição de verdade (de treino):

1. Parta da `main` atualizada e crie a branch `training/seu-nome/primeiro-pr`.
2. Refaça a mudança da Wiki 02: adicione um `SummaryItem` novo no `HomeViewModel` (pode ser o card de caminhada). Rode o app e confirme que aparece.
3. Faça o commit seguindo o padrão de mensagem do time.
4. Suba a branch e abra um Pull Request com título começando em `[TREINO]`.
5. Na descrição do PR, explique a mudança e anexe um print da Home com o card novo.

## 📬 Como entregar

Diferente das wikis anteriores, aqui a **sua entrega é o próprio Pull Request**. Cole o **link do seu PR** num comentário na Issue **"[iOS] Entrega — Wiki 03: Fluxo de Contribuição"**.

O feedback virá como um code review no seu PR. Pratique respondê-lo: faça os ajustes, commit e push, e veja o PR atualizar.

## ✅ Checklist de conclusão

- [ ] Criei uma branch `training/...` a partir da `main` atualizada
- [ ] Fiz commit com mensagem no padrão `tipo(escopo): descrição`
- [ ] Subi a branch com `git push`
- [ ] Abri um PR com título `[TREINO] ...` e descrição explicando a mudança
- [ ] Recebi o review, fiz um ajuste e vi o PR atualizar com o novo push

**Próxima parada:** Wiki 04 — Criação de UI *(em breve)*, onde você vai construir um componente visual do zero usando o tema do app.
