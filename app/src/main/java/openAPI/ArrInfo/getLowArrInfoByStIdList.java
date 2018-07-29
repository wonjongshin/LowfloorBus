package openAPI.ArrInfo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import openAPI.BusXMLparser.BusXMLPullParserFactory;

import org.xmlpull.v1.XmlPullParser;

import android.os.Handler;

/* 정류소ID로 저상버스 도착예정정보를 조회한다.
 * 정류소ID(stId) 를 입력 받는다.  */
public class getLowArrInfoByStIdList extends Thread {
	Handler mhandler;
	int mwhat;
	int count;

	String addr = "http://ws.bus.go.kr/api/rest/arrive/";
	String function = "getLowArrInfoByStId";
	String servicekey;

	String fullparam = "";
	String param1 = "&stId=";

	getLowArrInfoByStIdList_Element Data;

	public getLowArrInfoByStIdList(Handler phandler, int pwhat) {
		mhandler = phandler;
		mwhat = pwhat;

		this.servicekey = "";
		setParam("");
		
	}
	
	public getLowArrInfoByStIdList(Handler phandler, int pwhat, String serviceKey, String parameter1) {
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
    	Data = new getLowArrInfoByStIdList_Element();
		request(addr, function, servicekey, fullparam);
		
		mhandler.sendEmptyMessage(mwhat);
		
	}
	
	
	public getLowArrInfoByStIdList_Element getElement() {
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
	public String getdir(int index) {
		return Data.dir.get(index);
		
	}
	public String getfirstTm(int index) {
		return Data.firstTm.get(index);
		
	}
	public String getlastTm(int index) {
		return Data.lastTm.get(index);
		
	}
	public String getmkTm(int index) {
		return Data.mkTm.get(index);
		
	}
	public int getnextBus(int index) {
		return Data.nextBus.get(index);
		
	}
	public int getrouteType(int index) {
		return Data.routeType.get(index);
		
	}
	public String getrtNm(int index) {
		return Data.rtNm.get(index);
		
	}
	public int getstId(int index) {
		return Data.stId.get(index);
		
	}
	public String getstNm(int index) {
		return Data.stNm.get(index);
		
	}
	public int getstaOrd(int index) {
		return Data.staOrd.get(index);
		
	}
	public int getterm(int index) {
		return Data.term.get(index);
		
	}
	
	///////////////////////// ElementItem //////////////////////////
	public int getElementItemCount() {
		return Data.ElementItemCount;
		
	}
	public getLowArrInfoByStIdList_ElementItem getElementItem(int index) {
		return Data.ElementItem.get(index);
		
	}
	public int getavgCf(int index) {
		return Data.ElementItem.get(index).avgCf;
		
	}
	public int getbrdrde_Num(int index) {
		return Data.ElementItem.get(index).brdrde_Num;
		
	}
	public int getbrerde_Div(int index) {
		return Data.ElementItem.get(index).brerde_Div;
		
	}
	public int getbusType(int index) {
		return Data.ElementItem.get(index).busType;
		
	}
	public int getexpCf(int index) {
		return Data.ElementItem.get(index).expCf;
		
	}
	public int getexps(int index) {
		return Data.ElementItem.get(index).exps;
		
	}
	public int getfull(int index) {
		return Data.ElementItem.get(index).full;
		
	}
	public int getgoal(int index) {
		return Data.ElementItem.get(index).goal;
		
	}
	public int getisArrive(int index) {
		return Data.ElementItem.get(index).isArrive;
		
	}
	public int getisLast(int index) {
		return Data.ElementItem.get(index).isLast;
		
	}
	public int getkalCf(int index) {
		return Data.ElementItem.get(index).kalCf;
		
	}
	public int getkals(int index) {
		return Data.ElementItem.get(index).kals;
		
	}
	public int getneuCf(int index) {
		return Data.ElementItem.get(index).neuCf;
		
	}
	public int getneus(int index) {
		return Data.ElementItem.get(index).neus;
		
	}
	public int getnmainOrd(int index) {
		return Data.ElementItem.get(index).nmainOrd;
		
	}
	public int getnmainSec(int index) {
		return Data.ElementItem.get(index).nmainSec;
		
	}
	public int getnmainStnid(int index) {
		return Data.ElementItem.get(index).nmainStnid;
		
	}
	public int getnmain2Ord(int index) {
		return Data.ElementItem.get(index).nmain2Ord;
		
	}
	public int getnamin2Sec(int index) {
		return Data.ElementItem.get(index).namin2Sec;
		
	}
	public int getnmain2Stnid(int index) {
		return Data.ElementItem.get(index).nmain2Stnid;
		
	}
	public int getnmain3Ord(int index) {
		return Data.ElementItem.get(index).nmain3Ord;
		
	}
	public int getnmain3Sec(int index) {
		return Data.ElementItem.get(index).nmain3Sec;
		
	}
	public int getnmain3Stnid(int index) {
		return Data.ElementItem.get(index).nmain3Stnid;
		
	}
	public int getnstnId(int index) {
		return Data.ElementItem.get(index).nstnId;
		
	}
	public int getnstnOrd(int index) {
		return Data.ElementItem.get(index).nstnOrd;
		
	}
	public int getnstnSec(int index) {
		return Data.ElementItem.get(index).nstnSec;
		
	}
	public int getnstnSpd(int index) {
		return Data.ElementItem.get(index).nstnSpd;
		
	}
	public String getplainNo(int index) {
		return Data.ElementItem.get(index).plainNo;
		
	}
	public int getrerdie_Div(int index) {
		return Data.ElementItem.get(index).rerdie_Div;
		
	}
	public int getreride_Num(int index) {
		return Data.ElementItem.get(index).reride_Num;
		
	}
	public int getsectOrd(int index) {
		return Data.ElementItem.get(index).sectOrd;
		
	}
	public String getstationNm(int index) {
		return Data.ElementItem.get(index).stationNm;
		
	}
	public int gettraSpd(int index) {
		return Data.ElementItem.get(index).traSpd;
		
	}
	public int gettraTime(int index) {
		return Data.ElementItem.get(index).traTime;
		
	}
	public int getvehId(int index) {
		return Data.ElementItem.get(index).vehId;
		
	}
	public String getrepTm(int index) {
		return Data.ElementItem.get(index).repTm;
		
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
					
					else if(tag.equals("arsId")) {
						tagnum = 3;
					}
					else if(tag.equals("busRouteId")) {
						tagnum = 4;
					}
					else if(tag.equals("dir")) {
						tagnum = 5;
					}
					else if(tag.equals("firstTm")) {
						tagnum = 6;
					}
					else if(tag.equals("lastTm")) {
						tagnum = 7;
					}
					else if(tag.equals("mkTm")) {
						tagnum = 8;
					}
					else if(tag.equals("nextBus")) {
						tagnum = 9;
					}
					else if(tag.equals("routeType")) {
						tagnum = 10;
					}
					else if(tag.equals("rtNm")) {
						tagnum = 11;
					}
					else if(tag.equals("stId")) {
						tagnum = 12;
					}
					else if(tag.equals("stNm")) {
						tagnum = 13;
					}
					else if(tag.equals("staOrd")) {
						tagnum = 14;
					}
					else if(tag.equals("term")) {
						tagnum = 15;
					}

					///////////////////////// ElementItem //////////////////////////
					else if(tag.startsWith("avgCf")) {
						// busType일 경우 새 객체 추가
						Data.addElementItem();
						tagnum = 16;
						if(tag.endsWith("1"))
							elementindex = 0;
						else if(tag.endsWith("2"))
							elementindex = 1;
						else
							elementindex = -1;
					}
					else if(tag.startsWith("brdrde_Num")) {
						tagnum = 17;
						if(tag.endsWith("1"))
							elementindex = 0;
						else if(tag.endsWith("2"))
							elementindex = 1;
						else
							elementindex = -1;
					}
					else if(tag.startsWith("brerde_Div")) {
						tagnum = 18;
						if(tag.endsWith("1"))
							elementindex = 0;
						else if(tag.endsWith("2"))
							elementindex = 1;
						else
							elementindex = -1;
					}
					else if(tag.startsWith("busType")) {
						tagnum = 19;
						if(tag.endsWith("1"))
							elementindex = 0;
						else if(tag.endsWith("2"))
							elementindex = 1;
						else
							elementindex = -1;
					}
					else if(tag.startsWith("expCf")) {
						tagnum = 20;
						if(tag.endsWith("1"))
							elementindex = 0;
						else if(tag.endsWith("2"))
							elementindex = 1;
						else
							elementindex = -1;
					}
					else if(tag.startsWith("exps")) {
						tagnum = 21;
						if(tag.endsWith("1"))
							elementindex = 0;
						else if(tag.endsWith("2"))
							elementindex = 1;
						else
							elementindex = -1;
					}
					else if(tag.startsWith("full")) {
						tagnum = 22;
						if(tag.endsWith("1"))
							elementindex = 0;
						else if(tag.endsWith("2"))
							elementindex = 1;
						else
							elementindex = -1;
					}
					else if(tag.startsWith("goal")) {
						tagnum = 23;
						if(tag.endsWith("1"))
							elementindex = 0;
						else if(tag.endsWith("2"))
							elementindex = 1;
						else
							elementindex = -1;
					}
					else if(tag.startsWith("isArrive")) {
						tagnum = 24;
						if(tag.endsWith("1"))
							elementindex = 0;
						else if(tag.endsWith("2"))
							elementindex = 1;
						else
							elementindex = -1;
					}
					else if(tag.startsWith("isLast")) {
						tagnum = 25;
						if(tag.endsWith("1"))
							elementindex = 0;
						else if(tag.endsWith("2"))
							elementindex = 1;
						else
							elementindex = -1;
					}
					else if(tag.startsWith("kalCf")) {
						tagnum = 26;
						if(tag.endsWith("1"))
							elementindex = 0;
						else if(tag.endsWith("2"))
							elementindex = 1;
						else
							elementindex = -1;
					}
					else if(tag.startsWith("kals")) {
						tagnum = 27;
						if(tag.endsWith("1"))
							elementindex = 0;
						else if(tag.endsWith("2"))
							elementindex = 1;
						else
							elementindex = -1;
					}
					else if(tag.startsWith("neuCf")) {
						tagnum = 28;
						if(tag.endsWith("1"))
							elementindex = 0;
						else if(tag.endsWith("2"))
							elementindex = 1;
						else
							elementindex = -1;
					}
					else if(tag.startsWith("neus")) {
						tagnum = 29;
						if(tag.endsWith("1"))
							elementindex = 0;
						else if(tag.endsWith("2"))
							elementindex = 1;
						else
							elementindex = -1;
					}
					else if(tag.startsWith("nmain")) {
						tagnum = -1;
						if(tag.charAt(5) == '2') {
							if(tag.startsWith("Ord", 6))
								tagnum = 33;
							// nmain은 namin으로 오타 나 있다. tagnum = 33
							else if(tag.startsWith("Stnid", 6))
								tagnum = 35;
						}
						else if(tag.charAt(5) == '3') {
							if(tag.startsWith("Ord", 6))
								tagnum = 36;
							else if(tag.startsWith("Sec", 6))
								tagnum = 37;
							else if(tag.startsWith("Stnid", 6))
								tagnum = 38;
						}
						else {	// 번호 없을 경우
							if(tag.startsWith("Ord", 5))
								tagnum = 30;
							else if(tag.startsWith("Sec", 5))
								tagnum = 31;
							else if(tag.startsWith("Stnid", 5))
								tagnum = 32;
						}
						
						if(tag.endsWith("1"))
							elementindex = 0;
						else if(tag.endsWith("2"))
							elementindex = 1;
						else
							elementindex = -1;
					}
					else if(tag.startsWith("namin")) {

						tagnum = -1;
						if(tag.charAt(5) == '2') {
							if(tag.startsWith("Sec", 6))
								tagnum = 34;
						}
					}
					else if(tag.startsWith("nstnId")) {
						tagnum = 39;
						if(tag.endsWith("1"))
							elementindex = 0;
						else if(tag.endsWith("2"))
							elementindex = 1;
						else
							elementindex = -1;
					}
					else if(tag.startsWith("nstnOrd")) {
						tagnum = 40;
						if(tag.endsWith("1"))
							elementindex = 0;
						else if(tag.endsWith("2"))
							elementindex = 1;
						else
							elementindex = -1;
					}
					else if(tag.startsWith("nstnSec")) {
						tagnum = 41;
						if(tag.endsWith("1"))
							elementindex = 0;
						else if(tag.endsWith("2"))
							elementindex = 1;
						else
							elementindex = -1;
					}
					else if(tag.startsWith("nstnSpd")) {
						tagnum = 42;
						if(tag.endsWith("1"))
							elementindex = 0;
						else if(tag.endsWith("2"))
							elementindex = 1;
						else
							elementindex = -1;
					}
					else if(tag.startsWith("plainNo")) {
						tagnum = 43;
						if(tag.endsWith("1"))
							elementindex = 0;
						else if(tag.endsWith("2"))
							elementindex = 1;
						else
							elementindex = -1;
					}
					else if(tag.startsWith("rerdie_Div")) {
						tagnum = 44;
						if(tag.endsWith("1"))
							elementindex = 0;
						else if(tag.endsWith("2"))
							elementindex = 1;
						else
							elementindex = -1;
					}
					else if(tag.startsWith("reride_Num")) {
						tagnum = 45;
						if(tag.endsWith("1"))
							elementindex = 0;
						else if(tag.endsWith("2"))
							elementindex = 1;
						else
							elementindex = -1;
					}
					else if(tag.startsWith("sectOrd")) {
						tagnum = 46;
						if(tag.endsWith("1"))
							elementindex = 0;
						else if(tag.endsWith("2"))
							elementindex = 1;
						else
							elementindex = -1;
					}
					else if(tag.startsWith("stationNm")) {
						tagnum = 47;
						if(tag.endsWith("1"))
							elementindex = 0;
						else if(tag.endsWith("2"))
							elementindex = 1;
						else
							elementindex = -1;
					}
					else if(tag.startsWith("traSpd")) {
						tagnum = 48;
						if(tag.endsWith("1"))
							elementindex = 0;
						else if(tag.endsWith("2"))
							elementindex = 1;
						else
							elementindex = -1;
					}
					else if(tag.startsWith("traTime")) {
						tagnum = 49;
						if(tag.endsWith("1"))
							elementindex = 0;
						else if(tag.endsWith("2"))
							elementindex = 1;
						else
							elementindex = -1;
					}
					else if(tag.startsWith("vehId")) {
						tagnum = 50;
						if(tag.endsWith("1"))
							elementindex = 0;
						else if(tag.endsWith("2"))
							elementindex = 1;
						else
							elementindex = -1;
					}
					else if(tag.startsWith("repTm")) {
						tagnum = 51;
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
						Data.arsId.add( Integer.parseInt( parser.getText() ) );
						break;
						
					case 4:
						Data.busRouteId.add( Integer.parseInt( parser.getText() ) );
						break;

					case 5:
						Data.dir.add( parser.getText() );
						break;
						
					case 6:
						Data.firstTm.add( parser.getText() );
						break;
						
					case 7:
						Data.lastTm.add( parser.getText() );
						break;
						
					case 8:
						Data.mkTm.add( parser.getText() );
						break;
						
					case 9:
						Data.nextBus.add( Integer.parseInt( parser.getText() ) );
						break;
						
					case 10:
						Data.routeType.add( Integer.parseInt( parser.getText() ) );
						break;
						
					case 11:
						Data.rtNm.add( parser.getText() );
						break;
						
					case 12:
						Data.stId.add( Integer.parseInt( parser.getText() ) );
						break;
						
					case 13:
						Data.stNm.add( parser.getText() );
						break;
						
					case 14:
						Data.staOrd.add( Integer.parseInt( parser.getText() ) );
						break;
						
					case 15:
						Data.term.add( Integer.parseInt( parser.getText() ) );
						break;

					///////////////////////// ElementItem //////////////////////////
					case 16:
						Data.ElementItem.get(elementindex).avgCf = Integer.parseInt( parser.getText() );
						break;

					case 17:
						Data.ElementItem.get(elementindex).brdrde_Num = Integer.parseInt( parser.getText() );
						break;

					case 18:
						Data.ElementItem.get(elementindex).brerde_Div = Integer.parseInt( parser.getText() );
						break;

					case 19:
						Data.ElementItem.get(elementindex).busType = Integer.parseInt( parser.getText() );
						break;

					case 20:
						Data.ElementItem.get(elementindex).expCf = Integer.parseInt( parser.getText() );
						break;

					case 21:
						Data.ElementItem.get(elementindex).exps = Integer.parseInt( parser.getText() );
						break;

					case 22:
						Data.ElementItem.get(elementindex).full = Integer.parseInt( parser.getText() );
						break;

					case 23:
						Data.ElementItem.get(elementindex).goal = Integer.parseInt( parser.getText() );
						break;

					case 24:
						Data.ElementItem.get(elementindex).isArrive = Integer.parseInt( parser.getText() );
						break;

					case 25:
						Data.ElementItem.get(elementindex).isLast = Integer.parseInt( parser.getText() );
						break;

					case 26:
						Data.ElementItem.get(elementindex).kalCf = Integer.parseInt( parser.getText() );
						break;

					case 27:
						Data.ElementItem.get(elementindex).kals = Integer.parseInt( parser.getText() );
						break;

					case 28:
						Data.ElementItem.get(elementindex).neuCf = Integer.parseInt( parser.getText() );
						break;

					case 29:
						Data.ElementItem.get(elementindex).neus = Integer.parseInt( parser.getText() );
						break;

					case 30:
						Data.ElementItem.get(elementindex).nmainOrd = Integer.parseInt( parser.getText() );
						break;
					case 31:
						Data.ElementItem.get(elementindex).nmainSec = Integer.parseInt( parser.getText() );
						break;
					case 32:
						Data.ElementItem.get(elementindex).nmainStnid = Integer.parseInt( parser.getText() );
						break;

					case 33:
						Data.ElementItem.get(elementindex).nmain2Ord = Integer.parseInt( parser.getText() );
						break;
					case 34:
						Data.ElementItem.get(elementindex).namin2Sec = Integer.parseInt( parser.getText() );
						break;
					case 35:
						Data.ElementItem.get(elementindex).nmain2Stnid = Integer.parseInt( parser.getText() );
						break;

					case 36:
						Data.ElementItem.get(elementindex).nmain3Ord = Integer.parseInt( parser.getText() );
						break;
					case 37:
						Data.ElementItem.get(elementindex).nmain3Sec = Integer.parseInt( parser.getText() );
						break;
					case 38:
						Data.ElementItem.get(elementindex).nmain3Stnid = Integer.parseInt( parser.getText() );
						break;

					case 39:
						Data.ElementItem.get(elementindex).nstnId = Integer.parseInt( parser.getText() );
						break;

					case 40:
						Data.ElementItem.get(elementindex).nstnOrd = Integer.parseInt( parser.getText() );
						break;

					case 41:
						Data.ElementItem.get(elementindex).nstnSec = Integer.parseInt( parser.getText() );
						break;

					case 42:
						Data.ElementItem.get(elementindex).nstnSpd = Integer.parseInt( parser.getText() );
						break;

					case 43:
						Data.ElementItem.get(elementindex).plainNo = parser.getText();
						break;

					case 44:
						Data.ElementItem.get(elementindex).rerdie_Div = Integer.parseInt( parser.getText() );
						break;

					case 45:
						Data.ElementItem.get(elementindex).reride_Num = Integer.parseInt( parser.getText() );
						break;

					case 46:
						Data.ElementItem.get(elementindex).sectOrd = Integer.parseInt( parser.getText() );
						break;

					case 47:
						Data.ElementItem.get(elementindex).stationNm = parser.getText();
						break;

					case 48:
						Data.ElementItem.get(elementindex).traSpd = Integer.parseInt( parser.getText() );
						break;

					case 49:
						Data.ElementItem.get(elementindex).traTime = Integer.parseInt( parser.getText() );
						break;

					case 50:
						Data.ElementItem.get(elementindex).vehId = Integer.parseInt( parser.getText() );
						break;

					case 51:
						Data.ElementItem.get(elementindex).repTm = parser.getText();
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
