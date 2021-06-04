package chatting.app;

import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.*;

public class Client extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	JPanel p1;
	JTextField t1;
	JButton b1;
	static JTextArea a1;
	 
	static Socket s;
	static DataInputStream din;
	static DataOutputStream dout;

	Boolean typing;
	Client(){
		p1 = new JPanel();
		p1.setBackground(new Color(7,94,84));
		p1.setBounds(0,0,450,70);
		p1.setLayout(null);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("chatting/app/icons/3.png"));
		Image i2 = i1.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel l1 = new JLabel(i3);
		l1.setBounds(5,17,30,30);
		p1.add(l1);
		
		l1.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent ae){
				System.exit(0);
			}
		});
		
		ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("chatting/app/icons/2.png"));
		Image i5 = i4.getImage().getScaledInstance(60,60,Image.SCALE_DEFAULT);
		ImageIcon i6 = new ImageIcon(i5);
		JLabel l2 = new JLabel(i6);
		l2.setBounds(40,10,60,60);
		p1.add(l2);
		
		ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("chatting/app/icons/video.png"));
		Image i8 = i7.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
		ImageIcon i9 = new ImageIcon(i8);
		JLabel l3 = new JLabel(i9);
		l3.setBounds(290,20,30,30);
		p1.add(l3);
		
		ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("chatting/app/icons/phone.png"));
		Image i11 = i10.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
		ImageIcon i12 = new ImageIcon(i11);
		JLabel l4 = new JLabel(i12);
		l4.setBounds(340,20,40,30);
		p1.add(l4);
		
		ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("chatting/app/icons/3icon.png"));
		Image i14 = i13.getImage().getScaledInstance(13,25,Image.SCALE_DEFAULT);
		ImageIcon i15 = new ImageIcon(i14);
		JLabel l5 = new JLabel(i15);
		l5.setBounds(390,20,30,30);
		p1.add(l5);
		
		JLabel l6 = new JLabel("Bunty");
		l6.setFont(new Font("SAN_SERIF",Font.BOLD,18));
		l6.setForeground(Color.WHITE);
		l6.setBounds(110,15,100,18);
		p1.add(l6);
		
		JLabel l7 = new JLabel("Active Now");
		l7.setFont(new Font("SAN_SERIF",Font.PLAIN,14));
		l7.setForeground(Color.WHITE);
		l7.setBounds(110,35,100,20);
		p1.add(l7);
		
		t1 = new JTextField();
		t1.setBounds(5,655,310,40);
		t1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
		add(t1);
		
		Timer t = new Timer(1,new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				if(!typing){
					l7.setText("Active Now");
				}                                               //Status Change
			}
		});
		                                                  
		t.setInitialDelay(1000);                           
		
		t1.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent ke){
				l7.setText("typing...");
				t.stop();
				typing = true;
			}
			                                              //     Active Now to typing and vice versa
			public void keyReleased(KeyEvent ke){
				typing = false;
				
				if(!t.isRunning())
				 t.start();
			}
			
			
		});
		
		b1 = new JButton("Send");
		b1.setBackground(new Color(7,94,84));
		b1.setForeground(Color.WHITE);
		b1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
		b1.setBounds(320,655,123,40);
		add(b1);
		b1.addActionListener(this);
		
		a1 = new JTextArea();
		a1.setBounds(5,75,440,570);
		a1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
		a1.setEditable(false);
		a1.setLineWrap(true);
		a1.setWrapStyleWord(true);
		add(a1);
		
		add(p1);
		setLayout(null);
		setSize(450,700);
		setLocation(1000,200);
		setUndecorated(true);
		setVisible(true);
	}

public void actionPerformed(ActionEvent ae) {
		
		try{
			
		String out = t1.getText();
		a1.setText(a1.getText() + "\n\t\t\t" + out);
		dout.writeUTF(out);
		t1.setText("");
		
		}catch(Exception e){}
	
		}

	public static void main(String[] args) {
		new Client();
		
		
		try{
			
			//Rajan Added
			s = new Socket("192.168.1.4",5001);
			
			din = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream(s.getOutputStream());
			
			String msginput = "";
			while(true){
			msginput = din.readUTF();
			a1.setText(a1.getText() + "\n" + msginput);
			
			}
			
			
			
			
		}catch(Exception e){}
	}

	
}

