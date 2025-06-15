package com.geflas.demo;

import com.geflas.QRUtility;
import com.geflas.util.CommonUtility;
import com.google.zxing.WriterException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		runSwingApp();
		SpringApplication.run(DemoApplication.class, args);

	}
	public static void runSwingApp(){

		JFrame frame = new JFrame("File Transfer Tool");
		frame.setSize(1200,900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel(new GridLayout(4, 1, 20, 10)); // 4 rows, 1 column, 10 horizontal gap, 5 vertical gap

		JLabel label = new JLabel("    Either scan QR code or access the url manually.");
		label.setBounds(50, 50, 100, 30);
		label.setFont(new Font("Calibre", Font.BOLD, 30));
		panel.add(label);


		int port = 8080;
		String url = "http:/"+ CommonUtility.getInetAddress()+":"+port+"/upload";
		//url = "https://dailyaccounts.geflas.co.in/";
		int width = 280;
		int height = 280;

		JLabel urlLabel = new JLabel("    "+url);
		urlLabel.setBounds(50, 50, 50, 50   );
		urlLabel.setFont(new Font("Calibre", Font.ITALIC, 30));
		panel.add(urlLabel);

		// QR code to be added in Panel after HTTP server initialization
		try{



			// Create a JLabel to display the image

			String filePath =  new File("qrcode.png").getAbsolutePath();
			QRUtility.generateQRCodeImage(url,filePath,width,height);
			JLabel QRLabel = new JLabel(new ImageIcon(filePath));

			panel.add(QRLabel);



		}catch(WriterException | IOException e2){
			System.err.println("Error Generating QR code :" +e2.getMessage());
		}

		JPanel buttonPanel = new JPanel(new FlowLayout());
		JButton exitButton = new JButton("X - Exit");
		exitButton.setFont(new Font("Calibre", Font.BOLD, 30));
		exitButton.setForeground(Color.RED);
		buttonPanel.add(exitButton);
		// Add ActionListener to the button
		exitButton.addActionListener(e -> {
			System.exit(0);
		});

		panel.add(buttonPanel);


		frame.setContentPane(panel);
		// frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
