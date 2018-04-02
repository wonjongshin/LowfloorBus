package openAPI.BusPos;

import java.util.ArrayList;

public class getBusPosByRouteStList_Element {
	
	// Header Data
	public int headerCd;				// 에러 코드
	public String headerMsg;			// 에러 코드 명
	public int itemCount;				// Item 갯수
	// Item Data
	public ArrayList<Integer> busType;
	public ArrayList<String> dataTm;
	public ArrayList<Integer> lastStnId;
	public ArrayList<String> plainNo;
	public ArrayList<Integer> routeId;
	public ArrayList<Integer> sectDist;
	public ArrayList<Integer> sectOrd;
	public ArrayList<Integer> sectionId;
	public ArrayList<Integer> stopFlag;
	public ArrayList<Double> tmX;
	public ArrayList<Double> tmY;
	public ArrayList<Integer> vehId;
	
	public getBusPosByRouteStList_Element() {

		// Header Data
		headerCd = 0;
		headerMsg = "";
		itemCount = 0;
		// Item Data
		busType = new ArrayList<Integer>();
		dataTm = new ArrayList<String>();
		lastStnId = new ArrayList<Integer>();
		plainNo = new ArrayList<String>();
		routeId = new ArrayList<Integer>();
		sectDist = new ArrayList<Integer>();
		sectOrd = new ArrayList<Integer>();
		sectionId = new ArrayList<Integer>();
		stopFlag = new ArrayList<Integer>();
		tmX = new ArrayList<Double>();
		tmY = new ArrayList<Double>();
		vehId = new ArrayList<Integer>();
		
	}
	
}
