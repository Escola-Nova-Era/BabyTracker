package com.escolanovaeratech.babytracker.profile.ui.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.componentetelaperfil.R
import com.example.componentetelaperfil.ui.theme.AppTypography
import com.example.componentetelaperfil.ui.theme.BabyTrackerTheme
import com.example.componentetelaperfil.ui.theme.CardShapeLarge
import com.example.componentetelaperfil.ui.theme.SurfaceColor

@Composable
fun ProfileCard() {
    Card(
        shape = CardShapeLarge,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Box(
            modifier = Modifier
                .background(brush = backgroundCard)
                .padding(30.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baby),
                    contentDescription = null,
                    modifier = Modifier
                        .size(90.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(R.string.BabyName),
                    style = AppTypography.headlineLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(R.string.BabyBirthDate),
                    style = AppTypography.bodyLarge,
                    color = SurfaceColor.copy(alpha = 0.8f)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    InfoItem(stringResource(R.string.age), stringResource(R.string.BabyAge))
                    VerticalDivider(
                        modifier = Modifier
                            .height(24.dp)
                            .width(0.5.dp)
                            .align(Alignment.CenterVertically),
                        color = SurfaceColor.copy(alpha = 0.8f)
                    )
                    InfoItem(stringResource(R.string.weight), stringResource(R.string.BabyWeight))
                    VerticalDivider(
                        modifier = Modifier
                            .height(24.dp)
                            .width(0.5.dp)
                            .align(Alignment.CenterVertically),
                        color = SurfaceColor.copy(alpha = 0.8f)
                    )
                    InfoItem(stringResource(R.string.height), stringResource(R.string.BabyHeight))
                }
            }
        }
    }
}

@Composable
fun InfoItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = value, style = AppTypography.titleLarge, color = SurfaceColor)
        Text(text = label, style = AppTypography.bodyLarge, color = SurfaceColor.copy(alpha = 0.7f))
    }
}

@Preview
@Composable
fun ProfileCardPreview (){
    BabyTrackerTheme() {
        ProfileCard()
    }
}
