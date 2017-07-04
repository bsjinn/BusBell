package com.example.sojin.busbellapp;

import android.os.Build;
import android.os.StrictMode;

import com.example.sojin.busbellapp.item.BusArrInfoItem;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by KSM on 2017-07-03.
 */

public class BusArrInfo {
    static final String request_Address = "http://ws.bus.go.kr/api/rest/arrive/";          // 서비스 요청 주소
    static final String service_Key = "3nvztFALDhnl5ffO0FuwkATq9JDSLJPHjSSVRByOsG78s9vF%2F4SuBbuKcle1XytZB0hkdU19wrBSnqDKHWHpdA%3D%3D";                                                                // 서비스 키

    public static ArrayList<BusArrInfoItem> getArrInfoByRouteList(String stId, String busRouteId, String ord){
        String service_Name = "getArrInfoByRoute";
        String[] service_Parameter = {stId, busRouteId, ord};

        ArrayList<BusArrInfoItem> busArrInfoList = new ArrayList<BusArrInfoItem>();

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {
            StringBuilder urlBuilder = new StringBuilder(request_Address + service_Name);
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + service_Key);
            urlBuilder.append("&" + URLEncoder.encode("stId", "UTF-8") + "=" + service_Parameter[0]);
            urlBuilder.append("&" + URLEncoder.encode("busRouteId", "UTF-8") + "=" + service_Parameter[1]);
            urlBuilder.append("&" + URLEncoder.encode("ord", "UTF-8") + "=" + service_Parameter[2]);
            URL url = new URL(urlBuilder.toString());

            InputStream in = url.openStream();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();

            parser.setInput(in, "utf-8");

            int eventType = parser.getEventType();
            boolean isItemTag = false;

            String tagName = null;
            BusArrInfoItem item1 = null;
            BusArrInfoItem item2 = null;

            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    tagName = parser.getName();
                    if (tagName.equals("itemList")) {
                        isItemTag = true;
                        item1 = new BusArrInfoItem();
                        item2 = new BusArrInfoItem();
                    }
                } else if (eventType == XmlPullParser.TEXT && isItemTag) {
                    if (tagName.equals("plainNo1")) item1.setPlainNo(parser.getText());
                    else if (tagName.equals("plainNo2")) item2.setPlainNo(parser.getText());
                    else if (tagName.equals("vehId1")) item1.setVehId(parser.getText());
                    else if (tagName.equals("vehId2")) item2.setVehId(parser.getText());
                    else if (tagName.equals("arrmsg1")) item1.setArrmsg(parser.getText());
                    else if (tagName.equals("arrmsg2")) item2.setArrmsg(parser.getText());
                    else if (tagName.equals("stationNm1")) item1.setStationNm(parser.getText());
                    else if (tagName.equals("stationNm2")) item2.setStationNm(parser.getText());
                } else if (eventType == XmlPullParser.END_TAG) {
                    tagName = parser.getName();
                    if (tagName.equals("itemList")) {
                        isItemTag = false;
                        busArrInfoList.add(item1);
                        item1 = null;
                        busArrInfoList.add(item2);
                        item2 = null;
                    }
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return busArrInfoList;
    }
}
