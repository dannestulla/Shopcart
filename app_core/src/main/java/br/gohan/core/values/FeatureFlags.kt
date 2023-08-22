package br.gohan.core.values

import android.util.Log
import com.google.firebase.remoteconfig.FirebaseRemoteConfig

object FeatureFlags {
    fun setFeatureFlags(remoteConfig: FirebaseRemoteConfig) {
        val category = remoteConfig.getBoolean("categorias")

        Log.i("featureFlags", "Categorias: ${category.toString()}")
        categorias = category
    }

    fun getFeatureFlags(): MutableMap<String, Boolean> {
        val list = mutableMapOf<String, Boolean>()
        list["categorias"] = categorias
        return list
    }

    private var categorias = false

}