package dndData.relationData

import androidx.room.Embedded
import androidx.room.Relation
import dndData.entities.*
import dndData.utilData.Dettagli
import dndData.utilData.Incantatore
import dndData.utilData.Money
import dndData.utilData.Statistiche

data class SchedaCompleta(

    @Embedded
    var scheda: Scheda,
    @Embedded
    var statistiche: Statistiche?,
    @Embedded
    var incantatore: Incantatore?,
    @Embedded
    var dettagli: Dettagli?,

    @Embedded   var moneteTotali: Money,

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
