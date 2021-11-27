package br.com.fiap.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "produtos")
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    public String nome;

    public Produto() {
        super();
    }

    public Produto(long id, String nome) {
        super();
        this.id = id;
        this.nome = nome;
    }

}
