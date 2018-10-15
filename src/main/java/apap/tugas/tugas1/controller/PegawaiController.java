package apap.tugas.tugas1.controller;

import apap.tugas.tugas1.model.Pegawai;
import apap.tugas.tugas1.service.InstansiService;
import apap.tugas.tugas1.service.PegawaiService;
import apap.tugas.tugas1.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class PegawaiController {

    private static final Logger LOGGER = Logger.getLogger(PegawaiController.class.getName());

    private PegawaiService pegawaiService;

    private InstansiService instansiService;

    @Autowired
    @Qualifier(value = "PegawaiServiceImpl")
    public void setPegawaiService(PegawaiService pegawaiService) {
        this.pegawaiService = pegawaiService;
    }

    @Autowired
    @Qualifier(value = "InstansiServiceImpl")
    public void setInstansiService(InstansiService instansiService) {
        this.instansiService = instansiService;
    }

    @GetMapping(value = "/pegawai")
    public String pegawaiDetail(@RequestParam(value = "nip") String nip, Model model, RedirectAttributes redirect) {
        Optional<Pegawai> pegawai = pegawaiService.getManager().findPegawaiByNip(nip);
        LOGGER.log(Level.INFO, () -> String.format("Search Pegawai By NIP: %s; Result: %s", nip, pegawai.toString()));

        if (!pegawai.isPresent()) {
            redirect.addFlashAttribute(Message.MESSAGE_NAME,
                    new Message("Pegawai Tidak ditemukan", "", Message.Type.DANGER));
            return "redirect:/";
        }

        model.addAttribute("pegawai", pegawai.get());
        return "pages/PegawaiDetailPage.html";
    }

    @GetMapping(value = "/pegawai/termuda-tertua")
    public String pegawaiTertuaTermudaDetail(@RequestParam(value = "idInstansi") Long idInstansi,
                                             Model model,
                                             RedirectAttributes redirect) {
        if(instansiService.getManager().existsById(idInstansi)) {
            model.addAllAttributes(this.pegawaiService.getOldestAndYoungestPegawaiByInstansi(idInstansi));
        } else {
            redirect.addFlashAttribute(Message.MESSAGE_NAME,
                    new Message("Instansi tidak ditemukan", "", Message.Type.DANGER));
            return "redirect:/";
        }

        return "pages/PegawaiTermudaTertuaDetailPage.html";
    }

//    @GetMapping(value = "/pegawai/cari")
//    public String searchPegawai(@RequestParam(value = "idProvinsi", required = false) long idProvinsi,
//                                @RequestParam(value = "idInstansi", required = false) long idInstansi,
//                                @RequestParam(value = "idJabatan", required = false) long idJabatan,
//                                Model model
//    ) {
//        Optional<Pegawai> pegawaies = pegawaiService
//                .getManager()
//                .findPegawaiByProvinsiOrInstansiOrJabatan(idProvinsi, idInstansi, idJabatan);
//
//        if(pegawaies.isPresent()) {
//            model.addAttribute("listOfPegawai", pegawaies);
//        }
//
//        return "pages/PegawaiSearch.html";
//    }


}
