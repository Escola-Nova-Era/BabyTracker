# 🏛️ Wiki 02 — Arquitetura e Padrões

## 🎯 Objetivo

Ao final desta wiki, você vai entender como o código do BabyTracker Android é organizado, o que é cada pasta, como construímos uma tela (padrão MVVM), e quais regras de estilo o time segue. Isso é o que separa "fazer funcionar" de "contribuir do jeito do time".

---

## 📋 Pré-requisitos

- [Wiki 01 — Setup do Projeto](01-Setup-Projeto.md) concluída (app rodando no emulador).
- Conhecimento básico de Kotlin (se não tem, não desanima — vamos explicando conforme a gente anda).

---

## 📖 Conteúdo

### 1. O mapa do código

Tudo que interessa vive em `android/app/src/main/`:

```
java/com/escolanovaeratech/babytracker/
├── MainActivity2.kt            ← ponto de entrada da app (@main)
├── navigation/
│   └── NavGraph.kt             ← rotas e navegação (Compose Navigation)
├── theme/                      ← design tokens (cores, tipografia, formas)
│   ├── Color.kt
│   ├── Type.kt
│   ├── Shape.kt
│   └── Theme.kt
├── ui/
│   ├── components/             ← componentes compartilhados
│   │   ├── BarrarInferior.kt   ← bottom navigation
│   │   └── ...
│   └── screens/                ← telas do app (Screen1-4, futuros)
├── common/                     ← estrutura para features comuns
│   ├── data/
│   └── ui/
├── home/                       ← feature Home (MVVM)
│   ├── data/
│   └── ui/
├── insights/                   ← feature Insights (estrutura pronta)
│   ├── data/
│   └── ui/
├── login/                      ← feature Login (MVVM)
│   ├── data/
│   │   └── api/
│   └── ui/
├── profile/                    ← feature Profile (MVVM)
│   ├── data/
│   └── ui/
├── timeline/                   ← feature Timeline (MVVM)
│   ├── data/
│   └── ui/
└── network/
    └── NetworkModule.kt        ← Retrofit setup

res/
├── values/
│   ├── colors.xml
│   ├── themes.xml
│   └── shapes.xml
├── values-night/               ← dark mode
└── drawable/
```

**O fluxo**: `MainActivity2` carrega `BabyTrackingTheme`, que configura `BabyTrackerAppNavGraph` (navegação), que monta os `NavHost` com as telas.

```kotlin
NavHost(navController = navController, startDestination = "Screen1") {
    composable(route = "Screen1") { Screen1() }
    composable(route = "Timeline") { TimelineScreen() }
    composable(route = "Screen3") { Screen3() }
    composable(route = "Screen4") { Screen4() }
}
```

### 2. Arquitetura: MVVM Flat com Features

O projeto usa **MVVM** (Model, View, ViewModel) mas de forma "achatada" — sem pastas separadas em Presentation/Domain/Data. Cada **feature** (como Home, Profile) é autossuficiente:

```
feature/home/
├── data/                    ← dados, APIs, banco (Repository pattern)
├── ui/                      ← UI do Compose + ViewModel
│   ├── HomeScreen.kt        ← a tela em Compose
│   ├── HomeViewModel.kt     ← estado e lógica
│   └── components/          ← pedaços visuais (cards, botões)
│       ├── HomeCard.kt
│       └── ActionButton.kt
└── models/                  ← tipos exclusivos da feature (se houver)
```

**Cada camada tem um papel claro:**

| Camada | Papel | Exemplo na Home |
|--------|-------|-----------------|
| **Model** | Dados puros. Struct/class simples. | `Activity` (id, tipo, timestamp, dados) |
| **ViewModel** | Gerencia estado e lógica. Usa `StateFlow`, `ViewModel`. | `HomeViewModel` expõe `uiState: StateFlow<HomeUiState>` |
| **UI (Composable)** | Só layout. Lê o estado do ViewModel e desenha. | `HomeScreen` observa `homeViewModel.uiState` e renderiza |
| **Components** | Pedaços visuais reutilizáveis. | `ActivityCard`, `QuickActionButton` |

**Na prática, ViewModel em Kotlin:**

```kotlin
class HomeViewModel : ViewModel() {
    // StateFlow avisa a UI quando o valor muda
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun loadActivities() {
        // lógica aqui
        _uiState.value = HomeUiState.Success(activities)
    }
}

// A UI observa:
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    
    when (uiState) {
        is HomeUiState.Loading -> Text("Carregando...")
        is HomeUiState.Success -> ShowActivities(uiState.activities)
        is HomeUiState.Error -> Text("Erro: ${uiState.message}")
    }
}
```

**Regra do time:** quando criar uma tela nova, use essa mesma estrutura. Nada de Composable gigante com tudo junto.

### 3. Design Tokens (pasta `theme/`)

Aqui mora a regra mais importante de UI: **nunca escreva cor, fonte ou espaçamento na mão**. O projeto tem um catálogo centralizado.

**`Color.kt`** — paleta de cores semântica:

| Tipo | Exemplo | Uso |
|------|---------|-----|
| Primária | `primary = Color(0xFF7C5ACE)` | botões, foco, destaques |
| Secundária | `secondary = Color(0xFF4DB8A8)` | acentos complementares |
| De estado | `success`, `error`, `warning` | feedback (sucesso, erro) |
| Texto | `onPrimary`, `onSurface` | texto sobre cada background |
| Background | `background`, `surface` | fundos, cards, superfícies |

**Certo vs Errado:**

```kotlin
// ❌ Hardcoded: quebra a consistência
Text("Home")
    .foregroundStyle(Color(0xFF7C5ACE))

// ✅ Com token: consistente em toda a app
Text("Home")
    .foregroundStyle(MaterialTheme.colorScheme.primary)
```

