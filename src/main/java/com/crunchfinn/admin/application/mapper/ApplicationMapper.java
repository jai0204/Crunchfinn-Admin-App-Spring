package com.crunchfinn.admin.application.mapper;

import com.crunchfinn.admin.application.dto.*;
import com.crunchfinn.admin.application.entity.ApplicationBankPartner;
import com.crunchfinn.admin.application.entity.ApplicationDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationMapper {

    public ApplicationDetails toEntity(CreateApplicationRequest request) {
        ApplicationDetails application = new ApplicationDetails();

        application.setName(request.getName());
        application.setAge(request.getAge());
        application.setGender(request.getGender());
        application.setState(request.getState());
        application.setCity(request.getCity());
        application.setPhoneNumber(request.getPhoneNumber());
        application.setEmail(request.getEmail());

        application.setTargetCountry(request.getTargetCountry());
        application.setTargetUniversity(request.getTargetUniversity());
        application.setCourseLevel(request.getCourseLevel());
        application.setCourseName(request.getCourseName());
        application.setLoanAmount(request.getLoanAmount());
        application.setIntake(request.getIntake());

        application.setGreScore(request.getGreScore());
        application.setIeltsScore(request.getIeltsScore());
        application.setPteScore(request.getPteScore());
        application.setToeflScore(request.getToeflScore());
        application.setHasAdmit(request.getHasAdmit());

        application.setHasWorkExperience(request.getHasWorkExperience());
        application.setExperienceYears(request.getExperienceYears());
        application.setCurrentlyWorking(request.getCurrentlyWorking());
        application.setIncome(request.getIncome());
        application.setEmi(request.getEmi());

        application.setCoapplicantIncome(request.getCoapplicantIncome());
        application.setCoapplicantNoe(request.getCoapplicantNoe());
        application.setCoapplicantState(request.getCoapplicantState());
        application.setCoapplicantCity(request.getCoapplicantCity());
        application.setCoapplicantEmi(request.getCoapplicantEmi());

        application.setHasCollateral(request.getHasCollateral());
        application.setCollateralCategory(request.getCollateralCategory());
        application.setCollateralValue(request.getCollateralValue());
        application.setCollateralCity(request.getCollateralCity());
        application.setCollateralPincode(request.getCollateralPincode());

        application.setAcademicUniversity(request.getAcademicUniversity());
        application.setAcademicCourse(request.getAcademicCourse());
        application.setCgpa(request.getCgpa());
        application.setBacklogs(request.getBacklogs());

        return application;
    }

    public void updateEntity(ApplicationDetails application, UpdateApplicationRequest request) {

        application.setName(request.getName());
        application.setAge(request.getAge());
        application.setGender(request.getGender());
        application.setState(request.getState());
        application.setCity(request.getCity());
        application.setPhoneNumber(request.getPhoneNumber());
        application.setEmail(request.getEmail());

        application.setTargetCountry(request.getTargetCountry());
        application.setTargetUniversity(request.getTargetUniversity());
        application.setCourseLevel(request.getCourseLevel());
        application.setCourseName(request.getCourseName());
        application.setLoanAmount(request.getLoanAmount());
        application.setIntake(request.getIntake());

        application.setGreScore(request.getGreScore());
        application.setIeltsScore(request.getIeltsScore());
        application.setPteScore(request.getPteScore());
        application.setToeflScore(request.getToeflScore());
        application.setHasAdmit(request.getHasAdmit());

        application.setHasWorkExperience(request.getHasWorkExperience());
        application.setExperienceYears(request.getExperienceYears());
        application.setCurrentlyWorking(request.getCurrentlyWorking());
        application.setIncome(request.getIncome());
        application.setEmi(request.getEmi());

        application.setCoapplicantIncome(request.getCoapplicantIncome());
        application.setCoapplicantNoe(request.getCoapplicantNoe());
        application.setCoapplicantState(request.getCoapplicantState());
        application.setCoapplicantCity(request.getCoapplicantCity());
        application.setCoapplicantEmi(request.getCoapplicantEmi());

        application.setHasCollateral(request.getHasCollateral());
        application.setCollateralCategory(request.getCollateralCategory());
        application.setCollateralValue(request.getCollateralValue());
        application.setCollateralCity(request.getCollateralCity());
        application.setCollateralPincode(request.getCollateralPincode());

        application.setAcademicUniversity(request.getAcademicUniversity());
        application.setAcademicCourse(request.getAcademicCourse());
        application.setCgpa(request.getCgpa());
        application.setBacklogs(request.getBacklogs());
    }

    public ApplicationResponse toResponse(ApplicationDetails application) {

        ApplicationResponse response = new ApplicationResponse();

        response.setId(application.getId());
        response.setApplicationId(application.getApplicationId());

        if (application.getAssignedPartners() != null) {
            List<ApplicationBankPartnerResponse> partners =
                    application.getAssignedPartners()
                            .stream()
                            .map(this::mapToPartnerResponse)
                            .toList();

            response.setAssignedPartners(partners);
        }

        response.setName(application.getName());
        response.setAge(application.getAge());
        response.setGender(application.getGender());
        response.setState(application.getState());
        response.setCity(application.getCity());
        response.setPhoneNumber(application.getPhoneNumber());
        response.setEmail(application.getEmail());

        response.setTargetCountry(application.getTargetCountry());
        response.setTargetUniversity(application.getTargetUniversity());
        response.setCourseLevel(application.getCourseLevel());
        response.setCourseName(application.getCourseName());
        response.setLoanAmount(application.getLoanAmount());
        response.setIntake(application.getIntake());

        response.setGreScore(application.getGreScore());
        response.setIeltsScore(application.getIeltsScore());
        response.setPteScore(application.getPteScore());
        response.setToeflScore(application.getToeflScore());
        response.setHasAdmit(application.getHasAdmit());

        response.setHasWorkExperience(application.getHasWorkExperience());
        response.setExperienceYears(application.getExperienceYears());
        response.setCurrentlyWorking(application.getCurrentlyWorking());
        response.setIncome(application.getIncome());
        response.setEmi(application.getEmi());

        response.setCoapplicantIncome(application.getCoapplicantIncome());
        response.setCoapplicantNoe(application.getCoapplicantNoe());
        response.setCoapplicantState(application.getCoapplicantState());
        response.setCoapplicantCity(application.getCoapplicantCity());
        response.setCoapplicantEmi(application.getCoapplicantEmi());

        response.setHasCollateral(application.getHasCollateral());
        response.setCollateralCategory(application.getCollateralCategory());
        response.setCollateralValue(application.getCollateralValue());
        response.setCollateralCity(application.getCollateralCity());
        response.setCollateralPincode(application.getCollateralPincode());

        response.setAcademicUniversity(application.getAcademicUniversity());
        response.setAcademicCourse(application.getAcademicCourse());
        response.setCgpa(application.getCgpa());
        response.setBacklogs(application.getBacklogs());

        response.setStatus(application.getStatus());
        response.setSource(application.getSource());
        response.setAdminNotes(application.getAdminNotes());

        response.setCreatedAt(application.getCreatedAt());
        response.setUpdatedAt(application.getUpdatedAt());

        return response;
    }

    private ApplicationBankPartnerResponse mapToPartnerResponse(ApplicationBankPartner abp) {
        ApplicationBankPartnerResponse dto = new ApplicationBankPartnerResponse();

        dto.setId(abp.getId());
        dto.setBankPartnerId(abp.getBankPartner().getId());

        dto.setHandlerName(abp.getBankPartner().getHandlerName());
        dto.setCity(abp.getBankPartner().getCity());
        dto.setHandlerPhoneNumber(abp.getBankPartner().getHandlerPhoneNumber());

        dto.setBankName(abp.getBankPartner().getBank().getName());

        dto.setStatus(abp.getStatus());
        dto.setNotes(abp.getNotes());

        return dto;
    }
}
