package br.gohan.shopcart

import android.app.Application
import br.gohan.products.productsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class ShopcartApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ShopcartApplication)
            modules(
                listOf(
                    productsModule,
                    shopcartModule
                )
            )
        }
    }
}