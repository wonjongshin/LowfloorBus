package openAPI.StationInfo;

import java.util.ArrayList;

public class getStationByUidItem_Element {
	
	// Header Data
	public int headerCd;				// 에러 코드
	public String headerMsg;			// 에러 코드 명
	public int itemCount;				// Item 갯수
	// Item Data
	public ArrayList<Integer> arsId;
	public ArrayList<Integer> busRouteId;
	public ArrayList<String> firstTm;
	public ArrayList<String> lastTm;
	public ArrayList<Double> gpsX;
	public ArrayList<Double> gpsY;
	public ArrayList<Integer> routeType;
	public ArrayList<String> rtNm;
	public ArrayList<Integer> stId;
	public ArrayList<String> stNm;
	public ArrayList<Integer> staOrd;
	public ArrayList<Integer> stationTp;
	public ArrayList<Integer> term;

	public int ElementItemCount;
	public ArrayList<getStationByUidItem_ElementItem> ElementItem;

	public getStationByUidItem_Element() {

		// Header Data
		headerCd = 0;
		headerMsg = "";
		itemCount = 0;
		// Item Data
		arsId = new ArrayList<Integer>();
		busRouteId = new ArrayList<Integer>();
		firstTm = new ArrayList<String>();
		lastTm = new ArrayList<String>();
		gpsX = new ArrayList<Double>();
		gpsY = new ArrayList<Double>();
		routeType = new ArrayList<Integer>();
		rtNm = new ArrayList<String>();
		stId = new ArrayList<Integer>();
		stNm = new ArrayList<String>();
		staOrd = new ArrayList<Integer>();
		stationTp = new ArrayList<Integer>();
		term = new ArrayList<Integer>();

		ElementItemCount = 0;
		ElementItem = null;
		
	}

	public void addElementItem() {
		if(ElementItem == null) {
			// getLowStationByUidList에서 호출 함
			ElementItem = new ArrayList<getStationByUidItem_ElementItem>();
		}
		else {
			ElementItem.add(new getStationByUidItem_ElementItem());
		}
		ElementItemCount++;
		
	}
	
}
