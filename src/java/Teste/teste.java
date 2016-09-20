/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

import Controler.CidadeDB;
import Controler.ClienteDB;
import Controler.ProdutoDB;
import Controler.UsuarioDB;
import Modelo.Cidade;
import Modelo.Cliente;
import Modelo.Produto;
import Modelo.Usuario;

/**
 *
 * @author Craitson
 */
public class teste {
    
    public static void main(String[] args) {
    
        Cliente cli = new Cliente("Joao", "rua dos prazeres", "M", "2016-09-01 00:00:00", true, 0, "89190000");
        boolean teste = ClienteDB.insereCliente(cli);
        
        
        
//        Produto prod = new Produto(2, "sabao", 9);
//        boolean teste = ProdutoDB.insereProduto(prod);
//        
        
        
//        Cidade cid = new Cidade(89190000, "Taio", "SC");
//        boolean teste  = CidadeDB.insereCidade(cid);
//        
        
    }
    
}
