package facultate.project.repository;

import facultate.project.model.Right;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RightRepository extends JpaRepository<Right,Integer> {
    List<Right> findByName(String name);
}