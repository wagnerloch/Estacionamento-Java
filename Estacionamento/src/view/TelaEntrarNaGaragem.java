/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author wagne
 */
public class TelaEntrarNaGaragem extends javax.swing.JFrame {
    /**
     * Creates new form TelaEntrarNaGaragem
     */    
    public TelaEntrarNaGaragem() {
        initComponents();
        txtChassiVeiculo.setDocument(new SoNumeros());
        txtIdentificadorVaga.setDocument (new SoNumeros());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtChassiVeiculo = new javax.swing.JTextField();
        txtIdentificadorVaga = new javax.swing.JTextField();
        btnAlocarVeiculo = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Entrar na Garagem");
        setResizable(false);

        jLabel1.setText("Chassi do Veículo:");

        jLabel2.setText("Identificador da Vaga:");

        txtChassiVeiculo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtChassiVeiculoPropertyChange(evt);
            }
        });

        btnAlocarVeiculo.setText("Alocar Veículo");
        btnAlocarVeiculo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAlocarVeiculoMouseClicked(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtChassiVeiculo)
                    .addComponent(txtIdentificadorVaga, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(btnCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addComponent(btnAlocarVeiculo)
                .addGap(76, 76, 76))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtChassiVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtIdentificadorVaga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAlocarVeiculo)
                    .addComponent(btnCancelar))
                .addContainerGap(112, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseClicked
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnCancelarMouseClicked

    private void btnAlocarVeiculoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlocarVeiculoMouseClicked
        // TODO verificar se pode ser alocado
        int chassi = Integer.parseInt(txtChassiVeiculo.getText());
        int vaga = Integer.parseInt(txtIdentificadorVaga.getText());        
        
        if (TelaAbertura.catalogoDeCarros.pegaPeso(chassi) <= TelaAbertura.catalogoDeVagas.pegaPeso(vaga)) {
            if (TelaAbertura.catalogoDeCarros.pegaAltura(chassi) <= TelaAbertura.catalogoDeVagas.pegaAltura(vaga)) {
                if (TelaAbertura.catalogoDeCarros.pegaComprimento(chassi) <= TelaAbertura.catalogoDeVagas.pegaComprimento(vaga)) {
                    if (TelaAbertura.catalogoDeCarros.pegaLargura(chassi) <= TelaAbertura.catalogoDeVagas.pegaLargura(vaga)) {
                        if (TelaAbertura.catalogoDeCarros.pegaModelo(chassi) == null) {
                            JOptionPane.showMessageDialog(null,"Veículo não encontrado!");
                            return;
                        }
                        JOptionPane.showMessageDialog(null,"Veículo alocado com sucesso!");
                        TelaAbertura.catalogoDeCarros.alocar(chassi, vaga);
                        TelaAbertura.catalogoDeVagas.alocar(chassi, vaga, TelaAbertura.catalogoDeCarros.pegaModelo(chassi));
                        //alocado com sucesso
                        this.dispose();
                        return;
                    }
                }
            }
        }
        JOptionPane.showMessageDialog(null,"Veículo não alocado!");
        this.dispose();
         
    }//GEN-LAST:event_btnAlocarVeiculoMouseClicked

    private void txtChassiVeiculoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtChassiVeiculoPropertyChange
    }//GEN-LAST:event_txtChassiVeiculoPropertyChange


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlocarVeiculo;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtChassiVeiculo;
    private javax.swing.JTextField txtIdentificadorVaga;
    // End of variables declaration//GEN-END:variables
}
