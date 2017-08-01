package com.example.sojin.busbellapp.item;

import org.simpleframework.xml.Element;

/**
 * Created by KSM on 2017-07-25.
 */

public class ApiResultHeader {
    @Element(name = "headerCd")
    String headerCd;
    @Element(name = "headerMsg")
    String headerMsg;
    @Element(name = "itemCount")
    String itemCount;

    public String getHeaderCd() {
        return headerCd;
    }

    public void setHeaderCd(String headerCd) {
        this.headerCd = headerCd;
    }

    public String getHeaderMsg() {
        return headerMsg;
    }

    public void setHeaderMsg(String headerMsg) {
        this.headerMsg = headerMsg;
    }

    public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }
}
