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

    @Autowired
    @Qualifier(value = "PegawaiServiceImpl")
    private PegawaiService mService;

    @GetMapping(value = "/pegawai")
    public String pegawaiDetail(@RequestParam(value = "nip") String nip, Model model){
        Optional<Pegawai> pegawai = mService.getManager().getPegawaiByNip(nip);
        LOGGER.log(Level.INFO, String.format("Search Pegawai By NIP: %s; Result: %s", nip, pegawai.toString()));

        if(!pegawai.isPresent()) {
            // throw custom exception
            return "redirect:/";
        }

        model.addAttribute("pegawai", pegawai.get());
        return "pages/PegawaiDetailPage.html";
    }

}
