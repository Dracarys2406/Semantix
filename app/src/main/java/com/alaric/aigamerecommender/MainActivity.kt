package com.alaric.aigamerecommender

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.alaric.aigamerecommender.ui.features.search.SearchScreen
import com.alaric.aigamerecommender.ui.navigation.Semantix
import com.alaric.aigamerecommender.ui.theme.AiGameRecommenderTheme
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
            AiGameRecommenderTheme() {
                Semantix()
            }
        }
    }
}
