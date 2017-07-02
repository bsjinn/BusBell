package com.example.sojin.busbellapp;

// 한 노선을 지나는 버스정류장을 나타내는 클래스
public class BusStationsByRouteInfoItem {
    String busRouteId;      // 노선 ID
    String busRouteNm;      // 노선명
    String seq;             // 순번
    String section;         // 구간 ID
    String station;         // 정류소 ID
    String stationNm;       // 정류소 이름
    String gpsX;            // X좌표
    String gpsY;            // Y좌표
    String direction;       // 진행방향
    String fullSectDist;    // 정류소간 거리
    String stationNo;       // 정류소 고유번호
    String routeType;       // 노선 유형 (1:공항, 3:간선, 4:지선, 5:순환, 6:광역, 7:인천, 8:경기, 9:폐지, 0:공용)
    String beginTm;         // 첫차 시간
    String lastTm;          // 막차 시간
    String trnstnid;        // 회차지 정류소ID
    String posX;            // 좌표X
    String posY;            // 좌표Y
    String sectSpd;         // 구간속도
    String arsId;           // 정류소 고유번호
    String transYn;         // 회차지 여부
    String busPos_plainNo; // 해당 정류장에 위치하는 버스 정보


    public String getBusRouteId() {
        return busRouteId;
    }

    public void setBusRouteId(String busRouteId) {
        this.busRouteId = busRouteId;
    }

    public String getBusRouteNm() {
        return busRouteNm;
    }

    public void setBusRouteNm(String busRouteNm) {
        this.busRouteNm = busRouteNm;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getStationNm() {
        return stationNm;
    }

    public void setStationNm(String stationNm) {
        this.stationNm = stationNm;
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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getFullSectDist() {
        return fullSectDist;
    }

    public void setFullSectDist(String fullSectDist) {
        this.fullSectDist = fullSectDist;
    }

    public String getStationNo() {
        return stationNo;
    }

    public void setStationNo(String stationNo) {
        this.stationNo = stationNo;
    }

    public String getRouteType() {
        return routeType;
    }

    public void setRouteType(String routeType) {
        this.routeType = routeType;
    }

    public String getBeginTm() {
        return beginTm;
    }

    public void setBeginTm(String beginTm) {
        this.beginTm = beginTm;
    }

    public String getLastTm() {
        return lastTm;
    }

    public void setLastTm(String lastTm) {
        this.lastTm = lastTm;
    }

    public String getTrnstnid() {
        return trnstnid;
    }

    public void setTrnstnid(String trnstnid) {
        this.trnstnid = trnstnid;
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

    public String getSectSpd() {
        return sectSpd;
    }

    public void setSectSpd(String sectSpd) {
        this.sectSpd = sectSpd;
    }

    public String getArsId() {
        return arsId;
    }

    public void setArsId(String arsId) {
        this.arsId = arsId;
    }

    public String getTransYn() {
        return transYn;
    }

    public void setTransYn(String transYn) {
        this.transYn = transYn;
    }

    public String getBusPos_plainNo() {
        return busPos_plainNo;
    }

    public void setBusPos_plainNo(String busPos_plainNo) {
        this.busPos_plainNo = busPos_plainNo;
    }
}
