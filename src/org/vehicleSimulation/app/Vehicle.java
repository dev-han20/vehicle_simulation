package org.vehicleSimulation.app;

public class Vehicle {

	private String id;		        // ID
	private String name;	        // 이름
	private String shape;			// 모양 (원, 정사각형)
	private double initialNote;	    // 초기속도 (1노트 : 0.5144m/s)
	private double changeNote;	    // 속도증감량 (1노트 : 0.5144m/s)
	private double width;	        // 영역의 가로길이
	private double height;	        // 영역의 세로길이
	private double initialDegree;	// 최초 방향각 
}
