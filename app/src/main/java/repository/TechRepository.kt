package repository

import data.local.entity.Equipo
import data.local.entity.Laboratorio
import data.local.entity.dao.EquipoDao
import data.local.entity.dao.LaboratorioDao
import kotlinx.coroutines.flow.Flow

class TechRepository(
    private val laboratorioDao: LaboratorioDao,
    private val equipoDao: EquipoDao // Asegúrate de haber creado tu EquipoDao
) {

    // --- Operaciones de Laboratorios ---
    val todosLosLaboratorios: Flow<List<Laboratorio>> = laboratorioDao.obtenerTodosLosLaboratorios()

    suspend fun insertarLaboratorio(laboratorio: Laboratorio) {
        laboratorioDao.insertarLaboratorio(laboratorio)
    }

    // --- Operaciones de Equipos ---
    fun obtenerEquiposPorLaboratorio(labId: Int): Flow<List<Equipo>> {
        return equipoDao.obtenerEquiposPorLaboratorio(labId)
    }

    suspend fun insertarEquipo(equipo: Equipo) {
        equipoDao.insertarEquipo(equipo)
    }

    suspend fun actualizarEquipo(equipo: Equipo) {
        equipoDao.actualizarEquipo(equipo)
    }

    suspend fun eliminarEquipo(equipo: Equipo) {
        equipoDao.eliminarEquipo(equipo)
    }
}