**`Type.kt`** — tipografia:

```kotlin
// Definido em Type.kt
val typography = Typography(
    headlineLarge = TextStyle(fontSize = 32.sp, fontWeight = FontWeight.Bold),
    bodyLarge = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal),
    ...
)
```

**`Shape.kt`** — formas e border radius:

```kotlin
val shapes = Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp)
)
```

**Se precisar de um valor que não existe**, não hardcode — **propõe no PR** e adiciona ao token.

### 4. Navegação (Jetpack Navigation Compose)

O app usa **Navigation Compose** para ir entre telas. Você não vai criar telas com Activities antigas — tudo é Composable.

As rotas estão em `NavGraph.kt`:

```kotlin
@Composable
fun BabyTrackerAppNavGraph() {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen() }
        composable("profile") { ProfileScreen() }
        composable("timeline") { TimelineScreen() }
        composable("insights") { InsightsScreen() }
    }
}
```

Para navegar:

```kotlin
// Em um Composable, injete o navController:
Button(onClick = { navController.navigate("profile") }) {
    Text("Ir para Profile")
}
```

### 5. Dependências e Retrofit (rede)

O app usa **Retrofit** para chamar APIs. Está configurado em `network/NetworkModule.kt`:

```kotlin
object NetworkModule {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:3000/api/")  // URL da API (note o /api/)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()
}
```

> ⚠️ **Importante**: Certifique-se de que a `baseUrl` inclui o prefixo `/api/` no final (se o backend exigir). Se o backend responde em `/api/auth/login`, a URL base deve terminar em `/api/` — assim a chamada fica correta: `http://10.0.2.2:3000/api/ + auth/login = http://10.0.2.2:3000/api/auth/login`.

Para fazer uma chamada, você cria uma **interface Service**:

```kotlin
interface AuthService {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): AuthResponse
}

// Na ViewModel:
class LoginViewModel : ViewModel() {
    val authService = NetworkModule.retrofit.create(AuthService::class.java)
    
    fun login(email: String, password: String) {
        viewModelScope.launch {
            val response = authService.login(LoginRequest(email, password))
            // atualizar UI com o resultado
        }
    }
}
```

### 6. Regras de estilo do time

- **Nomes**: tipos em `PascalCase` (`HomeViewModel`), variáveis e funções em `camelCase` (`loadActivities`).
- **Organização**: imports em ordem (stdlib → android → projeto).
- **Composables**: sempre use `@Composable` antes da função. Se tiver preview, use `@Preview`.
- **Nullable**: use `?` com moderação. Prefira valores não-null com lógica defensiva.
- **Comentários**: explique *por quê*, não *o quê* (o código já mostra o quê).

---

## 🚑 Erros comuns

| Problema | Solução |
|----------|---------|
| "Não consigo entender MVVM" | Comece simples: Model = struct com dados, ViewModel = classe que guarda estado, View = Composable que desenha. Pronto. Depois expande. |
| Criei um componente gigante | Quebre em sub-componentes. Se um Composable é maior que 100 linhas, faltou extrair coisas. |
| Usei hardcoded color(0xFFFFFFFF) | Procure o token certo em Color.kt. Se não existir, proponha no PR. Nunca hardcode. |
| ViewModel demora a carregar estado | Use `StateFlow` (não `MutableState` puro) e recolha em `collectAsState()` no Composable. |
| Importei tudo de android.R | Você está usando View system legado (Android antigo). Aqui usamos Compose. Mude seus imports. |
| Meu Composable não redesenha quando estado muda | Certifique-se de que está observando `collectAsState()` ou usando `remember { mutableStateOf() }`. |

---

## 🧪 Tarefa de treino

Vamos explorar a estrutura sem mexer em código:

1. **Abra o projeto no Android Studio** e navegue por `app/src/main/java/com/escolanovaeratech/babytracker/`.
2. **Olhe a estrutura de uma feature** (ex.: `login/`). Anote: quantas pastas ela tem? Qual arquivo é o maior?
3. **Procure em `Color.kt`** e liste 5 cores que existem.
4. **Abra `NavGraph.kt`** e anote: quantas rotas estão registradas? Qual é a rota inicial (`startDestination`)?
5. **Procure um `@Composable`** (ex.: `HomeScreen.kt`) e veja se tem `@Preview` embaixo. O que ele te mostra no editor?

---

## 📬 Como entregar

Poste um comentário na Issue **"[Android] Entrega — Wiki 02: Arquitetura e Padrões"** com:

1. Respostas numeradas das 5 perguntas da tarefa
2. Um **print do Android Studio** mostrando a estrutura de pastas (com a feature `login/` expandida)
3. Um **print do NavGraph.kt** mostrando as rotas
4. Um **print de um @Preview** no editor (se conseguir abrir um arquivo com Composable + Preview)

O instrutor vai responder com feedback.

---

## ✅ Checklist de conclusão

- [ ] Entendo o mapa de pastas (java, res, theme, navigation, etc.)
- [ ] Entendo o fluxo: MainActivity2 → BabyTrackingTheme → NavGraph → Screens
- [ ] Sei o que é Model, ViewModel, UI (View)
- [ ] Entendo StateFlow e collectAsState()
- [ ] Procurei em Color.kt e vi tokens de cor
- [ ] Vi como uma tela (ex.: LoginScreen) está estruturada
- [ ] Abri um @Preview e vi ele renderizar na direita
- [ ] Entendo que não devo hardcodar cores/fontes

---

**Próxima parada:** Wiki 03 — Fluxo de Contribuição *(em breve)*, onde você vai fazer sua primeira branch e Pull Request.

Bom trabalho! 🚀