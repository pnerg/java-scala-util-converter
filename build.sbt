name := "java-scala-utils-converter"
organization := "org.dmonix.functional"
version := "1.1"

scalaVersion := "2.12.12"
crossScalaVersions := Seq("2.11.12", "2.12.12")

scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature", "-language:implicitConversions", "-language:higherKinds", "-target:jvm-1.8")
scalacOptions in (Compile, doc) ++= Seq("-doc-title", "Java Scala Utils Converter API")
scalacOptions in (Compile, doc) ++= Seq("-doc-root-content", baseDirectory.value+"/src/main/scaladoc/root-doc.txt")
scalacOptions in (Compile, doc) ++= Seq("-doc-footer", "Copyright (c) 2015 Peter Nerg, Apache License v2.0.")

libraryDependencies ++= Seq(
  `java-scala-utils`,
  scalatest % Test
)