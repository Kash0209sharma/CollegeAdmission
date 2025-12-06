package com.app.collegeadmissionapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.collegeadmissionapp.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val repository = AuthRepository()

    // LiveData for UI
    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    private val _userData = MutableLiveData<Map<String, Any>>()
    val userData: LiveData<Map<String, Any>> = _userData

    // Check if user is logged in
    fun checkAuthStatus(): Boolean {
        return repository.isUserLoggedIn()
    }

    // Get current user
    fun getCurrentUser(): FirebaseUser? {
        return repository.getCurrentUser()
    }

    // Register user
    fun register(name: String, email: String, phone: String, password: String) {
        _authState.value = AuthState.Loading

        viewModelScope.launch {
            val result = repository.register(name, email, phone, password)

            if (result.isSuccess) {
                _authState.value = AuthState.Success(result.getOrNull()!!)
            } else {
                _authState.value = AuthState.Error(
                    result.exceptionOrNull()?.message ?: "Registration failed"
                )
            }
        }
    }

    // Login user
    fun login(email: String, password: String) {
        _authState.value = AuthState.Loading

        viewModelScope.launch {
            val result = repository.login(email, password)

            if (result.isSuccess) {
                _authState.value = AuthState.Success(result.getOrNull()!!)
            } else {
                _authState.value = AuthState.Error(
                    result.exceptionOrNull()?.message ?: "Login failed"
                )
            }
        }
    }

    // Logout user
    fun logout() {
        repository.logout()
        _authState.value = AuthState.LoggedOut
    }

    // Send password reset email
    fun sendPasswordResetEmail(email: String) {
        viewModelScope.launch {
            val result = repository.sendPasswordResetEmail(email)

            if (result.isSuccess) {
                _authState.value = AuthState.PasswordResetSent
            } else {
                _authState.value = AuthState.Error(
                    result.exceptionOrNull()?.message ?: "Failed to send reset email"
                )
            }
        }
    }

    // Load user data
    fun loadUserData(uid: String) {
        viewModelScope.launch {
            val result = repository.getUserData(uid)

            if (result.isSuccess) {
                _userData.value = result.getOrNull() ?: emptyMap()
            }
        }
    }
}

// Auth states
sealed class AuthState {
    object Loading : AuthState()
    data class Success(val user: FirebaseUser) : AuthState()
    data class Error(val message: String) : AuthState()
    object LoggedOut : AuthState()
    object PasswordResetSent : AuthState()
}