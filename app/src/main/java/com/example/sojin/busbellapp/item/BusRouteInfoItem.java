package com.example.sojin.busbellapp.item;

import org.simpleframework.xml.Element;

public class BusRouteInfoItem {
    @Element(name = "busRouteId")
    private String busRouteId;              // 노선 ID
    @Element(name = "busRouteNm")
    private String busRouteNm;              // 노선명
    @Element(name = "corpNm")
    private String corpNm;                  // 운수사명
    @Element(name = "edStationNm")
    private String edStationNm;             // 종점
    @Element(name = "firstBusTm")
    private String firstBusTm;              // 금일첫차시간
    @Element(name = "firstLowTm")
    private String firstLowTm;              // 금일저상첫차시간
    @Element(name = "lastBusTm")
    private String lastBusTm;               // 금일막차시간
    @Element(name = "lastBusYn")
    private String lastBusYn;               // 막차운행여부
    @Element(name = "lastLowTm")
    private String lastLowTm;               // 금일저상막차시간
    @Element(name = "length")
    private String length;                  // 노선 길이 (KM)
    @Element(name = "routeType")
    private String routeType;               // 노선 유형  (1:공항, 3:간선, 4:지선, 5:순환, 6:광역, 7:인천, 8:경기, 9:폐지, 0:공용)
    @Element(name = "stStationNm")
    private String stStationNm;             // 기점
    @Element(name = "term")
    private String term;                    // 배차간격 (분)

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
