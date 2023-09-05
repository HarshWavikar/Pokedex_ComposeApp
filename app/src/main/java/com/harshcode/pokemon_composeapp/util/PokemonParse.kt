package com.harshcode.pokemon_composeapp.util

import androidx.compose.ui.graphics.Color
import com.harshcode.pokemon_composeapp.data.remote.response.Stat
import com.harshcode.pokemon_composeapp.data.remote.response.Type
import com.harshcode.pokemon_composeapp.ui.theme.AtkColor
import com.harshcode.pokemon_composeapp.ui.theme.DefColor
import com.harshcode.pokemon_composeapp.ui.theme.HPColor
import com.harshcode.pokemon_composeapp.ui.theme.SpAtkColor
import com.harshcode.pokemon_composeapp.ui.theme.SpDefColor
import com.harshcode.pokemon_composeapp.ui.theme.SpdColor
import com.harshcode.pokemon_composeapp.ui.theme.TypeBug
import com.harshcode.pokemon_composeapp.ui.theme.TypeDark
import com.harshcode.pokemon_composeapp.ui.theme.TypeDragon
import com.harshcode.pokemon_composeapp.ui.theme.TypeElectric
import com.harshcode.pokemon_composeapp.ui.theme.TypeFairy
import com.harshcode.pokemon_composeapp.ui.theme.TypeFighting
import com.harshcode.pokemon_composeapp.ui.theme.TypeFire
import com.harshcode.pokemon_composeapp.ui.theme.TypeFlying
import com.harshcode.pokemon_composeapp.ui.theme.TypeGhost
import com.harshcode.pokemon_composeapp.ui.theme.TypeGrass
import com.harshcode.pokemon_composeapp.ui.theme.TypeGround
import com.harshcode.pokemon_composeapp.ui.theme.TypeIce
import com.harshcode.pokemon_composeapp.ui.theme.TypeNormal
import com.harshcode.pokemon_composeapp.ui.theme.TypePoison
import com.harshcode.pokemon_composeapp.ui.theme.TypePsychic
import com.harshcode.pokemon_composeapp.ui.theme.TypeRock
import com.harshcode.pokemon_composeapp.ui.theme.TypeSteel
import com.harshcode.pokemon_composeapp.ui.theme.TypeWater
import java.util.Locale

fun parseTypeToColor(type : Type): Color{
    return when(type.type.name.lowercase(Locale.ROOT)){
        "normal" -> TypeNormal
        "fire" -> TypeFire
        "water" -> TypeWater
        "electric" -> TypeElectric
        "grass" -> TypeGrass
        "ice" -> TypeIce
        "fighting" -> TypeFighting
        "poison" -> TypePoison
        "ground" -> TypeGround
        "flying" -> TypeFlying
        "psychic" -> TypePsychic
        "bug" -> TypeBug
        "rock" -> TypeRock
        "ghost" -> TypeGhost
        "dragon" -> TypeDragon
        "dark" -> TypeDark
        "steel" -> TypeSteel
        "fairy" -> TypeFairy
        else -> Color.Black
    }
}

fun parseStatToColor(stat: Stat): Color {
    return when(stat.stat.name.lowercase(Locale.ROOT)) {
        "hp" -> HPColor
        "attack" -> AtkColor
        "defense" -> DefColor
        "special-attack" -> SpAtkColor
        "special-defense" -> SpDefColor
        "speed" -> SpdColor
        else -> Color.White
    }
}

fun parseStatToAbbr(stat: Stat): String {
    return when(stat.stat.name.lowercase(Locale.ROOT)) {
        "hp" -> "HP"
        "attack" -> "Atk"
        "defense" -> "Def"
        "special-attack" -> "SpAtk"
        "special-defense" -> "SpDef"
        "speed" -> "Spd"
        else -> ""
    }
}