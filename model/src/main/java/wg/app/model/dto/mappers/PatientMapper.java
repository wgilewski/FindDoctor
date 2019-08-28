package wg.app.model.dto.mappers;

import org.mapstruct.Mapper;
import wg.app.model.dto.PatientDto;
import wg.app.model.patient.Patient;

@Mapper
public interface PatientMapper
{
    PatientDto patientToPatientDto(Patient patient);
    Patient patientDtoToPatient(PatientDto patientDto);
}
