package cc

import scala.io.Source

object Main {
  def main(args: Array[String]) {
    println("Following is the content read:")

    val path = getClass.getResource("/edges.txt")
    Source.fromURL(path).foreach{
      print
    }
  }
}
