organization := "com.sqality.scct"

name := "scct"

version := "0.3-SNAPSHOT"

scalaVersion := "2.10.3"

autoScalaLibrary := false

crossScalaVersions := Seq("2.10.3", "2.11.0-M7")

resolvers += Opts.resolver.sonatypeSnapshots

libraryDependencies ++= Seq(
  "org.scala-lang" %  "scala-compiler"       % scalaVersion.value % "provided",
      "org.specs2" %% "specs2-core"          % "2.3.6"            % "test",
      "org.specs2" %% "specs2-mock"          % "2.3.6"            % "test",
      "org.specs2" %% "specs2-matcher-extra" % "2.3.6"            % "test",
      "org.specs2" %% "specs2-junit"         % "2.3.6"            % "test",
           "junit" %  "junit"                % "4.11"             % "test",
     "org.mockito" %  "mockito-core"         % "1.9.5"            % "test"
)

testOptions in Test ++= Seq(
  Tests.Setup(() => System.setProperty("scct-test-scala-version", scalaVersion.value)),
  Tests.Setup(() => System.setProperty("scct-test-scala-binary-version", scalaBinaryVersion.value))
)

// lame
conflictWarning ~= { _.copy(failOnConflict = false) }
