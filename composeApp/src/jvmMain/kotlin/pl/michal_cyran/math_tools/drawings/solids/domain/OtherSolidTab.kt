package pl.michal_cyran.math_tools.drawings.solids.domain

import org.jetbrains.compose.resources.DrawableResource


data class OtherSolidTab(
    val name: String,
    val solid: OtherSolid,
    val icon: DrawableResource
)