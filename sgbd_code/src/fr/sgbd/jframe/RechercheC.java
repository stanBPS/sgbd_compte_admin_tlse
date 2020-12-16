package fr.sgbd.jframe;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


import fr.sgbd.jdbc.Database;
import fr.sgbd.jdbc.Enregistrement;


public class RechercheC extends JPanel{
	
	Database d = new Database();
	JTable table_enr = new JTable();
	DefaultTableModel model = new DefaultTableModel();
	Object col[] = {"ENTITEFINANCIEREPROGRAMME","nomenclaturecomptable","depenses_recettes","investissement_fonctionnement","codechapitre","libellechapitre","codearticle", "libellearticle","codepresentationcroisee","libellepresentationcroisee","ordre_reel","montantrealise"};
	JRadioButton chapitre_button;
	JRadioButton article_button;
	ButtonGroup art_chap_grp;
	ResultSet rs;
	String tableName;
	int num;
	JComboBox<Integer> listCha_art;
	JTextField textCha_art;
	
	
	//Recuperer les valeurs des enregistrements
	public ArrayList<Enregistrement> enrListe(int num,String tablename){
		ArrayList<Enregistrement> enrListe = new ArrayList<>();
		enrListe.clear();
		if(tablename == "chapitre") {
			 rs = d.querySelectbyChapitre(num);
		}
		if (tablename == "article") {
			 rs = d.querySelectbyArticle(num);
		}
		
		try {
			Enregistrement enregistrement;
			while(rs.next()) {	
				 enregistrement = new Enregistrement(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getInt(10), rs.getString(11), rs.getString(12), rs.getFloat(13));
				 enrListe.add(enregistrement);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return enrListe;
	}
	
	//recuperer la liste des code en fonction de la table(article ou chapitre)
	public void getList(String colonne,String nomTable){
		listCha_art.removeAllItems();
		listCha_art.addItem(0);
		ResultSet rs = d.querySelectAllOrderBy(nomTable, colonne);
		try {
			while(rs.next()) {	
				listCha_art.addItem(rs.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Recuperer le labelle du code chapitre en fonction de son code
	public String getLibelleChapitre(int num,String table) {
		String result = "";
		if (num == 0) {
			return "";
		}else {
			
			ResultSet rs = d.querySelectWhereColonneInt(table,"codechapitre", num);
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
	
	//Recuperer le labelle du code article en fonction de son code
	public String getLibelleArticle(int num,String table) {
		String result = "";
		if (num == 0) {
			return "";
		}else {
		ResultSet rs = d.querySelectWhereColonneInt(table,"codearticle", num);
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
	
	//Remplir le tableau 
	public void show_enregistrement(int num,String table) {
		model.setRowCount(0); 
		ArrayList<Enregistrement> list = enrListe(num,table);
		Object[] row = new Object[13];
		for(int i=0;i<list.size();i++) {
			row[0]=list.get(i).getENTITEFINANCIEREPROGRAMME();
			row[1]=list.get(i).getNomenclaturecomptable();
			row[2]=list.get(i).getDepenses_recettes();
			row[3]=list.get(i).getInvestissement_fonctionnement();
			row[4]=list.get(i).getCodechapitre();
			row[5]=list.get(i).getLibellechapitre();
			row[6]=list.get(i).getCodearticle();
			row[7]=list.get(i).getLibellearticle();
			row[8]=list.get(i).getCodepresentationcroisee();
			row[9]=list.get(i).getLibellepresentationcroisee();
			row[10]=list.get(i).getOrdre_reel();
			row[11]=list.get(i).getMontantrealise();
			model.addRow(row);
		}
	}
	
	public RechercheC() {
		this.setBounds(21, 10, 1540, 660);
		//this.setBackground(Color.RED);
		this.setLayout(null);
		
		//filtre
		
		JLabel article_label = new JLabel("ARTICLE");
		article_button = new JRadioButton();
		article_button.setActionCommand("article");
		article_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				listCha_art.setEnabled(true);
				tableName = "article";
				getList("codearticle",tableName);
			}
			
		});


		JLabel chapitre_label = new JLabel("CHAPITRE");
		chapitre_button = new JRadioButton();
		chapitre_button.setActionCommand("chapitre");
		chapitre_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				listCha_art.setEnabled(true);
				tableName = "chapitre";
				getList("codechapitre",tableName);
			}
		});
		
		art_chap_grp =  new ButtonGroup();
		art_chap_grp.add(chapitre_button);
		art_chap_grp.add(article_button);
		
		listCha_art = new JComboBox<Integer>();
		listCha_art.setBounds(552, 62, 143, 38);
		listCha_art.setEditable(true);
		listCha_art.setEnabled(false);
		textCha_art = new JTextField();
		textCha_art.setEditable(false);
		textCha_art.setBounds(709, 62, 428, 38);
		
		

		
		listCha_art.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				int n = 0;
				String table = art_chap_grp.getSelection().getActionCommand();
				if(listCha_art.getSelectedIndex() == -1) {
					
					listCha_art.setSelectedItem(0);
				}
				else {
					n = (int)(listCha_art.getSelectedItem());
				}
				if(table == "article") {
					textCha_art.setText(getLibelleArticle(n,table));
					show_enregistrement(n,table);
				}
				if(table == "chapitre") {
					textCha_art.setText(getLibelleChapitre(n,table));
					show_enregistrement(n,table);
				}
				
			}
		});
		
		
		
		article_button.setBounds(709,21,20,13);
		chapitre_button.setBounds(821,21,20,13);
		article_label.setBounds(729,21,100,15);
		chapitre_label.setBounds(841,21,100,15);
		this.add(chapitre_label);
		this.add(article_label);
		this.add(article_button);
		this.add(chapitre_button);
		this.add(listCha_art);
		this.add(textCha_art);
		//tableau
		model.setColumnIdentifiers(col);
		table_enr.setModel(model);
		this.add(table_enr);
		
		JScrollPane scroll_container = new JScrollPane(table_enr);
		scroll_container.setBounds(0, 120, 1540, 540);
		
		 
		this.add(scroll_container);
		
		
	}
	



}
