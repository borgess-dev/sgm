package com.example.sgm

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VehicleForm(
    id: UUID?,
    vehiclesViewModel: VehiclesViewModel,
    groupsViewModel: GroupsViewModel,
    onBack: () -> Unit
) {
    val vehicle = vehiclesViewModel.findById(id)

    var nameInput by rememberSaveable { mutableStateOf(vehicle?.name ?: "") }
    var modelInput by rememberSaveable { mutableStateOf(vehicle?.model ?: "") }
    var yearInput by rememberSaveable { mutableStateOf(vehicle?.year ?: "") }
    var isActiveInput by rememberSaveable { mutableStateOf(vehicle?.isActive ?: true) }

    var selectedGroup by remember { mutableStateOf<Group?>(vehicle?.group) }

    var showSheet by rememberSaveable { mutableStateOf(false) }
    var query by rememberSaveable { mutableStateOf("") }

    val groupsList by groupsViewModel.group.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .systemBarsPadding()
            .padding(16.dp)
    ) {
        Text(
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(23, 23, 23),
            text = "Cadastro de Veículo"
        )

        Text(
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color(87, 83, 78),
            text = "Cadastre um veículo preenchendo o formulário abaixo!"
        )

        Spacer(
            modifier = Modifier
                .height(16.dp)
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Apelido") },
            placeholder = { Text("Dê um apelido ao veículo") },
            value = nameInput,
            onValueChange = { nameInput = it }
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Modelo") },
            placeholder = { Text("Determine o modelo") },
            value = modelInput,
            onValueChange = { modelInput = it }
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Ano") },
            placeholder = { Text("Determine o ano") },
            value = yearInput,
            onValueChange = { yearInput = it }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showSheet = true }
        ) {
            TextField(
                value = selectedGroup?.name ?: "",
                onValueChange = {},
                readOnly = true,
                enabled = false,
                label = { Text("Grupo do Veículo") },
                placeholder = { Text("Selecione um grupo") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        if (showSheet) {
            ModalBottomSheet(
                onDismissRequest = { showSheet = false }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    OutlinedTextField(
                        value = query,
                        onValueChange = { query = it },
                        label = { Text("Buscar grupo...") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    val filteredGroups = groupsList.filter {
                        it.name.contains(query, ignoreCase = true)
                    }

                    LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
                        items(filteredGroups) { groupItem ->
                            Text(
                                text = groupItem.name,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        selectedGroup = groupItem
                                        showSheet = false
                                    }
                                    .padding(vertical = 12.dp)
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isActiveInput,
                onCheckedChange = { isActiveInput = it }
            )
            Text("Ativo")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(

        ) {
            Button(
                onClick = { vehiclesViewModel.save(
                    vehicle?.id,
                    nameInput,
                    modelInput,
                    yearInput,
                    selectedGroup,
                    isActiveInput
                ) }
            ) {
                Text("Salvar")
            }

            Spacer(
                modifier = Modifier
                    .width(8.dp)
            )

            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(229, 229, 229),
                    contentColor = Color(23, 23, 23)
                ),
                onClick = { onBack() }
            ) {
                Text(
                    text = "Cancelar"
                )
            }
        }


    }
}