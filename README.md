# AppDynamics Kafka Example 

* download AppDynamics java agent

  move spring-boot-kafka-consumer/appdynamics/custom-interceptors.xml into <agent_folder>/<version>/conf/

* download machine agent and build the kafka extension

	https://github.com/Appdynamics/kafka-monitoring-extension

* build and run the code in both folders

	mvn clean package

* Start kafka
	go to kafka folder and run docker-compose up -d

* Start consumer and producers

* Start load script
	
	spring-boot-kafka-producer/load_kafka.sh
	



