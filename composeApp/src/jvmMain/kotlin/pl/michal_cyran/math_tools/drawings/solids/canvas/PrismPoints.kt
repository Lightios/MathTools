package pl.michal_cyran.math_tools.drawings.solids.canvas


object Prism3Sides {
    // original vertical distance between top and bottom faces (this is "height 5")
    const val BASE_HEIGHT = 0.72f

    fun edges(h: Float): List<Edge> {
        val a = Pair(0.05f, 0.23f)
        val b = Pair(0.95f, 0.23f)
        val c = Pair(0.725f, 0.05f)

        val d = Pair(a.first, a.second + h)
        val e = Pair(b.first, b.second + h)
        val f = Pair(c.first, c.second + h)

        return listOf(
            Edge(a, b, false),
            Edge(b, c, false),
            Edge(c, a, false),

            Edge(a, d, false),
            Edge(b, e, false),
            Edge(c, f, true),

            Edge(d, e, false),
            Edge(e, f, true),
            Edge(f, d, true),
        )
    }
}

object Prism4Sides {
    const val BASE_HEIGHT = 0.764f

    fun edges(h: Float): List<Edge> {
        val a = Pair(0.05f, 0.186f)
        val b = Pair(0.77f, 0.186f)
        val c = Pair(0.95f, 0.05f)
        val d = Pair(0.23f, 0.05f)

        val e = Pair(a.first, a.second + h)
        val f = Pair(b.first, b.second + h)
        val g = Pair(c.first, c.second + h)
        val hh = Pair(d.first, d.second + h)

        return listOf(
            Edge(a, b, false),
            Edge(b, c, false),
            Edge(c, d, false),
            Edge(d, a, false),

            Edge(a, e, false),
            Edge(b, f, false),
            Edge(c, g, false),
            Edge(d, hh, true),

            Edge(e, f, false),
            Edge(f, g, false),
            Edge(g, hh, true),
            Edge(hh, e, true),
        )
    }
}

object Prism5Sides {
    // Same total proportions as the 6-sided prism: top y = 0.05, bottom front y = 0.95 at height 5
    const val BASE_HEIGHT = 0.71f

    fun edges(h: Float): List<Edge> {
        val a = Pair(0.222f, 0.24f)   // front left
        val b = Pair(0.778f, 0.24f)   // front right
        val c = Pair(0.95f, 0.123f)   // right
        val d = Pair(0.5f, 0.05f)     // back
        val e = Pair(0.05f, 0.123f)   // left

        val a2 = Pair(a.first, a.second + h)
        val b2 = Pair(b.first, b.second + h)
        val c2 = Pair(c.first, c.second + h)
        val d2 = Pair(d.first, d.second + h)
        val e2 = Pair(e.first, e.second + h)

        return listOf(
            // top face
            Edge(a, b, false),
            Edge(b, c, false),
            Edge(c, d, false),
            Edge(d, e, false),
            Edge(e, a, false),

            // vertical edges
            Edge(a, a2, false),
            Edge(b, b2, false),
            Edge(c, c2, false),
            Edge(d, d2, true),
            Edge(e, e2, false),

            // bottom face
            Edge(a2, b2, false),
            Edge(b2, c2, false),
            Edge(c2, d2, true),
            Edge(d2, e2, true),
            Edge(e2, a2, false),
        )
    }
}

object Prism6Sides {
    const val BASE_HEIGHT = 0.71f

    fun edges(h: Float): List<Edge> {
        val a = Pair(0.208f, 0.24f)
        val b = Pair(0.764f, 0.24f)
        val c = Pair(0.95f, 0.145f)
        val d = Pair(0.82f, 0.05f)
        val e = Pair(0.263f, 0.05f)
        val f = Pair(0.05f, 0.145f)

        val a2 = Pair(a.first, a.second + h)
        val b2 = Pair(b.first, b.second + h)
        val c2 = Pair(c.first, c.second + h)
        val d2 = Pair(d.first, d.second + h)
        val e2 = Pair(e.first, e.second + h)
        val f2 = Pair(f.first, f.second + h)

        return listOf(
            Edge(a, b, false),
            Edge(b, c, false),
            Edge(c, d, false),
            Edge(d, e, false),
            Edge(e, f, false),
            Edge(f, a, false),

            Edge(a, a2, false),
            Edge(b, b2, false),
            Edge(c, c2, false),
            Edge(d, d2, true),
            Edge(e, e2, true),
            Edge(f, f2, false),

            Edge(a2, b2, false),
            Edge(b2, c2, false),
            Edge(c2, d2, true),
            Edge(d2, e2, true),
            Edge(e2, f2, true),
            Edge(f2, a2, false),
        )
    }
}

fun getSidesOfPrism(sidesCount: Int, prismHeight: Int): List<Edge> {
    val factor = prismHeight.coerceIn(1, 10) / 5f

    val edges = when (sidesCount) {
        3 -> Prism3Sides.edges(Prism3Sides.BASE_HEIGHT * factor)
        5 -> Prism5Sides.edges(Prism5Sides.BASE_HEIGHT * factor)
        6 -> Prism6Sides.edges(Prism6Sides.BASE_HEIGHT * factor)
        else -> Prism4Sides.edges(Prism4Sides.BASE_HEIGHT * factor)
    }
    return fitToCanvas(edges)
}

private fun fitToCanvas(edges: List<Edge>): List<Edge> {
    val points = edges.flatMap { listOf(it.p1, it.p2) }
    val minX = points.minOf { it.first }
    val maxX = points.maxOf { it.first }
    val minY = points.minOf { it.second }
    val maxY = points.maxOf { it.second }

    val available = 0.9f
    val scale = minOf(available / (maxX - minX), available / (maxY - minY))

    val offsetX = 0.5f - (minX + maxX) / 2f * scale
    val offsetY = 0.5f - (minY + maxY) / 2f * scale

    fun transform(p: Pair<Float, Float>) =
        Pair(p.first * scale + offsetX, p.second * scale + offsetY)

    return edges.map { Edge(transform(it.p1), transform(it.p2), it.dashed) }
}

