import javax.swing.*;
import java.awt.*;

public class LogIn extends JFrame {
    private JPanel loginPanel;
    private JPanel registrationPanel;

    public LogIn() {
        setTitle("Panel Switcher");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // Create Login Panel
        loginPanel = new JPanel(new BorderLayout());
        JPanel loginFieldsPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        loginFieldsPanel.add(usernameLabel);
        JTextField usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 16));
        loginFieldsPanel.add(usernameField);
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        loginFieldsPanel.add(passwordLabel);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        loginFieldsPanel.add(passwordField);
        loginPanel.add(loginFieldsPanel, BorderLayout.NORTH);

        JPanel loginButtonPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            String logUserName = usernameField.getText();
            String logPassword = passwordField.getText();

            if(FormativeLoginSystem.login(logUserName, logPassword)){
                UserAccount currentAccount = FormativeLoginSystem.returnLogin(logUserName);

                assert currentAccount != null;
                JOptionPane.showMessageDialog(LogIn.this, "Congratulations " + currentAccount.firstName +" "+ currentAccount.lastName + " you have made it in the CyberSecurity Class od 2025", "Login", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(LogIn.this, "Incorrect credentials have\n" +
                        "been supplied, try again", "Login", JOptionPane.INFORMATION_MESSAGE);
            }


        });
        loginButtonPanel.add(loginButton);

        JButton goToRegisterButton = new JButton("Go to Register");
        goToRegisterButton.addActionListener(e -> switchToRegistrationPanel());
        loginButtonPanel.add(goToRegisterButton);



        loginPanel.add(loginButtonPanel, BorderLayout.CENTER);

        // Create Registration Panel
        registrationPanel = new JPanel(new BorderLayout());
        JPanel registrationFieldsPanel = new JPanel(new GridLayout(4, 2, 5, 5)); // 4 rows, 2 columns, gaps of 5px
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        registrationFieldsPanel.add(firstNameLabel);
        JTextField firstNameField = new JTextField();
        firstNameField.setFont(new Font("Arial", Font.PLAIN, 16));
        registrationFieldsPanel.add(firstNameField);
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        registrationFieldsPanel.add(lastNameLabel);
        JTextField lastNameField = new JTextField();
        lastNameField.setFont(new Font("Arial", Font.PLAIN, 16));
        registrationFieldsPanel.add(lastNameField);
        JLabel regUsernameLabel = new JLabel("Username:");
        regUsernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        registrationFieldsPanel.add(regUsernameLabel);
        JTextField regUsernameField = new JTextField();
        regUsernameField.setFont(new Font("Arial", Font.PLAIN, 16));
        registrationFieldsPanel.add(regUsernameField);
        JLabel regPasswordLabel = new JLabel("Password:");
        regPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        registrationFieldsPanel.add(regPasswordLabel);
        JTextField regPasswordField = new JTextField();
        regPasswordField.setFont(new Font("Arial", Font.PLAIN, 16));
        registrationFieldsPanel.add(regPasswordField);
        registrationPanel.add(registrationFieldsPanel, BorderLayout.NORTH);

        JPanel registerButtonPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> {

            String userName  =  regUsernameField.getText();
            String lastname  =  lastNameField.getText();
            String firstName =  firstNameField.getText();
            String password  =  regPasswordField.getText();

            if(FormativeLoginSystem.regUser(userName, password, firstName ,lastname)){
                JOptionPane.showMessageDialog(LogIn.this, "User has been registered.", "Login", JOptionPane.INFORMATION_MESSAGE);
                regUsernameField.setText("");
                lastNameField.setText("");
                firstNameField.setText("");
                regPasswordField.setText("");
                switchToLoginPanel();
            }else{
                JOptionPane.showMessageDialog(LogIn.this, "Username does not meet the\n" +
                        "criteria, please ensure that\n" +
                        "your username contains an\n" +
                        "pound sign and is no more than 8\n" +
                        "characters in length .", "Login", JOptionPane.INFORMATION_MESSAGE);
            }

        });

        registerButtonPanel.add(registerButton);

        JButton goToLoginButton = new JButton("Go to Login");
        goToLoginButton.setFont(new Font("Arial", Font.PLAIN, 16));
        goToLoginButton.addActionListener(e -> switchToLoginPanel());
        registerButtonPanel.add(goToLoginButton);

        registrationPanel.add(registerButtonPanel, BorderLayout.CENTER);

        // Initial panel to display
        switchToLoginPanel();
    }
    private void switchToRegistrationPanel() {
        getContentPane().removeAll();
        getContentPane().add(registrationPanel);
        revalidate();
        repaint();
    }
    private void switchToLoginPanel() {
        getContentPane().removeAll();
        getContentPane().add(loginPanel);
        revalidate();
        repaint();
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LogIn().setVisible(true));
    }
}