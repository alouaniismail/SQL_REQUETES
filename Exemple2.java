import java.sql.*;
import oracle.jdbc.pool.OracleDataSource;

public class Exemple2 {
  public static void main(String[] args)
    throws SQLException, ClassNotFoundException, java.io.IOException {
    // Preparation de la connexion.
    OracleDataSource ods = new OracleDataSource();
    ods.setUser("ialouani");
    ods.setPassword("ialouani");
    // URL de connexion, on remarque que le pilote utilise est "thin".
    ods.setURL("jdbc:oracle:thin:@localhost:1521/oracle");

    Connection conn = null;
    Statement stmt = null;
    try {
      conn = ods.getConnection();
      stmt = conn.createStatement();
      // Execution de la requete.
      ResultSet rset = stmt.executeQuery("select * from ACTEUR");
      ResultSetMetaData rslt=rset.getMetaData();
      int number=rslt.getColumnCount();
      int a=1;
      while(a<=number){
	//a: numero de la colonne courante.
  System.out.println(a+" "+rslt.getColumnName(a)+" "+rslt.getColumnTypeName(a));
	  a++;
      }
    }
    finally {
      if (stmt != null) {
	stmt.close();
      }
      if (conn != null) {
	conn.close();
      }
    }
  }
}

