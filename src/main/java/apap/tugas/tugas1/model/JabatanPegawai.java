package apap.tugas.tugas1.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "jabatan_pegawai")
public class JabatanPegawai extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pegawai", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Pegawai pegawai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jabatan", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Jabatan jabatan;

    public JabatanPegawai() {
    }

    public JabatanPegawai(Pegawai pegawai, Jabatan jabatan) {
        this.pegawai = pegawai;
        this.jabatan = jabatan;
    }

    public Pegawai getPegawai() {
        return pegawai;
    }

    public Jabatan getJabatan() {
        return jabatan;
    }

    @Override
    public String toString() {
        return "JabatanPegawai{" +
                "pegawai=" + pegawai +
                ", jabatan=" + jabatan +
                ", id=" + id +
                '}';
    }
}
