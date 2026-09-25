package com.escolanovaeratech.babytracker.onboarding.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.escolanovaeratech.babytracker.onboarding.ui.components.OnboardingPageContent

@Composable
fun OnboardingScreen(
    onFinish: () -> Unit,
    viewModel: OnboardingViewModel = viewModel()
) {
    val pages = viewModel.pages
    val pagerState = rememberPagerState(pageCount = { pages.size })

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { pageIndex ->
        OnboardingPageContent(
            page = pages[pageIndex],
            pageIndex = pageIndex,
            numberOfPages = pages.size,
            currentPage = pagerState.currentPage,
            isLastPage = pageIndex == pages.lastIndex,
            onFinishClick = { viewModel.completeOnboarding(onFinish) }
        )
    }
}


