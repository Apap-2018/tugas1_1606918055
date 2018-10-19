package apap.tugas.tugas1.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="jabatan_pegawai")
public class JabatanPegawai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEntity)) return false;
        AbstractEntity that = (AbstractEntity) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return Long.toString(this.getId());
    }

}
