package openAPI.busRouteInfo;

import java.util.ArrayList;

public class getbusRouteList_Element {
	
	// Header Data
	public int headerCd;				// 에러 코드
	public String headerMsg;			// 에러 코드 명
	public int itemCount;				// Item 갯수
	// Item Data
	public ArrayList<Integer> busRouteId;	// 노선 ID
	public ArrayList<String> busRouteNm;	// 노선명
	public ArrayList<String> edStationNm;	// 종점
	
	public ArrayList<String> firstBusTm;	// 금일첫차시간
	public ArrayList<String> firstLowTm;	// 금일저상첫차시간
	public ArrayList<String> lastBusTm;		// 금일막차시간
	public ArrayList<String> lastBusYn;		// 막차운행여부
	public ArrayList<String> lastLowTm;		// 금일저상막차시간 
	
	public ArrayList<Float> length;			// 노선 길이 (Km)
	public ArrayList<Integer> routeType;	// 노선 유형
	public ArrayList<String> stStationNm;	// 기점
	public ArrayList<Integer> term;			// 배차간격(분)
	
	public getbusRouteList_Element() {

		// Header Data
		headerCd = 0;
		headerMsg = "";
		itemCount = 0;
		// Item Data
		busRouteId = new ArrayList<Integer>();	// 노선 ID
		busRouteNm = new ArrayList<String>();	// 노선명
		edStationNm = new ArrayList<String>();	// 종점
		
		firstBusTm = new ArrayList<String>();	// 금일첫차시간
		firstLowTm = new ArrayList<String>();	// 금일저상첫차시간
		lastBusTm = new ArrayList<String>();	// 금일막차시간
		lastBusYn = new ArrayList<String>();	// 막차운행여부
		lastLowTm = new ArrayList<String>();	// 금일저상막차시간 
		
		length = new ArrayList<Float>();		// 노선 길이 (Km)
		routeType = new ArrayList<Integer>();	// 노선 유형
		stStationNm = new ArrayList<String>();	// 기점
		term = new ArrayList<Integer>();		// 배차간격(분)
		
	}
	
}
