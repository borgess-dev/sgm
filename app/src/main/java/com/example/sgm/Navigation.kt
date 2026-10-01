package com.example.sgm

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import java.util.UUID
import kotlin.getValue

@Composable
fun Navigation(
    navigationController: NavHostController,
    modifier: Modifier = Modifier
) {

    val vehiclesViewModel = viewModel<VehiclesViewModel>()
    val groupsViewModel = viewModel<GroupsViewModel>()

    NavHost(
        navController = navigationController,
        startDestination = "vehicles"
    ) {

        composable("vehicles") {
            VehiclesList(
                vehiclesViewModel,
                onNavigateToForm = {id ->
                    if(id != null) {
                        navigationController.navigate("vehicleForm?vehicleId=$id")
                    } else {
                        navigationController.navigate("vehicleForm")
                    }
                }
            )
        }

        composable(
            "vehicleForm?vehicleId={vehicleId}",
            arguments = listOf(
                navArgument("vehicleId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
            ) { backstackEntry ->
            val vehicleId = backstackEntry.arguments?.getString("vehicleId")

            VehicleForm(
                vehicleId?.let { UUID.fromString(it) },
                vehiclesViewModel,
                groupsViewModel,
                onBack = { navigationController.navigate("vehicles") }
            )
        }

        composable("groups") {
            GroupList(
                groupsViewModel,
                onNavigateToForm = { id ->
                    if(id != null) {
                        navigationController.navigate("groupForm?groupId=$id")
                    }
                    else {
                        navigationController.navigate("groupForm")
                    }
                }
            )
        }

        composable(
            "groupForm?groupId={groupId}",
                arguments = listOf(
                    navArgument(
                        "groupId"
                    ) {
                        type = NavType.StringType
                        nullable = true
                        defaultValue = null
                    }
                )
            ) { backstackEntry ->
            val groupId = backstackEntry.arguments?.getString("groupId")

            GroupForm(
                groupId?.let { UUID.fromString(it) },
                groupsViewModel,
                onBack = { navigationController.navigate("groups") }
            )
        }

    }
}