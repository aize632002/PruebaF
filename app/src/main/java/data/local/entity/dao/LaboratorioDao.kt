package data.local.entity.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import data.local.entity.Laboratorio

@Dao
interface LaboratorioDao {
    @Query("SELECT * FROM laboratorios")
    fun obtenerTodosLosLaboratorios(): Flow<List<Laboratorio>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarLaboratorio(laboratorio: Laboratorio)
}