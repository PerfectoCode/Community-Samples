package pages;

import utilities.*;
import utilities.Library.byFields;

public class AmazonHome {
	private Library lib;

	public AmazonHome(Library l) {
		this.lib = l;
	}
	
	public void searchForItem(String searchText, int wait)
	{
		searchBoxText(searchText,wait);
		searchBoxSubmit(wait);
	}

	// enter text in search box
	public void searchBoxText(String searchbox, int wait) {
		lib.setText(byFields.xpath, "(//input[@id='nav-search-keywords' or @id='twotabsearchtextbox'] )[1]", searchbox,
				true, wait);

	}

	// submit the search box value
	public void searchBoxSubmit(int wait) {
		lib.submitElement(byFields.xpath, "(//input[@id='nav-search-keywords' or @id='twotabsearchtextbox'] )[1]",
				wait);
	}

}
