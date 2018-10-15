package apap.tugas.tugas1.dataclass;

import apap.tugas.tugas1.model.Instansi;
import apap.tugas.tugas1.model.Jabatan;
import apap.tugas.tugas1.model.Provinsi;

public class DataFactory {

    public static JabatanDC createJabatanData(Jabatan j) {
        JabatanDC jdc = new JabatanDC();
        jdc.transferFrom(j);
        return jdc;
    }

    public static InstansiDC createInstansiData(Instansi i) {
        InstansiDC idc = new InstansiDC();
        return idc;
    }

    public static ProvinsiDC createProvinsiData(Provinsi p) {
        ProvinsiDC pdc = new ProvinsiDC();
        return pdc;
    }
}
