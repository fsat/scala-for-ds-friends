package mine

import com.holdenkarau.spark.testing.DataFrameSuiteBase
import org.apache.spark.sql.SparkSession
import org.scalatest.{ FeatureSpec, GivenWhenThen, Matchers }

class PersonSpec extends FeatureSpec with GivenWhenThen with Matchers with DataFrameSuiteBase {

  val FixturePath = this.getClass.getResource("/fixtures/simple/person").getFile

  /*
    We are using the `implicit` keyword to tell the Scala compiler to automatically pass the result of this method
    (i.e. the `SparkSession`) into a method which requests for `SparkSession` implicitly.
   */
  implicit def sparkSession: SparkSession = spark

  feature("Person") {
    scenario("list") {
      When("the list of person is loaded")
      val result = Person.list(FixturePath)

      Then("the result should match the expected result")
      val expectedResult = Seq(
        Person("Thomas", 10),
        Person("Janet", 3),
        Person("Aaron", 12),
        Person("Daniel", 45),
        Person("Tim", 27),
        Person("Nicole", 29),
        Person("Anna", 63))
      result.collect() shouldBe expectedResult
    }

    scenario("findByName") {
      When("the person is found by name")
      val result = Person.findByName(FixturePath, name = "Thomas")

      Then("the result should match the expected result")
      val expectedResult = Seq(Person("Thomas", 10))
      result.collect() shouldBe expectedResult
    }

    scenario("findOlderThan") {
      When("the person older than 30 is requested")
      val result = Person.findOlderThan(FixturePath, age = 30)

      Then("the result should match the expected result")
      val expectedResult = Seq(
        Person("Daniel", 45),
        Person("Anna", 63))
      result.collect() shouldBe expectedResult
    }

    scenario("sortByName") {
      When("the list of person is sorted by name")
      val result = Person.sortByName(FixturePath)

      Then("the result should match the expected result")
      val expectedResult = Seq(
        Person("Aaron", 12),
        Person("Anna", 63),
        Person("Daniel", 45),
        Person("Janet", 3),
        Person("Nicole", 29),
        Person("Thomas", 10),
        Person("Tim", 27))
      result.collect() shouldBe expectedResult
    }

    scenario("sortByAge") {
      When("the list of person is sorted by age, youngest first")
      val result = Person.sortByAge(FixturePath, oldestFirst = false)

      Then("the result should match the expected result")
      val expectedResult = Seq(
        Person("Janet", 3),
        Person("Thomas", 10),
        Person("Aaron", 12),
        Person("Tim", 27),
        Person("Nicole", 29),
        Person("Daniel", 45),
        Person("Anna", 63))
      result.collect() shouldBe expectedResult
    }

    scenario("oldest") {
      When("the oldest person is requested")
      val result = Person.oldest(FixturePath)

      Then("the result should match the expected result")
      result shouldBe Person("Anna", 63)
    }

    scenario("groupByFirstLetterOfTheName") {
      When("the list of person is grouped by first letter of the Name")
      val result = Person.groupByFirstLetterOfTheName(FixturePath)

      Then("the result should match the expected result")
      val expectedResult = Map(
        "A" -> Seq(Person("Aaron", 12), Person("Anna", 63)),
        "D" -> Seq(Person("Daniel", 45)),
        "J" -> Seq(Person("Janet", 3)),
        "N" -> Seq(Person("Nicole", 29)),
        "T" -> Seq(Person("Thomas", 10), Person("Tim", 27)))
      result shouldBe expectedResult
    }

  }

}
