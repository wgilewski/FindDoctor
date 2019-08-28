package wg.app.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import wg.app.model.doctor.ServiceInfo;

public interface ServiceInfoRepository extends JpaRepository<ServiceInfo,Long> {
}
