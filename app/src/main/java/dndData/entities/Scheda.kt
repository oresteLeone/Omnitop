package dndData.entities

import androidx.room.*
import dndData.TipoScheda
import dndData.utilData.*


@Entity(tableName = "Scheda_table",
foreignKeys = arrayOf(
    ForeignKey(
        entity = Campagna::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("campagna_id")
        )
    )
)
data class Scheda(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id") val id: Int,
        @ColumnInfo(name = "campagna_id") val Campagnaid: Int,
        var nomePG: String,
        var tipoScheda: TipoScheda

)


