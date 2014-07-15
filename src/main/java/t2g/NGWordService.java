package t2g;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public enum NGWordService {
	INSTANCE;

	private List<String> ngwords = new ArrayList<String>();

	public boolean constainNGWord(String text) {
		return ngwords.stream().anyMatch(ngword -> text.matches(ngword));
	}

	public void initialize() throws IOException, URISyntaxException {
		// 画像ファイルロードの方式にならってファイル読み込み
		URL url = Thread.currentThread().getContextClassLoader().getResource("ngword.txt");

		Path path = Paths.get(url.toURI());

		ngwords = Files.readAllLines(path).stream()
				.map(ngwords -> ".*" + ngwords + ".*") // NGワードの確認は何度も行われるので、事前に加工しておく
				.collect(Collectors.toList());
	}
}
