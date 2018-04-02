package openAPI.BusPos;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import openAPI.BusXMLparser.BusXMLPullParserFactory;

import org.xmlpull.v1.XmlPullParser;

import android.os.Handler;

/* 노선ID로 차량들의 위치정보를 조회한다.
 * 노선ID(busRouteId) 를 입력 받는다.  */
public class getBusPosByRtidList extends Thread {
	Handler mhandler;
	int mwhat;
	int count;

	String addr = "http://ws.bus.go.kr/api/rest/buspos/";
	String function = "getBusPosByRtid";
	String servicekey = YOUR_OPENAPI_KEY_HERE;

	String fullparam = "";
	String param1 = "&busRouteId=";

	getBusPosByRtidList_Element Data;

	public getBusPosByRtidList(Handler phandler, int pwhat) {
		mhandler = phandler;
		mwhat = pwhat;
		
		setParam("");
		
	}
	
	public getBusPosByRtidList(Handler phandler, int pwhat, String parameter1) {
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
    	Data = new getBusPosByRtidList_Element();
		request(addr, function, servicekey, fullparam);
		
		mhandler.sendEmptyMessage(mwhat);
		
	}
	
	
	public getBusPosByRtidList_Element getElement() {
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
	public double getfullSectDist(int index) {
		return Data.fullSectDist.get(index);
		
	}
	public double getgpsX(int index) {
		return Data.gpsX.get(index);
		
	}
	public double getgpsY(int index) {
		return Data.gpsY.get(index);
		
	}
	public int getisrunyn(int index) {
		return Data.isrunyn.get(index);
		
	}
	public int getlastStTm(int index) {
		return Data.lastStTm.get(index);
		
	}
	public int getlastStnId(int index) {
		return Data.lastStnId.get(index);
		
	}
	public int getlastStnOrd(int index) {
		return Data.lastStnOrd.get(index);
		
	}
	public int getlstbusyn(int index) {
		return Data.lstbusyn.get(index);
		
	}
	public int getnextStTm(int index) {
		return Data.nextStTm.get(index);
		
	}
	public String getplainNo(int index) {
		return Data.plainNo.get(index);
		
	}
	public double getrtDist(int index) {
		return Data.rtDist.get(index);
		
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
	public int gettrnstnid(int index) {
		return Data.trnstnid.get(index);
		
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
					else if(tag.equals("fullSectDist")) {
						tagnum = 5;
					}
					else if(tag.equals("gpsX")) {
						tagnum = 6;
					}
					else if(tag.equals("gpsY")) {
						tagnum = 7;
					}
					else if(tag.equals("isrunyn")) {
						tagnum = 8;
					}
					else if(tag.equals("lastStTm")) {
						tagnum = 9;
					}
					else if(tag.equals("lastStnId")) {
						tagnum = 10;
					}
					else if(tag.equals("lastStnOrd")) {
						tagnum = 11;
					}
					else if(tag.equals("lstbusyn")) {
						tagnum = 12;
					}
					else if(tag.equals("nextStTm")) {
						tagnum = 13;
					}
					else if(tag.equals("plainNo")) {
						tagnum = 14;
					}
					else if(tag.equals("rtDist")) {
						tagnum = 15;
					}
					else if(tag.equals("sectDist")) {
						tagnum = 16;
					}
					else if(tag.equals("sectOrd")) {
						tagnum = 17;
					}
					else if(tag.equals("sectionId")) {
						tagnum = 18;
					}
					else if(tag.equals("stopFlag")) {
						tagnum = 19;
					}
					else if(tag.equals("trnstnid")) {
						tagnum = 20;
					}
					else if(tag.equals("vehId")) {
						tagnum = 21;
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
						Data.fullSectDist.add( Double.parseDouble( parser.getText() ) );
						break;
						
					case 6:
						Data.gpsX.add( Double.parseDouble( parser.getText() ) );
						break;
						
					case 7:
						Data.gpsY.add( Double.parseDouble( parser.getText() ) );
						break;
						
					case 8:
						Data.isrunyn.add( Integer.parseInt( parser.getText() ) );
						break;
						
					case 9:
						Data.lastStTm.add( Integer.parseInt( parser.getText() ) );
						break;
						
					case 10:
						Data.lastStnId.add( Integer.parseInt( parser.getText() ) );
						break;
						
					case 11:
						Data.lastStnOrd.add( Integer.parseInt( parser.getText() ) );
						break;
						
					case 12:
						Data.lstbusyn.add( Integer.parseInt( parser.getText() ) );
						break;
						
					case 13:
						Data.nextStTm.add( Integer.parseInt( parser.getText() ) );
						break;
						
					case 14:
						Data.plainNo.add( parser.getText() );
						break;
						
					case 15:
						Data.rtDist.add( Double.parseDouble( parser.getText() ) );
						break;
						
					case 16:
						Data.sectDist.add( Integer.parseInt( parser.getText() ) );
						break;
						
					case 17:
						Data.sectOrd.add( Integer.parseInt( parser.getText() ) );
						break;
						
					case 18:
						Data.sectionId.add( Integer.parseInt( parser.getText() ) );
						break;
						
					case 19:
						Data.stopFlag.add( Integer.parseInt( parser.getText() ) );
						break;
						
					case 20:
						Data.trnstnid.add( Integer.parseInt( parser.getText() ) );
						break;
						
					case 21:
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
