package com.example.hmqqg.hm.entity;

import java.util.List;

/**
 * 获取版本信息的容器
 * Created by Administrator on 2016/6/3.
 */
public class AppVersionEntity {

    /**
     * stamess : 获取版本信息成功！
     * staval : 1
     */

    private List<StatusEntity> status;
    /**
     * AppID : 1
     * AppName : Andriod
     * AppVer : 1.0
     * AppUrl : /appdl/app-release.apk
     */

    private List<DetailInfoEntity> DetailInfo;

    public List<StatusEntity> getStatus() {
        return status;
    }

    public void setStatus(List<StatusEntity> status) {
        this.status = status;
    }

    public List<DetailInfoEntity> getDetailInfo() {
        return DetailInfo;
    }

    public void setDetailInfo(List<DetailInfoEntity> DetailInfo) {
        this.DetailInfo = DetailInfo;
    }

    public static class StatusEntity {
        private String stamess;
        private String staval;

        public String getStamess() {
            return stamess;
        }

        public void setStamess(String stamess) {
            this.stamess = stamess;
        }

        public String getStaval() {
            return staval;
        }

        public void setStaval(String staval) {
            this.staval = staval;
        }
    }

    public static class DetailInfoEntity {
        private int AppID;
        private String AppName;
        private String AppVer;
        private String AppUrl;

        public int getAppID() {
            return AppID;
        }

        public void setAppID(int AppID) {
            this.AppID = AppID;
        }

        public String getAppName() {
            return AppName;
        }

        public void setAppName(String AppName) {
            this.AppName = AppName;
        }

        public String getAppVer() {
            return AppVer;
        }

        public void setAppVer(String AppVer) {
            this.AppVer = AppVer;
        }

        public String getAppUrl() {
            return AppUrl;
        }

        public void setAppUrl(String AppUrl) {
            this.AppUrl = AppUrl;
        }
    }
}
