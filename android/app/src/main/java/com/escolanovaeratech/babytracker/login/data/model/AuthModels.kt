package com.escolanovaeratech.babytracker.login.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// Corpo enviado no POST /auth/login
@JsonClass(generateAdapter = true)
data class LoguinRequest(
    @Json(name = "email") val email: String,
    @Json(name = "password") val password: String
)

// Corpo enviado no POST /auth/register
@JsonClass(generateAdapter = true)
data class RegisterRequest(
    @Json(name = "name") val name: String,
    @Json(name = "email") val email: String,
    @Json(name = "password") val password: String
)

// Resposta do backend após login ou registro bem-sucedido
@JsonClass(generateAdapter = true)
data class AuthResponse(
    @Json(name = "token") val token: String,
    @Json(name = "user") val user: UserDto
)

// Objeto dentro da resposta
@JsonClass(generateAdapter = true)
data class UserDto(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "email") val email: String
)

// Corpo enviado no POST /auth/forgot-password
@JsonClass(generateAdapter = true)
data class ForgotPasswordRequest(
    @Json(name = "email") val email: String
)

// Resposta genérica de mensagem (forgot/reset password)
@JsonClass(generateAdapter = true)
data class MessageResponse(
    @Json(name = "message") val message: String
)