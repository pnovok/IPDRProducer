package data.generator;

import java.util.Date;

public class IPDRRecord {
    private String batchId;
    private String cmIp;
    private String cmMacAddr;
    private String cmtsHostName;
    private String cmtsIp;
    private Integer dsChSet_Cnt;
    private Integer dsIdentifier;
    private Integer dsOctets;
    private Integer dsPackets;
    private String dsScn;
    private Integer dsTimeActive;
    private Date fromTime;
    private Integer mdIfIndex;
    private String mdIfName;
    private Integer qosVersion;
    private String timeZone;
    private Integer usChSet_Cnt;
    private Integer usIdentifier;
    private Integer usOctets;
    private Integer usPackets;
    private String usScn;
    private Integer usTimeActive;
    private Integer v;

    public String getUsScn() {
        return usScn;
    }

    public void setUsScn(String usScn) {
        this.usScn = usScn;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }


    public Integer getUsTimeActive() {
        return usTimeActive;
    }

    public void setUsTimeActive(Integer usTimeActive) {
        this.usTimeActive = usTimeActive;
    }

    public Integer getUsPackets() {
        return usPackets;
    }

    public void setUsPackets(Integer usPackets) {
        this.usPackets = usPackets;
    }

    public Integer getUsOctets() {
        return usOctets;
    }

    public void setUsOctets(Integer usOctets) {
        this.usOctets = usOctets;
    }

    public Integer getUsIdentifier() {
        return usIdentifier;
    }

    public void setUsIdentifier(Integer usIdentifier) {
        this.usIdentifier = usIdentifier;
    }

    public Integer getUsChSet_Cnt() {
        return usChSet_Cnt;
    }

    public void setUsChSet_Cnt(Integer usChSet_Cnt) {
        this.usChSet_Cnt = usChSet_Cnt;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    private String region;

    public Integer getQosVersion() {
        return qosVersion;
    }

    public void setQosVersion(Integer qosVersion) {
        this.qosVersion = qosVersion;
    }


    public String getMdIfName() {
        return mdIfName;
    }

    public void setMdIfName(String mdIfName) {
        this.mdIfName = mdIfName;
    }

    public Integer getMdIfIndex() {
        return mdIfIndex;
    }

    public void setMdIfIndex(Integer mdIfIndex) {
        this.mdIfIndex = mdIfIndex;
    }

    public Date getFromTime() {
        return fromTime;
    }

    public void setFromTime(Date fromTime) {
        this.fromTime = fromTime;
    }

    public String getDsScn() {
        return dsScn;
    }

    public void setDsScn(String dsScn) {
        this.dsScn = dsScn;
    }

    public Integer getDsPackets() {
        return dsPackets;
    }

    public void setDsPackets(Integer dsPackets) {
        this.dsPackets = dsPackets;
    }

    public Integer getDsOctets() {
        return dsOctets;
    }

    public void setDsOctets(Integer dsOctets) {
        this.dsOctets = dsOctets;
    }

    public Integer getDsIdentifier() {
        return dsIdentifier;
    }

    public void setDsIdentifier(Integer dsIdentifier) {
        this.dsIdentifier = dsIdentifier;
    }

    public Integer getDsChSet_Cnt() {
        return dsChSet_Cnt;
    }

    public void setDsChSet_Cnt(Integer dsChSet_Cnt) {
        this.dsChSet_Cnt = dsChSet_Cnt;
    }

    public String getCmtsIp() {
        return cmtsIp;
    }

    public void setCmtsIp(String cmtsIp) {
        this.cmtsIp = cmtsIp;
    }


    public String getCmtsHostName() {
        return cmtsHostName;
    }

    public void setCmtsHostName(String cmtsHostName) {
        this.cmtsHostName = cmtsHostName;
    }

    public String getCmMacAddr() {
        return cmMacAddr;
    }

    public void setCmMacAddr(String cmMacAddr) {
        this.cmMacAddr = cmMacAddr;
    }

    public String getCmIp() {
        return cmIp;
    }

    public void setCmIp(String cmIp) {
        this.cmIp = cmIp;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public Integer getDsTimeActive() {
        return dsTimeActive;
    }

    public void setDsTimeActive(Integer dsTimeActive) {
        this.dsTimeActive = dsTimeActive;
    }
}
