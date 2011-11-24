package net.jtmcgee.project.jokeme.services;

import java.util.ArrayList;

import net.jtmcgee.project.jokeme.Service;

public class Jokes2Go extends Service {

	public static final String url = "http://www.jokes2go.com/cgi-perl/randjoke.cgi?type=j";
	public static final String catUrl = "&dbnum=19910&iteration=17&cat=";
	public static final String catUrl2 = "&next=Another+Joke&menu=..%2Findex.html";
	
	@Override
	public String getJoke() {
		return getJoke(0);
	}

	@Override
	public String getJoke(int category) {
		if(category == 25 || category > 40)
			return "Not a valid category";
		
		int found = 0;
		StringBuilder joke = new StringBuilder();
		String[] page;
		
		if(category == 0)
			page = getURL(url);
		else
			page = getURL(url + catUrl + category + catUrl2);

		// it starts around 140ish, just to save time looping
		for(int i = 140; i < page.length; i++) {
			if(page[i].startsWith("<PRE style=\"font-family")) {
				page[i] = page[i].substring(49);
				found = i;
				break;
			}
		}
		
		if(found != 0) {
			String nextLine = page[found];
			while(!nextLine.startsWith("</td>")) {
				joke.append(nextLine + " ");
				found++;
				nextLine = page[found];
			}				
		}
		
		return joke.toString();
	}
	
	@Override
	public String[] getCategories() {
		String[] categories = new String[41];
		
		categories[26] = "Animal World";
		categories[2] = "At Work";
		categories[31] = "Barney";
		categories[1] = "Blondes";
		categories[16] = "Books";
		categories[39] = "Celebrities";
		categories[9] = "Children";
		categories[6] = "Computer Related";
		categories[34] = "Criminals";
		categories[28] = "Drunks";

		categories[37] = "Elderly";
		categories[13] = "Ethnic";
		categories[36] = "Father Goose Stories";
		categories[40] = "Food and Drink";
		categories[18] = "Foreign";
		// 25 removed
		categories[3] = "Historical Stuff";
		categories[35] = "Lawers and Legal";
		categories[12] = "Letters";
		categories[17] = "Marriage and Relationships";
		categories[19] = "Medicine";
		categories[7] = "Men";
		categories[32] = "Miscellaneous";
		categories[24] = "Music";
		categories[5] = "Ouch!";
		categories[22] = "Politics";
		categories[4] = "Practical Jokes";

		categories[15] = "Religion and Church";
		categories[30] = "Roads and Driving";
		categories[21] = "School and College";
		categories[14] = "Science Related";
		categories[27] = "Sex";
		categories[29] = "Situations";
		categories[33] = "Songs";
		categories[23] = "Sports";
		categories[20] = "Tests";
		categories[38] = "Travel";
		categories[10] = "War and Military";
		categories[8] = "Women";
		
		return categories;
	}

}
