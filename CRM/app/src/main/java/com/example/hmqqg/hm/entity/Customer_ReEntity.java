package com.example.hmqqg.hm.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/4/25.
 */
public class Customer_ReEntity {

    /**
     * stamess : 获取客户生日信息成功！
     * staval : 1
     */

    private List<StatusEntity> status;
    /**
     * UserId : KH16000093
     * UserName : 汪宝涛
     * UserType : null
     * UserSex : null
     * BirthDate : 2015/04/28
     * ValueEvaluate : null
     * CreditGrade : null
     * UserSort : null
     * UserSortName : null
     * Trade : null
     * TradeName : null
     * UserGrade : null
     * UserGradeName : null
     * PersScale : null
     * PersScaleName : null
     * Origin : null
     * OriginName : null
     * Moment : null
     * MomentName : null
     * Company : null
     * Address : null
     * Post : null
     * Phone : 13030303054
     * NetAddress : null
     * Fax : null
     * Email : null
     * DgAddress1 : null
     * DgAddress2 : null
     * Remark : null
     * OperatorId : null
     * OperatorName : null
     * CreateDate : 0001-01-01T00:00:00
     * UpdateUserId : null
     * UpdateUserName : null
     * UpdateDate : 0001-01-01T00:00:00
     * FuKuanFs : null
     * FuKuanFsName : null
     * ZheKouLv : 0.0
     * ZhangQi : 0
     * XinYunEd : 0.0
     * NaSuiNo : null
     * DefaultTax : 0.0
     * KaiPiaoType : null
     * KaiPiaoTypeName : null
     * Bank : null
     * Account : null
     * TZPH : null
     * TZNL : null
     * TZQD : null
     * TZNLVAL : null
     * TZQDVAL : null
     * CertifiyType : null
     * CertifiyNO : null
     * Duty : null
     * JJR : null
     * qq : null
     * WeiXin : null
     * OfficeTel : null
     * HomeTel : null
     * pUserName : null
     * MarriType : null
     * ContactName : null
     * ContactTel : null
     * RelaType : null
     * Call : null
     * DeptCode : null
     * OwnerID : null
     * BirthDay : null
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
        private Object UserType;
        private Object UserSex;
        private String BirthDate;
        private Object ValueEvaluate;
        private Object CreditGrade;
        private Object UserSort;
        private Object UserSortName;
        private Object Trade;
        private Object TradeName;
        private Object UserGrade;
        private Object UserGradeName;
        private Object PersScale;
        private Object PersScaleName;
        private Object Origin;
        private Object OriginName;
        private Object Moment;
        private Object MomentName;
        private Object Company;
        private Object Address;
        private Object Post;
        private String Phone;
        private Object NetAddress;
        private Object Fax;
        private Object Email;
        private Object DgAddress1;
        private Object DgAddress2;
        private Object Remark;
        private Object OperatorId;
        private Object OperatorName;
        private String CreateDate;
        private Object UpdateUserId;
        private Object UpdateUserName;
        private String UpdateDate;
        private Object FuKuanFs;
        private Object FuKuanFsName;
        private double ZheKouLv;
        private int ZhangQi;
        private double XinYunEd;
        private Object NaSuiNo;
        private double DefaultTax;
        private Object KaiPiaoType;
        private Object KaiPiaoTypeName;
        private Object Bank;
        private Object Account;
        private Object TZPH;
        private Object TZNL;
        private Object TZQD;
        private Object TZNLVAL;
        private Object TZQDVAL;
        private Object CertifiyType;
        private Object CertifiyNO;
        private Object Duty;
        private Object JJR;
        private Object qq;
        private Object WeiXin;
        private Object OfficeTel;
        private Object HomeTel;
        private Object pUserName;
        private Object MarriType;
        private Object ContactName;
        private Object ContactTel;
        private Object RelaType;
        private Object Call;
        private Object DeptCode;
        private Object OwnerID;
        private Object BirthDay;

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public void setUserType(Object UserType) {
            this.UserType = UserType;
        }

        public void setUserSex(Object UserSex) {
            this.UserSex = UserSex;
        }

        public void setBirthDate(String BirthDate) {
            this.BirthDate = BirthDate;
        }

        public void setValueEvaluate(Object ValueEvaluate) {
            this.ValueEvaluate = ValueEvaluate;
        }

        public void setCreditGrade(Object CreditGrade) {
            this.CreditGrade = CreditGrade;
        }

        public void setUserSort(Object UserSort) {
            this.UserSort = UserSort;
        }

        public void setUserSortName(Object UserSortName) {
            this.UserSortName = UserSortName;
        }

        public void setTrade(Object Trade) {
            this.Trade = Trade;
        }

        public void setTradeName(Object TradeName) {
            this.TradeName = TradeName;
        }

        public void setUserGrade(Object UserGrade) {
            this.UserGrade = UserGrade;
        }

        public void setUserGradeName(Object UserGradeName) {
            this.UserGradeName = UserGradeName;
        }

        public void setPersScale(Object PersScale) {
            this.PersScale = PersScale;
        }

        public void setPersScaleName(Object PersScaleName) {
            this.PersScaleName = PersScaleName;
        }

        public void setOrigin(Object Origin) {
            this.Origin = Origin;
        }

        public void setOriginName(Object OriginName) {
            this.OriginName = OriginName;
        }

        public void setMoment(Object Moment) {
            this.Moment = Moment;
        }

        public void setMomentName(Object MomentName) {
            this.MomentName = MomentName;
        }

        public void setCompany(Object Company) {
            this.Company = Company;
        }

        public void setAddress(Object Address) {
            this.Address = Address;
        }

        public void setPost(Object Post) {
            this.Post = Post;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }

        public void setNetAddress(Object NetAddress) {
            this.NetAddress = NetAddress;
        }

        public void setFax(Object Fax) {
            this.Fax = Fax;
        }

        public void setEmail(Object Email) {
            this.Email = Email;
        }

        public void setDgAddress1(Object DgAddress1) {
            this.DgAddress1 = DgAddress1;
        }

        public void setDgAddress2(Object DgAddress2) {
            this.DgAddress2 = DgAddress2;
        }

        public void setRemark(Object Remark) {
            this.Remark = Remark;
        }

        public void setOperatorId(Object OperatorId) {
            this.OperatorId = OperatorId;
        }

        public void setOperatorName(Object OperatorName) {
            this.OperatorName = OperatorName;
        }

        public void setCreateDate(String CreateDate) {
            this.CreateDate = CreateDate;
        }

        public void setUpdateUserId(Object UpdateUserId) {
            this.UpdateUserId = UpdateUserId;
        }

        public void setUpdateUserName(Object UpdateUserName) {
            this.UpdateUserName = UpdateUserName;
        }

        public void setUpdateDate(String UpdateDate) {
            this.UpdateDate = UpdateDate;
        }

        public void setFuKuanFs(Object FuKuanFs) {
            this.FuKuanFs = FuKuanFs;
        }

        public void setFuKuanFsName(Object FuKuanFsName) {
            this.FuKuanFsName = FuKuanFsName;
        }

        public void setZheKouLv(double ZheKouLv) {
            this.ZheKouLv = ZheKouLv;
        }

        public void setZhangQi(int ZhangQi) {
            this.ZhangQi = ZhangQi;
        }

        public void setXinYunEd(double XinYunEd) {
            this.XinYunEd = XinYunEd;
        }

        public void setNaSuiNo(Object NaSuiNo) {
            this.NaSuiNo = NaSuiNo;
        }

        public void setDefaultTax(double DefaultTax) {
            this.DefaultTax = DefaultTax;
        }

        public void setKaiPiaoType(Object KaiPiaoType) {
            this.KaiPiaoType = KaiPiaoType;
        }

        public void setKaiPiaoTypeName(Object KaiPiaoTypeName) {
            this.KaiPiaoTypeName = KaiPiaoTypeName;
        }

        public void setBank(Object Bank) {
            this.Bank = Bank;
        }

        public void setAccount(Object Account) {
            this.Account = Account;
        }

        public void setTZPH(Object TZPH) {
            this.TZPH = TZPH;
        }

        public void setTZNL(Object TZNL) {
            this.TZNL = TZNL;
        }

        public void setTZQD(Object TZQD) {
            this.TZQD = TZQD;
        }

        public void setTZNLVAL(Object TZNLVAL) {
            this.TZNLVAL = TZNLVAL;
        }

        public void setTZQDVAL(Object TZQDVAL) {
            this.TZQDVAL = TZQDVAL;
        }

        public void setCertifiyType(Object CertifiyType) {
            this.CertifiyType = CertifiyType;
        }

        public void setCertifiyNO(Object CertifiyNO) {
            this.CertifiyNO = CertifiyNO;
        }

        public void setDuty(Object Duty) {
            this.Duty = Duty;
        }

        public void setJJR(Object JJR) {
            this.JJR = JJR;
        }

        public void setQq(Object qq) {
            this.qq = qq;
        }

        public void setWeiXin(Object WeiXin) {
            this.WeiXin = WeiXin;
        }

        public void setOfficeTel(Object OfficeTel) {
            this.OfficeTel = OfficeTel;
        }

        public void setHomeTel(Object HomeTel) {
            this.HomeTel = HomeTel;
        }

        public void setPUserName(Object pUserName) {
            this.pUserName = pUserName;
        }

        public void setMarriType(Object MarriType) {
            this.MarriType = MarriType;
        }

        public void setContactName(Object ContactName) {
            this.ContactName = ContactName;
        }

        public void setContactTel(Object ContactTel) {
            this.ContactTel = ContactTel;
        }

        public void setRelaType(Object RelaType) {
            this.RelaType = RelaType;
        }

        public void setCall(Object Call) {
            this.Call = Call;
        }

        public void setDeptCode(Object DeptCode) {
            this.DeptCode = DeptCode;
        }

        public void setOwnerID(Object OwnerID) {
            this.OwnerID = OwnerID;
        }

        public void setBirthDay(Object BirthDay) {
            this.BirthDay = BirthDay;
        }

        public String getUserId() {
            return UserId;
        }

        public String getUserName() {
            return UserName;
        }

        public Object getUserType() {
            return UserType;
        }

        public Object getUserSex() {
            return UserSex;
        }

        public String getBirthDate() {
            return BirthDate;
        }

        public Object getValueEvaluate() {
            return ValueEvaluate;
        }

        public Object getCreditGrade() {
            return CreditGrade;
        }

        public Object getUserSort() {
            return UserSort;
        }

        public Object getUserSortName() {
            return UserSortName;
        }

        public Object getTrade() {
            return Trade;
        }

        public Object getTradeName() {
            return TradeName;
        }

        public Object getUserGrade() {
            return UserGrade;
        }

        public Object getUserGradeName() {
            return UserGradeName;
        }

        public Object getPersScale() {
            return PersScale;
        }

        public Object getPersScaleName() {
            return PersScaleName;
        }

        public Object getOrigin() {
            return Origin;
        }

        public Object getOriginName() {
            return OriginName;
        }

        public Object getMoment() {
            return Moment;
        }

        public Object getMomentName() {
            return MomentName;
        }

        public Object getCompany() {
            return Company;
        }

        public Object getAddress() {
            return Address;
        }

        public Object getPost() {
            return Post;
        }

        public String getPhone() {
            return Phone;
        }

        public Object getNetAddress() {
            return NetAddress;
        }

        public Object getFax() {
            return Fax;
        }

        public Object getEmail() {
            return Email;
        }

        public Object getDgAddress1() {
            return DgAddress1;
        }

        public Object getDgAddress2() {
            return DgAddress2;
        }

        public Object getRemark() {
            return Remark;
        }

        public Object getOperatorId() {
            return OperatorId;
        }

        public Object getOperatorName() {
            return OperatorName;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public Object getUpdateUserId() {
            return UpdateUserId;
        }

        public Object getUpdateUserName() {
            return UpdateUserName;
        }

        public String getUpdateDate() {
            return UpdateDate;
        }

        public Object getFuKuanFs() {
            return FuKuanFs;
        }

        public Object getFuKuanFsName() {
            return FuKuanFsName;
        }

        public double getZheKouLv() {
            return ZheKouLv;
        }

        public int getZhangQi() {
            return ZhangQi;
        }

        public double getXinYunEd() {
            return XinYunEd;
        }

        public Object getNaSuiNo() {
            return NaSuiNo;
        }

        public double getDefaultTax() {
            return DefaultTax;
        }

        public Object getKaiPiaoType() {
            return KaiPiaoType;
        }

        public Object getKaiPiaoTypeName() {
            return KaiPiaoTypeName;
        }

        public Object getBank() {
            return Bank;
        }

        public Object getAccount() {
            return Account;
        }

        public Object getTZPH() {
            return TZPH;
        }

        public Object getTZNL() {
            return TZNL;
        }

        public Object getTZQD() {
            return TZQD;
        }

        public Object getTZNLVAL() {
            return TZNLVAL;
        }

        public Object getTZQDVAL() {
            return TZQDVAL;
        }

        public Object getCertifiyType() {
            return CertifiyType;
        }

        public Object getCertifiyNO() {
            return CertifiyNO;
        }

        public Object getDuty() {
            return Duty;
        }

        public Object getJJR() {
            return JJR;
        }

        public Object getQq() {
            return qq;
        }

        public Object getWeiXin() {
            return WeiXin;
        }

        public Object getOfficeTel() {
            return OfficeTel;
        }

        public Object getHomeTel() {
            return HomeTel;
        }

        public Object getPUserName() {
            return pUserName;
        }

        public Object getMarriType() {
            return MarriType;
        }

        public Object getContactName() {
            return ContactName;
        }

        public Object getContactTel() {
            return ContactTel;
        }

        public Object getRelaType() {
            return RelaType;
        }

        public Object getCall() {
            return Call;
        }

        public Object getDeptCode() {
            return DeptCode;
        }

        public Object getOwnerID() {
            return OwnerID;
        }

        public Object getBirthDay() {
            return BirthDay;
        }
    }
}
