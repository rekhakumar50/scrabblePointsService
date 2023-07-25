package com.example.demo.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.demo.util.Utility;

public class RequestValidationTest {

	@InjectMocks
	private RequestValidation requestValidation;
	
	private MockedStatic<Utility> mockStatic;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		mockStatic = Mockito.mockStatic(Utility.class);
	}
	
	
	@Test
	public void testValidateScore() {
		mockStatic.when(() -> Utility.validateWord(anyString())).thenReturn(true);
		boolean isValid = requestValidation.validateStudent(com.example.demo.utility.Utility.getScoreDto("xxxx", 4));

		assertEquals(true, isValid);
		mockStatic.close();
	}
	
	
	@Test
	public void testValidateScoreWithInvalidDate() {
		mockStatic.when(() -> Utility.validateWord(anyString())).thenReturn(false);
		boolean isValid = requestValidation.validateStudent(com.example.demo.utility.Utility.getScoreDto("xxxx", 4));

		assertEquals(false, isValid);
		mockStatic.close();
	}
	
}
