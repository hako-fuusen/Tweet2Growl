package t2g;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum NGWordService {
	INSTANCE;

	private List<String> ngwords = new ArrayList<String>();

	public boolean constainNGWord(String text) {
		return ngwords.stream().anyMatch(ngword -> text.matches(ngword));
	}

	public void initialize() throws IOException, URISyntaxException {
		Optional<Path> ngWordFilePath = getNGWordTxtPath();

		// ifPreを使ってみたかったが、Files.readAllLinesがキャッチ例外出すのでやむなくisPreで対応
		if (ngWordFilePath.isPresent()) {
			Path path = ngWordFilePath.get();

			ngwords = Files.readAllLines(path).stream()
					.map(ngwords -> ".*" + ngwords + ".*") // NGワードの確認は何度も行われるので、事前に加工しておく
					.collect(Collectors.toList());
		}
	}

	/**
	 * JUnit, StreamText, Jarでそれぞれ実行時のロケーションが違うので、単一の指定だけだとngword.txtが取得できない。 
	 * そのため、各実行パターンごとのngword.txt読み込み処理を実装する
	 */
	private Optional<Path> getNGWordTxtPath() {
		Path path;

		// 通常の起動パターン時
		path = Paths.get(".", "ngword.txt");
		if (Files.isReadable(path)) {
			return Optional.of(path);
		}

		// JUnit, StreamTestから起動のパターン
		URL url = Thread.currentThread().getContextClassLoader()
				.getResource("ngword.txt");
		try {
			path = Paths.get(url.toURI());
			return Optional.of(path);

		} catch (URISyntaxException e) {
		}

		return Optional.empty();
	}
}
