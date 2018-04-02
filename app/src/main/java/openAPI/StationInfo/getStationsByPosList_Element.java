package openAPI.StationInfo;

import java.util.ArrayList;

public class getStationsByPosList_Element {
	
	// Header Data
	public int headerCd;				// 에러 코드
	public String headerMsg;			// 에러 코드 명
	public int itemCount;				// Item 갯수
	// Item Data
	public ArrayList<Integer> arsId;
	public ArrayList<Double> gpsX;
	public ArrayList<Double> gpsY;
	public ArrayList<Integer> stationId;
	public ArrayList<String> stationNm;
	public ArrayList<Integer> stationTp;
	
	public getStationsByPosList_Element() {

		// Header Data
		headerCd = 0;
		headerMsg = "";
		itemCount = 0;
		// Item Data
		arsId = new ArrayList<Integer>();
		gpsX = new ArrayList<Double>();
		gpsY = new ArrayList<Double>();
		stationId = new ArrayList<Integer>();
		stationNm = new ArrayList<String>();
		stationTp = new ArrayList<Integer>();
		
	}
	
}
