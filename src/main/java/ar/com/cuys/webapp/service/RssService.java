package ar.com.cuys.webapp.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;

import ar.com.cuys.webapp.entity.Item;
import ar.com.cuys.webapp.exception.RssException;
import ar.com.cuys.webapp.rss.ObjectFactory;
import ar.com.cuys.webapp.rss.TRss;
import ar.com.cuys.webapp.rss.TRssChannel;
import ar.com.cuys.webapp.rss.TRssItem;

public class RssService {
	
	public List<Item> getItems(Source source) throws RssException{
		List<Item> list = new ArrayList<Item>();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			JAXBElement<TRss> jaxbElement = unmarshaller.unmarshal(source, TRss.class);
			TRss rss = jaxbElement.getValue();
			List<TRssChannel> channels = rss.getChannel();
			for(TRssChannel channel : channels){
				List<TRssItem> items = channel.getItem();
				for(TRssItem rssItem : items){
					Item item = new Item();
					item.setTitle(rssItem.getTitle());
					item.setDescription(rssItem.getDescription());
					item.setLink(rssItem.getLink());
					Date date = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z",Locale.ENGLISH).parse(rssItem.getPubDate());
					item.setPublishedDate(date);
					list.add(item);
				}
				
			}					
		} catch (JAXBException e) {
			throw new RssException(e);
		} catch (ParseException e) {
			throw new RssException(e);
		}
		return list;
	}

}
