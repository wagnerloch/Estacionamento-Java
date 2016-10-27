/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estacionamento;

import com.google.common.collect.TreeMultimap;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Wagner Loch
 * @author Edson Rottava
 */

public class Estacionamento {
    public static Scanner ler = new Scanner (System.in);
    public static CatalogoDeCarros catalogoDeCarros = new CatalogoDeCarros ();
    public static CatalogoDeVagas catalogoDeVagas = new CatalogoDeVagas ();
    public static Estatistica estatistica = new Estatistica ();
    
    public static void main(String[] args) throws IOException {
        
        int comando;
        gerarLog (1, 0, 0, 0);
        do {
            System.out.println ("1 - Iniciar Nova Simulacao");
            System.out.println ("2 - Continuar Simulacao Anterior");
            comando = ler.nextInt();
        } while ((comando > 2) || (comando < 1));
        
        switch (comando) {
            case 1:
                gerarLog (2, 0, 0, 0);
                catalogoDeCarros.carregaCatalogoDoArquivo("VEICULOS.txt");
                gerarLog (4, 0, 0, 0);
                catalogoDeVagas.carregaCatalogoDoArquivo("VAGAS.txt");
                gerarLog (5, 0, 0, 0);
                menu ();            
                break;
            case 2:
                carregar ();
                gerarLog (3, 0, 0, 0);
                menu ();
                break;
        }
    }
    
    public static void menu () throws IOException {
        while (true) {
            int comando;
            do {
                System.out.println ("\n\t-- MENU:\n");
                System.out.println ("\t 1. Entrar na garagem");
                System.out.println ("\t 2. Pesquisar");
                System.out.println ("\t 3. Sair da garagem");
                System.out.println ("\t 4. Salvar");
                System.out.println ("\t 5. Gerar Relatorio");
                System.out.println ("\t 6. Sair");
                comando = ler.nextInt();
            } while ((comando < 1) || (comando > 6));
            switch (comando) {
                case 1:
                    entrarNaGaragem ();
                    break;
                case 2:
                    pesquisar ();
                    break;
                case 3:
                    sairDaGaragem ();
                    break;
                case 4:
                    salvar ();
                    break;
                case 5:
                    gerarRelatorio ();
                    break;
                case 6:
                    sair ();
                    break;
            }
        }
    }

    public static void entrarNaGaragem () throws IOException {
        catalogoDeCarros.listarCatalogo();
        System.out.println ();
        catalogoDeVagas.listarCatalogo();
        System.out.println ();
        System.out.print ("Chassi do veiculo: ");
        int chassi = ler.nextInt();
        System.out.print ("Identificador da vaga: ");
        int vaga = ler.nextInt();
        if (chassi == 0 || vaga == 0) {
            return;
        }
        if (catalogoDeCarros.pegaPeso(chassi) <= catalogoDeVagas.pegaPeso(vaga)) {
            if (catalogoDeCarros.pegaAltura(chassi) <= catalogoDeVagas.pegaAltura(vaga)) {
                if (catalogoDeCarros.pegaComprimento(chassi) <= catalogoDeVagas.pegaComprimento(vaga)) {
                    if (catalogoDeCarros.pegaLargura(chassi) <= catalogoDeVagas.pegaLargura(vaga)) {
                        catalogoDeCarros.alocar(chassi, vaga);
                        catalogoDeVagas.alocar(chassi, vaga, catalogoDeCarros.pegaModelo(chassi));
                        System.out.println ("Veiculo "+ catalogoDeCarros.pegaModelo(chassi) + " alocado com sucesso na vaga "+ vaga);
                        gerarLog (8, 1, chassi, vaga);
                        estatistica.addEstatistica(chassi);
                        return;
                    }
                }
            }
        }
        System.out.println ("Veiculo "+ catalogoDeCarros.pegaModelo(chassi) + "não pode ser alocado na vaga "+ vaga);
        catalogoDeVagas.alocarFalha(vaga);
        gerarLog (8, 0, chassi, vaga);
    }
    
