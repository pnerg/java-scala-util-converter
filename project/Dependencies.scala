import sbt._

object Dependencies extends AutoPlugin {

  object autoImport {
    /**
     * ------------------------------
     * Compile/hard dependencies
     * ------------------------------
     */
    val `java-scala-utils` = "org.dmonix.functional" % "java-scala-utils" % "1.11"
    /**
     * ------------------------------
     * Test dependencies
     * ------------------------------
     */
    val scalatest = "org.scalatest" %% "scalatest" % "3.2.3"
  }

}
