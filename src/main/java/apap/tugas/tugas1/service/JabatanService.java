package apap.tugas.tugas1.service;

import apap.tugas.tugas1.model.Jabatan;
import apap.tugas.tugas1.repository.JabatanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JabatanService implements IService<Jabatan, Long> {

    @Autowired
    private JabatanRepository mRepository;

    @Override
    public JpaRepository<Jabatan, Long> getManager() {
        return mRepository;
    }
}
