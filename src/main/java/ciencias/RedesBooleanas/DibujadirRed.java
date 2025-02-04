package ciencias.RedesBooleanas;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class DibujadirRed {

    Grafica grafica;
    HashMap<String,Integer>tabla;
    public DibujadirRed(Grafica grafica, HashMap tabla){
        this.grafica=grafica;
        this.tabla=tabla;
    }

    public void dibujar(){
        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Generación de Red");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel mainPanel = new JPanel(new BorderLayout());
            frame.getContentPane().add(mainPanel);

            JPanel generationsPanel = new JPanel(new GridLayout(1, 1));
            JScrollPane scrollPane = new JScrollPane(generationsPanel);
            mainPanel.add(scrollPane, BorderLayout.CENTER);

            JButton guardarImagenButton = new JButton("Guardar Imagen");
            guardarImagenButton.addActionListener(e -> {
                guardarImagen(generationsPanel);
            });
            mainPanel.add(guardarImagenButton, BorderLayout.SOUTH);

            GeneracionRed gen = new GeneracionRed(grafica, tabla);

            System.out.println("Nodos");
            System.out.println(gen.getGrafica().toString());

            for (int i = 0; i < 50; i++) {
                Grafica grafica1 = gen.getGrafica();
                JPanel panel = new JPanel(new GridLayout(20, 1));

                Iterator<Integer> valores = grafica1.iterator();
                while (valores.hasNext()) {
                    int value = valores.next();
                    JPanel labelPanel = new JPanel();
                    labelPanel.setBackground(value == 1 ? Color.RED : Color.WHITE);
                    labelPanel.setBorder(new LineBorder(Color.BLACK));
                    panel.add(labelPanel);
                }

                generationsPanel.add(panel);
                generationsPanel.revalidate(); // Actualiza el diseño del panel

                // Desplazar el scroll hacia la derecha
                JScrollBar horizontalScrollBar = scrollPane.getHorizontalScrollBar();
                horizontalScrollBar.setValue(horizontalScrollBar.getMaximum());

                frame.pack();
                frame.setVisible(true);

                try {
                    Thread.sleep(100); // Pausa para visualizar cada iteración
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                gen.nuevaGeneracion();
            }
        });
    }

    private void guardarImagen(Component component) {
        BufferedImage image = new BufferedImage(component.getWidth(), component.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = image.createGraphics();
        component.paint(graphics2D);
        graphics2D.dispose();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Imagen");
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try {
                ImageIO.write(image, "png", fileToSave);
                System.out.println("Imagen guardada correctamente.");
            } catch (IOException ex) {
                System.err.println("Error al guardar la imagen: " + ex.getMessage());
            }
        }
    }
}
