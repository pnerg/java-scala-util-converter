# java-scala-util-converter
![Build & Test](https://github.com/pnerg/java-scala-util-converter/workflows/Build%20&%20Test/badge.svg) [![codecov.io](http://codecov.io/github/pnerg/java-scala-util-converter/coverage.svg?branch=master)](http://codecov.io/github/pnerg/java-scala-util-converter?branch=master) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.dmonix.functional/java-scala-utils-converter_2.11/badge.svg?style=plastic)](https://maven-badges.herokuapp.com/maven-central/org.dmonix.functional/java-scala-utils-converter_2.11) [![Scaladoc](http://javadoc-badge.appspot.com/org.dmonix.functional/java-scala-utils-converter_2.11.svg?label=scaladoc)](http://javadoc-badge.appspot.com/org.dmonix.functional/java-scala-utils-converter_2.11)  
  
# Introduction

This library provides the mechanisms to convert between types provided by [java-scala-util](https://github.com/pnerg/java-scala-util) to their Scala equivalence and vice-versa.  
One can either perform explicit conversions by using a specific converter method or do implicit conversions using the decorator pattern provided by Scalas implicit method declaration

This allows you to seamlessly traverse between Java and Scala using core functionality such as _Try/Success/Failure_, _Option/None/Some_,  _Either/Left/Right_ and non-blocking _Futures_ and having these types automatically translated to/from Java/Scala.

## Documentation
For further reading on usage refer to the [Wiki](https://github.com/pnerg/java-scala-util-converter/wiki)

## Requirements

Java 8 (or higher) is required as the [java-scala-utils](https://github.com/pnerg/java-scala-util) builds heavily on [Lambda](http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/Lambda-QuickStart/index.html).

# Download

SBT
```
"org.dmonix.functional" % "java-scala-utils-converter" % [version]
```

## LICENSE

Copyright 2015 Peter Nerg.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

<http://www.apache.org/licenses/LICENSE-2.0>

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.