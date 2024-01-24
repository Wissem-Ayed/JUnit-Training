package com.healthycoderapp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class BMICalculatorTest {

	@BeforeAll
	static void beforeAll() {
		System.out.println("test before all");
	}

	@AfterAll
	static void afterAll() {
		System.out.println("tests after all");
	}

	@Test
	void should_ReturnTrue_When_DietRecommended() {

		// given
		double weight = 98.0;
		double height = 1.72;
		// when

		boolean recommended = BMICalculator.isDietRecommended(weight, height);

		// then
		assertTrue(recommended);
	}

	@Test
	void should_ReturnFalse_When_DietRecommended() {

		// given
		double weight = 78.0;
		double height = 1.82;
		// when

		boolean recommended = BMICalculator.isDietRecommended(weight, height);

		// then
		assertFalse(recommended);
	}

	@Test
	void should_ThrowsArethmeticException_When_HeightZero() {

		// given
		double weight = 78.0;
		double height = 0;
		// when

		Executable executable = () -> BMICalculator.isDietRecommended(weight, height);

		// then
		assertThrows(ArithmeticException.class, executable);
	}

	@Test
	void should_ReturnCoderWithWorstBMI_When_ListNotEmpty() {

		// given
		List<Coder> coders = new ArrayList<Coder>();
		coders.add(new Coder(1.80, 60.0));
		coders.add(new Coder(1.82, 98.0));
		coders.add(new Coder(1.82, 64.7));
		// when

		Coder coder = BMICalculator.findCoderWithWorstBMI(coders);

		// then
		assertAll(() -> assertEquals(1.82, coder.getHeight()), () -> assertEquals(98.0, coder.getWeight()));
	}

	@Test
	void should_ReturnNull_When_ListNotEmpty() {

		// given
		List<Coder> coders = new ArrayList<Coder>();
		// when

		Coder coder = BMICalculator.findCoderWithWorstBMI(coders);

		// then
		assertNull(coder);
	}

	@Test
	void should_ReturnCorrectBMIScoreArray_When_CoderListNotEmpty() {

		// given
		List<Coder> coders = new ArrayList<Coder>();
		coders.add(new Coder(1.80, 60.0));
		coders.add(new Coder(1.82, 98.0));
		coders.add(new Coder(1.82, 64.7));
		double[] expected = { 18.52, 29.59, 19.53 };
		// when

		double[] bmiScores = BMICalculator.getBMIScores(coders);

		// then
		assertArrayEquals(expected, bmiScores);
	}
}
