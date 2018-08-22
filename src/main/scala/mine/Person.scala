package mine

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import scala.collection.Map

object Person {
  /**
   * Implement this method as such:
   * 1. Use the [[SparkHelper.readText()]] to obtain the [[RDD]] of [[String]].
   * 2. The [[RDD.map()]] method takes a function which transforms the content of RDD.
   *
   * Refer to the `LearnRDDSpec`'s scenario called `RDD transformation using map method` on how to pass a function
   * into [[RDD.map()]].
   */
  def list(path: String)(implicit sparkSession: SparkSession): RDD[Person] = ???

  /**
   * Implement this method as such:
   * 1. Use the [[list()]] method to obtain the [[RDD]] of [[Person]].
   * 2. Use the [[RDD.filter()]] method to filter for the [[Person]] matching the name.
   */
  def findByName(path: String, name: String)(implicit sparkSession: SparkSession): RDD[Person] = ???

  /**
   * Implement this method as such:
   * 1. Use the [[list()]] method to obtain the [[RDD]] of [[Person]].
   * 2. Use the [[RDD.filter()]] method to filter for the [[Person]] older than specified `age`.
   */
  def findOlderThan(path: String, age: Int)(implicit sparkSession: SparkSession): RDD[Person] = ???

  /**
   * Implement this method as such:
   * 1. Use the [[list()]] method to obtain the [[RDD]] of [[Person]].
   * 2. Use the [[RDD.sortBy()]] method. [[RDD.sortBy()]] takes a function which return a value which will be used to
   *    perform the sort.
   */
  def sortByName(path: String)(implicit sparkSession: SparkSession): RDD[Person] = ???

  /**
   * This should be similar to [[sortByName()]], except using [[Person.age]] instead.
   */
  def sortByAge(path: String)(implicit sparkSession: SparkSession): RDD[Person] = ???

  /**
   * Implement this method as such:
   * 1. Use the [[sortByAge()]] method to obtain the [[RDD]] of [[Person]].
   * 2. Use [[RDD.first()]].
   *
   * Alternative:
   * - You can use [[RDD.take()]] to take a number of elements you wish. Scala's list has `.head` method returns the
   *   head of the list.
   */
  def oldest(path: String)(implicit sparkSession: SparkSession): Person = ???

  /**
   * Implement this method as such:
   * 1. Use the [[list()]] method to obtain the [[RDD]] of [[Person]].
   * 2. Use [[RDD.groupBy()]] which takes a function to group the RDD.
   * 3. Use `.collectAsMap()` method to convert the RDD to Scala [[Map]] having first letter of the name as the key,
   *    and the list of [[Person]] (represented by [[Iterable]]) as the value.
   */
  def groupByFirstLetterOfTheName(path: String)(implicit sparkSession: SparkSession): Map[String, Iterable[Person]] = ???

  def fromString(input: String): Person = {
    val parts = input.split(" ")
    Person(parts.head, parts.last.toInt)
  }
}

case class Person(name: String, age: Int)
