package net.jtmcgee.project.jokeme;

import net.jtmcgee.project.jokeme.services.Jokes2Go;

public class CLI {

	public static void main(String[] args) {
		Jokes2Go j2go = new Jokes2Go();
		
		if(args.length == 0) {
			System.out.println(j2go.getJoke());
			System.out.println("If you want to choose a category type 'jokeme CATEGORY'. To see the list of categories, type 'jokeme categories'");
			return;
		}
		
		if(args[0].toUpperCase().startsWith("HELP")) {
			System.out.println("JokeMe is a mini project of Jordan DeLoach (@jtmcgee). \nNote: Joke appropriateness cannot be guaranteed. Proceed at your own risk." +
					" \nWorking it isn't very complex. Just run 'jokeme' or " +
					"'jokeme CATEGORYnameHERE'. You can see the following available categories below, or by typing 'jokeme categories'.\n" +
					"Enjoy. Contribute at github.com/jdeloach/jokeme\n");
			for(String s: j2go.getCategories())
				if(s != null)
					System.out.println(s);
		} else if(args[0].toUpperCase().startsWith("CAT")) {
			for(String s: j2go.getCategories())
				if(s != null)
					System.out.println(s);
		}			
		
		// put everything in args[0] so when we check if it contains it, it is all in one string
		for(int i = 1; i < args.length; i++)
			args[0] += args[i];
		
		// if they input a string of a category ORRRRRRR it is a valid int spot of a cat
		try {
			if(new Integer(args[0]).intValue() < 41) {
				System.out.println(j2go.getJoke(new Integer(args[0]).intValue()));
				return;
			}
		} catch (NumberFormatException nfe) {} // no need to catch it, just means they typed a cat name instead of an int

		String[] cats = j2go.getCategories();
		for(int i = 0; i < cats.length; i++)
			if(cats[i] != null)
				if(cats[i].toUpperCase().startsWith(args[0].toUpperCase()))
					System.out.println(j2go.getJoke(i));
	}

}
