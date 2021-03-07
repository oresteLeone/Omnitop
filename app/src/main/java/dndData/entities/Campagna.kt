package dndData.entities

import android.graphics.Bitmap
import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import dndData.RuoloGiocatore

@Entity(tableName = "Campagna_table")
data class Campagna(
        @PrimaryKey(autoGenerate = true) val id: Int,
        var titoloCampagna: String,
        var ruoloCampagna: RuoloGiocatore,
        var descrizione: String?,
        var copertinaBitmap: Bitmap?


)

