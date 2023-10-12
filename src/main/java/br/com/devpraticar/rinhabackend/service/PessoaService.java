package br.com.devpraticar.rinhabackend.service;

import br.com.devpraticar.rinhabackend.entity.PessoaEntity;
import br.com.devpraticar.rinhabackend.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.Objects.nonNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository repository;

    public PessoaEntity salvar(PessoaEntity pessoaEntity) {
        if(!repository.existsByApelido(pessoaEntity.getApelido())) {
            return repository.save(pessoaEntity);
        }
        return null;
    }

    public PessoaEntity getById(UUID id) {
        if(nonNull(id)) {
            return repository.findById(id).orElse(null);
        }
        return null;
    }

    public List<PessoaEntity> getAllByTermo(String t) {
        return repository.findAllByTermo(t, Pageable.ofSize(50));
    }

    public Long contagemPessoas() {
        return repository.count();
    }

    public void deleteAll() {
        repository.deleteAll();
    }

}