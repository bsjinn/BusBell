package com.example.sojin.busbellapp.item;

import org.simpleframework.xml.Element;

/**
 * Created by KSM on 2017-07-02.
 */

public class BusPosInfoItem {
    @Element(name = "busType")
    private String busType;
    @Element(name = "dataTm")
    private String dataTm;
    @Element(name = "gpsX")
    private String gpsX;
    @Element(name = "gpsY")
    private String gpsY;
    @Element(name = "isFullFlag")
    private String isFullFlag;
    @Element(name = "islastyn")
    private String islastyn;
    @Element(name = "isrunyn")
    private String isrunyn;
    @Element(name = "lastStTm")
    private String lastStTm;
    @Element(name = "lastStnId")
    private String lastStnId;
    @Element(name = "nextStTm")
    private String nextStTm;
    @Element(name = "plainNo")
    private String plainNo;
    @Element(name = "posX")
    private String posX;
    @Element(name = "posY")
    private String posY;
    @Element(name = "rtDist")
    private String rtDist;
    @Element(name = "sectDist")
    private String sectDist;
    @Element(name = "sectOrd")
    private String sectOrd;
    @Element(name = "sectionId")
    private String sectionId;
    @Element(name = "stopFlag")
    private String stopFlag;
    @Element(name = "vehId")
    private String vehId;
    @Element(name = "fullSectDist")
    private String fullSectDist;
    @Element(name = "trnstnid")
    private String trnstnid;

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public String getDataTm() {
        return dataTm;
    }

    public void setDataTm(String dataTm) {
        this.dataTm = dataTm;
    }

    public String getGpsX() {
        return gpsX;
    }

    public void setGpsX(String gpsX) {
        this.gpsX = gpsX;
    }

    public String getGpsY() {
        return gpsY;
    }

    public void setGpsY(String gpsY) {
        this.gpsY = gpsY;
    }

    public String getIsFullFlag() {
        return isFullFlag;
    }

    public void setIsFullFlag(String isFullFlag) {
        this.isFullFlag = isFullFlag;
    }

    public String getIslastyn() {
        return islastyn;
    }

    public void setIslastyn(String islastyn) {
        this.islastyn = islastyn;
    }

    public String getIsrunyn() {
        return isrunyn;
    }

    public void setIsrunyn(String isrunyn) {
        this.isrunyn = isrunyn;
    }

    public String getLastStTm() {
        return lastStTm;
    }

    public void setLastStTm(String lastStTm) {
        this.lastStTm = lastStTm;
    }

    public String getLastStnId() {
        return lastStnId;
    }

    public void setLastStnId(String lastStnId) {
        this.lastStnId = lastStnId;
    }

    public String getNextStTm() {
        return nextStTm;
    }

    public void setNextStTm(String nextStTm) {
        this.nextStTm = nextStTm;
    }

    public String getPlainNo() {
        return plainNo;
    }

    public void setPlainNo(String plainNo) {
        this.plainNo = plainNo;
    }

    public String getPosX() {
        return posX;
    }

    public void setPosX(String posX) {
        this.posX = posX;
    }

    public String getPosY() {
        return posY;
    }

    public void setPosY(String posY) {
        this.posY = posY;
    }

    public String getRtDist() {
        return rtDist;
    }

    public void setRtDist(String rtDist) {
        this.rtDist = rtDist;
    }

    public String getSectDist() {
        return sectDist;
    }

    public void setSectDist(String sectDist) {
        this.sectDist = sectDist;
    }

    public String getSectOrd() {
        return sectOrd;
    }

    public void setSectOrd(String sectOrd) {
        this.sectOrd = sectOrd;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getStopFlag() {
        return stopFlag;
    }

    public void setStopFlag(String stopFlag) {
        this.stopFlag = stopFlag;
    }

    public String getVehId() {
        return vehId;
    }

    public void setVehId(String vehId) {
        this.vehId = vehId;
    }

    public String getFullSectDist() {
        return fullSectDist;
    }

    public void setFullSectDist(String fullSectDist) {
        this.fullSectDist = fullSectDist;
    }

    public String getTrnstnid() {
        return trnstnid;
    }

    public void setTrnstnid(String trnstnid) {
        this.trnstnid = trnstnid;
    }
}
