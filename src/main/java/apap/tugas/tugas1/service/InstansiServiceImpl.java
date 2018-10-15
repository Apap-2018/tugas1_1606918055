package apap.tugas.tugas1.service;

import apap.tugas.tugas1.repository.InstansiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Component(value = "InstansiServiceImpl")
public class InstansiServiceImpl implements InstansiService {

    private InstansiRepository repository;

    @Autowired
    public void setRepository(InstansiRepository repository) {
        this.repository = repository;
    }

    @Override
    public InstansiRepository getManager() {
        return repository;
    }
}
