/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estacionamento;

/**
 *
 * @author Wagner
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class ManipuladorDeArquivos {
    
    public static ArrayList<String> lerArquivoTexto (String caminho) throws IOException {
        BufferedReader buffRead = new BufferedReader (new FileReader (caminho));
        ArrayList<String> listaDeLinhas = new ArrayList<String>();
        String linha = null;
        
        do {
            linha = buffRead.readLine();
            if (linha != null) {
                listaDeLinhas.add(linha);
            }
        } while (linha != null);
        
        buffRead.close();
        return listaDeLinhas;
    }
}
