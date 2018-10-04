package apap.tugas.tugas1.service;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseService<O, I> {

    JpaRepository<O, I> getManager();

}
