package apap.tugas.tugas1.dataclass;

import apap.tugas.tugas1.model.Jabatan;

public class JabatanDC implements DataClass<Jabatan> {

    private Long id;
    private String nama;
    private String deskripsi;
    private Double gajiPokok;
    private Integer numberOfPegawai;

    @Override
    public void transferFrom(Jabatan jabatan) {
        this.setId(jabatan.getId());
        this.setNama(jabatan.getNama());
        this.setDeskripsi(jabatan.getDeskripsi());
        this.setGajiPokok(jabatan.getGajiPokok());
        this.setNumberOfPegawai(jabatan.getNumberOfPegawai());
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

    public Integer getNumberOfPegawai() {
        return numberOfPegawai;
    }

    public void setNumberOfPegawai(Integer numberOfPegawai) {
        this.numberOfPegawai = numberOfPegawai;
    }
}
