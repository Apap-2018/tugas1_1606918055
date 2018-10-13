package apap.tugas.tugas1.service;

import apap.tugas.tugas1.dataclass.JabatanDC;
import apap.tugas.tugas1.model.Jabatan;
import apap.tugas.tugas1.repository.JabatanRepository;


public interface JabatanService extends BaseService<JabatanRepository> {

    Jabatan createJabatan(JabatanDC jabatanDC);

}
