package data.generator;

import com.github.javafaker.Faker;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DataGenerator {

    static IPDRRecord message = new IPDRRecord();


    //This will generate most of the fields for IPDR message
    public static void generateIPDRMessage(){
        Faker fakeData = new Faker();
        message.setBatchId(fakeData.internet().ipV4Address());
        message.setCmIp(fakeData.internet().ipV4Address());
        //message.setCmMacAddr(fakeData.internet().macAddress());
        //I'm getting 100 MAC addresses, this can be changed
        message.setCmMacAddr(fakeData.number().numberBetween(1,10));
        message.setCmtsHostName(fakeData.internet().domainName());
        message.setCmtsIp((fakeData.internet().ipV6Address()));
        message.setDsChSet_Cnt(fakeData.number().numberBetween(3,10));
        message.setDsIdentifier(fakeData.number().numberBetween(1000,9999));
        message.setDsOctets(fakeData.number().numberBetween(100000,999999));
        message.setDsPackets(fakeData.number().numberBetween(1000,9999));
        message.setDsScn(fakeData.options().option("data", "video","telephony","default","both"));
        message.setDsTimeActive(fakeData.number().numberBetween(10000,99999));
        message.setFromTime(fakeData.date().past(2, TimeUnit.HOURS));
        message.setMdIfIndex(fakeData.number().numberBetween(1000,9999));
        message.setMdIfName(fakeData.options().option("Cable3/0/4","Cable1/0/5","Cable2/1/6"));
        message.setQosVersion(fakeData.number().numberBetween(1,5));
        message.setRegion((fakeData.options().option("Atlanta","Charlotte","Nashville")));
        message.setTimeZone((fakeData.options().option("UTC","EST","PST","CST","MST")));
        message.setUsChSet_Cnt(fakeData.number().numberBetween(3,10));
        message.setUsIdentifier(fakeData.number().numberBetween(10000,99999));
        message.setUsOctets(fakeData.number().numberBetween(100000,999999));
        message.setUsPackets(fakeData.number().numberBetween(10000,99999));
        message.setUsScn(message.getDsScn());
        message.setUsTimeActive(fakeData.number().numberBetween(10000,99999));
        message.setV(fakeData.number().numberBetween(1,3));
    }

    //This method will form the IPDR JSON message
    public static String buildIPDRMessage(){


        //Get batchId
        //msgJson.put("batchId",message.getBatchId());
        String batchId = "{\"batchId\":"+"\""+message.getBatchId()+"\",";

        //Get cmIp
        String cmIp = "\"cmIp\":{\"v4\":"+"\""+message.getCmIp()+"\"},";

        //Get cmMacAddr
        String cmMacAddr="\"cmMacAddr\":"+"\""+ "MACADDR"+message.getCmMacAddr() +"\",";

        //Get cmtsHostName
        String cmtsHostName="\"cmtsHostName\":"+"\""+message.getCmtsHostName()+"\",";

        //Get cmtsIp
        String  cmtsIp="\"cmtsIp\":{\"v4\":\""+message.getBatchId()+"\",\"v6\":\""+message.getCmtsIp()+"\"},";

        //Get dynamic array of random values for dsChSet field, the count of values is defined by dsChSet_Cnt
        int dsChSetArray[];
        int dsChSet_Cnt = message.getDsChSet_Cnt();
        dsChSetArray = new int[dsChSet_Cnt];

        Random rand = new Random();
        int upperbound = 100; //upper Range for dsChSet value
        StringBuilder dsChSet= new StringBuilder();
        dsChSet.append("\"dsChSet\":[");
        for (int i=0; i< dsChSet_Cnt; i++) {
            dsChSetArray[i] = rand.nextInt(upperbound);
            if (i == (dsChSet_Cnt - 1)) {
                dsChSet.append(String.valueOf(dsChSetArray[i]));
            }
            else {
                dsChSet.append(String.valueOf(dsChSetArray[i])).append(",");
            }
        }
        dsChSet.append("],");

        //Get dsIdentifier
        String dsIdentifier = "\"dsIdentifier\":"+String.valueOf(message.getDsIdentifier())+",";

        //Get dsOctets
        String dsOctets = "\"dsOctets\":"+String.valueOf(message.getDsOctets())+",";

        //Get dsPackets
        String dsPackets = "\"dsPackets\":"+String.valueOf(message.getDsPackets())+",";

        //Get dsScn
        String dsScn = "\"dsScn\":\""+message.getDsScn()+"\",";

        //Get dsTimeActive
        String dsTimeActive = "\"dsTimeActive\":"+String.valueOf(message.getDsTimeActive())+",";

        //Get FromTime as random date and transform it
        Date fromTime=message.getFromTime();
        Timestamp ts=new Timestamp(fromTime.getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //Round the random date to the nearest Quater
        LocalDateTime lastQuarter =  ts.toLocalDateTime().truncatedTo(ChronoUnit.HOURS)
                .plusMinutes(15 * (ts.toLocalDateTime().getMinute() / 15));
        ts= Timestamp.valueOf(lastQuarter);
        String fromTimeStr = "\"fromTime\":\"" + formatter.format(ts)+"\",";

        //Get mdIfIndex
        String mdIfIndex = "\"mdIfIndex\":"+String.valueOf(message.getMdIfIndex())+",";

        //Get mdIfName
        String mdIfName = "\"mdIfName\":\""+message.getMdIfName()+"\",";

        //Get qosVersion
        String qosVersion = "\"qosVersion\":"+String.valueOf(message.getQosVersion())+",";

        //Get mdIfName
        String region = "\"region\":\""+message.getRegion()+"\",";

        //Get timezone
        String timeZone = "\"timeZone\":\""+message.getTimeZone()+"\",";

        //Get toTime by adding 15 min to fromTime
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(ts.getTime());
        cal.add(Calendar.MINUTE, 15);
        ts = new Timestamp(cal.getTime().getTime());
        //System.out.println("ToTime: "+formatter.format(ts));
        String toTimeStr = "\"toTime\":\"" + formatter.format(ts)+"\",";

        //Get dynamic array of random values for usChSet field, the count of values is defined by usChSet_Cnt
        int usChSetArray[];
        int usChSet_Cnt = message.getUsChSet_Cnt();
        usChSetArray = new int[usChSet_Cnt];

        int us_upperbound = 10; //upper Range for usChSet value
        StringBuilder usChSet= new StringBuilder();
        usChSet.append("\"usChSet\":[");
        for (int i=0; i< usChSet_Cnt; i++) {
            usChSetArray[i] = rand.nextInt(us_upperbound);
            if (i == (usChSet_Cnt - 1)) {
                usChSet.append(String.valueOf(usChSetArray[i]));
            }
            else {
                usChSet.append(String.valueOf(usChSetArray[i])).append(",");
            }
        }
        usChSet.append("],");

        //Get usIdentifier
        String usIdentifier = "\"usIdentifier\":"+String.valueOf(message.getUsIdentifier())+",";

        //Get usOctets
        String usOctets = "\"usOctets\":"+String.valueOf(message.getUsOctets())+",";

        //Get usPackets
        String usPackets = "\"usPackets\":"+String.valueOf(message.getUsPackets())+",";

        //Get usScn
        String usScn = "\"usScn\":\""+message.getUsScn()+"\",";

        //Get usTimeActive
        String usTimeActive = "\"usTimeActive\":"+String.valueOf(message.getUsTimeActive())+",";

        //Get v
        String v = "\"v\":"+String.valueOf(message.getV())+"}";

        //Build the final JSON string message
        String msgString = new StringBuilder().append(batchId).append(cmIp).append(cmMacAddr).append(cmtsHostName).append(cmtsIp)
                .append(dsChSet).append(dsIdentifier).append(dsOctets).append(dsPackets)
                .append(dsScn).append(dsTimeActive).append(fromTimeStr).append(mdIfIndex)
                .append(mdIfName).append(qosVersion).append(region).append(timeZone)
                .append(toTimeStr).append(usChSet).append(usIdentifier).append(usOctets)
                .append(usPackets).append(usScn).append(usTimeActive).append(v).toString();

        return msgString;

       // System.out.print(msgString+"\n");

    }
}
