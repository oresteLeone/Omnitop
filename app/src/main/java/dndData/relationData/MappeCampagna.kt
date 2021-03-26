package dndData.relationData

import androidx.room.Embedded
import androidx.room.Relation
import dndData.entities.Campagna
import dndData.entities.Mappa

data class MappeCampagna(

        @Embedded
        var campagna: Campagna,
        @Relation(
                parentColumn = "id",
                entityColumn = "campagna_id",
                entity = Mappa::class
        )
        var mappe: List<Mappa>
)
