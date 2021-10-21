package br.unisul.aula.dtos;

import br.unisul.aula.banco.EnderecoDAO;
import br.unisul.aula.modelo.Cliente;
import br.unisul.aula.modelo.Endereco;
import br.unisul.aula.modelo.UnidadeFederativa;

public class ClienteDTO {
    private Long idCliente;
    private String nomeCliente;
    private String complementoCliente;
    private Integer numeroCliente;
    private Long idEndereco;
    private String logradouroEndereco;
    private Integer cepEndereco;
    private String bairroEndereco;
    private String cidadeEndereco;
    private String ufEndereco;


    public ClienteDTO(Cliente cliente) {
        this.idCliente = cliente.getId();
        this.nomeCliente = cliente.getNome();
        this.complementoCliente = cliente.getComplemento();
        this.numeroCliente = cliente.getNumero();
        this.idEndereco = cliente.getEndereco().getId();
        this.logradouroEndereco = cliente.getEndereco().getLogradouro();
        this.cepEndereco = cliente.getEndereco().getCep();
        this.bairroEndereco = cliente.getEndereco().getBairro();
        this.cidadeEndereco = cliente.getEndereco().getCidade();
        this.ufEndereco = cliente.getEndereco().getUf().name();
    }


    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getComplementoClinte() {
        return complementoCliente;
    }

    public void setComplementoCliente(String complementoCliente) {
        this.complementoCliente = complementoCliente;
    }

    public Integer getNumeroCliente() {
        return numeroCliente;
    }

    public void setNumeroCliente(Integer numeroCliente) {
        this.numeroCliente = numeroCliente;
    }

    public String getComplementoCliente() {
        return complementoCliente;
    }

    public Long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getLogradouroEndereco() {
        return logradouroEndereco;
    }

    public void setLogradouroEndereco(String logradouroEndereco) {
        this.logradouroEndereco = logradouroEndereco;
    }

    public Integer getCepEndereco() {
        return cepEndereco;
    }

    public void setCepEndereco(Integer cepEndereco) {
        this.cepEndereco = cepEndereco;
    }

    public String getBairroEndereco() {
        return bairroEndereco;
    }

    public void setBairroEndereco(String bairroEndereco) {
        this.bairroEndereco = bairroEndereco;
    }

    public String getCidadeEndereco() {
        return cidadeEndereco;
    }

    public void setCidadeEndereco(String cidadeEndereco) {
        this.cidadeEndereco = cidadeEndereco;
    }

    public String getUfEndereco() {
        return ufEndereco;
    }

    public void setUfEndereco(String ufEndereco) {
        this.ufEndereco = ufEndereco;
    }

    public Cliente converterParaCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(this.idCliente);
        return converterParaCliente(cliente);
    }
    public Cliente converterParaCliente(Cliente cliente) {
        cliente.setNome(this.nomeCliente);
        cliente.setComplemento(this.complementoCliente);
        cliente.setNumero(this.numeroCliente);
        Endereco endereco = buscarEndereco();
        cliente.setEndereco(endereco);
        return cliente;
    }

    private Endereco buscarEndereco() {
        Endereco endereco;

        EnderecoDAO endBanco = new EnderecoDAO();
        if (this.idEndereco != null) {
            endereco = endBanco.findById(this.idEndereco);
        } else
            if (endBanco.existeCEP(this.cepEndereco)>0) {
                endereco = endBanco.findByCep(this.cepEndereco);
            } else {
            endereco = new Endereco();
            endereco.setLogradouro(this.logradouroEndereco);
            endereco.setBairro(this.bairroEndereco);
            endereco.setCep(this.cepEndereco);
            endereco.setCidade(this.cidadeEndereco);
            endereco.setUf(UnidadeFederativa.valueOf(this.ufEndereco));
        }
        return endereco;
    }
}
