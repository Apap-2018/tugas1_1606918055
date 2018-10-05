package apap.tugas.tugas1.service;

import apap.tugas.tugas1.repository.PegawaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Component(value = "PegawaiServiceImpl")
public class PegawaiServiceImpl implements PegawaiService {

    @Autowired
    private PegawaiRepository mRepository;

    @Override
    public PegawaiRepository getManager() {
        return mRepository;
    }
}
