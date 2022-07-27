import javax.swing.*;
import java.awt.*;

public class Gradient extends JPanel {
    private Image image;
    private AlphaComposite alpha;
    public Gradient(Image image) {
        this.setOpaque(false);
        this.image = image;
        alpha = AlphaComposite.SrcOver.derive(0.8f);
    }

    public void setAlpha(int rate) {
        this.alpha = AlphaComposite.SrcOver.derive(rate / 100.0f);
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.clearRect(0, 0,  getWidth(), getHeight());
        g2.setComposite(alpha);
        g2.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
