package apap.tugas.tugas1.controller;

import apap.tugas.tugas1.dataclass.JabatanDC;
import apap.tugas.tugas1.dataclass.PegawaiDC;
import apap.tugas.tugas1.model.Pegawai;
import apap.tugas.tugas1.service.InstansiService;
import apap.tugas.tugas1.service.PegawaiService;
import apap.tugas.tugas1.service.ProvinsiService;
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
public class PegawaiController {

    private static final Logger LOGGER = Logger.getLogger(PegawaiController.class.getName());

    private PegawaiService pegawaiService;

    private InstansiService instansiService;

    private ProvinsiService provinsiService;

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

    @Autowired
    @Qualifier(value = "ProvinsiServiceImpl")
    public void setProvinsiService(ProvinsiService provinsiService) {
        this.provinsiService = provinsiService;
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

    @GetMapping(value = "/pegawai/tambah")
    public String retrieveCreatePegawai(Model model) {
        PegawaiDC pegawaiDC = new PegawaiDC();
        pegawaiDC.getJabatans().add(new JabatanDC());
        model.addAttribute("instansiList", instansiService.getManager().findAll());
        model.addAttribute("provinsiList", provinsiService.getManager().findAll());
        model.addAttribute("newPegawai", pegawaiDC);
        return "pages/CreatePegawaiPage.html";
    }

    @PostMapping(value = "/pegawai/tambah")
    public String createPegawai(@ModelAttribute PegawaiDC pegawaiDC, BindingResult bindingResult, RedirectAttributes redirect) {
        Message message = new Message();
        message.setTitle("Pegawai");

        if(bindingResult.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            for(FieldError err : bindingResult.getFieldErrors()) {
                stringBuilder.append(err.toString());
                stringBuilder.append('\n');
            }
            message.setContent(stringBuilder.toString());
            message.setType(Message.Type.DANGER);
        } else {
            Pegawai pegawai = this.pegawaiService.createPegawai(pegawaiDC);
            message.setContent("Pegawai " + pegawai.getNama() + " berhasil disimpan");
            message.setType(Message.Type.SUCCESS);
        }

        return "redirect:/pegawai/tambah";
    }

    @GetMapping(value = "/pegawai/ubah")
    public String retrieveUpdatePegawai(@RequestParam(value = "nip") String nip, Model model, RedirectAttributes redirectAttributes) {

        Optional<Pegawai> pegawaiOptional = pegawaiService.getManager().findPegawaiByNip(nip);

        Message message = new Message();
        message.setTitle("Pegawai");
        if(!pegawaiOptional.isPresent()) {
            // do something
            message.setType(Message.Type.WARNING);
            message.setContent("Pegawai tidak ditemukan");
            redirectAttributes.addFlashAttribute(Message.MESSAGE_NAME, message);
            return "redirect:/";
        }

        PegawaiDC pegawaiDC = new PegawaiDC();
        pegawaiDC.transferFrom(pegawaiOptional.get());

        model.addAttribute("instansiList", instansiService.getManager().findAll());
        model.addAttribute("provinsiList", provinsiService.getManager().findAll());
        model.addAttribute("updatePegawai", pegawaiDC);
        model.addAttribute(Message.MESSAGE_NAME, message);

        return "pages/UpdatePegawaiPage.html";
    }

    @PostMapping(value = "/pegawai/ubah")
    public String updatePegawai(@ModelAttribute PegawaiDC pegawaiDC, BindingResult bindingResult, RedirectAttributes redirect) {
        Pegawai pegawai = null;
        Message message = new Message();
        message.setTitle("Pegawai");

        if(bindingResult.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            message.setTitle("Gagal Mengubah Pegawai");
            for(FieldError err : bindingResult.getFieldErrors()) {
                stringBuilder.append(err.toString());
                stringBuilder.append('\n');
            }
            message.setContent(stringBuilder.toString());
            message.setType(Message.Type.DANGER);
            pegawai = this.pegawaiService.getManager().getOne(pegawaiDC.getId());
        } else {
            pegawai = this.pegawaiService.updatePegawai(pegawaiDC);
            message.setContent("Pegawai " + pegawai.getNama() + " berhasil diubah");
            message.setType(Message.Type.SUCCESS);
            LOGGER.log(Level.INFO, "Pegawai save -> " + pegawai.toString());
        }

        redirect.addFlashAttribute(Message.MESSAGE_NAME, message);
        return "redirect:/pegawai/ubah?nip=" + pegawai.getNip();
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
