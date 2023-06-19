package data.generator;

import java.util.Properties;
//import util.properties packages
import java.util.Properties;
//import simple producer packages
import org.apache.kafka.clients.producer.Producer;
//import KafkaProducer packages
import org.apache.kafka.clients.producer.KafkaProducer;
//import ProducerRecord packages
import org.apache.kafka.clients.producer.ProducerRecord;
import static data.generator.DataGenerator.*;

public class IPDRDataProducer {
    public static void main(String args[]){

        // Check arguments length value
        if(args.length != 4){
            System.out.println("Usage: java -cp IPDRDataProducer.jar data.generator.IPDRDataProducer " +
                    "<bootstrap_server_name:port> <kafka_topic> <number_of_messages_to_generate> <input_rate_ms>");
            return;
        }

        //Assign topicName to string variable
        String bootstrapServer = args[0].toString();
        String topicName = args[1].toString();
        Integer numberOfMessagesToGenerate = Integer.valueOf(args[2]);
        Integer inputRate = Integer.valueOf(args[3]);

        // create instance for properties to access producer configs
        Properties props = new Properties();

        //Assign localhost id
        props.put("bootstrap.servers", bootstrapServer);

        //Set acknowledgements for producer requests.
        props.put("acks", "all");

        //If the request fails, the producer can automatically retry,
        props.put("retries", 0);

        //Specify buffer size in config
        props.put("batch.size", 16384);

        //Reduce the no of requests less than 0
        props.put("linger.ms", 1);

        //The buffer.memory controls the total amount of memory available to the producer for buffering.
        props.put("buffer.memory", 33554432);

        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer
                <String, String>(props);

        System.out.println("Starting Data generation ... ");
        try {
            for (int i=1; i<=numberOfMessagesToGenerate; i++) {
                //generateIPDRMessage
                generateIPDRMessage();
                //Build an IPDR final JSON string and send it to Kafka topic
                producer.send(new ProducerRecord<String, String>(topicName, Integer.toString(i), buildIPDRMessage()));
                //Sleep between getting the next message
                Thread.sleep(inputRate);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All IPDR messages sent successfully");
        producer.close();

    }
}
