package br.com.devpraticar.rinhabackend.mapper;

import br.com.devpraticar.rinhabackend.entity.PessoaEntity;
import br.com.devpraticar.rinhabackend.rest.representation.PessoaRequest;
import br.com.devpraticar.rinhabackend.rest.representation.PessoaResponse;
import com.google.gson.Gson;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PessoaMapper {

    private static final Supplier<Gson> gsonSupplier = Gson::new;

    private static Gson getGson() {
        return gsonSupplier.get();
    }

    public static List<PessoaResponse> toAllResponse(List<PessoaEntity> entities) {
        List<PessoaResponse> responses = new ArrayList<>();
        entities.forEach(r -> responses.add(toResponse(r)));
        return responses;
    }

    public static PessoaResponse toResponse(PessoaEntity entity) {
        return new PessoaResponse(
            entity.getId(),
            entity.getApelido(),
            entity.getNome(),
            entity.getNascimento(),
            entity.getStack()
        );
    }

    @SneakyThrows
    public static PessoaEntity toEntity(PessoaRequest request) {
        var stack = Objects.nonNull(request.getStack()) ? getGson().toJson(request.getStack()) : null;
        return PessoaEntity.builder()
            .nome(request.getNome())
            .apelido(request.getApelido())
            .nascimento(request.getNascimento())
            .stack(stack)
            .build();
    }

}