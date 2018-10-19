package apap.tugas.tugas1.service;

import apap.tugas.tugas1.dataclass.PegawaiDC;
import apap.tugas.tugas1.model.Pegawai;
import apap.tugas.tugas1.repository.PegawaiRepository;

import java.util.List;
import java.util.Map;

public interface PegawaiService extends BaseService<PegawaiRepository> {

    String OLDEST_PEGAWAI = "oldest";

    String YOUNGEST_PEGAWAI = "youngest";

    enum PegawaiCRUD {CREATE, UPDATE}

    Map<String, Pegawai> getOldestAndYoungestPegawaiByInstansi(Long idInstansi);

    void savePegawaiFromDataClass(Pegawai pegawai, PegawaiDC pegawaiDC, PegawaiCRUD pegawaiCRUD);

    Pegawai createPegawai(PegawaiDC pegawaiDC);

    Pegawai updatePegawai(PegawaiDC pegawaiDC);

    String generateNipForPegawai(Pegawai pegawai);

    Boolean isNeedNewNip(Pegawai pegawai, PegawaiDC pegawaiDC);

    Map<String, Object> getFormOption();

    List<Pegawai> getSearchResult(Long idProvinsi, Long idInstansi, Long idJabatan);
}
