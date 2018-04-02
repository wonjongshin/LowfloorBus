package openAPI.StationInfo;

import java.util.ArrayList;

public class getBustimeByStationList_Element {
	
	// Header Data
	public int headerCd;				// 에러 코드
	public String headerMsg;			// 에러 코드 명
	public int itemCount;				// Item 갯수
	// Item Data
	public ArrayList<Integer> arsId;
	public ArrayList<Integer> busRouteId;
	public ArrayList<String> busRouteNm;
	public ArrayList<String> firstBusTm;
	public ArrayList<String> lastBusTm;
	public ArrayList<String> stationNm;

	public getBustimeByStationList_Element() {

		// Header Data
		headerCd = 0;
		headerMsg = "";
		itemCount = 0;
		// Item Data
		arsId = new ArrayList<Integer>();
		busRouteId = new ArrayList<Integer>();
		busRouteNm = new ArrayList<String>();
		firstBusTm = new ArrayList<String>();
		lastBusTm = new ArrayList<String>();
		stationNm = new ArrayList<String>();
		
	}
	
}
