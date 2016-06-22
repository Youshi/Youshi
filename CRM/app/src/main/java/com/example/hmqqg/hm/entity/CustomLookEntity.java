package com.example.hmqqg.hm.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/3/4.
 */
public class CustomLookEntity {

    /**
     * stamess : 获取客户信息成功！
     * staval : 1
     */

    private List<StatusEntity> status;
    /**
     * UserId : KH0900002
     * UserName : 四季沐歌太阳能
     * UserType :
     * UserSex :
     * BirthDate :
     * ValueEvaluate : null
     * CreditGrade : null
     * UserSort : null
     * UserSortName : 潜在
     * Trade : null
     * TradeName : 服务业
     * UserGrade : null
     * UserGradeName : 较好
     * PersScale : null
     * PersScaleName : 50-200人
     * Origin : null
     * OriginName : 促销活动
     * Moment : null
     * MomentName : null
     * Company : null
     * Address : null
     * Post : null
     * Phone :
     * NetAddress : null
     * Fax :
     * Email :
     * DgAddress1 : null
     * DgAddress2 : null
     * Remark : null
     * OperatorId : null
     * OperatorName : 管理员
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
     * TZPH :
     * TZNL :
     * TZQD :
     * CertifiyType :
     * CertifiyNO :
     * Duty : null
     * JJR : null
     * qq :
     * WeiXin :
     * OfficeTel :
     * HomeTel :
     * pUserName : null
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
        private String UserType;
        private String UserSex;
        private String BirthDate;
        private Object ValueEvaluate;
        private Object CreditGrade;
        private Object UserSort;
        private String UserSortName;
        private Object Trade;
        private String TradeName;
        private Object UserGrade;
        private String UserGradeName;
        private Object PersScale;
        private String PersScaleName;
        private Object Origin;
        private String OriginName;
        private Object Moment;
        private Object MomentName;
        private Object Company;
        private Object Address;
        private Object Post;
        private String Phone;
        private Object NetAddress;
        private String Fax;
        private String Email;
        private Object DgAddress1;
        private Object DgAddress2;
        private Object Remark;
        private Object OperatorId;
        private String OperatorName;
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
        private String TZPH;
        private String TZNL;
        private String TZQD;
        private String CertifiyType;
        private String CertifiyNO;
        private Object Duty;
        private Object JJR;
        private String qq;
        private String WeiXin;
        private String OfficeTel;
        private String HomeTel;
        private Object pUserName;

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public void setUserType(String UserType) {
            this.UserType = UserType;
        }

        public void setUserSex(String UserSex) {
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

        public void setUserSortName(String UserSortName) {
            this.UserSortName = UserSortName;
        }

        public void setTrade(Object Trade) {
            this.Trade = Trade;
        }

        public void setTradeName(String TradeName) {
            this.TradeName = TradeName;
        }

        public void setUserGrade(Object UserGrade) {
            this.UserGrade = UserGrade;
        }

        public void setUserGradeName(String UserGradeName) {
            this.UserGradeName = UserGradeName;
        }

        public void setPersScale(Object PersScale) {
            this.PersScale = PersScale;
        }

        public void setPersScaleName(String PersScaleName) {
            this.PersScaleName = PersScaleName;
        }

        public void setOrigin(Object Origin) {
            this.Origin = Origin;
        }

        public void setOriginName(String OriginName) {
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

        public void setFax(String Fax) {
            this.Fax = Fax;
        }

        public void setEmail(String Email) {
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

        public void setOperatorName(String OperatorName) {
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

        public void setTZPH(String TZPH) {
            this.TZPH = TZPH;
        }

        public void setTZNL(String TZNL) {
            this.TZNL = TZNL;
        }

        public void setTZQD(String TZQD) {
            this.TZQD = TZQD;
        }

        public void setCertifiyType(String CertifiyType) {
            this.CertifiyType = CertifiyType;
        }

        public void setCertifiyNO(String CertifiyNO) {
            this.CertifiyNO = CertifiyNO;
        }

        public void setDuty(Object Duty) {
            this.Duty = Duty;
        }

        public void setJJR(Object JJR) {
            this.JJR = JJR;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public void setWeiXin(String WeiXin) {
            this.WeiXin = WeiXin;
        }

        public void setOfficeTel(String OfficeTel) {
            this.OfficeTel = OfficeTel;
        }

        public void setHomeTel(String HomeTel) {
            this.HomeTel = HomeTel;
        }

        public void setPUserName(Object pUserName) {
            this.pUserName = pUserName;
        }

        public String getUserId() {
            return UserId;
        }

        public String getUserName() {
            return UserName;
        }

        public String getUserType() {
            return UserType;
        }

        public String getUserSex() {
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

        public String getUserSortName() {
            return UserSortName;
        }

        public Object getTrade() {
            return Trade;
        }

        public String getTradeName() {
            return TradeName;
        }

        public Object getUserGrade() {
            return UserGrade;
        }

        public String getUserGradeName() {
            return UserGradeName;
        }

        public Object getPersScale() {
            return PersScale;
        }

        public String getPersScaleName() {
            return PersScaleName;
        }

        public Object getOrigin() {
            return Origin;
        }

        public String getOriginName() {
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

        public String getFax() {
            return Fax;
        }

        public String getEmail() {
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

        public String getOperatorName() {
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

        public String getTZPH() {
            return TZPH;
        }

        public String getTZNL() {
            return TZNL;
        }

        public String getTZQD() {
            return TZQD;
        }

        public String getCertifiyType() {
            return CertifiyType;
        }

        public String getCertifiyNO() {
            return CertifiyNO;
        }

        public Object getDuty() {
            return Duty;
        }

        public Object getJJR() {
            return JJR;
        }

        public String getQq() {
            return qq;
        }

        public String getWeiXin() {
            return WeiXin;
        }

        public String getOfficeTel() {
            return OfficeTel;
        }

        public String getHomeTel() {
            return HomeTel;
        }

        public Object getPUserName() {
            return pUserName;
        }
    }
}
