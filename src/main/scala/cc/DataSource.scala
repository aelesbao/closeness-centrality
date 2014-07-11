package cc

import scala.io.Source

object DataSource {
  lazy val edgesPath = getClass.getResource("/edges.txt")

  def load = Source.fromURL(edgesPath)
}
