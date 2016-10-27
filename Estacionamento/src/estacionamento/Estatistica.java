/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estacionamento;

import static estacionamento.Estacionamento.catalogoDeCarros;
import java.util.ArrayList;

/**
 *
 * @author Junior
 */
public class Estatistica {
    private static ArrayList<ArrayList<Object>> matriz = new ArrayList<ArrayList<Object>>();
    private static int longos = 0, curtos = 0, pesados = 0, leves = 0, altos = 0, baixos = 0, largos = 0, estreitos = 0;
    private final float comprimento = (float) 2.5, largura = (float) 1.6, altura = (float) 1.7;
    private final int peso = 2500;
    
    /**
     *
     * @param chassi
     */
    
    public void  addEstatistica(int chassi){
        int pesoCarro;
        float alturaCarro, comprimentoCarro, larguraCarro;
        
        if((pesoCarro = catalogoDeCarros.pegaPeso(chassi)) < peso)
            leves++;
        else
            pesados++;
        
        if((alturaCarro = catalogoDeCarros.pegaAltura(chassi)) < altura)
            baixos++;
        else
            altos++;
        
        if((comprimentoCarro = catalogoDeCarros.pegaComprimento(chassi)) < comprimento)
            curtos++;
        else
            longos++;
        
        if((larguraCarro = catalogoDeCarros.pegaLargura(chassi)) < largura)
            estreitos++;
        else
            largos++;
        
        addMatriz (chassi, pesoCarro, alturaCarro, comprimentoCarro, larguraCarro, catalogoDeCarros.pegaModelo(chassi));
    }
    
    private static void addMatriz (int chassi, int pesoCarro, float alturaCarro, float comprimentoCarro, float larguraCarro, String modelo) {
        CarroMatriz carro = new CarroMatriz();
        boolean ok = false;
        int i;
        carro.chassi = chassi;
        carro.peso = pesoCarro;
        carro.altura = alturaCarro;
        carro.comprimento = comprimentoCarro;
        carro.largura = larguraCarro;
        carro.modelo = modelo;
        if (matriz.size() > 0) {
            for (i = 0; i < matriz.size(); i++) {
                CarroMatriz aux = (CarroMatriz) matriz.get(i).get(0);
                if (aux.peso == carro.peso) {
                    matriz.get(i).add(carro);
                    i = matriz.size();
                    ok = true;
                }
            }
            if (ok == false) {
                matriz.add(new ArrayList<Object>());
                matriz.get(i).add(carro);
            }
        }
        else {
            matriz.add(new ArrayList<Object>());
            matriz.get(0).add(carro);
        }
    }
    
    public static ArrayList<ArrayList<Object>> getMatriz() {
        return matriz;
    }
    
    public static int getLeves(){
        return leves;
    }
    
    public static int getPesados(){
        return pesados;
    }
    
    public static int getBaixos(){
        return baixos;
    }
    
    public static int getAltos(){
        return altos;
    }
    
    public static int getCurtos(){
        return curtos;
    }
    
    public static int getLongos(){
        return longos;
    }
    
    public static int getEstreitos(){
        return estreitos;
    }
    
    public static int getLargos(){
        return largos;
    }
    
}

