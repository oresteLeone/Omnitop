package dndData.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "incantesimi_table",foreignKeys = arrayOf(
        ForeignKey(
                entity = Scheda::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("scheda_id")
            )
    )
)
data class Incantesimi(

        @PrimaryKey(autoGenerate = true)
        var id: Int,
        var scheda_id: Int,
        var nomeIncantesimo: String,
        var descrizioneIncantesimo: String?,
        var lvlIncantesimo: Int?

)

