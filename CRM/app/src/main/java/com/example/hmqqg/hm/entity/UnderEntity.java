package com.example.hmqqg.hm.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/5/23.
 */
public class UnderEntity {


    /**
     * stamess : 下级客户信息获取成功！
     * staval : 1
     */

    private List<StatusEntity> status;
    /**
     * Row : 1
     * UserId : KH16000088
     * UserName : 陈小二
     * UserType : 1
     * UserSex : XBFL01
     * UserSexName : 男
     * BirthDate : 1986-08-08
     * CertifiyType : ZJ01
     * certifiyTypeName : 身份证
     * CertifiyNO : 456712198608084411
     * UserSort : ZL003
     * UserSortName : 关单
     * Trade : KHHY001
     * TradeName : 工业
     * UserGrade : DJ001
     * HomeTel : null
     * OfficeTel :
     * secretphone : 156****9898
     * UserGradeName : 强烈
     * PersScale : null
     * PersScaleName : null
     * Origin : LY001
     * OriginName : 讲座
     * Moment : null
     * MomentName : null
     * Company : null
     * Address : 997949@qq.com
     * Post : null
     * Phone : 15678789898
     * NetAddress : null
     * Fax :
     * Email : 997949@qq.com
     * Weixin :
     * QQ : null
     * Remark : null
     * TZPHVAL : null
     * Ownerid : OP10117
     * Name : 烟台测试
     * TZPH : null
     * TZNLVAL : null
     * TZNL : null
     * TZQDVAL : null
     * TZQD : null
     * OperatorId : OP10117
     * OperatorName : 烟台测试
     * CreateDate : 2016-05-06T11:36:15.167
     * UpdateDate : 2016-05-09T11:34:13.033
     * FuKuanFs : null
     * FuKuanFsName : null
     * ZheKouLv : null
     * ZhangQi : 0
     * NaSuiNo : null
     * KaiPiaoType : null
     * KaiPiaoTypeName : null
     * Bank : null
     * Account : null
     * DefaultTax : 0.0
     * MarriType : HY001
     * MarriTypeName : 已婚
     * XinYunEd : 0.0
     * Money : null
     * cDate : 2016-05-06
     * uDate : 2016-05-09
     * ContactName : 陈总
     * ContactTel : 18645456565
     * RelaType : RYGX01
     * RelaName : 朋友
     * Call : 陈总
     * DeptName : null
     * DeptCode : null
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
        private int Row;
        private String UserId;
        private String UserName;
        private String UserType;
        private String UserSex;
        private String UserSexName;
        private String BirthDate;
        private String CertifiyType;
        private String certifiyTypeName;
        private String CertifiyNO;
        private String UserSort;
        private String UserSortName;
        private String Trade;
        private String TradeName;
        private String UserGrade;
        private Object HomeTel;
        private String OfficeTel;
        private String secretphone;
        private String UserGradeName;
        private Object PersScale;
        private Object PersScaleName;
        private String Origin;
        private String OriginName;
        private Object Moment;
        private Object MomentName;
        private Object Company;
        private String Address;
        private Object Post;
        private String Phone;
        private Object NetAddress;
        private String Fax;
        private String Email;
        private String Weixin;
        private Object QQ;
        private Object Remark;
        private Object TZPHVAL;
        private String Ownerid;
        private String Name;
        private Object TZPH;
        private Object TZNLVAL;
        private Object TZNL;
        private Object TZQDVAL;
        private Object TZQD;
        private String OperatorId;
        private String OperatorName;
        private String CreateDate;
        private String UpdateDate;
        private Object FuKuanFs;
        private Object FuKuanFsName;
        private Object ZheKouLv;
        private int ZhangQi;
        private Object NaSuiNo;
        private Object KaiPiaoType;
        private Object KaiPiaoTypeName;
        private Object Bank;
        private Object Account;
        private double DefaultTax;
        private String MarriType;
        private String MarriTypeName;
        private double XinYunEd;
        private Object Money;
        private String cDate;
        private String uDate;
        private String ContactName;
        private String ContactTel;
        private String RelaType;
        private String RelaName;
        private String Call;
        private Object DeptName;
        private Object DeptCode;

        public void setRow(int Row) {
            this.Row = Row;
        }

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

        public void setUserSexName(String UserSexName) {
            this.UserSexName = UserSexName;
        }

        public void setBirthDate(String BirthDate) {
            this.BirthDate = BirthDate;
        }

        public void setCertifiyType(String CertifiyType) {
            this.CertifiyType = CertifiyType;
        }

        public void setCertifiyTypeName(String certifiyTypeName) {
            this.certifiyTypeName = certifiyTypeName;
        }

        public void setCertifiyNO(String CertifiyNO) {
            this.CertifiyNO = CertifiyNO;
        }

        public void setUserSort(String UserSort) {
            this.UserSort = UserSort;
        }

        public void setUserSortName(String UserSortName) {
            this.UserSortName = UserSortName;
        }

        public void setTrade(String Trade) {
            this.Trade = Trade;
        }

        public void setTradeName(String TradeName) {
            this.TradeName = TradeName;
        }

        public void setUserGrade(String UserGrade) {
            this.UserGrade = UserGrade;
        }

        public void setHomeTel(Object HomeTel) {
            this.HomeTel = HomeTel;
        }

        public void setOfficeTel(String OfficeTel) {
            this.OfficeTel = OfficeTel;
        }

        public void setSecretphone(String secretphone) {
            this.secretphone = secretphone;
        }

        public void setUserGradeName(String UserGradeName) {
            this.UserGradeName = UserGradeName;
        }

        public void setPersScale(Object PersScale) {
            this.PersScale = PersScale;
        }

        public void setPersScaleName(Object PersScaleName) {
            this.PersScaleName = PersScaleName;
        }

        public void setOrigin(String Origin) {
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

        public void setAddress(String Address) {
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

        public void setWeixin(String Weixin) {
            this.Weixin = Weixin;
        }

        public void setQQ(Object QQ) {
            this.QQ = QQ;
        }

        public void setRemark(Object Remark) {
            this.Remark = Remark;
        }

        public void setTZPHVAL(Object TZPHVAL) {
            this.TZPHVAL = TZPHVAL;
        }

        public void setOwnerid(String Ownerid) {
            this.Ownerid = Ownerid;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public void setTZPH(Object TZPH) {
            this.TZPH = TZPH;
        }

        public void setTZNLVAL(Object TZNLVAL) {
            this.TZNLVAL = TZNLVAL;
        }

        public void setTZNL(Object TZNL) {
            this.TZNL = TZNL;
        }

        public void setTZQDVAL(Object TZQDVAL) {
            this.TZQDVAL = TZQDVAL;
        }

        public void setTZQD(Object TZQD) {
            this.TZQD = TZQD;
        }

        public void setOperatorId(String OperatorId) {
            this.OperatorId = OperatorId;
        }

        public void setOperatorName(String OperatorName) {
            this.OperatorName = OperatorName;
        }

        public void setCreateDate(String CreateDate) {
            this.CreateDate = CreateDate;
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

        public void setZheKouLv(Object ZheKouLv) {
            this.ZheKouLv = ZheKouLv;
        }

        public void setZhangQi(int ZhangQi) {
            this.ZhangQi = ZhangQi;
        }

        public void setNaSuiNo(Object NaSuiNo) {
            this.NaSuiNo = NaSuiNo;
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

        public void setDefaultTax(double DefaultTax) {
            this.DefaultTax = DefaultTax;
        }

        public void setMarriType(String MarriType) {
            this.MarriType = MarriType;
        }

        public void setMarriTypeName(String MarriTypeName) {
            this.MarriTypeName = MarriTypeName;
        }

        public void setXinYunEd(double XinYunEd) {
            this.XinYunEd = XinYunEd;
        }

        public void setMoney(Object Money) {
            this.Money = Money;
        }

        public void setCDate(String cDate) {
            this.cDate = cDate;
        }

        public void setUDate(String uDate) {
            this.uDate = uDate;
        }

        public void setContactName(String ContactName) {
            this.ContactName = ContactName;
        }

        public void setContactTel(String ContactTel) {
            this.ContactTel = ContactTel;
        }

        public void setRelaType(String RelaType) {
            this.RelaType = RelaType;
        }

        public void setRelaName(String RelaName) {
            this.RelaName = RelaName;
        }

        public void setCall(String Call) {
            this.Call = Call;
        }

        public void setDeptName(Object DeptName) {
            this.DeptName = DeptName;
        }

        public void setDeptCode(Object DeptCode) {
            this.DeptCode = DeptCode;
        }

        public int getRow() {
            return Row;
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

        public String getUserSexName() {
            return UserSexName;
        }

        public String getBirthDate() {
            return BirthDate;
        }

        public String getCertifiyType() {
            return CertifiyType;
        }

        public String getCertifiyTypeName() {
            return certifiyTypeName;
        }

        public String getCertifiyNO() {
            return CertifiyNO;
        }

        public String getUserSort() {
            return UserSort;
        }

        public String getUserSortName() {
            return UserSortName;
        }

        public String getTrade() {
            return Trade;
        }

        public String getTradeName() {
            return TradeName;
        }

        public String getUserGrade() {
            return UserGrade;
        }

        public Object getHomeTel() {
            return HomeTel;
        }

        public String getOfficeTel() {
            return OfficeTel;
        }

        public String getSecretphone() {
            return secretphone;
        }

        public String getUserGradeName() {
            return UserGradeName;
        }

        public Object getPersScale() {
            return PersScale;
        }

        public Object getPersScaleName() {
            return PersScaleName;
        }

        public String getOrigin() {
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

        public String getAddress() {
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

        public String getWeixin() {
            return Weixin;
        }

        public Object getQQ() {
            return QQ;
        }

        public Object getRemark() {
            return Remark;
        }

        public Object getTZPHVAL() {
            return TZPHVAL;
        }

        public String getOwnerid() {
            return Ownerid;
        }

        public String getName() {
            return Name;
        }

        public Object getTZPH() {
            return TZPH;
        }

        public Object getTZNLVAL() {
            return TZNLVAL;
        }

        public Object getTZNL() {
            return TZNL;
        }

        public Object getTZQDVAL() {
            return TZQDVAL;
        }

        public Object getTZQD() {
            return TZQD;
        }

        public String getOperatorId() {
            return OperatorId;
        }

        public String getOperatorName() {
            return OperatorName;
        }

        public String getCreateDate() {
            return CreateDate;
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

        public Object getZheKouLv() {
            return ZheKouLv;
        }

        public int getZhangQi() {
            return ZhangQi;
        }

        public Object getNaSuiNo() {
            return NaSuiNo;
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

        public double getDefaultTax() {
            return DefaultTax;
        }

        public String getMarriType() {
            return MarriType;
        }

        public String getMarriTypeName() {
            return MarriTypeName;
        }

        public double getXinYunEd() {
            return XinYunEd;
        }

        public Object getMoney() {
            return Money;
        }

        public String getCDate() {
            return cDate;
        }

        public String getUDate() {
            return uDate;
        }

        public String getContactName() {
            return ContactName;
        }

        public String getContactTel() {
            return ContactTel;
        }

        public String getRelaType() {
            return RelaType;
        }

        public String getRelaName() {
            return RelaName;
        }

        public String getCall() {
            return Call;
        }

        public Object getDeptName() {
            return DeptName;
        }

        public Object getDeptCode() {
            return DeptCode;
        }
    }
}
