package dndData.relationData

import androidx.room.Embedded
import androidx.room.Relation
import dndData.entities.Campagna
import dndData.entities.Notes
import dndData.entities.Oggetti
import dndData.entities.Scheda

data class OggettiScheda (
        @Embedded
        var scheda: Scheda,
        @Relation(
                parentColumn = "id",
                entityColumn = "campagna_id",
                entity = Notes::class
        )
        var oggetti: List<Oggetti>
)

