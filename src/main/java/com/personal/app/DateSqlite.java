package com.personal.app;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

public class DateSqlite implements UserType {

	public Object assemble(Serializable arg0, Object arg1) throws HibernateException {
		// TODO Auto-generated method stub
		return arg0;
	}

	public Object deepCopy(Object arg0) throws HibernateException {
		// TODO Auto-generated method stub
		//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return arg0; // dateFormat.format((Date)arg0);
	}

	public Serializable disassemble(Object arg0) throws HibernateException {
		// TODO Auto-generated method stub
		return (Serializable) arg0;
	}

	public boolean equals(Object x, Object y) throws HibernateException {
		// TODO Auto-generated method stub
		return x == y || !(x == null || y == null) && x.equals(y);
	}

	public int hashCode(Object arg0) throws HibernateException {
		// TODO Auto-generated method stub
		return arg0.hashCode();
	}

	public boolean isMutable() {
		// TODO Auto-generated method stub
		return false;
	}

	public Object nullSafeGet(ResultSet arg0, String[] arg1, SessionImplementor arg2, Object arg3)
			throws HibernateException, SQLException {
		// TODO Auto-generated method stub
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = dateFormat.parse(arg0.getString(arg1[0]));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(arg0.wasNull())
			return null;
		return date;
	}

	 
	
	public void nullSafeSet(PreparedStatement arg0, Object arg1, int arg2)
			throws HibernateException, SQLException {
		if (arg1 == null) {
        	arg0.setNull(arg2,Types.DATE);
        }
        else {
            Date date = (Date) arg1;
        	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            arg0.setString(arg2,dateFormat.format(date));  //(arg2,new java.sql.Date(date.getTime()));
        }
		
	}
	
	
	public Object replace(Object arg0, Object arg1, Object arg2) throws HibernateException {
		// TODO Auto-generated method stub
		return arg0;
	}

	public Class returnedClass() {
		// TODO Auto-generated method stub
		return Date.class;
	}

	public int[] sqlTypes() {
		// TODO Auto-generated method stub
		return new int[]{Types.DATE};
	}

	public void nullSafeSet(PreparedStatement arg0, Object arg1, int arg2, SessionImplementor arg3)
			throws HibernateException, SQLException {
		// TODO Auto-generated method stub
		nullSafeSet(arg0,arg1,arg2);
		
	}



}
