#!/bin/zsh

mvn clean package

java -javaagent:target/agent-1.0-SNAPSHOT.jar -classpath target/agent-1.0-SNAPSHOT.jar manfred.agent.basic.Boot