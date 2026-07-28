# ✨ Wiki 05 — Nova Funcionalidade

## 🎯 Objetivo

Ao final desta wiki, você vai criar uma **feature completa do zero**: tela em Compose, ViewModel com gerenciamento de estado, modelo de dados, e integração ao NavGraph. Você será capaz de entregar uma funcionalidade inteira, pronta para produção (bem, quase — faltará testes e algumas validações).

---

## 📋 Pré-requisitos

- [Wiki 01 — Setup do Projeto](01-Setup-Projeto.md), [Wiki 02 — Arquitetura e Padrões](02-Arquitetura-e-Padroes.md), [Wiki 03 — Fluxo de Contribuição](03-Fluxo-de-Contribuicao.md) e [Wiki 04 — Criação de UI](04-Criacao-de-UI.md) concluídas.
- Conhecimento de Kotlin, Compose, MVVM e StateFlow (recapitulado aqui, mas ajuda ter visto).

---

## 📖 Conteúdo

### 1. Planejamento: Do zero ao código

Antes de abrir o Android Studio, sempre pergunte-se:

1. **O que a funcionalidade faz?** (descrição clara)
2. **Quais dados ela exibe?** (modelos de dados)
3. **Quais ações o usuário pode fazer?** (interações)
4. **Como ela se conecta ao resto da app?** (navegação, dependências)

**Exemplo: Feature "Relatorio de Atividades"**
- O que faz: mostra um gráfico com as atividades do último mês
- Dados: lista de atividades, totalizador, período selecionado
- Ações: mudar período (semana/mês/ano), refresh
- Conexão: tab própria no bottom bar

### 2. Estrutura de pastas da feature

Cada feature é uma pasta com essa estrutura:

```
relatorio/
├── data/
│   ├── models/
│   │   └── RelatarioData.kt        ← tipos exclusivos
│   ├── repository/
│   │   └── RelatarioRepository.kt  ← busca dados (mock ou real)
│   └── (api/ — se chamar backend)
├── ui/
│   ├── RelatarioScreen.kt          ← main Composable (tela)
│   ├── RelatarioViewModel.kt       ← gerencia estado e lógica
│   └── components/
│       ├── ChartCard.kt
│       └── PeriodSelector.kt
└── RelatarioEvent.kt               ← eventos opcionais (se usar)
```

### 3. Passo a passo: Criando RelatórioScreen

Vamos criar uma tela simplificada que mostra um relatorio de atividades.

#### Passo 1 — Defina os modelos de dados

**Arquivo:** `app/src/main/java/.../relatorio/data/models/RelatarioData.kt`

```kotlin
package com.escolanovaeratech.babytracker.relatorio.data.models

data class AtividadeComTotalizador(
    val tipo: String,        // "Feeding", "Sleep", "Diaper"
    val totalDia: Int,       // 5 (5 vezes no dia)
    val ultimoHorario: String // "14:30"
)

data class RelatorioDia(
    val data: String,                          // "12/01/2025"
    val atividades: List<AtividadeComTotalizador>
)

// UI State
sealed class RelatarioUiState {
    object Loading : RelatarioUiState()
    data class Success(val relatorio: RelatorioDia) : RelatarioUiState()
    data class Error(val message: String) : RelatarioUiState()
}
```

#### Passo 2 — Crie o ViewModel

**Arquivo:** `app/src/main/java/.../relatorio/ui/RelatarioViewModel.kt`

```kotlin
package com.escolanovaeratech.babytracker.relatorio.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.escolanovaeratech.babytracker.relatorio.data.models.*

class RelatarioViewModel : ViewModel() {
    
    // State (privado para manutenção, exposto como StateFlow)
    private val _uiState = MutableStateFlow<RelatarioUiState>(RelatarioUiState.Loading)
    val uiState: StateFlow<RelatarioUiState> = _uiState.asStateFlow()
    
    private val _periodo = MutableStateFlow("dia")  // "dia", "semana", "mês"
    val periodo: StateFlow<String> = _periodo.asStateFlow()
    
    // Inicializa
    init {
        loadRelatorio()
    }
    
    // Buscar dados (simulado)
    private fun loadRelatorio() {
        viewModelScope.launch {
            try {
                // Simular delay de rede
                kotlinx.coroutines.delay(1000)
                
                // Dados mock
                val mockRelatorio = RelatorioDia(
                    data = "12/01/2025",
                    atividades = listOf(
                        AtividadeComTotalizador("Feeding", 5, "14:30"),
                        AtividadeComTotalizador("Sleep", 2, "20:15"),
                        AtividadeComTotalizador("Diaper", 7, "18:45")
                    )
                )
                
                _uiState.value = RelatarioUiState.Success(mockRelatorio)
            } catch (e: Exception) {
                _uiState.value = RelatarioUiState.Error(e.message ?: "Erro desconhecido")
            }
        }
    }
    
    // Evento: trocar período
    fun mudarPeriodo(novoPeriodo: String) {
        _periodo.value = novoPeriodo
        loadRelatorio()  // recarregar dados
    }
    
    // Evento: refresh manual
    fun refresh() {
        _uiState.value = RelatarioUiState.Loading
        loadRelatorio()
    }
}
```

