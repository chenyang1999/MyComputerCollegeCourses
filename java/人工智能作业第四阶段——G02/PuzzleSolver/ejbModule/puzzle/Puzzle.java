package puzzle;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class Puzzle extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final Color DARK_GRAY = new Color(64, 64, 64);
    private Image[] imageArray;
    private Image movingImage;
    private int numOfTiles, dimension, imageWidth, imageHeight, panelWidth, panelHeight,
                r0, r1, c0, c1, x, y, movingCoord, sleepMs;
    private boolean animationDone = true;
    private byte[] state;

    public Puzzle(final int numOfTiles) {
        super();
        setNumOfTiles(numOfTiles);
        setSize(panelWidth, panelHeight);
        setDoubleBuffered(true);
    }

    public void setNumOfTiles(final int numOfTiles) {
        this.numOfTiles = numOfTiles;
        dimension = (int)Math.sqrt(numOfTiles);
        imageArray = new Image[numOfTiles];
        final String file = "images/" + (numOfTiles - 1) + "-puzzle/image_";
        imageArray[0] = null;
        for (int i = 1; i < numOfTiles; ++i) {
            final StringBuilder builder = new StringBuilder(file);
            if (i <= 9) {
                builder.append("0");
            }
            builder.append(Integer.toString(i));
            builder.append(".png");
            imageArray[i] = ImagePanel.getImage(builder.toString());
        }
        imageWidth  = imageArray[1].getWidth(null);
        imageHeight = imageArray[1].getHeight(null);
        panelWidth  = imageWidth * dimension;
        panelHeight = imageHeight * dimension;
        state = new byte[numOfTiles];
        sleepMs = 500 / imageWidth;
        animationDone = true;
        resetOrder();
    }

    public void resetOrder() {
        for (int i = 0; i < numOfTiles - 1; ++i) {
            state[i] = (byte)(i + 1);
        }
        state[numOfTiles - 1] = 0;
        repaint();
    }

    public void setOrder(final byte[] state) {
        System.arraycopy(state, 0, this.state, 0, state.length);
        repaint();
    }

    public void stopAnimation() {
        animationDone = true;
    }

    public void animatePuzzleTo(final byte[] newState) {
        int newPosOfTile = 0, currentPosOfTile = 0;
        for (int i = 0; i < numOfTiles; ++i) {
            if (state[i] == 0) {
                newPosOfTile = i;
            }
            if (newState[i] == 0) {
                currentPosOfTile = i;
            }
        }

        r0 = newPosOfTile / dimension;
        c0 = newPosOfTile % dimension;
        r1 = currentPosOfTile / dimension;
        c1 = currentPosOfTile % dimension;
        movingImage = imageArray[state[currentPosOfTile]];
        System.arraycopy(newState, 0, state, 0, newState.length);

        if (r0 == r1) {
            movingCoord = c1 * imageWidth;
        } else {
            movingCoord = r1 * imageHeight;
        }

        final Thread t = new Thread(new Runnable() {
            public void run() {
                x = c0 * imageWidth;
                y = r0 * imageHeight;
                animationDone = false;
                while (!animationDone) {
                    if (r0 == r1) {
                        if (c1 < c0) {
                            if (movingCoord < x) {
                                ++movingCoord;
                            } else {
                                animationDone = true;
                            }
                        } else {
                            if (movingCoord > x) {
                                --movingCoord;
                            } else {
                                animationDone = true;
                            }
                        }
                    } else {
                        if (r1 < r0) {
                            if (movingCoord < y) {
                                ++movingCoord;
                            } else {
                                animationDone = true;
                            }
                        } else {
                            if (movingCoord > y) {
                                --movingCoord;
                            } else {
                                animationDone = true;
                            }
                        }
                    }
                    repaint();
                    try {
                        Thread.sleep(sleepMs);
                    } catch (final InterruptedException ie) { }
                }
            }
        });

        t.start();
        try {
            t.join();
        } catch (final InterruptedException ie) { }
    }

    public Dimension getPreferredSize() {
        return new Dimension(panelWidth, panelHeight);
    }

    protected void paintComponent(final Graphics g) {
        int pos = 0;
        for (int row = 0; row < dimension; ++row) {
            for (int col = 0; col < dimension; ++col) {
                if (state[pos] != 0) {
                    final Image image = imageArray[state[pos]];
                    g.drawImage(image, col * imageWidth, row * imageHeight,
                            imageWidth, imageHeight, null);
                    g.setColor(DARK_GRAY);
                    g.drawLine((col + 1) * imageWidth - 1, row * imageHeight,
                               (col + 1) * imageWidth - 1, (row + 1) * imageHeight - 1);
                    g.drawLine(col * imageWidth, (row + 1) * imageHeight - 1,
                               (col + 1) * imageWidth - 1, (row + 1) * imageHeight - 1);
                } else {
                    g.setColor(Color.GRAY);
                    g.fillRect(col * imageWidth, row * imageHeight, imageWidth,
                            imageHeight);
                }
                ++pos;
            }
        }
        if (!animationDone) {
            paintMovingTileRegion(g);
        }
    }

    private void paintMovingTileRegion(final Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(x, y, imageWidth, imageHeight);
        g.fillRect(c1 * imageHeight, r1 * imageWidth, imageWidth, imageHeight);
        g.setColor(DARK_GRAY);
        if (r0 == r1) {
            // Moves left/right.
            g.drawImage(movingImage, movingCoord, y, imageWidth, imageHeight, null);
            g.drawLine(movingCoord, y + imageHeight - 1,
                    movingCoord + imageWidth - 1, y + imageHeight - 1);
            g.drawLine(movingCoord + imageWidth - 1, y,
                    movingCoord + imageWidth - 1, y + imageHeight - 1);
        } else {
            // Moves up/down.
            g.drawImage(movingImage, x, movingCoord, imageWidth, imageHeight, null);
            g.drawLine(x + imageWidth - 1, movingCoord,
                    x + imageWidth - 1, movingCoord + imageHeight - 1);
            g.drawLine(x, movingCoord + imageWidth - 1,
                    x + imageWidth - 1, movingCoord + imageHeight - 1);
        }
    }
}