package dndData.database

import androidx.room.TypeConverter
import dndData.RuoloGiocatore
import dndData.TipoScheda

class CustomConverters {

    @TypeConverter
    fun toRuoloGiocatore(value: String) = enumValueOf<RuoloGiocatore>(value)

    @TypeConverter
    fun fromRuoloGiocatore(value: RuoloGiocatore) = value.name

    @TypeConverter
    fun toTipoScheda(value: String) = enumValueOf<TipoScheda>(value)

    @TypeConverter
    fun fromTipoScheda(value: TipoScheda) = value.name

    /*@TypeConverter
    fun toCaratteristiche(value: String) = enumValueOf<Caratteristiche>(value)

    @TypeConverter
    fun fromCaratteristiche(value: Caratteristiche) = value.name*/


}