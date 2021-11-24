package br.unisul.aula.dtos;

import br.unisul.aula.modelo.Cliente;

import java.util.ArrayList;
import java.util.List;

public class CidadeDTO {

    private String cidade;
    private String UF;
    private List<InfoClienteDTO> clientes = new ArrayList<>();

    public CidadeDTO() {
    }
    public CidadeDTO(Cliente cliente) {
        this.setCidade(cliente.getEndereco().getCidade());
        this.setUF(cliente.getEndereco().getUf().name());
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public List<InfoClienteDTO> getClientes() {
        return clientes;
    }

    public void setClientes(List<InfoClienteDTO> clientes) {
        this.clientes = clientes;
    }
    public void setClientes(InfoClienteDTO cliente) {
        this.clientes.add(cliente);
    }
}