    public static void pesquisar () throws IOException {
        catalogoDeCarros.listarCatalogo();
        System.out.print ("\nChassi do veiculo: ");
        int chassi = ler.nextInt();
        if (chassi == 0) {
            return;
        }
        for (int i = 0; i < catalogoDeVagas.pegaTotalDeVagas(); i++) {
            int vaga = 101; //identificação da primeira vaga
            vaga += i;
            if (catalogoDeVagas.pegaDisponibilidade(vaga) == 1) {
                if (catalogoDeCarros.pegaPeso(chassi) <= catalogoDeVagas.pegaPeso(vaga)) {
                    if (catalogoDeCarros.pegaAltura(chassi) <= catalogoDeVagas.pegaAltura(vaga)) {
                        if (catalogoDeCarros.pegaComprimento(chassi) <= catalogoDeVagas.pegaComprimento(vaga)) {
                            if (catalogoDeCarros.pegaLargura(chassi) <= catalogoDeVagas.pegaLargura(vaga)) {
                                catalogoDeCarros.alocar(chassi, vaga);
                                catalogoDeVagas.alocar(chassi, vaga, catalogoDeCarros.pegaModelo(chassi));
                                System.out.println ("Veiculo "+ catalogoDeCarros.pegaModelo(chassi) + " alocado com sucesso na vaga "+ vaga);
                                gerarLog (8, 1, chassi, vaga);
                                estatistica.addEstatistica(chassi);
                                return;
                            }
                        }
                    }
                }
            }
        }
        System.out.println ("Nenhuma vaga disponivel para o veiculo "+ catalogoDeCarros.pegaModelo(chassi));
    }
    
    public static void sairDaGaragem () throws IOException {
        int vaga = 101; //primeira vaga
        int chassi = 0;
        System.out.println ("Vagas ocupadas:");
        System.out.printf ("%-14s|%-20s\n", "Identificacao", "Veiculo");
        for (int i = 0; i < catalogoDeVagas.pegaTotalDeVagas(); i++) {
            vaga += i;
            if (catalogoDeVagas.pegaDisponibilidade(vaga) == 0) {
                System.out.printf ("%-14d|%-20s\n", vaga, catalogoDeVagas.pegaNomeVeiculo(vaga));
            }
        }
        System.out.print ("Liberar vaga: ");
        vaga = ler.nextInt();
        if (vaga == 0) {
            return;
        }
        chassi = catalogoDeVagas.pegaChassiVeiculo(vaga);
        catalogoDeVagas.desalocar(vaga);
        
        //vaga desocupada com sucesso!
        gerarLog (9, 0, chassi, vaga);
    }
    
    public static final void salvar () throws IOException{
            //TRY CATCH
        try{
            //SALVA CARRO EM CARRO.SER
            FileOutputStream arquivo = new FileOutputStream("carro.ser");
            ObjectOutputStream saida = new ObjectOutputStream(arquivo);
            //saida.writeObject(carro);
            saida.writeObject(catalogoDeCarros);
            saida.close();
            arquivo.close();
            //System.out.printf("carros salvos em carro.ser");
           
            //SALVA VAGA EM VAGA.SER
            arquivo = new FileOutputStream("vaga.ser");
            saida = new ObjectOutputStream(arquivo);
            saida.writeObject(catalogoDeVagas);
            saida.close();
            arquivo.close();
            //System.out.printf("vagas salvas em vaga.ser");
        }
        catch(IOException i) {
            i.printStackTrace();
        }
        //TRY CATCH
        gerarLog (10, 0, 0, 0); 
    }

