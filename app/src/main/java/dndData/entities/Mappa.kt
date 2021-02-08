package dndData.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "mappa_table",foreignKeys = arrayOf(
        ForeignKey(
                entity = Campagna::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("campagna_id")
            )
        )
)
data class Mappa(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "campagna_id") val Campagnaid: Int,
    var titolo: String,
    //var img: Blob
)
