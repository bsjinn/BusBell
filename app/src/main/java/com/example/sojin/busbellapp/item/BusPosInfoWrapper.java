package com.example.sojin.busbellapp.item;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

/**
 * Created by KSM on 2017-07-25.
 */

@Root(name = "ServiceResult")
public class BusPosInfoWrapper {
    @Element(name="comMsgHeader", required = false)
    String comMsgHeader;
    @Element(name="msgHeader")
    ApiResultHeader apiResultHeader;
    @ElementList(name = "msgBody")
    ArrayList<BusPosInfoItem> busPosList;

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

    public ArrayList<BusPosInfoItem> getBusPosList() {
        return busPosList;
    }

    public void setBusPosList(ArrayList<BusPosInfoItem> busPosList) {
        this.busPosList = busPosList;
    }
}
