package com.example.sojin.busbellapp;

import android.os.Build;
import android.os.StrictMode;

import com.example.sojin.busbellapp.item.BusPosInfoItem;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by KSM on 2017-07-02.
 */

public class BusPos {
    static final String request_Address = "http://ws.bus.go.kr/api/rest/buspos/";          // 서비스 요청 주소
    static final String service_Key = "3nvztFALDhnl5ffO0FuwkATq9JDSLJPHjSSVRByOsG78s9vF%2F4SuBbuKcle1XytZB0hkdU19wrBSnqDKHWHpdA%3D%3D";                                                                // 서비스 키

    public static ArrayList<BusPosInfoItem>  getBusPosByRtidList(String busRouteId){
        String service_Name = "getBusPosByRtid";
        String service_Parameter = busRouteId;

        ArrayList<BusPosInfoItem> busPosByRtidList = new ArrayList<BusPosInfoItem>();

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
            BusPosInfoItem item = null;

            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    tagName = parser.getName();
                    if (tagName.equals("itemList")) {
                        isItemTag = true;
                        item = new BusPosInfoItem();
                    }
                } else if (eventType == XmlPullParser.TEXT && isItemTag) {
                    if (tagName.equals("sectionId")) item.setSectionID(parser.getText());
                    else if (tagName.equals("plainNo")) item.setPlainNo(parser.getText());
                } else if (eventType == XmlPullParser.END_TAG) {
                    tagName = parser.getName();
                    if (tagName.equals("itemList")) {
                        isItemTag = false;
                        busPosByRtidList.add(item);
                        item = null;
                    }
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return busPosByRtidList;
    }
}