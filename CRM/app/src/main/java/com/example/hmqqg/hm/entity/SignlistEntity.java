package com.example.hmqqg.hm.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/3/12.
 */
public class SignlistEntity {


    /**
     * stamess : 签到列表请求成功！
     * staval : 1
     */

    private List<StatusEntity> status;
    /**
     * UserId : OP10001
     * UserName : 管理员
     * DateCode : 2016-05-20
     * SignTime : 14:21
     * SignIp : 27.216.128.209
     * SignType : 0
     * SignAddress : 烟台市芝罘区世回尧街道
     * SignAttach : null
     * INOFF : 签到
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
        private String UserId;
        private String UserName;
        private String DateCode;
        private String SignTime;
        private String SignIp;
        private String SignType;
        private String SignAddress;
        private Object SignAttach;
        private String INOFF;

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public void setDateCode(String DateCode) {
            this.DateCode = DateCode;
        }

        public void setSignTime(String SignTime) {
            this.SignTime = SignTime;
        }

        public void setSignIp(String SignIp) {
            this.SignIp = SignIp;
        }

        public void setSignType(String SignType) {
            this.SignType = SignType;
        }

        public void setSignAddress(String SignAddress) {
            this.SignAddress = SignAddress;
        }

        public void setSignAttach(Object SignAttach) {
            this.SignAttach = SignAttach;
        }

        public void setINOFF(String INOFF) {
            this.INOFF = INOFF;
        }

        public String getUserId() {
            return UserId;
        }

        public String getUserName() {
            return UserName;
        }

        public String getDateCode() {
            return DateCode;
        }

        public String getSignTime() {
            return SignTime;
        }

        public String getSignIp() {
            return SignIp;
        }

        public String getSignType() {
            return SignType;
        }

        public String getSignAddress() {
            return SignAddress;
        }

        public Object getSignAttach() {
            return SignAttach;
        }

        public String getINOFF() {
            return INOFF;
        }
    }
}
