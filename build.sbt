name := """BookStoreApp"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava,PlayEbean)

scalaVersion := "2.12.7"

crossScalaVersions := Seq("2.11.12", "2.12.4")

libraryDependencies += javaJdbc

libraryDependencies += guice

// Test Database
libraryDependencies += "com.h2database" % "h2" % "1.4.197"

// Testing libraries for dealing with CompletionStage...
libraryDependencies += "org.assertj" % "assertj-core" % "3.6.2" % Test
libraryDependencies += "org.awaitility" % "awaitility" % "2.0.0" % Test

// Make verbose tests
testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.41"

// Resolver is needed only for SNAPSHOT versions
resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies ++= Seq(
  "com.adrianhurt" %% "play-bootstrap" % "1.4-P26-B3-SNAPSHOT"
)

libraryDependencies += "com.typesafe.play" %% "play-mailer" % "6.0.1"
libraryDependencies += "com.typesafe.play" %% "play-mailer-guice" % "6.0.1"

// https://mvnrepository.com/artifact/org.mindrot/jbcrypt
libraryDependencies += "org.mindrot" % "jbcrypt" % "0.3m"


libraryDependencies ++= Seq(
  ws
)
libraryDependencies += "javax.servlet" % "servlet-api" % "3.0-alpha-1" % "provided"

libraryDependencies ++= Seq(
 
 "ws.securesocial" %% "securesocial" % "master-SNAPSHOT"
)

libraryDependencies ++= Seq(

  "com.restfb" % "restfb" % "2.18.1",
  "org.apache.mahout" % "mahout-core" % "0.9"
)

resolvers += "Scala-Tools Maven2 Snapshots Repository" at "http://scala-tools.org/repo-snapshots"

// https://mvnrepository.com/artifact/com.google.guava/guava
libraryDependencies += "com.google.guava" % "guava" % "15.0"
libraryDependencies ++= Seq(
  javaJpa,
  "org.hibernate" % "hibernate-entitymanager" % "5.1.0.Final" // replace by your jpa implementation
)

PlayKeys.externalizeResources := false

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.3.4"





