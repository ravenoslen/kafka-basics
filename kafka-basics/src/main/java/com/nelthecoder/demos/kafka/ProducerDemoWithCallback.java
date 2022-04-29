package com.nelthecoder.demos.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemoWithCallback {

    private static Logger log = LoggerFactory.getLogger(ProducerDemoWithCallback.class.getName());

    public static void main(String[] args) {
        log.info("I am a Kafka Producer");

        //create Producer Properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //create the Producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        ProducerRecord<String, String> producerRecord;
        for (int i = 0; i < 10; i++) {
            //create a producer record
            producerRecord = new ProducerRecord<>("demo_java", "hello world " + i);

            //send the data - asynchronous
            producer.send(producerRecord, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    //executes every time a record is successfully sent or exception is thrown
                    if (e == null) {
                        //the record was successfully sent
                        log.info("Received new metada/ \n" +
                                "Topic: " + metadata.topic() + "\n" +
                                "Partition: " + metadata.partition() + "\n" +
                                "Offset: " + metadata.offset() + "\n" +
                                "Timestamp: " + metadata.timestamp());
                    } else {
                        log.error("Error while producing", e);
                    }
                }
            });

            //force kafka to use different partitions and no sticky partitions
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //flush data - synchronous
        producer.flush();

        //flush and close producer
        producer.close();

    }
}