#### Passo 3 — Crie os componentes menores

**Arquivo:** `app/src/main/java/.../relatorio/ui/components/AtividadeItem.kt`

```kotlin
package com.escolanovaeratech.babytracker.relatorio.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.MaterialTheme
import com.escolanovaeratech.babytracker.relatorio.data.models.AtividadeComTotalizador

@Composable
fun AtividadeItem(
    atividade: AtividadeComTotalizador,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = atividade.tipo,
                fontSize = 16.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Último: ${atividade.ultimoHorario}",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        
        Text(
            text = "${atividade.totalDia}x",
            fontSize = 18.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}
```

#### Passo 4 — Crie a Screen principal

**Arquivo:** `app/src/main/java/.../relatorio/ui/RelatarioScreen.kt`

```kotlin
package com.escolanovaeratech.babytracker.relatorio.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.escolanovaeratech.babytracker.relatorio.data.models.RelatarioUiState
import com.escolanovaeratech.babytracker.relatorio.ui.components.AtividadeItem

@Composable
fun RelatarioScreen(
    viewModel: RelatarioViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val periodo by viewModel.periodo.collectAsState()
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Relatório",
                fontSize = 24.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            
            IconButton(onClick = { viewModel.refresh() }) {
                Icon(Icons.Default.Refresh, contentDescription = "Atualizar")
            }
        }
        
        // Period Selector (simplificado)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            PeriodButton("Dia", periodo == "dia") { viewModel.mudarPeriodo("dia") }
            PeriodButton("Semana", periodo == "semana") { viewModel.mudarPeriodo("semana") }
            PeriodButton("Mês", periodo == "mes") { viewModel.mudarPeriodo("mes") }
        }
        
        // Content baseado no estado
        when (uiState) {
            is RelatarioUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is RelatarioUiState.Success -> {
                val relatorio = (uiState as RelatarioUiState.Success).relatorio
                
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(relatorio.atividades) { atividade ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surface
                            )
                        ) {
                            AtividadeItem(atividade)
                        }
                    }
                }
            }
            is RelatarioUiState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Erro: ${(uiState as RelatarioUiState.Error).message}",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

@Composable
fun PeriodButton(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
            contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
        ),
        modifier = Modifier.weight(1f)
    ) {
        Text(label)
    }
}

@Preview(showBackground = true)
@Composable
fun RelatarioScreenPreview() {
    RelatarioScreen()
}
```

### 4. Integrar ao NavGraph

Após criar a tela, registre ela no `NavGraph.kt`:

```kotlin
// Em app/src/main/java/.../navigation/NavGraph.kt

@Composable
fun BabyTrackerAppNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen() }
        composable("timeline") { TimelineScreen() }
        composable("relatorio") { RelatarioScreen() }  // ← Adicione aqui (sem acento)
        composable("profile") { ProfileScreen() }
    }
}
```

E no `BarraInferior`, adicione o botão que navega para a tela:

```kotlin
NavigationBarItem(
    icon = { Icon(Icons.Default.BarChart, contentDescription = "Relatório") },
    label = { Text("Relatório") },
    selected = currentRoute == "relatorio",
    onClick = {
        navController.navigate("relatorio") {  // sem acento
            popUpTo("home")
            launchSingleTop = true
        }
    }
)
```

> 💡 **Nota**: Rotas no `NavGraph` nunca devem ter acentos — sempre use nomes sem caracteres especiais (ex.: `relatorio`, não `relatório`) para evitar problemas de encoding.

### 5. Checklist de qualidade

Antes de commitar, verifique:

