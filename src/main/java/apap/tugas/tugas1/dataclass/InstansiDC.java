package apap.tugas.tugas1.dataclass;

import apap.tugas.tugas1.model.Instansi;

public class InstansiDC implements DataClass<Instansi> {

    private Long id;

    private String nama;

    private String deskripsi;

    private ProvinsiDC provinsi;

    @Override
    public void transferFrom(Instansi instansi) {
        this.setId(instansi.getId());
        this.setNama(instansi.getNama());
        this.setDeskripsi(instansi.getDeskripsi());
        this.setProvinsi(DataClassFactory.createProvinsiData(instansi.getProvinsi()));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public ProvinsiDC getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(ProvinsiDC provinsi) {
        this.provinsi = provinsi;
    }

}
