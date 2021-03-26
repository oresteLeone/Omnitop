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
        var puntiFeritaAttuali: Int?,
        var puntiFeritaTotali: Int?,
        var classeArmatura: Int?,
        var iniziativa: Int?,
        var velocitaMov: Double?,
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

        var abAcrobazia: Int?,
        var abAddestrare:Int?,
        var abArcano:Int?,
        var abAtletica:Int?,
        var abFurtivita:Int?,
        var abIndagare:Int?,
        var abInganno:Int?,
        var abIntimidire:Int?,
        var abIntrattenere:Int?,
        var abIntuizione:Int?,
        var abMedicina:Int?,
        var abNatura:Int?,
        var abPercezione:Int?,
        var abPersuasione:Int?,
        var abRapiditMano:Int?,
        var abReligione:Int?,
        var abSoprav:Int?,
        var abStoria:Int?

        )

//-------------------------------------------------------------

data class Money(
        var moneteR:Int?,
        var moneteA:Int?,
        var moneteE:Int?,
        var moneteO:Int?,
        var moneteP:Int?,
)

//-------------------------------------------------------------


data class Incantatore(
    var incantatoreClasse: String?,
    var carIncantatore: String?,
    var cd: Int?,
    var bonus: Int?,
    var slotLVL1MAX: Int?,
    var slotLVL1: Int?,
    var slotLVL2MAX: Int?,
    var slotLVL2: Int?,
    var slotLVL3MAX: Int?,
    var slotLVL3: Int?,
    var slotLVL4MAX: Int?,
    var slotLVL4: Int?,
    var slotLVL5MAX: Int?,
    var slotLVL5: Int?,
    var slotLVL6MAX: Int?,
    var slotLVL6: Int?,
    var slotLVL7MAX: Int?,
    var slotLVL7: Int?,
    var slotLVL8MAX: Int?,
    var slotLVL8: Int?,
    var slotLVL9MAX: Int?,
    var slotLVL9: Int?

)

//-------------------------------------------------------------

data class Dettagli(
    var razza: String?,
    var classe: String?,
    var background: String?,
    var nomeGiocatore: String?,
    var allineamento: String?,
    var exp: Int?,
    var puntiIspirazione: Int?,
    var altreInformazioni: String?

)
