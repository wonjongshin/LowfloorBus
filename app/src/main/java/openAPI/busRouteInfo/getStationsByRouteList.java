package openAPI.busRouteInfo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import openAPI.BusXMLparser.BusXMLPullParserFactory;

import org.xmlpull.v1.XmlPullParser;

import android.location.Location;
import android.os.Handler;

/* 노선별 경유 정류소 조회 서비스
 * 노선 Id (busRouteId)를 매개변수로 가진다. */
public class getStationsByRouteList extends Thread {
	Handler mhandler;
	int mwhat;
	int count;

	String addr = "http://ws.bus.go.kr/api/rest/busRouteInfo/";
	String function = "getStaionByRoute";
	String servicekey = YOUR_OPENAPI_KEY_HERE;

	String fullparam = "";
	String param1 = "&busRouteId=";

	getStationsByRouteList_Element Data;

	public getStationsByRouteList(Handler phandler, int pwhat) {
		mhandler = phandler;
		mwhat = pwhat;
		
		setParam("");
		
	}
	
	public getStationsByRouteList(Handler phandler, int pwhat, String parameter1) {
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
    	Data = new getStationsByRouteList_Element();
		request(addr, function, servicekey, fullparam);
		
		mhandler.sendEmptyMessage(mwhat);
		
	}
		
	
	public getStationsByRouteList_Element getElement() {
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
	public String getbeginTm(int index) {
		return Data.beginTm.get(index);
		
	}
	public int getbusRouteId(int index) {
		return Data.busRouteId.get(index);
		
	}
	public String getRouteNm(int index) {
		return Data.busRouteNm.get(index);
		
	}
	public String direction(int index) {
		return Data.direction.get(index);
		
	}
	public double getgpsX(int index) {
		return Data.gpsX.get(index);
		
	}
	public double getgpsY(int index) {
		return Data.gpsY.get(index);
		
	}
	public String getlastTm(int index) {
		return Data.lastTm.get(index);
		
	}
	public int getrouteType(int index) {
		return Data.routeType.get(index);
		
	}
	public int getsection(int index) {
		return Data.section.get(index);
		
	}
	public int getseq(int index) {
		return Data.seq.get(index);
		
	}
	public int getstation(int index) {
		return Data.station.get(index);
		
	}
	public String getstationNm(int index) {
		return Data.stationNm.get(index);
		
	}
	public int getstationNo(int index) {
		return Data.stationNo.get(index);
		
	}
	public double getfullSectDist(int index) {
		return Data.fullSectDist.get(index);
		
	}
	public int gettrnstnid(int index) {
		return Data.trnstnid.get(index);
		
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
					else if(tag.equals("beginTm")) {
						tagnum = 3;
					}
					else if(tag.equals("busRouteId")) {
						tagnum = 4;
					}
					else if(tag.equals("busRouteNm")) {
						tagnum = 5;
					}
					else if(tag.equals("direction")) {
						tagnum = 6;
					}
					else if(tag.equals("gpsX")) {
						tagnum = 7;
					}
					else if(tag.equals("gpsY")) {
						tagnum = 8;
					}
					else if(tag.equals("lastTm")) {
						tagnum = 9;
					}
					else if(tag.equals("routeType")) {
						tagnum = 10;
					}
					else if(tag.equals("section")) {
						tagnum = 11;
					}
					else if(tag.equals("seq")) {
						tagnum = 12;
					}
					else if(tag.equals("station")) {
						tagnum = 13;
					}
					else if(tag.equals("stationNm")) {
						tagnum = 14;
					}
					else if(tag.equals("stationNo")) {
						tagnum = 15;
					}
					else if(tag.equals("fullSectDist")) {
						tagnum = 16;
					}
					else if(tag.equals("trnstnid")) {
						tagnum = 17;
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
						Data.beginTm.add( parser.getText() );
						break;

					case 4:
						Data.busRouteId.add( Integer.parseInt( parser.getText() ) );
						break;

					case 5:
						Data.busRouteNm.add( parser.getText() );
						break;

					case 6:
						Data.direction.add( parser.getText() );
						break;

					case 7:
						Data.gpsX.add( Location.convert( parser.getText() ) );
						break;

					case 8:
						Data.gpsY.add( Location.convert( parser.getText() ) );
						break;

					case 9:
						Data.lastTm.add( parser.getText() );
						break;

					case 10:
						Data.routeType.add( Integer.parseInt( parser.getText() ) );
						break;

					case 11:
						Data.section.add( Integer.parseInt( parser.getText() ) );
						break;

					case 12:
						Data.seq.add( Integer.parseInt( parser.getText() ) );
						break;

					case 13:
						Data.station.add( Integer.parseInt( parser.getText() ) );
						break;

					case 14:
						Data.stationNm.add( parser.getText() );
						break;

					case 15:
						Data.stationNo.add( Integer.parseInt( parser.getText() ) );
						break;

					case 16:
						Data.fullSectDist.add( Double.parseDouble( parser.getText() ) );
						break;

					case 17:
						Data.trnstnid.add( Integer.parseInt( parser.getText() ) );
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
