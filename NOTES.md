# Kafka Notes

## Reminders...
```
#With zookeeper
zookeeper-server-start.sh ~/kafka_2.13-3.1.0/config/zookeeper.properties
kafka-server-start.sh ~/kafka_2.13-3.1.0/config/server.properties

#creating Storage
kafka-storage.sh random-uuid
kafka-storage.sh format -t om7dIhCPSp6DDdsfMI7hvQ -c ~/kafka_2.13-3.1.0/config/kraft/server.properties

#Without Zookeeper
kafka-server-start.sh ~/kafka_2.13-3.1.0/config/kraft/server.properties

#Creating topics
kafka-topics.sh --bootstrap-server localhost:9092 --list
kafka-topics.sh --bootstrap-server localhost:9092 --create --topic first_topic
kafka-topics.sh --bootstrap-server localhost:9092 --list
kafka-topics.sh --bootstrap-server localhost:9092 --create --topic second_topic --partition 3
kafka-topics.sh --bootstrap-server localhost:9092 --create --topic second_topic --partitions 3
kafka-topics.sh --bootstrap-server localhost:9092 --create --topic third_topic --partitions 3 --replication-factor 1
kafka-topics.sh --bootstrap-server localhost:9092 --list
kafka-topics.sh --bootstrap-server localhost:9092 --describe --topic first_topic
kafka-topics.sh --bootstrap-server localhost:9092 --describe --topic second_topic
kafka-topics.sh --bootstrap-server localhost:9092 --describe --topic third_topic
kafka-topics.sh --bootstrap-server localhost:9092 --delete --topic third_topic
kafka-topics.sh --bootstrap-server localhost:9092 --delete --topic second_topic
kafka-topics.sh --bootstrap-server localhost:9092 --delete --topic first_topic
kafka-topics.sh --bootstrap-server localhost:9092 --create --topic first_topic --partitions 3

#Send data - Kafka Producer
kafka-console-producer.sh --bootstrap-server localhost:9092 --topic first_topic
kafka-console-producer.sh --bootstrap-server localhost:9092 --topic first_topic --producer-property acks=all
kafka-console-producer.sh --bootstrap-server localhost:9092 --topic first_topic

# Kafka Consumer
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic first_topic
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic first_topic --from-beginning
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic first_topic --from-beginning --formatter kafka.tools.DefaultMessageFormatter --property print.timestamp=true --property print.key=true --property print.value=true

#Consumer Groups
kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group my-first-consumer-group
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic first_topic --group my-first-consumer-group
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic first_topic --group my-second-consumer-group

kafka-consumer-groups.sh --bootstrap-server localhost:9092 --list

#Resetting offsets
kafka-consumer-groups.sh --bootstrap-server localhost:9092 --group my-first-consumer-group --reset-offsets --to-earliest --execute --all-topics
kafka-consumer-groups.sh --bootstrap-server localhost:9092 --group my-first-consumer-group --reset-offsets --shift-by -2 --execute --all-topics
```