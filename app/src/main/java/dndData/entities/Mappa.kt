package dndData.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mappa_table")
data class Mappa(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "mappa_id") val id: Int,
    var titolo: String,
    //var img: Blob
)
