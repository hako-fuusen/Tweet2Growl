package t2g;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.BeforeClass;
import org.junit.Test;

// src/test/resource/ngword.txtの記載内容によってテスト結果が変動するので注意
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

	@Test
	public void escape() {
		String testCase1 = "(笑)";

		assertThat(NGWordService.INSTANCE.constainNGWord(testCase1), is(true));
	}

	@Test
	public void regex() {
		String testCase1 = "はい #はいじゃないが";

		assertThat(NGWordService.INSTANCE.constainNGWord(testCase1), is(true));
		
		String testCase2 = "はい　#はいじゃないが";

		assertThat(NGWordService.INSTANCE.constainNGWord(testCase2), is(true));
		
		String testCase3 = "この写真にカエルが映っています #気がついた人はRT";

		assertThat(NGWordService.INSTANCE.constainNGWord(testCase3), is(true));
		
		String testCase4 = "#10人以上にRTされたら　ラーメンでも食べます";

		assertThat(NGWordService.INSTANCE.constainNGWord(testCase4), is(true));
	}
}
