package test;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import edu.tju.ina.seeworld.util.enums.MultimediaType;

public class EnumTest {
	@Test
	public void testMultimediaType(){
		MultimediaType type = MultimediaType.Movie;
		System.out.println(type.name());
	}
	
	public void testStringUtil(){
		System.out.println(StringUtils.difference("Movie", "MOVIE"));
		
	}
}
