package dndData.utilData

import android.util.Size
import androidx.room.ColumnInfo
import androidx.room.Embedded

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

        /*@Embedded    var abAcrobazia: Ability, @Embedded    var abAddestrare: Ability,
        @Embedded    var abArcano: Ability, @Embedded    var abAtletica: Ability,
        @Embedded    var abFurtivita: Ability, @Embedded    var abIndagare: Ability,
        @Embedded    var abInganno: Ability, @Embedded    var abIntimidire: Ability,
        @Embedded    var abIntrattenere: Ability, @Embedded    var abIntuizione: Ability,
        @Embedded    var abMedicina: Ability, @Embedded    var abNatura: Ability,
        @Embedded    var abPersuasione: Ability, @Embedded    var abRapiditMano: Ability,
        @Embedded    var abReligione: Ability, @Embedded    var abSoprav: Ability,
        @Embedded    var abStoria: Ability,*/







        )

data class Equipaggiamento(
    var cosaacaso: String?

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

data class Ability(
        var nome: String,
        var add: Boolean,
        var ex: Boolean,
        var modifier: Int
)