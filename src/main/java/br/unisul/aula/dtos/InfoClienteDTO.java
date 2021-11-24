package br.unisul.aula.dtos;

import br.unisul.aula.modelo.Cliente;

public class InfoClienteDTO {
    private Long id;
    private String nome;

    public InfoClienteDTO() {
    }

    public InfoClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
