package br.edu.fateczl.controle_estoque.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @OneToMany
    public List<Venda> venda;
}
