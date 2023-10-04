package br.com.devpraticar.rinhabackend.repository;

import br.com.devpraticar.rinhabackend.entity.PessoaEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PessoaRepository extends JpaRepository<PessoaEntity, UUID> {
    boolean existsByApelido(String apelido);

    @Query("SELECT p FROM PessoaEntity p WHERE p.apelido LIKE %:termo% OR p.nome LIKE %:termo% OR p.stack LIKE %:termo%")
    List<PessoaEntity> findAllByTermo(@Param("termo") String termo, Pageable pageable);


}
