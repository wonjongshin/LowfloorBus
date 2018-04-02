package openAPI.BusXMLparser;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class BusXMLPullParserFactory {
	String urlStr;
	String encode;
	
	public BusXMLPullParserFactory(String addr, String func, String key, String full_parameter) {
		Initialize(addr, func, key, full_parameter, null);
		
	}
	
	public BusXMLPullParserFactory(String addr, String func, String key, String full_parameter, String encoding) {
		Initialize(addr, func, key, full_parameter, encoding);
		
	}
	
	private void Initialize(String addr, String func, String key, String full_parameter, String encoding) {
		urlStr = addr;
		if(urlStr.charAt( urlStr.length()-1 ) != '/') {
			urlStr += '/';
		}
		
		urlStr += func;
		
		try {
			urlStr += "?ServiceKey=" + URLEncoder.encode(key, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
			urlStr += "?ServiceKey=" + key;
		}
		
		urlStr += full_parameter;
		
		encode = encoding;
		
	}
	
	public XmlPullParser getParser() {

		XmlPullParserFactory parserCreator;
		XmlPullParser parser = null;
		try {
			URL url = new URL(urlStr);
			parserCreator = XmlPullParserFactory.newInstance();
			parser = parserCreator.newPullParser();

			// encode 위치에 "UTF-8" 또는 null
			parser.setInput( url.openStream(), encode );
			
		} catch (XmlPullParserException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
		return parser;
		
	}
}
