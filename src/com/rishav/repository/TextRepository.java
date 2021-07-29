package com.rishav.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.rishav.model.TextModel;
import com.rishav.util.MySQLConnUtils;

public class TextRepository {

	private Connection dbConnection;

	public TextRepository() {
		//openConnection();
	}

	public TextModel save(TextModel model) {
		openConnection();
		int status = 0;
		String query = "Insert into text (title,password,content,exposure,start_date,end_date) values (?,?,?,?,?,?)";
		try {
			PreparedStatement statement = dbConnection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, model.getTitle());
			statement.setString(2, model.getPassword());
			statement.setString(3, model.getText());
			statement.setString(4, model.getExposure());
			statement.setDate(5, model.getStartDate());
			statement.setDate(6, model.getEndDate());

			status = statement.executeUpdate();
			
			if (status == 0) {
	            throw new SQLException("Creating user failed, no rows affected.");
	        }

	        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                model.setId((int) generatedKeys.getLong(1));
	            }
	            else {
	                throw new SQLException("Creating user failed, no ID obtained.");
	            }
	        }
			dbConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return model;
	}

	public int update(TextModel model) {
		openConnection();
		int status = 0;
		String query = "update text set title=?,password=?,content=?,exposure=?,start_date=?,end_date=? where id =?";
		try {
			PreparedStatement statement = dbConnection.prepareStatement(query);

			statement.setString(1, model.getTitle());
			statement.setString(2, model.getPassword());
			statement.setString(3, model.getText());
			statement.setString(4, model.getExposure());
			statement.setDate(5, model.getStartDate());
			statement.setDate(6, model.getEndDate());
			statement.setInt(7, model.getId());

			status = statement.executeUpdate();
			dbConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	public int delete(int id) {
		int status = 0;
		try {
			PreparedStatement ps = dbConnection.prepareStatement("delete from text where id=?");
			ps.setInt(1, id);
			status = ps.executeUpdate();

			dbConnection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	public TextModel getContentById(int id) {
		openConnection();
		TextModel e = new TextModel();

		try {
			// Connection con=EmpDao.getConnection();
			PreparedStatement ps = dbConnection.prepareStatement("select * from text where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				e.setId(rs.getInt("id"));
				e.setTitle(rs.getString("title"));
				e.setPassword(rs.getString("password"));
				e.setExposure(rs.getString("exposure"));
				e.setText(rs.getString("content"));
				e.setStartDate(rs.getDate("start_date"));
				e.setEndDate(rs.getDate("end_date"));
			}
			dbConnection.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return e;
	}
	
	public List<TextModel> getAllContent(){  
		openConnection();
        List<TextModel> list=new ArrayList<>();  
          
        try{   
            PreparedStatement ps=dbConnection.prepareStatement("select * from text");  
            ResultSet rs=ps.executeQuery();  
            while(rs.next()){  
                TextModel e=new TextModel();  
                e.setId(rs.getInt("id"));
				e.setTitle(rs.getString("title"));
				e.setPassword(rs.getString("password"));
				e.setExposure(rs.getString("exposure"));
				e.setText(rs.getString("content"));
				e.setStartDate(rs.getDate("start_date"));
				e.setEndDate(rs.getDate("end_date")); 
                list.add(e);  
            }  
            dbConnection.close();  
        }catch(Exception e){e.printStackTrace();}  
          
        return list;  
    }  
	
	private void openConnection() {
		try {
			dbConnection = MySQLConnUtils.getMySQLConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
