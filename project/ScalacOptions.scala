object ScalacOptions {
  val options = List(
    // Code encoding
    "-encoding",
    "UTF-8",
    // Deprecation warnings
    "-deprecation",
    // Warnings about features that should be imported explicitly
    "-feature",
    // Enable additional warnings about assumptions in the generated code
    "-unchecked",
    // Recommended additional warnings
    "-Xlint",
    // Warn when argument list is modified to match receiver
    "-Ywarn-adapted-args",
    // Warn about dead code
    "-Ywarn-dead-code",
    // Warn about inaccessible types in signatures
    "-Ywarn-inaccessible",
    // Warn when non-nullary overrides a nullary (def foo() over def foo)
    "-Ywarn-nullary-override",
    // Warn when numerics are unintentionally widened
    "-Ywarn-numeric-widen"
  )
}
