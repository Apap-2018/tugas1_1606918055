package apap.tugas.tugas1.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "jabatan")
public class Jabatan extends AbstractEntity {

    @NotNull
    @Column(nullable = false)
    private String nama;

    @NotNull
    @Column(nullable = false)
    private String deskripsi;

    @NotNull
    @Column(name = "gaji_pokok", nullable = false)
    private double gajiPokok;

    @OneToMany(mappedBy = "jabatan", fetch = FetchType.LAZY)
    private List<JabatanPegawai> pegawaiList;

    public Jabatan() {
    }

    public Jabatan(String nama, String deskripsi, double gajiPokok) {
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.gajiPokok = gajiPokok;
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

    public double getGajiPokok() {
        return gajiPokok;
    }

    public void setGajiPokok(double gajiPokok) {
        this.gajiPokok = gajiPokok;
    }

    public List<JabatanPegawai> getPegawaiList() {
        return pegawaiList;
    }

    @Override
    public String toString() {
        return "Jabatan{" +
                "nama='" + nama + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNama(), getDeskripsi(), getGajiPokok());
    }
}
