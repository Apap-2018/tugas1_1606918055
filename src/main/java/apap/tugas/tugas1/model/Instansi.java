package apap.tugas.tugas1.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@Table(name = "instansi")
public class Instansi extends AbstractEntity {

    @NotNull
    @Column(nullable = false)
    private String nama;

    @NotNull
    @Column(nullable = false)
    private String deskripsi;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_provinsi", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Provinsi provinsi;

    @OneToMany(mappedBy = "instansi", fetch = FetchType.LAZY)
    private List<Pegawai> pegawaiList;

    public Instansi() {
    }

    public Instansi(String nama, String deskripsi, Provinsi provinsi) {
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.provinsi = provinsi;
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

    public Provinsi getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(Provinsi provinsi) {
        this.provinsi = provinsi;
    }

    public List<Pegawai> getPegawaiList() {
        return pegawaiList;
    }

    @Override
    public String toString() {
        return "Instansi{" +
                "nama='" + nama + '\'' +
                ", provinsi='" + provinsi + '\'' +
                ", id=" + id +
                '}';
    }
}
