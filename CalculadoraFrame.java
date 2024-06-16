import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CalculadoraFrame extends JFrame implements ActionListener {
    enum EstadoCalculadora {
        INICIAL, IGNORADO, ENTRADA1, OPERADOR, ENTRADA2, CALCULANDO
    };

    private EstadoCalculadora estadoCalc = EstadoCalculadora.INICIAL;
    private int num1 = 0, num2 = 0;
    private char sinal = '+';
    private int num1Multiplier = +1;
    private Display display;

    public static void main(String[] args) {
        CalculadoraFrame calculadoraFrame = new CalculadoraFrame();
        calculadoraFrame.initComponents();
    }

    void initComponents() {
        this.setTitle("Calculadora");
        this.setSize(300, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        display = new Display();
        Botoes botoes = new Botoes();
        botoes.setActionListener(this);

        this.add(display, BorderLayout.NORTH);
        this.add(botoes, BorderLayout.CENTER);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String textoBotao = e.getActionCommand();
        switch (textoBotao) {
            case "C":
                num1 = 0;
                num2 = 0;
                sinal = '+';
                num1Multiplier = +1;
                estadoCalc = EstadoCalculadora.INICIAL;
                display.setText("0");
                break;
            case "+":
                if (estadoCalc == EstadoCalculadora.INICIAL) {
                    num1Multiplier = 1;
                    display.setText(textoBotao);
                }
            case "-":
                if (estadoCalc == EstadoCalculadora.INICIAL) {
                    num1Multiplier = -1;
                    display.setText(textoBotao);
                }
            case "x":
            case "/":
                if (estadoCalc == EstadoCalculadora.ENTRADA1 || estadoCalc == EstadoCalculadora.OPERADOR
                        || estadoCalc == EstadoCalculadora.CALCULANDO) {
                    sinal = textoBotao.charAt(0);
                    estadoCalc = EstadoCalculadora.OPERADOR;
                    display.setText(textoBotao);
                }
                break;
            case "=":
                try {
                    if (estadoCalc == EstadoCalculadora.ENTRADA2) {
                        estadoCalc = EstadoCalculadora.CALCULANDO;
                        switch (sinal) {
                            case '+':
                                num1 += num2;
                                break;
                            case '-':
                                num1 -= num2;
                                break;
                            case 'x':
                                num1 *= num2;
                                break;
                            case '/':
                                if (num2 == 0) {
                                    throw new ArithmeticException("Divisão por zero! - Clique em C para limpar");
                                }
                                num1 /= num2;
                                break;
                        }
                        num2 = 0;
                        display.setText(Integer.toString(num1));
                    }
                } catch (ArithmeticException ex) {
                    num1 = 0;
                    num2 = 0;
                    sinal = '+';
                    num1Multiplier = +1;
                    estadoCalc = EstadoCalculadora.INICIAL;
                    display.setText(ex.getMessage());
                }
                break;
            default:
                if (estadoCalc == EstadoCalculadora.INICIAL) {
                    num1 = Integer.parseInt(textoBotao);
                    num1 *= num1Multiplier;
                    estadoCalc = EstadoCalculadora.ENTRADA1;
                    display.setText(Integer.toString(num1));
                } else if (estadoCalc == EstadoCalculadora.ENTRADA1) {
                    num1 = num1 * 10 + Integer.parseInt(textoBotao);
                    display.setText(Integer.toString(num1));
                } else if (estadoCalc == EstadoCalculadora.OPERADOR) {
                    num2 = Integer.parseInt(textoBotao);
                    estadoCalc = EstadoCalculadora.ENTRADA2;
                    display.setText(Integer.toString(num2));
                } else if (estadoCalc == EstadoCalculadora.ENTRADA2) {
                    num2 = num2 * 10 + Integer.parseInt(textoBotao);
                    display.setText(Integer.toString(num2));
                } else if (estadoCalc == EstadoCalculadora.CALCULANDO) {
                    num1 = Integer.parseInt(textoBotao);
                    estadoCalc = EstadoCalculadora.ENTRADA1;
                    display.setText(Integer.toString(num1));
                }
                break;
        }
    }
}
