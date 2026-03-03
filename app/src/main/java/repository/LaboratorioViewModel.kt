package repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import data.local.entity.Laboratorio
import repository.TechRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow

class LaboratorioViewModel(private val repository: TechRepository) : ViewModel() {

    // Esta variable observará los cambios en la base de datos en tiempo real
    val todosLosLaboratorios: Flow<List<Laboratorio>> = repository.todosLosLaboratorios

    // Función para guardar un nuevo laboratorio usando Corrutinas
    fun insertarLaboratorio(nombre: String, edificio: String) {
        val nuevoLab = Laboratorio(nombre = nombre, edificio = edificio)
        viewModelScope.launch {
            repository.insertarLaboratorio(nuevoLab)
        }
    }
}

// Esta clase extra (Factory) es necesaria en Android para pasarle el Repository al ViewModel
class LaboratorioViewModelFactory(private val repository: TechRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LaboratorioViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LaboratorioViewModel(repository) as T
        }
        throw IllegalArgumentException("Clase ViewModel desconocida")
    }
}