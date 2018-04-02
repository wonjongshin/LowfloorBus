package openAPI.PathInfo;

import java.util.ArrayList;

public class getLocationInfoList_Element {
	
	// Header Data
	public int headerCd;				// 에러 코드
	public String headerMsg;			// 에러 코드 명
	public int itemCount;				// Item 갯수
	// Item Data
	public ArrayList<Double> gpsX;		// X좌표
	public ArrayList<Double> gpsY;		// Y좌표
	public ArrayList<Integer> poiId;	// POI ID
	public ArrayList<String> poiNm;		// POI 이름
	
	public getLocationInfoList_Element() {

		// Header Data
		headerCd = 0;
		headerMsg = "";
		itemCount = 0;
		// Item Data
		gpsX = new ArrayList<Double>();		// X좌표 
		gpsY = new ArrayList<Double>();		// Y좌표
		poiId = new ArrayList<Integer>();	// POI ID
		poiNm = new ArrayList<String>();	// POI 이름
		
	}
	
}
