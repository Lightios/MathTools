package pl.michal_cyran.math_tools.drawings.solids.canvas

data class Edge(
    val p1: Pair<Float, Float>,
    val p2: Pair<Float, Float>,
    val dashed: Boolean,
)
