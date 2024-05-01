package com.ravicodes.crud;


import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/training-centers")
public class TrainingCenterController {

    private static final java.util.regex.Pattern PHONE_PATTERN = java.util.regex.Pattern.compile("^\\d{10}$");
    private static final java.util.regex.Pattern EMAIL_PATTERN = java.util.regex.Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    
    @Autowired
    private TrainingCenterService trainingCenterService;
    @PostMapping("/test")
    public ResponseEntity<String> testRequest(@Valid @RequestBody Map<String, Object> requestBody) {
        return ResponseEntity.ok("Request received: " + requestBody);
    }
    @PostMapping("/postmap")
    public ResponseEntity<TrainingCenter> createTrainingCenter( @RequestBody TrainingCenterRequest request) {
       
        TrainingCenter trainingCenter = new TrainingCenter();
        trainingCenter.setCenterName(request.centerName);
        trainingCenter.setCenterCode(request.centerCode);
        trainingCenter.setAddress(request.address);
        trainingCenter.setStudentCapacity(request.studentCapacity);
        trainingCenter.setCoursesOffered(request.coursesOffered);
        trainingCenter.setContactEmail(request.contactEmail);
        trainingCenter.setContactPhone(request.contactPhone);

        validateRequest(request);

        TrainingCenter createdTrainingCenter = trainingCenterService.createTrainingCenter(trainingCenter);
        return ResponseEntity.ok(createdTrainingCenter);
    }

    @GetMapping
    public ResponseEntity<List<TrainingCenter>> getAllTrainingCenters() {
        List<TrainingCenter> trainingCenters = trainingCenterService.getAllTrainingCenters();
        return ResponseEntity.ok(trainingCenters);
    }

    private void validateRequest(TrainingCenterRequest request) {
        
        if (request.centerName == null || request.centerName.trim().length() > 40) {
            
            throw new ValidationException("CenterName must not be null and should be less than" + request.centerName +"40 characters");
        }

        if (request.centerCode == null || !request.centerCode.matches("^[a-zA-Z0-9]{12}$")) {
            throw new ValidationException("CenterCode must not be null and should be exactly 12 alphanumeric characters");
        }

        if (request.address == null) {
            throw new ValidationException("Address must not be null");
        }

        if (request.contactPhone == null || !PHONE_PATTERN.matcher(request.contactPhone).matches()) {
            throw new ValidationException("Invalid contact phone number");
        }

        if (request.contactEmail != null && !EMAIL_PATTERN.matcher(request.contactEmail).matches()) {
            throw new ValidationException("Invalid contact email");
        }
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(ValidationException ex) {
        return new ErrorResponse(ex.getMessage());
    }
    
    static class TrainingCenterRequest {
        private String centerName;
        private String centerCode;
        private Address address;
        private Integer studentCapacity;
        private List<String> coursesOffered;
        private String contactEmail;
        private String contactPhone;
    
        // No-arg constructor
        public TrainingCenterRequest() {}
    
        // Getters and Setters
        public String getCenterName() {
            return centerName;
        }
    
        public void setCenterName(String centerName) {
            this.centerName = centerName;
        }
    
        public String getCenterCode() {
            return centerCode;
        }
    
        public void setCenterCode(String centerCode) {
            this.centerCode = centerCode;
        }
    
        public Address getAddress() {
            return address;
        }
    
        public void setAddress(Address address) {
            this.address = address;
        }
    
        public Integer getStudentCapacity() {
            return studentCapacity;
        }
    
        public void setStudentCapacity(Integer studentCapacity) {
            this.studentCapacity = studentCapacity;
        }
    
        public List<String> getCoursesOffered() {
            return coursesOffered;
        }
    
        public void setCoursesOffered(List<String> coursesOffered) {
            this.coursesOffered = coursesOffered;
        }
    
        public String getContactEmail() {
            return contactEmail;
        }
    
        public void setContactEmail(String contactEmail) {
            this.contactEmail = contactEmail;
        }
    
        public String getContactPhone() {
            return contactPhone;
        }
    
        public void setContactPhone(String contactPhone) {
            this.contactPhone = contactPhone;
        }
    }
    static class ErrorResponse {
        String message;

        ErrorResponse(String message) {
            this.message = message;
        }
    }

    static class ValidationException extends RuntimeException {
        ValidationException(String message) {
            super(message);
        }
    }
}