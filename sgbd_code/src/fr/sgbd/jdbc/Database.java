/**
 * 
 */
package fr.sgbd.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author stanb
 *
 */
public class Database {
	
	Connection connection;
	ResultSet resultSet;
	PreparedStatement statement;
	
	public Connection connexionDatabase() {
		Properties props = new Properties();
		try (FileInputStream file = new FileInputStream("conf.properties")){
			props.load(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		String url = props.getProperty("jdbc.url");
		String login = props.getProperty("jdbc.login");
		String password = props.getProperty("jdbc.password");
		try {
			Class.forName(props.getProperty("jdbc.driver.class"));
			connection = DriverManager.getConnection(url,login,password);
		}catch (ClassNotFoundException e) {
			System.out.println("Error, Driver upload");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
		
	}
	
	public Connection fermerConnexion() {
		try {
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public ResultSet executerRequete(String sql) {
		connexionDatabase();
		try{
			statement = connection.prepareStatement(sql);
			try{
				resultSet = statement.executeQuery();
			}catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return resultSet;
	}
	
	public String requeteUpdate(String sql) {
		connexionDatabase();
		String result = "";
		try {
			statement = connection.prepareStatement(sql);
			statement.executeUpdate();
			result = sql;
			
		}catch (Exception e) {
		}
		return result;
	}

	public ResultSet querySelectAll(String nomTable) {
		//connexionDatabase();
		String sql= "SELECT * FROM "+ nomTable;
		return this.executerRequete(sql);
	}
	
	public ResultSet querySelectAllOrderBy(String nomTable,String colonne) {
		//connexionDatabase();
		String sql= "SELECT * FROM "+ nomTable + " ORDER BY "+colonne;
		return this.executerRequete(sql);
	}
	
	public ResultSet querySelectCol(String nomTable, String colonne) {
		//connexionDatabase();
		String sql= "SELECT "+ colonne+ " FROM "+nomTable;
		return this.executerRequete(sql);
	}
	
	public ResultSet querySelectWhereColonneInt(String nomTable,String colonne ,int num) {
		//connexionDatabase();
		String sql= "SELECT * FROM "+ nomTable + " WHERE "+ colonne+ "="+num;
		return this.executerRequete(sql);
	}
	
	public ResultSet querySelectWhereColonneString(String nomTable,String colonne ,String code) {
		//connexionDatabase();
		String sql= "SELECT * FROM "+ nomTable + " WHERE "+ colonne+ "= '"+code+"'";
		return this.executerRequete(sql);
	}
	
	public int querySelectGetTotalEnr() {
		int result= 0;
		//connexionDatabase();
		String sql= "SELECT MAX(ID) FROM COMPTE_ADM_TLS1";
		ResultSet rs = executerRequete(sql);
		try {	
			while (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return result;

	}

	public ResultSet querySelectbyArticle(int article) {
		//connexionDatabase();
		String sql = "SELECT c.id, e.ENTITEFINANCIEREPROGRAMME,e.nomenclaturecomptable,c.depenses_recettes,c.investissement_fonctionnement,c.codechapitre,ch.libellechapitre,c.codearticle, a.libellearticle,c.codepresentationcroisee,p.libellepresentationcroisee,c.ordre_reel,c.montantrealise "
				+ "FROM compte_adm_tls1 c,article a,chapitre ch,entitefinanciereprogramme e ,presentationcroisee p"
				+ " WHERE c.codearticle="+article
				+ " and c.codearticle = a.codearticle"
				+ " and c.codechapitre = ch.codechapitre"
				+ " and c.entitefinanciereprogramme = e.ENTITEFINANCIEREPROGRAMME"
				+ " and c.codepresentationcroisee = p.codepresentationcroisee ORDER BY c.id";
		return this.executerRequete(sql);
	}
	
	public ResultSet querySelectbyChapitre(int chapitre) {
		//connexionDatabase();
		String sql = "SELECT c.id, e.ENTITEFINANCIEREPROGRAMME,e.nomenclaturecomptable,c.depenses_recettes,c.investissement_fonctionnement,c.codechapitre,ch.libellechapitre,c.codearticle, a.libellearticle,c.codepresentationcroisee,p.libellepresentationcroisee,"
				+ "c.ordre_reel,c.montantrealise FROM compte_adm_tls1 c,article a,chapitre ch,entitefinanciereprogramme e ,presentationcroisee p"
				+ " WHERE c.codechapitre="+chapitre
				+ " and c.codearticle = a.codearticle"
				+ " and c.codechapitre = ch.codechapitre"
				+ " and c.entitefinanciereprogramme = e.ENTITEFINANCIEREPROGRAMME"
				+ " and c.codepresentationcroisee = p.codepresentationcroisee ORDER BY c.id";
		return this.executerRequete(sql);
	}
		
	public ResultSet querySelectbyId(int id) {
		//connexionDatabase();
		String sql = "SELECT c.id, e.ENTITEFINANCIEREPROGRAMME,c.depenses_recettes,c.investissement_fonctionnement,c.codechapitre,c.codearticle,c.codepresentationcroisee,"
				+ "c.ordre_reel,c.montantrealise FROM compte_adm_tls1 c,article a,chapitre ch,entitefinanciereprogramme e ,presentationcroisee p"
				+ " WHERE c.id="+id
				+ " and c.codearticle = a.codearticle"
				+ " and c.codechapitre = ch.codechapitre"
				+ " and c.entitefinanciereprogramme = e.ENTITEFINANCIEREPROGRAMME"
				+ " and c.codepresentationcroisee = p.codepresentationcroisee";
		return this.executerRequete(sql);
	}
	public ResultSet querySelectbyCodes(String e,String d_r, String i_f, int codechapitre,int codearticle,  int codepresentation,String o_r) {
		//connexionDatabase();
		String sql = "SELECT c.id, c.ENTITEFINANCIEREPROGRAMME,c.depenses_recettes,c.investissement_fonctionnement,c.codechapitre,c.codearticle,c.codepresentationcroisee,"
				+ "c.ordre_reel,c.montantrealise FROM compte_adm_tls1 c"
				+ " WHERE c.codearticle ="+ codearticle
				+ " and c.depenses_recettes='"+ d_r+"'"
				+ " and c.investissement_fonctionnement='"+i_f+"'"
				+ " and c.ordre_reel='"+o_r+"'"
				+ " and c.codechapitre ="+ codechapitre
				+ " and c.entitefinanciereprogramme ='"+e+"'"
				+ " and c.codepresentationcroisee ="+ codepresentation;
		return this.executerRequete(sql);
	}
	
	public String addToDatabase(String e,String d_r, String i_f, int codechapitre,int codearticle,  int codepresentation,String o_r,float montant) {
		//connexionDatabase();
		String sql= "INSERT INTO COMPTE_ADM_TLS1(ordre_reel,depenses_recettes,investissement_fonctionnement,codechapitre,codearticle,codepresentationcroisee,ENTITEFINANCIEREPROGRAMME,"
				+ "montantrealise)  VALUES('"+o_r+"', '"+d_r+"', '"+i_f+"',"+codechapitre+","+codearticle+","+codepresentation+",'"+e+"',"+montant+")";
		return this.requeteUpdate(sql);
	}
	
	public String deleteFromDatabase(int id) {
		//connexionDatabase();
		String sql= "DELETE FROM COMPTE_ADM_TLS1 WHERE id="+id;
		return this.requeteUpdate(sql);
	}
	
	public String UpdateEnrDatabase(int id,String e,String d_r, String i_f, int codechapitre,int codearticle,  int codepresentation,String o_r,float montant) {
		//connexionDatabase();
		String sql= "UPDATE COMPTE_ADM_TLS1"
				+ " SET ordre_reel= '"+o_r+"'"
				+ " ,depenses_recettes= '"+d_r+"'"
				+ " ,investissement_fonctionnement= '"+i_f+"'"
				+ " ,codechapitre= "+codechapitre
				+ " ,codearticle= "+codearticle
				+ " ,codepresentationcroisee= "+codepresentation
				+ " ,ENTITEFINANCIEREPROGRAMME= '"+e+"'"
				+ " ,montantrealise= "+montant
				+ " WHERE id="+id;
		return this.requeteUpdate(sql);
	}
	
}
