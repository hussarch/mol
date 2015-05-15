package com.hussar.framework.common;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.hussar.framework.common.io.SimpleCacher;

public class SimpleCacherTest {

	private SimpleCacher cacher;
	
	@Before
	public void setUp() throws Exception {
		cacher = new SimpleCacher("/cache.data");
	}

	@Test
	public void test_save_read() {
		String value = "2015-2-12";
		cacher.saveVaule("maj.interim.adjustment.day", value);
		assertEquals(value, cacher.getValue("maj.interim.adjustment.day"));
	}

}
