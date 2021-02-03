package dndData.database

import androidx.room.TypeConverter
import dndData.LvlCompetenza
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

    @TypeConverter
    fun toLvlCompetenza(value: String) = enumValueOf<LvlCompetenza>(value)

    @TypeConverter
    fun fromLvlCompetenza(value: LvlCompetenza) = value.name

}