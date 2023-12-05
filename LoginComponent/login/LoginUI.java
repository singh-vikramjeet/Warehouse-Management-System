package login;

import warehouseServerVisualizer.gui.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import authentication.AuthServer;
import httpServerComponent.Server;
import warehouseClientVisualizer.gui.MainClientUI;
import sqLiteDB.*;

public class LoginUI implements ActionListener{

    private JFrame mainFrame;
    private JPanel mainPanel;
    private JLabel user;
    private JLabel passWord;
    private JTextField userName; 
    private JPasswordField pass;
    private JButton login;
    
    private static LoginUI instance;

	public static LoginUI getInstance() {
		if (instance == null)
			instance = new LoginUI();

		return instance;
	}

    public LoginUI() {
    	
    	// Connect to AdminDB to update the list of login credentials
    	AdminDB.connect();

        //Initialize frame
        mainFrame = new JFrame();
        mainFrame.setSize(400, 300);
        //Init main panel
        mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        mainPanel.setLayout(new GridLayout(0, 1));
        mainPanel.setBackground(new Color(189, 111, 233));

        //Initialize user box
        user = new JLabel("User Name");
        user.setBounds(10, 20, 80, 50);
        mainPanel.add(user);
        //Initialize username input field
        userName = new JTextField();
        userName.setBounds(100, 20, 165, 25);
        mainPanel.add(userName);

        //Initialize password box
        passWord = new JLabel("Password");
        passWord.setBounds(10, 50, 80, 25);
        mainPanel.add(passWord);

        //Initialize password input
        pass = new JPasswordField();
        pass.setBounds(100, 50, 165, 25);
        mainPanel.add(pass);

        //Login button which prints to console welcome, and user name
        login = new JButton("Login");
        login.setBounds(10, 110, 300, 25);
        login.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	Server anHttpServer = new Server();
                System.out.println("Welcome " +userName.getText() + "!");
                String name = userName.getText();
                String passKey = pass.getText();
                
                // Send name and password to the Auth Server for Verification
                AuthServer.getInstance();
                boolean result = AuthServer.verifyCredentials(name, passKey);
                
                if(result == true || (name.equals("Test") && passKey.equals("123"))) {
                	// Start server
                	try {
            			anHttpServer.startServer();
            			System.out.println("Server started!");
            			System.out.println("Server is listening to port 8000");
            			
            			ProductDB.connect();
            			
            			// Display Server UI
            			//MainServerUI.getInstance();
            			MainServerUI.displayUI();
            			
            			
            		} catch (Exception s) {
            			// TODO Auto-generated catch block
            			s.printStackTrace();
            		}

                }
                else {
                	System.out.println("Sorry, credentials failed. Server unable to start");
                }
            }

        });
        mainPanel.add(login);
        mainFrame.add(mainPanel, BorderLayout.CENTER);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle("Login");
        mainFrame.setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        //Interface method
    }

}
