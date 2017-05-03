package com.bawei.taynews_demo.httrack.shoppingMall;

import java.util.List;

/**
 * 类用途：
 * 作者：ShiZhuangZhuang
 * 时间：2017/4/24 9:10
 */

public class NewsBean {
    private String result;
    private List<DataBean> data;


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }
    public static class DataBean {
        private String ID;
        private String TITLE;
        private String SUBTITLE;
        private Object IMAGEURL;
        private String FROMNAME;
        private String SHOWTIME;
        private int RN;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getTITLE() {
            return TITLE;
        }

        public void setTITLE(String TITLE) {
            this.TITLE = TITLE;
        }

        public String getSUBTITLE() {
            return SUBTITLE;
        }

        public void setSUBTITLE(String SUBTITLE) {
            this.SUBTITLE = SUBTITLE;
        }

        public Object getIMAGEURL() {
            return IMAGEURL;
        }

        public void setIMAGEURL(Object IMAGEURL) {
            this.IMAGEURL = IMAGEURL;
        }

        public String getFROMNAME() {
            return FROMNAME;
        }

        public void setFROMNAME(String FROMNAME) {
            this.FROMNAME = FROMNAME;
        }

        public String getSHOWTIME() {
            return SHOWTIME;
        }

        public void setSHOWTIME(String SHOWTIME) {
            this.SHOWTIME = SHOWTIME;
        }

        public int getRN() {
            return RN;
        }

        public void setRN(int RN) {
            this.RN = RN;
        }
    }

}
