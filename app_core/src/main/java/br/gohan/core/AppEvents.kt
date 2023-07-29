package br.gohan.core

import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest

sealed class AppEvents {
    class ShowSnackbar(val message: String) : AppEvents()
    object ApiError : AppEvents()
    object ExceptionError: AppEvents()
}

suspend fun collectAppEvents(appEvents: SharedFlow<AppEvents>, snackBarHost: SnackbarHostState) {
    appEvents.collectLatest {
        when (it) {
            is AppEvents.ShowSnackbar -> {
                snackBarHost.showSnackbar(it.message)
            }

            else -> {}
        }
    }
}