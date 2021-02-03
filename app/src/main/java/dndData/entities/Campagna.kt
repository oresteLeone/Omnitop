package dndData.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import dndData.RuoloGiocatore

@Entity(tableName = "Campagna_table")
data class Campagna(
        @PrimaryKey(autoGenerate = true) val id: Int,
        var nome: String,
       // var ruolo: RuoloGiocatore,
        var descrizione: String?,
        //var copertina: Blob?,


)

