# Spanning-Tree-Lambda

This Repository is a copy of the [Spanning-Tree-Simulation](https://github.com/Drinkler/Spanning-Tree-Simulation) Repository. This Repository is modified for AWS Lambda for use on 
my personal website [floriandrinkler.com](https://floriandrinkler.com).













# Spanning-Tree-Simulation :deciduous_tree:

[![Build Status](https://travis-ci.com/Drinkler/Spanning-Tree-Simulation.svg?branch=master)](https://travis-ci.com/Drinkler/Spanning-Tree-Simulation)
[![GitHub](https://img.shields.io/github/license/drinkler/Spanning-Tree-Simulation?label=license)](https://github.com/Drinkler/Spanning-Tree-Simulation/blob/master/LICENSE)
[![GitHub tag (latest by date)](https://img.shields.io/github/v/tag/drinkler/Spanning-Tree-Simulation)](https://github.com/Drinkler/Spanning-Tree-Simulation/tree/v2.0.0)
[![GitHub release (latest by date)](https://img.shields.io/github/v/release/drinkler/Spanning-Tree-Simulation)](https://github.com/Drinkler/Spanning-Tree-Simulation/releases/latest)
[![GitHub issues](https://img.shields.io/github/issues/drinkler/Spanning-Tree-Simulation)](https://github.com/Drinkler/Spanning-Tree-Simulation/issues)
[![GitHub Release Date](https://img.shields.io/github/release-date/drinkler/Spanning-Tree-Simulation)](https://github.com/Drinkler/Spanning-Tree-Simulation/releases)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/5ce4c70928de42dc88b7df2069ba35a9)](https://www.codacy.com/manual/Drinkler/Spanning-Tree-Simulation?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Drinkler/Spanning-Tree-Simulation&amp;utm_campaign=Badge_Grade)

This Project was created by Florian Drinkler. It is for the "Labor Netztechnik" (english: "Laboratory network technology") Lecture, 4th semester.

Documentation and Information about the Project can be found [here](doc/) (German).

## IMPORTANT :exclamation:

The Project is compiled with the [Java SE 9](https://www.oracle.com/java/technologies/javase/javase9-archive-downloads.html) Version.
<br/>Which means the jar File needs to be executed with a Java Version greater or equal than SE 9.
<br/>It was tested with the [Java SE 12](https://www.oracle.com/java/technologies/javase/jdk12-archive-downloads.html) Version.
<br/>
<br/>[JDK Installation Instructions for Windows](https://docs.oracle.com/en/java/javase/12/install/installation-jdk-microsoft-windows-platforms.html#GUID-DAF345BA-B3E7-4CF2-B87A-B6662D691840)

## Command line options

```
Usage: <main class> [options]
  Options:
    -h, --help
      Shows all available options.
    -i, --input
      Name of the input file.
      Default: graph.txt
    --maxident
      Max allowed  length of a node name.
      Default: 100
    --maxitems
      Max allowed lines of the input file.
      Default: 100
    --maxkosten
      Max allowed range for the weight of a link.
      Default: 100
    --maxnodeid
      Max allowed range for the id of a node.
      Default: 100
    -o, --output
      Name of the output file.
      Default: output.txt
    --pbu
      The amount of broadcasts for each node. (Lower numbers could output
      wrong solutions!)
      Default: 10
```

## Execute

The jar File can be found in the [release](release/) Folder or under [releases](https://github.com/Drinkler/Spanning-Tree-Simulation/releases). The latest release can be found [here](https://github.com/Drinkler/Spanning-Tree-Simulation/releases/latest).
<br/>
The files `graph.txt` and `graph2.txt` are examples for the input file.

```
java -jar spanning-tree-simulation-1.1.3.jar [options]
```

## Conventions for the input file

-   It must be a .txt file
-   Lines with Comments are getting ignored
-   You must start with the Nodes
-   The Graph isn't checked for correctness

Example:

```
Graph <mygraphname> {
  <Nodename> = <ID of Node>;
  // Line gets ignored (Comment)
  <Nodename> - <Nodename> : <Weight of Link>;
}
```

#### Usage of Dependencies

Copyright © 2017 [JCommander](https://github.com/cbeust/jcommander) Dependency. Released under the [Apache License 2.0](https://github.com/cbeust/jcommander/blob/master/license.txt)
