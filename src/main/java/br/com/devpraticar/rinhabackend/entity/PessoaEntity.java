package br.com.devpraticar.rinhabackend.entity;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "pessoa", indexes = {
    @Index(name = "ap_index", columnList = "apelido"),
})
@Cacheable
public class PessoaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(name = "apelido", nullable = false, unique = true, length = 32)
    private String apelido;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "nascimento", nullable = false, length = 10)
    private String nascimento;

    @Column(name = "stack", length = 1024)
    private String stack;

}
