package data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "equipos",
    foreignKeys = [
        ForeignKey(
            entity = Laboratorio::class,
            parentColumns = ["id"],
            childColumns = ["laboratorioId"],
            onDelete = ForeignKey.CASCADE // Si borras el laboratorio, se borran sus equipos
        )
    ]
)

data class Equipo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val estado: String, // Puedes usar String por ahora ("Operativo", "Dañado", "Pendiente")
    val laboratorioId: Int

)
