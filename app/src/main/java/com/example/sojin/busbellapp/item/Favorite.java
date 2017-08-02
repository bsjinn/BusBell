package com.example.sojin.busbellapp.item;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sungmokang on 2017. 8. 1..
 */

public class Favorite extends RealmObject implements Serializable{
    @PrimaryKey
    private int idx;
    private String favorite_title;
    private String depart_station_id;
    private String depart_station_nm;
    private String arrive_pre_station_id;
    private String arrive_station_id;
    private String arrive_station_nm;
    private String route_id;
    private String route_nm;

    public Favorite() {
    }

    public Favorite(int idx, String favorite_title, String depart_station_id, String depart_station_nm, String arrive_pre_station_id, String arrive_station_id, String arrive_station_nm, String route_id, String route_nm) {
        this.idx = idx;
        this.favorite_title = favorite_title;
        this.depart_station_id = depart_station_id;
        this.depart_station_nm = depart_station_nm;
        this.arrive_pre_station_id = arrive_pre_station_id;
        this.arrive_station_id = arrive_station_id;
        this.arrive_station_nm = arrive_station_nm;
        this.route_id = route_id;
        this.route_nm = route_nm;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getFavorite_title() {
        return favorite_title;
    }

    public void setFavorite_title(String favorite_title) {
        this.favorite_title = favorite_title;
    }

    public String getDepart_station_id() {
        return depart_station_id;
    }

    public void setDepart_station_id(String depart_station_id) {
        this.depart_station_id = depart_station_id;
    }

    public String getDepart_station_nm() {
        return depart_station_nm;
    }

    public void setDepart_station_nm(String depart_station_nm) {
        this.depart_station_nm = depart_station_nm;
    }

    public String getArrive_pre_station_id() {
        return arrive_pre_station_id;
    }

    public void setArrive_pre_station_id(String arrive_pre_station_id) {
        this.arrive_pre_station_id = arrive_pre_station_id;
    }

    public String getArrive_station_id() {
        return arrive_station_id;
    }

    public void setArrive_station_id(String arrive_station_id) {
        this.arrive_station_id = arrive_station_id;
    }

    public String getArrive_station_nm() {
        return arrive_station_nm;
    }

    public void setArrive_station_nm(String arrive_station_nm) {
        this.arrive_station_nm = arrive_station_nm;
    }

    public String getRoute_id() {
        return route_id;
    }

    public void setRoute_id(String route_id) {
        this.route_id = route_id;
    }

    public String getRoute_nm() {
        return route_nm;
    }

    public void setRoute_nm(String route_nm) {
        this.route_nm = route_nm;
    }
}
