package openAPI.StationInfo;

import java.util.ArrayList;

public class getRouteByStationList_Element {
	
	// Header Data
	public int headerCd;				// 에러 코드
	public String headerMsg;			// 에러 코드 명
	public int itemCount;				// Item 갯수
	// Item Data
	public ArrayList<Integer> busRouteId;
	public ArrayList<String> busRouteNm;
	public ArrayList<Integer> busRouteType;
	public ArrayList<Double> length;
	public ArrayList<Integer> nextBus;
	public ArrayList<String> stBegin;
	public ArrayList<String> stEnd;
	public ArrayList<Integer> term;

	public getRouteByStationList_Element() {

		// Header Data
		headerCd = 0;
		headerMsg = "";
		itemCount = 0;
		// Item Data
		busRouteId = new ArrayList<Integer>();
		busRouteNm = new ArrayList<String>();
		busRouteType = new ArrayList<Integer>();
		length = new ArrayList<Double>();
		nextBus = new ArrayList<Integer>();
		stBegin = new ArrayList<String>();
		stEnd = new ArrayList<String>();
		term  = new ArrayList<Integer>();
		
	}
	
}
