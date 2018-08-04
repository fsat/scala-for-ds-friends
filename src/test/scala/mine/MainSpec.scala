package mine

import org.scalatest.{ FunSpec, Matchers }

class MainSpec extends FunSpec with Matchers {
  describe("plusOne") {
    it("adds one") {
      Main.plusOne(1) shouldBe 2
    }

    it("works with negative numbers") {
      Main.plusOne(-1) shouldBe 0
    }
  }
}
