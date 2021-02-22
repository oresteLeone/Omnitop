package dndData.entities

import androidx.room.*

@Entity(tableName = "privilegi_e_tratti_table",foreignKeys = arrayOf(
    ForeignKey(
        entity = Scheda::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("scheda_id")
    )
)
)
data class PrivilegiETratti (

    @PrimaryKey(autoGenerate = true) val id: Int,
    val scheda_id: Int,
    var titoloPrivilegi : String,
    var descrizoneCompetenze: String?

    )