package com.crunchfinn.admin.application.dto;

import com.crunchfinn.admin.application.enums.*;
import com.crunchfinn.admin.common.enums.Gender;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ApplicationResponse {

    private Long id;
    private String applicationId;

    private List<ApplicationBankPartnerResponse> assignedPartners;

    // basic info
    private String name;
    private Byte age;
    private Gender gender;
    private String state;
    private String stateName;
    private String city;
    private String phoneNumber;
    private String email;

    // target info
    private String targetCountry;
    private String targetCountryName;
    private String targetUniversity;
    private CourseLevel courseLevel;
    private String courseName;
    private BigDecimal loanAmount;
    private String intake;

    private Short greScore;
    private Short ieltsScore;
    private Short pteScore;
    private Short toeflScore;
    private Boolean hasAdmit;

    // experience
    private Boolean hasWorkExperience;
    private Short experienceYears;
    private Boolean currentlyWorking;
    private BigDecimal income;
    private BigDecimal emi;

    // co-applicant
    private BigDecimal coapplicantIncome;
    private ApplicantNOE coapplicantNoe;
    private String coapplicantState;
    private String coapplicantStateName;
    private String coapplicantCity;
    private BigDecimal coapplicantEmi;

    // collateral
    private Boolean hasCollateral;
    private CollateralCategory collateralCategory;
    private BigDecimal collateralValue;
    private String collateralCity;
    private String collateralPincode;

    // academics
    private String academicUniversity;
    private String academicCourse;
    private BigDecimal cgpa;
    private Byte backlogs;

    // admin
    private ApplicationStatus status;
    private ApplicationSource source;
    private String adminNotes;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // getters & setters
    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTargetCountry() {
        return targetCountry;
    }

    public void setTargetCountry(String targetCountry) {
        this.targetCountry = targetCountry;
    }

    public String getTargetUniversity() {
        return targetUniversity;
    }

    public void setTargetUniversity(String targetUniversity) {
        this.targetUniversity = targetUniversity;
    }

    public CourseLevel getCourseLevel() {
        return courseLevel;
    }

    public void setCourseLevel(CourseLevel courseLevel) {
        this.courseLevel = courseLevel;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getIntake() {
        return intake;
    }

    public void setIntake(String intake) {
        this.intake = intake;
    }

    public Short getGreScore() {
        return greScore;
    }

    public void setGreScore(Short greScore) {
        this.greScore = greScore;
    }

    public Short getIeltsScore() {
        return ieltsScore;
    }

    public void setIeltsScore(Short ieltsScore) {
        this.ieltsScore = ieltsScore;
    }

    public Short getPteScore() {
        return pteScore;
    }

    public void setPteScore(Short pteScore) {
        this.pteScore = pteScore;
    }

    public Short getToeflScore() {
        return toeflScore;
    }

    public void setToeflScore(Short toeflScore) {
        this.toeflScore = toeflScore;
    }

    public Boolean getHasAdmit() {
        return hasAdmit;
    }

    public void setHasAdmit(Boolean hasAdmit) {
        this.hasAdmit = hasAdmit;
    }

    public Boolean getHasWorkExperience() {
        return hasWorkExperience;
    }

    public void setHasWorkExperience(Boolean hasWorkExperience) {
        this.hasWorkExperience = hasWorkExperience;
    }

    public Short getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(Short experienceYears) {
        this.experienceYears = experienceYears;
    }

    public Boolean getCurrentlyWorking() {
        return currentlyWorking;
    }

    public void setCurrentlyWorking(Boolean currentlyWorking) {
        this.currentlyWorking = currentlyWorking;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public BigDecimal getEmi() {
        return emi;
    }

    public void setEmi(BigDecimal emi) {
        this.emi = emi;
    }

    public BigDecimal getCoapplicantIncome() {
        return coapplicantIncome;
    }

    public void setCoapplicantIncome(BigDecimal coapplicantIncome) {
        this.coapplicantIncome = coapplicantIncome;
    }

    public ApplicantNOE getCoapplicantNoe() {
        return coapplicantNoe;
    }

    public void setCoapplicantNoe(ApplicantNOE coapplicantNoe) {
        this.coapplicantNoe = coapplicantNoe;
    }

    public String getCoapplicantState() {
        return coapplicantState;
    }

    public void setCoapplicantState(String coapplicantState) {
        this.coapplicantState = coapplicantState;
    }

    public String getCoapplicantCity() {
        return coapplicantCity;
    }

    public void setCoapplicantCity(String coapplicantCity) {
        this.coapplicantCity = coapplicantCity;
    }

    public BigDecimal getCoapplicantEmi() {
        return coapplicantEmi;
    }

    public void setCoapplicantEmi(BigDecimal coapplicantEmi) {
        this.coapplicantEmi = coapplicantEmi;
    }

    public Boolean getHasCollateral() {
        return hasCollateral;
    }

    public void setHasCollateral(Boolean hasCollateral) {
        this.hasCollateral = hasCollateral;
    }

    public CollateralCategory getCollateralCategory() {
        return collateralCategory;
    }

    public void setCollateralCategory(CollateralCategory collateralCategory) {
        this.collateralCategory = collateralCategory;
    }

    public BigDecimal getCollateralValue() {
        return collateralValue;
    }

    public void setCollateralValue(BigDecimal collateralValue) {
        this.collateralValue = collateralValue;
    }

    public String getCollateralCity() {
        return collateralCity;
    }

    public void setCollateralCity(String collateralCity) {
        this.collateralCity = collateralCity;
    }

    public String getCollateralPincode() {
        return collateralPincode;
    }

    public void setCollateralPincode(String collateralPincode) {
        this.collateralPincode = collateralPincode;
    }

    public String getAcademicUniversity() {
        return academicUniversity;
    }

    public void setAcademicUniversity(String academicUniversity) {
        this.academicUniversity = academicUniversity;
    }

    public String getAcademicCourse() {
        return academicCourse;
    }

    public void setAcademicCourse(String academicCourse) {
        this.academicCourse = academicCourse;
    }

    public BigDecimal getCgpa() {
        return cgpa;
    }

    public void setCgpa(BigDecimal cgpa) {
        this.cgpa = cgpa;
    }

    public Byte getBacklogs() {
        return backlogs;
    }

    public void setBacklogs(Byte backlogs) {
        this.backlogs = backlogs;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public ApplicationSource getSource() {
        return source;
    }

    public void setSource(ApplicationSource source) {
        this.source = source;
    }

    public String getAdminNotes() {
        return adminNotes;
    }

    public void setAdminNotes(String adminNotes) {
        this.adminNotes = adminNotes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "ApplicationResponse{" +
                "applicationId='" + applicationId + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", targetCountry='" + targetCountry + '\'' +
                ", targetUniversity='" + targetUniversity + '\'' +
                ", courseLevel=" + courseLevel +
                ", courseName='" + courseName + '\'' +
                ", loanAmount=" + loanAmount +
                ", intake='" + intake + '\'' +
                ", greScore=" + greScore +
                ", ieltsScore=" + ieltsScore +
                ", pteScore=" + pteScore +
                ", toeflScore=" + toeflScore +
                ", hasAdmit=" + hasAdmit +
                ", hasWorkExperience=" + hasWorkExperience +
                ", experienceYears=" + experienceYears +
                ", currentlyWorking=" + currentlyWorking +
                ", income=" + income +
                ", emi=" + emi +
                ", coapplicantIncome=" + coapplicantIncome +
                ", coapplicantNoe=" + coapplicantNoe +
                ", coapplicantState='" + coapplicantState + '\'' +
                ", coapplicantCity='" + coapplicantCity + '\'' +
                ", coapplicantEmi=" + coapplicantEmi +
                ", hasCollateral=" + hasCollateral +
                ", collateralCategory=" + collateralCategory +
                ", collateralValue=" + collateralValue +
                ", collateralCity='" + collateralCity + '\'' +
                ", collateralPincode='" + collateralPincode + '\'' +
                ", academicUniversity='" + academicUniversity + '\'' +
                ", academicCourse='" + academicCourse + '\'' +
                ", cgpa=" + cgpa +
                ", backlogs=" + backlogs +
                ", status=" + status +
                ", source=" + source +
                ", adminNotes='" + adminNotes + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getTargetCountryName() {
        return targetCountryName;
    }

    public void setTargetCountryName(String targetCountryName) {
        this.targetCountryName = targetCountryName;
    }

    public String getCoapplicantStateName() {
        return coapplicantStateName;
    }

    public void setCoapplicantStateName(String coapplicantStateName) {
        this.coapplicantStateName = coapplicantStateName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ApplicationBankPartnerResponse> getAssignedPartners() {
        return assignedPartners;
    }

    public void setAssignedPartners(List<ApplicationBankPartnerResponse> assignedPartners) {
        this.assignedPartners = assignedPartners;
    }
}
