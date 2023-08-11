# Kafka commands

## Kafka in AWS env
sudo su

cd /home/ec2-user/kafka_2.12-2.8.1/bin

### command to list all topics
./kafka-topics.sh --list --bootstrap-server "<bootstrap-server-name>" --command-config client.properties

### command to create a topic
>./kafka-topics.sh --create --bootstrap-server "<bootstrap-server-name>" --command-config client.properties --replication-factor 3 --partitions 3 --topic <topic-name>

### command to produce a message on the topic
>./kafka-console-producer.sh  --bootstrap-server "<bootstrap-server-name>" --producer.config client.properties --topic <topic-name>

### command to consume a message from the topic
>./kafka-console-consumer.sh --topic <topic-name> --from-beginning --bootstrap-server "<bootstrap-server-name>" --consumer.config client.properties

### command to delete a topic
./kafka-topics.sh --delete --bootstrap-server "<bootstrap-server-name>" --command-config "client.properties" --topic <topic-name>

## Kafka In local
cd C:\kafka

### to run the zookeeper server
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

### to run Kafka-server
.\bin\windows\kafka-server-start.bat .\config\server.properties

### to create a topic
.\bin\windows\kafka-topics.bat --create --topic topic-name --bootstrap-server localhost:9092

### to put a message on a topic
.\bin\windows\kafka-console-producer.bat --topic topic-name --bootstrap-server localhost:9092

### to consume from a topic
.\bin\windows\kafka-console-consumer.bat --topic topic-name --from-beginning --bootstrap-server localhost:9092
