package com.example.sgm

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.UUID

@Composable
fun GroupList(
    groupsViewModel: GroupsViewModel,
    onNavigateToForm: (UUID) -> Unit
) {

    val groupsList by groupsViewModel.group.collectAsState()
    Column(
        modifier = Modifier
            .systemBarsPadding()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(23, 23, 23),
            text = "Grupos de Veículos"
        )

        Spacer(
            modifier = Modifier
                .height(4.dp)
        )

        Text(
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color(87, 83, 78),
            text = "Nessa tela, você verá todos os grupos de veículos que você cadastrou e poderá gerenciá-los"
        )

        Spacer(
            modifier = Modifier
                .height(8.dp)
        )

        LazyColumn (
            modifier = Modifier
                .systemBarsPadding()
        ) {
            items(groupsList) { group ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(245, 245, 245))
                        .border(1.dp, Color(212, 212, 212), RoundedCornerShape(16.dp))
                        .clickable { onNavigateToForm(group.id) }
                        .padding(16.dp)

                ) {
                    Column() {
                        Text(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(23, 23, 23),
                            text = group.name
                        )
                        Text(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color(87, 83, 78),
                            text = group.description
                        )
                    }
                }

                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                )
            }
        }
    }
}