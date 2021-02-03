package dndData.entities

import androidx.room.*
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
    var tipoScheda: TipoScheda,
    @Embedded
    var statistiche: Statistiche,
    @Embedded
    var equipaggiamento: Equipaggiamento,
    @Embedded
    var incantesimi: Incantesimi,
    @Embedded
    var dettagli: Dettagli
)


