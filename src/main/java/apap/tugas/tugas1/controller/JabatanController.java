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
import java.util.logging.Level;
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

        model.addAttribute("newJabatan", new JabatanDC());
        model.addAttribute("message", new Message("Jabatan: " + jabatan.getNama(), "Berhasil ditambahkan!", Message.Type.SUCCESS));

        return "pages/CreateJabatanPage.html";
    }

    @GetMapping(value = "/jabatan/ubah")
    public String retrieveUpdateJabatan(@RequestParam(value = "jabatanId") Long jabatanId, Model model) {
        Optional<Jabatan> jabatan = this.service.getManager().findById(jabatanId);

        if(jabatan.isPresent()) {
            JabatanDC jabatanToUpdated = new JabatanDC();
            jabatanToUpdated.transferFrom(jabatan.get());
            model.addAttribute("jabatan", jabatanToUpdated);
        } else {
            // throw custom error
            LOGGER.log(Level.INFO, () -> "Jabatan Not Found: " + jabatanId);
        }

        return "pages/UpdateJabatanPage.html";
    }

    @PostMapping(value = "/jabatan/ubah")
    public String updateJabatan(@ModelAttribute JabatanDC jabatan,
                                Model model,
                                BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            //
        }

        this.service.updateJabatan(jabatan);

        // todo: feedback message ga keluar karena redirect
        model.addAttribute("message", new Message(
                "Jabatan: " + jabatan.getNama(),
                "Berhasil diubah!", Message.Type.SUCCESS));

        return String.format("redirect:/jabatan/ubah?jabatanId=%d", jabatan.getId());
    }

}
