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
   *
   * Scala tip:
   * The `implicit` statement is an instruction to the Scala compiler to automatically pass an instance of
   * [[SparkSession]] available at the same scope where the method is called.
   *
   * The Scala compiler will perform match based on the type which is [[SparkSession]] in this case.
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
   *
   * Note that [[RDD.sortBy()]] takes a second optional [[Boolean]] argument which controls whether the sort is
   * ascending or descending.
   */
  def sortByAge(path: String)(implicit sparkSession: SparkSession): RDD[Person] = ???

  /**
   * Implement this method as such:
   * 1. Use the [[sortByAge()]] method to obtain the [[RDD]] of [[Person]], sorted by the oldest first.
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
   *
   * Spark tip:
   * Be careful when using any `collect*` method from RDD as it would load all the contents of RDD into memory.
   * As RDD normally represents an large list with unknown length, doing so may cause your program to crash with
   * [[OutOfMemoryError]].
   *
   * Similar caution applies to [[RDD.groupBy()]] as it group elements within the RDD and produce in memory list.
   *
   * Scala tip:
   * [[String]] in Scala is treated like a list of characters. This means the `.head` method of String will return the
   * first element, which is the first letter.
   */
  def groupByFirstLetterOfTheName(path: String)(implicit sparkSession: SparkSession): Map[String, Iterable[Person]] = ???

  def fromString(input: String): Person = {
    val parts = input.split(" ")
    Person(parts.head, parts.last.toInt)
  }
}

case class Person(name: String, age: Int)
