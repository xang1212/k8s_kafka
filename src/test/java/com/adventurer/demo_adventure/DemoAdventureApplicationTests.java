package com.adventurer.demo_adventure;

import com.adventurer.demo_adventure.model.AdventureModel;
import com.adventurer.demo_adventure.model.ResponseModel;
import com.adventurer.demo_adventure.repository.AdventureNativeRepository;
import com.adventurer.demo_adventure.repository.AdventureNativeRepositoryTest;
import com.adventurer.demo_adventure.service.AdventureService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DemoAdventureApplicationTests {

	@Test
	public void test_insertAdventure_expect_201(){
		AdventureNativeRepository adventureNativeRepository  = new AdventureNativeRepositoryTest();
		AdventureService adventureService = new AdventureService(adventureNativeRepository);

		AdventureModel adventureModel = new AdventureModel();
		adventureModel.setName("Adventure");
		adventureModel.setBalance(BigDecimal.valueOf(600));
		ResponseModel<Integer> result =adventureService.insertAdventure(adventureModel);
		Assertions.assertTrue(result.getStatust() == 201);

	}

	@Test
	public void test_insertAdventure_expect_400() {
		AdventureNativeRepository adventureNativeRepository = new AdventureNativeRepositoryTest();
		AdventureService adventureService = new AdventureService(adventureNativeRepository);

		AdventureModel adventureModel = new AdventureModel();
		adventureModel.setName(null);
		adventureModel.setBalance(BigDecimal.valueOf(600));

		ResponseModel<Integer> result = adventureService.insertAdventure(adventureModel);
		Assertions.assertTrue(result.getStatust() == 400);

	}

	@Test
	public void test_insertAdventure_expect_500() {
		AdventureNativeRepository adventureNativeRepository = mock(AdventureNativeRepository.class);
		when(adventureNativeRepository.insertAdventure(any())).thenThrow(new RuntimeException("Simulated exception"));

		AdventureService adventureService = new AdventureService(adventureNativeRepository);

		AdventureModel adventureModel = new AdventureModel();
		adventureModel.setName("Adventure");
		adventureModel.setBalance(BigDecimal.valueOf(600));

		ResponseModel<Integer> result = adventureService.insertAdventure(adventureModel);
		assertEquals(500, result.getStatust());
	}

}
