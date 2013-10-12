package me.hopto.patriarch.incrementer.app.calculator;

import static org.assertj.core.api.Assertions.assertThat;
import me.hopto.patriarch.incrementer.core.formula.Formula;
import me.hopto.patriarch.incrementer.core.formula.FormulaWrapper;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FormulaWrapperTest {
	@Rule
	public TestName					name		= new TestName();
	private static Logger		logger	= Logger.getLogger(FormulaWrapperTest.class);
	private FormulaWrapper	formulaWrapper;
	private int							level;
	private double					nextCost;
	private double					globalCost;
	private double					nextIncrement;
	private double					globalIncrement;

	@Before
	public void setup() {
		logger.debug("[BEGIN] " + name.getMethodName());
		level = 0;
		formulaWrapper = new FormulaWrapper(0.1d, 15.0d, new Formula() {
			private static final long	serialVersionUID	= -972193014569969911L;

			@Override
			public double getNextCostForLevel(double baseCost, int level) {
				return lameHackForRounding(lameHackForRounding(baseCost * (Math.pow(1.15d, level))));
			}

			@Override
			public double getGlobalCostForLevel(double baseCost, int level) {
				return lameHackForRounding((baseCost * (Math.pow(1.15D, level) - 1.0d)) / 0.15d);
			}

			@Override
			public double getGlobalIncrementForLevel(double baseIncrement, int level) {
				return lameHackForRounding(baseIncrement * level);
			}
		});
	}

	@After
	public void tearDown() throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("next cost: " + nextCost);
			logger.debug("global cost: " + globalCost);
			logger.debug("next increment: " + nextIncrement);
			logger.debug("global increment: " + globalIncrement);
			logger.debug("[ END ] " + name.getMethodName());
		}
	}

	@Test
	public void testForLevel0() {
		// Setup

		// Test
		nextCost = formulaWrapper.getNextCostForLevel(level);
		globalCost = formulaWrapper.getGlobalCostForLevel(level);
		nextIncrement = formulaWrapper.getNextIncrement();
		globalIncrement = formulaWrapper.getGlobalIncrementForLevel(level);

		// Assert
		assertThat(nextCost).isEqualTo(15.0d);
		assertThat(globalCost).isEqualTo(0.0d);
		assertThat(nextIncrement).isEqualTo(0.1d);
		assertThat(globalIncrement).isEqualTo(0.0d);
	}

	@Test
	public void testForLevel1() {
		// Setup
		level = 1;

		// Test
		nextCost = formulaWrapper.getNextCostForLevel(level);
		globalCost = formulaWrapper.getGlobalCostForLevel(level);
		nextIncrement = formulaWrapper.getNextIncrement();
		globalIncrement = formulaWrapper.getGlobalIncrementForLevel(level);

		// Assert
		assertThat(nextCost).isEqualTo(17.25d);
		assertThat(globalCost).isEqualTo(15.0d);
		assertThat(nextIncrement).isEqualTo(0.1d);
		assertThat(globalIncrement).isEqualTo(0.1d);
	}

	@Test
	public void testForLevel2() {
		// Setup
		level = 2;

		// Test
		nextCost = formulaWrapper.getNextCostForLevel(level);
		globalCost = formulaWrapper.getGlobalCostForLevel(level);
		nextIncrement = formulaWrapper.getNextIncrement();
		globalIncrement = formulaWrapper.getGlobalIncrementForLevel(level);

		// Assert
		assertThat(nextCost).isEqualTo(19.84d);
		assertThat(globalCost).isEqualTo(32.25d);
		assertThat(nextIncrement).isEqualTo(0.1d);
		assertThat(globalIncrement).isEqualTo(0.2d);
	}

	@Test
	public void testForLevel3() {
		// Setup
		level = 3;

		// Test
		nextCost = formulaWrapper.getNextCostForLevel(level);
		globalCost = formulaWrapper.getGlobalCostForLevel(level);
		nextIncrement = formulaWrapper.getNextIncrement();
		globalIncrement = formulaWrapper.getGlobalIncrementForLevel(level);

		// Assert
		assertThat(nextCost).isEqualTo(22.81d);
		assertThat(globalCost).isEqualTo(52.09d);
		assertThat(nextIncrement).isEqualTo(0.1d);
		assertThat(globalIncrement).isEqualTo(0.3d);
	}

	@Test
	public void testForLevel4() {
		// Setup
		level = 4;

		// Test
		nextCost = formulaWrapper.getNextCostForLevel(level);
		globalCost = formulaWrapper.getGlobalCostForLevel(level);
		nextIncrement = formulaWrapper.getNextIncrement();
		globalIncrement = formulaWrapper.getGlobalIncrementForLevel(level);

		// Assert
		assertThat(nextCost).isEqualTo(26.24d);
		assertThat(globalCost).isEqualTo(74.9d);
		assertThat(nextIncrement).isEqualTo(0.1d);
		assertThat(globalIncrement).isEqualTo(0.4d);
	}

	@Test
	public void testForLevel5() {
		// Setup
		level = 5;

		// Test
		nextCost = formulaWrapper.getNextCostForLevel(level);
		globalCost = formulaWrapper.getGlobalCostForLevel(level);
		nextIncrement = formulaWrapper.getNextIncrement();
		globalIncrement = formulaWrapper.getGlobalIncrementForLevel(level);

		// Assert
		assertThat(nextCost).isEqualTo(30.17d);
		assertThat(globalCost).isEqualTo(101.14d);
		assertThat(nextIncrement).isEqualTo(0.1d);
		assertThat(globalIncrement).isEqualTo(0.5d);
	}
}
