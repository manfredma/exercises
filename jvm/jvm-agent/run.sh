#!/bin/zsh

mvn clean package

java -javaagent:target/jvm-agent-1.0-SNAPSHOT.jar -classpath target/jvm-agent-1.0-SNAPSHOT.jar manfred.agent.basic.Boot