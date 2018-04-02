package openAPI.StationInfo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import openAPI.BusXMLparser.BusXMLPullParserFactory;

import org.xmlpull.v1.XmlPullParser;

import android.os.Handler;

/* 정류소고유번호와 노선id를 입력받아 첫차/막차예정시간을 조회한다.
 * 정류소고유번호(arsId), 노선Id(busRouteId) 를 입력 받는다.  */
public class getBustimeByStationList extends Thread {
	Handler mhandler;
	int mwhat;
	int count;

	String addr = "http://ws.bus.go.kr/api/rest/stationinfo/";
	String function = "getBustimeByStation";
	String servicekey = YOUR_OPENAPI_KEY_HERE;

	String fullparam = "";
	String param1 = "&arsId=";
	String param2 = "&busRouteId=";

	getBustimeByStationList_Element Data;

	public getBustimeByStationList(Handler phandler, int pwhat) {
		mhandler = phandler;
		mwhat = pwhat;
		
		setParam("", "");
		
	}
	
	public getBustimeByStationList(Handler phandler, int pwhat, String parameter1, String parameter2) {
		mhandler = phandler;
		mwhat = pwhat;

		setParam(parameter1, parameter2);
		
	}
	
	public void setParam(String parameter1, String parameter2) {
		try {
			fullparam = param1 + URLEncoder.encode(parameter1, "UTF-8");
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			fullparam = param1 + parameter1;
			
		}
		
		try {
			fullparam += param2 + URLEncoder.encode(parameter2, "UTF-8");
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			fullparam += param2 + parameter2;
			
		}
		
	}
	
	
	public void run() {
    	Data = new getBustimeByStationList_Element();
		request(addr, function, servicekey, fullparam);
		
		mhandler.sendEmptyMessage(mwhat);
		
	}
	
	
	public getBustimeByStationList_Element getElement() {
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
	
	public int getarsId(int index) {
		return Data.arsId.get(index);
		
	}
	public int getbusRouteId(int index) {
		return Data.busRouteId.get(index);
		
	}
	public String getbusRotueNm(int index) {
		return Data.busRouteNm.get(index);
		
	}
	public String getfirstBusTm(int index) {
		return Data.firstBusTm.get(index);
		
	}
	public String getlastBusTm(int index) {
		return Data.lastBusTm.get(index);
		
	}
	public String getstationNm(int index) {
		return Data.stationNm.get(index);
		
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
					
					else if(tag.equals("arsId")) {
						tagnum = 3;
					}
					else if(tag.equals("busRouteId")) {
						tagnum = 4;
					}
					else if(tag.equals("busRouteNm")) {
						tagnum = 5;
					}
					else if(tag.equals("firstBusTm")) {
						tagnum = 6;
					}
					else if(tag.equals("lastBusTm")) {
						tagnum = 7;
					}
					else if(tag.equals("stationNm")) {
						tagnum = 8;
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
						Data.arsId.add( Integer.parseInt( parser.getText() ) );
						break;

					case 4:
						Data.busRouteId.add( Integer.parseInt( parser.getText() ) );
						break;

					case 5:
						Data.busRouteNm.add( parser.getText() );
						break;

					case 6:
						Data.firstBusTm.add( parser.getText() );
						break;

					case 7:
						Data.lastBusTm.add( parser.getText() );
						break;
						
					case 8:
						Data.stationNm.add( parser.getText() );
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
