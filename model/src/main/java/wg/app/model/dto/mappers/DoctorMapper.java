package wg.app.model.dto.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import wg.app.model.doctor.Doctor;
import wg.app.model.dto.DoctorDto;

@Component
@Mapper
public interface DoctorMapper
{
    DoctorDto doctorToDoctorDto(Doctor doctor);
    Doctor doctorDtoToDoctor(DoctorDto doctorDto);
}
