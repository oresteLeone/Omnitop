package dndData.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import dndData.TipoScheda
import dndData.utilData.Dettagli
import dndData.utilData.Equipaggiamento
import dndData.utilData.Incantesimi
import dndData.utilData.Statistiche


@Entity(tableName = "Scheda_table",
foreignKeys = arrayOf(
    ForeignKey(
        entity = Campagna::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("campagna_id")
        )
    )
)
data class Scheda(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "scheda_id") var id: Int,
    @ColumnInfo(name = "campagna_id") var Campagnaid: Int,
    var nomePG: String,
    var nomeGiocatore: String?,
    //var tipoScheda: TipoScheda,
    //var statistiche: Statistiche,
    //var equipaggiamento: Equipaggiamento,
    //var incantesimi: Incantesimi,
    //var dettagli: Dettagli
)


