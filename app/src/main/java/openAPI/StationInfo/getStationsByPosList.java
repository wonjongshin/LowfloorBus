package openAPI.StationInfo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import openAPI.BusXMLparser.BusXMLPullParserFactory;

import org.xmlpull.v1.XmlPullParser;

import android.os.Handler;

/* 좌표기반 근접 정류소 조회
 * 기준좌표(tmX tmY) 반경(radius : 0~1500m) 을 입력 받는다.  */
public class getStationsByPosList extends Thread {
	Handler mhandler;
	int mwhat;
	int count;

	String addr = "http://ws.bus.go.kr/api/rest/stationinfo/";
	String function = "getStationByPos";
	String servicekey = YOUR_OPENAPI_KEY_HERE;

	String fullparam = "";
	String param1 = "&tmX=";
	String param2 = "&tmY=";
	String param3 = "&radius=";

	getStationsByPosList_Element Data;

	public getStationsByPosList(Handler phandler, int pwhat) {
		mhandler = phandler;
		mwhat = pwhat;
		
		setParam("", "", "");
		
	}
	
	public getStationsByPosList(Handler phandler, int pwhat, String parameter1, String parameter2, String parameter3) {
		mhandler = phandler;
		mwhat = pwhat;

		setParam(parameter1, parameter2, parameter3);
		
	}
	
	public void setParam(String parameter1, String parameter2, String parameter3) {
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
		
		try {
			fullparam += param3 + URLEncoder.encode(parameter3, "UTF-8");
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			fullparam += param3 + parameter3;
			
		}
		
	}
	
	
	public void run() {
    	Data = new getStationsByPosList_Element();
		request(addr, function, servicekey, fullparam);
		
		mhandler.sendEmptyMessage(mwhat);
		
	}
	
	
	public getStationsByPosList_Element getElement() {
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
	public double getgpxX(int index) {
		return Data.gpsX.get(index);
		
	}
	public double getgpsY(int index) {
		return Data.gpsY.get(index);
		
	}
	public int getstationId(int index) {
		return Data.stationId.get(index);
		
	}
	public String getstationNm(int index) {
		return Data.stationNm.get(index);
		
	}
	public int getstationTp(int index) {
		return Data.stationTp.get(index);
		
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
					else if(tag.equals("gpsX")) {
						tagnum = 4;
					}
					else if(tag.equals("gpsY")) {
						tagnum = 5;
					}
					else if(tag.equals("stationId")) {
						tagnum = 6;
					}
					else if(tag.equals("stationNm")) {
						tagnum = 7;
					}
					else if(tag.equals("stationTp")) {
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
						Data.gpsX.add( Double.parseDouble( parser.getText() ) );
						break;

					case 5:
						Data.gpsY.add( Double.parseDouble( parser.getText() ) );
						break;

					case 6:
						Data.stationId.add( Integer.parseInt( parser.getText() ) );
						break;

					case 7:
						Data.stationNm.add( parser.getText() );
						break;

					case 8:
						Data.stationTp.add( Integer.parseInt( parser.getText() ) );
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
