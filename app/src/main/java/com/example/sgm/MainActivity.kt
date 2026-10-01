package com.example.sgm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sgm.ui.theme.SGMTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navigationController = rememberNavController()
            val navBackStackEntry by navigationController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            SGMTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomNavigation(navigationController) },
                    floatingActionButton = {
                        if(currentRoute == "vehicles") {
                            FloatingActionButton(
                                onClick = { navigationController.navigate("vehicleForm") }
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.outline_add_notes_24),
                                    contentDescription = "Adicionar Veículo"
                                )
                            }
                        }

                        if(currentRoute == "groups") {
                            FloatingActionButton(
                                onClick = { navigationController.navigate("groupForm") }
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.outline_add_notes_24),
                                    contentDescription = "Adicionar Grupo"
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    Navigation(navigationController, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}