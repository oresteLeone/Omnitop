package dndData.entities

import android.os.Parcelable
import androidx.room.*
import dndData.RuoloGiocatore
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "note_table", foreignKeys = arrayOf(
    ForeignKey(
        onDelete = ForeignKey.CASCADE,
        entity = Campagna::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("campagna_id")
        )
    ),
    indices = arrayOf(Index(name = "campagna_id_indexNotes", value = ["campagna_id"], unique = true))
)
data class Notes(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "campagna_id") val Campagnaid: Int?,

    var titoloNota: String,
    var corpoNota: String?,
    @ColumnInfo(defaultValue = "false" ) var preferito: Boolean,
    var ruoloNota: RuoloGiocatore,

): Parcelable

