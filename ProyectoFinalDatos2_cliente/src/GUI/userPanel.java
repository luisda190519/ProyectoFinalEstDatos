package GUI;

import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import static java.lang.Math.random;
import javax.swing.*;
import java.util.Random;

public class userPanel extends javax.swing.JPanel {

    public userPanel(ImageIcon imagen) {
        initComponents();
        this.setMaximumSize(this.getPreferredSize());
        Image img = imagen.getImage();
        Image newimg = img.getScaledInstance(202, 108, java.awt.Image.SCALE_SMOOTH);
        imagen = new ImageIcon(newimg);

        pic.setIcon(imagen);
        JPanel p2 = new RoundedPanel(15, new Color(35, 39, 42));
        p2.setBounds(0, 0, 250, 150);
        p2.setOpaque(false);
        this.add(p2);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    }

    class RoundedPanel extends JPanel {

        private Color backgroundColor;
        private int cornerRadius = 15;

        public RoundedPanel(LayoutManager layout, int radius) {
            super(layout);
            cornerRadius = radius;
        }

        public RoundedPanel(LayoutManager layout, int radius, Color bgColor) {
            super(layout);
            cornerRadius = radius;
            backgroundColor = bgColor;
        }

        public RoundedPanel(int radius) {
            super();
            cornerRadius = radius;
        }

        public RoundedPanel(int radius, Color bgColor) {
            super();
            cornerRadius = radius;
            backgroundColor = bgColor;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Dimension arcs = new Dimension(cornerRadius, cornerRadius);
            int width = getWidth();
            int height = getHeight();
            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            //Draws the rounded panel with borders.
            if (backgroundColor != null) {
                graphics.setColor(backgroundColor);
            } else {
                graphics.setColor(getBackground());
            }
            graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); //paint background
            graphics.setColor(getForeground());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pic = new javax.swing.JLabel();

        setBackground(new java.awt.Color(44, 47, 51));
        setMaximumSize(new java.awt.Dimension(250, 150));
        setMinimumSize(new java.awt.Dimension(250, 150));
        setPreferredSize(new java.awt.Dimension(250, 150));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel pic;
    // End of variables declaration//GEN-END:variables
}
