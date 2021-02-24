package dndData.relationData

import androidx.room.Embedded
import androidx.room.Relation
import dndData.entities.*

data class SchedaCompleta(

    @Embedded
    var scheda: Scheda,

    @Relation(
        parentColumn = "id",
        entityColumn = "scheda_id",
        entity = Oggetti::class
    ) var oggetti: List<Oggetti>,

    @Relation(
        parentColumn = "id",
        entityColumn = "scheda_id",
        entity = PrivilegiETratti::class
    ) var privilegiETratti: List<PrivilegiETratti>,


    @Relation(
        parentColumn = "id",
        entityColumn = "scheda_id",
        entity = CompetenzeELinguaggi::class
    ) var competenzeELinguaggi: List<CompetenzeELinguaggi>,


    @Relation(
        parentColumn = "id",
        entityColumn = "scheda_id",
        entity = Incantesimi::class
    ) var incantesimi: List<Incantesimi>,


    )
