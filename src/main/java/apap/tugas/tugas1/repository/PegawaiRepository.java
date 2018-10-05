package apap.tugas.tugas1.repository;

import apap.tugas.tugas1.model.Pegawai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PegawaiRepository extends JpaRepository<Pegawai, Long> {

    Pegawai getPegawaiByNip(String nip);
}
