package com.harshcode.pokemon_composeapp.data.remote.response

data class HeldItem(
    val item: Item,
    val version_details: List<VersionDetail>
)