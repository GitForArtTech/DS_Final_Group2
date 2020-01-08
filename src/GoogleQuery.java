import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;


public class GoogleQuery

{

	public String searchKeyword;

	public String url;

	public String content;

	public GoogleQuery(String searchKeyword)

	{

		this.searchKeyword = searchKeyword;

		this.url = "http://www.google.com.tw/search?q=" + searchKeyword + "&oe=utf8&num=20";

	}

	private String fetchContent() throws IOException

	{
		String retVal = "";

		URL u = new URL(url);

		URLConnection conn = u.openConnection();

		conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");

		InputStream in = conn.getInputStream();

		InputStreamReader inReader = new InputStreamReader(in, "utf-8");

		BufferedReader bufReader = new BufferedReader(inReader);
		String line = null;

		while ((line = bufReader.readLine()) != null) {
			retVal += line;

		}
		return retVal;
	}

	public ArrayList<WebTree>[] query() throws IOException {
		ArrayList<WebTree> dead = new ArrayList<WebTree>();
		ArrayList<Keyword> keywords = Rank.getKeywords();
		ArrayList<WebTree> al = new ArrayList<WebTree>();
		if (content == null)

		{

			content = fetchContent();

		}
		Document doc = Jsoup.parse(content);
		System.out.println(doc.text());
		Elements lis = doc.select("div");
		lis = lis.select(".O9g5cc");
		System.out.println(lis.size());
		
		for (int i = 0; i < lis.size(); i++) {
			System.out.println(i);
			Element li = lis.get(i);
			String errorUrl = "";
			try {
				String title = li.select(".BNeawe").get(0).text();
				String citeUrl = li.select("a").get(0).attr("href");
				errorUrl = citeUrl;
				if (citeUrl.contains("/search"))
					continue;
				if (citeUrl.contains("/url?"))
					citeUrl = "http://google.com" + citeUrl;
				System.out.println(title);
				WebPage querytree = new WebPage("" + citeUrl, "" + title);
				WebTree qt = new WebTree(querytree);
				qt.setPostOrderScore(keywords);
				al.add(qt);

				// for(int i = 0 ; i < block.size(); i++)
				// System.out.println(block.get(i).text());

				// System.out.println(block.get(1).text());
				// System.out.println(block.get(2).text());

				// String title = block.get(1).text();
				// String citeUrl = block.get(2).text();

				// System.out.println(title+" "+citeUrl);

				//				retVal.put(title, citeUrl);
			} catch (Exception e) {
				System.out.println(errorUrl);
			}

		}
		System.out.println("running default tree");
		WebPage rootPage1 = new WebPage("http://www.meetpets.org.tw/pets/dog", "找一隻愛你的狗| 台灣認養地圖");
		WebTree tree1 = new WebTree(rootPage1);
		tree1.root.addChild(new WebNode(new WebPage("http://www.meetpets.org.tw/pets/dog?page=1", "第二頁")));
		tree1.root.addChild(new WebNode(new WebPage("http://www.meetpets.org.tw/pets/dog?page=2", "第三頁")));
		tree1.root.children.get(1)
		.addChild(new WebNode(new WebPage("http://www.meetpets.org.tw/content/74529", "小黑狗")));
		WebPage rootPage3 = new WebPage("http://www.doghome.org.tw/phpbb2/viewforum.php?f=37&code=foster", "流浪動物花園");
		WebTree tree3 = new WebTree(rootPage3);
		tree3.root.addChild(new WebNode(
				new WebPage("http://www.doghome.org.tw/phpbb2/viewforum.php?f=137&code=foster", "全省狗狗送養區")));
		tree3.root.addChild(
				new WebNode(new WebPage("http://www.doghome.org.tw/phpbb2/viewforum.php?f=38&code=foster", "全省貓貓送養區")));
		tree3.root.addChild(
				new WebNode(new WebPage("http://www.doghome.org.tw/phpbb2/viewforum.php?f=16&code=foster", "走失協尋")));

		WebPage rootPage4 = new WebPage("https://act-adopt.ahiqo.ntpc.gov.tw/adopt_list.php", "新北市政府-動物保護防疫處");
		WebTree tree4 = new WebTree(rootPage4);
		tree4.root.addChild(
				new WebNode(new WebPage("https://act-adopt.ahiqo.ntpc.gov.tw/adopt_list.php?ctid=1r", "認養狗兒")));
		tree4.root.addChild(
				new WebNode(new WebPage("https://act-adopt.ahiqo.ntpc.gov.tw/adopt_list.php?ctid=2", "認養貓兒")));
		tree4.root.addChild(
				new WebNode(new WebPage("https://act-adopt.ahiqo.ntpc.gov.tw/adopt_list.php?flag=1", "認養其他")));
		tree1.setPostOrderScore(keywords);
		tree3.setPostOrderScore(keywords);
		tree4.setPostOrderScore(keywords);
		dead.add(tree1);
		dead.add(tree3);
		dead.add(tree4);
		System.out.println("Sorting");
		Collections.sort(dead, new SortbyScore());
		Collections.sort(al, new SortbyScore());
		System.out.println("Sorting end");
		for(int i=0;i<=2; i++) {
			System.out.println(dead.get(i).root.webPage.name+" "+ dead.get(i).root.nodeScore + dead.get(i).root.webPage.url);
		}		
		for(int i=0;i<al.size(); i++) {
			System.out.println(al.get(i).root.webPage.name+" "+ al.get(i).root.nodeScore + al.get(i).root.webPage.url);
		}	
		return new ArrayList[] {dead, al};

	}

	static class SortbyScore implements Comparator<WebTree> {
		// Used for sorting in descending order of
		// nodeScore
		public int compare(WebTree a, WebTree b) {
			return (int) (b.root.nodeScore - a.root.nodeScore);
		}
	}

}
