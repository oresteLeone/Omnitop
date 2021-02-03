package dndData.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "oggetti_table",foreignKeys = arrayOf(
        ForeignKey(
                entity = Scheda::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("scheda_id")
        )
))
data class Oggetti(
        var todo: Int  // TODO: 03/02/2021

)
