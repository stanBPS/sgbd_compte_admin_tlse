package fr.sgbd.jframe;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import fr.sgbd.jdbc.Database;

public class Add_panel extends JPanel{
	
	Database d = new Database();
	static String mdp;
	JComboBox<Integer> codeArticle;
	JComboBox<Integer> codeChapitre;
	JComboBox<Integer> codePresentation;
	JTextField id;
	JComboBox<String> o_r;
	JComboBox<String> i_f;
	JComboBox<String> d_r;
	JComboBox<String> entiteFinanciere;
	JTextField montant;
	boolean firstConnectionA = true;
	boolean firstConnectionB = true;
	boolean firstConnectionP = true;
	boolean firstConnectionE = true;

	public Add_panel() {
		this.setBounds(0, 0, 1650, 720);
		//this.setBackground(Color.blue);
		this.setLayout(null);
		
		
		id = new JTextField();
		id.setBounds(127,50,104,38);
		JLabel id_label = new JLabel("ID");
		id_label.setBounds(170,15,208,38);
		int n = d.querySelectGetTotalEnr();
		id.setText(String.valueOf(n+1));
		id.setEditable(false);
		
		o_r = new JComboBox<>();
		o_r.setBounds(335,50,208,38);
		JLabel or_label = new JLabel("ORDRE/REEL");
		or_label.setBounds(400,15,208,38);
		o_r.addItem("Ordre");
		o_r.addItem("Reel");
		
		i_f = new JComboBox<>();
		i_f.setBounds(633,50,208,38);
		JLabel if_label = new JLabel("INVESTISSEMENT/FONCTIONNEMENT");
		if_label.setBounds(633,15,208,38);
		i_f.addItem("Investissement");
		i_f.addItem("Fonctionnement");
		
		
		d_r = new JComboBox<>();
		d_r.setBounds(910,50,208,38);
		JLabel dr_label = new JLabel("DEPENSES/RECETTES");
		dr_label.setBounds(950,15,208,38);
		d_r.addItem("Depense");
		d_r.addItem("Recette");
		
		codeArticle = new JComboBox<>();
		codeArticle.setBounds(77,127,208,38);
		JLabel codeArticle_label = new JLabel("CODE ARTICLE");
		codeArticle_label.setBounds(130,100,208,38);
		JTextField codeArticle_text = new JTextField();
		codeArticle_text.setEditable(false);
		codeArticle_text.setBounds(389,127,500,38);
		
		codeChapitre = new JComboBox<>();
		codeChapitre.setBounds(77,204,208,38);
		JLabel codeChapitre_label = new JLabel("CODE CHAPITRE");
		codeChapitre_label.setBounds(130,177,208,38);		
		JTextField codeChapitre_text = new JTextField();
		codeChapitre_text.setEditable(false);
		codeChapitre_text.setBounds(389,204,500,38);
		
		codePresentation = new JComboBox<>();
		codePresentation.setBounds(77,281,208,38);
		JLabel codePresentation_label = new JLabel("CODE PRESENTATION");
		codePresentation_label.setBounds(110,254,208,38);
		JTextField codePresentation_text = new JTextField();
		codePresentation_text.setEditable(false);
		codePresentation_text.setBounds(389,281,500,38);
		
		entiteFinanciere = new JComboBox<>();
		entiteFinanciere.setBounds(77,358,208,38);
		JLabel entiteFinanciere_label = new JLabel("Entite Financiere Programme");
		entiteFinanciere_label.setBounds(90,330,208,38);
		JTextField entiteFinanciere_text = new JTextField();
		entiteFinanciere_text.setEditable(false);
		entiteFinanciere_text.setBounds(389,358,500,38);
		
		montant = new JTextField();
		montant.setBounds(940,240,204,40);
		JLabel montant_label = new JLabel("MONTANT");
		montant_label.setBounds(1000, 210, 200, 40);
		
		JButton button_add = new JButton("AJOUTER");
		button_add.setBounds(500,467,287,137);
		JPasswordField pass = new JPasswordField(10);
		button_add.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = e.getActionCommand();
				if(s.equals("AJOUTER")) {
					JPanel panel = new JPanel();
					JLabel label = new JLabel("Enter a password:");
					JPasswordField pass = new JPasswordField(10);
					panel.add(label);
					panel.add(pass);
					String[] options = new String[]{"OK", "Cancel"};
					int option = JOptionPane.showOptionDialog(null, panel, "The title",
					                         JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
					                         null, options, options[1]);
					mdp= String.valueOf(pass.getPassword());
					if (option == 0){
						if (mdp == null) {
					    	mdp="";
					    }
					    if(mdp.equals("1234")) {
					    	if(entiteFinanciere.getSelectedIndex() != -1 &&  d_r.getSelectedIndex() != -1 &&  i_f.getSelectedIndex() != -1 &&  codeChapitre.getSelectedIndex() != -1 && codeArticle.getSelectedIndex() != -1 &&   codePresentation.getSelectedIndex() != -1 && o_r.getSelectedIndex() != -1 && !montant.getText().isEmpty()) {
					    		d.addToDatabase((String)(entiteFinanciere.getSelectedItem()), (String)(d_r.getSelectedItem()), (String)(i_f.getSelectedItem()), (int) (codeChapitre.getSelectedItem()), (int) (codeArticle.getSelectedItem()),(int) (codePresentation.getSelectedItem()),(String) o_r.getSelectedItem(), Float.valueOf(montant.getText()));
					    		int n = d.querySelectGetTotalEnr();
					    		id.setText(String.valueOf(n));
					    	}else {
						    	JOptionPane.showMessageDialog(null, "Ajout impossible remplisser toute les cases","Message d'erreur",JOptionPane.ERROR_MESSAGE);
					    	}
					    }else {
					    	JOptionPane.showMessageDialog(null, "Mauvais mot de passe","Message d'erreur",JOptionPane.ERROR_MESSAGE);
					    }
					}
				    
				}
				
				
			}
		});
		
		this.add(id);
		this.add(id_label);
		this.add(o_r);
		this.add(or_label);
		this.add(i_f);
		this.add(if_label);
		this.add(d_r);
		this.add(dr_label);
		
		this.add(codeArticle);
		this.add(codeArticle_label);
		this.add(codeArticle_text);
		
		this.add(codeChapitre);
		this.add(codeChapitre_text);
		this.add(codeChapitre_label);
		
		this.add(codePresentation);
		this.add(codePresentation_label);
		this.add(codePresentation_text);
		
		this.add(entiteFinanciere);
		this.add(entiteFinanciere_label);
		this.add(entiteFinanciere_text);

		this.add(button_add);
		this.add(montant);
		this.add(montant_label);
		//a
		codeArticle.addPopupMenuListener(new PopupMenuListener() {	
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
				if(firstConnectionA) {
					getListArticle();
					firstConnectionA =false;
				}
				
			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}

		});
		
		codeArticle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int n= 0;
				n = (int)(codeArticle.getSelectedItem());
				codeArticle_text.setText(getLibelleArticle(n));
			}
		});
		//b
		codeChapitre.addPopupMenuListener(new PopupMenuListener() {	
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
				if(firstConnectionB) {
					getListChapitre();
					firstConnectionB =false;
				}
				
			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}

		});
		
		codeChapitre.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int n= 0;
				n = (int)(codeChapitre.getSelectedItem());
				codeChapitre_text.setText(getLibelleChapitre(n));
			}
		});
		//p
		codePresentation.addPopupMenuListener(new PopupMenuListener() {	
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
				if(firstConnectionP) {
					getListPresentation();
					firstConnectionP =false;
				}
				
			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}

		});
		
		codePresentation.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int n= 0;
				n = (int)(codePresentation.getSelectedItem());
				codePresentation_text.setText(getLibellePresentation(n));
			}
		});
		//e
		entiteFinanciere.addPopupMenuListener(new PopupMenuListener() {	
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
				if(firstConnectionE) {
					getListEntite();
					firstConnectionE =false;
				}
				
			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}

		});
		
		entiteFinanciere.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String n="";
				n = (String)(entiteFinanciere.getSelectedItem());
				entiteFinanciere_text.setText(getLibelleEntite(n));
			}
		});
		
	}
	
	
	public void getListArticle(){
		ResultSet rs = d.querySelectAllOrderBy("article", "codearticle");
		try {
			rs.setFetchSize(600);
			while(rs.next()) {	
				codeArticle.addItem(rs.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//Recuperer le labelle du code article en fonction de son code
	public String getLibelleArticle(int num) {
		String result = "";
		if (num == 0) {
			return "";
		}else {
			ResultSet rs = d.querySelectWhereColonneInt("article","codearticle", num);
			try {	
				while (rs.next()) {
					result = rs.getString(2);

				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;

		}
	}
	
	public void getListChapitre(){
		ResultSet rs = d.querySelectAllOrderBy("chapitre", "codechapitre");
		
		try {
			rs.setFetchSize(600);
			while(rs.next()) {	
				codeChapitre.addItem(rs.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Recuperer le labelle du code chapitre en fonction de son code
		public String getLibelleChapitre(int num) {
			String result = "";
			if (num == 0) {
				return "";
			}else {
				
				ResultSet rs = d.querySelectWhereColonneInt("chapitre","codechapitre", num);
				try {	
					while (rs.next()) {
						result = rs.getString(2);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return result;
			}
			
		}
		
		public void getListPresentation(){
			ResultSet rs = d.querySelectAllOrderBy("presentationcroisee", "CODEPRESENTATIONCROISEE");
			try {
				rs.setFetchSize(600);
				while(rs.next()) {	
					codePresentation.addItem(rs.getInt(1));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//Recuperer le labelle du code article en fonction de son code
		public String getLibellePresentation(int num) {
			String result = "";

			ResultSet rs = d.querySelectWhereColonneInt("presentationcroisee","CODEPRESENTATIONCROISEE", num);
			try {	
				while (rs.next()) {
					result = rs.getString(2);

				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;

			
		}
		
		public void getListEntite(){
			ResultSet rs = d.querySelectAllOrderBy("ENTITEFINANCIEREPROGRAMME", "ENTITEFINANCIEREPROGRAMME");
			try {
				rs.setFetchSize(600);
				while(rs.next()) {	
					entiteFinanciere.addItem(rs.getString(1));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//Recuperer le labelle du code article en fonction de son code
		public String getLibelleEntite(String code) {
			String result = "";
	
			ResultSet rs = d.querySelectWhereColonneString("ENTITEFINANCIEREPROGRAMME","ENTITEFINANCIEREPROGRAMME", code);
			try {	
				while (rs.next()) {
					result = rs.getString(2);

				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;

			
		}
		
		
}
