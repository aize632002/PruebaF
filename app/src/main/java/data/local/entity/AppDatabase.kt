package data.local.entity

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import data.local.entity.Laboratorio
import data.local.entity.Equipo
import data.local.entity.dao.LaboratorioDao
import data.local.entity.dao.EquipoDao

@Database(entities = [Laboratorio::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun laboratorioDao(): LaboratorioDao
    abstract fun equipoDao(): EquipoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                // Aquí es donde te daba error. Asegúrate de que sea EXACTO:
                val instance = Room.databaseBuilder<AppDatabase>(
                    context.applicationContext,
                    AppDatabase::class.java, // <-- Verifica que esto no esté en rojo
                    "techsolutions_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}