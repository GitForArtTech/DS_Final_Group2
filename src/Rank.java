import java.util.ArrayList;
public class Rank {
		private static ArrayList<Keyword> keywords = new ArrayList<>();
		
		public static ArrayList<Keyword> getKeywords(){
			return keywords;
		}
		public static void addKeyWords(Keyword keyword) {
			keywords.add(keyword);
		}
		public static void clean() {
			keywords = new ArrayList<>();
		}
	
}
