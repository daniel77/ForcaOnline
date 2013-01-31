package br.edu.up.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLAzureScriptRunner {

   public static void main(String[] args) {

      // Create a variable for the connection string.
      String connectionUrl = "jdbc:sqlserver://ajeuq4qabd.database.windows.net:1433;database=forca;user=forca@ajeuq4qabd;password={Daniel01};encrypt=true;hostNameInCertificate=*.database.windows.net;loginTimeout=30;"; 
      
      // Declare the JDBC objects.
      Connection con = null;
      Statement stmt = null;
      ResultSet rs = null;

      try {
         // Establish the connection.
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
         con = DriverManager.getConnection(connectionUrl);
         
         System.out.println(con.isClosed());
         // Create and execute an SQL statement that returns some data.
         //String SQL = "CREATE TABLE PALAVRA(ID int IDENTITY PRIMARY KEY, PALAVRA VARCHAR(40) NOT NULL , DICA VARCHAR(100) NOT NULL)";
         //String SQL = "DROP TABLE PALAVRA";
         // String SQL = "insert into PALAVRA(PALAVRA, DICA) values ('DANIEL' , 'CRIADOR DESTE SOFTWARE')";
         
         String SQL = "SELECT * FROM PALAVRA";
         
         stmt = con.createStatement();
         // stmt.execute(SQL);
         rs = stmt.executeQuery(SQL);
         // Iterate through the data in the result set and display it.
         while (rs.next()) {
            System.out.println(rs.getString(2) + " " + rs.getString(3));
         }
      }

      // Handle any errors that may have occurred.
      catch (Exception e) {
         e.printStackTrace();
      }
      finally {
         if (rs != null) try { rs.close(); } catch(Exception e) {}
         if (stmt != null) try { stmt.close(); } catch(Exception e) {}
         if (con != null) try { con.close(); } catch(Exception e) {}
      }
   }
}