import java.sql.*;
import oracle.jdbc.pool.OracleDataSource;

public class Joker2 {
  public static void main(String[] args)
      throws SQLException, ClassNotFoundException, java.io.IOException {
    // Preparation de la connexion. 
    OracleDataSource ods = new OracleDataSource();
    ods.setUser("ialouani");
    ods.setPassword("ialouani");
    // URL de connexion, on remarque que le pilote utilise est "thin".
    ods.setURL("jdbc:oracle:thin:@localhost:1521/oracle");

    Connection conn = null;
    // Requete parametree.
    PreparedStatement pstmt1 = null;
    try {
      conn = ods.getConnection();
      // Execution et affichage de la requete parametree pour ? (joker) allant
      // de 1 � 10.
      for (int i = 1; i <= 10; i++) {
        pstmt1 = conn.prepareStatement("select distinct NUMERO_ACTEUR from ACTEUR natural join ROLE natural join FILM "
    	                                + "where DUREE = ?");
        pstmt1.setInt(1, (i+1)*30);//limitation a 33 minutes.
        ResultSet rset = pstmt1.executeQuery();
        while (rset.next()) {
        System.out.println("L'acteur joue dans un des films qui dure moins de 33 mns\t\t///" + rset.getString(1) + "///\t\tNUMERO_ACTEUR " );
        }
      }
    }
    finally {
      if (pstmt1 != null) {
	pstmt1.close();
      }
      if (conn != null) {
	conn.close();
      }
    }
  }
}
