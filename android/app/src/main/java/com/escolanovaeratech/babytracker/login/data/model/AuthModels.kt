package com.escolanovaeratech.babytracker.login.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// Corpo enviado no POST /auth/login
@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)

// Corpo enviado no POST /auth/register
@Serializable
data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)

// Resposta do backend após login ou registro bem-sucedido
@Serializable
data class AuthResponse(
    val token: String,
    @SerialName("user") val user: UserDto
)

// Objeto dentro da resposta
@Serializable
data class UserDto(
    val id: String,
    val name: String,
    val email: String
)

// Corpo enviado no POST /auth/forgot-password
@Serializable
data class ForgotPasswordRequest(
    val email: String
)

// Resposta genérica de mensagem (forgot/reset password)
@Serializable
data class MessageResponse(
    val message: String
)