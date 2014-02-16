organization := "com.sqality.scct"

name := "scct"

version := "0.3-SNAPSHOT"

scalaVersion := "2.10.3"

autoScalaLibrary := false

crossScalaVersions := Seq("2.10.3", "2.11.0-M8")

resolvers += Opts.resolver.sonatypeSnapshots

libraryDependencies ++= Seq(
  "org.scala-lang" %  "scala-compiler"       % scalaVersion.value % "provided",
      "org.specs2" %% "specs2-core"          % "2.3.7"            % "test",
      "org.specs2" %% "specs2-mock"          % "2.3.7"            % "test",
      "org.specs2" %% "specs2-matcher-extra" % "2.3.7"            % "test",
      "org.specs2" %% "specs2-junit"         % "2.3.7"            % "test",
           "junit" %  "junit"                % "4.11"             % "test",
     "org.mockito" %  "mockito-core"         % "1.9.5"            % "test"
)

testOptions in Test ++= Seq(
  Tests.Setup(() => System.setProperty("scct-test-scala-version", scalaVersion.value)),
  Tests.Setup(() => System.setProperty("scct-test-scala-binary-version", scalaBinaryVersion.value))
)

// lame
conflictWarning ~= { _.copy(failOnConflict = false) }

// coveralls
// seq(com.github.theon.coveralls.CoverallsPlugin.coverallsSettings: _*)

// automatic publishing
publishTo <<= version { (v: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT")) Some("snapshots" at nexus + "content/repositories/snapshots")
  else Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { x => false }

pomExtra := <url>http://sqality.com</url>
  <licenses>
    <license>
      <name>Apache License, Version 2.0</name>
      <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:sqality/scct.git</url>
    <connection>scm:git:git@github.com:sqality/scct.git</connection>
  </scm>
  <developers>
    <developer>
      <id>mtkopone</id>
      <name>Mikko Koponen</name>
      <url>http://mtkopone.github.com</url>
    </developer>
    <developer>
      <id>D-Roch</id>
      <name>Roch Delsalle</name>
    </developer>
  </developers>

scalariformSettings

seq(ScctBuild.instrumentSettings : _*)

credentials += Credentials("Sonatype Nexus Repository Manager", "oss.sonatype.org", System.getenv("SONATYPE_USER"), System.getenv("SONATYPE_PASS"))
