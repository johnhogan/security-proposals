#!/bin/bash

name=${1?Specify a module name}

mkdir $name &&
cat > $name/pom.xml <<EOF
<?xml version="1.0" encoding="UTF-8"?>
<!--
  Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
-->
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <parent>
    <groupId>net.java.jsr375</groupId>
    <artifactId>jsr375-proposals</artifactId>
    <version>1.0-SNAPSHOT</version>
  </parent>

  <artifactId>$name</artifactId>

</project>
EOF

mkdir -p $name/src/main/java/javax/security/auth &&
mkdir -p $name/src/main/java/org/acme &&
perl -i -pe 's,^( *)(</modules>),$1  <module>$ENV{name}</module>\n$1$2,' pom.xml &&
echo "# $name" > $name/README.adoc &&
git add $name &&
git commit -m "Add $name" $name pom.xml
