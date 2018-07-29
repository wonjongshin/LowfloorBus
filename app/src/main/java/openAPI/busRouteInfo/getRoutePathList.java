package openAPI.busRouteInfo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import openAPI.BusXMLparser.BusXMLPullParserFactory;

import org.xmlpull.v1.XmlPullParser;

import android.location.Location;
import android.os.Handler;

/* 노선의 지도상 경로를 리턴한다.
 * 노선 Id (busRouteId)를 매개변수로 가진다. */
public class getRoutePathList extends Thread {
	Handler mhandler;
	int mwhat;
	int count;

	String addr = "http://ws.bus.go.kr/api/rest/busRouteInfo/";
	String function = "getRoutePath";
	String servicekey;

	String fullparam = "";
	String param1 = "&busRouteId=";

	getRoutePathList_Element Data;

	public getRoutePathList(Handler phandler, int pwhat) {
		mhandler = phandler;
		mwhat = pwhat;

		this.servicekey = "";
		setParam("");
		
	}
	
	public getRoutePathList(Handler phandler, int pwhat, String serviceKey, String parameter1) {
		mhandler = phandler;
		mwhat = pwhat;

		this.servicekey = serviceKey;
		setParam(parameter1);
		
	}
	
	public void setParam(String parameter1) {
		try {
			fullparam = param1 + URLEncoder.encode(parameter1, "UTF-8");
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			fullparam = param1 + parameter1;
			
		}
		
	}
	
	
	public void run() {
    	Data = new getRoutePathList_Element();
		request(addr, function, servicekey, fullparam);
		
		mhandler.sendEmptyMessage(mwhat);
		
	}
		
	
	public getRoutePathList_Element getElement() {
		return Data;
		
	}

	public int getheaderCd() {
		return Data.headerCd;
		
	}
	public String getheaderMsg() {
		return Data.headerMsg;
		
	}
	public int getitemCount() {
		return Data.itemCount;
		
	}
	public int getseq(int index) {
		return Data.no.get(index);
		
	}
	public double getgpsX(int index) {
		return Data.gpsX.get(index);
		
	}
	public double getgpsY(int index) {
		return Data.gpsY.get(index);
		
	}
    	
    protected void request(String addr, String function, String servicekey, String full_parameter) {

		try {
			BusXMLPullParserFactory factory = new BusXMLPullParserFactory(addr, function, servicekey, full_parameter);
			XmlPullParser parser = factory.getParser();
			
			if(parser == null) {
				Data.headerCd = -1;
				Data.headerMsg = "Parser 생성 실패";
				
				return;
			}
			
			String tag;
			int parserEvent = parser.getEventType();
			
			int tagnum = -1;
			count = 0;
			while (parserEvent != XmlPullParser.END_DOCUMENT ) {
				
				switch(parserEvent) {
				case XmlPullParser.START_TAG:
					tag = parser.getName();

					if(tag.equals("itemList")) {
						count++;
					}
					else if(tag.equals("headerCd")) {
						tagnum = 0;
					}
					else if(tag.equals("headerMsg")) {
						tagnum = 1;
					}
					else if(tag.equals("itemCount")) {
						tagnum = 2;
					}
					else if(tag.equals("gpsX")) {
						tagnum = 3;
					}
					else if(tag.equals("gpsY")) {
						tagnum = 4;
					}
					else if(tag.equals("no")) {
						tagnum = 5;
					}
					else {
						tagnum = -1;
					}
					break;
				/* XmlPullParser.START_TAG 끝 */
					
				case XmlPullParser.TEXT:
					tag = parser.getName();
					
					switch(tagnum) {
					case 0:
						Data.headerCd = Integer.parseInt( parser.getText() );
						break;

					case 1:
						Data.headerMsg = parser.getText();
						break;

					case 2:
						Data.itemCount = Integer.parseInt( parser.getText() );
						break;

					case 3:
						Data.gpsX.add( Location.convert(parser.getText()) );
						break;

					case 4:
						Data.gpsY.add( Location.convert(parser.getText()) );
						break;

					case 5:
						Data.no.add( Integer.parseInt( parser.getText() ) );
						break;
						
					default:
						break;
						
					} /* Switch 끝 */
					
					break;
				/* XmlPullParser.TEXT 끝 */
				
				case XmlPullParser.END_TAG:
					tag = parser.getName();
					break;
				/* XmlPullParser.END_TAG 끝 */
					
				case XmlPullParser.IGNORABLE_WHITESPACE:
					// 빈칸을 무시
					break;
				/* XmlPullParser.IGNORABLE_WHITESPACE 끝 */
					
				default:
					break;
					
				} /* Switch 끝 */
				
				parserEvent = parser.nextToken(); 
				
			} /* while 끝 */

		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		// 전송받은 itemCount 값이 정확하게 않는 경우도 있기 떄문에 직접 세서 저장
		Data.itemCount = count;
		
		return;

    } /* request 함수 끝 */

}
