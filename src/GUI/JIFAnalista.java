package GUI;

import Domain.Analizador.Solicitud;
import Domain.Sistema.SistemaSingleton;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JIFAnalista extends javax.swing.JInternalFrame {

    private List<Solicitud> datosSolicitudes;
    private int oldDatosSolicitudesCount;

    private boolean flag_actualizar;
    private boolean flag_datosSolicitudesActualizados;

    private Thread hiloActualizarDatos;
    protected boolean hiloActualizarDatos_running;

    private Thread hiloActualizarGUI;
    protected boolean hiloActualizarGUI_running;

    private Solicitud solicitudSeleccionada;

    public JIFAnalista() {
        initComponents();
        Dimension jfSize = JFWindow.getInstance().getSize();
        Dimension jifSize = this.getSize();
        this.setLocation((jfSize.width - jifSize.width) / 2, (((jfSize.height - JFWindow.getInstance().jF_jMenuBar.getHeight()) - jifSize.height) / 2));
        this.show();
        this.jLabelActualizando.setText("");
        initThreads();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListSolicitudes = new javax.swing.JList<>();
        jButtonAnalizar = new javax.swing.JButton();
        jSpinnerSubprocesos = new javax.swing.JSpinner();
        jSpinnerEsclavos = new javax.swing.JSpinner();
        jLabelSubprocesos = new javax.swing.JLabel();
        jLabelEsclavos = new javax.swing.JLabel();
        jLabelActualizando = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Analista: "+SistemaSingleton.getInstance().getUsuario().getUsername());
        setVisible(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });

        jListSolicitudes.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListSolicitudesValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jListSolicitudes);

        jButtonAnalizar.setText("Analizar");
        jButtonAnalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAnalizarActionPerformed(evt);
            }
        });

        jSpinnerSubprocesos.setValue(1);
        jSpinnerSubprocesos.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerSubprocesosStateChanged(evt);
            }
        });

        jSpinnerEsclavos.setValue(1);
        jSpinnerEsclavos.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerEsclavosStateChanged(evt);
            }
        });

        jLabelSubprocesos.setForeground(new java.awt.Color(0, 0, 0));
        jLabelSubprocesos.setText("Subprocesos:");

        jLabelEsclavos.setForeground(new java.awt.Color(0, 0, 0));
        jLabelEsclavos.setText("Esclavos:");

        jLabelActualizando.setForeground(new java.awt.Color(0, 0, 0));
        jLabelActualizando.setText("[Actualizando]");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelActualizando)
                    .addComponent(jLabelEsclavos)
                    .addComponent(jSpinnerEsclavos, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelSubprocesos)
                    .addComponent(jSpinnerSubprocesos, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAnalizar))
                .addContainerGap(7, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelEsclavos)
                        .addGap(6, 6, 6)
                        .addComponent(jSpinnerEsclavos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabelSubprocesos)
                        .addGap(6, 6, 6)
                        .addComponent(jSpinnerSubprocesos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(jButtonAnalizar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelActualizando, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        // TODO add your handling code here:
        JFWindow.getInstance().jF_jMenuItemGestionAnalisis.setEnabled(true);
        killThreads();
    }//GEN-LAST:event_formInternalFrameClosed

    private void jButtonAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnalizarActionPerformed
        // TODO add your handling code here:
        actualizarSolicitudSeleccionada();
        //Aqui se hace el analisis de la solicitud selecionada
        //request - response
        if (this.solicitudSeleccionada != null) {
            if (SistemaSingleton.getInstance().analizarSolicitud(this.solicitudSeleccionada,
                    (int) this.jSpinnerSubprocesos.getValue(),
                    (int) this.jSpinnerEsclavos.getValue())) {
                System.out.println("solicitud en analisis");
                this.datosSolicitudes.remove(solicitudSeleccionada);
                synchronized (this) {
                    this.flag_actualizar = true;
                    this.flag_datosSolicitudesActualizados = true;
                    notifyAll();
                }
                this.jButtonAnalizar.setEnabled(false);
                this.jListSolicitudes.clearSelection();
            } else {
                System.out.println("solicituda no agregada");
            }
        } else {
            System.out.println("No hay solicitudes para analizar");
        }

    }//GEN-LAST:event_jButtonAnalizarActionPerformed

    private void jSpinnerEsclavosStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerEsclavosStateChanged
        // TODO add your handling code here:
        if ((int) this.jSpinnerEsclavos.getValue() <= 0) {
            this.jSpinnerEsclavos.setValue(1);
        }
    }//GEN-LAST:event_jSpinnerEsclavosStateChanged

    private void jSpinnerSubprocesosStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerSubprocesosStateChanged
        // TODO add your handling code here:
        if ((int) this.jSpinnerSubprocesos.getValue() <= 0) {
            this.jSpinnerSubprocesos.setValue(1);
        }
    }//GEN-LAST:event_jSpinnerSubprocesosStateChanged

    private void jListSolicitudesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListSolicitudesValueChanged
        // TODO add your handling code here:
        if (jListSolicitudes.getSelectedIndex() != -1) {
            this.jButtonAnalizar.setEnabled(true);
        }
    }//GEN-LAST:event_jListSolicitudesValueChanged

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        // TODO add your handling code here:
        if (!jListSolicitudes.isSelectionEmpty()) {
            this.jListSolicitudes.clearSelection();
            this.jButtonAnalizar.setEnabled(false);
        }
    }//GEN-LAST:event_jPanel1MousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAnalizar;
    private javax.swing.JLabel jLabelActualizando;
    private javax.swing.JLabel jLabelEsclavos;
    private javax.swing.JLabel jLabelSubprocesos;
    private javax.swing.JList<String> jListSolicitudes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinnerEsclavos;
    private javax.swing.JSpinner jSpinnerSubprocesos;
    // End of variables declaration//GEN-END:variables

    private void initThreads() {
        this.datosSolicitudes = new ArrayList<>();
        this.flag_actualizar = false;
        this.flag_datosSolicitudesActualizados = true;

        this.hiloActualizarDatos_running = true;
        this.hiloActualizarDatos = new Thread(new Runnable() {
            @Override
            public void run() {
                while (hiloActualizarDatos_running) {
                    try {
                        int n = 1000;
                        Thread.sleep(n);
                        actualizarDatos();
                        actualizarSolicitudSeleccionada();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(JIFGestor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                System.out.println(hiloActualizarDatos.getName() + " killed");
            }//run

        }, "hiloActualizarDatos_JIFAnalista");
        this.hiloActualizarDatos.start();

        this.hiloActualizarGUI_running = true;
        this.hiloActualizarGUI = new Thread(new Runnable() {
            @Override
            public void run() {
                while (hiloActualizarDatos_running) {
                    actualizarGUI();
                }
                System.out.println(hiloActualizarGUI.getName() + " killed");
            }//run

        }, "hiloActualizarGUI_JIFAnalista");
        this.hiloActualizarGUI.start();

    }//initThreads

    public synchronized void killThreads() {
        this.hiloActualizarDatos_running = false;
        this.hiloActualizarGUI_running = false;
        this.flag_actualizar = true;
        notifyAll();
    }//killThreads

    private synchronized void actualizarDatos() {
        //request - response
        this.flag_datosSolicitudesActualizados = false;
        if (SistemaSingleton.getInstance().getUsuario() != null) {
            oldDatosSolicitudesCount = datosSolicitudes.size();
            this.datosSolicitudes = SistemaSingleton.getInstance().misDatos(SistemaSingleton.getInstance().getUsuario().getUsername());
            if (datosSolicitudes.size() > oldDatosSolicitudesCount) {
                this.flag_datosSolicitudesActualizados = true;
                this.flag_actualizar = true;
                notifyAll();
            }
        }
    }//actualizarDatos

    private synchronized void actualizarGUI() {
        if (!flag_actualizar) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(JIFAdministrador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                int n = 200;
                this.jLabelActualizando.setText("Actualizando.");
                Thread.sleep(n);
                this.jLabelActualizando.setText("Actualizando..");
                Thread.sleep(n);
                this.jLabelActualizando.setText("Actualizando...");
                Thread.sleep(n);
                this.jLabelActualizando.setText("Actualizado!");
                this.flag_actualizar = false;
                actualizarLista();
                Thread.sleep(n * 2);
            } catch (InterruptedException ex) {
                Logger.getLogger(JIFDigitador.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//actualizarGUI

    private void actualizarLista() {
        String[] datosLista = new String[this.datosSolicitudes.size()];
        for (Solicitud solicitud : this.datosSolicitudes) {
            datosLista[datosSolicitudes.indexOf(solicitud)] = solicitud.data_getUrl();
        }
        jListSolicitudes.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() {
                return datosLista.length;
            }

            public String getElementAt(int i) {
                return datosLista[i];
            }
        });
    }//actualizarLista

    private void actualizarSolicitudSeleccionada() {
        for (Solicitud solicitud : this.datosSolicitudes) {
            if (solicitud.data_getUrl().equals(this.jListSolicitudes.getSelectedValue())) {
                this.solicitudSeleccionada = solicitud;
                break;
            }
        }
    }//actualizarSolicitudSeleccionada

}//class
