package Com.ty;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class HotelMenu {
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelmenudet","root","root");
			boolean repeat=true;
			do {
				System.out.println("Hotel AMD");
			System.out.println("1.Add Menu\n2.Delete Menu\n3.Update Menu\n4.Get Menu\n5.Get Item\n6.Exit");
			System.out.println("Enter your Choice");
			int ch =scanner.nextInt();
			switch(ch) {
			
			//Case-1 "Add Menu"
			case 1:{
				System.out.println("Enter the id");
				int id=scanner.nextInt();
				
				
				System.out.println("Enter the Name");
				String name=scanner.next();
				
				System.out.println("Enter the Quantity");
				int qnt=scanner.nextInt();
				
				System.out.println("Enter the Cost");
				int cost=scanner.nextInt();
				
				PreparedStatement PreparedStatement=connection.prepareStatement("insert into hotelmenu values(?,?,?,?)");
				
				PreparedStatement.setInt(1, id);
				PreparedStatement.setString(2, name);
				PreparedStatement.setInt(3, qnt);
				PreparedStatement.setInt(4, cost);
				
				PreparedStatement.execute();
				
				connection.close();

				System.out.println("Menu Saved......");
				System.out.println("---------------------------------");
				
			}
			
			break;
			
			
			//Case-2 "Delete Menu"
			case 2:{
				System.out.println("Enter the id");
				int id=scanner.nextInt();
				
				PreparedStatement PreparedStatement=connection.prepareStatement("Delete from hotelmenu where id=?");
				
				PreparedStatement.setInt(1, id);
				
				PreparedStatement.execute();
				
				System.out.println("Item Deleted......");
			}
			break;
			//Case-3 "Update Menu"
			case 3:{
				
				System.out.println("Enter the id");
				int id=scanner.nextInt();
				
				System.out.println("Enter the Item Name to Update");
				String name=scanner.next();
				
				PreparedStatement PreparedStatement=connection.prepareStatement("update hotelmenu set name=? where id=?");
				
				
				PreparedStatement.setString(1,name);
				PreparedStatement.setInt(2, id);
				
				PreparedStatement.execute();
				
				System.out.println("Item Updated......");
				
			}
			break;
			// Case-4 "Get Menu 
			case 4:{
				System.out.println("Enter the id");
				int id=scanner.nextInt();
				
				PreparedStatement PreparedStatement=connection.prepareStatement("select * from hotelmenu ");
				
				ResultSet resultSet=PreparedStatement.executeQuery();
				while(resultSet.next()) {
					System.out.println("---------------------------------------");
					System.out.println("The Item id is :"+resultSet.getInt(1));
					System.out.println("The Item Name is :"+resultSet.getString(2));
					System.out.println("The Qnt of Item is :"+resultSet.getInt(3));
					System.out.println("The Cost of Item is :"+resultSet.getInt(4));
					System.out.println("---------------------------------------");
				}
			}
				break;
				
				//case-5 "Get Item"
			case 5:{
				System.out.println("Enter the id");
				int id=scanner.nextInt();
				
				PreparedStatement PreparedStatement=connection.prepareStatement("select * from hotelmenu where id=? ");
				PreparedStatement.setInt(1,id);
				ResultSet resultSet=PreparedStatement.executeQuery();
				while(resultSet.next()) {
					System.out.println("---------------------------------------");
					System.out.println("The Item id is :"+resultSet.getInt(1));
					System.out.println("The Item Name is :"+resultSet.getString(2));
					System.out.println("The Qnt of Item is :"+resultSet.getInt(3));
					System.out.println("The Cost of Item is :"+resultSet.getInt(4));
					System.out.println("---------------------------------------");
				}
				
			
			}
				
			//case-6 "Exit"
			case 6:{
				repeat=false;
				System.out.println("Thank You");
				System.out.println("---------------------------------");
			}
			break;
			
				default:
				System.out.println("Invalid Choice");
				System.out.println("---------------------------------");
				break;
			}
			}
			while(repeat);
			connection.close();
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
