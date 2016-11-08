package com.personal.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class AppDateTest {

	
	@Test
	public void testDate() throws ParseException
	{
		String  cadena = "2016-11-07";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(116,10,07);
		Assert.assertEquals(dateFormat.format(date),"2016-11-07");
		Assert.assertEquals(dateFormat.parse("2016-11-07").getYear(),116);
		Assert.assertEquals(dateFormat.parse("2016-11-07").getMonth(),10);
		Assert.assertEquals(dateFormat.parse("2016-11-07").getDate(),7);

	}
}
