package com.example.sgm

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.UUID

@Composable
fun GroupForm(
    id: UUID?,
    groupsViewModel: GroupsViewModel,
    onBack: () -> Unit
) {

    val group = groupsViewModel.findById(id)

    var nameInput by rememberSaveable() { mutableStateOf(group?.name ?: "") }
    var descriptionInput by rememberSaveable() { mutableStateOf(group?.description ?: "") }
    var isActiveInput by rememberSaveable() { mutableStateOf(group?.isActive ?: true) }

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
            text = "Cadastro de Grupo"
        )

        Text(
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color(87, 83, 78),
            text = "Cadastre um grupo de veículos preenchendo o formulário abaixo!"
        )

        Spacer(
            modifier = Modifier
                .height(16.dp)
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Nome do Grupo") },
            placeholder = { Text("Digite o nome do grupo") },
            value = nameInput,
            onValueChange = { nameInput = it }
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Descrição") },
            placeholder = { Text("Determine uma descrição") },
            value = descriptionInput,
            onValueChange = { descriptionInput = it },
        )

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
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.End),
        ) {

            Button(
                onClick = {
                    groupsViewModel.save(
                        group?.id,
                        nameInput,
                        descriptionInput,
                        isActiveInput
                    )

                    onBack()
                }
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