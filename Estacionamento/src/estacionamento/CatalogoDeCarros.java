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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Wagner
 */
public class CatalogoDeCarros implements java.io.Serializable{
    private int totalDeCarros;
    private Map <String, Carro> catalogoDeCarros;
    
    public CatalogoDeCarros () {
        catalogoDeCarros = new HashMap <String, Carro> ();
    }
    
    public void carregaCatalogoDoArquivo (String caminho) throws IOException {
        ArrayList <String> linhas = ManipuladorDeArquivos.lerArquivoTexto(caminho);
        Carro carro;
        
        for (String linha : linhas) {
            carro = this.interpretadorDeLinha(linha);
            catalogoDeCarros.put(carro.pegaModelo(), carro);
            totalDeCarros++;
        }
    }
    
    public Map pegarCatalogo () {
        return catalogoDeCarros;
    }
    
    public void listarCatalogo () {
        int contador = 0;
        System.out.printf ("%-20s|%-7s|%-5s|%-7s|%-12s|%-8s\n", "Modelo", "Chassi", "Peso", "Altura", "Comprimento", "Largura");
        for (Carro carro: catalogoDeCarros.values()) {
            if (contador < 5) {
                if (!carro.pegaSituacao()) {
                    System.out.printf ("%-20s|%-7s|%-5s|%-7s|%-12s|%-8s\n", carro.pegaModelo(),
                                    carro.pegaChassi(), carro.pegaPeso(), carro.pegaAltura(),
                                    carro.pegaComprimento(), carro.pegaLargura());
                    contador++;
                }
            }            
        }
    }
    
    public void alocar (int chassi, int vaga) {
        for (Carro carro: catalogoDeCarros.values()) {
            if (carro.pegaChassi() == chassi) {
                carro.alocarEmVaga(vaga);
            }
        }
    }
    
    public String pegaModelo (int chassi) {
        for (Carro carro: catalogoDeCarros.values()) {
            if (carro.pegaChassi() == chassi) {
                return carro.pegaModelo();
            }
        }
        return null;
    }
    
    public int pegaPeso (int chassi) {
        for (Carro carro: catalogoDeCarros.values()) {
            if (carro.pegaChassi() == chassi) {
                return carro.pegaPeso();
            }
        }
        return 0;
    }
    public float pegaAltura (int chassi) {
        for (Carro carro: catalogoDeCarros.values()) {
            if (carro.pegaChassi() == chassi) {
                return carro.pegaAltura();
            }
        }
        return 0;
    }
    
    public float pegaComprimento (int chassi) {
        for (Carro carro: catalogoDeCarros.values()) {
            if (carro.pegaChassi() == chassi) {
                return carro.pegaComprimento();
            }
        }
        return 0;
    }
    
    public float pegaLargura (int chassi) {
        for (Carro carro: catalogoDeCarros.values()) {
            if (carro.pegaChassi() == chassi) {
                return carro.pegaLargura();
            }
        }
        return 0;
    }
    
    public int pegaTotalDeCarros () {
        return totalDeCarros;
    }
    
    private Carro interpretadorDeLinha (String linha) {
        String[] infoCarro = linha.split(",");
        
        if (infoCarro.length == 6) {
            Carro carro = new Carro (infoCarro[0],
                                     Integer.valueOf(infoCarro[1]),
                                     Integer.valueOf(infoCarro[2]),
                                     Float.valueOf(infoCarro[3]),
                                     Float.valueOf(infoCarro[4]),
                                     Float.valueOf(infoCarro[5])
                                    );
            return carro;
        }
        else {
            System.out.println ("\nERRO: A descricao do carro nao esta no padrao correto.");
            System.out.println ("\tlinha: '"+ linha +"'\n");
        }
        return null;
    }
    
}
