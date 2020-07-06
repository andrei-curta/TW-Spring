package facultate.project.repository;

import facultate.project.model.Resource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends CrudRepository<Resource,Integer> {

    List<Resource> findByName(String name);
}
