package apap.tugas.tugas1.service;

import apap.tugas.tugas1.repository.ProvinsiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Component(value = "ProvinsiServiceImpl")
public class ProvinsiServiceImpl implements ProvinsiService {

    private ProvinsiRepository provinsiRepository;

    @Autowired
    public void setProvinsiRepository(ProvinsiRepository provinsiRepository) {
        this.provinsiRepository = provinsiRepository;
    }

    @Override
    public ProvinsiRepository getManager() {
        return provinsiRepository;
    }
}
