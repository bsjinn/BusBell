package com.example.sojin.busbellapp.item;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

/**
 * Created by sungmokang on 2017. 7. 29..
 */

@Root(name = "ServiceResult")
public class BusArrInfoWrapper {
    @Element(name = "comMsgHeader", required = false)
    String comMsgHeader;
    @Element(name = "msgHeader")
    ApiResultHeader apiResultHeader;
    @ElementList(name = "msgBody")
    ArrayList<BusArrInfoItem> busArrInfoList;

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

    public ArrayList<BusArrInfoItem> getBusArrInfoList() {
        return busArrInfoList;
    }

    public void setBusArrInfoList(ArrayList<BusArrInfoItem> busArrInfoList) {
        this.busArrInfoList = busArrInfoList;
    }
}
