package apap.tugas.tugas1.controller;

import apap.tugas.tugas1.dataclass.JabatanDC;
import apap.tugas.tugas1.model.Jabatan;
import apap.tugas.tugas1.service.JabatanService;
import apap.tugas.tugas1.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.logging.Logger;

@Controller
public class JabatanController {

    private static final Logger LOGGER = Logger.getLogger(JabatanController.class.getName());

    private JabatanService service;

    @Autowired
    @Qualifier(value = "JabatanServiceImpl")
    public void setService(JabatanService service) {
        this.service = service;
    }

    @GetMapping(value = "/jabatan")
    public String retrieveJabatanDetail(@RequestParam(value = "id") Long jabatanId, Model model) {
        Optional<Jabatan> jabatan = service.getManager().findById(jabatanId);

        if (!jabatan.isPresent()) {
            // throw error
            return "redirect:/";
        }

        model.addAttribute("jabatan", jabatan.get());
        return "pages/JabatanDetailPage.html";
    }

    @GetMapping(value = "/jabatan/tambah")
    public String retrieveCreateJabatan(Model model) {
        model.addAttribute("newJabatan", new JabatanDC());
        return "pages/CreateJabatanPage.html";
    }

    @PostMapping(value = "/jabatan/tambah")
    public String createJabatan(@ModelAttribute JabatanDC newJabatan, Model model, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            //
        }

        Jabatan jabatan = this.service.createJabatan(newJabatan);

        model.addAttribute("message", new Message("Jabatan: " + jabatan.getNama(), "Berhasil ditambahkan!", Message.Type.SUCCESS));
        model.addAttribute("newJabatan", new JabatanDC());

        return "pages/CreateJabatanPage.html";
    }

}
