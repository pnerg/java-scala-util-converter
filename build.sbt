name := "java-scala-utils-converter"

organization := "org.dmonix.functional"

version := "1.0-SNAPSHOT"

crossScalaVersions := Seq("2.9.3", "2.10.4", "2.11.5")

scalacOptions <++= scalaVersion map { (v: String) => 
  if (v.trim.startsWith("2.1"))
    Seq("-deprecation", "-unchecked", "-feature", "-language:implicitConversions", "-language:higherKinds")
  else
    Seq("-deprecation", "-unchecked")
}

publishTo <<= version { (v: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (v.endsWith("-SNAPSHOT"))
    Some("snapshots" at nexus+"content/repositories/snapshots")
  else
    Some("releases" at nexus+"service/local/staging/deploy/maven2")
}

credentials ++= {
  val sonatype = ("Sonatype Nexus Repository Manager", "oss.sonatype.org")
  def loadMavenCredentials(file: java.io.File) : Seq[Credentials] = {
    xml.XML.loadFile(file) \ "servers" \ "server" map (s => {
      val host = (s \ "id").text
      val realm = if (host == sonatype._2) sonatype._1 else "Unknown"
      Credentials(realm, host, (s \ "username").text, (s \ "password").text)
    })
  }
  val ivyCredentials   = Path.userHome / ".ivy2" / ".credentials"
  val mavenCredentials = Path.userHome / ".m2"   / "settings.xml"
  (ivyCredentials.asFile, mavenCredentials.asFile) match {
    case (ivy, _) if ivy.canRead => Credentials(ivy) :: Nil
    case (_, mvn) if mvn.canRead => loadMavenCredentials(mvn)
    case _ => Nil
  }
}

libraryDependencies += "org.dmonix.functional" % "java-scala-utils" % "1.4"

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <url>https://github.com/pnerg/java-scala-util-converter</url>
  <licenses>
    <license>
      <name>Apache</name>
      <url>http://www.opensource.org/licenses/Apache-2.0</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:pnerg/java-scala-util-converter.git</url>
    <connection>scm:git:git@github.com:pnerg/java-scala-util-converter.git</connection>
  </scm>
  <developers>
    <developer>
      <id>pnerg</id>
      <name>Peter Nerg</name>
      <url>http://github.com/pnerg</url>
    </developer>
  </developers>)
