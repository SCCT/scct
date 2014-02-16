package com.sqality.scct

import org.specs2.mutable._
import reflect.internal.Flags

class DebugFlagsSpec extends Specification {
  type FlagsType = AnyRef { def flagsToString(flags: Long): String }

  "what are these" in {
    println("Flags: " + Flags.asInstanceOf[FlagsType].flagsToString(131136))
    ok
  }
}
