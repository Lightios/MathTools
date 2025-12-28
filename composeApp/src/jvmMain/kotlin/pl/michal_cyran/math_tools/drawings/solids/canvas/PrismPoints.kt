package pl.michal_cyran.math_tools.drawings.solids.canvas


object Prism3Sides {
    val a = Pair(0.25f, 0.3f)
    val b = Pair(0.65f, 0.3f)
    val c = Pair(0.55f, 0.15f)

    val d = Pair(0.25f, 0.9f)
    val e = Pair(0.65f, 0.9f)
    val f = Pair(0.55f, 0.75f)

    val sides = listOf(
        a, b, false,
        b, c, false,
        c, a, false,

        a, d, false,
        b, e, false,
        c, f, true,

        d, e, false,
        e, f, true,
        f, d, true,
    )
}

object Prism4Sides {
    val a = Pair(0.25f, 0.3f)
    val b = Pair(0.65f, 0.3f)
    val c = Pair(0.75f, 0.2f)
    val d = Pair(0.35f, 0.2f)

    val e = Pair(0.25f, 0.9f)
    val f = Pair(0.65f, 0.9f)
    val g = Pair(0.75f, 0.8f)
    val h = Pair(0.35f, 0.8f)

    val sides = listOf(
        a, b, false,
        b, c, false,
        c, d, false,
        d, a, false,

        a, e, false,
        b, f, false,
        c, g, false,
        d, h, true,

        e, f, false,
        f, g, false,
        g, h, true,
        h, e, true,
    )
}

object Prism6Sides {
    val a = Pair(0.35f, 0.3f)
    val b = Pair(0.65f, 0.3f)
    val c = Pair(0.75f, 0.22f)
    val d = Pair(0.68f, 0.14f)
    val e = Pair(0.38f, 0.14f)
    val f = Pair(0.265f, 0.22f)

    val a2 = Pair(0.35f, 0.9f)
    val b2 = Pair(0.65f, 0.9f)
    val c2 = Pair(0.75f, 0.82f)
    val d2 = Pair(0.68f, 0.74f)
    val e2 = Pair(0.38f, 0.74f)
    val f2 = Pair(0.265f, 0.82f)


    val sides = listOf(
        a, b, false,
        b, c, false,
        c, d, false,
        d, e, false,
        e, f, false,
        f, a, false,

        a, a2, false,
        b, b2, false,
        c, c2, false,
        d, d2, true,
        e, e2, true,
        f, f2, false,

        a2, b2, false,
        b2, c2, false,
        c2, d2, true,
        d2, e2, true,
        e2, f2, true,
        f2, a2, false,
    )
}

fun getSidesOfPrism(sidesCount: Int): List<Any> {
    return when (sidesCount) {
        3 -> Prism3Sides.sides
        4 -> Prism4Sides.sides
        6 -> Prism6Sides.sides
        else -> Prism4Sides.sides
    }
}