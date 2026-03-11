public class Main {
	public static void main(String[] args) {
		// JSON形式の文字列を模したもの
		String jsonData = "{\"title\":\"Frieren\", \"bpm\":126}";

		// ここからタイトルだけを抜き出すロジックを考える（Stringクラスのメソッド練習）
		int start = jsonData.indexOf("title\":\"") + 8;
		int end = jsonData.indexOf("\"", start);
		System.out.println("解析結果: " + jsonData.substring(start, end));

		// EP02のデータを想定したダミー作成
		String[] ep02Plugins = { "Operator", "Serum", "FabFilter Q3", "Roar" };
		ProjectData myProject = new ProjectData("Just", 124, 15.5, ep02Plugins);

		// コンソールに出力してみる
		myProject.displayInfo();

		// --- BPMを抜き出して数値に変換する練習 ---

		// 1. "bpm": の位置を探して、その後の数字の開始位置を特定
		int bpmStart = jsonData.indexOf("bpm\":") + 5;

		// 2. お尻の「}」の位置を探す
		int bpmEnd = jsonData.indexOf("}", bpmStart);

		// 3. 数字の部分だけを切り出す ("126" という文字列)
		String bpmStr = jsonData.substring(bpmStart, bpmEnd);

		// 4. 重要！文字列を「int型」の数値に変換する
		int bpmValue = Integer.parseInt(bpmStr);

		// 5. 数値なので計算してみる
		System.out.println("元のBPM: " + bpmValue);
		System.out.println("BPMを10上げると: " + (bpmValue + 10)); // 136になれば成功！

		// --- プラグインの使用回数を集計する練習 ---

		// 1. 複数のプロジェクト（ダミー）を配列にまとめる
		ProjectData[] myWorks = {
				new ProjectData("Just", 124, 15.5, new String[] { "Operator", "Serum", "Roar" }),
				new ProjectData("Frieren", 126, 10.0, new String[] { "Serum", "FabFilter Q3" }),
				new ProjectData("NewTrack", 128, 5.0, new String[] { "Operator", "Serum" })
		};

		// 2. 「Serum」が何回使われたか数える変数
		int serumCount = 0;
		String targetPlugin = "Serum";

		// 3. ループ（for文）で全てのプロジェクトをチェックする
		for (ProjectData project : myWorks) {
			// 各プロジェクトのプラグインリストを取り出す
			for (String plugin : project.getPlugins()) { // ※注：ProjectDataにgetPluginsメソッドが必要
				if (plugin.equals(targetPlugin)) {
					serumCount++;
				}
			}
		}

		System.out.println(targetPlugin + " の合計使用回数: " + serumCount + " 回");

		// --- 制作統計の算出練習 ---

		double totalHours = 0;
		int totalBpm = 0;

		for (ProjectData project : myWorks) {
			// 制作時間を加算
			totalHours += project.getDuration(); // ※注：ProjectDataにgetDurationメソッドが必要
			// BPMを加算
			totalBpm += project.getBpm(); // ※注：ProjectDataにgetBpmメソッドが必要
		}

		double averageBpm = (double) totalBpm / myWorks.length;

		System.out.println("これまでの総制作時間: " + totalHours + " 時間");
		System.out.println("平均制作BPM: " + averageBpm);
		
		// --- 条件フィルタリングの練習 ---

		System.out.println("【重点プロジェクト一覧 (10h以上)】");

		for (ProjectData project : myWorks) {
		    // 制作時間が10.0時間以上の場合のみ、中身を実行
		    if (project.getDuration() >= 10.0) {
		        // 条件に合う曲名と時間を表示
		        System.out.println("・" + project.getTitle() + " - " + project.getDuration() + " hours");
		    }
		}
		
		// --- ポートフォリオ用の最終レポート作成 ---
		System.out.println("\n=== Portfolio Report Summary ===");

		String report = String.format(
		    "Total Production Time: %.1f hours\n" +
		    "Average Tempo: %.1f BPM\n" +
		    "Main Weapon: %s (%d uses)",
		    totalHours, averageBpm, targetPlugin, serumCount
		);

		System.out.println(report);

	}
}