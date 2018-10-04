package apap.tugas.tugas1.model;

import javax.persistence.*;
import java.util.Objects;


@MappedSuperclass
public abstract class AbstractEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true, updatable = false, nullable = false)
    protected long id;

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
