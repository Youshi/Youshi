package com.example.hmqqg.hm.entity;

import java.util.List;

/**
 * 修改密码实体类
 * Created by Android-Wq on 2016/1/22.
 */
public class PassEntity {


    /**
     * stamess : 修改密码失败！
     * staval : 0
     */

    private List<StatusEntity> status;

    public void setStatus(List<StatusEntity> status) {
        this.status = status;
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
