package apap.tugas.tugas1.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = { CascadeType.PERSIST, CascadeType.MERGE },
            mappedBy = "jabatans", targetEntity = Pegawai.class)
    private Set<JabatanPegawai> pegawais;

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

    public Set<JabatanPegawai> getPegawais() {
        return pegawais;
    }

    public Integer getNumberOfPegawai() {
        return getPegawais().size();
    }

    @Override
    public String toString() {
        return "Jabatan{" +
                "nama='" + nama + '\'' +
                ", id=" + getId() +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNama(), getDeskripsi(), getGajiPokok());
    }
}
