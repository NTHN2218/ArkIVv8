package Registers;

import utilities.UniversalThemes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterItem extends JPanel {

    private final RegisterManager.RegisterEntry entry;
    private final JLabel nameLabel;
    private boolean isSelected;

    public RegisterItem(RegisterManager.RegisterEntry entry, Runnable onClick) {
        this.entry = entry;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        setOpaque(true);
        setAlignmentX(Component.LEFT_ALIGNMENT);

        nameLabel = new JLabel(entry.name);
        nameLabel.setFont(UniversalThemes.UI_FONT_BIG);
        add(nameLabel, BorderLayout.WEST);

        setSelected(false); // initial paint

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onClick.run();
            }
        });
    }

    public RegisterManager.RegisterEntry getEntry() {
        return entry;
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;
        if (selected) {
            setBackground(UniversalThemes.ACCENT_COLOR);
            nameLabel.setForeground(UniversalThemes.TXT_SELECTED);
        } else {
            setBackground(UniversalThemes.BORDER_COLOR1);
            nameLabel.setForeground(UniversalThemes.TXT_PRIMARY);
        }
        repaint();
    }

    public boolean isSelected() {
        return isSelected;
    }
}