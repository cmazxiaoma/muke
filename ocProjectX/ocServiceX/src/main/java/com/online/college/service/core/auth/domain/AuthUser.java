package com.online.college.service.core.auth.domain;

import java.util.Date;
import java.util.Set;

import com.online.college.common.orm.BaseEntity;
import com.online.college.common.web.auth.SessionUser;

/**
 *
 * @Description: 系统用户
 * @author cmazxiaoma
 * @date 2018-02-08 11:04
 * @version V1.0
 */
public class AuthUser extends BaseEntity implements SessionUser {

    private static final long serialVersionUID = -6616392725819110582L;

    /**
     * 登录用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 头像
     */
    private String header;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 状态： 待审核(0), 审核通过(1), 默认(2)， 审核未通过(3), 禁用(5)
     */
    private Integer status;

    /**
     * 生日日期
     */
    private Date birthday;

    /**
     * 学历： 大专， 本科， 硕士， 博士， 博士后
     */
    private String education;

    /**
     * 大学编号
     */
    private String collegeCode;

    /**
     * 大学名称
     */
    private String collegeName;

    /**
     * 资格证书编号
     */
    private String certNo;

    /**
     * 头衔
     */
    private String title;

    /**
     * 签名
     */
    private String sign;

    /**
     * 微信公众号openid
     */
    private String openId;

    /**
     * 微信号id
     */
    private String wechatId;

    /**
     * qq号
     */
    private String qq;

    /**
     * 最后一次登录时间
     */
    private Date loginTime;

    /**
     * 最后一次登录ip
     */
    private String ip;

    /**
     * 所在省份
     */
    private String province;

    /**
     * 所在城市
     */
    private String city;

    /**
     * 所在地区
     */
    private String district;

    /**
     * 推荐权重
     */
    private Integer weight;

    /**
     * 扩展字段： 微信昵称
     */
    private String nickname;

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return this.username;
    }

    @Override
    public Long getUserId() {
        // TODO Auto-generated method stub
        return this.getId();
    }

    @Override
    public Set<String> getPermissions() {
        // TODO Auto-generated method stub
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getCollegeCode() {
        return collegeCode;
    }

    public void setCollegeCode(String collegeCode) {
        this.collegeCode = collegeCode;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
