package com.example.oleh_maksymuk.flexible_news_api

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.example.oleh_maksymuk.flexible_news_api.constants.COUNTRY_PREFERENCE_KEY
import com.example.oleh_maksymuk.flexible_news_api.constants.IS_PREFERENCES_CHANGED_KEY
import com.example.oleh_maksymuk.flexible_news_api.constants.LANGUAGE_PREFERENCE_KEY

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.preferences_content, MySettingsFragment())
            .commit()
    }

    class MySettingsFragment : PreferenceFragmentCompat() {

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.preferences, rootKey)

            findPreference(COUNTRY_PREFERENCE_KEY).setOnPreferenceChangeListener(firePreferenceChanged())
            findPreference(LANGUAGE_PREFERENCE_KEY).setOnPreferenceChangeListener(firePreferenceChanged())
        }

        private fun firePreferenceChanged(): (Preference, Any) -> Boolean {
            return { _, _ ->
                PreferenceManager.getDefaultSharedPreferences(context).edit()
                    .putBoolean(IS_PREFERENCES_CHANGED_KEY, true).apply()
                true
            }
        }
    }


}