package fr.sgbd.jdbc;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Enregistrement {
		private int codechapitre,codearticle,codepresentationcroisee ;
		private float montantrealise;
		private String  ENTITEFINANCIEREPROGRAMME,nomenclaturecomptable,depenses_recettes,investissement_fonctionnement,libellechapitre,libellearticle,libellepresentationcroisee,ordre_reel;
		/**
		 * @param id
		 * @param eNTITEFINANCIEREPROGRAMME
		 * @param nomenclaturecomptable
		 * @param depenses_recettes
		 * @param investissement_fonctionnement
		 * @param codechapitre
		 * @param libellechapitre
		 * @param codearticle
		 * @param libellearticle
		 * @param codepresentationcroisee
		 * @param libellepresentationcroisee
		 * @param ordre_reel
		 * @param montantrealise
		 */
		public Enregistrement( String eNTITEFINANCIEREPROGRAMME, String nomenclaturecomptable,
				String depenses_recettes, String investissement_fonctionnement, int codechapitre,
				String libellechapitre, int codearticle, String libellearticle, int codepresentationcroisee,
				String libellepresentationcroisee, String ordre_reel, float montantrealise) {
			super();
			this.ENTITEFINANCIEREPROGRAMME = eNTITEFINANCIEREPROGRAMME;
			this.nomenclaturecomptable = nomenclaturecomptable;
			this.depenses_recettes = depenses_recettes;
			this.investissement_fonctionnement = investissement_fonctionnement;
			this.codechapitre = codechapitre;
			this.libellechapitre = libellechapitre;
			this.codearticle = codearticle;
			this.libellearticle = libellearticle;
			this.codepresentationcroisee = codepresentationcroisee;
			this.libellepresentationcroisee = libellepresentationcroisee;
			this.ordre_reel = ordre_reel;
			this.montantrealise = montantrealise;
		}
		/**
		 * @return the eNTITEFINANCIEREPROGRAMME
		 */
		public String getENTITEFINANCIEREPROGRAMME() {
			return ENTITEFINANCIEREPROGRAMME;
		}
		/**
		 * @return the nomenclaturecomptable
		 */
		public String getNomenclaturecomptable() {
			return nomenclaturecomptable;
		}
		/**
		 * @return the depenses_recettes
		 */
		public String getDepenses_recettes() {
			return depenses_recettes;
		}
		/**
		 * @return the investissement_fonctionnement
		 */
		public String getInvestissement_fonctionnement() {
			return investissement_fonctionnement;
		}
		/**
		 * @return the codechapitre
		 */
		public int getCodechapitre() {
			return codechapitre;
		}
		/**
		 * @return the libellechapitre
		 */
		public String getLibellechapitre() {
			return libellechapitre;
		}
		/**
		 * @return the codearticle
		 */
		public int getCodearticle() {
			return codearticle;
		}
		/**
		 * @return the libellearticle
		 */
		public String getLibellearticle() {
			return libellearticle;
		}
		/**
		 * @return the codepresentationcroisee
		 */
		public int getCodepresentationcroisee() {
			return codepresentationcroisee;
		}
		/**
		 * @return the libellepresentationcroisee
		 */
		public String getLibellepresentationcroisee() {
			return libellepresentationcroisee;
		}
		/**
		 * @return the ordre_reel
		 */
		public String getOrdre_reel() {
			return ordre_reel;
		}
		/**
		 * @return the montantrealise
		 */
		public String getMontantrealise() {
			NumberFormat nf = NumberFormat.getNumberInstance(Locale.FRANCE);
			DecimalFormat df = (DecimalFormat)nf;
			df.applyPattern("###,###.###");
			
			return df.format(montantrealise);
		}
		
		
		
}