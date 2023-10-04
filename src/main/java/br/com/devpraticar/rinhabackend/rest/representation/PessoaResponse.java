package br.com.devpraticar.rinhabackend.rest.representation;

import java.util.UUID;

public record PessoaResponse(UUID id, String apelido, String nome, String nascimento, String stack) { }
