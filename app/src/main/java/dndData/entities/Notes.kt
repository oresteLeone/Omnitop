package dndData.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import dndData.RuoloGiocatore

@Entity(tableName = "note_table", foreignKeys = arrayOf(
    ForeignKey(
        entity = Campagna::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("campagna_id")
        )
    )
)
data class Notes(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "campagna_id") val Campagnaid: Int,

    var titoloNota: String,
    var corpoNota: String?,
    @ColumnInfo(defaultValue = "false" ) var preferito: Boolean,
    var ruoloNota: RuoloGiocatore,
    //var copertina: blob?
)

