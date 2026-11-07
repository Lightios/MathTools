package pl.michal_cyran.math_tools.drawings.solids.canvas


private const val PYRAMID_BASE_HEIGHT = 0.65f
private const val PYRAMID_FRONT_Y = 0.7f

object Pyramid3Sides {
    fun edges(h: Float): List<Edge> {
        val a = Pair(0.25f, 0.7f)
        val b = Pair(0.65f, 0.7f)
        val c = Pair(0.55f, 0.6f)

        val x = Pair(0.5f, PYRAMID_FRONT_Y - h)

        return listOf(
            Edge(a, b, false),
            Edge(b, c, false),
            Edge(c, a, false),

            Edge(a, x, false),
            Edge(b, x, false),
            Edge(c, x, true),
        )
    }
}

object Pyramid4Sides {
    fun edges(h: Float): List<Edge> {
        val a = Pair(0.25f, 0.7f)
        val b = Pair(0.65f, 0.7f)
        val c = Pair(0.75f, 0.6f)
        val d = Pair(0.35f, 0.6f)

        val x = Pair(0.5f, PYRAMID_FRONT_Y - h)

        return listOf(
            Edge(a, b, false),
            Edge(b, c, false),
            Edge(c, d, false),
            Edge(d, a, false),

            Edge(a, x, false),
            Edge(b, x, false),
            Edge(c, x, false),
            Edge(d, x, true),
        )
    }
}

object Pyramid5Sides {
    fun edges(h: Float): List<Edge> {
        val a = Pair(0.346f, 0.7f)    // front left
        val b = Pair(0.654f, 0.7f)    // front right
        val c = Pair(0.75f, 0.601f)   // right
        val d = Pair(0.5f, 0.54f)     // back
        val e = Pair(0.25f, 0.601f)   // left

        val x = Pair(0.5f, PYRAMID_FRONT_Y - h)

        return listOf(
            Edge(a, b, false),
            Edge(b, c, false),
            Edge(c, d, false),
            Edge(d, e, false),
            Edge(e, a, false),

            Edge(a, x, false),
            Edge(b, x, false),
            Edge(c, x, false),
            Edge(d, x, true),
            Edge(e, x, false),
        )
    }
}

object Pyramid6Sides {
    fun edges(h: Float): List<Edge> {
        val a = Pair(0.35f, 0.7f)
        val b = Pair(0.65f, 0.7f)
        val c = Pair(0.75f, 0.62f)
        val d = Pair(0.68f, 0.54f)
        val e = Pair(0.38f, 0.54f)
        val f = Pair(0.265f, 0.62f)

        val x = Pair(0.5f, PYRAMID_FRONT_Y - h)

        return listOf(
            Edge(a, b, false),
            Edge(b, c, false),
            Edge(c, d, false),
            Edge(d, e, false),
            Edge(e, f, false),
            Edge(f, a, false),

            Edge(a, x, false),
            Edge(b, x, false),
            Edge(c, x, false),
            Edge(d, x, true),
            Edge(e, x, true),
            Edge(f, x, false),
        )
    }
}

/**
 * @param pyramidHeight 1..10, where 5 is the original proportion
 */
fun getSidesOfPyramid(sidesCount: Int, pyramidHeight: Int): List<Edge> {
    val factor = pyramidHeight.coerceIn(1, 10) / 5f

    val edges = when (sidesCount) {
        3 -> Prism3Sides.edges(Prism3Sides.BASE_HEIGHT * factor)
        5 -> Prism5Sides.edges(Prism5Sides.BASE_HEIGHT * factor)
        6 -> Prism6Sides.edges(Prism6Sides.BASE_HEIGHT * factor)
        else -> Prism4Sides.edges(Prism4Sides.BASE_HEIGHT * factor)
    }
    return keepInsideCanvas(edges)
}

private fun keepInsideCanvas(edges: List<Edge>): List<Edge> {
    val points = edges.flatMap { listOf(it.p1, it.p2) }
    val minY = points.minOf { it.second }
    val maxY = points.maxOf { it.second }

    val available = 0.9f
    val scale = minOf(1f, available / (maxY - minY))

    fun scaled(p: Pair<Float, Float>) =
        Pair(0.5f + (p.first - 0.5f) * scale, p.second * scale)

    val scaledMinY = minY * scale
    val dy = if (scaledMinY < 0.05f) 0.05f - scaledMinY else 0f

    fun transform(p: Pair<Float, Float>): Pair<Float, Float> {
        val s = scaled(p)
        return Pair(s.first, s.second + dy)
    }

    return edges.map { Edge(transform(it.p1), transform(it.p2), it.dashed) }
}
