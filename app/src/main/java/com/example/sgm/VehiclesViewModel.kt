package com.example.sgm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

class VehiclesViewModel : ViewModel() {

    private var _vehicles = MutableStateFlow<List<Vehicle>>(emptyList())
    val vehicles = _vehicles.asStateFlow()

    fun findById(id: UUID?): Vehicle? {
        if (id == null) return null
        return _vehicles.value.firstOrNull { vehicle -> vehicle.id == id }
    }

    fun save(
        id: UUID?,
        name: String,
        model: String,
        year: String,
        group: Group?,
        isActive: Boolean
    ) {
        val currentList = _vehicles.value.toMutableList()

        if (id != null) {
            val index = currentList.indexOfFirst { it.id == id }
            if (index != -1) {
                currentList[index] = Vehicle(
                    id,
                    name,
                    model,
                    year,
                    group,
                    isActive
                )
            }
        } else {
            val newVehicle = Vehicle(
                id = java.util.UUID.randomUUID(),
                name = name,
                model = model,
                year = year,
                group = group,
                isActive = isActive
            )
            currentList.add(newVehicle)
        }

        _vehicles.value = currentList
    }

    fun remove(vehicle: Vehicle) {
        _vehicles.value -= vehicle
    }
}