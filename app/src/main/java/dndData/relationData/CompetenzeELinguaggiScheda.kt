package dndData.relationData

import androidx.room.Embedded
import androidx.room.Relation
import dndData.entities.CompetenzeELinguaggi
import dndData.entities.PrivilegiETratti
import dndData.entities.Scheda

data class CompetenzeELinguaggiScheda(
    @Embedded
    var scheda: Scheda,
    @Relation(
        parentColumn = "id",
        entityColumn = "scheda_id",
        entity = CompetenzeELinguaggi::class
    )
    var competenzeELinguaggi: List<CompetenzeELinguaggi>
)