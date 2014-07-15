package t2g;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class NGWordServiceTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		NGWordService.INSTANCE.initialize();
	}

	@Test
	public void noescape() {
		String testCase1 = "shindanテストケース";
		String testCase2 = "テストshindanケース";
		String testCase3 = "テストケースshindan";

		assertThat(NGWordService.INSTANCE.constainNGWord(testCase1), is(true));
		assertThat(NGWordService.INSTANCE.constainNGWord(testCase2), is(true));
		assertThat(NGWordService.INSTANCE.constainNGWord(testCase3), is(true));
		
		String testCase4 = "shindan";
		String testCase5 = " shindan";
		String testCase6 = "shindan ";

		assertThat(NGWordService.INSTANCE.constainNGWord(testCase4), is(true));
		assertThat(NGWordService.INSTANCE.constainNGWord(testCase5), is(true));
		assertThat(NGWordService.INSTANCE.constainNGWord(testCase6), is(true));
		
		String testCase7 = "shindam";

		assertThat(NGWordService.INSTANCE.constainNGWord(testCase7), is(false));
	}

}
