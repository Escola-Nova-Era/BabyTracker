# 🏛️ Wiki 02 — Arquitetura e Padrões

## 🎯 Objetivo

Ao final desta wiki, você vai saber como o código do BabyTracker iOS é organizado, o que é cada pasta, como uma tela é construída (padrão MVVM) e quais regras de estilo o time segue. Isso é o que separa "fazer funcionar" de "contribuir do jeito do time".

## 📋 Pré-requisitos

- [Wiki 01 — Setup do Projeto](01-Setup-Projeto.md) concluída (app rodando no simulador).

---

## 📖 Conteúdo

### 1. O mapa do código

Tudo que interessa vive em `ios/BabyTracker/`:

```
BabyTracker/
├── BabyTrackerApp.swift    ← ponto de entrada do app (@main)
├── App/
│   └── ContentView.swift   ← primeira view carregada
├── Screens/                ← uma pasta por tela do app
│   ├── Home/
│   ├── Tracking/
│   ├── Insights/
│   ├── Profile/
│   ├── Login/
│   ├── CreateAccountView.swift
│   └── MainTabView.swift   ← a barra de abas que liga as 4 telas
├── Components/             ← componentes compartilhados entre telas
├── Helpers/                ← o "tema" do app (cores, espaçamentos, fontes)
├── Services/               ← comunicação com o backend (rede e autenticação)
├── Models/                 ← modelos compartilhados
├── ViewModels/             ← view models compartilhados (ex.: autenticação)
└── Resources/              ← recursos diversos
```

O fluxo de inicialização é: `BabyTrackerApp` → `ContentView` → `MainTabView`, que monta a `TabView` com as quatro telas:

```swift
TabView {
    HomeView()
        .tabItem { Label("Home", systemImage: "house") }

    TrackingView()
        .tabItem { Label("Tracking", systemImage: "map") }

    InsightsView()
        .tabItem { Label("Insights", systemImage: "chart.bar") }

    ProfileView()
        .tabItem { Label("Profile", systemImage: "person") }
}
.tint(AppColors.primary)
```

### 2. Anatomia de uma feature

Cada tela segue a mesma estrutura de pastas. Olhe a Home como referência:

```
Screens/Home/
├── HomeView.swift              ← a View principal da tela
├── Models/
│   ├── SummaryItem.swift       ← dados que a tela exibe
│   └── ActionButton.swift
├── ViewModels/
│   └── HomeViewModel.swift     ← estado e lógica da tela
└── Components/
    ├── SummaryCard.swift       ← pedaços visuais reutilizáveis
    └── QuickActionButton.swift
```

Esse é o padrão **MVVM** (Model, View, ViewModel). Cada camada tem um papel:

| Camada | Papel | Exemplo na Home |
|--------|-------|-----------------|
| **Model** | Só dados. Struct simples, sem lógica de tela. | `SummaryItem` (título, valor, ícone, cores) |
| **ViewModel** | Guarda o estado da tela e a lógica. É uma `class` que a View observa. | `HomeViewModel` expõe `summaryItems` e `actionButtons` |
| **View** | Só layout. Lê o estado do ViewModel e desenha. | `HomeView` percorre `summaryItems` e desenha um `SummaryCard` para cada |
| **Components** | Pedaços visuais da tela, cada um em seu arquivo. | `SummaryCard`, `QuickActionButton` |

Na prática, a ligação entre View e ViewModel funciona assim:

```swift
// O ViewModel é uma classe observável:
final class HomeViewModel: ObservableObject {
    // @Published avisa a View quando o valor muda
    @Published private(set) var summaryItems: [SummaryItem] = [ ... ]
}
```

O `@Published` faz a mágica: quando o dado muda, toda View que observa esse ViewModel se redesenha sozinha.

**Regra do time:** ao criar uma tela nova, crie a pasta `Screens/SuaTela/` com essa mesma estrutura interna. Nada de View gigante com tudo dentro.

### 3. O tema do app (pasta Helpers)

Aqui mora a regra mais importante para quem mexe em UI: **nunca escreva cor, fonte ou espaçamento na mão**. O projeto tem um catálogo de tokens, e todo visual sai dele:

**`AppColors`** — todas as cores do app, organizadas por papel:

| Grupo | Exemplos | Uso |
|-------|----------|-----|
| Fundo | `backgroundTop`, `backgroundBottom` | gradiente de fundo das telas |
| Superfícies | `surface`, `surfaceStrong`, `surfaceMuted` | cards e campos |
| Identidade | `primary` (azul bebê), `accent` (lilás), `highlight`, `mint` | botões e destaques |
| Texto | `textPrimary`, `textSecondary`, `textTertiary` | hierarquia de texto |
| Bordas | `border`, `divider` | contornos e divisórias |
| Estados | `success` | feedback positivo |

