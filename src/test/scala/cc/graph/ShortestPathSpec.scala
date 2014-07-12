package cc.graph

import org.scalatest.{FlatSpec, Matchers}

class ShortestPathSpec extends FlatSpec with Matchers {
  /*
   * Example Graph:
   *    _ 2 _
   *   /  |  \
   * 1 -- 3 -- 4 -- 5
   *
   */
  val edges = Set(Edge(1, 2), Edge(1, 3), Edge(2, 3), Edge(2, 4), Edge(3, 4), Edge(4, 5))
  val graph = Graph(edges)

  "Dijkstra's algorithm" should "calculate shortest path from source to all nodes" in {
    val dijkstra = new Dijkstra(graph)
    val distances = dijkstra.distancesFrom(2)
    val expectedDistances = Map(1 -> 1, 3 -> 1, 4 -> 1, 5 -> 2)
    distances should equal(expectedDistances)
  }

  it should "validate that the source node exists" in {
    val dijkstra = new Dijkstra(graph)
    the [AssertionError] thrownBy {
      dijkstra.distancesFrom(0)
    } should have message "assumption failed: Source is not known!"
  }
}
