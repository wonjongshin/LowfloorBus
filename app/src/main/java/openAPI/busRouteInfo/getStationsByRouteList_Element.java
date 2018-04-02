package openAPI.busRouteInfo;

import java.util.ArrayList;

public class getStationsByRouteList_Element {
	
	// Header Data
	public int headerCd;				// 에러 코드
	public String headerMsg;			// 에러 코드 명
	public int itemCount;				// Item 갯수
	// Item Data
	public ArrayList<String> beginTm;		// 첫차 시간
	public ArrayList<Integer> busRouteId;	// 노선 ID
	public ArrayList<String> busRouteNm;	// 노선명
	public ArrayList<String> direction;		// 진행방향
	public ArrayList<Double> gpsX;			// X좌표 (WGS 84)
	public ArrayList<Double> gpsY;			// Y좌표 (WGS 84)
	public ArrayList<String> lastTm;		// 막차 시간
	public ArrayList<Integer> routeType;	// 노선 유형
	public ArrayList<Integer> section;		// 구간 ID
	public ArrayList<Integer> seq;			// 순번
	public ArrayList<Integer> station;		// 정류소 ID
	public ArrayList<String> stationNm;		// 정류소 이름
	public ArrayList<Integer> stationNo;	// 정류소 고유번호
	public ArrayList<Double> fullSectDist;	// 정류소간 거리
	public ArrayList<Integer> trnstnid;		// 회차지 정류소ID
	
	public getStationsByRouteList_Element() {

		// Header Data
		headerCd = 0;
		headerMsg = "";
		itemCount = 0;
		// Item Data
		beginTm = new ArrayList<String>();		// 첫차 시간
		busRouteId = new ArrayList<Integer>();	// 노선 ID
		busRouteNm = new ArrayList<String>();	// 노선명
		direction = new ArrayList<String>();	// 진행방향
		gpsX = new ArrayList<Double>();			// X좌표 (WGS 84)
		gpsY = new ArrayList<Double>();			// Y좌표 (WGS 84)
		lastTm = new ArrayList<String>();		// 막차 시간
		routeType = new ArrayList<Integer>();	// 노선 유형
		section = new ArrayList<Integer>();		// 구간 ID 
		seq = new ArrayList<Integer>();			// 순번
		station = new ArrayList<Integer>();		// 정류소 ID
		stationNm = new ArrayList<String>();	// 정류소 이름
		stationNo = new ArrayList<Integer>();	// 정류소 고유번호
		fullSectDist = new ArrayList<Double>();	// 정류소간 거리
		trnstnid = new ArrayList<Integer>();	// 회차지 정류소ID
		
	}
	
}
