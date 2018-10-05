package apap.tugas.tugas1.controller;

import apap.tugas.tugas1.model.Jabatan;
import apap.tugas.tugas1.service.JabatanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class GeneralController {

    private static final Logger LOGGER = Logger.getLogger(GeneralController.class.getName());

    @Autowired
    @Qualifier(value = "JabatanServiceImpl")
    private JabatanService mJabatanService;

    @GetMapping(value = "/")
    public String home(Model model) {
        List<Jabatan> jabatanList = mJabatanService.getManager().findAll();
        model.addAttribute("jabatanList", jabatanList);

        return "pages/HomePage.html";
    }
}
