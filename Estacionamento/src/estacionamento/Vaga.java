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
public class Vaga implements java.io.Serializable{ //MUDEI AQUI J.R
    private int identificacao;
    private int pesoMaximo;
    private float alturaMaxima;
    private float comprimentoMaximo;
    private float larguraMaxima;
    private boolean ocupada;
    private int chassi; //chassi do veículo que está na vaga
    private String nomeDoVeiculoQueEstaAlocado;
    private int sucesso; //MUDEI AQUI J.R 
    private int falha; //MUDEI AQUI J.R
    
    /**
     * Método construtor da Classe Vaga
     * @param identificacao da vaga
     * @param pesoMaximo comportado pela vaga
     * @param alturaMaxima da vaga
     * @param comprimentoMaximo da vaga
     * @param larguraMaxima da vaga
     */
    
    public Vaga (int identificacao, int pesoMaximo, float alturaMaxima, float comprimentoMaximo, float larguraMaxima) {
        this.identificacao = identificacao;
        this.pesoMaximo = pesoMaximo;
        this.alturaMaxima = alturaMaxima;
        this.comprimentoMaximo = comprimentoMaximo;
        this.larguraMaxima = larguraMaxima;
        this.ocupada = false;
        this.chassi = 0;
        this.nomeDoVeiculoQueEstaAlocado = null;
        this.sucesso = 0; //MUDEI AQUI J.R
        this.falha = 0; //MUDEI AQUI J.R
    }
    
    /**
     * Pega a identificação da vaga
     * @return identificacao da vaga
     */
    
    public int pegaIdentificacao () {
        return identificacao;
    }
    
    /**
     * Pega o peso máximo da vaga
     * @return pesoMaximo da vaga
     */
    
    public int pegaPesoMaximo () {
        return pesoMaximo;
    }
    
    /**
     * Pega a altura máxima da vaga
     * @return alturaMaxima da vaga
     */
    
    public float pegaAlturaMaxima () {
        return alturaMaxima;
    }
    
    /**
     * Pega o comprimento máximo da vaga
     * @return comprimentoMaximo da vaga
     */
    
    public float pegaComprimentoMaximo () {
        return comprimentoMaximo;
    }
    
    /**
     * Pega a largura máxima da vaga
     * @return larguraMaxima da vaga
     */
    
    public float pegaLarguraMaxima () {
        return larguraMaxima;
    }
    
    /**
     * Pega se a vaga está ocupada ou não
     * @return true se a vaga está ocupada
     * @return false se a vaga estiver disponível
     */
    
    public boolean pegaDisponibilidade () {
        return ocupada;
    }
    
    /**
     * Pega o chassi do veículo que está na vaga
     * @return chassi do veículo que está na vaga
     */
    
    public int pegaChassi () {
        return chassi;
    }
    
    public void alocarVeiculo (int chassi, String nome) {
        this.chassi = chassi;
        this.ocupada = true;
        this.nomeDoVeiculoQueEstaAlocado = nome;
        this.sucesso++; //MUDEI AQUI J.R
    }
    
    public void alocarVeiculoFalha () {
        this.falha ++;
    }
    
    public void desalocarVeiculo () {
        this.chassi = 0;
        this.ocupada = false;
        this.nomeDoVeiculoQueEstaAlocado = null;
    }
    
    public String pegaVeiculoAlocado () {
        return this.nomeDoVeiculoQueEstaAlocado;
    }
    
    public int pegaSucesso () {
        return sucesso;
    }
    
    public int pegaFalha () {
        return falha;
    }
}
