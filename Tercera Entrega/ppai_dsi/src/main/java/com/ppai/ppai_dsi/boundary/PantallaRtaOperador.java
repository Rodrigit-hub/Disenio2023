// package com.ppai.ppai_dsi.boundary;

// Si quisieramos usar Swing, deberiamos realizar lo siguiente:

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

// public class PantallaRtaOperador {
//     public static void main(String[] args) {
//         SwingUtilities.invokeLater(() -> {
//             createAndShowGUI();
//         });
//     }

//     private static void createAndShowGUI() {
//         JFrame frame = new JFrame("Ejemplo de GUI con Swing");
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//         JPanel panel = new JPanel(new BorderLayout());

//         // Botones
//         JButton cancelButton = new JButton("Cancelar");
//         JButton confirmButton = new JButton("Confirmar");

//         // Grilla de validaciones
//         String[] columnNames = {"Validación"};
//         Object[][] data = {
//                 {"Validación 1"},
//                 {"Validación 2"},
//                 {"Validación 3"}
//         };

//         JTable validationsTable = new JTable(data, columnNames);
//         JScrollPane scrollPane = new JScrollPane(validationsTable);

//         // Manejadores de eventos para los botones
//         cancelButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 JOptionPane.showMessageDialog(frame, "Operación cancelada");
//             }
//         });

//         confirmButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 // Lógica de confirmación
//                 JOptionPane.showMessageDialog(frame, "Operación confirmada");
//             }
//         });

//         // Añadir componentes al panel
//         panel.add(cancelButton, BorderLayout.WEST);
//         panel.add(confirmButton, BorderLayout.EAST);
//         panel.add(scrollPane, BorderLayout.CENTER);

//         // Ajustar el tamaño, hacer visible y centrar la ventana
//         frame.add(panel);
//         frame.setSize(400, 300);
//         frame.setLocationRelativeTo(null);
//         frame.setVisible(true);
//     }
// }