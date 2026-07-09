package Registers;

import utilities.UniversalThemes;

import javax.swing.*;
import javax.swing.plaf.basic.BasicMenuItemUI;
import java.awt.*;
import java.awt.event.ActionListener;

public class RegisterContextMenu {

    public interface Handler {
        void onRename();
        void onMoveUp();
        void onMoveDown();
        void onSetDefault();
        void onDelete();
    }

    public static void show(Component invoker, int x, int y,
                            boolean isFirst, boolean isLast, boolean isDefault, boolean canDelete,
                            Handler handler) {

        JPopupMenu menu = new JPopupMenu();
        menu.setBackground(UniversalThemes.BG_COMPONENT);
        menu.setBorder(BorderFactory.createLineBorder(UniversalThemes.BORDER_COLOR2, 1));

        menu.add(buildItem("Rename", true, e -> handler.onRename(), UniversalThemes.TXT_PRIMARY));
        menu.add(buildItem("Move Up", !isFirst, e -> handler.onMoveUp(), UniversalThemes.TXT_PRIMARY));
        menu.add(buildItem("Move Down", !isLast, e -> handler.onMoveDown(), UniversalThemes.TXT_PRIMARY));
        menu.add(buildItem(isDefault ? "Default Register" : "Set as Default", !isDefault,
                e -> handler.onSetDefault(), UniversalThemes.TXT_PRIMARY));
        menu.addSeparator();
        menu.add(buildItem("Delete", canDelete, e -> handler.onDelete(), UniversalThemes.DANGER_COLOR));

        menu.show(invoker, x, y);
    }

    public interface UnrecognizedHandler {
        void onRecognize();
        void onDelete();
    }

    public static void showForUnrecognized(Component invoker, int x, int y, UnrecognizedHandler handler) {
        JPopupMenu menu = new JPopupMenu();
        menu.setBackground(UniversalThemes.BG_COMPONENT);
        menu.setBorder(BorderFactory.createLineBorder(UniversalThemes.BORDER_COLOR2, 1));

        menu.add(buildItem("Recognize", true, e -> handler.onRecognize(), UniversalThemes.TXT_PRIMARY));
        menu.addSeparator();
        menu.add(buildItem("Delete", true, e -> handler.onDelete(), UniversalThemes.DANGER_COLOR));

        menu.show(invoker, x, y);
    }

    private static JMenuItem buildItem(String label, boolean enabled, ActionListener action, Color hoverTextColor) {
        JMenuItem item = new JMenuItem(label);
        item.setFont(UniversalThemes.UI_FONT_SMALL2);
        item.setEnabled(enabled);
        item.setForeground(UniversalThemes.TXT_PRIMARY);
        item.setBackground(UniversalThemes.BG_COMPONENT);
        item.setBorder(BorderFactory.createEmptyBorder(7, 16, 7, 20));
        item.setOpaque(true);
        item.addActionListener(action);
        applyObsidianItemTheme(item, hoverTextColor);
        return item;
    }

    private static void applyObsidianItemTheme(JMenuItem item, Color hoverTextColor) {
        item.setUI(new BasicMenuItemUI() {
            @Override
            protected void paintBackground(Graphics g, JMenuItem menuItem, Color bgColor) {
                boolean hovered = menuItem.getModel().isArmed() && menuItem.isEnabled();
                g.setColor(hovered ? UniversalThemes.BORDER_COLOR1 : UniversalThemes.BG_COMPONENT);
                g.fillRect(0, 0, menuItem.getWidth(), menuItem.getHeight());
            }

            @Override
            protected void paintText(Graphics g, JMenuItem menuItem, Rectangle textRect, String text) {
                boolean hovered = menuItem.getModel().isArmed() && menuItem.isEnabled();
                g.setColor(!menuItem.isEnabled() ? UniversalThemes.DISABLED_TEXT
                        : hovered ? hoverTextColor : UniversalThemes.TXT_PRIMARY);
                super.paintText(g, menuItem, textRect, text);
            }
        });
    }
}