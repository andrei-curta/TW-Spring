package facultate.project.service;

import facultate.project.model.Resource;
import facultate.project.repository.ResourceRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ResourceService {
    private final ResourceRepository resourceRepository;

    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public void SaveResource(Resource resource){
        resourceRepository.save(resource);
    }

}
