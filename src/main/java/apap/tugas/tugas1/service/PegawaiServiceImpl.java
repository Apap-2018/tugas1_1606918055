package apap.tugas.tugas1.service;

import apap.tugas.tugas1.model.Pegawai;
import apap.tugas.tugas1.repository.PegawaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

    @Override
    public Map<String, Pegawai> getOldestAndYoungestPegawaiByInstansi(Long idInstansi) {
        Map<String, Pegawai> pegawaiMap = new HashMap<>();
        pegawaiMap.put(OLDEST_PEGAWAI, mRepository.findFirstPegawaiByInstansiIdOrderByTanggalLahirAsc(idInstansi).orElse(null));
        pegawaiMap.put(YOUNGEST_PEGAWAI, mRepository.findFirstPegawaiByInstansiIdOrderByTanggalLahirDesc(idInstansi).orElse(null));
        return Collections.unmodifiableMap(pegawaiMap);
    }
}
