package apap.tugas.tugas1.repository;

import apap.tugas.tugas1.model.Provinsi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinsiRepository extends JpaRepository<Provinsi, Long> {

}
