import javax.swing.*;
import java.awt.*;

public class MainWindow {
    public static void main(String[] args) {
        JFrame frame = new JFrame("计算器");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JTextField displayField = new JTextField();
        displayField.setEditable(false);
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setPreferredSize(new Dimension(frame.getWidth(), 50));
        frame.add(displayField, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 5, 5));

        // 每排4个按钮，共20个
        panel.add(createFixedSizeButton("C", displayField));
        panel.add(createFixedSizeButton("÷", displayField));
        panel.add(createFixedSizeButton("×", displayField));
        panel.add(createFixedSizeButton("←", displayField));

        panel.add(createFixedSizeButton("7", displayField));
        panel.add(createFixedSizeButton("8", displayField));
        panel.add(createFixedSizeButton("9", displayField));
        panel.add(createFixedSizeButton("-", displayField));

        panel.add(createFixedSizeButton("4", displayField));
        panel.add(createFixedSizeButton("5", displayField));
        panel.add(createFixedSizeButton("6", displayField));
        panel.add(createFixedSizeButton("+", displayField));

        panel.add(createFixedSizeButton("1", displayField));
        panel.add(createFixedSizeButton("2", displayField));
        panel.add(createFixedSizeButton("3", displayField));
        panel.add(createFixedSizeButton("=", displayField));

        panel.add(createFixedSizeButton("%", displayField));
        panel.add(createFixedSizeButton("0", displayField));
        panel.add(createFixedSizeButton(".", displayField));
        // 最后一个按钮可以选择保留一个括号或直接去掉
        // panel.add(createFixedSizeButton("(", displayField));
        panel.add(createFixedSizeButton("=", displayField));

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private static JButton createFixedSizeButton(String text, JTextField displayField) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(50, 50));

        button.addActionListener(_ -> {
            if ("=".equals(text)) {
                try {
                    String expression = displayField.getText().trim();
                    if (expression.isEmpty()) {
                        displayField.setText("错误: 空表达式");
                        return;
                    }

                    // 更宽松的表达式验证
                    if (expression.matches(".*[+\\-×÷.]$")) {
                        displayField.setText("错误: 表达式不完整");
                        return;
                    }

                    double result = evaluateExpression(expression);
                    displayField.setText(formatDouble(result));
                } catch (Exception ex) {
                    displayField.setText("错误: 计算失败");
                }
            } else if ("C".equals(text)) {
                displayField.setText("");
            } else if ("←".equals(text)) {
                String currentText = displayField.getText();
                if (!currentText.isEmpty()) {
                    displayField.setText(currentText.substring(0, currentText.length() - 1));
                }
            } else {
                displayField.setText(displayField.getText() + text);
            }
        });

        return button;
    }

    private static String formatDouble(double value) {
        if (value == (long) value) {
            return String.format("%d", (long) value); // 整数不显示小数位
        } else {
            return String.format("%s", value); // 小数保留原有格式
        }
    }

    private static double evaluateExpression(String expression) throws Exception {
        // 仅支持 + - * / 和括号
        expression = expression.replace("÷", "/").replace("×", "*");
        // 去除空格
        expression = expression.replaceAll("\\s+", "");
        try {
            return simpleEval(expression);
        } catch (Exception e) {
            throw new Exception("表达式计算错误");
        }
    }

    // 简单四则运算解析器（递归实现，支持括号）
    private static double simpleEval(String expr) {
        return parseExpr(expr);
    }

    // 解析表达式
    private static double parseExpr(String expr) {
        int len = expr.length();
        int bracket = 0;
        int opPos = -1;
        char op = 0;
        // 从右往左找最低优先级的运算符（+ -）
        for (int i = len - 1; i >= 0; i--) {
            char c = expr.charAt(i);
            if (c == ')')
                bracket++;
            if (c == '(')
                bracket--;
            if (bracket == 0 && (c == '+' || (c == '-' && i > 0))) {
                opPos = i;
                op = c;
                break;
            }
        }
        if (opPos != -1) {
            double left = parseExpr(expr.substring(0, opPos));
            double right = parseTerm(expr.substring(opPos + 1));
            return op == '+' ? left + right : left - right;
        }
        return parseTerm(expr);
    }

    // 解析乘除
    private static double parseTerm(String expr) {
        int len = expr.length();
        int bracket = 0;
        int opPos = -1;
        char op = 0;
        for (int i = len - 1; i >= 0; i--) {
            char c = expr.charAt(i);
            if (c == ')')
                bracket++;
            if (c == '(')
                bracket--;
            if (bracket == 0 && (c == '*' || c == '/')) {
                opPos = i;
                op = c;
                break;
            }
        }
        if (opPos != -1) {
            double left = parseTerm(expr.substring(0, opPos));
            double right = parseFactor(expr.substring(opPos + 1));
            if (op == '*')
                return left * right;
            if (right == 0)
                throw new ArithmeticException("除零错误");
            return left / right;
        }
        return parseFactor(expr);
    }

    // 解析括号或数字
    private static double parseFactor(String expr) {
        expr = expr.trim();
        if (expr.startsWith("(") && expr.endsWith(")")) {
            return parseExpr(expr.substring(1, expr.length() - 1));
        }
        return Double.parseDouble(expr);
    }
}