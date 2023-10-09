package br.com.devpraticar.rinhabackend.rest.api;

import br.com.devpraticar.rinhabackend.mapper.PessoaMapper;
import br.com.devpraticar.rinhabackend.rest.representation.PessoaRequest;
import br.com.devpraticar.rinhabackend.rest.representation.PessoaResponse;
import br.com.devpraticar.rinhabackend.rest.validator.RestValidator;
import br.com.devpraticar.rinhabackend.service.PessoaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.unprocessableEntity;
import static org.springframework.util.StringUtils.hasText;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService service;

    @PostMapping(value = "/pessoas", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> criar(@RequestBody PessoaRequest body) {
        RestValidator.validatorFields(body);
        var pessoa = service.salvar(PessoaMapper.toEntity(body));
        if(isNull(pessoa)) {
            return unprocessableEntity().build();
        }
        return created(URI.create(String.format("/pessoas/%s", pessoa.getId().toString()))).build();
    }

    @GetMapping(value = "/pessoas/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PessoaResponse> getById(@PathVariable("id") UUID id) {
        var pessoa = service.getById(id);
        if (isNull(pessoa) || isNull(pessoa.getId())) {
            return notFound().build();
        }
        return ok(PessoaMapper.toResponse(pessoa));
    }

    @GetMapping(value = "/pessoas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PessoaResponse>> getAllByTermo(@RequestParam(value = "t") String t) {
        if(!hasText(t)) {
           return badRequest().build();
        }
        var entitys = service.getAllByTermo(t);
        if(nonNull(entitys) && !entitys.isEmpty()) {
            return ok().body(PessoaMapper.toAllResponse(entitys));
        }
        return ok().body(Collections.emptyList());
    }

    @GetMapping("/contagem-pessoas")
    public ResponseEntity<Long> contagemPessoas() {
        return ok(service.contagemPessoas());
    }

    @DeleteMapping("/pessoas")
    public ResponseEntity<Void> deleteAll() {
        service.deleteAll();
        return noContent().build();
    }

}
