# Week 2

## Objective

Each projects would need to have tests, so let's learn how to write tests. In doing so, we'll learn a bit more about library dependencies and other helpful SBT functionalities.

* Learn how to write test using [ScalaTest](http://www.scalatest.org/) library.
* SBT repl
* Scala repl

To make things interesting, we'll also write a simple Spark test using [Spark Testing Base](https://github.com/holdenk/spark-testing-base). 

## Adding ScalaTest dependency

First we need to register ScalaTest as a dependency.

Add the following entry in the `build.sbt`.

```
libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.5" % "test"
)
```

The `org.scalatest`, `scalatest`, and `3.0.5` refers to the library's `groupId`, `artifactId`, and version respectively.

The character `%%` between tells SBT the ScalaTest library is written using Scala, and hence it should fetch the version that matches our project's Scala version.

The word `test` at the end refers to the library being made available as test dependency. This means ScalaTest will only be made available during the compilation and execution of tests. ScalaTest won't be made available during the compilation of the actual code, and it won't be packaged when we choose to package our application.


## Writing our first test

Let's add a simple function called `plusOne` to our `Main` object.

```scala
object Main {
  def plusOne(input: Int): Int = input + 1
  
  ...
```

We will write a test for `plusOne`.

First create the directory where the test will sit.

```bash
mkdir -p src/test/scala
```

SBT by convention expects the tests file to reside within `src/test/scala`.

Let's create our test file.

```bash
mkdir -p src/test/scala/mine
touch src/test/scala/mine/MainSpec.scala
```

Open the `MainSpec.scala` in IntelliJ, and paste the following content into the file.

```scala
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
```

* `packge mine` declares that `MainSpec` resides in the `mine` package.
* The `import` statement declares that we're importing [FunSpec]() and `Matchers`.
  * `FunSpec` allows us to write the `describe` and `it` statements.
  * `Matchers` provides assertions via `shouldBe` statement.

## Running our test from command line

To run our test from command line, execute the following command.

```bash
sbt test
```

## Running our test from SBT repl

Run the following command to launch the SBT repl.

```bash
sbt
```

Run our test by executing the following in the repl.

```sbtshell
test
```

Running `sbt test` from the command line is equivalent of executing the `test` command in the repl.

To run a particular test:

```sbtshell
testOnly *MainSpec
```

To run a particular scenario in a particular test:

```sbtshell
testOnly *MainSpec -- -z "negative numbers"
```

The test above only executes the negative numbers scenario and skips everything else.

Try to change the `plusOne` method to minus 1, and re-run the `test` in SBT repl - you should see all tests are now marked as red.

Try the following command as well.

```sbtshell
~test
``` 

The SBT will wait for any changes, and it will automatically execute the test when there are changes.

To exit the repl, press `Ctrl+C`.

## Running Scala console from SBT repl

First start the SBT repl.

```bash
sbt
```

Then run the following in the SBT repl to start the Scala console.

```sbtshell
console
```

You are now in the Scala console.

Try to run the following command.

```
scala> 1 + 1
res0: Int = 2
```

The result is assigned to `res0` variable which you can reference.

```
scala> res0 + 1
res2: Int = 3
```

Let's invoke our program.

```
scala> import mine.Main
import mine.Main

scala> Main.plusOne(1)
res0: Int = 2
```

Type `:quit` to exit the Scala repl.


## Week 2 Result

That's it! Commit all your change, and push it to your git repo.


If you'd like, you can compare what you've done with the solution.

```
git checkout upstream/week-02-result
``` 

## Starting the Week 3

Create your own Week 3 branch:

```bash
git push origin $(git rev-parse upstream/week-03):refs/heads/week-03
git checkout -b week-03 origin/week-03
```

Read `week-03.md` to get started.
