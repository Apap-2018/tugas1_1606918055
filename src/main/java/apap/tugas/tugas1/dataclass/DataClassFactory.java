package apap.tugas.tugas1.dataclass;

import apap.tugas.tugas1.model.Instansi;
import apap.tugas.tugas1.model.Jabatan;
import apap.tugas.tugas1.model.Pegawai;
import apap.tugas.tugas1.model.Provinsi;

public class DataClassFactory {

    public static JabatanDC createJabatanDataFrom(Jabatan j) {
        JabatanDC jdc = new JabatanDC();
        jdc.transferFrom(j);
        return jdc;
    }

    public static InstansiDC createInstansiDataFrom(Instansi i) {
        InstansiDC idc = new InstansiDC();
        idc.transferFrom(i);
        return idc;
    }

    public static ProvinsiDC createProvinsiDataFrom(Provinsi p) {
        ProvinsiDC pdc = new ProvinsiDC();
        pdc.transferFrom(p);
        return pdc;
    }

    public static PegawaiDC createPegawaiDataFrom(Pegawai p) {
        PegawaiDC pgc = new PegawaiDC();
        pgc.transferFrom(p);
        return pgc;
    }

    public static PegawaiDC createPegawaiDataForForm() {
        PegawaiDC pgc = new PegawaiDC();
        pgc.getJabatans().add(new JabatanDC());
        pgc.setInstansi(new InstansiDC());
        return pgc;
    }
}
