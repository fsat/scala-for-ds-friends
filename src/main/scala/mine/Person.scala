package mine

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

object Person {
  /**
   * Implement the list method this way:
   * 1. Use the [[SparkHelper.readText()]] to obtain the [[RDD]] of [[String]].
   * 2. The [[RDD.map()]] method takes a function which transforms the content of RDD.
    *
    * Refer to the `LearnRDDSpec`'s scenario called `RDD transformation using map method` on how to pass a function
    * into [[RDD.map()]].
   */
  def list(path: String)(implicit sparkSession: SparkSession): RDD[Person] = ???

  def findByName(path: String, name: String)(implicit sparkSession: SparkSession): RDD[Person] = ???

  def findOlderThan(path: String, age: Int)(implicit sparkSession: SparkSession): RDD[Person] = ???

  def sortByName(path: String)(implicit sparkSession: SparkSession): RDD[Person] = ???

  def sortByAge(path: String)(implicit sparkSession: SparkSession): RDD[Person] = ???

  def oldest(path: String)(implicit sparkSession: SparkSession): Person = ???

  def groupByFirstLetterOfTheName(path: String)(implicit sparkSession: SparkSession): Map[String, Seq[Person]] = ???

  def fromString(input: String): Person = {
    val parts = input.split(" ")
    Person(parts.head, parts.last.toInt)
  }
}

case class Person(name: String, age: Int)
