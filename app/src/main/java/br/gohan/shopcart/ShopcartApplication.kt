package br.gohan.shopcart

import android.app.Application
import android.util.Log
import br.gohan.core.database.databaseModule
import br.gohan.core.values.FeatureFlags
import br.gohan.products.productsModule
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
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
                    shopcartModule,
                    databaseModule
                )
            )
        }
        setRemoteConfig()
    }

    private fun setRemoteConfig() {
        FirebaseApp.initializeApp(this)
        val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
        remoteConfig.fetchAndActivate().addOnCompleteListener {
            if (it.isSuccessful) {
                FeatureFlags.setFeatureFlags(remoteConfig)
            } else {
                Log.i("featureFlags", "failed to fetch")
            }
        }
    }
}