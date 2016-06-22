package com.example.hmqqg.hm.entity;

import java.util.List;

/**
 * 投资提醒列表容器
 * Created by bona on 2016/4/8.
 */
public class Invest_Entity {

    /**
     * stamess : 获取客户投资到期信息成功！
     * staval : 1
     */

    private List<StatusEntity> status;
    /**
     * InvestId : 133
     * userid : KH16000093
     * userName : 汪宝涛
     * InvestName : 郝继金
     * InvestType : CPLX01
     * InvestSum : 6.0E8
     * InvestRate : 1.0
     * ContactNo : Hsdawd
     * BankAccount : 622848625689589955
     * BankName : 农业银行
     * BankMaster : null
     * BankDetail : null
     * BuyYears : null
     * StartDate : 2016-04-01T00:00:00
     * EndDate : 2016-04-28T00:00:00
     * GetRateDate : 2016-4-26
     * InvestDesc : 不粗
     * GetPeriod : null
     * OwnerID : null
     * IsCorp : false
     * OperateId : KH16000093
     * OperateName :
     * Pic1Tit :
     * Pic2Tit :
     * Pic3Tit :
     * Pic4Tit :
     * Pic1Path :
     * Pic2Path :
     * Pic3Path :
     * Pic4Path :
     * CreatedBy :
     * CreateDate : 2016-04-25T11:07:47.9073127+08:00
     * UpdateDate : null
     * IsRemoved : false
     * RemovedBy : null
     * RemovedDate : null
     * UpdatorID : null
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
        private int InvestId;
        private String userid;
        private String userName;
        private String InvestName;
        private String InvestType;
        private double InvestSum;
        private double InvestRate;
        private String ContactNo;
        private String BankAccount;
        private String BankName;
        private Object BankMaster;
        private Object BankDetail;
        private Object BuyYears;
        private String StartDate;
        private String EndDate;
        private String GetRateDate;
        private String InvestDesc;
        private Object GetPeriod;
        private Object OwnerID;
        private boolean IsCorp;
        private String OperateId;
        private String OperateName;
        private String Pic1Tit;
        private String Pic2Tit;
        private String Pic3Tit;
        private String Pic4Tit;
        private String Pic1Path;
        private String Pic2Path;
        private String Pic3Path;
        private String Pic4Path;
        private String CreatedBy;
        private String CreateDate;
        private Object UpdateDate;
        private boolean IsRemoved;
        private Object RemovedBy;
        private Object RemovedDate;
        private Object UpdatorID;

        public void setInvestId(int InvestId) {
            this.InvestId = InvestId;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public void setInvestName(String InvestName) {
            this.InvestName = InvestName;
        }

        public void setInvestType(String InvestType) {
            this.InvestType = InvestType;
        }

        public void setInvestSum(double InvestSum) {
            this.InvestSum = InvestSum;
        }

        public void setInvestRate(double InvestRate) {
            this.InvestRate = InvestRate;
        }

        public void setContactNo(String ContactNo) {
            this.ContactNo = ContactNo;
        }

        public void setBankAccount(String BankAccount) {
            this.BankAccount = BankAccount;
        }

        public void setBankName(String BankName) {
            this.BankName = BankName;
        }

        public void setBankMaster(Object BankMaster) {
            this.BankMaster = BankMaster;
        }

        public void setBankDetail(Object BankDetail) {
            this.BankDetail = BankDetail;
        }

        public void setBuyYears(Object BuyYears) {
            this.BuyYears = BuyYears;
        }

        public void setStartDate(String StartDate) {
            this.StartDate = StartDate;
        }

        public void setEndDate(String EndDate) {
            this.EndDate = EndDate;
        }

        public void setGetRateDate(String GetRateDate) {
            this.GetRateDate = GetRateDate;
        }

        public void setInvestDesc(String InvestDesc) {
            this.InvestDesc = InvestDesc;
        }

        public void setGetPeriod(Object GetPeriod) {
            this.GetPeriod = GetPeriod;
        }

        public void setOwnerID(Object OwnerID) {
            this.OwnerID = OwnerID;
        }

        public void setIsCorp(boolean IsCorp) {
            this.IsCorp = IsCorp;
        }

        public void setOperateId(String OperateId) {
            this.OperateId = OperateId;
        }

        public void setOperateName(String OperateName) {
            this.OperateName = OperateName;
        }

        public void setPic1Tit(String Pic1Tit) {
            this.Pic1Tit = Pic1Tit;
        }

        public void setPic2Tit(String Pic2Tit) {
            this.Pic2Tit = Pic2Tit;
        }

        public void setPic3Tit(String Pic3Tit) {
            this.Pic3Tit = Pic3Tit;
        }

        public void setPic4Tit(String Pic4Tit) {
            this.Pic4Tit = Pic4Tit;
        }

        public void setPic1Path(String Pic1Path) {
            this.Pic1Path = Pic1Path;
        }

        public void setPic2Path(String Pic2Path) {
            this.Pic2Path = Pic2Path;
        }

        public void setPic3Path(String Pic3Path) {
            this.Pic3Path = Pic3Path;
        }

        public void setPic4Path(String Pic4Path) {
            this.Pic4Path = Pic4Path;
        }

        public void setCreatedBy(String CreatedBy) {
            this.CreatedBy = CreatedBy;
        }

        public void setCreateDate(String CreateDate) {
            this.CreateDate = CreateDate;
        }

        public void setUpdateDate(Object UpdateDate) {
            this.UpdateDate = UpdateDate;
        }

        public void setIsRemoved(boolean IsRemoved) {
            this.IsRemoved = IsRemoved;
        }

        public void setRemovedBy(Object RemovedBy) {
            this.RemovedBy = RemovedBy;
        }

        public void setRemovedDate(Object RemovedDate) {
            this.RemovedDate = RemovedDate;
        }

        public void setUpdatorID(Object UpdatorID) {
            this.UpdatorID = UpdatorID;
        }

        public int getInvestId() {
            return InvestId;
        }

        public String getUserid() {
            return userid;
        }

        public String getUserName() {
            return userName;
        }

        public String getInvestName() {
            return InvestName;
        }

        public String getInvestType() {
            return InvestType;
        }

        public double getInvestSum() {
            return InvestSum;
        }

        public double getInvestRate() {
            return InvestRate;
        }

        public String getContactNo() {
            return ContactNo;
        }

        public String getBankAccount() {
            return BankAccount;
        }

        public String getBankName() {
            return BankName;
        }

        public Object getBankMaster() {
            return BankMaster;
        }

        public Object getBankDetail() {
            return BankDetail;
        }

        public Object getBuyYears() {
            return BuyYears;
        }

        public String getStartDate() {
            return StartDate;
        }

        public String getEndDate() {
            return EndDate;
        }

        public String getGetRateDate() {
            return GetRateDate;
        }

        public String getInvestDesc() {
            return InvestDesc;
        }

        public Object getGetPeriod() {
            return GetPeriod;
        }

        public Object getOwnerID() {
            return OwnerID;
        }

        public boolean isIsCorp() {
            return IsCorp;
        }

        public String getOperateId() {
            return OperateId;
        }

        public String getOperateName() {
            return OperateName;
        }

        public String getPic1Tit() {
            return Pic1Tit;
        }

        public String getPic2Tit() {
            return Pic2Tit;
        }

        public String getPic3Tit() {
            return Pic3Tit;
        }

        public String getPic4Tit() {
            return Pic4Tit;
        }

        public String getPic1Path() {
            return Pic1Path;
        }

        public String getPic2Path() {
            return Pic2Path;
        }

        public String getPic3Path() {
            return Pic3Path;
        }

        public String getPic4Path() {
            return Pic4Path;
        }

        public String getCreatedBy() {
            return CreatedBy;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public Object getUpdateDate() {
            return UpdateDate;
        }

        public boolean isIsRemoved() {
            return IsRemoved;
        }

        public Object getRemovedBy() {
            return RemovedBy;
        }

        public Object getRemovedDate() {
            return RemovedDate;
        }

        public Object getUpdatorID() {
            return UpdatorID;
        }
    }
}
