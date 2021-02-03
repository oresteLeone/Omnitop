package dndData.relationData

import androidx.room.Embedded
import androidx.room.Relation
import dndData.entities.Campagna
import dndData.entities.Notes

data class  NoteCampagna(
    @Embedded
    var campagna: Campagna,
    @Relation(
        parentColumn = "id",
        entityColumn = "campagna_id",
        entity = Notes::class
    )
    var note: List<Notes>
)
