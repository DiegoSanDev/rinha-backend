package br.com.devpraticar.rinhabackend.rest.validator;

import br.com.devpraticar.rinhabackend.rest.exception.BadRequestException;
import br.com.devpraticar.rinhabackend.rest.exception.UnprocessableEntityException;
import br.com.devpraticar.rinhabackend.rest.representation.PessoaRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;
import java.util.regex.Pattern;

import static java.util.Objects.nonNull;
import static org.springframework.util.StringUtils.hasText;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestValidator {

    private static final int TAMANHO_MAXIMO_32_CARACTERES = 32;
    private static final int TAMANHO_MAXIMO_100_CARACTERES = 100;

    public static void validatorFields(PessoaRequest request) {
        validateStack(request.getStack());
        birthValidator(request.getNascimento());
        fieldValidatorWithMaxLength(request.getNome(), TAMANHO_MAXIMO_100_CARACTERES);
        fieldValidatorWithMaxLength(request.getApelido(), TAMANHO_MAXIMO_32_CARACTERES);
    }

    public static void validateStack(List<String> stack) {
        if(nonNull(stack) && !stack.isEmpty()) {
            for(String s : stack) {
                if (!hasText(s) || s.length() > TAMANHO_MAXIMO_32_CARACTERES) {
                    throw new UnprocessableEntityException();
                }
                if(isStringNumber(s)) {
                    throw new BadRequestException();
                }
            }
        }
    }

    private static void fieldValidatorWithMaxLength(String valor, int tamanhoMaximo) {
        if(!hasText(valor) || valor.length() > tamanhoMaximo) {
            throw new UnprocessableEntityException();
        }
        if(isStringNumber(valor)) {
            throw new BadRequestException();
        }
    }

    private static void birthValidator(String nascimento) {
        if(!hasText(nascimento)) {
            throw new UnprocessableEntityException();
        }
        if(!isFormatValidBirth(nascimento)) {
            throw new BadRequestException();
        }
    }

    private static boolean isStringNumber(String str) {
        return NumberUtils.isCreatable(str);
    }

    private static boolean isFormatValidBirth(String nascimento) {
        var regexNascimento = "\\d{4}-\\d{2}-\\d{2}";
        var pattern = Pattern.compile(regexNascimento);
        var matcher = pattern.matcher(nascimento);
        return matcher.matches();
    }

}
