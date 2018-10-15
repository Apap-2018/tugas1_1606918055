package apap.tugas.tugas1.service;

import apap.tugas.tugas1.model.Pegawai;
import apap.tugas.tugas1.repository.PegawaiRepository;

import java.util.Map;

public interface PegawaiService extends BaseService<PegawaiRepository> {

    String OLDEST_PEGAWAI = "oldest";

    String YOUNGEST_PEGAWAI = "youngest";

    Map<String, Pegawai> getOldestAndYoungestPegawaiByInstansi(Long idInstansi);
}
