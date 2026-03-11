public class ProjectData {
    private String title;      // 曲名
    private int bpm;           // テンポ
    private double duration;    // 制作時間(h)
    private String[] plugins;  // 使用プラグイン

    // コンストラクタ
    public ProjectData(String title, int bpm, double duration, String[] plugins) {
        this.title = title;
        this.bpm = bpm;
        this.duration = duration;
        this.plugins = plugins;
    }

    // データを表示するメソッド（練習用）
    public void displayInfo() {
        System.out.println("Track: " + title + " (" + bpm + " BPM)");
        System.out.println("Time spent: " + duration + " hours");
        System.out.print("Plugins: ");
        for (String p : plugins) System.out.print("[" + p + "] ");
        System.out.println("\n---");
    }
    
 // プラグインリストを返すメソッド
    public String[] getPlugins() {
        return this.plugins;
    }
    
    public double getDuration() {
        return this.duration;
    }

    public int getBpm() {
        return this.bpm;
    }
    
    public String getTitle() {
        return this.title;
    }
}