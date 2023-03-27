package Mundo;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.logger.Level;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.support.ConnectionSource;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;

public class IngresarAspirante {
    public IngresarAspirante() {
        JFrame frame = new JFrame("Bolsa de aspirantes");

        // Configurar el panel de agregar aspirantes
        JPanel agregarPanel = new JPanel();
        agregarPanel.setLayout(new GridLayout(0, 2, 10, 10));
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();
        JLabel lblCedula = new JLabel("Cédula:");
        JTextField txtCedula = new JTextField();
        JLabel lblTelefono = new JLabel("Teléfono:");
        JTextField txtTelefono = new JTextField();
        JLabel lblProfesion = new JLabel("Profesión:");
        JTextField txtProfesion = new JTextField();
        JLabel lblExperiencia = new JLabel("Años de experiencia:");
        JTextField txtExperiencia = new JTextField();
        JLabel lblEdad = new JLabel("Edad:");
        JTextField txtEdad = new JTextField();
        JButton btnGuardar = new JButton("Guardar");
        JButton btnBorrar = new JButton("Borrar");
        JButton btnVolver = new JButton("Volver");
        agregarPanel.add(lblNombre);
        agregarPanel.add(txtNombre);
        agregarPanel.add(lblCedula);
        agregarPanel.add(txtCedula);
        agregarPanel.add(lblTelefono);
        agregarPanel.add(txtTelefono);
        agregarPanel.add(lblProfesion);
        agregarPanel.add(txtProfesion);
        agregarPanel.add(lblExperiencia);
        agregarPanel.add(txtExperiencia);
        agregarPanel.add(lblEdad);
        agregarPanel.add(txtEdad);
        agregarPanel.add(btnGuardar);
        agregarPanel.add(btnBorrar);
        agregarPanel.add(btnVolver);

        // Agregar panel de agregar aspirantes al frame
        frame.getContentPane().add(agregarPanel, BorderLayout.CENTER);

        // Configurar el frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        // Agregar acciones a los botones
        btnGuardar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                // Obtener los valores ingresados por el usuario
                Logger.setGlobalLogLevel(Level.OFF);
                // Ubicación del archivo de la base de datos
                String url = "jdbc:h2:file:./Bolsa de empleados";
                ConnectionSource conexion =
                        null;
                try {
                    conexion = new JdbcConnectionSource(url);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                // Obtener acceso a la lista de objetos=>Tabla (DAO)
                // Primero es la clase de la tabla, Segundo tipo de la llave

                Dao<Aspirantes, String> lista_aspirantes =
                        null;
                try {
                    lista_aspirantes = DaoManager.createDao(conexion, Aspirantes.class);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                String nombre = txtNombre.getText();
                int cedula = Integer.parseInt(txtCedula.getText());
                int telefono = Integer.parseInt(txtTelefono.getText());
                String profesion = txtProfesion.getText();
                int experiencia = Integer.parseInt(txtExperiencia.getText());
                int edad = Integer.parseInt(txtEdad.getText());


                // Limpiar los campos
                txtNombre.setText("");
                txtCedula.setText("");
                txtTelefono.setText("");
                txtProfesion.setText("");
                txtExperiencia.setText("");
                txtEdad.setText("");

                // Enfocar el campo de nombre
                txtNombre.requestFocus();
                Aspirantes aspirante = new Aspirantes (cedula , nombre, experiencia,edad,profesion,telefono);
                try {
                    lista_aspirantes.create(aspirante);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        btnBorrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Limpiar los campos
                txtNombre.setText("");
                txtCedula.setText("");
                txtTelefono.setText("");
                txtProfesion.setText("");
                txtExperiencia.setText("");
                txtEdad.setText("");

                // Enfocar el campo de nombre
                txtNombre.requestFocus();
            }
        });

        btnVolver.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    BolsaDeAspirantes   ingresarPanel = new BolsaDeAspirantes();


                    // Esconder el panel principal de la bolsa de aspirantes
                    frame.setVisible(false);
                }
            });
    }

    public static void main(String[] args) {
        new IngresarAspirante();
    }
}