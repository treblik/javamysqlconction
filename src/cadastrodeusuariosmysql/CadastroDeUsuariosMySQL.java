 
package cadastrodeusuariosmysql;
 
import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class CadastroDeUsuariosMySQL {

  
    public static void main(String[] args) {
        
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o email:");
        String email = scanner.nextLine();
        System.out.println("Digite a senha:");
        String senha = scanner.nextLine();
        Usuario u = new Usuario();
        u.setEmail(email);
        u.setSenha(senha);
        
        
        inserirUsuario(u);
    }
    public static void inserirUsuario(Usuario u){
        try{
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);
            
            Connection connection =DriverManager.getConnection("jdbc:mysql://localhost:3306?useTimezone=true&serverTimezone=UTC","root","");
            
           PreparedStatement stmt = connection.prepareStatement("INSERT INTO cadastro_usuarios.usuarios(email,senha)VALUES(?,?)");
           
           stmt.setString(1,u.getEmail());
           stmt.setString(2,u.getSenha());
           
           int linhasAfetadas = stmt.executeUpdate();
            
           if (linhasAfetadas > 0){
               System.out.println("Usuário cadastrado com sucesso");
           }else{
               System.out.println("Algo de errado não está certo");
           }
           
        }catch(Exception e){
           e.printStackTrace();
        }
        
    }
    
}
