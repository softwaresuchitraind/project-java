package com.sukla.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;



public class KaffMessageProducerApp
{

	public static void main(String[] args)
	{
		
		//create apache kafka properties
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		
		//create KafkaProducer object
		KafkaProducer<String, String> producer = new KafkaProducer<String,String>(props);
		
		//create ProducerRecord<-,-> representing the message
		
		ProducerRecord<String, String> record = new ProducerRecord<String, String>("sukla-tpc2", "welcome to Apache kafka");
		
		//send the message
		producer.send(record);
		//flush the message
		producer.flush();
		//close the connection
		producer.close();
		
		System.out.println("Message is sent");

	}

}
