package com.sqality.scct

import org.specs2.mutable._
import tools.nsc.symtab.Flags

class DebugFlagsSpec extends Specification {
  "what are these" in {
    println("Flags: " + Flags.flagsToString(131136))
  }
}