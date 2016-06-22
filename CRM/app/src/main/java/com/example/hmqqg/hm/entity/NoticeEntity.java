package com.example.hmqqg.hm.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/3/9.
 */
public class NoticeEntity {

    /**
     * stamess : 获取客户信息成功！
     * staval : 1
     */

    private List<StatusEntity> status;
    /**
     * newsId : 12121810571610
     * newsType : 2
     * title : 2013年公司业务规划
     * titleColor : #000000
     * titleUrl :
     * level : 普通
     * mContent : 年底备货公司集资通知
     * sendId : OP10006
     * sentName : 杨秀东
     * receverItem : null
     * receverUid : null
     * isTime : false
     * sendTime : 2012-12-31T14:33:55
     * sentState : 0
     * readNum : 0
     * reads : 1
     */

    private List<DetailInfoEntity> DetailInfo;

    public void setStatus(List<StatusEntity> status) {
        this.status = status;
    }

    public void setDetailInfo(List<DetailInfoEntity> DetailInfo) {
        this.DetailInfo = DetailInfo;
    }

    public List<StatusEntity> getStatus() {
        return status;
    }

    public List<DetailInfoEntity> getDetailInfo() {
        return DetailInfo;
    }

    public static class StatusEntity {
        private String stamess;
        private String staval;

        public void setStamess(String stamess) {
            this.stamess = stamess;
        }

        public void setStaval(String staval) {
            this.staval = staval;
        }

        public String getStamess() {
            return stamess;
        }

        public String getStaval() {
            return staval;
        }
    }

    public static class DetailInfoEntity {
        private String newsId;
        private int newsType;
        private String title;
        private String titleColor;
        private String titleUrl;
        private String level;
        private String mContent;
        private String sendId;
        private String sentName;
        private Object receverItem;
        private Object receverUid;
        private boolean isTime;
        private String sendTime;
        private int sentState;
        private int readNum;
        private int reads;

        public void setNewsId(String newsId) {
            this.newsId = newsId;
        }

        public void setNewsType(int newsType) {
            this.newsType = newsType;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setTitleColor(String titleColor) {
            this.titleColor = titleColor;
        }

        public void setTitleUrl(String titleUrl) {
            this.titleUrl = titleUrl;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public void setMContent(String mContent) {
            this.mContent = mContent;
        }

        public void setSendId(String sendId) {
            this.sendId = sendId;
        }

        public void setSentName(String sentName) {
            this.sentName = sentName;
        }

        public void setReceverItem(Object receverItem) {
            this.receverItem = receverItem;
        }

        public void setReceverUid(Object receverUid) {
            this.receverUid = receverUid;
        }

        public void setIsTime(boolean isTime) {
            this.isTime = isTime;
        }

        public void setSendTime(String sendTime) {
            this.sendTime = sendTime;
        }

        public void setSentState(int sentState) {
            this.sentState = sentState;
        }

        public void setReadNum(int readNum) {
            this.readNum = readNum;
        }

        public void setReads(int reads) {
            this.reads = reads;
        }

        public String getNewsId() {
            return newsId;
        }

        public int getNewsType() {
            return newsType;
        }

        public String getTitle() {
            return title;
        }

        public String getTitleColor() {
            return titleColor;
        }

        public String getTitleUrl() {
            return titleUrl;
        }

        public String getLevel() {
            return level;
        }

        public String getMContent() {
            return mContent;
        }

        public String getSendId() {
            return sendId;
        }

        public String getSentName() {
            return sentName;
        }

        public Object getReceverItem() {
            return receverItem;
        }

        public Object getReceverUid() {
            return receverUid;
        }

        public boolean isIsTime() {
            return isTime;
        }

        public String getSendTime() {
            return sendTime;
        }

        public int getSentState() {
            return sentState;
        }

        public int getReadNum() {
            return readNum;
        }

        public int getReads() {
            return reads;
        }
    }
}
