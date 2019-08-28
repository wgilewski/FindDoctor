package wg.app.web.services;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import wg.app.model.doctor.Doctor;
import wg.app.model.dto.DoctorDto;
import wg.app.model.dto.mappers.DoctorMapper;
import wg.app.model.exceptions.MyException;
import wg.app.persistence.DoctorRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService
{
   private final DoctorRepository doctorRepository;
   private final DoctorMapper mapper = Mappers.getMapper(DoctorMapper.class);

   public Optional<Doctor> saveDoctor(Doctor doctor)
   {
       checkMethodValue(doctor);
       return Optional.of(doctorRepository.save(doctor));
   }
   public void deleteDoctor(Long doctorId)
   {
       checkMethodValue(doctorId);
       Optional<Doctor> doctor = doctorRepository.findById(doctorId);
       doctor.ifPresentOrElse(doctorRepository::delete, () -> new MyException("DELETING DOCTOR ERROR"));
   }

   public Optional<DoctorDto> getOneById(Long doctorId)
   {
       checkMethodValue(doctorId);
       Optional<Doctor> doctor = doctorRepository.findById(doctorId);

       if (!doctor.isPresent())
       {
           throw new MyException("THERE IS NO DOCTOR WITCH SUCH ID IN DB");
       }
       else
       {
           return Optional.of(mapper.doctorToDoctorDto(doctor.get()));
       }
   }

   public List<DoctorDto> getAll()
   {
       return doctorRepository.findAll().stream()
               .map(mapper::doctorToDoctorDto)
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
