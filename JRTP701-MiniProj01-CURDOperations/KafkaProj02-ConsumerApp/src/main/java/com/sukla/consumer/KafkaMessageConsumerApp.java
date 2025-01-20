package com.sukla.consumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;



public class KafkaMessageConsumerApp
{

	public static void main(String[] args)
	{

		// create apache kafka properties
		Properties props = new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "consumer_grp1");

		// create KafkaConsumer object
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

		// connect to topic
		consumer.subscribe(Arrays.asList("sukla-tpc2"));

		// read the messages through pooling
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));

			for (ConsumerRecord record : records) {
				System.out.println(record.value());
			}
		}

	}

}
