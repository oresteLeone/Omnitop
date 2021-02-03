package dndData.relationData

import androidx.room.Embedded
import androidx.room.Relation
import dndData.entities.Campagna
import dndData.entities.Scheda

data class SchedeCampagna(
    @Embedded
    var campagna: Campagna,

    @Relation(
        parentColumn = "id",
        entityColumn = "campagna_id",
        entity = Scheda::class
    )
    var schede: List<Scheda>
)
