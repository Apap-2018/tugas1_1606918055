package apap.tugas.tugas1.dataclass;

import apap.tugas.tugas1.model.Instansi;
import apap.tugas.tugas1.model.Jabatan;
import apap.tugas.tugas1.model.Pegawai;
import apap.tugas.tugas1.model.Provinsi;

public class DataClassFactory {

    public static JabatanDC createJabatanData(Jabatan j) {
        JabatanDC jdc = new JabatanDC();
        jdc.transferFrom(j);
        return jdc;
    }

    public static InstansiDC createInstansiData(Instansi i) {
        InstansiDC idc = new InstansiDC();
        idc.transferFrom(i);
        return idc;
    }

    public static ProvinsiDC createProvinsiData(Provinsi p) {
        ProvinsiDC pdc = new ProvinsiDC();
        pdc.transferFrom(p);
        return pdc;
    }

    public static PegawaiDC createPegawaiData(Pegawai p) {
        PegawaiDC pgc = new PegawaiDC();
        pgc.transferFrom(p);
        return pgc;
    }
}
