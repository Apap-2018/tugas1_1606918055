package apap.tugas.tugas1.dataclass;

import apap.tugas.tugas1.model.Provinsi;

public class ProvinsiDC implements DataClass<Provinsi> {

    private String nama;

    private Double presentaseTunjangan;

    @Override
    public void transferFrom(Provinsi provinsi) {
        this.setNama(provinsi.getNama());
        this.setPresentaseTunjangan(provinsi.getPresentaseTunjangan());
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
