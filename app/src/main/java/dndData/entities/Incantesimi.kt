package dndData.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "incantesimi_table",foreignKeys = arrayOf(
        ForeignKey(
                onDelete = ForeignKey.CASCADE,
                entity = Scheda::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("scheda_id")
            )
    ),
        indices = arrayOf(Index(name = "scheda_id_indexIncantesimi", value = ["scheda_id"], unique = true))
)
data class Incantesimi(

        @PrimaryKey(autoGenerate = true) val id: Int,
        val scheda_id: Int,
        var nomeIncantesimo: String,
        var descrizioneIncantesimo: String?,
        var lvlIncantesimo: Int?

)

