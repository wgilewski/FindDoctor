package wg.app.web.services;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import wg.app.model.dto.PatientDto;
import wg.app.model.dto.mappers.PatientMapper;
import wg.app.model.exceptions.MyException;
import wg.app.model.patient.Patient;
import wg.app.persistence.PatientRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService
{
    private final PatientRepository patientRepository;
    private final PatientMapper mapper = Mappers.getMapper(PatientMapper.class);

    public Optional<Patient> savePatient(Patient patient)
    {
        checkMethodValue(patient);
        return Optional.of(patientRepository.save(patient));

    }
    public void deletePatient(Long patientId)
    {
        checkMethodValue(patientId);
        Optional<Patient> patient = patientRepository.findById(patientId);
        patient.ifPresentOrElse(patientRepository::delete, () -> new MyException("DELETING PATIENT ERROR"));
    }

    public Optional<PatientDto> getOneById(Long patientId)
    {
        checkMethodValue(patientId);
        Optional<Patient> patient = patientRepository.findById(patientId);

        if (!patient.isPresent())
        {
            throw new MyException("THERE IS NO DOCTOR WITH SUCH ID IN DB");
        }
        return Optional.of(mapper.patientToPatientDto(patient.get()));

    }

    public List<PatientDto> getAll()
    {
        return patientRepository.findAll().stream()
                .map(mapper::patientToPatientDto)
                .collect(Collectors.toList());
    }

    public <T> void checkMethodValue(T type)
    {
        if (type == null)
        {
            throw new MyException("VALUE GIVEN TO THE METHOD IS NULL");
        }
    }
}
