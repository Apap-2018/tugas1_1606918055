package apap.tugas.tugas1.controller;

import apap.tugas.tugas1.model.Pegawai;
import apap.tugas.tugas1.service.PegawaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class PegawaiController {

    private static final Logger LOGGER = Logger.getLogger(PegawaiController.class.getName());

    private PegawaiService service;

    @Autowired
    @Qualifier(value = "PegawaiServiceImpl")
    public void setService(PegawaiService service) {
        this.service = service;
    }

    @GetMapping(value = "/pegawai")
    public String pegawaiDetail(@RequestParam(value = "nip") String nip, Model model) {
        Optional<Pegawai> pegawai = service.getManager().findPegawaiByNip(nip);
        LOGGER.log(Level.INFO, () -> String.format("Search Pegawai By NIP: %s; Result: %s", nip, pegawai.toString()));

        if (!pegawai.isPresent()) {
            // throw custom exception
            return "redirect:/";
        }

        model.addAttribute("pegawai", pegawai.get());
        return "pages/PegawaiDetailPage.html";
    }

//    @GetMapping(value = "/pegawai/cari")
//    public String searchPegawai(@RequestParam(value = "idProvinsi", required = false) long idProvinsi,
//                                @RequestParam(value = "idInstansi", required = false) long idInstansi,
//                                @RequestParam(value = "idJabatan", required = false) long idJabatan,
//                                Model model
//    ) {
//        Optional<Pegawai> pegawaies = service
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
