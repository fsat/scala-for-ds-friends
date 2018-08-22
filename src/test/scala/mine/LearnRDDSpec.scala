package mine

import com.holdenkarau.spark.testing.SharedSparkContext
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
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

}
