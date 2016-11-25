/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Craitson
 */
public class Usuario {
    
    private int codigo;
    private int tipo;
    private String nome;
    private String login;
    private String email;
    private boolean ativo;
    private String senha;

    public Usuario() {
    }

    
    
    public Usuario(int codigo, int tipo, String nome, boolean ativo) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.nome = nome;
        this.ativo = ativo;
    }

    public Usuario(int codigo, int tipo, String nome, String login, String email, boolean ativo) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.nome = nome;
        this.login = login;
        this.email = email;
        this.ativo = ativo;
    }
    public Usuario(int codigo, int tipo, String nome, String login, String email, boolean ativo, String senha) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.senha = senha;
        this.nome = nome;
        this.login = login;
        this.email = email;
        this.ativo = ativo;
    }
    
    public Usuario(int codigo, String nome, String email) {
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
    }
    public Usuario(int codigo, String nome, String email, int tipo) {
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
        this.tipo = tipo;
    }
    
    public Usuario(String nome, String login, String email, boolean ativo, int tipo, String senha) {
        this.tipo = tipo;
        this.nome = nome;
        this.login = login;
        this.email = email;
        this.ativo = ativo;
        this.senha = senha;
    }
    public Usuario(String nome, String login, String email, boolean ativo, int tipo, String senha, int codigo) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.nome = nome;
        this.login = login;
        this.email = email;
        this.ativo = ativo;
        this.senha = senha;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
