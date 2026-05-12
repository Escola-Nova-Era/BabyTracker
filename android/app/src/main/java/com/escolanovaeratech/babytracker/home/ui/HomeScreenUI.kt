package com.escolanovaeratech.babytracker.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.escolanovaeratech.babytracker.R

@Composable
fun HomeScreenUI() {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(16.dp)
        ) {

            Text(
                text = stringResource(R.string.quick_actions),
                style = MaterialTheme.typography.headlineMedium
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    ActionButton(
                        text = stringResource(R.string.add_feeding),
                        color = Color(0xFF1573BF),
                        iconRes = R.drawable.ic_bottle,
                        modifier = Modifier.weight(1f)
                    )

                    ActionButton(
                        stringResource(R.string.add_diaper),
                        color = Color(0xFF43AB47),
                        iconRes = R.drawable.ic_heart,
                        modifier = Modifier.weight(1f)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    ActionButton(
                        stringResource(R.string.sleep_wake),
                        color = Color(0xFF8951EB),
                        iconRes = R.drawable.ic_moon,
                        modifier = Modifier.weight(1f)
                    )

                    ActionButton(
                        stringResource(R.string.add_bath),
                        color = Color(0xFFFF5722),
                        iconRes = R.drawable.ic_bath,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun ActionButton(
    text: String,
    color: Color,
    iconRes: Int,
    modifier: Modifier = Modifier
) {

    Button(
        onClick = { },
        modifier = modifier.height(100.dp),
        shape = RoundedCornerShape(18.dp),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = Color.White
        )
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = text,
                modifier = Modifier.size(28.dp),
                tint = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = text,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}