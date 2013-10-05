package me.hopto.patriarch.incrementer.data;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class) 
public class BuiltTest {

	@Before
	public void setup() throws Exception {
	}
	
	@Test
	public void emptyTest() {
		
		assertThat(true).isTrue();
	}

}
