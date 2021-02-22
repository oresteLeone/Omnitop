package dndData.relationData

import androidx.room.Embedded
import androidx.room.Relation
import dndData.entities.Incantesimi
import dndData.entities.PrivilegiETratti
import dndData.entities.Scheda

data class PrivilegiETrattiScheda (
    @Embedded
    var scheda: Scheda,
    @Relation(
        parentColumn = "id",
        entityColumn = "scheda_id",
        entity = PrivilegiETratti::class
    )
    var privilegiETratti: List<PrivilegiETratti>

)