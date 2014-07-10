name := "Closeness Centrality"

version := "1.0"

scalaVersion := "2.11.1"

mainClass := Some("cc.Main")

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.11" % "2.2.0" % "test"
)

sources in (Compile,doc) := Seq.empty