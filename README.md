# Simple Data Generator of IPDR messages

## Main logic:
1) Generate IPDR record fields. I'm using faker Java library.
2) Build IPDR JSON message based on the message format listed below.  
3) Send IPDR message into Kafka queue
4) Sleep before proceeding to the next message

## Usage: 

java -cp IPDRDataProducer.jar data.generator.IPDRDataProducer <bootstrap_server_name:port> <kafka_topic> <number_of_messages_to_generate> <input_rate_ms>

Example: to generate 10 messages with sleep time of 1 sec in-between:
java -cp IPDRProducer.jar data.generator.IPDRDataProducer secondary-5.secondary.root.hwx.site:9092 mytopic 10 1000

## Sample IPDR Message Format:

```
{
   "batchId":"xxx.xxx.xx.xx",						<IPV4 Address>
   "cmIp":{
      "v4":"xx.xx.xx.xx"							<IPV4 Address>
   },
   "cmMacAddr":"123456ABCDEF",
   "cmtsHostName":"TESTRPCC01.ga.at.cox.net",
   "cmtsIp":{
      "v4":"xxx.xxx.xx.xx", 						<Same as batchId>
      "v6":"xxxx:xxx:xexx:x:xxx:xxx:xx:xx"			<IPV6 Address>
   },
   "dsChSet":[
      17,
      18,
      19,
      20,
      21,
      22,
      23,
      24,
      25,
      26,
      27,
      28,
      29,
      30,
      31,
      32,
      33,
      34,
      35,
      36,
      37,
      38,
      39,
      40,
      41,
      42,
      43,
      44,
      45,
      46,
      47,
      48,
      159
   ],
   "dsIdentifier":8000,
   "dsOctets":757040,
   "dsPackets":1570,
   "dsScn":"vidiptvdn",
   "dsTimeActive":58694,
   "fromTime":"2023-05-23T12:15:00Z",
   "mdIfIndex":1149,
   "mdIfName":"Cable3/0/4",
   "qosVersion":2,
   "region":"Atlanta",
   "timeZone":"UTC",
   "toTime":"2023-05-23T12:30:00Z",
   "usChSet":[
      1,
      2,
      3,
      4,
      5,
      6,
      7,
      8
   ],
   "usIdentifier":12907,
   "usOctets":157877,
   "usPackets":1374,
   "usScn":"vidiptvup",
   "usTimeActive":58694,
   "v":1
}
```