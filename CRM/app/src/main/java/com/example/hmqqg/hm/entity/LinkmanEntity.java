package com.example.hmqqg.hm.entity;

import java.util.List;

/**
 * 紧急联系人实体类
 * Created by bona on 2016/3/5.
 */
public class LinkmanEntity {

    /**
     * stamess : 数据获取成功！
     * staval : 1
     */

    private List<StatusEntity> status;
    /**
     * ManId : LXR1600002
     * ManName : 别吵吵了
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
        private String ManId;
        private String ManName;

        public void setManId(String ManId) {
            this.ManId = ManId;
        }

        public void setManName(String ManName) {
            this.ManName = ManName;
        }

        public String getManId() {
            return ManId;
        }

        public String getManName() {
            return ManName;
        }
    }
}
