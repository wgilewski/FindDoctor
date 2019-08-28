package wg.app.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import wg.app.model.Visit;

public interface VisitRepository extends JpaRepository<Visit,Long> {
}
