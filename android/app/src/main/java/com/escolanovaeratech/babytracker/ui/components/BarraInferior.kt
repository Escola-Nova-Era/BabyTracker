package com.escolanovaeratech.babytracker.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BarrarInferior(
    modifier: Modifier = Modifier
) {
    BottomAppBar(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            // Usando os ícones nativos que não dependem do pacote externo "material.icons"
            IconButton(onClick = {}) {
                Icon(
                    painter = androidx.compose.ui.res.painterResource(id = android.R.drawable.ic_menu_view),
                    contentDescription = "Home"
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    painter = androidx.compose.ui.res.painterResource(id = android.R.drawable.ic_menu_sort_by_size),
                    contentDescription = "Gráficos"
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    painter = androidx.compose.ui.res.painterResource(id = android.R.drawable.ic_menu_recent_history),
                    contentDescription = "Histórico"
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    painter = androidx.compose.ui.res.painterResource(id = android.R.drawable.ic_menu_my_calendar),
                    contentDescription = "Perfil"
                )
            }
        }
    }
}