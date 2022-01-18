package af.cmr.indyli.gespro.light.business.dao.impl;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import af.cmr.indyli.gespro.light.business.utils.FilePropertiesLoader;

public class GpEntityManager {

	private String dbURL = "";
	private String user = "";
	private String password = "";
	private Integer nbMaxRow;
	private java.sql.Connection dbConnect = null;
	private java.sql.Statement dbStatement = null;

	
	public GpEntityManager() {
		Properties prop = FilePropertiesLoader.loadProp();
		this.dbURL = prop.getProperty("url.prop.server");
		this.user = prop.getProperty("user.prop.db");;
		this.password = prop.getProperty("pwd.prop.db");
		this.connect();
	}
	
	/**
	 * Constructeur
	 * @param url
	 * @param user
	 * @param password
	 */
	public GpEntityManager(Integer nbMaxRow) {
		this();
		this.nbMaxRow = nbMaxRow;
	}

	/**
	 * Connecter à la base de donnée
	 * @return false en cas d'échec
	 */
	public Boolean connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			this.dbConnect = DriverManager.getConnection(this.dbURL, this.user, this.password); 
			this.dbStatement = this.dbConnect.createStatement();
			if (this.nbMaxRow != null){
				this.dbStatement.setMaxRows(this.nbMaxRow);	
			}
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(GpEntityManager.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(GpEntityManager.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			Logger.getLogger(GpEntityManager.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(GpEntityManager.class.getName()).log(Level.SEVERE, null, ex);
		}
		return false;
	}

	/**
	 * Executer une requete SQL
	 * @param sql
	 * @return resultat de la requete
	 */
	public ResultSet exec(String sql) {
		try {
			ResultSet rs = this.dbStatement.executeQuery(sql);
			return rs;
		} catch (SQLException ex) {
			Logger.getLogger(GpEntityManager.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}


	/**
	 * Executer une requete SQL
	 * @param sql
	 * @return resultat de la requete
	 */
	public ResultSet selectAvecParamGenerique(String sql,Object[] tabParam) {
		ResultSet resultat = null;
		try {
			PreparedStatement preparedStatement = this.dbConnect.prepareStatement(sql);
			for (int i = 0; i < tabParam.length; i++) {
				preparedStatement.setObject(i+1, tabParam[i]);
			}
			resultat = preparedStatement.executeQuery();
		} catch (SQLException ex) {
			Logger.getLogger(GpEntityManager.class.getName()).log(Level.SEVERE, null, ex);
		}
		return resultat;
	}

	/**
	 * Met à jour la base de donnee
	 * @param sql : Requete de mise à jour
	 * @param tabParam : Tableau de valeurs de paramètres de la requete
	 * @return 1 si tout s'est bien passé, 0 sinon
	 */
	public int updateAvecParamGenerique(String sql,Object[] tabParam) {
		try {
			PreparedStatement preparedStatement = this.dbConnect.prepareStatement(sql);
			for (int i = 0; i < tabParam.length; i++) {
				preparedStatement.setObject(i+1, tabParam[i]);
			}
			return preparedStatement.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(GpEntityManager.class.getName()).log(Level.SEVERE, null, ex);
		}
		return 0;
	}
	/**
	 * Remonte l'identifiant d'un enregistrement par une colonne de type unique
	 * @param tableName : Nom de la table concern�e par la recherche
	 * @param whereColumnName : Colonne concernee par la clause where
	 * @param valueWhereColumnName : Valeur correspondante de recherche
	 * @param keyColumnName : Cle primaire recherch�e
	 * @return
	 */
	public Integer findIdByAnyColumn(String tableName,String whereColumnName,Object valueWhereColumnName,String keyColumnName) {
		String REQ_SQL = "SELECT "+keyColumnName+" FROM "+tableName+"  where "+whereColumnName+" = ? LIMIT 1";
    	Object[] tabParam = {valueWhereColumnName};
    	ResultSet resultat = this.selectAvecParamGenerique(REQ_SQL, tabParam);
    	Integer foundId = null;
    	if (resultat != null) {
            try {
				while (resultat.next()) {
					foundId = resultat.getInt(keyColumnName);
				}
				resultat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
		return foundId;
	}

	/**
	 * Fermer la connexion au serveur de DB
	 */
	public void close() {
		try {
			this.dbStatement.close();
			this.dbConnect.close();
		} catch (SQLException ex) {
			Logger.getLogger(GpEntityManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public Integer getNbMaxRow() {
		return nbMaxRow;
	}

	public void setNbMaxRow(Integer nbMaxRow) {
		this.nbMaxRow = nbMaxRow;
	}

	public java.sql.Connection getDbConnect() {
		return dbConnect;
	}

	public void setDbConnect(java.sql.Connection dbConnect) {
		this.dbConnect = dbConnect;
	}
}
