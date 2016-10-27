/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estacionamento;

import static estacionamento.Estacionamento.catalogoDeCarros;
import com.google.common.collect.Ordering;
import com.google.common.collect.TreeMultimap;

/**
 *
 * @author Junior
 */
public class Estatistica {
    private static TreeMultimap<Float, Integer> mapList = TreeMultimap.create(Ordering.natural().reverse(), Ordering.natural());
    private static int longos = 0, curtos = 0, pesados = 0, leves = 0, altos = 0, baixos = 0, largos = 0, estreitos = 0;
    final float peso = 2500, comprimento = (float) 2.5, largura = (float) 1.6, altura = (float) 1.7;
    
    /**
     *
     * @param chassi
     */
    
    public void  addEstatistica(int chassi){
        float pesoCarro;
        
        if((pesoCarro = catalogoDeCarros.pegaPeso(chassi)) < peso)
            leves++;
        else
            pesados++;
        
        if(catalogoDeCarros.pegaAltura(chassi) < altura)
            baixos++;
        else
            altos++;
        
        if(catalogoDeCarros.pegaComprimento(chassi) < comprimento)
            curtos++;
        else
            longos++;
        
        if(catalogoDeCarros.pegaLargura(chassi) < largura)
            estreitos++;
        else
            largos++;
        
        mapList.put(pesoCarro, chassi);
    }
    
    public static TreeMultimap getMapList(){
        return mapList;
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

