package wg.app.web.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wg.app.model.dto.PatientDto;
import wg.app.model.patient.Patient;
import wg.app.web.services.PatientService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<Patient> addPatient(RequestEntity<Patient> request) {
        return patientService
                .savePatient(request.getBody())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getOnePatient(@PathVariable Long id) {
        return patientService.getOneById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<PatientDto>> getAllDoctors()
    {
        if (patientService.getAll().size() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patientService.getAll());
    }


}
