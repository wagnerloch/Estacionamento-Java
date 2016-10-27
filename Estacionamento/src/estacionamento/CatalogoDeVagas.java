/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estacionamento;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Wagner
 */
public class CatalogoDeVagas implements java.io.Serializable{
    private int totalDeVagas;
    private Map <String, Vaga> catalogoDeVagas;
    
    public CatalogoDeVagas () {
        catalogoDeVagas = new HashMap <String, Vaga> ();
    }
    
    public void carregaCatalogoDoArquivo (String caminho) throws IOException {
        ArrayList <String> linhas = ManipuladorDeArquivos.lerArquivoTexto(caminho);
        Vaga vaga;
        
        for (String linha : linhas) {
            vaga = this.interpretadorDeLinha(linha);
            catalogoDeVagas.put(Integer.toString(vaga.pegaIdentificacao()), vaga);
            totalDeVagas++;
        }
    }
    
    public void listarCatalogo () {
        System.out.printf ("%-14s|%-9s|%-11s|%-16s|%-12s\n", "Identificacao", "Peso Max", "Altura Max", "Comprimento Max", "Largura Max");
        for (Vaga vaga: catalogoDeVagas.values()) {
            if (!vaga.pegaDisponibilidade()) {
                System.out.printf ("%-14d|%-9d|%-11.1f|%-16.1f|%-12.1f\n", vaga.pegaIdentificacao(), vaga.pegaPesoMaximo(),
                                    vaga.pegaAlturaMaxima(), vaga.pegaComprimentoMaximo(), vaga.pegaLarguraMaxima());
            }
        }
    }
    
    public void alocar (int chassi, int id, String nome) {
        for (Vaga vaga: catalogoDeVagas.values()) {
            if (vaga.pegaIdentificacao() == id) {
                vaga.alocarVeiculo(chassi, nome);
            }
        }
    }
    
    public void alocarFalha (int id) {
        for (Vaga vaga: catalogoDeVagas.values()) {
            if (vaga.pegaIdentificacao() == id) {
                vaga.alocarVeiculoFalha();
            }
        }
    }
    
    public void desalocar (int id) {
        for (Vaga vaga: catalogoDeVagas.values()) {
            if (vaga.pegaIdentificacao() == id) {
                vaga.desalocarVeiculo();
            }
        }
    }
    
    public int pegaPeso (int id) {
        for (Vaga vaga: catalogoDeVagas.values()) {
            if (vaga.pegaIdentificacao() == id) {
                return vaga.pegaPesoMaximo();
            }
        }
        return 0;
    }
    
    public float pegaAltura (int id) {
        for (Vaga vaga: catalogoDeVagas.values()) {
            if (vaga.pegaIdentificacao() == id) {
                return vaga.pegaAlturaMaxima();
            }
        }
        return 0;
    }
    
    public float pegaComprimento (int id) {
        for (Vaga vaga: catalogoDeVagas.values()) {
            if (vaga.pegaIdentificacao() == id) {
                return vaga.pegaComprimentoMaximo();
            }
        }
        return 0;
    }
     
     public float pegaLargura (int id) {
        for (Vaga vaga: catalogoDeVagas.values()) {
            if (vaga.pegaIdentificacao() == id) {
                return vaga.pegaLarguraMaxima();
            }
        }
        return 0;
    }
     
     public int pegaTotalDeVagas () {
         return totalDeVagas;
     }
     
     public int pegaDisponibilidade (int id) {
         for (Vaga vaga: catalogoDeVagas.values()) {
             if (vaga.pegaIdentificacao() == id) {
                 if (vaga.pegaDisponibilidade() == false) { //está disponível
                     return 1;
                 }
                 else {
                     return 0;
                 }
             }
         }
         return -1;
     }
     
     public String pegaNomeVeiculo (int id) {
         for (Vaga vaga: catalogoDeVagas.values()) {
             if (vaga.pegaIdentificacao() == id) {
                 return vaga.pegaVeiculoAlocado();
             }
         }
         return null;
     }
     
     public int pegaChassiVeiculo (int id) {
         for (Vaga vaga: catalogoDeVagas.values()) {
             if (vaga.pegaIdentificacao() == id) {
                 return vaga.pegaChassi();
             }
         }
         return 0;
     }
     
     public int pegaSucessos (int id) {
         for (Vaga vaga: catalogoDeVagas.values()) {
             if (vaga.pegaIdentificacao() == id) {
                 return vaga.pegaSucesso();
             }
         }
         return 0;
     }
     
     public int pegaFalhas (int id) {
         for (Vaga vaga: catalogoDeVagas.values()) {
             if (vaga.pegaIdentificacao() == id) {
                 return vaga.pegaFalha();
             }
         }
         return 0;
     }
    
    private Vaga interpretadorDeLinha (String linha) {
        String[] infoVaga = linha.split(",");
        
        if (infoVaga.length == 5) {
            Vaga vaga = new Vaga (Integer.valueOf(infoVaga[0]),
                                     Integer.valueOf(infoVaga[1]),
                                     Float.valueOf(infoVaga[2]),
                                     Float.valueOf(infoVaga[3]),
                                     Float.valueOf(infoVaga[4])
                                    );
            return vaga;
        }
        else {
            System.out.println ("\nERRO: A descricao da vaga nao esta no padrao correto.");
            System.out.println ("\tlinha: '"+ linha +"'\n");
        }
        return null;
    }
    
}
