package dndData.relationData

import androidx.room.Embedded
import androidx.room.Relation
import dndData.entities.Incantesimi
import dndData.entities.Scheda

data class IncantesimiScheda(

    @Embedded
    var scheda: Scheda,
    @Relation(
        parentColumn = "id",
        entityColumn = "scheda_id",
        entity = Incantesimi::class
    )
    var grimorio: List<Incantesimi>
)
