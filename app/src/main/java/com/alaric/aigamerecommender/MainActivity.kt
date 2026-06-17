package com.alaric.aigamerecommender

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.alaric.aigamerecommender.ui.navigation.Semantix
import com.alaric.aigamerecommender.ui.theme.SemantixTheme
import com.alaric.aigamerecommender.ui.theme.SemantixThemeType
import com.alaric.data.mock.DatabaseSeeder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // Injecting your seeder directly from the Data module
    @Inject
    lateinit var databaseSeeder: DatabaseSeeder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(

        )
        // MOCK seeding database
        //databaseSeeder.seedMockData()

        setContent {
            SemantixTheme(themeType = SemantixThemeType.CYBERPUNK) {
                Semantix()
            }
        }
    }
}
