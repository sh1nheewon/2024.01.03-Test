package com.callor.iolist.exec;

import com.callor.iolist.service.ListService;

public class List {
	public static void main(String[] args) {
		ListService list = new ListService();
		list.loadList();
		list.printList();
	}
}
