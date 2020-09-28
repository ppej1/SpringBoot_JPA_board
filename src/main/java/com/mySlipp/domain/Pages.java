package com.mySlipp.domain;

import java.util.ArrayList;
import java.util.List;

public class Pages {
    private int firstPage;
    private int lastPage;
    private int currentPage;
    private int listOfOnePage;
    public Pages(int firstPage, int AllList, int listOfOnePage, int currentPage) {
        this.firstPage = firstPage;
        this.listOfOnePage = listOfOnePage;
        this.lastPage = AllList/listOfOnePage;
		if(AllList%listOfOnePage>0){
			this.lastPage += 1;
		}
        this.currentPage = currentPage;
    }
    
    public int getCurrentPage() {
        return currentPage;
    }
    
    public List<Integer> getPagesList() {
        List<Integer> pages = new ArrayList<>();
        for (int i = firstPage; i <= lastPage; i++) {
            pages.add(i);
        }
        if (pages.size()==0) {
			pages.add(1);
		}
        return pages;
    }
    
    public List<Question> getOnePageList(List<Question> questionAllList){
		List<Question> questionList =  new ArrayList<>();
		for (int i = listOfOnePage*(currentPage-1) ; i <= (listOfOnePage*currentPage)-1; i++) {
	    	if(i<questionAllList.size()){
	    	    questionList.add(questionAllList.get(i));
	    	}
	    }
		return questionList;	
    }
}