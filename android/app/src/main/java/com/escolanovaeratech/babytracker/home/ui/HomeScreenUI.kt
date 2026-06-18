package com.escolanovaeratech.babytracker.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.escolanovaeratech.babytracker.R
import com.escolanovaeratech.babytracker.theme.AccentOrange
import com.escolanovaeratech.babytracker.theme.AccentGreen
import com.escolanovaeratech.babytracker.theme.AccentBlue
import com.escolanovaeratech.babytracker.theme.BabyTrackerTheme
import com.escolanovaeratech.babytracker.theme.PrimaryColor
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

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    ActionButton(
                        text = stringResource(R.string.add_feeding),
                        color = AccentBlue,
                        iconRes = R.drawable.ic_bottle,
                        modifier = Modifier.weight(1f)
                    )

                    ActionButton(
                        text = stringResource(R.string.add_diaper),
                        color = AccentGreen,
                        iconRes = R.drawable.ic_heart,
                        modifier = Modifier.weight(1f)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    ActionButton(
                        text = stringResource(R.string.sleep_wake),
                        color = PrimaryColor,
                        iconRes = R.drawable.ic_moon,
                        modifier = Modifier.weight(1f)
                    )

                    ActionButton(
                        text = stringResource(R.string.add_bath),
                        color = AccentOrange,
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
        contentPadding = PaddingValues(0.dp),
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

@Preview
@Composable
fun HomeScreenUIPreview() {
    BabyTrackerTheme {
        HomeScreenUI()
    }
}