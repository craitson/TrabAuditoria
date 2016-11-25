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
public class Venda {

    private int codigo;
    private String data;
    private int quantidade;
    private String data_pagamento;
    private float valor_pago;

    private Produto produto;
    private Cliente cliente;

    public Venda() {
    }

    public Venda(int codigo, String data, int quantidade, String data_pagamento, float valor_pago, Produto produto, Cliente cliente) {
        this.codigo = codigo;
        this.data = data;
        this.quantidade = quantidade;
        this.data_pagamento = data_pagamento;
        this.valor_pago = valor_pago;
        this.produto = produto;
        this.cliente = cliente;
    }
    
    

    public Produto getProduto() {
        if (produto == null) {
            produto = new Produto();
        }
        return produto;
    }

    public Cliente getCliente() {
        if (cliente == null) {
            cliente = new Cliente();
        }
        return cliente;
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getData_pagamento() {
        return data_pagamento;
    }

    public void setData_pagamento(String data_pagamento) {
        this.data_pagamento = data_pagamento;
    }

    public float getValor_pago() {
        return valor_pago;
    }

    public void setValor_pago(float valor_pago) {
        this.valor_pago = valor_pago;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
