package objetos;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class NotificacaoUtil {

    public static void mostrarAvisoTemporario(JFrame frame, String mensagem, Color corFundo) {
        JWindow aviso = new JWindow(frame);

        JLabel label = new JLabel(mensagem, SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(corFundo);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setBorder(new EmptyBorder(10, 20, 10, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);

        aviso.add(label);
        aviso.pack();

        int x = frame.getX() + frame.getWidth() - aviso.getWidth() - 20;
        int y = frame.getY() + 20;
        aviso.setLocation(x, y);

        aviso.setBackground(new Color(0, 0, 0, 0)); 
        aviso.setVisible(true);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            float opacity = 1f;

            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    opacity -= 0.05f;
                    if (opacity <= 0f) {
                        aviso.setVisible(false);
                        aviso.dispose();
                        timer.cancel();
                    } else {
                        aviso.setOpacity(opacity);
                    }
                });
            }
        }, 1000, 10); 
    }
}


