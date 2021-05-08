package org.vehicleSimulation.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class VehicleSimulator {

	// 이동체정보 저장 할 리스트
	static List<Vehicle> list = new ArrayList<Vehicle>();  
	
	public static void main(String[] args) {
		System.out.println("###");
		
		// 이동체 데이터 받기
		inputData();
		
	}

	
	private static void inputData() {
		
		Scanner sc = new Scanner(System.in);
		boolean repeatChk = false;
		
		int vCount = 0;
		do {
			repeatChk = false;
			System.out.print("이동체의 수량을 입력해주십시오 : ");
			 vCount = sc.nextInt();
			if(vCount <= 0 || vCount > 4) {
				System.out.println("이동체의 수량은 1대 이상, 4대 이하만 가능합니다.");
				System.out.println();
				repeatChk = true;
			} 
		}while(repeatChk);
		
		for(int i=1; i<=vCount; i++) {
			System.out.println();
			System.out.println(i+"번째 이동체 정보입력을 시작하겠습니다...");
			Vehicle vehicle = new Vehicle();
			
			String id = "";
			System.out.print(i+"번째 이동체의 이름을 입력하시오 : ");
			id = sc.next();
			System.out.println(id);
		

//			long simulationTime = 0L;
//			do {
//				System.out.print("시뮬레이션 할 시간(초)을 입력하세요 : ");
//				simulationTime = sc.nextLong();
//				if(simulationTime <= 0) {
//					System.out.println("Error : 시간(초)은 0보다 커야 합니다.");
//					System.out.println();
//				} 
//			}while(simulationTime <= 0);
			
		}
		
		
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
