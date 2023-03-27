package Mundo;

import Mundo.Aspirantes;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.Level;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VerCedulas {
    public VerCedulas() {
        JFrame frame = new JFrame("Administrador");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel verPanel = new JPanel(new BorderLayout());
        String[] columnNames = {"Cédula", "Nombre", "Profesión", "Edad", "Experiencia", "Teléfono"};

        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        Logger.setGlobalLogLevel(Level.OFF);
        String url = "jdbc:h2:file:./Bolsa de empleados";
        ConnectionSource conexion = null;
        try {
            conexion = new JdbcConnectionSource(url);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        Dao<Aspirantes, String> lista_aspirantes = null;
        try {
            lista_aspirantes = DaoManager.createDao(conexion, Aspirantes.class);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        List<Aspirantes> aspirantes = new ArrayList<>();
        try {
            aspirantes = lista_aspirantes.queryForAll();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        for (Aspirantes aspirante : aspirantes) {
            Object[] row = {aspirante.getCedula(), aspirante.getNombre(), aspirante.getProfesion(), aspirante.getEdad(), aspirante.getExperienciaA(), aspirante.getTelefono()};
            tableModel.addRow(row);
        }
        table.setModel(tableModel);
        verPanel.add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(verPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setSize(1200, 900);
        frame.setVisible(true);
        JPanel searchPanel = new JPanel(new FlowLayout());

        //BOTONES


        //lARGO DE EL CUADRO DE INGRESO
        JTextField searchField = new JTextField(10);
        JButton searchButton = new JButton("Buscar");
        searchPanel.add(new JLabel("Buscar por nombre:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        JTextField searchCedulaField = new JTextField(10);
        JButton searchCedulaButton = new JButton("Buscar por cédula");
        JPanel searchPanel2 = new JPanel(new FlowLayout());
        searchPanel2.add(new JLabel("Buscar por cédula:"));

        JTextField filterField = new JTextField(10);
        JButton filterButton = new JButton("Filtrar");
        searchPanel.add(new JLabel("Eliminar registros con experiencia menor o igual"));
        searchPanel.add(filterField);
        searchPanel.add(filterButton);
        searchPanel2.add(searchCedulaField);
        searchPanel2.add(searchCedulaButton);
        searchPanel2.setAlignmentX(Component.LEFT_ALIGNMENT); // Alinea el panel a la izquierda




        JButton searchmaxexperiencia = new JButton("Buscar por mayor experiencia");

        JButton searchmasjoven= new JButton("Aspirante más joven");

        JButton eliminarButton = new JButton("Eliminar o Contratar");
        JButton promedioEdadButton = new JButton("Promedio de Edad");
        JButton btnOrdenarExperiencia= new JButton("Ordenar de mayor a menor Experiencia");
        JButton searchMaxEdad= new JButton("Ordenar de mayor a menor Edad");
        JButton searchordenarprofesion =new JButton("Ordenar A-Z Porfesion");






        JPanel searchContainer = new JPanel(new GridLayout(9,2));
        searchContainer.add(searchPanel);
        searchContainer.add(searchPanel2);
        searchContainer.add(searchmaxexperiencia);
        searchContainer.add(searchmasjoven);
        searchContainer.add(eliminarButton);
        searchContainer.add(promedioEdadButton);
        searchContainer.add(btnOrdenarExperiencia);
        searchContainer.add(searchMaxEdad);
        searchContainer.add(searchordenarprofesion);

        searchContainer.setAlignmentX(Component.LEFT_ALIGNMENT); // Alinea el contenedor a la izquierda

        verPanel.add(searchContainer, BorderLayout.NORTH);




        JButton volverButton = new JButton("Volver");
        verPanel.add(volverButton, BorderLayout.SOUTH);




        //ACCIONES

        Dao<Aspirantes, String> daoAspirantes = lista_aspirantes;
        searchMaxEdad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                QueryBuilder<Aspirantes, String> queryBuilder = daoAspirantes.queryBuilder();
                queryBuilder.orderBy("edad", false); // Ordenar por edad en orden descendente


                List<Aspirantes> resultados = new ArrayList<>();
                try {
                    resultados = daoAspirantes.query(queryBuilder.prepare());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                tableModel.setRowCount(0);
                for (Aspirantes aspirante : resultados) {
                    Object[] row = {aspirante.getCedula(), aspirante.getNombre(), aspirante.getProfesion(), aspirante.getEdad(), aspirante.getExperienciaA(), aspirante.getTelefono()};
                    tableModel.addRow(row);
                }
            }
        });

        searchordenarprofesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                QueryBuilder<Aspirantes, String> queryBuilder = daoAspirantes.queryBuilder();
                queryBuilder.orderBy("profesion", true); // Ordenar por profesión en orden ascendente

                List<Aspirantes> resultados = new ArrayList<>();
                try {
                    resultados = daoAspirantes.query(queryBuilder.prepare());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                tableModel.setRowCount(0);
                for (Aspirantes aspirante : resultados) {
                    Object[] row = {aspirante.getCedula(), aspirante.getNombre(), aspirante.getProfesion(), aspirante.getEdad(), aspirante.getExperienciaA(), aspirante.getTelefono()};
                    tableModel.addRow(row);
                }
            }
        });




// Agregar el ActionListener al botón Filtrar

        Dao<Aspirantes, String> finalLista_aspirantes2 = lista_aspirantes;
        ConnectionSource finalConexion = conexion;
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el valor del JTextField
                String filterValue = filterField.getText().trim();

                // Verificar que se ha ingresado un valor
                if (filterValue.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Por favor ingrese un valor para filtrar.");
                    return;
                }

                // filtrar los registros de la tabla

                try {
                    QueryBuilder<Aspirantes, String> queryBuilder = finalLista_aspirantes2.queryBuilder();
                    queryBuilder.where().le("experienciaA", filterValue);
                    List<Aspirantes> filteredAspirantes = finalLista_aspirantes2.query(queryBuilder.prepare());

                    // Eliminar los registros filtrados
                    Dao<Aspirantes, String> lista_aspirantes = DaoManager.createDao(finalConexion, Aspirantes.class);
                    for (Aspirantes aspirante : filteredAspirantes) {
                        lista_aspirantes.delete(aspirante);
                    }

                    // Actualizar la tabla con los registros filtrados
                    tableModel.setRowCount(0);
                    for (Aspirantes aspirante : filteredAspirantes) {
                        Object[] row = {aspirante.getCedula(), aspirante.getNombre(), aspirante.getProfesion(), aspirante.getEdad(), aspirante.getExperienciaA(), aspirante.getTelefono()};
                        tableModel.addRow(row);
                    }

                    // Mostrar un mensaje de éxito
                    JOptionPane.showMessageDialog(frame, "Registros que visualizas,  fueron los eliminados");
                } catch (SQLException ex) {
                }
            }
        });


// Crear el ActionListener para el botón Eliminar
        Dao<Aspirantes, String> finalLista_aspirantes1 = lista_aspirantes;
        List<Aspirantes> finalAspirantes = aspirantes;

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los índices de las filas seleccionadas
                int[] selectedRows = table.getSelectedRows();

                // Verificar que se han seleccionado filas
                if (selectedRows.length == 0) {
                    JOptionPane.showMessageDialog(frame, "Por favor seleccione al menos una fila para eliminar.");
                    return;
                }

                // Pedir confirmación antes de eliminar los registros
                int confirmacion = JOptionPane.showConfirmDialog(frame, "¿Está seguro que desea eliminar los registros seleccionados?",
                        "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (confirmacion != JOptionPane.YES_OPTION) {
                    return;
                }

                // Eliminar los registros seleccionados de la base de datos
                try {
                    for (int i = selectedRows.length - 1; i >= 0; i--) {
                        Aspirantes aspirante = finalAspirantes.get(selectedRows[i]);
                        finalLista_aspirantes1.delete(aspirante);
                        tableModel.removeRow(selectedRows[i]);
                    }
                    JOptionPane.showMessageDialog(frame, "Registros eliminados exitosamente.");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BolsaDeAspirantes   ingresarPanel = new BolsaDeAspirantes();


                // Esconder el panel principal de la bolsa de aspirantes
                frame.setVisible(false);
            }
        });


        Dao<Aspirantes, String> daoAspirantes2 = lista_aspirantes;
        searchCedulaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cedulaBusqueda = Integer.parseInt(searchCedulaField.getText());

                QueryBuilder<Aspirantes, String> queryBuilder = daoAspirantes2.queryBuilder();
                try {
                    queryBuilder.where().eq("cedula", cedulaBusqueda);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                List<Aspirantes> resultados = new ArrayList<>();
                try {
                    resultados = daoAspirantes2.query(queryBuilder.prepare());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                tableModel.setRowCount(0);
                for (Aspirantes aspirante : resultados) {
                    Object[] row = {aspirante.getCedula(), aspirante.getNombre(), aspirante.getProfesion(), aspirante.getEdad(), aspirante.getExperienciaA(), aspirante.getTelefono()};
                    tableModel.addRow(row);
                }
            }
        });

        // BOTON PROMEDIO GENERA UNA VENTANA DE TEXTO
        List<Aspirantes> finalAspirantes1 = aspirantes;
        promedioEdadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el promedio de edad de los aspirantes
                double promedioEdad = finalAspirantes1.stream()
                        .mapToInt(Aspirantes::getEdad)
                        .average()
                        .orElse(0.0);

                // Mostrar el resultado en un cuadro de diálogo
                JOptionPane.showMessageDialog(frame, "El promedio de edad de los aspirantes es: " + promedioEdad);
            }
        });
        searchmaxexperiencia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                QueryBuilder<Aspirantes, String> queryBuilder = daoAspirantes.queryBuilder();
                queryBuilder.orderBy("experienciaA", false);
                // Ordenar por experiencia en orden descendente
                queryBuilder.limit(1L);
                //Aqui solo trae el primer registro

                List<Aspirantes> resultados = new ArrayList<>();
                try {
                    resultados = daoAspirantes.query(queryBuilder.prepare());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                tableModel.setRowCount(0);
                for (Aspirantes aspirante : resultados) {
                    Object[] row = {aspirante.getCedula(), aspirante.getNombre(), aspirante.getProfesion(), aspirante.getEdad(), aspirante.getExperienciaA(), aspirante.getTelefono()};
                    tableModel.addRow(row);
                }
            }
        });


        btnOrdenarExperiencia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                QueryBuilder<Aspirantes, String> queryBuilder = daoAspirantes.queryBuilder();
                queryBuilder.orderBy("experienciaA", false);
                // Ordenar por experiencia en orden descendente o decen

                List<Aspirantes> resultados = new ArrayList<>();
                try {
                    resultados = daoAspirantes.query(queryBuilder.prepare());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                tableModel.setRowCount(0);
                for (Aspirantes aspirante : resultados) {
                    Object[] row = {aspirante.getCedula(), aspirante.getNombre(), aspirante.getProfesion(), aspirante.getEdad(), aspirante.getExperienciaA(), aspirante.getTelefono()};
                    tableModel.addRow(row);
                }
            }
        });

        searchmasjoven.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear un QueryBuilder para construir la consulta SQL
                QueryBuilder<Aspirantes, String> queryBuilder = daoAspirantes.queryBuilder();

                // Ordenar por edad en orden ascendente
                queryBuilder.orderBy("Edad", true);

                // Limitar a un solo resultado
                queryBuilder.limit(1L);

                // Ejecutar la consulta y obtener los resultados en una lista
                List<Aspirantes> resultados = new ArrayList<>();
                try {
                    resultados = daoAspirantes.query(queryBuilder.prepare());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                // Actualizar la tabla con los resultados obtenidos
                tableModel.setRowCount(0);
                for (Aspirantes aspirante : resultados) {
                    Object[] row = {aspirante.getCedula(), aspirante.getNombre(), aspirante.getProfesion(), aspirante.getEdad(), aspirante.getExperienciaA(), aspirante.getTelefono()};
                    tableModel.addRow(row);
                }
            }
        });


//boton busqueda linea de arriba
        Dao<Aspirantes, String> finalLista_aspirantes = lista_aspirantes;
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreBusqueda = searchField.getText();

                QueryBuilder<Aspirantes, String> queryBuilder = finalLista_aspirantes.queryBuilder();
                try {
                    queryBuilder.where().like("nombre", "%" + nombreBusqueda + "%");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                List<Aspirantes> resultados = new ArrayList<>();
                try {
                    resultados = finalLista_aspirantes.query(queryBuilder.prepare());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                tableModel.setRowCount(0);
                for (Aspirantes aspirante : resultados) {
                    Object[] row = {aspirante.getCedula(), aspirante.getNombre(), aspirante.getProfesion(), aspirante.getEdad(), aspirante.getExperienciaA(), aspirante.getTelefono()};
                    tableModel.addRow(row);
                }
            }
        });
    }

    public static void main(String[] args) {
        new VerCedulas();
    }
}


