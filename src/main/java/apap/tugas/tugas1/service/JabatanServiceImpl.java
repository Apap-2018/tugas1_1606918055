package apap.tugas.tugas1.service;

import apap.tugas.tugas1.repository.JabatanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Component(value = "JabatanServiceImpl")
public class JabatanServiceImpl implements JabatanService {

    @Autowired
    private JabatanRepository mRepository;

    @Override
    public JabatanRepository getManager() {
        return mRepository;
    }
}
