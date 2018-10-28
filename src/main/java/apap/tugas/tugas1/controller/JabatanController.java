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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping(value = "/jabatan/view")
    public String retrieveJabatanDetail(@RequestParam(value = "idJabatan") Long idJabatan,
                                        Model model,
                                        RedirectAttributes redirectAttributes) {
        Optional<Jabatan> jabatan = service.getManager().findById(idJabatan);

        if (!jabatan.isPresent()) {
            // throw error
            Message message =  new Message();
            message.setType(Message.Type.DANGER);
            message.setTitle(Jabatan.class.getSimpleName());
            message.setContent(String.format("Jabatan dengan ID: %d tidak ditemukan", idJabatan));
            redirectAttributes.addFlashAttribute(Message.MESSAGE_NAME, message);
            return "redirect:/";
        }

        JabatanDC jabatanDC = new JabatanDC();
        jabatanDC.transferFrom(jabatan.get());

        model.addAttribute("jabatan", jabatanDC);
        return "pages/JabatanDetailPage.html";
    }

    @GetMapping(value = "/jabatan/tambah")
    public String retrieveCreateJabatan(Model model) {
        model.addAttribute("newJabatan", new JabatanDC());
        return "pages/JabatanCreatePage.html";
    }

    @PostMapping(value = "/jabatan/tambah")
    public String createJabatan(@ModelAttribute JabatanDC newJabatan, Model model, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            final Message message = new Message();
            StringBuilder stringBuilder = new StringBuilder();
            for(FieldError err : bindingResult.getFieldErrors()) {
                stringBuilder.append(err.toString());
                stringBuilder.append('\n');
            }
            message.setContent(stringBuilder.toString());
            message.setType(Message.Type.DANGER);

            model.addAttribute("newJabatan", newJabatan);
            model.addAttribute(Message.MESSAGE_NAME, message);
        } else {
            Jabatan jabatan = this.service.createJabatan(newJabatan);

            model.addAttribute("newJabatan", new JabatanDC());
            model.addAttribute(
                    Message.MESSAGE_NAME, new Message("Jabatan: " + jabatan.getNama(), "Berhasil ditambahkan!", Message.Type.SUCCESS));
        }


        return "pages/JabatanCreatePage.html";
    }

    @GetMapping(value = "/jabatan/ubah")
    public String retrieveUpdateJabatan(@RequestParam(value = "idJabatan") Long idJabatan, Model model) {
        Optional<Jabatan> jabatan = this.service.getManager().findById(idJabatan);

        if(jabatan.isPresent()) {
            JabatanDC jabatanToUpdated = new JabatanDC();
            jabatanToUpdated.transferFrom(jabatan.get());
            model.addAttribute("jabatan", jabatanToUpdated);
        } else {
            // throw custom error
            LOGGER.log(Level.INFO, () -> "Jabatan Not Found: " + idJabatan);
        }

        return "pages/JabatanUpdatePage.html";
    }

    @PostMapping(value = "/jabatan/ubah")
    public String updateJabatan(@ModelAttribute JabatanDC jabatan,
                                BindingResult bindingResult,
                                RedirectAttributes redirect) {

        Message message = new Message();
        message.setTitle("Jabatan: " + jabatan.getNama());

        if(bindingResult.hasErrors()) {
            message.setContent("Gagal mengubah, cek isian");
            message.setType(Message.Type.DANGER);
        } else {
            this.service.updateJabatan(jabatan);
            message.setContent("Berhasil diubah");
            message.setType(Message.Type.SUCCESS);
        }


        redirect.addFlashAttribute(Message.MESSAGE_NAME, message);
        return String.format("redirect:/jabatan/ubah?idJabatan=%d", jabatan.getId());
    }

    @PostMapping(value = "/jabatan/hapus")
    public String deleteJabatan(@ModelAttribute JabatanDC jabatan,
                                BindingResult bindingResult,
                                RedirectAttributes redirect) {

        Message message = new Message();
        message.setTitle("Jabatan: " + jabatan.getId());

        if(bindingResult.hasErrors()) {
            message.setType(Message.Type.DANGER);
            message.setContent("Gagal mengubah, cek isian");
            redirect.addFlashAttribute(Message.MESSAGE_NAME, message);
            return String.format("redirect:/jabatan/ubah?idJabatan=%d", jabatan.getId());
        }

        try {
            this.service.deleteJabatan(jabatan);
            message.setContent("Berhasil terhapus");
            message.setType(Message.Type.SUCCESS);
        } catch (Exception e) {
            message.setContent("Gagal menghapus, masih tedapat pegawai dengan jabatan " + jabatan.getNama());
            message.setType(Message.Type.DANGER);
            return String.format("redirect:/jabatan/ubah?idJabatan=%d", jabatan.getId());

        } finally {
            redirect.addFlashAttribute(Message.MESSAGE_NAME, message);
        }

        return "redirect:/";
    }

    @GetMapping(value = "/jabatan/viewall")
    public String retrieveViewAllJabatan(Model model) {
        model.addAttribute("jabatanList", this.service.getManager().findAll());
        return "pages/JabatanViewAll.html";
    }
}
