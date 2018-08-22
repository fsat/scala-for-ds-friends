package mine

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

object SparkHelper {
  def readText(path: String)(implicit spark: SparkSession): RDD[String] =
    spark.sparkContext.textFile(path)

}
