package openAPI.busRouteInfo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import openAPI.BusXMLparser.BusXMLPullParserFactory;

import org.xmlpull.v1.XmlPullParser;

import android.os.Handler;

/* 노선 기본정보
 * 노선 Id (busRouteId)를 매개변수로 가진다. */
public class getRouteInfoItem extends Thread {
	Handler mhandler;
	int mwhat;
	int count;

	String addr = "http://ws.bus.go.kr/api/rest/busRouteInfo/";
	String function = "getRouteInfo";
	String servicekey = YOUR_OPENAPI_KEY_HERE;

	String fullparam = "";
	String param1 = "&busRouteId=";

	getRouteInfoItem_Element Data;

	public getRouteInfoItem(Handler phandler, int pwhat) {
		mhandler = phandler;
		mwhat = pwhat;
		
		setParam("");
		
	}
	
	public getRouteInfoItem(Handler phandler, int pwhat, String parameter1) {
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
    	Data = new getRouteInfoItem_Element();
		request(addr, function, servicekey, fullparam);
		
		mhandler.sendEmptyMessage(mwhat);
		
	}
		
	
	public getRouteInfoItem_Element getElement() {
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
	public String getedStationNm(int index) {
		return Data.edStationNm.get(index);
		
	}
	public Object getfirstBusTm(int index) {
		return Data.firstBusTm.get(index);
		
	}
	public Object getfirLowTm(int index) {
		return Data.firstLowTm.get(index);
		
	}
	public Object getlastBusTm(int index) {
		return Data.lastBusTm.get(index);
		
	}
	public Object getlastbusYn(int index) {
		return Data.lastBusYn.get(index);
		
	}
	public Object getlastLowTm(int index) {
		return Data.lastLowTm.get(index);
		
	}
	public float getlength(int index) {
		return Data.length.get(index);
		
	}
	public int getrouteType(int index) {
		return Data.routeType.get(index);
		
	}
	public String getstStationNm(int index) {
		return Data.stStationNm.get(index);
		
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
					else if(tag.equals("edStationNm")) {
						tagnum = 5;
					}
					else if(tag.equals("firstBusTm")) {
						tagnum = 6;
					}
					else if(tag.equals("firstLowTm")) {
						tagnum = 7;
					}
					else if(tag.equals("lastBusTm")) {
						tagnum = 8;
					}
					else if(tag.equals("lastBusYn")) {
						tagnum = 9;
					}
					else if(tag.equals("lastLowTm")) {
						tagnum = 10;
					}
					else if(tag.equals("length")) {
						tagnum = 11;
					}
					else if(tag.equals("routeType")) {
						tagnum = 12;
					}
					else if(tag.equals("stStationNm")) {
						tagnum = 13;
					}
					else if(tag.equals("term")) {
						tagnum = 14;
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
						Data.edStationNm.add( parser.getText() );
						break;

					case 6:
						Data.firstBusTm.add( parser.getText() );
						break;

					case 7:
						Data.firstLowTm.add( parser.getText() );
						break;

					case 8:
						Data.lastBusTm.add( parser.getText() );
						break;

					case 9:
						Data.lastBusYn.add( parser.getText() );
						break;

					case 10:
						Data.lastLowTm.add( parser.getText() );
						break;

					case 11:
						Data.length.add( Float.parseFloat( parser.getText() ) );
						break;

					case 12:
						Data.routeType.add( Integer.parseInt( parser.getText() ) );
						break;

					case 13:
						Data.stStationNm.add( parser.getText() );
						break;

					case 14:
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
