package apap.tugas.tugas1.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "provinsi")
public class Provinsi extends AbstractEntity {

    @NotNull
    @Column(nullable = false)
    private String nama;

    @NotNull
    @Column(name = "presentase_tunjangan", nullable = false)
    private double presentaseTunjangan;

    @OneToMany(mappedBy = "provinsi", fetch = FetchType.LAZY)
    private List<Instansi> instansiList;

    public Provinsi() {
    }

    public Provinsi(String nama, double presentaseTunjangan) {
        this.nama = nama;
        this.presentaseTunjangan = presentaseTunjangan;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public double getPresentaseTunjangan() {
        return presentaseTunjangan;
    }

    public void setPresentaseTunjangan(double presentaseTunjangan) {
        this.presentaseTunjangan = presentaseTunjangan;
    }

    public List<Instansi> getInstansiList() {
        return instansiList;
    }

    @Override
    public String toString() {
        return "Provinsi{" +
                "nama='" + nama + '\'' +
                ", presentaseTunjangan=" + presentaseTunjangan +
                '}';
    }
}
