package ar.com.cuys.webapp.service;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ar.com.cuys.webapp.entity.Item;
import ar.com.cuys.webapp.exception.RssException;

public class RssServiceTest {

	private RssService rssService;
	
	@Before
	public void setUp() throws Exception {
		rssService = new RssService();
	}

	@Test
	public void testGetItemsFile() throws RssException {
		List<Item> items = rssService.getItems(new File("src/test/resources/cuys.xml"));
		assertEquals(19, items.size());
	}

	@Test
	public void testGetItemsString() throws RssException {
		List<Item> items = rssService.getItems("https://twitrss.me/twitter_user_to_rss/?user=cuys");
		assertEquals(19, items.size());
	}

}
