package apap.tugas.tugas1.controller;

import apap.tugas.tugas1.dataclass.DataClassFactory;
import apap.tugas.tugas1.dataclass.JabatanDC;
import apap.tugas.tugas1.dataclass.PegawaiDC;
import apap.tugas.tugas1.model.Pegawai;
import apap.tugas.tugas1.service.InstansiService;
import apap.tugas.tugas1.service.JabatanService;
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

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class PegawaiController {

    private static final Logger LOGGER = Logger.getLogger(PegawaiController.class.getName());

    private PegawaiService pegawaiService;

    private InstansiService instansiService;

    private ProvinsiService provinsiService;

    private JabatanService jabatanService;

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

    @Autowired
    @Qualifier(value = "JabatanServiceImpl")
    public void setJabatanService(JabatanService jabatanService) { this.jabatanService = jabatanService; }

    @GetMapping(value = "/pegawai")
    public String pegawaiDetail(@RequestParam(value = "nip") final String nip,
                                final Model model,
                                final RedirectAttributes redirect) {

        final Optional<Pegawai> pegawai = pegawaiService.getManager().findPegawaiByNip(nip);
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
    public String pegawaiTertuaTermudaDetail(@RequestParam(value = "idInstansi") final Long idInstansi,
                                             final Model model,
                                             final RedirectAttributes redirect) {
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
        model.addAllAttributes(pegawaiService.getFormOption());
        model.addAttribute("newPegawai", DataClassFactory.createPegawaiDataForForm());
        return "pages/CreatePegawaiPage.html";
    }

    @PostMapping(value = "/pegawai/tambah", params = { "j" })
    public String addMoreJabatan(@ModelAttribute PegawaiDC newPegawai,
                                 final Model model,
                                 final BindingResult bindingResult) {

        if(newPegawai.getJabatans() == null) {
            List<JabatanDC> jabatanDCList = new ArrayList<>();
            jabatanDCList.add(new JabatanDC());
            newPegawai.setJabatans(jabatanDCList);
        } else {
            newPegawai.getJabatans().add(new JabatanDC());
        }

        model.addAllAttributes(pegawaiService.getFormOption());
        model.addAttribute("newPegawai", newPegawai);
        return "pages/CreatePegawaiPage.html";
    }

    @PostMapping(value = "/pegawai/tambah", params = { "save" })
    public String createPegawai(@ModelAttribute final PegawaiDC pegawaiDC,
                                final Model model,
                                final BindingResult bindingResult,
                                final RedirectAttributes redirect) {
        final Message message = new Message();
        message.setTitle(Pegawai.class.getSimpleName());

        if(bindingResult.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            for(FieldError err : bindingResult.getFieldErrors()) {
                stringBuilder.append(err.toString());
                stringBuilder.append('\n');
            }
            message.setContent(stringBuilder.toString());
            message.setType(Message.Type.DANGER);

            model.addAttribute("newPegawai", pegawaiDC);
            model.addAttribute(Message.MESSAGE_NAME, message);
            return "pages/CreatePegawaiPage.html";
        }

        Pegawai pegawai = this.pegawaiService.createPegawai(pegawaiDC);
        message.setContent("Pegawai " + pegawai.getNama() + " berhasil disimpan");
        message.setType(Message.Type.SUCCESS);
        redirect.addFlashAttribute(Message.MESSAGE_NAME, message);
        return "redirect:/pegawai?nip=" + pegawai.getNip();
    }

    @GetMapping(value = "/pegawai/ubah")
    public String retrieveUpdatePegawai(@RequestParam(value = "nip") final String nip,
                                        final Model model,
                                        final RedirectAttributes redirectAttributes) {
        final Optional<Pegawai> pegawaiOptional = pegawaiService.getManager().findPegawaiByNip(nip);
        if(!pegawaiOptional.isPresent()) {
            // do something
            final Message message = new Message();
            message.setTitle(Pegawai.class.getSimpleName());
            message.setType(Message.Type.WARNING);
            message.setContent("Pegawai tidak ditemukan");
            redirectAttributes.addFlashAttribute(Message.MESSAGE_NAME, message);
            return "redirect:/";
        }

        model.addAllAttributes(pegawaiService.getFormOption());
        model.addAttribute("updatePegawai", DataClassFactory.createPegawaiDataFrom(pegawaiOptional.get()));
        return "pages/UpdatePegawaiPage.html";
    }

    @PostMapping(value = "/pegawai/ubah", params = { "j" })
    public String addMoreJabatanUbah(@ModelAttribute PegawaiDC pegawai,
                                 final Model model,
                                 final BindingResult bindingResult) {

        if(pegawai.getJabatans() == null) {
            List<JabatanDC> jabatanDCList = new ArrayList<>();
            jabatanDCList.add(new JabatanDC());
            pegawai.setJabatans(jabatanDCList);
        } else {
            pegawai.getJabatans().add(new JabatanDC());
        }

        model.addAllAttributes(pegawaiService.getFormOption());
        model.addAttribute("updatePegawai", pegawai);
        return "pages/UpdatePegawaiPage.html";
    }

    @PostMapping(value = "/pegawai/ubah", params = { "save" })
    public String updatePegawai(@ModelAttribute final PegawaiDC pegawaiDC,
                                final BindingResult bindingResult,
                                final RedirectAttributes redirect) {
        Pegawai pegawai = null;
        final Message message = new Message();
        message.setTitle(Pegawai.class.getSimpleName());

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
        }

        redirect.addFlashAttribute(Message.MESSAGE_NAME, message);
        return "redirect:/pegawai/ubah?nip=" + pegawai.getNip();
    }

    @GetMapping(value = "/pegawai/cari")
    public String searchPegawai(@RequestParam(value = "idProvinsi", required = false) final Long idProvinsi,
                                @RequestParam(value = "idInstansi", required = false) final Long idInstansi,
                                @RequestParam(value = "idJabatan", required = false) final Long idJabatan,
                                Model model
    ) {
        LOGGER.log(Level.INFO, () -> idProvinsi + " " + idInstansi + " " + idJabatan + " ");

        List<Pegawai> pegawaiList;
        if(idProvinsi == null && idInstansi == null && idJabatan == null) {
            pegawaiList = pegawaiService.getManager().findAll();
        } else {
            pegawaiList = pegawaiService.getManager()
                    .findDistinctPegawaiByInstansiIdOrInstansi_ProvinsiIdOrJabatans_Id(idInstansi, idProvinsi, idJabatan);
            final Message message = new Message();
            message.setTitle(Pegawai.class.getSimpleName());
            message.setContent(pegawaiList.size() + " berhasil ditemukan");
            model.addAttribute(Message.MESSAGE_NAME, message);
        }

        Map<String, Object> attributes = new HashMap<>();
        attributes.put("pegawaiList", pegawaiList);
        attributes.putAll(pegawaiService.getFormOption());

        model.addAllAttributes(attributes);
        return "pages/PegawaiSearch.html";
    }


}
