package apap.tugas.tugas1.controller;

import apap.tugas.tugas1.model.Instansi;
import apap.tugas.tugas1.model.Jabatan;
import apap.tugas.tugas1.service.InstansiService;
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

    private JabatanService jabatanService;

    private InstansiService instansiService;

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

    @GetMapping(value = "/")
    public String home(Model model) {
        List<Jabatan> jabatanList = jabatanService.getManager().findAll();
        List<Instansi> instansiList = instansiService.getManager().findAll();

        model.addAttribute("jabatanList", jabatanList);
        model.addAttribute("instansiList", instansiList);
        return "pages/HomePage.html";
    }
}
