package apap.tugas.tugas1.repository;

import apap.tugas.tugas1.model.Jabatan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JabatanRepository extends JpaRepository<Jabatan, Long> {
}