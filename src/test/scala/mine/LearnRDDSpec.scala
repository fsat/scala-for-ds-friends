package mine

import com.holdenkarau.spark.testing.SharedSparkContext
import org.apache.spark.SparkContext
import org.scalatest.{ FeatureSpec, GivenWhenThen, Matchers }

class LearnRDDSpec extends FeatureSpec with GivenWhenThen with Matchers with SharedSparkContext {
  val FixturePath = this.getClass.getResource("/fixtures/simple/person").getFile

  /*
  We are using the `implicit` keyword to tell the Scala compiler to automatically pass the result of this method
  (i.e. the `SparkContext`) into a method which requests for `SparkContext` implicitly.


   */
  implicit def sparkContext(): SparkContext = sc

  feature("Spark RDD") {
    scenario("Read RDD[String]") {
      When("we ask Spark to read a directory containing one or more text file")
      val result = sparkContext().textFile(FixturePath)

      Then("the resulting RDD should contain all the text content of the file")
      val expectedResult = Seq(
        "Thomas 10",
        "Janet 3",
        "Aaron 12",
        "Daniel 45",
        "Tim 27",
        "Nicole 29",
        "Anna 63")
      result.collect() shouldBe expectedResult
    }

    scenario("RDD transformation using map method") {
      When("we ask Spark to read a directory containing one or more text file")
      val rdd = sparkContext().textFile(FixturePath)

      And("we use the map method to transform the String to Person instance")
      val result = rdd.map(text => Person.fromString(text))

      Then("the resulting RDD should be RDD of Person containing all the Person listed in the File")
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
  }

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
        "A" -> Seq(Person("Aaron", 12)))
      result shouldBe expectedResult
    }

  }

}
