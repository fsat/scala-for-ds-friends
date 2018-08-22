# Week 3

## Objective

Now that we have got our project setup done, let's play with [Spark](https://spark.apache.org/)!

We'll get ourselves familiar with [RDD](https://spark.apache.org/docs/latest/rdd-programming-guide.html#resilient-distributed-datasets-rdds). This API forms the core of the Spark functionality. Although day-to-day you most likely won't be using this, it's good to gain understanding of RDD as it forms the basis of the [DataSet and DataFrame](https://spark.apache.org/docs/latest/sql-programming-guide.html#creating-dataframes).

## Enabling Spark in our build

First, let's observe our `build.sbt` - you should find the following entries.

```
val SparkVersion = "2.3.1"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core"          % SparkVersion % "provided",
  "org.apache.spark" %% "spark-sql"           % SparkVersion % "provided",
  "org.apache.spark" %% "spark-hive"          % SparkVersion % "provided",
  "org.scalatest"    %% "scalatest"           % "3.0.5" % "test",
  "com.holdenkarau"  %% "spark-testing-base"  % s"${SparkVersion}_0.10.0" % "test"
)
```

* The `SparkVersion` is the constant which indicates which Spark version we will be using (we're using `2.3.1`).
* The `"provided"` keyword tells SBT that the Spark library _should not_ be packaged with our artifact as it will be available in the [Classpath ](https://docs.oracle.com/javase/tutorial/essential/environment/paths.html) when our artifact is run.
* We also enabled `spark-testing-base` library which provides many convenience libraries when writing automated test with Spark.

## Using our SBT console

The exercise in this week will require you to switch back and forth between IntelliJ and the command line.

We will modify our code in IntelliJ and run it in the command line.
 
Let's jump to our SBT console.

```bash
sbt
```

Let's run all the tests.

```sbtshell
test
```

You will see some failure - this is expected, and we'll get to this later!

## Some notes on RDD

The mental model similar to lists and sequences can be applied to `RDD`.

_You can think of `RDD` as a distributed list where it's possible to distribute operations to the list to the Spark workers so they are handled in parallel._ 

In Scala, whenever a list is declared, the type of the list must be specified, i.e. `List[String]` or `List[Cat]`. This also applies to `RDD`.

## Spark RDD: reading files

At its most basic, you will use RDD to read data stored in files.

Open the `SimpleRDDSpec.scala` in your IDE, and run the following in the SBT console.

```sbtshell
testOnly *SimpleRDDSpec
```

This will run `SimpleRDDSpec` and all the tests will pass.

In the `SimpleRDDSpec`, there are following RDD operations which we should go through:

* `spark.sparkContext.textFile`: reads simple text files given a directory path. Returns `RDD[String]`
* The `map()` method of the RDD which performs the transformation of the RDD content. These transforms are parallelized across the Spark workers.
* The `collect()` method of the RDD which collects _all_ the elements in the `RDD`. This is ok for the purpose of our tests since we know our `RDD`s are small and we'd like to illustrate the actual element of the `RDD`.

## Playing with RDDs

Open `Person.scala` and you will see methods with `???`. The `???` are placeholders in Scala which indicates the method has yet to be implemented.

Your exercise this week would be to implement these methods.

If you implemented these methods correctly, you should see `PersonSpec` passing.

```sbtshell
testOnly *PersonSpec
```

If you'd like to run a particular scenario, for example a scenario called `groupByFirstLetterOfTheName` in `PersonSpec.scala`:

```sbtshell
testOnly *PersonSpec -- -z "groupByFirstLetterOfTheName"
```

Implement all these methods until `PersonSpec` is green. Each methods you need to implement is provided with some hints for you to get started.

## Week 3 Result

Once done, commit all your change, and push it to your git repo.


If you'd like, you can compare what you've done with the solution.

```
git checkout upstream/week-03-result
``` 

## Starting the Week 4

Create your own Week 4 branch:

```bash
git push origin $(git rev-parse upstream/week-04):refs/heads/week-04
git checkout -b week-04 origin/week-04
```

Read `week-04.md` to get started.




 