    public static void carregar () {
            //TRY CATCH
        try {
            //CARREGA CARRO DO ARQUIVO CARRO.SER
            FileInputStream entrada = new FileInputStream("carro.ser");
            ObjectInputStream in = new ObjectInputStream(entrada);
            //carro = (Carro) in.readObject();
            catalogoDeCarros = (CatalogoDeCarros) in.readObject();
            in.close();
            entrada.close();
            gerarLog (7, 0, 0, 0);
            //NAO ACHOU ARQUIVO CARRO.SER
        }catch(ClassNotFoundException c) {
            System.out.println("carro.ser não encontrado");
            return;
        }
        catch(IOException i) {
            i.printStackTrace();
            return;
        }
            //TRY CATCH
        try {
            //CARREGA CARRO DO ARQUIVO CARRO.SER
            FileInputStream entrada = new FileInputStream("vaga.ser");
            ObjectInputStream in = new ObjectInputStream(entrada);
            catalogoDeVagas = (CatalogoDeVagas) in.readObject();
            in.close();
            entrada.close();
            gerarLog (6, 0, 0, 0);
            //NAO ACHOU ARQUIVO CARRO.SER
        }catch(ClassNotFoundException c) {
                System.out.println("vaga.ser não encontrado");
                return;
        }
        catch(IOException i) {
            i.printStackTrace();
            return;
        }
        //TRY CATCH
    }

    public static void gerarRelatorio () throws IOException {
        try (FileWriter arquivo = new FileWriter("Relatorio.txt")) {
            PrintWriter gravarArquivo = new PrintWriter(arquivo);
            //CRIA ARQUIVO RELATORIO.TXT
            gravarArquivo.println("Relatorio:");
            gravarArquivo.println();
            //IMPRIME AS VAGAS COM SUCESSO E FALHA
            
            for (int i = 0; i < catalogoDeVagas.pegaTotalDeVagas(); i++) {
                int idVaga = 101; //primeira vaga
                idVaga += i;
                gravarArquivo.print("Vaga: ");            
                gravarArquivo.print (idVaga);
                gravarArquivo.print (" | Tentativas com sucesso: ");
                gravarArquivo.print (catalogoDeVagas.pegaSucessos(idVaga));
                gravarArquivo.print (" | Tentativas sem sucesso: ");
                gravarArquivo.print (catalogoDeVagas.pegaFalhas(idVaga));
                gravarArquivo.println ("");
            }
            
            //IMPRIME TODOS OS VEICULOS POR CATEGORIA
            gravarArquivo.println();
            gravarArquivo.println("Total de veiculos LONGOS estacionados hoje: " + Estatistica.getLongos());
            gravarArquivo.println("Total de veiculos CURTOS estacionados hoje: " + Estatistica.getCurtos());
            gravarArquivo.println("Total de veiculos PESADOS estacionados hoje: " + Estatistica.getPesados());
            gravarArquivo.println("Total de veiculos LEVES estacionados hoje: " + Estatistica.getLeves());
            gravarArquivo.println("Total de veiculos ALTOS estacionados hoje: " + Estatistica.getAltos());
            gravarArquivo.println("Total de veiculos BAIXOS estacionados hoje: " + Estatistica.getBaixos());
            gravarArquivo.println("Total de veiculos LARGOS estacionados hoje: " + Estatistica.getLargos());
            gravarArquivo.println("Total de veiculos ESTREITOS estacionados hoje: " + Estatistica.getEstreitos());
            gravarArquivo.println();
            //IMPRIME POR ORDEM DECRESCENTE DE PESO, ALTURA, COMPRIMENTO E LARGURA
            gravarArquivo.println("Carros estacionados hoje: ");
            TreeMultimap<Float, Integer> mapList;
            mapList = Estatistica.getMapList();
            for (Float key : mapList.keySet()) {
                List<Integer> list = (List<Integer>) mapList.get(key);
                while (list.size() > 0){
                int imprimirChassi = list.get(0);
                    for(int aux = 0; aux < list.size(); aux++){
                        if (catalogoDeCarros.pegaAltura(imprimirChassi) < catalogoDeCarros.pegaAltura(list.get(aux))){
                            imprimirChassi = list.get(aux);
                        }else{
                            if (catalogoDeCarros.pegaAltura(imprimirChassi) == catalogoDeCarros.pegaAltura(list.get(aux))){
                                //MUDA FATOR
                                if (catalogoDeCarros.pegaComprimento(imprimirChassi) < catalogoDeCarros.pegaComprimento(list.get(aux))){
                                    imprimirChassi = list.get(aux);
                                }else{
                                    if (catalogoDeCarros.pegaComprimento(imprimirChassi) == catalogoDeCarros.pegaComprimento(list.get(aux))){
                                        //MUDA FATOR
                                        if (catalogoDeCarros.pegaLargura(imprimirChassi) < catalogoDeCarros.pegaLargura(list.get(aux))){
                                            imprimirChassi = list.get(aux);
                                        }
                                    }
                                }
                            }
                        }
                        gravarArquivo.println(imprimirChassi);
                        list.remove((Integer) imprimirChassi);
                    }
                }
            }
            //FECHA O ARQUIVO RELATORIO.TXT
            arquivo.close();
            System.out.println();   
            System.out.println("Relatorio foi gravado com sucesso em Relatorio.txt");
            gerarLog (12, 0, 0, 0);
        }
    }
    
