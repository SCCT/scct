organization := "reaktor"

name := "scct"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.10.3"

autoScalaLibrary := false

crossScalaVersions := Seq("2.10.3", "2.11.0-M7")

resolvers += Opts.resolver.sonatypeSnapshots

libraryDependencies ++= Seq(
  "org.scala-lang" %  "scala-compiler" % scalaVersion.value % "provided",
      "org.specs2" %% "specs2"         % "1.14"             % "test",
           "junit" %  "junit"          % "4.10"             % "test",
     "org.mockito" %  "mockito-all"    % "1.9.5"            % "test" withSources
)

testOptions in Test ++= Seq(
  Tests.Setup(() => System.setProperty("scct-test-scala-version", scalaVersion.value)),
  Tests.Setup(() => System.setProperty("scct-test-scala-binary-version", scalaBinaryVersion.value))
)

// lame
conflictWarning ~= { _.copy(failOnConflict = false) }
