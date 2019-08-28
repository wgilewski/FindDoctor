package wg.app.web.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wg.app.model.doctor.Doctor;
import wg.app.model.dto.DoctorDto;
import wg.app.web.services.DoctorService;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;


@RestController
@RequiredArgsConstructor
@RequestMapping("/doctors")
public class DoctorController
{
    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<Doctor> addDoctor(RequestEntity<Doctor> request)
    {
       return doctorService
               .saveDoctor(request.getBody())
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDoctor(@PathVariable Long id)
    {
        doctorService.deleteDoctor(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDto> getOneDoctor(@PathVariable Long id)
    {
        return doctorService.getOneById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/all")
    public ResponseEntity<List<DoctorDto>> getAllDoctors()
    {
        if (doctorService.getAll().size() == 0)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(doctorService.getAll());
    }
}
