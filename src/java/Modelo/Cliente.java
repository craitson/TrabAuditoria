/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Timestamp;

/**
 *
 * @author Craitson
 */
public class Cliente {
    
    private int codigo;
    private String nome;
    private String endereco;
    private String sexo;
    private String nascimento;
    private boolean ativo;
    private float saldoDevedor;
    private String cep;
    private Cidade cidade;

    public Cliente() {
    }

    public Cliente(int codigo, String nome, String endereco, String sexo, String nascimento, boolean ativo, float saldoDevedor, String cep) {
        this.codigo = codigo;
        this.nome = nome;
        this.endereco = endereco;
        this.sexo = sexo;
        this.nascimento = nascimento;
        this.ativo = ativo;
        this.saldoDevedor = saldoDevedor;
        this.cep = cep;
    }
    
    public Cliente(String nome, String endereco, String sexo, String nascimento, boolean ativo, float saldoDevedo) {
        this.nome = nome;
        this.endereco = endereco;
        this.sexo = sexo;
        this.nascimento = nascimento;
        this.ativo = ativo;
        this.saldoDevedor = saldoDevedor;
        this.cep = cep;
    }

    public Cidade getCidade() {
        if (cidade == null){
            cidade = new Cidade();
        }
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public float getSaldoDevedor() {
        return saldoDevedor;
    }

    public void setSaldoDevedor(float saldoDevedor) {
        this.saldoDevedor = saldoDevedor;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getCodigo() {
        return codigo;
    }

    public boolean isAtivo() {
        return ativo;
    }
}
