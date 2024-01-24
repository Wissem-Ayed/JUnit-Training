package com.healthycoderapp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DietPlannerTest {

	private DietPlanner dietPlanner;

	@BeforeEach
	void setup() {
		this.dietPlanner = new DietPlanner(20, 30, 50);
	}
	@AfterEach
	void finish() {
		System.out.println("tests finished");
	}

	@Test
	void should_ReturnCorrectDietPlan_When_CorrectCoder() {
		// given
		Coder coder = new Coder(1.82, 75.0, 26, Gender.MALE);
		DietPlan expecteDietPlan = new DietPlan(2202, 110, 73, 275);
		// when
		DietPlan actualDietPlan = dietPlanner.calculateDiet(coder);
		// then
		assertAll(() -> assertEquals(expecteDietPlan.getCalories(), actualDietPlan.getCalories()),
				() -> assertEquals(expecteDietPlan.getCarbohydrate(), actualDietPlan.getCarbohydrate()),
				() -> assertEquals(expecteDietPlan.getFat(), actualDietPlan.getFat()),
				() -> assertEquals(expecteDietPlan.getProtein(), actualDietPlan.getProtein()));
	}

}
