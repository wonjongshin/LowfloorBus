package openAPI.StationInfo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import openAPI.BusXMLparser.BusXMLPullParserFactory;

import org.xmlpull.v1.XmlPullParser;

import android.os.Handler;

/* 정류소고유번호를 입력받아 경유하는 노선목록을 조회한다.
 * 정류소고유번호(arsId) 를 입력 받는다.  */
public class getRouteByStationList extends Thread {
	Handler mhandler;
	int mwhat;
	int count;

	String addr = "http://ws.bus.go.kr/api/rest/stationinfo/";
	String function = "getRouteByStation";
	String servicekey = YOUR_OPENAPI_KEY_HERE;

	String fullparam = "";
	String param1 = "&arsId=";

	getRouteByStationList_Element Data;

	public getRouteByStationList(Handler phandler, int pwhat) {
		mhandler = phandler;
		mwhat = pwhat;
		
		setParam("");
		
	}
	
	public getRouteByStationList(Handler phandler, int pwhat, String parameter1) {
		mhandler = phandler;
		mwhat = pwhat;

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
    	Data = new getRouteByStationList_Element();
		request(addr, function, servicekey, fullparam);
		
		mhandler.sendEmptyMessage(mwhat);
		
	}
	
	
	public getRouteByStationList_Element getElement() {
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

	public int getbusRouteId(int index) {
		return Data.busRouteId.get(index);
		
	}
	public String getbusRouteNm(int index) {
		return Data.busRouteNm.get(index);
		
	}
	public int getbusRouteType(int index) {
		return Data.busRouteType.get(index);
		
	}
	public double getlength(int index) {
		return Data.length.get(index);
		
	}
	public int getnextBus(int index) {
		return Data.nextBus.get(index);
		
	}
	public String getstBegin(int index) {
		return Data.stBegin.get(index);
		
	}
	public String getstEnd(int index) {
		return Data.stEnd.get(index);
		
	}
	public int getterm(int index) {
		return Data.term.get(index);
		
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
					
					
					else if(tag.equals("busRouteId")) {
						tagnum = 3;
					}
					else if(tag.equals("busRouteNm")) {
						tagnum = 4;
					}
					else if(tag.equals("busRouteType")) {
						tagnum = 5;
					}
					else if(tag.equals("length")) {
						tagnum = 6;
					}
					else if(tag.equals("nextBus")) {
						tagnum = 7;
					}
					else if(tag.equals("stBegin")) {
						tagnum = 8;
					}
					else if(tag.equals("stEnd")) {
						tagnum = 9;
					}
					else if(tag.equals("term")) {
						tagnum = 10;
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
						Data.busRouteId.add( Integer.parseInt( parser.getText() ) );
						break;
						
					case 4:
						Data.busRouteNm.add( parser.getText() );
						break;
						
					case 5:
						Data.busRouteType.add( Integer.parseInt( parser.getText() ) );
						break;
						
					case 6:
						Data.length.add( Double.parseDouble( parser.getText() ) );
						break;
						
					case 7:
						Data.nextBus.add( Integer.parseInt( parser.getText() ) );
						break;
						
					case 8:
						Data.stBegin.add( parser.getText() );
						break;
						
					case 9:
						Data.stEnd.add( parser.getText() );
						break;
						
					case 10:
						Data.term.add( Integer.parseInt( parser.getText() ) );
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
