# 🎨 Wiki 04 — Criação de UI

## 🎯 Objetivo

Ao final desta wiki, você vai construir um componente visual completo usando Jetpack Compose e seguindo os tokens de design do app. Você criará um card reutilizável, o testará com `@Preview`, e entenderá como componentes bem feitos facilitam a vida de quem quer reaproveitar código.

---

## 📋 Pré-requisitos

- [Wiki 01 — Setup do Projeto](01-Setup-Projeto.md), [Wiki 02 — Arquitetura e Padrões](02-Arquitetura-e-Padroes.md) e [Wiki 03 — Fluxo de Contribuição](03-Fluxo-de-Contribuicao.md) concluídas.
- Conhecimento básico de Kotlin (tipos, classes, null-safety).

---

## 📖 Conteúdo

### 1. Anatomia de um Composable simples

Um Composable é uma **função anotada com `@Composable`** que descreve um pedaço de UI. Aqui está um exemplo bem pequeno:

```kotlin
@Composable
fun SimpleGreeting(name: String) {
    Text(
        text = "Oi, $name!",
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )
}

@Preview
@Composable
fun SimpleGreetingPreview() {
    SimpleGreeting("Maria")
}
```

Notem:
- `@Composable` marca a função como elemento UI
- Recebe dados como parâmetro (`name: String`)
- `@Preview` permite visualizar no editor sem rodar o app
- Usa `Text()`, que é um Composable built-in

### 2. Layout básico: Column, Row, Box

Compose tem três layouts fundamentais:

**`Column`** — coloca itens **verticalmente**:

```kotlin
@Composable
fun MyColumn() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Primeira linha")
        Text("Segunda linha")
        Text("Terceira linha")
    }
}
```

**`Row`** — coloca itens **horizontalmente**:

```kotlin
@Composable
fun MyRow() {
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Esquerda")
        Text("Centro")
        Text("Direita")
    }
}
```

**`Box`** — coloca itens em **camadas** (tipo um `div` com `position: relative`):

```kotlin
@Composable
fun MyBox() {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(Color.Blue)
    ) {
        Text("Conteúdo dentro da box", modifier = Modifier.align(Alignment.Center))
    }
}
```

### 3. Criando um componente: ActivityCard

Vamos criar um card que mostra uma atividade do bebê (ex.: alimentação, sono). Aqui está o passo a passo:

**Passo 1 — Defina o modelo de dados:**

```kotlin
// Em app/src/main/java/.../models/Activity.kt
data class Activity(
    val id: String,
    val title: String,
    val description: String,
    val icon: String,  // nome de um ícone ou emoji
    val timestamp: String
)
```

**Passo 2 — Crie o Composable:**

```kotlin
// Em app/src/main/java/.../ui/components/ActivityCard.kt

@Composable
fun ActivityCard(
    activity: Activity,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Ícone
            Text(
                text = activity.icon,
                fontSize = 32.sp,
                modifier = Modifier.size(48.dp)
            )
            
            // Conteúdo
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = activity.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = activity.description,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
            
            // Timestamp
            Text(
                text = activity.timestamp,
                fontSize = 10.sp,
                color = Color.LightGray,
                modifier = Modifier.align(Alignment.Top)
            )
        }
    }
}
```

**Passo 3 — Adicione um `@Preview` para testar:**

```kotlin
@Preview(showBackground = true)
@Composable
fun ActivityCardPreview() {
    val mockActivity = Activity(
        id = "1",
        title = "Alimentação",
        description = "Leite materno - 150ml",
        icon = "🍼",
        timestamp = "14:30"
    )
    ActivityCard(activity = mockActivity)
}
```

Salve, e você verá o card renderizar no painel direito do Android Studio — é instantâneo, sem compilar o app inteiro!

### 4. Usando tokens de design (cores, tipografia, espaçamento)

O card acima usa cores hardcoded. **Isso é errado.** Sempre use os tokens:

```kotlin
// ❌ Errado
Text("Alimentação", color = Color(0xFF000000))

// ✅ Certo
Text("Alimentação", color = MaterialTheme.colorScheme.onSurface)
```

Os tokens disponíveis vêm de `theme/`:

```kotlin
@Composable
fun ActivityCard(
    activity: Activity,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),  // ✅ Token
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface  // ✅ Token
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = activity.icon,
                fontSize = 32.sp,
                modifier = Modifier.size(48.dp)
            )
            
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = activity.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,  // ✅ Token
                    color = MaterialTheme.colorScheme.onSurface  // ✅ Token
                )
                Text(
                    text = activity.description,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant  // ✅ Token
                )
            }
            
            Text(
                text = activity.timestamp,
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.outline,  // ✅ Token
                modifier = Modifier.align(Alignment.Top)
            )
        }
    }
}
```

### 5. State em Composables (quando o usuário interage)

Se o componente precisa reagir a interação (ex.: botão é pressionado, input muda), use `remember` e `mutableStateOf`:

