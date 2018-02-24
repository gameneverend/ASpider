package com.less.aspider.samples.bean;

import com.less.aspider.dao.Column;
import com.less.aspider.dao.Table;

/**
 * Created by deeper on 2018/2/22.
 */

@Table(value = "lagou_new")
public class LagouDB {

    @Column(value = "id", columnDefinition = "int primary key auto_increment")
    private int id;
    @Column(value = "positionId", columnDefinition = "varchar(30) not null unique")
    private String positionId;
    @Column
    private String positionName;
    @Column
    private String city;
    @Column
    private String createTime;
    @Column
    private String salary;
    @Column
    private String companyId;
    @Column
    private String companyLogo;
    @Column
    private String companyName;
    @Column
    private String companyFullName;
    @Column
    private String field;
    @Column
    private String devtrend;
    @Column
    private String personCount;
    @Column
    private String homePage;
    @Column
    private String experience;
    @Column
    private String edu;
    @Column
    private String jobType;
    @Column(columnDefinition = "text")
    private String jobAllure;
    @Column(columnDefinition = "text")
    private String jobDesc;
    @Column
    private String jobAddr;
    @Column
    private String investmentAgency;

    public LagouDB(){}
    public LagouDB(String positionId, String positionName, String city, String createTime, String salary, String companyId, String companyLogo, String companyName, String companyFullName, String field, String devtrend, String personCount, String homePage, String experience, String edu, String jobType, String jobAllure, String jobDesc, String jobAddr) {
        this.positionId = positionId;
        this.positionName = positionName;
        this.city = city;
        this.createTime = createTime;
        this.salary = salary;
        this.companyId = companyId;
        this.companyLogo = companyLogo;
        this.companyName = companyName;
        this.companyFullName = companyFullName;
        this.field = field;
        this.devtrend = devtrend;
        this.personCount = personCount;
        this.homePage = homePage;
        this.experience = experience;
        this.edu = edu;
        this.jobType = jobType;
        this.jobAllure = jobAllure;
        this.jobDesc = jobDesc;
        this.jobAddr = jobAddr;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyFullName() {
        return companyFullName;
    }

    public void setCompanyFullName(String companyFullName) {
        this.companyFullName = companyFullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getDevtrend() {
        return devtrend;
    }

    public void setDevtrend(String devtrend) {
        this.devtrend = devtrend;
    }

    public String getPersonCount() {
        return personCount;
    }

    public void setPersonCount(String personCount) {
        this.personCount = personCount;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getJobAllure() {
        return jobAllure;
    }

    public void setJobAllure(String jobAllure) {
        this.jobAllure = jobAllure;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public String getJobAddr() {
        return jobAddr;
    }

    public void setJobAddr(String jobAddr) {
        this.jobAddr = jobAddr;
    }

    public String getInvestmentAgency() {
        return investmentAgency;
    }

    public void setInvestmentAgency(String investmentAgency) {
        this.investmentAgency = investmentAgency;
    }

    @Override
    public String toString() {
        return "LagouDB{" +
                "id=" + id +
                ", positionId='" + positionId + '\'' +
                ", positionName='" + positionName + '\'' +
                ", city='" + city + '\'' +
                ", createTime='" + createTime + '\'' +
                ", salary='" + salary + '\'' +
                ", companyId='" + companyId + '\'' +
                ", companyLogo='" + companyLogo + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyFullName='" + companyFullName + '\'' +
                ", field='" + field + '\'' +
                ", devtrend='" + devtrend + '\'' +
                ", personCount='" + personCount + '\'' +
                ", homePage='" + homePage + '\'' +
                ", experience='" + experience + '\'' +
                ", edu='" + edu + '\'' +
                ", jobType='" + jobType + '\'' +
                ", jobAllure='" + jobAllure + '\'' +
                ", jobDesc='" + jobDesc + '\'' +
                ", jobAddr='" + jobAddr + '\'' +
                ", investmentAgency='" + investmentAgency + '\'' +
                '}';
    }
}
