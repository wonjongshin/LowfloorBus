package openAPI.StationInfo;

import java.util.ArrayList;

public class getStationByNameList_Element {
	
	// Header Data
	public int headerCd;				// 에러 코드
	public String headerMsg;			// 에러 코드 명
	public int itemCount;				// Item 갯수
	// Item Data
	public ArrayList<Integer> arsId;
	public ArrayList<Integer> stId;
	public ArrayList<String> stNm;
	public ArrayList<Double> tmX;
	public ArrayList<Double> tmY;

	public getStationByNameList_Element() {

		// Header Data
		headerCd = 0;
		headerMsg = "";
		itemCount = 0;
		// Item Data
		arsId = new ArrayList<Integer>();
		stId = new ArrayList<Integer>();
		stNm = new ArrayList<String>();
		tmX = new ArrayList<Double>();
		tmY = new ArrayList<Double>();
		
	}
	
}
