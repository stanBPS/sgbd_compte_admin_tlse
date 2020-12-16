package fr.sgbd.main;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import fr.sgbd.jdbc.Database;
import fr.sgbd.jframe.CRUD;
import fr.sgbd.jframe.RechercheA;
import fr.sgbd.jframe.RechercheC;
import fr.sgbd.main.MainJFrame;


public class MainJFrame extends JFrame{

	static MainJFrame homeJ = new MainJFrame();

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            	 homeJ.setVisible(true);   
            }
        });

	}
	
	public MainJFrame() {
		super("GESTION COMPTE ADMINISTRATIF");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1600, 900);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		RechercheC RechercheC = new RechercheC();
		RechercheA RechercheA = new RechercheA();
		CRUD CRUD = new CRUD();
		/*---------------------CONTAINER-------------------------*/
		//Container
		JPanel container = (JPanel) this.getContentPane();
		container.setLayout(null);
		container.setBackground(Color.WHITE);
		
		/*--------------------TITLE CONTAINER--------------------------*/
		//Title	
		JLabel title = new JLabel("GESTION COMPTE ADMINISTRATIF");
		title.setBounds(552, 15, 466, 32);
		title.setForeground(new Color(0x09105A));
		title.setFont(new Font("Serif", Font.BOLD,22));
		
		/*--------------------NAVBAR--------------------------*/
		//Navbar
		JPanel navbar = new JPanel();
		navbar.setLayout(null);
		navbar.setBounds(0,0,1609,180);
		navbar.setBackground(new Color(0xC8C8C8));
		
		
		/*--------------------MENU ONGLET--------------------------*/
		//Recherche par code
		JLabel recherche_code = new JLabel("RECHERCHE PAR CODE");
		recherche_code.setBounds(53, 140, 198, 40);
		recherche_code.setForeground(new Color(0x161E86));
		recherche_code.setFont(new Font("Serif", Font.BOLD,17));
	
		
		//Recherche avancee
		JLabel recherche_avancee = new JLabel("RECHERCHE AVANCEE");
		recherche_avancee.setBounds(324, 140, 191, 40);
		recherche_avancee.setForeground(new Color(0x333980));
		recherche_avancee.setFont(new Font("Serif", Font.BOLD,17));
		
		//Submenu Enregistrement
		JLabel submenu = new JLabel("ENREGISTREMENT");
		submenu.setBounds(588, 140, 158, 40);
		submenu.setForeground(new Color(0x333980));
		submenu.setFont(new Font("Serif", Font.BOLD,17));
		
		Border border = BorderFactory.createMatteBorder(0, 0, 3, 0,new Color(0x161E86));
		Border borderEmpty = BorderFactory.createEmptyBorder();
		recherche_code.setBorder(border);

		/*--------------------ACTION MENU--------------------------*/
		recherche_code.addMouseListener(new MouseAdapter() {	
			@Override
			public void  mouseClicked(MouseEvent e)  {
				CRUD.setVisible(false);
				RechercheA.setVisible(false);
				RechercheC.setVisible(true);
				recherche_avancee.setBorder(borderEmpty);
				submenu.setBorder(borderEmpty);
				recherche_avancee.setForeground(new Color(0x333980));
				submenu.setForeground(new Color(0x333980));
				recherche_code.setForeground(new Color(0x161E86));
				recherche_code.setBorder(border);
			}
		});
		
		recherche_avancee.addMouseListener(new MouseAdapter() {	
			@Override
			public void  mouseClicked(MouseEvent e)  {
				CRUD.setVisible(false);
				RechercheA.setVisible(true);
				RechercheC.setVisible(false);
				recherche_code.setBorder(borderEmpty);
				submenu.setBorder(borderEmpty);
				recherche_avancee.setForeground(new Color(0x161E86));
				submenu.setForeground(new Color(0x333980));
				recherche_code.setForeground(new Color(0x333980)); 
				recherche_avancee.setBorder(border);
				/*Font font = recherche_avancee.getFont(); 
				Map attributes = font.getAttributes(); 
				attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON); 
				recherche_avancee.setFont(font.deriveFont(attributes)); */
			}
			
			
		});
		
		
		submenu.addMouseListener(new MouseAdapter() {	
			@Override
			public void  mouseClicked(MouseEvent e)  {
				CRUD.setVisible(true);
				RechercheA.setVisible(false);
				RechercheC.setVisible(false);
				recherche_code.setBorder(borderEmpty);
				recherche_avancee.setBorder(borderEmpty);
				recherche_avancee.setForeground(new Color(0x333980));
				submenu.setForeground(new Color(0x161E86));
				recherche_code.setForeground(new Color(0x333980));
				submenu.setBorder(border);
				
			}
		});
		
		/*layer*/
		JLayeredPane content = new JLayeredPane();
		content.setBounds(0, 180, 1600, 720);
		//content.setBackground(new Color(0xC8C8C8));
		content.add(RechercheC);
		content.add(RechercheA);
		content.add(CRUD);
		CRUD.setVisible(false);
		RechercheA.setVisible(false);
		RechercheC.setVisible(true);
		
		/*-------------------ADD COMPONENT IN CONTAINER---------------------------*/
		//add coponent in container
		navbar.add(recherche_code);
		navbar.add(recherche_avancee);
		navbar.add(submenu);
		navbar.add(title);
		container.add(navbar);	
		container.add(content);
		

		
	}
	
	
	

}
