package cc

import cc.graph.{Graph, Edge}

import scala.io.Source

object Main {
  def main(args: Array[String]) {
    val edges = loadEdges(getClass.getResource("/edges.txt"))
    val graph = Graph(edges)
    println(graph)
  }

  def loadEdges(path: java.net.URL) = {
    val source = Source.fromURL(path)
    val lines = source.getLines().map(_.split(" "))
    lines.map(l => Edge(l(0), l(1))).toIterable
  }
}
