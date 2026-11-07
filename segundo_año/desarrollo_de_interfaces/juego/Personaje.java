import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * COMPONENTE BASE: todo lo que se mueve en el escenario es un Personaje.
 * Hereda de JLabel, así que se dibuja y se posiciona como cualquier componente Swing.
 * Si no encuentra la imagen, muestra un emoji como alternativa.
 *
 * Si se pasa ancho = 0, el ancho se calcula a partir del alto para que la imagen
 * no se deforme. Con espejo = true la imagen se voltea horizontalmente.
 */
public class Personaje extends JLabel {

    public Personaje(String ruta, String alternativa,
                     int x, int y, int ancho, int alto) {
        this(ruta, alternativa, x, y, ancho, alto, false);
    }

    public Personaje(String ruta, String alternativa,
                     int x, int y, int ancho, int alto, boolean espejo) {

        super();

        ImageIcon icono = cargarIcono(ruta);

        if (icono != null) {
            if (ancho <= 0) {
                ancho = Math.round(alto * (float) icono.getIconWidth()
                                        / icono.getIconHeight());
            }
            setIcon(new ImageIcon(prepararImagen(icono.getImage(), ancho, alto, espejo)));
        } else {
            if (ancho <= 0) {
                ancho = Math.round(alto * 1.2f);
            }
            setText(alternativa);
            // Swing pinta los emojis de un solo color: en blanco se ven sobre carretera y hierba
            setForeground(Color.WHITE);
            setFont(new Font("Segoe UI Emoji", Font.PLAIN, Math.round(alto * 0.8f)));
            setHorizontalAlignment(SwingConstants.CENTER);
        }

        setBounds(x, y, ancho, alto);
    }

    /**
     * Carga la imagen sin depender de desde dónde se ejecute el programa.
     * Primero la busca en el classpath y, si no está, como archivo (ver buscarArchivo).
     * Devuelve null si no la encuentra o no es una imagen válida.
     */
    private static ImageIcon cargarIcono(String ruta) {
        ImageIcon icono = null;

        URL url = Personaje.class.getResource("/" + ruta);
        if (url != null) {
            icono = new ImageIcon(url);
        } else {
            Path archivo = buscarArchivo(ruta);
            if (archivo != null) {
                icono = new ImageIcon(archivo.toString());
            }
        }

        return (icono != null && icono.getIconWidth() > 0) ? icono : null;
    }

    // Resultado de cada búsqueda (también si no se encontró), para no repetirla por cada coche
    private static final Map<String, Path> ENCONTRADOS = new HashMap<>();

    private static Path buscarArchivo(String ruta) {
        if (!ENCONTRADOS.containsKey(ruta)) {
            ENCONTRADOS.put(ruta, buscarArchivoEnDisco(ruta));
        }
        return ENCONTRADOS.get(ruta);
    }

    /**
     * Busca el archivo en el disco, por este orden:
     * 1) subiendo carpetas desde la carpeta de trabajo y desde las clases compiladas;
     * 2) bajando hasta 6 niveles de subcarpetas desde la carpeta de trabajo.
     * Así funciona desde la terminal y también desde un IDE (como VS Code) que ejecute
     * desde la carpeta del espacio de trabajo en vez de desde la carpeta del juego.
     */
    private static Path buscarArchivoEnDisco(String ruta) {
        List<Path> inicios = new ArrayList<>();
        inicios.add(Paths.get("").toAbsolutePath());
        try {
            inicios.add(Paths.get(Personaje.class.getProtectionDomain()
                    .getCodeSource().getLocation().toURI()));
        } catch (Exception e) {
            // Sin ubicación de las clases: solo se usa la carpeta de trabajo
        }

        for (Path inicio : inicios) {
            Path carpeta = inicio;
            for (int nivel = 0; carpeta != null && nivel < 5; nivel++) {
                Path candidato = carpeta.resolve(ruta);
                if (Files.isRegularFile(candidato)) {
                    return candidato;
                }
                carpeta = carpeta.getParent();
            }
        }

        try (Stream<Path> archivos = Files.walk(inicios.get(0), 6)) {
            return archivos
                    .filter(p -> p.endsWith(ruta) && Files.isRegularFile(p))
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    /** Escala la imagen al tamaño pedido y, si hace falta, la voltea. */
    private static Image prepararImagen(Image origen, int ancho, int alto, boolean espejo) {
        // ImageIcon espera a que la imagen escalada esté cargada del todo
        Image escalada = new ImageIcon(
                origen.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH)).getImage();

        BufferedImage resultado = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resultado.createGraphics();
        if (espejo) {
            g.drawImage(escalada, ancho, 0, -ancho, alto, null);
        } else {
            g.drawImage(escalada, 0, 0, null);
        }
        g.dispose();
        return resultado;
    }

    public void mover(int dx, int dy) {
        setLocation(getX() + dx, getY() + dy);
    }

    public boolean colisionaCon(Personaje otro) {
        return getBounds().intersects(otro.getBounds());
    }
}
