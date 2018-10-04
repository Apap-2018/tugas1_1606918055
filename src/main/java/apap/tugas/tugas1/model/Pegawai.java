package apap.tugas.tugas1.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pegawai")
public class Pegawai extends AbstractEntity {

    @NotNull
    @Column(unique = true, nullable = false)
    private String nip;

    @NotNull
    @Column(nullable = false)
    private String nama;

    @NotNull
    @Column(name = "tempat_lahir", nullable = false)
    private String tempatLahir;

    @NotNull
    @Column(name = "tanggal_lahir", nullable = false)
    private Date tanggalLahir;

    @NotNull
    @Column(name = "tahun_masuk", nullable = false)
    private String tahunMasuk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_instansi", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Instansi instansi;

    @OneToMany(mappedBy = "pegawai", fetch = FetchType.EAGER)
    private List<JabatanPegawai> jabatanPegawaiList;

    public Pegawai() {}

    public Pegawai(String nip, String nama, String tempatLahir, Date tanggalLahir, String tahunMasuk, Instansi instansi) {
        this.nip = nip;
        this.nama = nama;
        this.tempatLahir = tempatLahir;
        this.tanggalLahir = tanggalLahir;
        this.tahunMasuk = tahunMasuk;
        this.instansi = instansi;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public void setJabatanPegawaiList(List<JabatanPegawai> jabatanPegawaiList) {
        this.jabatanPegawaiList = jabatanPegawaiList;
    }

    public String getTahunMasuk() {
        return tahunMasuk;
    }

    public void setTahunMasuk(String tahunMasuk) {
        this.tahunMasuk = tahunMasuk;
    }

    public Instansi getInstansi() {
        return instansi;
    }

    public void setInstansi(Instansi instansi) {
        this.instansi = instansi;
    }

    public List<JabatanPegawai> getJabatanPegawaiList() {
        return jabatanPegawaiList;
    }

    @Override
    public String toString() {
        return "Pegawai{" +
                "id=" + id +
                ", nip='" + nip + '\'' +
                ", nama='" + nama + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pegawai pegawai = (Pegawai) o;
        return getId() == pegawai.getId() &&
                Objects.equals(getNip(), pegawai.getNip());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNip());
    }
}
