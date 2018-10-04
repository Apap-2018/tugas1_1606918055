package apap.tugas.tugas1.service;

import apap.tugas.tugas1.model.Jabatan;
import apap.tugas.tugas1.repository.JabatanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Component(value = "JabatanServiceImplV1")
public class JabatanServiceImpl implements JabatanService {

    @Autowired
    private JabatanRepository mRepository;

    @Override
    public JpaRepository<Jabatan, Long> getManager() {
        return mRepository;
    }
}
