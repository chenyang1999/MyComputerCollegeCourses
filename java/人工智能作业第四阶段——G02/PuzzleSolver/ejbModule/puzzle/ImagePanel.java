package puzzle;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private final Image img;

    public ImagePanel(final String filename) {
        this(getImage(filename));
    }

    public ImagePanel(final Image img) {
        this.img = img;
        final Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }

    public void paintComponent(final Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

    public static Image getImage(final String filename) {
        ImageIcon icon;
        final URL url = ImagePanel.class.getResource(filename);
        if (url != null) {
            icon = new ImageIcon(url);
        } else {
            // Read from file.
            icon = new ImageIcon(filename);

            // Try to read from URL.
            if ((icon == null) ||
                (icon.getImageLoadStatus() != MediaTracker.COMPLETE)) {
                try {
                    icon = new ImageIcon(new URL(filename));
                } catch (final MalformedURLException murle) {
                    // Not a URL.
                    return null;
                }
            }
        }
        return icon.getImage();
    }
}