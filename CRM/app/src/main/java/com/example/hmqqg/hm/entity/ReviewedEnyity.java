package com.example.hmqqg.hm.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/4/20.
 */
public class ReviewedEnyity {

    /**
     * status : [{"stamess":"点评成功！","staval":"1"}]
     * DetailInfo : null
     */

    private Object DetailInfo;
    /**
     * stamess : 点评成功！
     * staval : 1
     */

    private List<StatusEntity> status;

    public void setDetailInfo(Object DetailInfo) {
        this.DetailInfo = DetailInfo;
    }

    public void setStatus(List<StatusEntity> status) {
        this.status = status;
    }

    public Object getDetailInfo() {
        return DetailInfo;
    }

    public List<StatusEntity> getStatus() {
        return status;
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
