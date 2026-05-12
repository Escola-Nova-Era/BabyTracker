package com.escolanovaeratech.babytracker.home.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreenUI() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "QUICK ACTIONS",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                ActionButton("Add Feeding", Color(0xFF1573BF), Modifier.weight(1f))
                ActionButton("Add Diaper", Color(0xFF43AB47), Modifier.weight(1f))
            }

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                ActionButton("Sleep/Wake", Color(0xFF8951EB), Modifier.weight(1f))
                ActionButton("Add Bath", Color(0xFFFF5722), Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun ActionButton(
    text: String,
    color: Color,
    icon: Modifier,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { },
        modifier = modifier.height(90.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = Color.White
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(25.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = text,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

