package utility;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.SystemColor;
import java.nio.file.Files;
import static java.nio.file.FileVisitOption.FOLLOW_LINKS;
import java.io.IOException;
import java.io.File;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;
import java.lang.String;
public class frm {
	private JFrame frame;
	private JTextField txt1;
	private JTextField txt2;
	private String flg;
	private JTextField wait;
	public static void main(String[] args) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frm window = new frm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public frm() {
		initialize();
	}
	private void initialize() {
		frame = new JFrame("Utility");
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		//frame.setSize(900, 650);
		frame.setBounds(100, 100, 711, 485);
		frame.setLocationRelativeTo(null);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btn1 = new JButton("Shut down");
		btn1.setForeground(new Color(51, 51, 204));
		btn1.setFont(new Font("Ink Free", Font.BOLD, 14));
		btn1.setBounds(70, 412, 108, 23);
		btn1.setVisible(false);
		btn1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				try{String cmd="shutdown -s -t 2";
				Runtime.getRuntime().exec(cmd);}
					catch(IOException c){
					}
			}
		});
		frame.getContentPane().add(btn1);
		
		JButton btn2= new JButton("Restart");
		btn2.setForeground(new Color(0, 0, 255));
		btn2.setFont(new Font("Ink Free", Font.BOLD, 14));
		btn2.setBounds(70, 385, 108, 23);
		btn2.setVisible(false);
		btn2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				try{String cmd2="shutdown -r -t 5";
				Runtime.getRuntime().exec(cmd2);}
				catch(IOException c){
				}
			}
		});	
		frame.getContentPane().add(btn2);
		
		JToggleButton tgbtn1 = new JToggleButton("");
		tgbtn1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				if(state==ItemEvent.SELECTED)
					{
						btn1.setVisible(true);
						btn2.setVisible(true);
					}
				else
				{
					btn1.setVisible(false);
					btn2.setVisible(false);
				}
			}
		});
		tgbtn1.setBorder(BorderFactory.createBevelBorder(0));
		tgbtn1.setIcon(new ImageIcon(frm.class.getResource("/utility/icon.png")));
		tgbtn1.setBounds(10, 385, 50, 50);
		frame.getContentPane().add(tgbtn1);
		
		txt1 = new JTextField();
		txt1.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		txt1.setAlignmentX(Component.RIGHT_ALIGNMENT);
		txt1.setBounds(new Rectangle(1, 1, 1, 1));
		txt1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		txt1.setEditable(false);
		txt1.setFont(new Font("Algerian", Font.PLAIN, 22));
		txt1.setBackground(new Color(0, 204, 255));
		txt1.setHorizontalAlignment(SwingConstants.CENTER);
		txt1.setText("Shafe's Personal Computer");
		txt1.setBounds(0, 0, 695, 34);
		frame.getContentPane().add(txt1);
		txt1.setColumns(10);
		
		JScrollPane scrlpan = new JScrollPane();
		scrlpan.setBounds(10, 90, 675, 284);
		frame.getContentPane().add(scrlpan);
		
		JTextArea txt = new JTextArea();
		txt.setFont(new Font("Monospaced", Font.ITALIC, 12));
		scrlpan.setViewportView(txt);
		
		txt2 = new JTextField();
		txt2.setBackground(new Color(175, 238, 238));
		txt2.setToolTipText("");
		txt2.setFont(new Font("Nirmala UI", Font.PLAIN, 13));
		txt2.setBounds(153, 45, 532, 34);
		frame.getContentPane().add(txt2);
		txt2.setColumns(10);
		JButton btn3 = new JButton("Search");
		btn3.setBorder(BorderFactory.createBevelBorder(0));
		btn3.setIcon(new ImageIcon(frm.class.getResource("/utility/icon2.png")));
		btn3.setForeground(Color.BLUE);
		btn3.setFont(new Font("Ink Free", Font.BOLD, 14));
		btn3.setBounds(10, 45, 133, 34);
		btn3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String input= txt2.getText();
				flg="0";
				File[] drives=File.listRoots();
				if(drives != null && drives.length>0)
				{
					for(int i=0;i<drives.length;i++){
						Path file=Paths.get(drives[i]+"\\");
						try{
							SimpleFileVisitor<Path> pathVisitor= new SimpleFileVisitor<Path>(){
							public FileVisitResult visitFile(Path file, BasicFileAttributes attr){
							if(file.getFileName().toString().contains(input)){
								txt.append(file+"\n");
								flg="1";
								return FileVisitResult.CONTINUE;
							}
							else{
								return FileVisitResult.CONTINUE;
							}
						}
						public FileVisitResult visitFileFailed(Path p, IOException e){
							return FileVisitResult.CONTINUE;
						}
					};
					EnumSet<FileVisitOption> option=EnumSet.of(FOLLOW_LINKS);
					Files.walkFileTree(file, option, 6, pathVisitor);
				} catch (Exception ex) {}	
				}}
				if(flg=="0")
					JOptionPane.showMessageDialog(frame,"No File found!!!!!");
				txt.append("Completed");
			}
		});
		frame.getContentPane().add(btn3);
		
		JButton clrbtn = new JButton("CLEAR");
		clrbtn.setBackground(new Color(245, 245, 245));
		clrbtn.setFont(new Font("Ink Free", Font.BOLD, 16));
		clrbtn.setForeground(Color.RED);
		clrbtn.setBounds(596, 385, 89, 23);
		clrbtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				txt.setText(null);
				txt2.setText(null);
			}
		});
		frame.getContentPane().add(clrbtn);	
		
		wait = new JTextField();
		wait.setFont(new Font("Nirmala UI", Font.ITALIC, 12));
		wait.setEditable(false);
		wait.setText("Please wait awhile after hitting SEARCH button..............");
		wait.setBackground(new Color(220, 220, 220));
		wait.setBounds(205, 385, 300, 20);
		frame.getContentPane().add(wait);
	}
}
