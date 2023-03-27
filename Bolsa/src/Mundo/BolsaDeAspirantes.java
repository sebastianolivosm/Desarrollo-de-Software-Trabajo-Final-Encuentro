package Mundo;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.Level;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.support.ConnectionSource;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Scanner;
import javax.swing.*;

public class BolsaDeAspirantes {
    public BolsaDeAspirantes() {
        JFrame frame = new JFrame("Bolsa de aspirantes");

        // Crear botones
        JButton btnAgregar = new JButton("Agregar nuevas hojas de vida de aspirantes");
        JButton btnMostrar = new JButton("Administrador bolsa de aspirantes");


        // Agregar botones al panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));
        panel.add(btnAgregar);
        panel.add(btnMostrar);



        // Agregar panel al frame
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        // Configurar el frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        // Agregar acciones a los botones
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    ManejadorBanco manejador = new ManejadorBanco();
                    manejador.main(new String[]{});
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                frame.setVisible(true);
            }
        });
        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear instancia del panel de ver cesulas
                VerCedulas ingresarPanel = new VerCedulas();


                // Esconder el panel principal de la bolsa de aspirantes
                frame.setVisible(false);
            }
        });

            }
            public static void main(String[] args) {
                BolsaDeAspirantes bda = new BolsaDeAspirantes();
            }
    }
