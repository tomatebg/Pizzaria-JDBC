package pizzaria.view;

import javax.swing.JTextField;
import pizzaria.controller.ClienteController;

public class ClienteFormFilter extends javax.swing.JPanel {

    public ClienteFormFilter() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        c_cliente_busca = new javax.swing.JTextField();
        btn_cliente_busca = new javax.swing.JButton();

        jLabel1.setText("Busca:");

        btn_cliente_busca.setText("Pesquisar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(c_cliente_busca, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_cliente_busca)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(c_cliente_busca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cliente_busca))
                .addGap(10, 10, 10))
        );
    }// </editor-fold>//GEN-END:initComponents

    public JTextField getCampoBusca() {
        return c_cliente_busca;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cliente_busca;
    private javax.swing.JTextField c_cliente_busca;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

    public String getQueryParaFiltrar() {
        return c_cliente_busca.getText();
    }

    public void cleanQuery() {
        c_cliente_busca.setText(null);
    }

    public void setController(ClienteController controller) {
        this.btn_cliente_busca.addActionListener(e -> controller.filtrarClientes());
    }

}
