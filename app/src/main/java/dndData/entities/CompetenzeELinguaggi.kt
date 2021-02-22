package dndData.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "competenze_e_linguaggi_table",foreignKeys = arrayOf(
    ForeignKey(
        entity = Scheda::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("scheda_id")
    )
)
)
data class CompetenzeELinguaggi(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val scheda_id: Int,
    var titoloCompetenze : String,
    var descrizoneCompetenze: String?
)