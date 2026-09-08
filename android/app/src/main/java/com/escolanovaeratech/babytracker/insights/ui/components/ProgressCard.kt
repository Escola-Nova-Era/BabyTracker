package com.escolanovaeratech.babytracker.insights.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.escolanovaeratech.babytracker.R
import com.escolanovaeratech.babytracker.theme.AppTypography
import com.escolanovaeratech.babytracker.theme.BabyTrackerTheme
import com.escolanovaeratech.babytracker.theme.CardShapeLarge
import com.escolanovaeratech.babytracker.theme.ProfileCardGradient
import com.escolanovaeratech.babytracker.theme.SurfaceColor

@Composable
fun ProgressCard (){
    Card(
        shape = CardShapeLarge,
        modifier = Modifier
        .fillMaxWidth()
         .padding(20.dp)
    ) {
        Box(
            modifier = Modifier
                .background(brush = ProfileCardGradient)
                .padding(30.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = stringResource(R.string.progressHeader),
                    style = AppTypography.headlineLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(R.string.progressInfo),
                    style = AppTypography.bodyLarge,
                    color = SurfaceColor.copy(alpha = 0.8f)
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProgressCardPreview() {
    BabyTrackerTheme {
        ProgressCard()
    }
}