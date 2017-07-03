package com.example.sojin.busbellapp.item;

// 노선 번호로 검색 된 버스 경로를 나타내는 클래스
public class BusRouteInfoItem {
    String busRouteId;              // 노선 ID
    String busRouteNm;              // 노선명
    String length;                  // 노선 길이 (KM)
    String routeType;               // 노선 유형  (1:공항, 3:간선, 4:지선, 5:순환, 6:광역, 7:인천, 8:경기, 9:폐지, 0:공용)
    String stStationNm;             // 기점
    String edStationNm;             // 종점
    String term;                    // 배차간격 (분)
    String lastBusYn;               // 막차운행여부
    String firstBusTm;              // 금일첫차시간
    String lastBusTm;               // 금일막차시간
    String firstLowTm;              // 금일저상첫차시간
    String lastLowTm;               // 금일저상막차시간
    String corpNm;                  // 운수사명

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

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getRouteType() {
        return routeType;
    }

    public void setRouteType(String routeType) {
        this.routeType = routeType;
    }

    public String getStStationNm() {
        return stStationNm;
    }

    public void setStStationNm(String stStationNm) {
        this.stStationNm = stStationNm;
    }

    public String getEdStationNm() {
        return edStationNm;
    }

    public void setEdStationNm(String edStationNm) {
        this.edStationNm = edStationNm;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getLastBusYn() {
        return lastBusYn;
    }

    public void setLastBusYn(String lastBusYn) {
        this.lastBusYn = lastBusYn;
    }

    public String getFirstBusTm() {
        return firstBusTm;
    }

    public void setFirstBusTm(String firstBusTm) {
        this.firstBusTm = firstBusTm;
    }

    public String getLastBusTm() {
        return lastBusTm;
    }

    public void setLastBusTm(String lastBusTm) {
        this.lastBusTm = lastBusTm;
    }

    public String getFirstLowTm() {
        return firstLowTm;
    }

    public void setFirstLowTm(String firstLowTm) {
        this.firstLowTm = firstLowTm;
    }

    public String getLastLowTm() {
        return lastLowTm;
    }

    public void setLastLowTm(String lastLowTm) {
        this.lastLowTm = lastLowTm;
    }

    public String getCorpNm() {
        return corpNm;
    }

    public void setCorpNm(String corpNm) {
        this.corpNm = corpNm;
    }
}
