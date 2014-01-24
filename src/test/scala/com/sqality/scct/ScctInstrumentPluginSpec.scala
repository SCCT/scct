package com.sqality.scct

import org.specs2.mutable._
import org.specs2.mock._
import tools.nsc.Global

class ScctInstrumentPluginSpec extends Specification with Mockito {
  val sut = new ScctInstrumentPlugin(smartMock[Global])
  "Plugin" should {
    "provide metadata" in {
      sut.name mustEqual "scct"
      sut.description mustEqual "Scala code coverage instrumentation plugin."
    }
    "only contain the transformer component" in {
      val components = sut.components
      components.size mustEqual 1
      components(0) must haveClass[ScctTransformComponent]
    }
    "run after typer and before patmat" in {
      val c = sut.components(0)
      c.runsAfter mustEqual List("typer")
      c.runsBefore mustEqual List("patmat")
    }
  }

  "Plugin options" should {
    "have defaults" in {
      sut.scctOptions.projectId must not be empty
      sut.scctOptions.baseDir.getName must not be empty
    }
    "be settable" in {
      sut.processOptions(List("basedir:/base/dir", "projectId:myProject", "excludePackages:myRegex,yourRegex",
        "excludeFiles:regex1,regex2,regex3"), s => ())
      sut.scctOptions.projectId mustEqual "myProject"
      sut.scctOptions.baseDir.getAbsolutePath mustEqual "/base/dir"
      sut.scctOptions.excludeClasses must haveClass[Array[scala.util.matching.Regex]]
      sut.scctOptions.excludeClasses(0).toString mustEqual "myRegex"
      sut.scctOptions.excludeClasses(1).toString mustEqual "yourRegex"
      sut.scctOptions.excludeFiles(0).toString mustEqual "regex1"
      sut.scctOptions.excludeFiles(1).toString mustEqual "regex2"
      sut.scctOptions.excludeFiles(2).toString mustEqual "regex3"
    }
    "report error" in {
      var err = ""
      sut.processOptions(List("wrong:option"), s => { err = s })
      err mustEqual "Unknown option: wrong:option"
    }
  }
}
