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
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "scheda_id") var id: Int,
    @ColumnInfo(name = "campagna_id") var Campagnaid: Int,

    var nome: String,
    var corpo: String?,
    @ColumnInfo(defaultValue = "false" ) var preferito: Boolean,
    var ruolo: RuoloGiocatore,
    //var copertina: blob?
)

