package GUI;

import Domain.Analizador.Solicitud;
import Domain.Sistema.SistemaSingleton;
import Utility.Utility;
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListSolicitudesSinAnalizar = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBoxEnlaces = new javax.swing.JCheckBox();
        jLabelAnalisis2CrearSolicitud = new javax.swing.JLabel();
        jCheckBoxImagenes = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jSpinnerEsclavos = new javax.swing.JSpinner();
        jButtonAnalizar = new javax.swing.JButton();
        jSpinnerSubprocesos = new javax.swing.JSpinner();
        jLabelSubprocesos = new javax.swing.JLabel();
        jLabelEsclavos = new javax.swing.JLabel();
        jLabelActualizando = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListSolicitudesAnalizadas = new javax.swing.JList<>();

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

        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
        });

        jListSolicitudesSinAnalizar.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListSolicitudesSinAnalizarValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jListSolicitudesSinAnalizar);

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Análisis solicitados:");

        jCheckBox1.setForeground(new java.awt.Color(0, 0, 0));
        jCheckBox1.setText("Análisis de Elementos");
        jCheckBox1.setEnabled(false);

        jCheckBoxEnlaces.setForeground(new java.awt.Color(0, 0, 0));
        jCheckBoxEnlaces.setText("Enlaces");
        jCheckBoxEnlaces.setEnabled(false);

        jLabelAnalisis2CrearSolicitud.setForeground(new java.awt.Color(0, 0, 0));
        jLabelAnalisis2CrearSolicitud.setText("Análisis de Elementos y Extracción:");

        jCheckBoxImagenes.setForeground(new java.awt.Color(0, 0, 0));
        jCheckBoxImagenes.setText("Imágenes");
        jCheckBoxImagenes.setEnabled(false);

        jCheckBox3.setForeground(new java.awt.Color(0, 0, 0));
        jCheckBox3.setText("Análisis de Elementos y Comparación");
        jCheckBox3.setEnabled(false);
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        jSpinnerEsclavos.setValue(1);
        jSpinnerEsclavos.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerEsclavosStateChanged(evt);
            }
        });

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

        jLabelSubprocesos.setForeground(new java.awt.Color(0, 0, 0));
        jLabelSubprocesos.setText("Subprocesos:");

        jLabelEsclavos.setForeground(new java.awt.Color(0, 0, 0));
        jLabelEsclavos.setText("Esclavos:");

        jLabelActualizando.setForeground(new java.awt.Color(0, 0, 0));
        jLabelActualizando.setText("[Actualizando]");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox1)
                            .addComponent(jCheckBox3)
                            .addComponent(jLabelAnalisis2CrearSolicitud)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBoxImagenes)
                                    .addComponent(jCheckBoxEnlaces))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 261, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSpinnerSubprocesos, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelEsclavos)
                            .addComponent(jSpinnerEsclavos, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelSubprocesos)
                            .addComponent(jButtonAnalizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelActualizando)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelEsclavos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerEsclavos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabelSubprocesos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerSubprocesos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonAnalizar))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelAnalisis2CrearSolicitud)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxEnlaces)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxImagenes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jLabelActualizando, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Analizar", jPanel2);

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Solicitudes analizadas:");

        jListSolicitudesAnalizadas.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListSolicitudesAnalizadasValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jListSolicitudesAnalizadas);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Listo", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                this.datosSolicitudes.remove(this.solicitudSeleccionada);
                synchronized (this) {
                    this.flag_actualizar = true;
                    this.flag_datosSolicitudesActualizados = true;
                    notifyAll();
                }
                this.jButtonAnalizar.setEnabled(false);
                this.jListSolicitudesSinAnalizar.clearSelection();
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

    private void jListSolicitudesSinAnalizarValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListSolicitudesSinAnalizarValueChanged
        // TODO add your handling code here:
        if (jListSolicitudesSinAnalizar.getSelectedIndex() != -1) {
            this.jButtonAnalizar.setEnabled(true);
            actualizarSolicitudSeleccionada();
            actualizarOthers();
        } else {
            this.solicitudSeleccionada = null;
        }

    }//GEN-LAST:event_jListSolicitudesSinAnalizarValueChanged

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        // TODO add your handling code here:
        if (!jListSolicitudesSinAnalizar.isSelectionEmpty()) {
            this.jListSolicitudesSinAnalizar.clearSelection();
            this.jButtonAnalizar.setEnabled(false);
        }
    }//GEN-LAST:event_jPanel1MousePressed

    private void jListSolicitudesAnalizadasValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListSolicitudesAnalizadasValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jListSolicitudesAnalizadasValueChanged

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jPanel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MousePressed
        // TODO add your handling code here:
        this.solicitudSeleccionada = null;
        actualizarOthers();
        this.jListSolicitudesSinAnalizar.clearSelection();

    }//GEN-LAST:event_jPanel2MousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAnalizar;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBoxEnlaces;
    private javax.swing.JCheckBox jCheckBoxImagenes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelActualizando;
    private javax.swing.JLabel jLabelAnalisis2CrearSolicitud;
    private javax.swing.JLabel jLabelEsclavos;
    private javax.swing.JLabel jLabelSubprocesos;
    private javax.swing.JList<String> jListSolicitudesAnalizadas;
    private javax.swing.JList<String> jListSolicitudesSinAnalizar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSpinner jSpinnerEsclavos;
    private javax.swing.JSpinner jSpinnerSubprocesos;
    private javax.swing.JTabbedPane jTabbedPane1;
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
            if (datosSolicitudes.size() > oldDatosSolicitudesCount||datosSolicitudes.size() < oldDatosSolicitudesCount) {
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
                actualizarListas();
                actualizarOthers();
                Thread.sleep(n * 2);
            } catch (InterruptedException ex) {
                Logger.getLogger(JIFDigitador.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//actualizarGUI

    private void actualizarListas() {
        String[] datosLista = new String[this.datosSolicitudes.size()];
        for (Solicitud solicitud : this.datosSolicitudes) {
            if (!solicitud.data_getEstado().equals(Utility.ESTADO_FINALIZADO)) {
                datosLista[datosSolicitudes.indexOf(solicitud)] = solicitud.data_getUrl();
            }
        }
        jListSolicitudesSinAnalizar.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() {
                return datosLista.length;
            }

            public String getElementAt(int i) {
                return datosLista[i];
            }
        });

        String[] datosLista2 = new String[this.datosSolicitudes.size()];
        for (Solicitud solicitud : this.datosSolicitudes) {
            if (solicitud.data_getEstado().equals(Utility.ESTADO_FINALIZADO)) {
                datosLista2[datosSolicitudes.indexOf(solicitud)] = solicitud.data_getUrl();
            }
        }
        jListSolicitudesAnalizadas.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() {
                return datosLista2.length;
            }

            public String getElementAt(int i) {
                return datosLista2[i];
            }
        });

    }//actualizarListas

    private void actualizarSolicitudSeleccionada() {
        for (Solicitud solicitud : this.datosSolicitudes) {
            if (solicitud.data_getUrl().equals(this.jListSolicitudesSinAnalizar.getSelectedValue())) {
                this.solicitudSeleccionada = solicitud;
                break;
            }
        }
    }//actualizarSolicitudSeleccionada

    private void actualizarOthers() {
        if (this.solicitudSeleccionada != null) {
            this.jCheckBox1.setSelected(this.solicitudSeleccionada.doAnalisis1());
            this.jCheckBoxImagenes.setSelected(this.solicitudSeleccionada.doAnalisis2_extract_img());
            this.jCheckBoxEnlaces.setSelected(this.solicitudSeleccionada.doAnalisis2_extract_links());
            this.jCheckBox3.setSelected(this.solicitudSeleccionada.doAnalisis3());
        } else {
            this.jButtonAnalizar.setEnabled(false);
            this.jCheckBox1.setSelected(false);
            this.jCheckBoxImagenes.setSelected(false);
            this.jCheckBoxEnlaces.setSelected(false);
            this.jCheckBox3.setSelected(false);
        }
    }//actualizarOthers

}//class
