package openAPI.BusPos;

import java.util.ArrayList;

public class getBusPosByRtidList_Element {
	
	// Header Data
	public int headerCd;				// 에러 코드
	public String headerMsg;			// 에러 코드 명
	public int itemCount;				// Item 갯수
	// Item Data
	public ArrayList<Integer> busType;
	public ArrayList<String> dataTm;
	public ArrayList<Double> fullSectDist;
	public ArrayList<Double> gpsX;
	public ArrayList<Double> gpsY;
	public ArrayList<Integer> isrunyn;
	public ArrayList<Integer> lastStTm;
	public ArrayList<Integer> lastStnId;
	public ArrayList<Integer> lastStnOrd;
	public ArrayList<Integer> lstbusyn;
	public ArrayList<Integer> nextStTm;
	public ArrayList<String> plainNo;
	public ArrayList<Double> rtDist;
	public ArrayList<Integer> sectDist;
	public ArrayList<Integer> sectOrd;
	public ArrayList<Integer> sectionId;
	public ArrayList<Integer> stopFlag;
	public ArrayList<Integer> trnstnid;
	public ArrayList<Integer> vehId;
	
	public getBusPosByRtidList_Element() {

		// Header Data
		headerCd = 0;
		headerMsg = "";
		itemCount = 0;
		// Item Data
		busType = new ArrayList<Integer>();
		dataTm = new ArrayList<String>();
		fullSectDist = new ArrayList<Double>();
		gpsX = new ArrayList<Double>();
		gpsY = new ArrayList<Double>();
		isrunyn = new ArrayList<Integer>();
		lastStTm = new ArrayList<Integer>();
		lastStnId = new ArrayList<Integer>();
		lastStnOrd = new ArrayList<Integer>();
		lstbusyn = new ArrayList<Integer>();
		nextStTm = new ArrayList<Integer>();
		plainNo = new ArrayList<String>();
		rtDist = new ArrayList<Double>();
		sectDist = new ArrayList<Integer>();
		sectOrd = new ArrayList<Integer>();
		sectionId = new ArrayList<Integer>();
		stopFlag = new ArrayList<Integer>();
		trnstnid = new ArrayList<Integer>();
		vehId = new ArrayList<Integer>();
		
	}
	
}