    public static void gerarLog (int situacao,int condicao, int chassi, int vaga) throws IOException {
        File logArq = new File ("Log.txt");
        
        if (!logArq.exists()) {
            logArq.createNewFile();
        }
        
        FileWriter arquivo = new FileWriter (logArq, true);
        PrintWriter escreverArquivo = new PrintWriter (arquivo);
        
        switch (situacao) {
            case 1:                
                escreverArquivo.print (pegarDataEHoraDoSistema ()); //chama a função para imprimir a data e a hora do sistema
                escreverArquivo.println (" - Programa iniciado");
                break;
                
            case 2:
                escreverArquivo.print (pegarDataEHoraDoSistema ());
                escreverArquivo.println (" - Iniciada nova simulação");
                break;
                
            case 3:
                escreverArquivo.print (pegarDataEHoraDoSistema ());
                escreverArquivo.println (" - Simulação anterior carregada");
                break;
                
            case 4:
                escreverArquivo.print (pegarDataEHoraDoSistema ());
                escreverArquivo.println (" - Lista de veículos carregada");
                break;
                
            case 5:
                escreverArquivo.print (pegarDataEHoraDoSistema ());
                escreverArquivo.println (" - Lista de vagas carregada");
                break;
                
            case 6:
                escreverArquivo.print (pegarDataEHoraDoSistema ());
                escreverArquivo.println (" - Lista de vagas salvas anteriormente carregada");
                break;
                
            case 7:
                escreverArquivo.print (pegarDataEHoraDoSistema ());
                escreverArquivo.println (" - Lista de veículos salvos anteriormente carregados");
                break;
                
            case 8:
                escreverArquivo.print (pegarDataEHoraDoSistema ());
                escreverArquivo.print (" - Tentativa de alocação: ");
                if (condicao == 1) {//vaga alocada com sucesso
                    escreverArquivo.printf ("Chassi: %d Vaga: %d SUCESSO", chassi, vaga);
                    escreverArquivo.println ("");
                }   
                else {
                    escreverArquivo.printf ("Chassi: %d Vaga: %d FALHA", chassi, vaga);
                    escreverArquivo.println ("");
                }
                break;
                
            case 9:
                escreverArquivo.print (pegarDataEHoraDoSistema ());
                escreverArquivo.printf (" - Saida da garagem: Chassi: %d Vaga: %d", chassi, vaga);
                escreverArquivo.println ("");
                break;
                
            case 10:
                escreverArquivo.print (pegarDataEHoraDoSistema ());
                escreverArquivo.println (" - Programa salvo");
                break;
                
            case 11:
                escreverArquivo.print (pegarDataEHoraDoSistema ());
                escreverArquivo.println (" - Simulação encerrada");
                escreverArquivo.println ("==========================================================================");
                break;
                
            case 12:
               escreverArquivo.print (pegarDataEHoraDoSistema ());
               escreverArquivo.println (" - Relatório gerado");
               break;                        
        }
        
        escreverArquivo.close();
        arquivo.close();
            
    }
    
    public static Date pegarDataEHoraDoSistema () {
        Date data = new Date (); //pega hora
        return data;
    }
    
    public static void sair () throws IOException {
        gerarLog (11, 0, 0, 0);
        System.exit(0);
    }
}
