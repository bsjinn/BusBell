package com.example.sojin.busbellapp.item;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

/**
 * Created by KSM on 2017-07-25.
 */

@Root(name = "ServiceResult")
public class BusRouteInfoWrapper {
    @Element(name="comMsgHeader", required = false)
    String comMsgHeader;
    @Element(name="msgHeader")
    ApiResultHeader apiResultHeader;
    @ElementList(name = "msgBody")
    ArrayList<BusRouteInfoItem> busRouteList;

    public String getComMsgHeader() {
        return comMsgHeader;
    }

    public void setComMsgHeader(String comMsgHeader) {
        this.comMsgHeader = comMsgHeader;
    }

    public ApiResultHeader getApiResultHeader() {
        return apiResultHeader;
    }

    public void setApiResultHeader(ApiResultHeader apiResultHeader) {
        this.apiResultHeader = apiResultHeader;
    }

    public ArrayList<BusRouteInfoItem> getBusRouteList() {
        return busRouteList;
    }

    public void setBusRouteList(ArrayList<BusRouteInfoItem> busRouteList) {
        this.busRouteList = busRouteList;
    }
}