```kotlin
@Composable
fun CounterButton() {
    var count by remember { mutableStateOf(0) }  // ← estado local
    
    Button(
        onClick = { count++ },
        modifier = Modifier.padding(16.dp)
    ) {
        Text("Clicado $count vezes")
    }
}

@Preview
@Composable
fun CounterButtonPreview() {
    CounterButton()
}
```

Toda vez que `count` muda, o Composable é redesenhado — é a "reatividade" do Compose.

### 6. Boas práticas ao criar componentes

1. **Sempre deixe `modifier` como parâmetro padrão** — assim quem usa o componente consegue ajustar posição, padding, alinhamento, etc. Este é um padrão universal em Compose:

```kotlin
@Composable
fun MyComponent(modifier: Modifier = Modifier) {  // ← sempre este padrão
    Box(modifier = modifier) {
        // usar modifier dentro
    }
}

// Quem usa consegue customizar:
MyComponent(modifier = Modifier.padding(16.dp))
MyComponent(modifier = Modifier.fillMaxWidth().align(Alignment.Center))
```

2. **Extraia subcomponentes** — se um Composable fica muito grande, quebre em pedaços:

```kotlin
@Composable
fun ActivityCard(...) {
    Card(...) {
        Row(...) {
            ActivityCardIcon(...)        // ← sub-componente
            ActivityCardContent(...)     // ← sub-componente
            ActivityCardTimestamp(...)   // ← sub-componente
        }
    }
}
```

3. **Sempre deixe `@Preview`** — assim você testa visual sem rodar o app inteiro.

4. **Use `@Preview` com parâmetros** — teste diferentes estados:

```kotlin
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Preview(showBackground = true, backgroundColor = 0xFF000000)  // dark mode
@Composable
fun ActivityCardPreview() {
    ActivityCard(mockActivity)
}
```

---

## 🚑 Erros comuns

| Problema | Solução |
|----------|---------|
| `@Preview` não renderiza | Salve o arquivo. Se ainda não aparecer, tente **Build → Clean Project** ou **invalidate Caches**. |
| Composable muito grande | Quebre em sub-composables. Regra: se passou de 100-150 linhas, faltou extrair coisas. |
| Hardcodei uma cor e agora o dark mode quebrou | Sempre use tokens (MaterialTheme.colorScheme.*). Nunca `Color(0xFF...)` diretamente. |
| Cliquei no botão mas nada aconteceu | Você definiu o `onClick` mas talvez o `clickable` modifier não está no lugar certo. Procure por `Button(onClick = { ... })`. |
| Estado não atualiza na UI | Use `remember { mutableStateOf(...) }` e leia com `by remember { ... }`. Se usou só `var`, não funciona. |
| Importei Colors errado | Você importou `androidx.compose.ui.graphics.Color`? Tá certo. Se importou `android.graphics.Color`, está errado (é sistema antigo). |

---

## 🧪 Tarefa de treino

Vamos criar um componente reutilizável:

1. **Crie um arquivo** `app/src/main/java/.../ui/components/BabyActivityCard.kt`

2. **Copie e adapte o código do `ActivityCard`** que mostramos acima (mude nomes, cores, espaçamentos conforme quiser)

3. **Crie um modelo** em `app/src/main/java/.../models/BabyActivity.kt`:

```kotlin
data class BabyActivity(
    val id: String,
    val type: String,  // "Feeding", "Sleep", "Diaper", etc.
    val duration: String,  // "2 min", "1h 30min", etc.
    val notes: String,  // observações opcionais
    val timestamp: String
)
```

4. **Crie um `@Preview` que renderize o componente** com dados mockados

5. **Commit e push** (use a branch de treino se ainda estiver nela):

```bash
git add .
git commit -m "feat(android-ui): cria BabyActivityCard component"
git push
```

---

## 📬 Como entregar

Poste um comentário na Issue **"[Android] Entrega — Wiki 04: Criação de UI"** com:

1. Um **print do Android Studio** mostrando o `@Preview` do seu component renderizado
2. Um **print do código** mostrando seu Composable completo
3. Sua resposta: qual foi a maior dificuldade ao criar o componente?

O instrutor vai responder com feedback.

---

## ✅ Checklist de conclusão

- [ ] Criei um arquivo `.kt` com um Composable novo
- [ ] Usei `@Composable` e `@Preview` corretamente
- [ ] Meu componente usa tokens (MaterialTheme.colorScheme.*)
- [ ] Recebi parâmetros (dados, modifier, callbacks)
- [ ] O `@Preview` renderiza sem erros
- [ ] Testei em pelo menos 2 estados/tamanhos diferentes no @Preview
- [ ] Fiz commit com mensagem `feat(android-ui): ...`

---

**Próxima parada:** Wiki 05 — Nova Funcionalidade *(em breve)*, onde você vai juntar tudo isso e criar uma screen completa do zero.

Você está ficando expert! 🚀