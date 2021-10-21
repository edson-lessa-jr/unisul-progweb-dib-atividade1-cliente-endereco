package br.unisul.aula.dtos;

import br.unisul.aula.modelo.Endereco;
import br.unisul.aula.modelo.UnidadeFederativa;

public class EnderecoDTO {
    private Long idEndereco;
    private String logradouroEndereco;
    private Integer cepEndereco;
    private String bairroEndereco;
    private String cidadeEndereco;
    private String ufEndereco;

    public EnderecoDTO(Endereco endereco) {
        this.idEndereco = endereco.getId();
        this.logradouroEndereco = endereco.getLogradouro();
        this.cepEndereco = endereco.getCep();
        this.bairroEndereco = endereco.getBairro();
        this.cidadeEndereco = endereco.getCidade();
        this.ufEndereco = endereco.getUf().name();
    }

    public EnderecoDTO() {

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

    public Endereco converterParaEndereco() {
        Endereco endereco = new Endereco();
        endereco.setId(this.idEndereco);
        return converterParaEndereco(endereco);
    }

    public Endereco converterParaEndereco(Endereco endereco) {
        endereco.setLogradouro(this.logradouroEndereco);
        endereco.setCep(this.cepEndereco);
        endereco.setBairro(this.bairroEndereco);
        endereco.setCidade(this.cidadeEndereco);
        endereco.setUf(UnidadeFederativa.valueOf(this.ufEndereco));
        return endereco;
    }

    @Override
    public String toString() {
        return "EnderecoDTO{" +
                "idEndereco=" + idEndereco +
                ", logradouroEndereco='" + logradouroEndereco + '\'' +
                ", cepEndereco=" + cepEndereco +
                ", bairroEndereco='" + bairroEndereco + '\'' +
                ", cidadeEndereco='" + cidadeEndereco + '\'' +
                ", ufEndereco='" + ufEndereco + '\'' +
                '}';
    }
}
