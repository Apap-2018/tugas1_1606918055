package apap.tugas.tugas1.service;

import apap.tugas.tugas1.dataclass.JabatanDC;
import apap.tugas.tugas1.model.Jabatan;
import apap.tugas.tugas1.repository.JabatanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@Component(value = "JabatanServiceImpl")
public class JabatanServiceImpl implements JabatanService {

    private JabatanRepository repository;

    @Autowired
    public void setRepository(JabatanRepository repository) {
        this.repository = repository;
    }

    @Override
    public JabatanRepository getManager() {
        return repository;
    }

    @Override
    public Jabatan createJabatan(JabatanDC jabatanDC) {
        Jabatan jabatan = new Jabatan();
        jabatan.setNama(jabatanDC.getNama());
        jabatan.setDeskripsi(jabatanDC.getDeskripsi());
        jabatan.setGajiPokok(jabatanDC.getGajiPokok());

        repository.save(jabatan);
        return jabatan;
    }

    @Override
    public Jabatan updateJabatan(JabatanDC jabatanDC) {
        Jabatan j = this.repository.getOne(jabatanDC.getId());
        j.setNama(jabatanDC.getNama());
        j.setDeskripsi(jabatanDC.getDeskripsi());
        j.setGajiPokok(jabatanDC.getGajiPokok());
        this.repository.save(j);
        return j;
    }

    @Override
    public void deleteJabatan(JabatanDC jabatanDC) {
        Long id = jabatanDC.getId();
        this.repository.deleteById(id);
    }
}
