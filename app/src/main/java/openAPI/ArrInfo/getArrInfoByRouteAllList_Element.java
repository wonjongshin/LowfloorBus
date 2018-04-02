package openAPI.ArrInfo;

import java.util.ArrayList;

public class getArrInfoByRouteAllList_Element {
	
	// Header Data
	public int headerCd;				// 에러 코드
	public String headerMsg;			// 에러 코드 명
	public int itemCount;				// Item 갯수
	// Item Data
	public ArrayList<Integer> arsId;
	public ArrayList<Integer> busRouteId;
	public ArrayList<String> dir;
	public ArrayList<String> firstTm;
	public ArrayList<String> lastTm;
	public ArrayList<String> mkTm;
	public ArrayList<Integer> nextBus;
	public ArrayList<Integer> routeType;
	public ArrayList<String> rtNm;
	public ArrayList<Integer> stId;
	public ArrayList<String> stNm;
	public ArrayList<Integer> staOrd;
	public ArrayList<Integer> term;

	public int ElementItemCount;
	public ArrayList<getLowArrInfoByStIdList_ElementItem> ElementItem;

	public getArrInfoByRouteAllList_Element() {

		// Header Data
		headerCd = 0;
		headerMsg = "";
		itemCount = 0;
		// Item Data
		arsId = new ArrayList<Integer>();
		busRouteId = new ArrayList<Integer>();
		dir = new ArrayList<String>();
		firstTm = new ArrayList<String>();
		lastTm = new ArrayList<String>();
		mkTm = new ArrayList<String>();;
		nextBus = new ArrayList<Integer>();
		routeType = new ArrayList<Integer>();
		rtNm = new ArrayList<String>();
		stId = new ArrayList<Integer>();
		stNm = new ArrayList<String>();
		staOrd = new ArrayList<Integer>();
		term = new ArrayList<Integer>();

		ElementItemCount = 0;
		ElementItem = null;
		
	}

	public void addElementItem() {
		if(ElementItem == null) {
			// getLowStationByUidList에서 호출 함
			ElementItem = new ArrayList<getLowArrInfoByStIdList_ElementItem>();
		}
		else {
			ElementItem.add(new getLowArrInfoByStIdList_ElementItem());
		}
		ElementItemCount++;
		
	}
	
}
