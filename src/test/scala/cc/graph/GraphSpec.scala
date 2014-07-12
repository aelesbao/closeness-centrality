package cc.graph

import org.scalatest.{FlatSpec, Matchers}

class GraphSpec extends FlatSpec with Matchers {
  val edges = Set((1, 2), (1, 3), (2, 3), (3, 4))

  "An Edge" should "be looped when 'from' and 'to' are equal" in {
    val edge = Edge(1, 1)
    edge shouldBe 'looped
  }

  it should "build its inverse too" in {
    val edge = Edge(1, 2)
    edge.invert should equal(Edge(2, 1))
  }

  "A Graph" should "store all vertices" in {
    val graph = Graph(edges)
    graph.vertices should contain allOf(1, 2, 3, 4)
  }

  it should "have size equals to number of vertices" in {
    val graph = Graph(edges)
    graph should have size 4
  }

  it should "test for existance of a node" in {
    val graph = Graph(edges)
    graph.contains(2) should be(true)
  }

  it should "allow access to neighbours of node" in {
    val graph = Graph(edges)
    graph(1) should equal(Set(2, 3))
  }

  it should "add the inverse of the specified edges" in {
    val graph = Graph(edges)
    graph(4) should contain(3)
  }

  it should "not allow looped edges" in {
    the [AssertionError] thrownBy {
      new Graph + (1, 1)
    } should have message "assumption failed: Cannot add a looped edge"
  }
}
