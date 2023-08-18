# Simple Data Generator of IPDR messages

## Main logic:
1) Generate IPDR record fields. I'm using faker Java library.
2) Build IPDR JSON message based on the message format listed below.  
3) Send IPDR message into Kafka queue
4) Sleep before proceeding to the next message

## Usage on Unsecured Cluster: 
```
java -cp IPDRDataProducer.jar data.generator.IPDRDataProducer <bootstrap_server_name:port> <kafka_topic> <number_of_messages_to_generate> <input_rate_milliseconds> <macAddress_start_offset>
```
Note: This was tested on Kafka version 2.5.0.7.1.7.1000-142 

Example: to generate 10 messages with sleep time of 1 sec in-between and a distinct mac addresses of MACADDR1 through MACADDR10:

```
java -cp IPDRProducer.jar data.generator.IPDRDataProducer secondary-5.secondary.root.hwx.site:9092 mytopic 10 1000 0
 
```
## Usage on Secured SASL_SSL (Kerberos) Cluster:

```
java -cp IPDRDataProducer.jar data.generator.IPDRDataProducer <bootstrap_server_name:9093> <kafka_topic> <number_of_messages_to_generate> <input_rate_milliseconds>" +
<path_to_truststore_file/truststore_file.jks>
```
Note: This was tested on Kafka version 3.4.0.7.2.17.0-334 on CDP Public Cloud Data Hub cluster. The code was deployed on the broker node.

Example: to generate 10 messages with sleep time of 1 sec in-between and a distinct mac addresses of MACADDR1 through MACADDR10 on a secured Kafka cluster with the truststore.jks file:

```
java -cp IPDRProducer.jar data.generator.IPDRDataProducer secondary-5.secondary.root.hwx.site:9093 mytopic 10 1000 0 /home/pnovokshonov/truststore.jks
```
## Sample IPDR Message Format:

```
{"batchId":"103.65.107.58",
"cmIp":{"v4":"198.75.91.98"},
"cmMacAddr":"MACADDR39",
"cmtsHostName":"schaefer.info",
"cmtsIp":{"v4":"103.65.107.58","v6":"cb20:8db0:c3bf:247b:d593:a9f2:1c2f:50af"},
"dsChSet":[31,70,79,26],
"dsIdentifier":5957,
"dsOctets":635159,
"dsPackets":6099,
"dsScn":"default",
"dsTimeActive":35962,
"fromTime":"2023-08-01 19:15:00",
"mdIfIndex":2869,
"mdIfName":"Cable2/1/6",
"qosVersion":3,
"region":"Charlotte",
"timeZone":"EST",
"toTime":"2023-08-01 19:30:00",
"usChSet":[0,6,9,9,8,0,3],
"usIdentifier":89100,
"usOctets":584150,
"usPackets":23106,
"usScn":"default",
"usTimeActive":21239,
"v":2}
```