package com.HMSApp.Hospital.Management.System.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.AttributeNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.HMSApp.Hospital.Management.System.entity.Patient;
import com.HMSApp.Hospital.Management.System.repository.PatientRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class PatientController {

    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // Add patient
    @PostMapping("/patients")
    public Patient createPatient(@RequestBody Patient patient) {
        patient.setId(null); // 👈 make sure id is null for new patient
        return patientRepository.save(patient);
    }

    // Get all patients
    @GetMapping("/patients")
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // Get patient by id
    @GetMapping("/patients/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) throws AttributeNotFoundException {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new AttributeNotFoundException("Patient not found with id :" + id));
        return ResponseEntity.ok(patient);
    }

    // Delete patient
    @DeleteMapping("/patients/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePatient(@PathVariable Long id) throws AttributeNotFoundException {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new AttributeNotFoundException("Patient not found with id " + id));
        patientRepository.delete(patient);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    // Update patient
    @PutMapping("/patients/{id}")
    public ResponseEntity<Patient> updatePatientById(@PathVariable Long id,
                                                     @RequestBody Patient patientDetails)
                                                     throws AttributeNotFoundException {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new AttributeNotFoundException("Patient not found with id " + id));

        patient.setName(patientDetails.getName());
        patient.setAge(patientDetails.getAge());
        patient.setBlood(patientDetails.getBlood());
        patient.setPrescription(patientDetails.getPrescription());
        patient.setDose(patientDetails.getDose());
        patient.setFees(patientDetails.getFees());
        patient.setUrgency(patientDetails.getUrgency());

        Patient updatedPatient = patientRepository.save(patient);
        return ResponseEntity.ok(updatedPatient);
    }
}
