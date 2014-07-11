package cc.graph

import org.scalatest.{FlatSpec, Matchers}

class GraphSpec extends FlatSpec with Matchers {
  val edges = Set(Edge(1, 2), Edge(1, 3), Edge(2, 3), Edge(3, 4))

  "An Edge" should "be looped when 'from' and 'to' are equal" in {
    val edge = Edge(1, 1)
    edge shouldBe 'looped
  }

  "A Graph" should "store all vertices" in {
    val graph = Graph(edges)
    graph.vertices should contain allOf(1, 2, 3, 4)
  }

  it should "have size equals to number of vertices" in {
    val graph = Graph(edges)
    graph should have size 4
  }

  it should "allow access to neighbours of node" in {
    val graph = Graph(edges)
    graph(1) should equal(Set(2, 3))
  }

  it should "not allow looped edges" in {
    the [AssertionError] thrownBy {
      new Graph + Edge(1, 1)
    } should have message "assumption failed: Cannot add a looped edge"
  }
}
