package br.com.devpraticar.rinhabackend.rest.validator;

import br.com.devpraticar.rinhabackend.rest.exception.BadRequestException;
import br.com.devpraticar.rinhabackend.rest.exception.UnprocessableEntityException;
import br.com.devpraticar.rinhabackend.rest.representation.PessoaRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;
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
                if(isStringNumber(s)) {
                    throw new BadRequestException();
                } else if (s.length() > TAMANHO_MAXIMO_32_CARACTERES) {
                    throw new UnprocessableEntityException();
                }
            }
        }
    }

    private static void fieldValidatorWithMaxLength(String valor, int tamanhoMaximo) {
        if(isStringNumber(valor)) {
            throw new BadRequestException();
        } else if(isFieldNull(valor) || valor.length() > tamanhoMaximo) {
            throw new UnprocessableEntityException();
        }
    }

    private static void birthValidator(String nascimento) {
        if(!hasText(nascimento)) {
            throw new UnprocessableEntityException();
        } else if(!isValidNascimento(nascimento)) {
            throw new BadRequestException();
        }
    }

    private static boolean isFieldNull(String campo) {
        return isNull(campo);
    }

    private static boolean isStringNumber(String str) {
        return NumberUtils.isCreatable(str);
    }

    private static boolean isValidNascimento(String nascimento) {
        var sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(nascimento);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    private static boolean isFormatValidBirth(String nascimento) {
        var regexNascimento = "\\d{4}-\\d{2}-\\d{2}";
        var pattern = Pattern.compile(regexNascimento);
        var matcher = pattern.matcher(nascimento);
        return matcher.matches();
    }

}
