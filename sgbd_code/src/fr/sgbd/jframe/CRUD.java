package fr.sgbd.jframe;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class CRUD  extends JPanel{

		
		public CRUD() {
			this.setBounds(0, 0, 1600, 720);
			this.setBackground(Color.ORANGE);
			this.setLayout(null);
			
			Add_panel add_panel = new Add_panel();
			Delete_panel delete_panel = new Delete_panel();
			Modif_panel modif_panel = new Modif_panel();
			
			/*--------------------NAVBAR--------------------------*/
			//Navbar
			JPanel navbar = new JPanel();
			navbar.setLayout(null);
			navbar.setBounds(0,0,267,720);
			navbar.setBackground(new Color(0xC8C8C8));
			
			/*------------------MENU BUTTON-----------------------*/
			JButton add_button = new JButton("<html><div style='text-align: center;'>AJOUT D'UN <br> ENREGISTREMENT</div></html>");
			add_button.setFont(new Font("Serif", Font.BOLD,13));
			add_button.setForeground(new Color(0x09105A));
			
			JButton delete_button = new JButton("<html><div style='text-align: center;'>SUPPRESSION D'UN <br> ENREGISTREMENT</div></html>");
			delete_button.setFont(new Font("Serif", Font.BOLD,13));
			delete_button.setForeground(new Color(0x09105A));
			
			JButton modif_button = new JButton("<html><div style='text-align: center;'>MODIFICATION D'UN <br> ENREGISTREMENT</div></html>");
			modif_button.setFont(new Font("Serif", Font.BOLD,13));
			modif_button.setForeground(new Color(0x09105A));
			
			
			//Add
			add_button.setBounds(16, 111, 235, 147);
			add_button.setBackground(new Color(0xC1C1C1));
			add_button.addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent arg0) {
					delete_button.setBackground(new Color(0xF3F3F3));
					add_button.setBackground(new Color(0xC1C1C1));
					modif_button.setBackground(new Color(0xF3F3F3));
					modif_panel.setVisible(false);
					delete_panel.setVisible(false);
					add_panel.setVisible(true);
					
				}
			});

			
			// delete
			delete_button.setBounds(16, 287, 235, 147);
			delete_button.setBackground(new Color(0xF3F3F3));
			delete_button.addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent arg0) {
					delete_button.setBackground(new Color(0xC1C1C1));
					add_button.setBackground(new Color(0xF3F3F3));
					modif_button.setBackground(new Color(0xF3F3F3));
					modif_panel.setVisible(false);
					add_panel.setVisible(false);
					delete_panel.setVisible(true);
					
				}
			});
			
			//modif
			
			modif_button.setBounds(16, 462, 235, 147);
			modif_button.setBackground(new Color(0xF3F3F3));
			modif_button.addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent arg0) {
					delete_button.setBackground(new Color(0xF3F3F3));
					add_button.setBackground(new Color(0xF3F3F3));
					modif_button.setBackground(new Color(0xC1C1C1));
					add_panel.setVisible(false);
					delete_panel.setVisible(false);
					modif_panel.setVisible(true);
					
				}
			});
			
			navbar.add(add_button);
			navbar.add(modif_button);
			navbar.add(delete_button);
			
			
			JLayeredPane content = new JLayeredPane();
			content.setBounds(267, 0, 1600, 720);
			//content.setBackground(new Color(0xC8C8C8));
			content.add(add_panel);
			content.add(delete_panel);
			content.add(modif_panel);
			
			this.add(navbar);
			this.add(content);
			
		}
}
