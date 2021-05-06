package org.vehicleSimulation.demo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Scanner;

public class DemoSimulator {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		long simulationTime = 0L;
		do {
			System.out.print("시뮬레이션 할 시간(초)을 입력하세요 : ");
			simulationTime = sc.nextLong();
			if(simulationTime <= 0) {
				System.out.println("Error : 시간(초)은 0보다 커야 합니다.");
				System.out.println();
			} 
		}while(simulationTime <= 0);

		int direction = 0;
		do {
			System.out.println();
			System.out.println("0  : X축 방향으로 이동");
			System.out.println("45 : 대각선 방향으로 이동");
			System.out.println("90 : Y축 방향으로 이동");
			System.out.print("이동체의 각도를 입력하세요 : ");
			direction = sc.nextInt();
			if(!(direction == 0 || direction == 45 ||direction == 90)) {
				System.out.println("Error : 방향각도는 0, 45, 90만 입력 할 수 있습니다.");
			}
		}while(!(direction == 0 || direction == 45 ||direction == 90));

		double row = 0.0;
		double column = 0.0;
		do {
			System.out.println();
			System.out.print("영역의 가로 크기를 입력해주세요 : ");
			column = sc.nextDouble();
			System.out.print("영역의 세로 크기를 입력해주세요 : ");
			row = sc.nextDouble();
			if(row <= 0 || column <= 0) {
				System.out.println("영역의 각 길이는 0보다 커야합니다");
			}
		}while(row <= 0 || column <= 0);
		
		double rowSpeed = 0.0;
		double columnSpeed = 0.0;
		do {
			System.out.println();
			System.out.println("1노트 당 0.1칸씩 이동속도가 증가합니다");
			System.out.print("X축의 간격의 속도를 입력하세요 : ");
			columnSpeed = sc.nextDouble();
			System.out.print("Y축의 간격의 속도를 입력하세요 : ");
			rowSpeed = sc.nextDouble();
			if(rowSpeed <= 0 || columnSpeed <= 0) {
				System.out.println("속도의 값은 0보다 커야합니다");
			}
		}while(rowSpeed <= 0 || columnSpeed <= 0);

		String id = "empty";
		String name = "empty";
		System.out.println(); 
		System.out.print("ID를 입력해주세요 : ");
		id = sc.next();
		System.out.print("이름를 입력해주세요 : ");
		name = sc.next();
		System.out.println();

		StringBuilder sb = new StringBuilder();

		sb.append("입력받은 값은 다음과 같습니다.");
		sb.append("\n시뮬레이션 진행 시간(초) : " + simulationTime);
		sb.append("\n방향각도 : " + direction);
		sb.append("\n영역크기 : " + Math.round(column*10) * Math.round(row*10));
		sb.append("\nX축 속도(노트) : " + Math.round(columnSpeed) + ", Y축 속도(노트) : " + Math.round(rowSpeed));
		sb.append("\nID : " + id);
		sb.append("\n이름 : " + name);
		sb.append("\n\n시뮬레이션 초단위 결과 출력");

		
		long currecntTime = 0L;
		double currecntRow = 0.0;
		double currecntColumn = 0.0;
		LocalDateTime lt = LocalDateTime.now();
		
		File file = new File("C:\\ide\\log\\"+lt.toString().replaceAll(":", ".")+".txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));

		while(simulationTime != currecntTime) {
			currecntTime++;
			if(direction == 0) {
				double cTemp = Math.round((currecntColumn + (columnSpeed / 10.0)) * 10) / 10.0;
				if(cTemp >= column) {
					currecntColumn = column;
				}else {
					currecntColumn = cTemp;
				}
				sb.append("\n");
				sb.append("현재시간:"+lt.plusSeconds(currecntTime).toString().replaceAll("T", " ").substring(0, lt.toString().lastIndexOf("."))+"초"
						+ ", 이름:"+name+", ID:"+id+", 방향각:"+direction+", X축 속도:"+Math.round(columnSpeed)+"노트" +", Y축 속도:"+Math.round(rowSpeed)+"노트"
						+ ", 좌표:("+currecntColumn+","+currecntRow+")"
						+ ", 이동면적:" + (Math.round(currecntColumn * 10) + Math.round(currecntRow * 10))
						+ ", 전체면적:" + (Math.round(column*10) * Math.round(row*10))
						+ ", 이동면적/전체면적 = MPI:" + ((Math.round(currecntColumn * 10) + Math.round(currecntRow * 10))*1.0) / ((Math.round(column*10) * Math.round(row*10))*1.0)) ;	
			}else if(direction == 45) {
				double cTemp = Math.round((currecntColumn + (columnSpeed / 10.0)) * 10) / 10.0;
				double rTemp = Math.round((currecntRow + 	(rowSpeed	 / 10.0)) * 10) / 10.0;
				if(cTemp >= column) {
					currecntColumn = column;
				}else {
					currecntColumn = cTemp;
				}
				
				if(rTemp >= row) {
					currecntRow = row;
				}else {
					currecntRow = rTemp;
				}
				sb.append("\n");
				sb.append("현재시간:"+lt.plusSeconds(currecntTime).toString().replaceAll("T", " ").substring(0, lt.toString().lastIndexOf("."))+"초"
						+ ", 이름:"+name+", ID:"+id+", 방향각:"+direction+", X축 속도:"+Math.round(columnSpeed)+"노트" +", Y축 속도:"+Math.round(rowSpeed)+"노트"
						+ ", 좌표:("+currecntColumn+","+currecntRow+")"
						+ ", 이동면적:" + (Math.round(currecntColumn * 10) + Math.round(currecntRow * 10))
						+ ", 전체면적:" + (Math.round(column*10) * Math.round(row*10))
						+ ", 이동면적/전체면적 = MPI:" + ((Math.round(currecntColumn * 10) + Math.round(currecntRow * 10))*1.0) / ((Math.round(column*10) * Math.round(row*10))*1.0)) ;
			}else {
				double rTemp = Math.round((currecntRow + 	(rowSpeed	 / 10.0)) * 10) / 10.0;
				if(rTemp >= row) {
					currecntRow = row;
				}else {
					currecntRow = rTemp;
				}
				sb.append("\n");
				sb.append("현재시간:"+lt.plusSeconds(currecntTime).toString().replaceAll("T", " ").substring(0, lt.toString().lastIndexOf("."))+"초"
						+ ", 이름:"+name+", ID:"+id+", 방향각:"+direction+", X축 속도:"+Math.round(columnSpeed)+"노트" +", Y축 속도:"+Math.round(rowSpeed)+"노트"
						+ ", 좌표:("+currecntColumn+","+currecntRow+")"
						+ ", 이동면적:" + (Math.round(currecntColumn * 10) + Math.round(currecntRow * 10))
						+ ", 전체면적:" + (Math.round(column*10) * Math.round(row*10))
						+ ", 이동면적/전체면적 = MPI:" + ((Math.round(currecntColumn * 10) + Math.round(currecntRow * 10))*1.0) / ((Math.round(column*10) * Math.round(row*10))*1.0)) ;
			}
		}

		System.out.println(sb.toString());
		bw.write(sb.toString());
		bw.flush();
		bw.close();

	}

}
