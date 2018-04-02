package openAPI.busRouteInfo;

import java.util.ArrayList;

public class getRoutePathList_Element {
	
	// Header Data
	public int headerCd;				// 에러 코드
	public String headerMsg;			// 에러 코드 명
	public int itemCount;				// Item 갯수
	// Item Data
	public ArrayList<Double> gpsX;	// 좌표X (WGS 84)
	public ArrayList<Double> gpsY;	// 좌표Y (WGS 84)
	public ArrayList<Integer> no;		// 순번
	
	public getRoutePathList_Element() {

		// Header Data
		headerCd = 0;
		headerMsg = "";
		itemCount = 0;
		// Item Data
		gpsX = new ArrayList<Double>();	// 좌표X (WGS 84)
		gpsY = new ArrayList<Double>();	// 좌표Y (WGS 84)
		no = new ArrayList<Integer>();		// 순번
		
	}
	
}
