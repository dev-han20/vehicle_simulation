package org.vehicleSimulation.app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VehicleSimulator {

	// 이동체정보 저장 할 리스트
	static List<Vehicle> vehicleList = new ArrayList<Vehicle>();  
	
	public static void main(String[] args) throws IOException {
		
		// 이동체 데이터 받기
		inputData();
		
		// 시뮬레이션 시작 시간
		LocalDateTime lt = LocalDateTime.now();
		
		for (Vehicle v : vehicleList) {
			// 시뮬레이션 실행
			simulation(v, lt);
		}
		
		
	}

	private static void simulation(Vehicle vehicle, LocalDateTime lt) throws IOException {
		long currentTime = 0L;
		double currentX = 0;
		double currentY = 0;
		double currentDegree = 0;
		double currentNote = 0;
		double nextX = 0;
		double nextY = 0;
		double nextDegree = 0;
		double nextNote = 0;
		double distance = 0;
		double currentMovingArea = 0;
		
		StringBuilder sb = new StringBuilder();
//		File file = new File("C:\\ide\\log\\"+lt.toString().replaceAll(":", ".")+".txt");
		File file = new File("D:\\IDE\\_STS_starAD\\workspace\\vehicleSimulation\\log\\"
					+lt.toString().replaceAll("T", "_").replaceAll(":", ".").substring(0, lt.toString().lastIndexOf("."))
					+"_"+vehicle.getName()+".txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		
		if(currentTime == 0) {
			sb.append("시간|이름|ID|타입|이동체크기(m)|x좌표|y좌표|이동속도(knot)|방향각|이동면적(m2)|전체면적(m2)|MPI");
			sb.append("\n");
			sb.append(lt.plusSeconds(currentTime).toString().replaceAll("T", " ").substring(0, lt.toString().lastIndexOf("."))
					+ "|"+vehicle.getName()+"|"+vehicle.getId()+"|"+vehicle.getShape()+"|"+vehicle.getvLength()
					+ "|"+vehicle.getStartX()+"|"+vehicle.getStartY()+"|"+vehicle.getInitialNote()+"|"+vehicle.getInitialDegree()
					+ "|0.000|"+new BigDecimal(vehicle.getWidth()*vehicle.getHeight()).toPlainString()+"|0.000") ;
			
			// 현재 값 초기화
			currentX = vehicle.getStartX();
			currentY = vehicle.getStartY();
			currentDegree = vehicle.getInitialDegree();
			currentNote = vehicle.getInitialNote();
		}
		
		while(vehicle.getSimulationTime() != currentTime) {
			// 1초 증가
			currentTime++;
			
			// 1초뒤 노트 = 현재노트 + 노트증감량
			nextNote = (currentNote + vehicle.getChangeNote() > 50 ? 50 : currentNote + vehicle.getChangeNote());
			// 1초뒤 각도 = 현재각도 + 각도증감량
			nextDegree = currentDegree + vehicle.getChangeDegree();
			if(nextDegree < 0) {
				nextDegree = 0;
			}else if(nextDegree > 90) {
				nextDegree = 90;
			}
			// 다음 X축 좌표 = 현재X축 좌표 + (1초뒤 노트 x 0.5144 x cos(1초뒤 각도의 라디안 값)) 
			nextX = currentX + (nextNote * 0.5144 * Math.cos(Math.toRadians(nextDegree)));
			// 다음 Y축 좌표 = 현재Y축 좌표 + (1초뒤 노트 x 0.5144 x sin(1초뒤 각도의 라디안 값)) 
			nextY = currentY + (nextNote * 0.5144 * Math.sin(Math.toRadians(nextDegree)));
			// 두점 사이의 거리 피타고라스 정리 써서 구함
			distance = Math.sqrt(Math.pow(nextX-currentX, 2) + Math.pow(nextY-currentY, 2));
			// 현재까지 이동한 영역의 합
			currentMovingArea = currentMovingArea + (distance * vehicle.getvLength());
			
			currentX = nextX;
			currentY = nextY;
			currentNote = nextNote;
			currentDegree = nextDegree;
			
			sb.append("\n");
			sb.append(lt.plusSeconds(currentTime).toString().replaceAll("T", " ").substring(0, lt.toString().lastIndexOf("."))
					+ "|"+vehicle.getName()+"|"+vehicle.getId()+"|"+vehicle.getShape()+"|"+vehicle.getvLength()
					+ "|"+currentX+"|"+currentY+"|"+currentNote+"|"+currentDegree+"|"+currentMovingArea
					+ "|"+new BigDecimal(vehicle.getWidth()*vehicle.getHeight()).toPlainString()
					+ "|"+new BigDecimal(currentMovingArea / (vehicle.getWidth()*vehicle.getHeight())).toPlainString()) ;
			
		}
		
		System.out.println(sb.toString());
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private static void inputData() {
		
		Scanner sc = new Scanner(System.in);
		
		//입력 값 검증체크 변수
		boolean repeatChk = false;
		
		int vCount = 0;
		do {// 이동체 수량 받기
			repeatChk = false;
			System.out.print("이동체의 수량을 입력해주십시오(1대 ~ 4대) : ");
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
			
			do {// 이동체 크기 입력
				System.out.print(i+"번 이동체의 크기를 입력하십시오 : ");
				repeatChk = false;
				double vLength = sc.nextDouble();
				vehicle.setvLength(vLength);
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
				System.out.print(i+"번 이동체의 시작좌표(가로축)를 입력하십시오(0m ~ "+vehicle.getWidth()+"m) : ");
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
				System.out.print(i+"번 이동체의 시작좌표(세로축)를 입력하십시오(0m ~ "+vehicle.getHeight()+"m) : ");
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
				System.out.print(i+"번 이동체의 임무수행시간을 입력하십시오 : ");
				repeatChk = false;
				double simulationTime = sc.nextDouble();
				vehicle.setSimulationTime(simulationTime);
			}while(repeatChk);
			
			vehicleList.add(vehicle);
		}
		sc.close();
	}
	

}
