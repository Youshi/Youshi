package com.example.hmqqg.hm.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/3/12.
 */
public class SingoneEntity {

    /**
     * LogId : 16
     * UserId : op10002
     * UserName : null
     * DateCode : 2016-03-12
     * WeekDays : 六
     * TimeStep : null
     * SignTime : 10-52
     * ClockIn : null
     * ClockOff : null
     * RealDays : null
     * LateTime : null
     * EarlyTime : null
     * WorkTime : null
     * Remark : null
     * IsNight : false
     * IsCheck : false
     * SignIP : 119.180.10.169
     * SignType :
     * SignAddress : null
     * SignAttach :
     * SignWay : M
     * InOff : null
     */

    private DetailInfoEntity DetailInfo;
    /**
     * stamess : 签到列表请求成功！
     * staval : 1
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
        private int LogId;
        private String UserId;
        private Object UserName;
        private String DateCode;
        private String WeekDays;
        private Object TimeStep;
        private String SignTime;
        private Object ClockIn;
        private Object ClockOff;
        private Object RealDays;
        private Object LateTime;
        private Object EarlyTime;
        private Object WorkTime;
        private Object Remark;
        private boolean IsNight;
        private boolean IsCheck;
        private String SignIP;
        private String SignType;
        private Object SignAddress;
        private String SignAttach;
        private String SignWay;
        private Object InOff;

        public void setLogId(int LogId) {
            this.LogId = LogId;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public void setUserName(Object UserName) {
            this.UserName = UserName;
        }

        public void setDateCode(String DateCode) {
            this.DateCode = DateCode;
        }

        public void setWeekDays(String WeekDays) {
            this.WeekDays = WeekDays;
        }

        public void setTimeStep(Object TimeStep) {
            this.TimeStep = TimeStep;
        }

        public void setSignTime(String SignTime) {
            this.SignTime = SignTime;
        }

        public void setClockIn(Object ClockIn) {
            this.ClockIn = ClockIn;
        }

        public void setClockOff(Object ClockOff) {
            this.ClockOff = ClockOff;
        }

        public void setRealDays(Object RealDays) {
            this.RealDays = RealDays;
        }

        public void setLateTime(Object LateTime) {
            this.LateTime = LateTime;
        }

        public void setEarlyTime(Object EarlyTime) {
            this.EarlyTime = EarlyTime;
        }

        public void setWorkTime(Object WorkTime) {
            this.WorkTime = WorkTime;
        }

        public void setRemark(Object Remark) {
            this.Remark = Remark;
        }

        public void setIsNight(boolean IsNight) {
            this.IsNight = IsNight;
        }

        public void setIsCheck(boolean IsCheck) {
            this.IsCheck = IsCheck;
        }

        public void setSignIP(String SignIP) {
            this.SignIP = SignIP;
        }

        public void setSignType(String SignType) {
            this.SignType = SignType;
        }

        public void setSignAddress(Object SignAddress) {
            this.SignAddress = SignAddress;
        }

        public void setSignAttach(String SignAttach) {
            this.SignAttach = SignAttach;
        }

        public void setSignWay(String SignWay) {
            this.SignWay = SignWay;
        }

        public void setInOff(Object InOff) {
            this.InOff = InOff;
        }

        public int getLogId() {
            return LogId;
        }

        public String getUserId() {
            return UserId;
        }

        public Object getUserName() {
            return UserName;
        }

        public String getDateCode() {
            return DateCode;
        }

        public String getWeekDays() {
            return WeekDays;
        }

        public Object getTimeStep() {
            return TimeStep;
        }

        public String getSignTime() {
            return SignTime;
        }

        public Object getClockIn() {
            return ClockIn;
        }

        public Object getClockOff() {
            return ClockOff;
        }

        public Object getRealDays() {
            return RealDays;
        }

        public Object getLateTime() {
            return LateTime;
        }

        public Object getEarlyTime() {
            return EarlyTime;
        }

        public Object getWorkTime() {
            return WorkTime;
        }

        public Object getRemark() {
            return Remark;
        }

        public boolean isIsNight() {
            return IsNight;
        }

        public boolean isIsCheck() {
            return IsCheck;
        }

        public String getSignIP() {
            return SignIP;
        }

        public String getSignType() {
            return SignType;
        }

        public Object getSignAddress() {
            return SignAddress;
        }

        public String getSignAttach() {
            return SignAttach;
        }

        public String getSignWay() {
            return SignWay;
        }

        public Object getInOff() {
            return InOff;
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
