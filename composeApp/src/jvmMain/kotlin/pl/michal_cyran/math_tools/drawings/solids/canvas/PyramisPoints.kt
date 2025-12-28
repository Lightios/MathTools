package pl.michal_cyran.math_tools.drawings.solids.canvas


object Pyramid3Sides {

    val a = Pair(0.25f, 0.7f)
    val b = Pair(0.65f, 0.7f)
    val c = Pair(0.55f, 0.6f)

    val x = Pair(0.5f, 0.05f)

    val sides = listOf(
        a, b, false,
        b, c, false,
        c, a, false,

        a, x, false,
        b, x, false,
        c, x, true,

    )
}

object Pyramid4Sides {

    val a = Pair(0.25f, 0.7f)
    val b = Pair(0.65f, 0.7f)
    val c = Pair(0.75f, 0.6f)
    val d = Pair(0.35f, 0.6f)

    val x = Pair(0.5f, 0.05f)

    val sides = listOf(
        a, b, false,
        b, c, false,
        c, d, false,
        d, a, false,

        a, x, false,
        b, x, false,
        c, x, false,
        d, x, true,
    )
}

object Pyramid6Sides {
    val a = Pair(0.35f, 0.7f)
    val b = Pair(0.65f, 0.7f)
    val c = Pair(0.75f, 0.62f)
    val d = Pair(0.68f, 0.54f)
    val e = Pair(0.38f, 0.54f)
    val f = Pair(0.265f, 0.62f)

    val x = Pair(0.5f, 0.05f)


    val sides = listOf(
        a, b, false,
        b, c, false,
        c, d, false,
        d, e, false,
        e, f, false,
        f, a, false,

        a, x, false,
        b, x, false,
        c, x, false,
        d, x, true,
        e, x, true,
        f, x, false,
    )
}

fun getSidesOfPyramid(sidesCount: Int): List<Any> {
    return when (sidesCount) {
        3 -> Pyramid3Sides.sides
        4 -> Pyramid4Sides.sides
        6 -> Pyramid6Sides.sides
        else -> Pyramid4Sides.sides
    }
}