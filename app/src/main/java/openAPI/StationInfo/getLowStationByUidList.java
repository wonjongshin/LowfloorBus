package openAPI.StationInfo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import openAPI.BusXMLparser.BusXMLPullParserFactory;

import org.xmlpull.v1.XmlPullParser;

import android.os.Handler;

/* 고유번호를 입력받은 정류소의 저상버스 도착정보 제공
 * arsId 를 입력 받는다.  */
public class getLowStationByUidList extends Thread {
	Handler mhandler;
	int mwhat;
	int count;

	String addr = "http://ws.bus.go.kr/api/rest/stationinfo/";
	String function = "getLowStationByUid";
	String servicekey = YOUR_OPENAPI_KEY_HERE;

	String fullparam = "";
	String param1 = "&arsId=";

	getLowStationByUidList_Element Data;

	public getLowStationByUidList(Handler phandler, int pwhat) {
		mhandler = phandler;
		mwhat = pwhat;
		
		setParam("");
		
	}
	
	public getLowStationByUidList(Handler phandler, int pwhat, String parameter1) {
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
    	Data = new getLowStationByUidList_Element();
		request(addr, function, servicekey, fullparam);
		
		mhandler.sendEmptyMessage(mwhat);
		
	}
	
	
	public getLowStationByUidList_Element getElement() {
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
	public String getfirstTm(int index) {
		return Data.firstTm.get(index);
		
	}
	public String getlastTm(int index) {
		return Data.lastTm.get(index);
		
	}
	public int getrouteType(int index) {
		return Data.routeType.get(index);
		
	}
	public String getrtNm(int index) {
		return Data.rtNm.get(index);
		
	}
	public int getstaOrd(int index) {
		return Data.staOrd.get(index);
		
	}
	public int getterm(int index) {
		return Data.term.get(index);
		
	}
	public int getarsId(int index) {
		return Data.arsId.get(index);
		
	}
	public int getstID(int index) {
		return Data.stID.get(index);
		
	}
	public String getstNm(int index) {
		return Data.stNm.get(index);
		
	}
	
	///////////////////////// ElementItem //////////////////////////
	
	public int getElementItemCount() {
		return Data.ElementItemCount;
		
	}
	public getLowStationByUidList_ElementItem getElementItem(int index) {
		return Data.ElementItem.get(index);
		
	}
	public int getElementItem_busType(int index) {
		return Data.ElementItem.get(index).busType;
		
	}
	public int getElementItem_isArrive(int index) {
		return Data.ElementItem.get(index).isArrive;
		
	}
	public int getElementItem_isLast(int index) {
		return Data.ElementItem.get(index).isLast;
		
	}
	public String getElementItem_plainNo(int index) {
		return Data.ElementItem.get(index).plainNo;
		
	}
	public String getElementItem_repTm(int index) {
		return Data.ElementItem.get(index).repTm;
		
	}
	public int getElementItem_sectOrd(int index) {
		return Data.ElementItem.get(index).sectOrd;
		
	}
	public String getElementItem_stationNm(int index) {
		return Data.ElementItem.get(index).stationNm;
		
	}
	public int getElementItem_traSpd(int index) {
		return Data.ElementItem.get(index).traSpd;
		
	}
	public int getElementItem_traTime(int index) {
		return Data.ElementItem.get(index).traTime;
		
	}
	public int getElementItem_vehId(int index) {
		return Data.ElementItem.get(index).vehId;
		
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
			int elementindex = -1;
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
					else if(tag.equals("firstTm")) {
						tagnum = 4;
					}
					else if(tag.equals("lastTm")) {
						tagnum = 5;
					}
					else if(tag.equals("routeType")) {
						tagnum = 6;
					}
					else if(tag.equals("rtNm")) {
						tagnum = 7;
					}
					else if(tag.equals("staOrd")) {
						tagnum = 8;
					}
					else if(tag.equals("term")) {
						tagnum = 9;
					}
					else if(tag.equals("arsId")) {
						tagnum = 10;
					}
					else if(tag.equals("stID")) {
						tagnum = 11;
					}
					else if(tag.equals("stNm")) {
						tagnum = 12;
					}

					///////////////////////// ElementItem //////////////////////////
					else if(tag.startsWith("busType")) {
						// busType일 경우 새 객체 추가
						Data.addElementItem();
						tagnum = 13;
						if(tag.endsWith("1"))
							elementindex = 0;
						else if(tag.endsWith("2"))
							elementindex = 1;
						else
							elementindex = -1;
					}
					else if(tag.compareTo("isArrive") == 0) {
						tagnum = 14;
						if(tag.endsWith("1"))
							elementindex = 0;
						else if(tag.endsWith("2"))
							elementindex = 1;
						else
							elementindex = -1;
					}
					else if(tag.compareTo("isLast") == 0) {
						tagnum = 15;
						if(tag.endsWith("1"))
							elementindex = 0;
						else if(tag.endsWith("2"))
							elementindex = 1;
						else
							elementindex = -1;
					}
					else if(tag.compareTo("plainNo") == 0) {
						tagnum = 16;
						if(tag.endsWith("1"))
							elementindex = 0;
						else if(tag.endsWith("2"))
							elementindex = 1;
						else
							elementindex = -1;
					}
					else if(tag.compareTo("repTm") == 0) {
						tagnum = 17;
						if(tag.endsWith("1"))
							elementindex = 0;
						else if(tag.endsWith("2"))
							elementindex = 1;
						else
							elementindex = -1;
					}
					else if(tag.compareTo("sectOrd") == 0) {
						tagnum = 18;
						if(tag.endsWith("1"))
							elementindex = 0;
						else if(tag.endsWith("2"))
							elementindex = 1;
						else
							elementindex = -1;
					}
					else if(tag.compareTo("stationNm") == 0) {
						tagnum = 19;
						if(tag.endsWith("1"))
							elementindex = 0;
						else if(tag.endsWith("2"))
							elementindex = 1;
						else
							elementindex = -1;
					}
					else if(tag.compareTo("traSpd") == 0) {
						tagnum = 20;
						if(tag.endsWith("1"))
							elementindex = 0;
						else if(tag.endsWith("2"))
							elementindex = 1;
						else
							elementindex = -1;
					}
					else if(tag.compareTo("traTime") == 0) {
						tagnum = 21;
						if(tag.endsWith("1"))
							elementindex = 0;
						else if(tag.endsWith("2"))
							elementindex = 1;
						else
							elementindex = -1;
					}
					else if(tag.compareTo("vehId") == 0) {
						tagnum = 22;
						if(tag.endsWith("1"))
							elementindex = 0;
						else if(tag.endsWith("2"))
							elementindex = 1;
						else
							elementindex = -1;
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
						Data.firstTm.add( parser.getText() );
						break;

					case 5:
						Data.lastTm.add( parser.getText() );
						break;

					case 6:
						Data.routeType.add( Integer.parseInt( parser.getText() ) );
						break;

					case 7:
						Data.rtNm.add( parser.getText() );
						break;

					case 8:
						Data.staOrd.add( Integer.parseInt( parser.getText() ) );
						break;

					case 9:
						Data.term.add( Integer.parseInt( parser.getText() ) );
						break;

					case 10:
						Data.arsId.add( Integer.parseInt( parser.getText() ) );
						break;

					case 11:
						Data.stID.add( Integer.parseInt( parser.getText() ) );
						break;

					case 12:
						Data.stNm.add( parser.getText() );
						break;

					///////////////////////// ElementItem //////////////////////////
					case 13:
						Data.ElementItem.get(elementindex).busType = Integer.parseInt( parser.getText() );
						break;
						
					case 14:
						Data.ElementItem.get(elementindex).isArrive = Integer.parseInt( parser.getText() );
						break;
						
					case 15:
						Data.ElementItem.get(elementindex).isLast = Integer.parseInt( parser.getText() );
						break;
						
					case 16:
						Data.ElementItem.get(elementindex).plainNo = parser.getText();
						break;
						
					case 17:
						Data.ElementItem.get(elementindex).repTm = parser.getText();
						break;
						
					case 18:
						Data.ElementItem.get(elementindex).sectOrd = Integer.parseInt( parser.getText() );
						break;
						
					case 19:
						Data.ElementItem.get(elementindex).stationNm = parser.getText();
						break;
						
					case 20:
						Data.ElementItem.get(elementindex).traSpd = Integer.parseInt( parser.getText() );
						break;
						
					case 21:
						Data.ElementItem.get(elementindex).traTime = Integer.parseInt( parser.getText() );
						break;
						
					case 22:
						Data.ElementItem.get(elementindex).vehId = Integer.parseInt( parser.getText() );
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
