# Scala for DS Friends

This project is created for my DS friends at work to get them up to speed in Scala, going down the path of data engineering using Spark.

You are more than welcome to use this repo too if this interests you.

## Assumptions

My DS friends at work has been programming for some time, and are comfortable with Python. As such, this repo will focus to get them up to speed with Scala and its build tools so they can with Spark on Scala. Prior programming knowledge and understanding is assumed, but no or minimal Scala exposure to Scala is necessary.

All of us at work has Github account, so I will be using that to host this repo.

At work we are using MacOS, as such the installation steps are written for this in mind. Apart from the installation, for most part the repo can be followed on other OS.

We're also quite busy at work, so this is written in a way that you'd spend about 2 hours doing the exercise in this repo.

## Getting Started

Fork this repo so you have your own copy.

Once forked, clone this repo to your machine. Add the original repo as `upstream`:

```bash
git remote add upstream git@github.com:fsat/scala-for-ds-friends.git
git fetch upstream 
```

This way you would have your own copy that you can modify as you please.

To get the updates from the `upstream` repo, simply do:

```bash
git fetch upstream
```

## Structure of the repo

The repo will have branches corresponding to the what is to be accomplished each week.

```bash
git branch -a | grep week
```

So the Week 1, we will have a branch called `week-01` containing the exercise, and `week-01-result` containing the solution. 

Similarly for Week 2, we will have `week-02` and `week-02-result` and so on.


## Starting the Week 1

Create your own Week 1 branch:

```bash
git push origin $(git rev-parse upstream/week-01):refs/heads/week-01
git checkout -b week-01 origin/week-01
```

Read `week-01.md` to get started.

Good luck!