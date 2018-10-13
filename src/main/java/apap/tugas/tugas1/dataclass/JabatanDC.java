package apap.tugas.tugas1.dataclass;

import apap.tugas.tugas1.model.Jabatan;

public class JabatanDC implements DataClass<Jabatan> {

    private Long id;
    private String nama;
    private String deskripsi;
    private Double gajiPokok;

    @Override
    public void transferFrom(Jabatan jabatan) {
        this.setId(jabatan.getId());
        this.setNama(jabatan.getNama());
        this.setDeskripsi(jabatan.getDeskripsi());
        this.setGajiPokok(jabatan.getGajiPokok());
    }

    @Override
    public Jabatan transferTo(Jabatan jabatan) {
        jabatan.setNama(this.getNama());
        jabatan.setDeskripsi(this.getDeskripsi());
        jabatan.setGajiPokok(this.getGajiPokok());

        return jabatan;
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

    public Double getGajiPokok() {
        return gajiPokok;
    }

    public void setGajiPokok(Double gajiPokok) {
        this.gajiPokok = gajiPokok;
    }
}
