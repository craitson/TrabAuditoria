/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Modelo.Usuario;

/**
 *
 * @author Craitson
 */
public class Log {

    private int codigo;
    private String data;
    private String tipo;
    private String tabela;
    private String campo;
    private String campo_antes;
    private String campo_depois;
    private Usuario usuario;

    public Log() {
    }

    public Usuario getUsuario() {
        if (usuario == null) {
            usuario = new Usuario();
        }
        return usuario;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTabela() {
        return tabela;
    }

    public void setTabela(String tabela) {
        this.tabela = tabela;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getCampo_antes() {
        return campo_antes;
    }

    public void setCampo_antes(String campo_antes) {
        this.campo_antes = campo_antes;
    }

    public String getCampo_depois() {
        return campo_depois;
    }

    public void setCampo_depois(String campo_depois) {
        this.campo_depois = campo_depois;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
