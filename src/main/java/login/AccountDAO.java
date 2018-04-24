package login;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class AccountDAO implements Serializable{

	private static Connection getConnection() throws URISyntaxException, SQLException {
	    URI dbUri = new URI(System.getenv("DATABASE_URL"));

	    String username = dbUri.getUserInfo().split(":")[0];
	    String password = dbUri.getUserInfo().split(":")[1];
	    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";

	    return DriverManager.getConnection(dbUrl, username, password);
	}

	public boolean insert(UserAccount account){
		PreparedStatement ps = null;
		int flag = 1;
		Connection com = null;
		try{
			com = getConnection();

			if(idcheck(account)){
				ps = com.prepareStatement("insert into public.account(user_id,password,email,birthday) values(?,?,?,?)");

				ps.setString(1,account.getId());
				ps.setString(2,account.getPass());
				ps.setString(3,account.getEmail());
				ps.setString(4,account.getBirthday());

				if(ps.executeUpdate() == 0){
					return false;
				}
				flag = 0;
			}
			else{return false;}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(ps != null){ps.close();}
				if(com != null){com.close();}
			}catch(Exception e){}
		}
		if(flag == 0){return true;}
		else{return false;}
	}

	public boolean check(UserAccount account){
		Connection db = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int flag = 1;
		int idcheck = 0;

		try{
			db = getConnection();

			ps = db.prepareStatement("select * from public.account order by user_id");
			rs = ps.executeQuery();

			while(rs.next()){
				UserAccount enter = new UserAccount();

				enter.setId(rs.getString("user_id"));
				enter.setPass(rs.getString("password"));
					if(enter.getId().equals(account.getId()) && enter.getPass().equals(account.getPass())){
						flag = 0;
						break;
					}
					if(enter.getId().equals(account.getId())){
						System.out.print("重複発見");
						idcheck = 1;
					}
				}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs != null){rs.close();}
				if(ps != null){ps.close();}
			}catch(Exception e){}
		}
		if(idcheck == 1){return false;}

		if(flag != 1){return true;}
		else {return false;}
	}

	public boolean idcheck(UserAccount account){
		Connection db = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int idcheck = 0;

		try{
			db = getConnection();

			ps = db.prepareStatement("select * from public.account order by user_id");
			rs = ps.executeQuery();

			while(rs.next()){
				UserAccount enter = new UserAccount();

				enter.setId(rs.getString("user_id"));

					if(enter.getId().equals(account.getId())){
						System.out.print("重複発見");
						idcheck = 1;
					}
				}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs != null){rs.close();}
				if(ps != null){ps.close();}
			}catch(Exception e){}
		}
		if(idcheck == 1){return false;}

		return true;
	}

	public ArrayList<UserAccount> selectAllUsers(){
		Connection db = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<UserAccount> accountList = new ArrayList<UserAccount>();

		try{
			db = getConnection();

			ps = db.prepareStatement("select * from public.account order by user_id");
			rs = ps.executeQuery();

			while(rs.next()){
				UserAccount enter = new UserAccount();
				enter.setId(rs.getString("user_id"));
				enter.setEmail(rs.getString("email"));
				enter.setBirthday(rs.getString("birthday"));
				accountList.add(enter);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs != null){rs.close();}
				if(ps != null){ps.close();}
			}catch(Exception e){}
		}
		return accountList;
	}

	public boolean update(UserAccount account) {
		Connection db = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try{
			db = getConnection();

			ps = db.prepareStatement("update public.account set user_id=?,email=?,birthday=? where user_id=?");
			ps.setString(1,account.getId());
			ps.setString(2,account.getEmail());
			ps.setString(3,account.getBirthday());
			ps.setString(4,account.getId());

			if(ps.executeUpdate() == 0){
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs != null){rs.close();}
				if(ps != null){ps.close();}
			}catch(Exception e){}
		}
		return true;
	}

	public UserAccount selectId(String id) {
		Connection db = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		UserAccount account = new UserAccount();

		try{
			db = getConnection();

			ps = db.prepareStatement("select * from public.account where user_id=?");
			ps.setString(1,id);
			rs = ps.executeQuery();

			account.setId(rs.getString("user_id"));
			account.setEmail(rs.getString("email"));
			account.setBirthday(rs.getString("birthday"));
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs != null){rs.close();}
				if(ps != null){ps.close();}
			}catch(Exception e){}
		}
		return account;
	}

	public boolean delete(String id) {
		PreparedStatement ps = null;
		Connection com = null;
		try{
			com = getConnection();
			ps = com.prepareStatement("delete from public.account where user_id=?");
			ps.setString(1,id);
			if(ps.executeUpdate() == 0){
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(ps != null){ps.close();}
				if(com != null){com.close();}
			}catch(Exception e){}
		}
		return true;
	}
}
