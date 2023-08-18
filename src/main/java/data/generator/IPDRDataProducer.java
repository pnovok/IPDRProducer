package data.generator;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.config.SslConfigs;

import java.util.Properties;

import static data.generator.DataGenerator.buildIPDRMessage;
import static data.generator.DataGenerator.generateIPDRMessage;

//import util.properties packages
//import simple producer packages
//import KafkaProducer packages
//import ProducerRecord packages

public class IPDRDataProducer {
    public static void main(String args[]){

        // Check arguments length value
        if(args.length < 5 || args.length > 6) {
            System.out.println("Usage on Secured SASL_SSL cluster: java -cp IPDRDataProducer.jar data.generator.IPDRDataProducer " +
                    "<bootstrap_server_name:9093> <kafka_topic> <number_of_messages_to_generate> <input_rate_ms>" +
                    "<macAddress_start_offset> <path_to_truststore_file/truststore_file.jks>\n");

            System.out.println("Usage on Unsecured cluster: java -cp IPDRDataProducer.jar data.generator.IPDRDataProducer " +
                    "<bootstrap_server_name:9092> <kafka_topic> <number_of_messages_to_generate> <input_rate_ms> <macAddress_start_offset>");

            return;
        }

        //Assign topicName to string variable
        String bootstrapServer = args[0].toString();
        String topicName = args[1].toString();
        Integer numberOfMessagesToGenerate = Integer.valueOf(args[2]);
        Integer inputRate = Integer.valueOf(args[3]);
        Integer macAddrStartOffset = Integer.valueOf(args[4]);


        // create instance for properties to access producer configs
        Properties props = new Properties();

        if(args.length > 5 ) {
            String truststorePathName = args[5];
           //configure the following three settings for SSL Encryption
           props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
           props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, truststorePathName);
//            //props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG,  "test1234");

            //configure the following three settings for Kerberos Authentication
            props.put("sasl.kerberos.service.name", "kafka");
            props.put("sasl.jaas.config", "com.sun.security.auth.module.Krb5LoginModule required useTicketCache=true;");

        }

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
                generateIPDRMessage(i+macAddrStartOffset);
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
