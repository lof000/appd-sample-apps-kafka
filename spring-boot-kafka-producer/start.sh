
#java -Dappdynamics.agent.tierName="producer" -Dappdynamics.agent.nodeName="producer_node" -javaagent:$AGENT_PATH/javaagent.jar  -jar target/spring-kafka-producer-0.0.1-SNAPSHOT.jar


java  -jar target/spring-kafka-producer-0.0.1-SNAPSHOT.jar
