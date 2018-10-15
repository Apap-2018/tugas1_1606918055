package apap.tugas.tugas1.repository;

import apap.tugas.tugas1.model.Instansi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstansiRepository extends JpaRepository<Instansi, Long> {
}
