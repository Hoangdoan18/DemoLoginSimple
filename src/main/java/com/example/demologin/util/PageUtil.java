package com.example.demologin.util;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PageUtil {
    private int limit;

    private int page;

    //Set page = 1 if page < 1
    public PageUtil(int limit, int page){
        this.limit = limit;
        if (page < 1) {
            this.page = 1;
        } else {
            this.page = page;
        }
    }

    public int offset() {
        return (page - 1) * limit;
    }

    public int totalPage(int totalItems) {
        int totalPage;
        if (totalItems % limit == 0) {
            totalPage = totalItems / limit;
        } else {
            totalPage = totalItems / limit + 1;
        }
        return totalPage;
    }
}
