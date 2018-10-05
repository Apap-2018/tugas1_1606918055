package apap.tugas.tugas1.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "jabatan_pegawai",
            joinColumns = @JoinColumn(name = "id_pegawai", referencedColumnName = "id",  nullable = false),
            inverseJoinColumns = @JoinColumn(name = "id_jabatan", referencedColumnName = "id",nullable = false))
    private final Set<Jabatan> jabatans = new HashSet<>();

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

    public Set<Jabatan> getJabatans() {
        return jabatans;
    }

    private Double getPersenTunjangan() {
        return getInstansi().getProvinsi().getPresentaseTunjangan();
    }

    public Double getGaji() {
        final Function<Double, Double> calculateGaji = (gajiPokok) ->
                gajiPokok + (gajiPokok * getPersenTunjangan() * 0.01);

        final Set<Jabatan> jabatans = getJabatans();

        if(jabatans.isEmpty()) {
            return .0;
        } else if(jabatans.size() == 1) {
            final Jabatan jabatan = jabatans.iterator().next();
            System.out.println(calculateGaji.apply(jabatan.getGajiPokok()));
            return calculateGaji.apply(jabatan.getGajiPokok());
        } else {
            double maxGajiPokok = .0;
            for (Jabatan jabatan : jabatans) {
                if(maxGajiPokok < jabatan.getGajiPokok())
                    maxGajiPokok = jabatan.getGajiPokok();
            }
            return calculateGaji.apply(maxGajiPokok);
        }
    }

    @Override
    public String toString() {
        return "Pegawai{" +
                "id=" + id +
                ", nip='" + nip + '\'' +
                ", nama='" + nama + '\'' +
                '}';
    }
}
