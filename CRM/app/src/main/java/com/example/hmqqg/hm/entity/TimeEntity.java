package com.example.hmqqg.hm.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/6/2.
 */
public class TimeEntity {

    /**
     * KqbcId : 101
     * KqbcName : 正常班
     * BeginTime : 08:30
     * EndTime : 17:00
     * ValidBeginTime : 06:00
     * ValidEndTime : 22:00
     * LateTime : 08:30
     * WorkTimeDel : 1.0
     * IsNight : false
     * IsDefault : true
     * RealDays : 1.0
     */

    private DetailInfoEntity DetailInfo;
    /**
     * stamess : 签到设置请求成功！
     * staval : 0
     */

    private List<StatusEntity> status;

    public void setDetailInfo(DetailInfoEntity DetailInfo) {
        this.DetailInfo = DetailInfo;
    }

    public void setStatus(List<StatusEntity> status) {
        this.status = status;
    }

    public DetailInfoEntity getDetailInfo() {
        return DetailInfo;
    }

    public List<StatusEntity> getStatus() {
        return status;
    }

    public static class DetailInfoEntity {
        private String KqbcId;
        private String KqbcName;
        private String BeginTime;
        private String EndTime;
        private String ValidBeginTime;
        private String ValidEndTime;
        private String LateTime;
        private double WorkTimeDel;
        private boolean IsNight;
        private boolean IsDefault;
        private double RealDays;

        public void setKqbcId(String KqbcId) {
            this.KqbcId = KqbcId;
        }

        public void setKqbcName(String KqbcName) {
            this.KqbcName = KqbcName;
        }

        public void setBeginTime(String BeginTime) {
            this.BeginTime = BeginTime;
        }

        public void setEndTime(String EndTime) {
            this.EndTime = EndTime;
        }

        public void setValidBeginTime(String ValidBeginTime) {
            this.ValidBeginTime = ValidBeginTime;
        }

        public void setValidEndTime(String ValidEndTime) {
            this.ValidEndTime = ValidEndTime;
        }

        public void setLateTime(String LateTime) {
            this.LateTime = LateTime;
        }

        public void setWorkTimeDel(double WorkTimeDel) {
            this.WorkTimeDel = WorkTimeDel;
        }

        public void setIsNight(boolean IsNight) {
            this.IsNight = IsNight;
        }

        public void setIsDefault(boolean IsDefault) {
            this.IsDefault = IsDefault;
        }

        public void setRealDays(double RealDays) {
            this.RealDays = RealDays;
        }

        public String getKqbcId() {
            return KqbcId;
        }

        public String getKqbcName() {
            return KqbcName;
        }

        public String getBeginTime() {
            return BeginTime;
        }

        public String getEndTime() {
            return EndTime;
        }

        public String getValidBeginTime() {
            return ValidBeginTime;
        }

        public String getValidEndTime() {
            return ValidEndTime;
        }

        public String getLateTime() {
            return LateTime;
        }

        public double getWorkTimeDel() {
            return WorkTimeDel;
        }

        public boolean isIsNight() {
            return IsNight;
        }

        public boolean isIsDefault() {
            return IsDefault;
        }

        public double getRealDays() {
            return RealDays;
        }
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
}
