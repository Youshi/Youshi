package com.example.hmqqg.hm.entity;

import java.util.List;

/**
 * 修改个人信息实体类
 * Created by Android-Wq on 2016/1/22.
 */
public class Person {


    /**
     * stamess : 个人信息修改成功！
     * staval : 1
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
