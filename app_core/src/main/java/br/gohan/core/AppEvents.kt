package br.gohan.core

sealed class AppEvents {
    object ApiError : AppEvents()
    object ExceptionError: AppEvents()

}