**`AppSpacing`** — a escala de espaçamentos (4, 8, 12, 16, 20, 24, 32, 40):

```swift
VStack(spacing: AppSpacing.medium) { ... }   // ✅ do jeito do time
VStack(spacing: 16) { ... }                  // ❌ número mágico
```

**`AppTypography` e `AppTheme`** (arquivo `AppTheme.swift`) — as fontes (`largeTitle`, `body`, `footnote`, tamanhos de ícone...) e constantes globais como `AppTheme.cornerRadius`.

Compare o certo e o errado:

```swift
// ❌ Hardcoded: funciona, mas foge do padrão
Text("Last Feeding")
    .font(.system(size: 13, weight: .semibold))
    .foregroundStyle(Color.gray)

// ✅ Com tokens: consistente com o app inteiro
Text("Last Feeding")
    .font(AppTypography.footnoteStrong)
    .foregroundStyle(AppColors.textSecondary)
```

Se precisar de um valor que não existe no catálogo, a solução não é hardcodar: é propor o token novo no PR.

### 4. A camada de Services (visão geral)

Você não vai precisar mexer aqui tão cedo, mas precisa saber que existe:

- **`Services/Network/`** — o cliente HTTP do app: `NetworkClient` (faz as requisições), `APIEndpoint` (descreve cada endpoint), `HTTPMethod`, `NetworkError` e `NetworkConfiguration` (URL base da API).
- **`Services/Auth/`** — autenticação: `AuthService` (register/login contra o backend), `AuthModels` (structs de request/response) e `TokenStorage` (guarda o token no Keychain do iPhone, o cofre seguro do sistema).

Um detalhe que você vai reencontrar nos testes: o `AuthService` tem um **protocolo** (`AuthServiceProtocol`) e duas implementações, a real e a `MockAuthService`, que finge autenticar sem chamar o backend. É assim que o app funciona em desenvolvimento sem depender da API no ar.

### 5. Regras de estilo do time

- **Nomes**: tipos em `PascalCase` (`SummaryCard`), propriedades e funções em `camelCase` (`summaryItems`).
- **Estado de View**: `@State private var` (sempre `private`).
- **Constantes**: `let` sempre que o valor não muda.
- **Views**: structs conformando `View`, layout dentro de `body`.
- **Indentação**: 4 espaços (padrão do Xcode).
- **Concorrência**: prefira `async`/`await`. Evite Combine em código novo.
- **Force unwrap (`!`)**: evite. Prefira `if let`, `guard let` ou valores padrão.
- **Comentários**: só onde a lógica não é óbvia. Código claro vale mais que comentário longo.

---

## 🧪 Tarefa de treino

Uma caça ao tesouro no código (tudo local, sem commit):

1. **Ache a cor da barra de abas.** Em qual arquivo e linha a cor dos ícones da TabView é definida, e qual token ela usa?
2. **Siga o fluxo de um dado.** Abra o `HomeViewModel` e conte quantos `SummaryItem` existem. Depois ache na `HomeView` o ponto em que eles viram `SummaryCard` na tela.
3. **Mexa de verdade.** Adicione um quinto `SummaryItem` no `HomeViewModel` (ex.: `"Last Walk"`, valor `"1h ago"`, ícone `"figure.walk"`, use `AppColors.yellowSoft` no tint). Rode o app e veja seu card aparecer na Home.
4. **Desfaça como gente grande.** No Terminal, rode `git status` para ver o arquivo modificado e depois:

```bash
git restore BabyTracker/Screens/Home/ViewModels/HomeViewModel.swift
```

Rode `git status` de novo e confirme que ficou limpo. Você acabou de aprender a descartar mudanças locais, um comando que vai te salvar muitas vezes.

## 📬 Como entregar

Poste um comentário na Issue **"Entrega — Wiki 02"** deste repositório com:

1. Sua resposta do item 1: **arquivo e token** que definem a cor da barra de abas.
2. Sua resposta do item 2: **quantos `SummaryItem` existem** e em qual arquivo eles viram `SummaryCard`.
3. Um **print da Home com o seu quinto card** aparecendo no simulador.
4. Um **print do `git status` limpo** depois do `git restore` (provando que você descartou a mudança).

O instrutor vai responder com o feedback na própria Issue.

## ✅ Checklist de conclusão

- [ ] Sei explicar o que vai em Models, ViewModels, Components e na View de uma feature
- [ ] Entendi o papel do `@Published` e do `ObservableObject`
- [ ] Sei onde ficam `AppColors`, `AppSpacing` e `AppTypography` e por que não se hardcoda valores
- [ ] Achei a cor da TabView e o caminho do `SummaryItem` até a tela
- [ ] Criei meu card temporário na Home e descartei com `git restore`

**Próxima parada:** Wiki 03 — Fluxo de Contribuição *(em breve)*, onde você vai criar sua primeira branch de treino e abrir seu primeiro PR.
