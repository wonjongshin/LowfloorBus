package openAPI.StationInfo;

import java.util.ArrayList;

public class getLowStationByUidList_Element {
	
	// Header Data
	public int headerCd;				// 에러 코드
	public String headerMsg;			// 에러 코드 명
	public int itemCount;				// Item 갯수
	// Item Data
	public ArrayList<Integer> busRouteId;
	public ArrayList<String> firstTm;
	public ArrayList<String> lastTm;
	public ArrayList<Integer> routeType;
	public ArrayList<String> rtNm;
	public ArrayList<Integer> staOrd;
	public ArrayList<Integer> term;
	public ArrayList<Integer> arsId;
	public ArrayList<Integer> stID;
	public ArrayList<String> stNm;

	public int ElementItemCount;
	public ArrayList<getLowStationByUidList_ElementItem> ElementItem;

	public getLowStationByUidList_Element() {

		// Header Data
		headerCd = 0;
		headerMsg = "";
		itemCount = 0;
		// Item Data
		busRouteId = new ArrayList<Integer>();
		firstTm = new ArrayList<String>();
		lastTm = new ArrayList<String>();
		routeType = new ArrayList<Integer>();
		rtNm = new ArrayList<String>();
		staOrd = new ArrayList<Integer>();
		term = new ArrayList<Integer>();
		arsId = new ArrayList<Integer>();
		stID = new ArrayList<Integer>();
		stNm = new ArrayList<String>();
		
		ElementItemCount = 0;
		ElementItem = null;
		
	}

	public void addElementItem() {
		if(ElementItem == null) {
			// getLowStationByUidList에서 호출 함
			ElementItem = new ArrayList<getLowStationByUidList_ElementItem>();
		}
		else {
			ElementItem.add(new getLowStationByUidList_ElementItem());
		}
		ElementItemCount++;
		
	}
	
}
