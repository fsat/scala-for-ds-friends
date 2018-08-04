# Week 1

## Objective

We'll start by getting familiar on Scala project:

* Install all the required dependencies.
* Scala project setup using [SBT](https://www.scala-sbt.org/).
* Creating a Hello World program.
* Running the Hello World program.

## Installation

### Homebrew

Follow the [Homebrew](https://brew.sh/) installation if you haven't already got it installed.

### JDK 8

We picked JDK 8 as it's compatible with the Scala + Spark version we use at work. 

```bash
brew tap caskroom/cask
brew tap caskroom/versions
brew cask install java8
```

### SBT

Install SBT after you have JDK 8 installed:

```bash
brew install sbt
```

### IntelliJ IDEA Community Edition

Download and install the Community Edition of Intellij IDEA for Mac:

https://www.jetbrains.com/idea/download/#section=mac

If you are not on Mac, choose the appropriate OS.

### Enable IntelliJ Scala Plugin

Once IntelliJ is installed, enable the Scala plugin:

http://allaboutscala.com/tutorials/chapter-1-getting-familiar-intellij-ide/scala-environment-setup-install-scala-plugin-intellij/


## Scala project setup using SBT

Next we will create a minimum project setup using SBT.

Create the following directories:

```bash
mkdir -p src/main/scala
mkdir -p src/main/resources
```

SBT follows the following directory conventions:

* `src/main/scala` contains all the `*.scala` source files.
* `src/main/resources` contains all non-code source files such as configuration files for the application, logging, etc.

Create the `build.sbt`.

```bash
touch build.sbt
```

The file `build.sbt` contains the build definition used by SBT.

Populate the `build.sbt` with the following definition.

```
name := "my-project"
version := "0.0.0"
scalaVersion := "2.11.11"
```

The `name` and `version` refers to the name and the version of our project respectively.  

Define the sbt version we will be using.

```bash
mkdir project
touch project/build.properties
```

Populate the `project/build.properties` with the SBT version we will be using.

```
sbt.version = 1.1.6
```


## Creating a Hello World program

First, we will create a package called `mine`.

```bash
mkdir -p src/main/scala/mine
```

The directory under `src/main/scala` contains the files which resides under the root package, while `src/main/scala/mine` contains the files residing under the `mine` package.

_It's considered a good practice to avoid creating files under the root package to prevent potential clashes with some other libraries_

We will now create our Hello World program.

```bash
touch src/main/scala/mine/Main.scala
```

Populate `src/main/scala/mine/Main.scala` with the following.

```scala
package mine

object Main {
  def main(args: Array[String]): Unit = {
    println("Hello World")
  }
}
```

* We now have a [singleton object](https://docs.scala-lang.org/tour/singleton-objects.html) called `Main`.
* The `main` method represents the main entry point of the program.
* The `args` variable is an array of String - hence `Array[String]`.
* The `main` method returns `Unit` which is a representation of `void` in Scala. In other words, the `main` method doesn't return any result.
* The `println` prints the given string with a line break (there's also `print` method that prints the string _without_ the line break). 


## Running the Hello World program

Run the program.

```bash
sbt run
```

If this is the first time ever you are using SBT, it will take _*really*_ long for the program to start. Don't worry this is to be expected as SBT will need to download its dependencies and to bootstrap Scala. The setup is only done once for the first time you are running a particular version of SBT or Scala.

So yes, subsequent run will be faster.

Run the program again.

```bash
sbt run
```


## Open your program with the editor

Now start the IntelliJ IDEA, and then open the root directory of your cloned project.

## Notes on SBT tasks

SBT provides the following target out of the box.

* `compile`: compiles the program that you've written.
* `run`: runs the compiled program if there's a singleton object present with the `main` method.
* `clean`: clears all the compiled artifacts.

SBT keeps track of task dependency, i.e. if you run `sbt run` it will execute `compile` to pick up changes if available so the run will display the updated code changes.

If you're so keen, you can change the `"Hello World"` to whatever `String` you fancy, and subsequently executing `sbt run` will show you the updated result.

## Week 1 Result

That's it! Commit all your change, and push it to your git repo.


If you'd like, you can compare what you've done with the solution.

```
git checkout upstream/week-01-result
``` 

## Starting the Week 2

Create your own Week 2 branch:

```bash
git push origin $(git rev-parse upstream/week-01):refs/heads/week-02
git checkout -b week-02 origin/week-02
```

Read `week-02.md` to get started.
