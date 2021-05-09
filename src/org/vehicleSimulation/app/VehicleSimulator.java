package org.vehicleSimulation.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VehicleSimulator {

	// 이동체정보 저장 할 리스트
	static List<Vehicle> vehicleList = new ArrayList<Vehicle>();  
	
	public static void main(String[] args) {
		System.out.println("###");
		
		// 이동체 데이터 받기
		inputData();
		
	}

	
	private static void inputData() {
		
		Scanner sc = new Scanner(System.in);
		
		//입력 값 검증체크 변수
		boolean repeatChk = false;
		
		int vCount = 0;
		do {// 이동체 수량 받기
			repeatChk = false;
			System.out.print("이동체의 수량을 입력해주십시오 : ");
			 vCount = sc.nextInt();
			if(vCount <= 0 || vCount > 4) {
				System.out.println("error : 이동체의 수량은 1대 이상, 4대 이하만 가능합니다.");
				System.out.println();
				repeatChk = true;
			} 
		}while(repeatChk);
		
		
		for(int i=1; i<=vCount; i++) {
			System.out.println();
			System.out.println(i+"번 이동체 정보입력을 시작하겠습니다...");
			Vehicle vehicle = new Vehicle();
			
			do {// 이동체 이름 받기
				System.out.print(i+"번 이동체의 이름을 입력하시오 : ");
				repeatChk = false;
				String name = sc.next();
				for (Vehicle vc : vehicleList) {
					if(vc.getName().equals(name)) {
						repeatChk = true;
					}
				}
				if(repeatChk) {
					System.out.println("error : 이름이 중복됩니다. 다시 입력해주십시오.");
					System.out.println();
				}else {
					vehicle.setName(name);	
				}
			}while(repeatChk);
			
			do {// 이동체 ID 받기
				System.out.print(i+"번 이동체의 ID를 입력하시오 : ");
				repeatChk = false;
				String id = sc.next();
				for (Vehicle vc : vehicleList) {
					if(vc.getId().equals(id)) {
						repeatChk = true;
					}
				}
				if(repeatChk) {
					System.out.println("error : ID가 중복됩니다. 다시 입력해주십시오.");
					System.out.println();
				}else{
					vehicle.setId(id);
				}
			}while(repeatChk);
			
			do {// 이동체 외형 받기
				System.out.print(i+"번 이동체의 외형 type을 선택하십시오(원, 정사각형) : ");
				repeatChk = false;
				String shape = sc.next();
				if(!(shape.equals("원") || shape.equals("정사각형"))) {
					repeatChk = true;
					System.out.println("error : 원 또는 정사각형만 입력 가능합니다. 다시 입력해주십시오.");
					System.out.println();
				}else {
					vehicle.setShape(shape);
				}
			}while(repeatChk);
			
			do {// 이동체 최초 방향각 입력
				System.out.print(i+"번 이동체의 최초 방향각을 입력하십시오(0도 ~ 90도) : ");
				repeatChk = false;
				double initialDegree = sc.nextDouble();
				if(initialDegree < 0 || initialDegree > 90) {
					repeatChk = true;
					System.out.println("error : 방향각 범위가 맞지 않습니다. 다시 입력해주십시오");
					System.out.println();
				}else {
					vehicle.setInitialDegree(initialDegree);
				}
			}while(repeatChk);
			
			do {// 이동체 최초 이동속도 입력
				System.out.print(i+"번 이동체의 최초 이동속도를 입력하십시오(1노트 ~ 50노트) : ");
				repeatChk = false;
				double initialNote = sc.nextDouble();
				if(initialNote < 1 || initialNote > 50) {
					repeatChk = true;
					System.out.println("error : 이동속도 범위가 맞지 않습니다. 다시 입력해주십시오");
					System.out.println();
				}else {
					vehicle.setInitialNote(initialNote);
				}
			}while(repeatChk);
			
			do {// 이동체 임무가로영역 입력
				System.out.print(i+"번 이동체의 임무영역(가로)을 입력하십시오(0m ~ 1000000m) : ");
				repeatChk = false;
				double width = sc.nextDouble();
				if(width < 0 || width > 1000000) {
					repeatChk = true;
					System.out.println("error : 임무영역(가로) 범위가 맞지 않습니다. 다시 입력해주십시오");
					System.out.println();
				}else {
					vehicle.setWidth(width);
				}
			}while(repeatChk);
			
			do {// 이동체 임무세로영역 입력
				System.out.print(i+"번 이동체의 임무영역(세로)을 입력하십시오(0m ~ 1000000m) : ");
				repeatChk = false;
				double height = sc.nextDouble();
				if(height < 0 || height > 1000000) {
					repeatChk = true;
					System.out.println("error : 임무영역(세로) 범위가 맞지 않습니다. 다시 입력해주십시오");
					System.out.println();
				}else {
					vehicle.setHeight(height);
				}
			}while(repeatChk);
			
			do {// 이동체 각도 변화량 입력
				System.out.print(i+"번 이동체의 방향각 증감량을 입력하십시오(0도 ~ 90도) : ");
				repeatChk = false;
				double changeDegree = sc.nextDouble();
				if(changeDegree < 0 || changeDegree > 90) {
					repeatChk = true;
					System.out.println("error : 방향각 증감량의 범위가 맞지 않습니다. 다시 입력해주십시오");
					System.out.println();
				}else {
					vehicle.setChangeDegree(changeDegree);
				}
			}while(repeatChk);
			
			do {// 이동체 이동속도 변화량 입력
				System.out.print(i+"번 이동체의 이동속도의 증감량을 입력하십시오(1노트 ~ 50노트) : ");
				repeatChk = false;
				double changeNote = sc.nextDouble();
				if(changeNote < 1 || changeNote > 50) {
					repeatChk = true;
					System.out.println("error : 이동속도 범위가 맞지 않습니다. 다시 입력해주십시오");
					System.out.println();
				}else {
					vehicle.setChangeNote(changeNote);
				}
			}while(repeatChk);
			
			do {// 이동체 시작 가로축 좌표 입력
				System.out.print(i+"번 이동체의 시작좌표(가로축)를 입력하십시오(0m ~ "+vehicle.getWidth()+"m : ");
				repeatChk = false;
				double startX = sc.nextDouble();
				if(startX < 0 || startX > vehicle.getWidth()) {
					repeatChk = true;
					System.out.println("error : 가로축의 시작좌표 범위가 맞지 않습니다. 다시 입력해주십시오");
					System.out.println();
				}else {
					vehicle.setStartX(startX);
				}
			}while(repeatChk);
			
			do {// 이동체 시작 세로축 좌표 입력
				System.out.print(i+"번 이동체의 시작좌표(세로축)를 입력하십시오(0m ~ "+vehicle.getHeight()+"m : ");
				repeatChk = false;
				double startY = sc.nextDouble();
				if(startY < 0 || startY > vehicle.getHeight()) {
					repeatChk = true;
					System.out.println("error : 세로축의 시작좌표 범위가 맞지 않습니다. 다시 입력해주십시오");
					System.out.println();
				}else {
					vehicle.setStartY(startY);
				}
			}while(repeatChk);
			
			do {// 이동체 임무수행 시간 입력
				System.out.print(i+"번 이동체의 시작좌표(세로축)를 입력하십시오(0m ~ "+vehicle.getHeight()+"m : ");
				repeatChk = false;
				double startY = sc.nextDouble();
				if(startY < 0 || startY > vehicle.getHeight()) {
					repeatChk = true;
					System.out.println("error : 세로축의 시작좌표 범위가 맞지 않습니다. 다시 입력해주십시오");
					System.out.println();
				}else {
					vehicle.setStartY(startY);
				}
			}while(repeatChk);
			
			
			vehicleList.add(vehicle);
		}
		

//			long simulationTime = 0L;
//			do {
//				System.out.print("시뮬레이션 할 시간(초)을 입력하세요 : ");
//				simulationTime = sc.nextLong();
//				if(simulationTime <= 0) {
//					System.out.println("Error : 시간(초)은 0보다 커야 합니다.");
//					System.out.println();
//				} 
//			}while(simulationTime <= 0);
			
		
		
//		int direction = 0;
//		do {
//			System.out.println();
//			System.out.println("0  : X축 방향으로 이동");
//			System.out.println("45 : 대각선 방향으로 이동");
//			System.out.println("90 : Y축 방향으로 이동");
//			System.out.print("이동체의 각도를 입력하세요 : ");
//			direction = sc.nextInt();
//			if(!(direction == 0 || direction == 45 ||direction == 90)) {
//				System.out.println("Error : 방향각도는 0, 45, 90만 입력 할 수 있습니다.");
//			}
//		}while(!(direction == 0 || direction == 45 ||direction == 90));
//
//		double row = 0.0;
//		double column = 0.0;
//		do {
//			System.out.println();
//			System.out.print("영역의 가로 크기를 입력해주세요 : ");
//			column = sc.nextDouble();
//			System.out.print("영역의 세로 크기를 입력해주세요 : ");
//			row = sc.nextDouble();
//			if(row <= 0 || column <= 0) {
//				System.out.println("영역의 각 길이는 0보다 커야합니다");
//			}
//		}while(row <= 0 || column <= 0);
//		
//		double rowSpeed = 0.0;
//		double columnSpeed = 0.0;
//		do {
//			System.out.println();
//			System.out.println("1노트 당 0.1칸씩 이동속도가 증가합니다");
//			System.out.print("X축의 간격의 속도를 입력하세요 : ");
//			columnSpeed = sc.nextDouble();
//			System.out.print("Y축의 간격의 속도를 입력하세요 : ");
//			rowSpeed = sc.nextDouble();
//			if(rowSpeed <= 0 || columnSpeed <= 0) {
//				System.out.println("속도의 값은 0보다 커야합니다");
//			}
//		}while(rowSpeed <= 0 || columnSpeed <= 0);
//
//		String id = "empty";
//		String name = "empty";
//		System.out.println(); 
//		System.out.print("ID를 입력해주세요 : ");
//		id = sc.next();
//		System.out.print("이름를 입력해주세요 : ");
//		name = sc.next();
//		System.out.println();
//
//		
	}
	
	

}
