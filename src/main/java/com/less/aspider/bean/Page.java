package com.less.aspider.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deeper on 2017/12/19.
 */

public class Page {

    private boolean downloadSuccess;

    private List<Request> targetRequests = new ArrayList<Request>();

    public void setDownloadSuccess(boolean downloadSuccess) {
        this.downloadSuccess = downloadSuccess;
    }

    public boolean isDownloadSuccess() {
        return downloadSuccess;
    }
}
