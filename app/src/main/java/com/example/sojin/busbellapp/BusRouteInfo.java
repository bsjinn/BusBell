package com.example.sojin.busbellapp;

import android.os.Build;
import android.os.StrictMode;

import com.example.sojin.busbellapp.item.BusRouteInfoItem;
import com.example.sojin.busbellapp.item.BusStationsByRouteInfoItem;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class BusRouteInfo {
    static final String request_Address = "http://ws.bus.go.kr/api/rest/busRouteInfo/";          // 서비스 요청 주소
    static final String service_Key = "3nvztFALDhnl5ffO0FuwkATq9JDSLJPHjSSVRByOsG78s9vF%2F4SuBbuKcle1XytZB0hkdU19wrBSnqDKHWHpdA%3D%3D";                                                                // 서비스 키

    // 노선번호에 해당되는 노선 목록 조회
    // 파라미터 : strRouteInfo (검색할 노선번호)
    // 리턴값 : busRouteInfoList (검색된 노선정보를 담은 ArrayList)
    public static ArrayList<BusRouteInfoItem> getBusRouteList(String strSrch) {
        String service_Name = "getBusRouteList";
        String service_Parameter = strSrch;

        ArrayList<BusRouteInfoItem> busRouteInfoList = new ArrayList<BusRouteInfoItem>();

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {
            StringBuilder urlBuilder = new StringBuilder(request_Address + service_Name);
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + service_Key);
            urlBuilder.append("&" + URLEncoder.encode("strSrch", "UTF-8") + "=" + service_Parameter);
            URL url = new URL(urlBuilder.toString());

            InputStream in = url.openStream();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();

            parser.setInput(in, "utf-8");

            int eventType = parser.getEventType();
            boolean isItemTag = false;

            String tagName = null;
            BusRouteInfoItem item = null;

            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    tagName = parser.getName();
                    if (tagName.equals("itemList")) {
                        isItemTag = true;
                        item = new BusRouteInfoItem();
                    }
                } else if (eventType == XmlPullParser.TEXT && isItemTag) {
                    if (tagName.equals("busRouteId")) item.setBusRouteId(parser.getText());
                    else if (tagName.equals("busRouteNm")) item.setBusRouteNm(parser.getText());
                    else if (tagName.equals("length")) item.setLength(parser.getText());
                    else if (tagName.equals("routeType")) item.setRouteType(parser.getText());
                    else if (tagName.equals("stStationNm")) item.setStStationNm(parser.getText());
                    else if (tagName.equals("edStationNm")) item.setEdStationNm(parser.getText());
                    else if (tagName.equals("term")) item.setTerm(parser.getText());
                    else if (tagName.equals("lastBusYn")) item.setLastBusYn(parser.getText());
                    else if (tagName.equals("firstBusTm")) item.setFirstBusTm(parser.getText());
                    else if (tagName.equals("lastBusTm")) item.setLastBusTm(parser.getText());
                    else if (tagName.equals("firstLowTm")) item.setFirstLowTm(parser.getText());
                    else if (tagName.equals("lastLowTm")) item.setFirstLowTm(parser.getText());
                    else if (tagName.equals("corpNm")) item.setCorpNm(parser.getText());
                } else if (eventType == XmlPullParser.END_TAG) {
                    tagName = parser.getName();
                    if (tagName.equals("itemList")) {
                        isItemTag = false;
                        busRouteInfoList.add(item);
                        item = null;
                    }
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return busRouteInfoList;
    }

    public void getBusPathList() {

    }

    public void getBusRouteInfoItem() {

    }

    // 노선별 경유 정류소 조회 서비스
    // 파라미터 : busRouteId (검색할 노선 ID)
    // 리턴값 : busStationByRouteInfoList (검색된 노선의 정류장을 담은 ArrayList)
    public static ArrayList<BusStationsByRouteInfoItem> getStationByRouteList(String busRouteId) {
        String service_Name = "getStaionByRoute";
        String service_Parameter = busRouteId;

        ArrayList<BusStationsByRouteInfoItem> busStationByRouteInfoList = new ArrayList<BusStationsByRouteInfoItem>();

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {
            StringBuilder urlBuilder = new StringBuilder(request_Address + service_Name);
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + service_Key);
            urlBuilder.append("&" + URLEncoder.encode("busRouteId", "UTF-8") + "=" + service_Parameter);

            URL url = new URL(urlBuilder.toString());

            InputStream in = url.openStream();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();

            parser.setInput(in, "utf-8");

            int eventType = parser.getEventType();
            boolean isItemTag = false;

            String tagName = null;
            BusStationsByRouteInfoItem item = null;

            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    tagName = parser.getName();
                    if (tagName.equals("itemList")) {
                        isItemTag = true;
                        item = new BusStationsByRouteInfoItem();
                    }
                } else if (eventType == XmlPullParser.TEXT && isItemTag) {
                    if (tagName.equals("busRouteId")) item.setBusRouteId(parser.getText());
                    else if (tagName.equals("busRouteNm")) item.setBusRouteNm(parser.getText());
                    else if (tagName.equals("seq")) item.setSeq(parser.getText());
                    else if (tagName.equals("section")) item.setSection(parser.getText());
                    else if (tagName.equals("station")) item.setStation(parser.getText());
                    else if (tagName.equals("stationNm")) item.setStationNm(parser.getText());
                    else if (tagName.equals("gpsX")) item.setGpsX(parser.getText());
                    else if (tagName.equals("gpsY")) item.setGpsY(parser.getText());
                    else if (tagName.equals("direction")) item.setDirection(parser.getText());
                    else if (tagName.equals("fullSectDist")) item.setFullSectDist(parser.getText());
                    else if (tagName.equals("stationNo")) item.setStationNo(parser.getText());
                    else if (tagName.equals("routeType")) item.setRouteType(parser.getText());
                    else if (tagName.equals("beginTm")) item.setBeginTm(parser.getText());
                    else if (tagName.equals("lastTm")) item.setLastTm(parser.getText());
                    else if (tagName.equals("trnstnid")) item.setTrnstnid(parser.getText());
                    else if (tagName.equals("posX")) item.setPosX(parser.getText());
                    else if (tagName.equals("posY")) item.setPosY(parser.getText());
                    else if (tagName.equals("sectSpd")) item.setSectSpd(parser.getText());
                    else if (tagName.equals("arsId")) item.setArsId(parser.getText());
                    else if (tagName.equals("transYn")) item.setTransYn(parser.getText());
                } else if (eventType == XmlPullParser.END_TAG) {
                    tagName = parser.getName();
                    if (tagName.equals("itemList")) {
                        isItemTag = false;
                        busStationByRouteInfoList.add(item);
                        item = null;
                    }
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return busStationByRouteInfoList;
    }
}
