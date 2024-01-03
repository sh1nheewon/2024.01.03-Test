package com.callor.iolist.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.callor.iolist.models.ListDto;
import com.callor.iolist.utils.Line;

public class ListService {

	private List<ListDto> ioList = null;

	public ListService() {
		ioList = new ArrayList<ListDto>();
	}

	public void loadList() {
		String dataFile = "src/com/callor/iolist/models/매입매출데이터.txt";
		InputStream is = null;
		Scanner scan = null;

		try {
			is = new FileInputStream(dataFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		scan = new Scanner(is);

		while (scan.hasNext()) {
			String line = scan.nextLine();
			String[] list = line.split(",");
			ListDto dto = new ListDto();
			dto.date = list[0];
			dto.time = list[1];
			dto.name = list[2];
			dto.customer = list[3];
			dto.CEO = list[4];
			dto.seperate = list[5];
			dto.num = list[6];
			dto.buy = list[7];
			dto.cell = list[8];

			dto.intSeperate = (int) Integer.parseInt(dto.seperate);
			dto.intNum = (int) Integer.parseInt(dto.num);
			dto.intBuy = (int) Integer.parseInt(dto.buy);
			dto.intCell = (int) Integer.parseInt(dto.cell);

			if (dto.intSeperate == 1) {
				dto.totalBuy = dto.intBuy * dto.intNum;
				dto.totalCell = 0;
			}
			if (dto.intSeperate == 2) {
				dto.totalCell = dto.intCell * dto.intNum;
				dto.totalBuy = 0;
			}
			ioList.add(dto);
		}
		scan.close();
	}

	public void printList() {
		for (ListDto dto : ioList) {
			Line.dLine(100);
			System.out.println("거래일자\t거래처	\t		상품이름		매입금액\t판매금액");
			Line.sLine(100);
			System.out.printf("%s\t", dto.date);
			System.out.printf("%s\t", dto.customer);
			System.out.printf("%s	\t", dto.name);
			System.out.printf("%s	\t", dto.buy);
			System.out.printf("%s\n", dto.cell);
			Line.sLine(100);
			System.out.printf("거래건수\t%d건\t						%d	   %d\n",
					dto.intSeperate, dto.totalCell,dto.totalBuy);
			Line.dLine(100);
		}

	}
}
