package apap.tugas.tugas1.dataclass;

import apap.tugas.tugas1.model.Jabatan;
import apap.tugas.tugas1.model.Pegawai;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class PegawaiDC implements DataClass<Pegawai> {

    private Long id;

    private String nip;

    private String nama;

    private String tempatLahir;

    private Date tanggalLahir;

    private String tahunMasuk;

    private InstansiDC instansi;

    private List<JabatanDC> jabatans;

    private Supplier<List<JabatanDC>> loadJabatans;

    @Override
    public void transferFrom(Pegawai pegawai) {
        this.setId(pegawai.getId());
        this.setNip(pegawai.getNip());
        this.setNama(pegawai.getNama());
        this.setTempatLahir(pegawai.getTempatLahir());
        this.setTanggalLahir(pegawai.getTanggalLahir());
        this.setTahunMasuk(pegawai.getTahunMasuk());
        this.setInstansi(DataClassFactory.createInstansiData(pegawai.getInstansi()));

        this.loadJabatans = () -> {
            // lazy load
            Set<Jabatan> jbtns = pegawai.getJabatans();
            List<JabatanDC> listDc = new ArrayList<>(jbtns.size());

            for (Jabatan j : jbtns) {
                listDc.add(DataClassFactory.createJabatanData(j));
            }

            return listDc;
        };
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public InstansiDC getInstansi() {
        return instansi;
    }

    public void setInstansi(InstansiDC instansi) {
        this.instansi = instansi;
    }

    public List<JabatanDC> getJabatans() {
        if(this.jabatans == null && loadJabatans != null) {
            this.setJabatans(loadJabatans.get());
        } else {
            this.setJabatans(new ArrayList<>());
        }

        return this.jabatans;
    }

    public void setJabatans(List<JabatanDC> jabatans) {
        this.jabatans = jabatans;
    }
}
