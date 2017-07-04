package com.example.sojin.busbellapp.item;

/**
 * Created by KSM on 2017-07-03.
 */

public class BusArrInfoItem {
    private String vehId;           // 도착예정 버스ID
    private String plainNo;         // 도착예정 차량번호
    private String stationNm;       // 도착예정버스의 최종정류소명
    private String arrmsg;          // 도착예정버스 알람메시지

    public String getVehId() {
        return vehId;
    }

    public void setVehId(String vehId) {
        this.vehId = vehId;
    }

    public String getPlainNo() {
        return plainNo;
    }

    public void setPlainNo(String plainNo) {
        this.plainNo = plainNo;
    }

    public String getStationNm() {
        return stationNm;
    }

    public void setStationNm(String stationNm) {
        this.stationNm = stationNm;
    }

    public String getArrmsg() {
        return arrmsg;
    }

    public void setArrmsg(String arrmsg) {
        this.arrmsg = arrmsg;
    }
}
