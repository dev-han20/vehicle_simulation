package org.vehicleSimulation.app;

public class Vehicle {

	private String id;		        // ID
	private String name;	        // 이름
	private String shape;			// 모양 (원, 정사각형)
	private double simulationTime;	// 임무 시간
	private double initialNote;	    // 초기속도 (1노트 : 0.5144m/s)
	private double changeNote;	    // 속도 변화량 (1노트 : 0.5144m/s)
	private double width;	        // 전체 영역의 가로길이
	private double height;	        // 전체 영역의 세로길이
	private double initialDegree;	// 초기 각도
	private double changeDegree;	// 각도 변화량
	private double startX;			// 시작 x좌표
	private double startY;			// 시작 y좌표
	
	
	public double getSimulationTime() {
		return simulationTime;
	}
	public void setSimulationTime(double simulationTime) {
		this.simulationTime = simulationTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShape() {
		return shape;
	}
	public void setShape(String shape) {
		this.shape = shape;
	}
	public double getInitialNote() {
		return initialNote;
	}
	public void setInitialNote(double initialNote) {
		this.initialNote = initialNote;
	}
	public double getChangeNote() {
		return changeNote;
	}
	public void setChangeNote(double changeNote) {
		this.changeNote = changeNote;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getInitialDegree() {
		return initialDegree;
	}
	public void setInitialDegree(double initialDegree) {
		this.initialDegree = initialDegree;
	}
	public double getChangeDegree() {
		return changeDegree;
	}
	public void setChangeDegree(double changeDegree) {
		this.changeDegree = changeDegree;
	}
	public double getStartX() {
		return startX;
	}
	public void setStartX(double startX) {
		this.startX = startX;
	}
	public double getStartY() {
		return startY;
	}
	public void setStartY(double startY) {
		this.startY = startY;
	}
	
	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", name=" + name + ", shape=" + shape + ", initialNote=" + initialNote
				+ ", changeNote=" + changeNote + ", width=" + width + ", height=" + height + ", initialDegree="
				+ initialDegree + ", changeDegree=" + changeDegree + ", startX=" + startX + ", startY=" + startY + "]";
	}
	
	
	
}
