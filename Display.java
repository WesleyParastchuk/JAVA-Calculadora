import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;

public class Display extends JPanel {
    JTextField textField;

    Display() {
        this.setLayout(new BorderLayout());
        textField = new JTextField("0");
        textField.setEditable(false);
        textField.setBackground(Color.WHITE);
        textField.setHorizontalAlignment(JTextField.RIGHT);

        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        this.add(textField);
    }

    public void setText(String text) {
        textField.setText(text);
    }
}
