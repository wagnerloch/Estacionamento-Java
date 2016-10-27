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
public class Carro implements java.io.Serializable{ //MUDEI AQUI J.R
    private String modelo;
    private int numeroDoChassi;
    private int peso;
    private float altura;
    private float comprimento;
    private float largura;
    private boolean estacionado;
    private int vaga;
    
    /**
     * Método construtor da Classe Carro
     * @param modelo do Carro
     * @param numeroDoChassi do carro
     * @param peso do carro
     * @param altura do carro
     * @param comprimento do carro
     * @param largura do carro
     */
    
    public Carro (String modelo, int numeroDoChassi, int peso, float altura, float comprimento, float largura) {
        this.modelo = modelo;
        this.numeroDoChassi = numeroDoChassi;
        this.peso = peso;
        this.altura = altura;
        this.comprimento = comprimento;
        this.largura = largura;
        this.estacionado = false;
        this.vaga = 0;
    }
    
    /**
     * Pega o modelo do carro
     * @return modelo do carro
     */
    
    public String pegaModelo () {
        return modelo;
    }
    
    /**
     * Pega o número do chassi do carro
     * @return chassi do carro
     */
    
    public int pegaChassi () {
        return numeroDoChassi;
    }
    
    /**
     * Pega o peso do carro
     * @return peso do carro
     */
    
    public int pegaPeso () {
        return peso;
    }
    
    /**
     * Pega a altura do carro
     * @return altura do carro
     */
    
    public float pegaAltura () {
        return altura;
    }
    
    /**
     * Pega o comprimento do carro
     * @return comprimento do carro
     */
    
    public float pegaComprimento () {
        return comprimento;
    }
    
    /**
     * Pega a largura do carro
     * @return largura do carro
     */
    
    public float pegaLargura () {
        return largura;
    }
    
    /**
     * Diz se o carro está na lista de espera ou já foi estacionado
     * @return true se o carro já foi estacionado
     * @return false se o carro ainda está aguardando
     */
    
    public boolean pegaSituacao () {
        return estacionado;
    }
    
    public int pegaVaga () {
        return vaga;
    }
    
    public void alocarEmVaga (int vaga) {
        this.vaga = vaga;
        this.estacionado = true;
    }
}
