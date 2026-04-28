package com.crunchfinn.admin.application.entity;

import com.crunchfinn.admin.application.enums.*;
import com.crunchfinn.admin.common.enums.Gender;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "application_details")
public class ApplicationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "application_id", unique = true, length = 20)
    private String applicationId;


    /* Basic Info */
    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "age", columnDefinition = "TINYINT")
    private Byte age;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 10)
    private Gender gender;

    @Column(name = "state", length = 2)
    private String state;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "phone_number", length = 11)
    private String phoneNumber;

    @Column(name = "email", length = 100)
    private String email;

    /* Target Info */
    @Column(name = "target_country", length = 2)
    private String targetCountry;

    @Column(name = "target_university", length = 150)
    private String targetUniversity;

    @Enumerated(EnumType.STRING)
    @Column(name = "course_level", length = 15)
    private CourseLevel courseLevel;

    @Column(name = "course_name", length = 150)
    private String courseName;

    @Column(name = "loan_amount", precision = 12, scale = 2)
    private BigDecimal loanAmount;

    @Column(name = "intake", length = 30)
    private String intake;

    @Column(name = "gre_score", columnDefinition = "SMALLINT")
    private Short greScore;

    @Column(name = "ielts_score", columnDefinition = "SMALLINT")
    private Short ieltsScore;

    @Column(name = "pte_score", columnDefinition = "SMALLINT")
    private Short pteScore;

    @Column(name = "toefl_score", columnDefinition = "SMALLINT")
    private Short toeflScore;

    @Column(name = "has_admit")
    private Boolean hasAdmit;

    /* Experience Info */
    @Column(name = "has_work_experience")
    private Boolean hasWorkExperience;

    @Column(name = "experience_years", columnDefinition = "SMALLINT")
    private Short experienceYears;

    @Column(name = "is_currently_working")
    private Boolean currentlyWorking;

    @Column(name = "income", precision = 12, scale = 2)
    private BigDecimal income;

    @Column(name = "emi", precision = 12, scale = 2)
    private BigDecimal emi;

    /* Co-Applicant Info */
    @Column(name = "coapplicant_income", precision = 12, scale = 2)
    private BigDecimal coapplicantIncome;

    @Enumerated(EnumType.STRING)
    @Column(name = "coapplicant_noe", length = 30)
    private ApplicantNOE coapplicantNoe;

    @Column(name = "coapplicant_state", length = 2)
    private String coapplicantState;

    @Column(name = "coapplicant_city", length = 100)
    private String coapplicantCity;

    @Column(name = "coapplicant_emi", precision = 12, scale = 2)
    private BigDecimal coapplicantEmi;

    /* Collateral Info */
    @Column(name = "has_collateral")
    private Boolean hasCollateral;

    @Enumerated(EnumType.STRING)
    @Column(name = "collateral_category", length = 30)
    private CollateralCategory collateralCategory;

    @Column(name = "collateral_value", precision = 15, scale = 2)
    private BigDecimal collateralValue;

    @Column(name = "collateral_city", length = 100)
    private String collateralCity;

    @Column(name = "collateral_pincode", length = 6)
    private String collateralPincode;

    /* Academics Info */
    @Column(name = "academic_university", length = 150)
    private String academicUniversity;

    @Column(name = "academic_course", length = 150)
    private String academicCourse;

    @Column(name = "cgpa", precision = 4, scale = 2)
    private BigDecimal cgpa;

    @Column(name = "backlogs", columnDefinition = "TINYINT")
    private Byte backlogs;

    /* Admin Info */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    private ApplicationStatus status = ApplicationStatus.NEW;

    @Enumerated(EnumType.STRING)
    @Column(name = "source", length = 20)
    private ApplicationSource source = ApplicationSource.ADMIN;

    @Column(name = "admin_notes", columnDefinition = "TEXT")
    private String adminNotes;

    /* Timestamps */
    @Column(name = "created_at", updatable = false, insertable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;

    /* Lifecycle hooks */
    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    /* Getters and Setters */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        return "applicationDetails{" +
                "id=" + id +
                ", applicationId='" + applicationId + '\'' +
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
}
