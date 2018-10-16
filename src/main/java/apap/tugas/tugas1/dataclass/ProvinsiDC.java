package apap.tugas.tugas1.dataclass;

import apap.tugas.tugas1.model.Provinsi;

public class ProvinsiDC implements DataClass<Provinsi> {

    private Long id;

    private String nama;

    private Double presentaseTunjangan;

    @Override
    public void transferFrom(Provinsi provinsi) {
        this.setId(provinsi.getId());
        this.setNama(provinsi.getNama());
        this.setPresentaseTunjangan(provinsi.getPresentaseTunjangan());
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

    public Double getPresentaseTunjangan() {
        return presentaseTunjangan;
    }

    public void setPresentaseTunjangan(Double presentaseTunjangan) {
        this.presentaseTunjangan = presentaseTunjangan;
    }
}
