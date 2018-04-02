package openAPI.BusPos;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import openAPI.BusXMLparser.BusXMLPullParserFactory;

import org.xmlpull.v1.XmlPullParser;

import android.os.Handler;

/* 한 정류소의 특정노선의 도착예정정보를 조회한다.
 * 노선ID(busRouteId) 정류소ID(stId) 정수로순번(ord) 를 입력 받는다.  */
public class getLowBusPosByRouteStList extends Thread {
	Handler mhandler;
	int mwhat;
	int count;

	String addr = "http://ws.bus.go.kr/api/rest/buspos/";
	String function = "getLowBusPosByRouteSt";
	String servicekey = YOUR_OPENAPI_KEY_HERE;

	String fullparam = "";
	String param1 = "&busRouteId=";
	String param2 = "&startOrd=";
	String param3 = "&endOrd=";

	getLowBusPosByRouteStList_Element Data;

	public getLowBusPosByRouteStList(Handler phandler, int pwhat) {
		mhandler = phandler;
		mwhat = pwhat;
		
		setParam("", "", "");
		
	}
	
	public getLowBusPosByRouteStList(Handler phandler, int pwhat, String parameter1, String parameter2, String parameter3) {
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
    	Data = new getLowBusPosByRouteStList_Element();
		request(addr, function, servicekey, fullparam);
		
		mhandler.sendEmptyMessage(mwhat);
		
	}
	
	
	public getLowBusPosByRouteStList_Element getElement() {
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
	
	public int getbusType(int index) {
		return Data.busType.get(index);
		
	}
	public String getdataTm(int index) {
		return Data.dataTm.get(index);
		
	}
	public int getlastStnId(int index) {
		return Data.lastStnId.get(index);
		
	}
	public String getplainNo(int index) {
		return Data.plainNo.get(index);
		
	}
	public int getsectDist(int index) {
		return Data.sectDist.get(index);
		
	}
	public int getsectOrd(int index) {
		return Data.sectOrd.get(index);
		
	}
	public int getsectionId(int index) {
		return Data.sectionId.get(index);
		
	}
	public int getstopFlag(int index) {
		return Data.stopFlag.get(index);
		
	}
	public double gettmX(int index) {
		return Data.tmX.get(index);
		
	}
	public double gettmY(int index) {
		return Data.tmY.get(index);
		
	}
	public int getvehId(int index) {
		return Data.vehId.get(index);
		
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
					
					else if(tag.equals("busType")) {
						tagnum = 3;
					}
					else if(tag.equals("dataTm")) {
						tagnum = 4;
					}
					else if(tag.equals("lastStnId")) {
						tagnum = 5;
					}
					else if(tag.equals("plainNo")) {
						tagnum = 6;
					}
					else if(tag.equals("sectDist")) {
						tagnum = 7;
					}
					else if(tag.equals("sectOrd")) {
						tagnum = 8;
					}
					else if(tag.equals("sectionId")) {
						tagnum = 9;
					}
					else if(tag.equals("stopFlag")) {
						tagnum = 10;
					}
					else if(tag.equals("tmX")) {
						tagnum = 11;
					}
					else if(tag.equals("tmY")) {
						tagnum = 12;
					}
					else if(tag.equals("vehId")) {
						tagnum = 13;
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
						Data.busType.add( Integer.parseInt( parser.getText() ) );
						break;
						
					case 4:
						Data.dataTm.add( parser.getText() );
						break;
						
					case 5:
						Data.lastStnId.add( Integer.parseInt( parser.getText() ) );
						break;
						
					case 6:
						Data.plainNo.add( parser.getText() );
						break;
						
					case 7:
						Data.sectDist.add( Integer.parseInt( parser.getText() ) );
						break;
						
					case 8:
						Data.sectOrd.add( Integer.parseInt( parser.getText() ) );
						break;
						
					case 9:
						Data.sectionId.add( Integer.parseInt( parser.getText() ) );
						break;
						
					case 10:
						Data.stopFlag.add( Integer.parseInt( parser.getText() ) );
						break;
						
					case 11:
						Data.tmX.add( Double.parseDouble( parser.getText() ) );
						break;
						
					case 12:
						Data.tmY.add( Double.parseDouble( parser.getText() ) );
						break;
						
					case 13:
						Data.vehId.add( Integer.parseInt( parser.getText() ) );
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
