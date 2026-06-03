package com.escolanovaeratech.babytracker.login.data.api

import com.escolanovaeratech.babytracker.login.data.model.AuthResponse
import com.escolanovaeratech.babytracker.login.data.model.ForgotPasswordRequest
import com.escolanovaeratech.babytracker.login.data.model.LoginRequest
import com.escolanovaeratech.babytracker.login.data.model.MessageResponse
import com.escolanovaeratech.babytracker.login.data.model.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    // POST /auth/login
    @POST("auth/login")
    suspend fun login(@Body request: RegisterRequest): AuthResponse

    // POST /auth/register
    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): AuthResponse

    // POST auth/forgot-password
    @POST("auth/forgot-password")
    suspend fun forgotPassword(@Body request: ForgotPasswordRequest): MessageResponse
}