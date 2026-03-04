package data.local.entity.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import data.local.entity.Equipo
import kotlinx.coroutines.flow.Flow // <--- ESTA ES LA LÍNEA QUE TE FALTA

@Dao
interface EquipoDao {
    // Filtramos solo los equipos de un laboratorio específico
    @Query("SELECT * FROM equipos WHERE laboratorioId = :labId")
    fun obtenerEquiposPorLaboratorio(labId: Int): Flow<List<Equipo>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarEquipo(equipo: Equipo)
    @Update
    suspend fun actualizarEquipo(equipo: Equipo)
    @Delete
    suspend fun eliminarEquipo(equipo: Equipo)


}