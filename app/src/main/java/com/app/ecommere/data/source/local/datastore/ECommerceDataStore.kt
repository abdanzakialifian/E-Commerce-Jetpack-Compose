package com.app.ecommere.data.source.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.datastore by preferencesDataStore("ecommerce")

@Singleton
class ECommerceDataStore @Inject constructor(@ApplicationContext context: Context) {
    private val eCommerceDataStore = context.datastore

    suspend fun saveUserSession(isLogin: Boolean) {
        eCommerceDataStore.edit { preference ->
            preference[USER_SESSION] = isLogin
        }
    }

    fun getUserSession(): Flow<Boolean> = eCommerceDataStore.data.map { preference ->
        preference[USER_SESSION] ?: false
    }

    suspend fun saveUserData(email: String) {
        eCommerceDataStore.edit { preference ->
            preference[USER_DATA] = email
        }
    }

    fun getUserData(): Flow<String> = eCommerceDataStore.data.map { preference ->
        preference[USER_DATA] ?: ""
    }

    companion object {
        private val USER_SESSION = booleanPreferencesKey("user_session")
        private val USER_DATA = stringPreferencesKey("user_data")
    }
}