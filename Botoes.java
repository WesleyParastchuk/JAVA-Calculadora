import javax.swing.JPanel;
import java.awt.GridLayout;

public class Botoes extends JPanel {
    private String[] botoes = {
            "7", "8", "9", "/",
            "4", "5", "6", "x",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
    };

    private Botao[] botoesArray = new Botao[botoes.length];

    Botoes() {
        this.setLayout(new GridLayout(4, 4, 2, 2));
        for (int i = 0; i < botoes.length; i++) {
            botoesArray[i] = new Botao(botoes[i]);
            this.add(botoesArray[i]);
        }
    }

    public void setActionListener(CalculadoraFrame calculadoraFrame) {
        for (int i = 0; i < botoesArray.length; i++) {
            botoesArray[i].addActionListener(calculadoraFrame);
        }
    }
}
