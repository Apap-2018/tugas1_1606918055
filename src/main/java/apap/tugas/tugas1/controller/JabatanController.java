package apap.tugas.tugas1.controller;

import apap.tugas.tugas1.model.Jabatan;
import apap.tugas.tugas1.service.JabatanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.logging.Logger;

@Controller
public class JabatanController {

    private static final Logger LOGGER = Logger.getLogger(JabatanController.class.getName());

    @Autowired
    @Qualifier(value = "JabatanServiceImpl")
    private JabatanService mService;

    @GetMapping(value = "/jabatan")
    public String jabatanDetail(@RequestParam(value = "id") Long jabatanId, Model model) {
        Optional<Jabatan> jabatan = mService.getManager().findById(jabatanId);

        if(!jabatan.isPresent()) {
            // throw error
            return "redirect:/";
        }

        model.addAttribute("jabatan", jabatan.get());
        return "pages/JabatanDetailPage.html";
    }
}
