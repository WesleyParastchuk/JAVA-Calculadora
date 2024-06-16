import javax.swing.JButton;
import java.awt.Color;

public class Botao extends JButton {
    Botao(String textoBotao) {
        super(textoBotao);
        this.setFocusable(false);
        this.setBackground(Color.decode("#969da8"));
    }
}
