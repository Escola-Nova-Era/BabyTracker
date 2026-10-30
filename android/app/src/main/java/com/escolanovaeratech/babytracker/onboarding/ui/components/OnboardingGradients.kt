package com.escolanovaeratech.babytracker.onboarding.ui.components

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

object OnboardingGradients {
    private val gradients = listOf(
        Brush.verticalGradient(listOf(Color(0xFFFDEFF5), Color(0xFFFDF8FA), Color(0xFFF8FBFD))),
        Brush.verticalGradient(listOf(Color(0xFFFDF1F6), Color(0xFFFDF9FA), Color(0xFFF7FAFD))),
        Brush.verticalGradient(listOf(Color(0xFFEFF7FD), Color(0xFFFDFDFD), Color(0xFFF5F9FD))),
    )

    fun forPage(index: Int): Brush = gradients.getOrElse(index) {
        gradients.first()
    }
}