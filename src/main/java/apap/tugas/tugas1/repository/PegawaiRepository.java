package apap.tugas.tugas1.repository;

import apap.tugas.tugas1.model.Pegawai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PegawaiRepository extends JpaRepository<Pegawai, Long> {

    Optional<Pegawai> findPegawaiByNip(String nip);

    Optional<Pegawai> findFirstPegawaiByInstansiIdOrderByTanggalLahirAsc(Long idInstansi);

    Optional<Pegawai> findFirstPegawaiByInstansiIdOrderByTanggalLahirDesc(Long idInstansi);

    Optional<Pegawai> findFirstPegawaiByNipStartingWithOrderByNipDesc(String nipWithoutSequence);

    List<Pegawai> findDistinctPegawaiByInstansiIdOrInstansi_ProvinsiIdOrJabatans_Id(Long idInstani, Long idProvinsi, Long idJabatan);

}