- [ ] Code compila sem warnings
- [ ] `@Preview` renderiza sem erros
- [ ] Strings usam tokens de cor/tipografia
- [ ] ViewModel não guarda lógica de UI (Android specific)
- [ ] Composables são "simples" — sem muita complexidade
- [ ] Estados estão em `StateFlow`, não em `mutableStateOf` no ViewModel
- [ ] Nenhum `viewModelScope.launch` em Composable direto
- [ ] Estrutura de pastas segue o padrão

---

## 🚑 Erros comuns

| Problema | Solução |
|----------|---------|
| ViewModel não "salva" estado quando roda em background | Use `StateFlow` + `viewModelScope.launch`, não `mutableStateOf` no escopo global. |
| Coloquei lógica pesada em `@Composable` | Mova para ViewModel. Composables só devem desenhar, não calcular. |
| Composable redesenha infinitamente | Você está chamando `viewModel.refresh()` direto no Composable? Mude para um botão. |
| Estado perdido quando rotaciona a tela | ViewModel sobrevive a rotação, `mutableStateOf` não. Use `viewModel()` + `StateFlow`. |
| "No type argument has been specified" em `MutableStateFlow` | Tipo genérico não inferido. Use explícito: `MutableStateFlow<RelatarioUiState>(...)`. |
| NavGraph não reconhece minha tela | Você registrou em `NavGraph.kt`? Certifique-se do `composable("rota") { SuaTela() }`. |

---

## 🧪 Tarefa final de treino

Agora é sua vez de criar uma feature **pequena mas completa**:

1. **Escolha uma tela simples** (não precisa ser compleja):
   - Ex.: "Minhas Anotações" (lista de notas com adicionar/deletar)
   - Ex.: "Estatísticas Rápidas" (cards com números)
   - Ex.: "Agendador" (próximas atividades)

2. **Crie a estrutura de pastas** dentro de `app/src/main/java/.../sua-feature/`

3. **Defina os modelos de dados** (data class simples)

4. **Crie o ViewModel** com `StateFlow` e um método para carregar dados

5. **Crie 1-2 componentes** visuais (reutilizáveis)

6. **Crie a Screen** principal que observa o ViewModel

7. **Adicione `@Preview`** (sem precisar rodar o app)

8. **Registre no NavGraph** (opcional para esta treino, mas válioso praticar)

9. **Commit:**
```bash
git checkout -b training/seu-nome/nova-feature
git add .
git commit -m "feat(android): cria feature sua-tela com ViewModel e Compose"
git push -u origin training/seu-nome/nova-feature
```

10. **Abra PR** com título `[TREINO] Seu Nome — Implementação de sua-feature`

---

## 📬 Como entregar

Poste um comentário na Issue **"[Android] Entrega — Wiki 05: Nova Funcionalidade"** com:

1. Link do seu PR
2. Um **print do @Preview** renderizando sua Screen
3. Um **print do ViewModel** mostrando um método completo
4. Sua resposta: qual foi a parte mais interessante de criar uma feature do zero?

---

## ✅ Checklist de conclusão

- [ ] Criei a estrutura de pastas (data, ui, components)
- [ ] Defini modelos de dados com `data class`
- [ ] Criei um ViewModel com `StateFlow`
- [ ] Implementei pelo menos um método de lógica (load, refresh, etc.)
- [ ] Criei componentes visuais reutilizáveis
- [ ] A Screen principal usa `collectAsState()` para observar ViewModel
- [ ] Usei tokens de cor/tipografia (MaterialTheme.colorScheme.*)
- [ ] Testei com `@Preview`
- [ ] Fiz commit e abri PR com título `[TREINO]`
- [ ] Respondi ao review (se houve)

---

## 🎉 Parabéns!

Você completou toda a trilha de wikis! Agora você:

- ✅ Consegue rodar o app localmente
- ✅ Entende a arquitetura (MVVM, Compose, estrutura de pastas)
- ✅ Sabe fazer o ciclo completo de contribuição (branch → commit → PR → review)
- ✅ Consegue criar componentes visuais
- ✅ Consegue criar uma feature **inteira** (dados → lógica → UI → navegação)

**Próximos passos:**
1. Contribuir com tarefas reais do backlog
2. Aprender testes (Unit + UI)
3. Aprender integração com APIs reais
4. Mentoria um(a) colega novo(a) que entra no time

Bem-vindo ao time Android do BabyTracker! 🚀