import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class ConnectionClass {
   public static void main(String args[]) {
      Connection c = null;
      Statement stmt = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://ec2-54-147-36-107.compute-1.amazonaws.com:5432/deum74j36kqraj",
            "kexafudwppoppl", "c0abaa9ed698fdce77c4c79079ca966d7b06eb9f7a524cb3db5a90faf9c8eb6c");
            System.out.println("success");
         stmt = c.createStatement();

         ResultSet rs = stmt.executeQuery( "select * from player" );

         while ( rs.next() ) {

         int id = rs.getInt("id");

         String  first_name = rs.getString("first_name");

         String  last_name = rs.getString("last_name");

         String  codename = rs.getString("codename"); 

         System.out.printf( "id = %s , first_name = %s, last_name = %s, codename = %s ", id,first_name, last_name, codename );

         System.out.println();
         }
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);

      }
      System.out.println("Opened database successfully");
   }
}