package com.escolanovaeratech.babytracker.onboarding.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.escolanovaeratech.babytracker.R
import com.escolanovaeratech.babytracker.onboarding.data.model.OnboardingPage
import com.escolanovaeratech.babytracker.theme.ComponentSize
import com.escolanovaeratech.babytracker.theme.Spacing


@Composable
fun OnboardingPageContent(
    page: OnboardingPage,
    pageIndex: Int,
    numberOfPages: Int,
    currentPage: Int,
    isLastPage: Boolean,
    onFinishClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(OnboardingGradients.forPage(pageIndex))
            .padding(horizontal = Spacing.xl),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(Spacing.xxl))

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(page.imageRes),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .weight(1f, fill = false)
                    .aspectRatio(1f)
            )

            Spacer(modifier = Modifier.height(Spacing.xl))

            Text(
                text = stringResource(page.titleRes),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(Spacing.sm))

            Text(
                text = stringResource(page.descriptionRes),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(Spacing.xl))

        OnboardingPageIndicator(
            numberOfPages = numberOfPages,
            currentPage = currentPage
        )

        Spacer(modifier = Modifier.height(Spacing.xl))

        AnimatedVisibility(visible = isLastPage) {
            Button(
                onClick = onFinishClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(ComponentSize.primaryButtonHeight)
            ) {
                Text(text = stringResource(R.string.onboarding_finish_button))
            }
        }

        Spacer(modifier = Modifier.height(Spacing.xl))
    }

}



@Preview(showBackground = true, name = "Página de onboarding")
@Composable
fun OnboardingPageContentPreview() {
    val samplePage = OnboardingPage(
        descriptionRes = R.string.onboarding_page1_description,
        titleRes = R.string.onboarding_page1_title,
        imageRes = R.drawable.onboarding_welcome
    )


    MaterialTheme {
        OnboardingPageContent(
            page = samplePage,
            pageIndex = 0,
            numberOfPages = 3,
            currentPage = 0,
            isLastPage = false,
            onFinishClick = {}
        )
    }
}

