package dndData.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pedina_table")
data class Pedina(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var nome: String,
    //var token: blob
)
