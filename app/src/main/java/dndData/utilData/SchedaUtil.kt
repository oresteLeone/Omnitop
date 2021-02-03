package dndData.utilData

data class Statistiche(
    var razza: String?,
    var classe: String?,
    var lvl: Int?

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
