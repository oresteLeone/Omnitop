package dndData.utilData

import android.util.Size
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Relation
import dndData.LvlCompetenza
import dndData.entities.Notes
import dndData.entities.Oggetti
import dndData.entities.Scheda

data class Statistiche(
        var razza: String?,
        var classe: String?,
        var lvl: Int?,
        var puntiFerita: Int?,
        var classeArmatura: Int?,
        var iniziativa: Int?,
        var velocitaMov: Int?,
        var percezionePassiva: Int?,
        var bonusCompetenza: Int?,
        var dadoVita: Int?,

        var STR: Int?,
        var DEX: Int?,
        var CON: Int?,
        var INT: Int?,
        var WIS: Int?,
        var CHA: Int?,
        var tiroSalvezzaSTR: Boolean?,
        var tiroSalvezzaDEX: Boolean?,
        var tiroSalvezzaCON: Boolean?,
        var tiroSalvezzaINT: Boolean?,
        var tiroSalvezzaWIS: Boolean?,
        var tiroSalvezzaCHA: Boolean?,

        var abAcrobazia: LvlCompetenza,
        var abAddestrare:LvlCompetenza,
        var abArcano:LvlCompetenza,
        var abAtletica:LvlCompetenza,
        var abFurtivita:LvlCompetenza,
        var abIndagare:LvlCompetenza,
        var abInganno:LvlCompetenza,
        var abIntimidire:LvlCompetenza,
        var abIntrattenere:LvlCompetenza,
        var abIntuizione:LvlCompetenza,
        var abMedicina:LvlCompetenza,
        var abNatura:LvlCompetenza,
        var abPersuasione:LvlCompetenza,
        var abRapiditMano:LvlCompetenza,
        var abReligione:LvlCompetenza,
        var abSoprav:LvlCompetenza,
        var abStoria:LvlCompetenza,


        )


data class Equipaggiamento(
    @Embedded   var moneteTotali: Money,
    @Embedded   var scheda: Scheda,
    @Relation(
                parentColumn = "id",
                entityColumn = "scheda_id",
                entity = Scheda::class
    )
    var oggetti: List<Oggetti>
)
data class Money(
        var moneteR:Int?,
        var moneteA:Int?,
        var moneteE:Int?,
        var moneteO:Int?,
        var moneteP:Int?,
)




data class Incantesimi(
    var incantatore: String?,
    var caratteristica: String?,
    var cd: String?,
    var bonus: Int?

)

data class Dettagli(
    var background: String?,
    var allineamento: String?,
    var exp: Int?,
    var puntiIspirazione: Int?,
    var descrizioni: String?

)
