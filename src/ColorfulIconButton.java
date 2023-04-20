import java.awt.Graphics;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicButtonUI;

public class ColorfulIconButton extends JButton {
    public ColorfulIconButton(Icon icon) {
        super(icon);
        setUI(new BasicButtonUI() {
            @Override
            public void paint(Graphics g, javax.swing.JComponent c) {
                if (getModel().isEnabled()) {
                    super.paint(g, c); // Draw the button as usual
                } else {
                    // Draw the icon in full color even when the button is disabled
                    Icon icon = getIcon();
                    if (icon != null) {
                        icon.paintIcon(c, g, (c.getWidth() - icon.getIconWidth()) / 2, (c.getHeight() - icon.getIconHeight()) / 2);
                    }
                }
            }
        });
    }

    public ColorfulIconButton()
    {
        super();
        setUI(new BasicButtonUI() {
            @Override
            public void paint(Graphics g, javax.swing.JComponent c) {
                if (getModel().isEnabled()) {
                    super.paint(g, c); // Draw the button as usual
                } else {
                    // Draw the icon in full color even when the button is disabled
                    Icon icon = getIcon();
                    if (icon != null) {
                        icon.paintIcon(c, g, (c.getWidth() - icon.getIconWidth()) / 2, (c.getHeight() - icon.getIconHeight()) / 2);
                    }
                }
            }
        });
    }
}