package com.example.sgm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID
import kotlin.collections.minus

class GroupsViewModel : ViewModel() {

    private var _groups = MutableStateFlow<List<Group>>(emptyList())
    val group = _groups.asStateFlow()

    fun findById(id: UUID?): Group? {
        if (id == null) return null
        return _groups.value.firstOrNull { group -> group.id == id }
    }

    fun save(
        id: UUID?,
        name: String,
        description: String,
        isActive: Boolean
    ) {
        val currentList = _groups.value.toMutableList()

        if (id != null) {
            val index = currentList.indexOfFirst { it.id == id }
            if (index != -1) {
                currentList[index] = Group(
                    id,
                    name,
                    description,
                    isActive
                )
            }
        } else {
            val newVehicle = Group(
                id = java.util.UUID.randomUUID(),
                name = name,
                description = description,
                isActive = isActive
            )
            currentList.add(newVehicle)
        }

        _groups.value = currentList
    }

    fun remove(group: Group) {
        _groups.value -= group
    }

}