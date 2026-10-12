package dam.segundo_año.desarrollo_de_interfaces.fantasi;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GeneradorCampeones {

    // Imágenes oficiales de DataDragon (LoL), descargadas por URL
    private static final String URL_FOTOS = "https://ddragon.leagueoflegends.com/cdn/13.24.1/img/champion/";

    // Carpeta del proyecto, por si el editor no copia las imágenes al classpath (ej. VS Code)
    private static final String CARPETA_PROYECTO = "dam/segundo_año/desarrollo_de_interfaces/fantasi";

    public static ArrayList<Campeon> cargarMercado() {
        ArrayList<Campeon> mercado = new ArrayList<>();

        // ==================== TOP ====================
        mercado.add(new Campeon("Darius", "Luchador", 89, new ArrayList<>(List.of("TOP")), 14.5,
                URL_FOTOS + "Darius.png"));
        mercado.add(new Campeon("Malphite", "Tanque", 80, new ArrayList<>(List.of("TOP")), 8.0,
                URL_FOTOS + "Malphite.png"));

        // ==================== JUNGLA ====================
        mercado.add(new Campeon("Lee Sin", "Asesino", 92, new ArrayList<>(List.of("JG")), 18.0,
                URL_FOTOS + "LeeSin.png"));
        mercado.add(new Campeon("Warwick", "Luchador", 76, new ArrayList<>(List.of("JG")), 7.0,
                URL_FOTOS + "Warwick.png"));

        // ==================== MID ====================
        // Ahri usa una imagen incluida en el proyecto (carpeta imagenes)
        mercado.add(new Campeon("Ahri", "Mago", 90, new ArrayList<>(List.of("MID")), 15.0,
                "/imagenes/ahri.png"));
        mercado.add(new Campeon("Annie", "Mago", 75, new ArrayList<>(List.of("MID")), 7.0,
                URL_FOTOS + "Annie.png"));

        // ==================== ADC ====================
        mercado.add(new Campeon("Jinx", "Tirador", 88, new ArrayList<>(List.of("ADC")), 14.0,
                URL_FOTOS + "Jinx.png"));
        mercado.add(new Campeon("Ashe", "Tirador", 79, new ArrayList<>(List.of("ADC")), 8.0,
                URL_FOTOS + "Ashe.png"));

        // ==================== SOPORTE ====================
        mercado.add(new Campeon("Thresh", "Soporte", 91, new ArrayList<>(List.of("SUP")), 16.0,
                URL_FOTOS + "Thresh.png"));
        mercado.add(new Campeon("Sona", "Soporte", 76, new ArrayList<>(List.of("SUP")), 7.0,
                URL_FOTOS + "Sona.png"));

        return mercado;
    }

    // Guarda la imagen original de cada ruta para no cargarla más de una vez
    private static final HashMap<String, ImageIcon> originales = new HashMap<>();

    private static ImageIcon cargarIcono(String ruta) {
        if (originales.containsKey(ruta)) {
            return originales.get(ruta);
        }
        ImageIcon icono = null;
        try {
            if (ruta.startsWith("http")) {
                icono = new ImageIcon(new java.net.URI(ruta).toURL());              // desde internet
            } else {
                URL url = GeneradorCampeones.class.getResource(ruta);               // dentro del proyecto
                if (url != null) {
                    icono = new ImageIcon(url);
                } else {
                    icono = new ImageIcon(CARPETA_PROYECTO + ruta);                 // por ruta de archivo
                }
            }
            if (icono.getImageLoadStatus() != MediaTracker.COMPLETE) {
                icono = null;
            }
        } catch (Exception e) {
            icono = null;
        }
        if (icono == null) {
            System.out.println("No se pudo cargar la imagen: " + ruta);
        }
        originales.put(ruta, icono);
        return icono;
    }

    // Foto estirada a un tamaño exacto (diálogo)
    public static ImageIcon obtenerImagenEscalada(String ruta, int ancho, int alto) {
        if (ruta == null || ruta.isEmpty()) {
            return null;
        }
        ImageIcon original = cargarIcono(ruta);
        if (original == null) {
            return null;
        }
        return new ImageIcon(original.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH));
    }

    // Foto que rellena todo el recuadro SIN deformarse: se escala hasta cubrirlo y se recorta lo que sobra
    public static ImageIcon obtenerImagenRellena(String ruta, int ancho, int alto) {
        if (ruta == null || ruta.isEmpty()) {
            return null;
        }
        ImageIcon original = cargarIcono(ruta);
        if (original == null) {
            return null;
        }

        // 1. Escala suficiente para cubrir el recuadro en ancho Y en alto
        double escala = Math.max((double) ancho / original.getIconWidth(),
                (double) alto / original.getIconHeight());
        int nuevoAncho = (int) Math.ceil(original.getIconWidth() * escala);
        int nuevoAlto = (int) Math.ceil(original.getIconHeight() * escala);
        Image escalada = new ImageIcon(
                original.getImage().getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH)).getImage();

        // 2. Lienzo del tamaño exacto; la foto se dibuja centrada y lo que sobra queda fuera
        BufferedImage lienzo = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
        Graphics g = lienzo.getGraphics();
        g.drawImage(escalada, (ancho - nuevoAncho) / 2, (alto - nuevoAlto) / 2, null);
        g.dispose();

        return new ImageIcon(lienzo);
    }
}
