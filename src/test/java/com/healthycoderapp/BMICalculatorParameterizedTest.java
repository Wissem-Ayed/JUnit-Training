package com.healthycoderapp;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class BMICalculatorParameterizedTest {

	@BeforeAll
	static void beforeAll() {
		System.out.println("test before all");
	}

	@AfterAll
	static void afterAll() {
		System.out.println("tests after all");
	}
	
	
	@Nested
	class isDietRecommendedTests{
		@ParameterizedTest
		@ValueSource(doubles = {85.0, 90.0, 95.0})
		void should_ReturnTrue_When_DietRecommended(Double coderWeight) {

			// given
			double weight = coderWeight;
			double height = 1.72;
			// when

			boolean recommended = BMICalculator.isDietRecommended(weight, height);

			// then
			assertTrue(recommended);
		}
		@ParameterizedTest(name= "weight={0}, height={1}")
		@CsvSource(value ={"89.0, 1.72","95.0, 1.75","110.0, 1.78"})
		void should_ReturnTrue_When_DietRecommended2(Double coderWeight,Double coderHeight) {

			// given
			double weight = coderWeight;
			double height = coderHeight;
			// when

			boolean recommended = BMICalculator.isDietRecommended(weight, height);

			// then
			assertTrue(recommended);
		}
		@ParameterizedTest(name= "weight={0}, height={1}")
		@CsvFileSource(resources ="/diet-recommended-input-data.csv",numLinesToSkip = 1)
		void should_ReturnTrue_When_DietRecommended3(Double coderWeight,Double coderHeight) {

			// given
			double weight = coderWeight;
			double height = coderHeight;
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
		
		@RepeatedTest(10)
		void should_ReturnFalse_When_DietRecommendedRepeated() {

			// given
			double weight = 78.0;
			double height = 1.82;
			// when

			boolean recommended = BMICalculator.isDietRecommended(weight, height);

			// then
			assertFalse(recommended);
		}
	}
    @Nested
	class FindcoderWithWortsBMITests{
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
	void should_ReturnCoderWithWorstBMIIn100Ms_When_ListHas10000Elements() {

		// given
		List<Coder> coders = new ArrayList<Coder>();
		for(int i =0 ; i < 10000;i++) {
		coders.add(new Coder(2+i,10+i ));}
		// when

		Executable executable =() -> BMICalculator.findCoderWithWorstBMI(coders);

		// then
		assertTimeout(Duration.ofMillis(100), executable);
	}

	@Test
	//to ignore the test and still see it for future requirements
	@Disabled
	void should_ReturnNull_When_ListNotEmpty() {

		// given
		List<Coder> coders = new ArrayList<Coder>();
		// when

		Coder coder = BMICalculator.findCoderWithWorstBMI(coders);

		// then
		assertNull(coder);
	}}
    @Nested
	class FindBMIScoresTest{
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


}
