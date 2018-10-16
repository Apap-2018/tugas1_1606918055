package apap.tugas.tugas1.service;

import apap.tugas.tugas1.dataclass.PegawaiDC;
import apap.tugas.tugas1.model.Instansi;
import apap.tugas.tugas1.model.Pegawai;
import apap.tugas.tugas1.repository.PegawaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@Component(value = "PegawaiServiceImpl")
public class PegawaiServiceImpl implements PegawaiService {

    private PegawaiRepository pegawaiRepository;

    private JabatanService jabatanService;

    private InstansiService instansiService;

    @Autowired
    public void setPegawaiRepository(PegawaiRepository pegawaiRepository) {
        this.pegawaiRepository = pegawaiRepository;
    }

    @Autowired
    @Qualifier(value = "JabatanServiceImpl")
    public void setJabatanService(JabatanService jabatanService) {
        this.jabatanService = jabatanService;
    }

    @Autowired
    @Qualifier(value = "InstansiServiceImpl")
    public void setInstansiService(InstansiService instansiService) {
        this.instansiService = instansiService;
    }

    @Override
    public PegawaiRepository getManager() {
        return pegawaiRepository;
    }

    @Override
    public String generateNipForPegawai(Pegawai pegawai) {
        Long instansiDigit = pegawai.getInstansi().getId();

        LocalDate tanggalLahir = pegawai.getTanggalLahir().toLocalDate();
        int day = tanggalLahir.getDayOfMonth();
        int month = tanggalLahir.getMonthValue();
        int year = tanggalLahir.getYear() % 100; // get last two digit

        String tanggalLahirDigit = Integer.toString(year);
        tanggalLahirDigit = (month < 10 ? "0" + month : month) + tanggalLahirDigit;
        tanggalLahirDigit = (day < 10 ? "0" + day : day) + tanggalLahirDigit;

        String nipPegawaiWithoutSequence = instansiDigit + tanggalLahirDigit + pegawai.getTahunMasuk();

        int totalPegawaiByTahunMasuk = this.pegawaiRepository.countPegawaiByNipStartingWith(nipPegawaiWithoutSequence) + 1;
        String seqDigit = totalPegawaiByTahunMasuk < 10 ?
                "0" + totalPegawaiByTahunMasuk :
                Integer.toString(totalPegawaiByTahunMasuk);

        return  nipPegawaiWithoutSequence + seqDigit;
    }

    @Override
    public Map<String, Pegawai> getOldestAndYoungestPegawaiByInstansi(Long idInstansi) {
        Map<String, Pegawai> pegawaiMap = new HashMap<>();
        pegawaiMap.put(OLDEST_PEGAWAI, pegawaiRepository.findFirstPegawaiByInstansiIdOrderByTanggalLahirAsc(idInstansi).orElse(null));
        pegawaiMap.put(YOUNGEST_PEGAWAI, pegawaiRepository.findFirstPegawaiByInstansiIdOrderByTanggalLahirDesc(idInstansi).orElse(null));
        return Collections.unmodifiableMap(pegawaiMap);
    }

    @Override
    public Boolean isNeedNewNip(Pegawai pegawai, PegawaiDC pegawaiDC) {
        return !pegawai.getTanggalLahir().equals(pegawaiDC.getTanggalLahir())
                || !pegawai.getTahunMasuk().equals(pegawaiDC.getTahunMasuk())
                || pegawai.getInstansi().getId() != pegawaiDC.getInstansi().getId();
    }

    @Override
    public void savePegawaiFromDataClass(Pegawai pegawai, PegawaiDC pegawaiDC, PegawaiCRUD pegawaiCRUD) {
        Instansi instansi = this.instansiService.getManager().getOne(pegawaiDC.getInstansi().getId());
        boolean isPegawaiNeedNewNip = pegawaiCRUD == PegawaiCRUD.CREATE || this.isNeedNewNip(pegawai, pegawaiDC);

        // todo: implementasi NIP yang bener berdasarkan data
        pegawai.setNama(pegawaiDC.getNama());
        pegawai.setTahunMasuk(pegawaiDC.getTahunMasuk());
        pegawai.setTempatLahir(pegawaiDC.getTempatLahir());
        pegawai.setTanggalLahir(pegawaiDC.getTanggalLahir());
        pegawai.setInstansi(instansi);

        if(isPegawaiNeedNewNip) {
            pegawai.setNip(this.generateNipForPegawai(pegawai));
        }

        // todo: implementasi jabatan di form
        this.pegawaiRepository.save(pegawai);
    }

    @Override
    public Pegawai createPegawai(PegawaiDC pegawaiDC) {

        Pegawai p = new Pegawai();
        this.savePegawaiFromDataClass(p, pegawaiDC, PegawaiCRUD.CREATE);

        // todo delete this: this is just filling for null constaint
        p.getJabatans().add(jabatanService.getManager().getOne((long) 1));
        this.pegawaiRepository.save(p);

        return p;
    }

    @Override
    public Pegawai updatePegawai(PegawaiDC pegawaiDC) {
        Pegawai pegawai = this.pegawaiRepository.getOne(pegawaiDC.getId());
        this.savePegawaiFromDataClass(pegawai, pegawaiDC, PegawaiCRUD.UPDATE);
        return pegawai;
    }
}
