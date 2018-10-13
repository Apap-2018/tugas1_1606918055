package apap.tugas.tugas1.repository;

import apap.tugas.tugas1.model.Pegawai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PegawaiRepository extends JpaRepository<Pegawai, Long> {

    Optional<Pegawai> findPegawaiByNip(String nip);
//
//    @Query("SELECT * FROM Pegawai WHERE idInstansi")
//    Optional<Pegawai> findPegawaiByProvinsiOrInstansiOrJabatan(@Param("") Long provinsi, Long instansi, Long jabatan);
}
