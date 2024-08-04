package com.example.rayan_task.service;

import org.springframework.data.domain.PageRequest;

/**
 * Author: ASOU SAFARI
 * Date:6/11/24
 * Time:3:30 PM
 */
public class BuildPageRequest {

    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 25;

    public static PageRequest build(Integer pageNumber, Integer pageSize){
        int queryPageNumber;
        int queryPageSize;
        if (pageNumber != null && pageNumber > 0){
            queryPageNumber = pageNumber - 1 ;
        }else {
            queryPageNumber = DEFAULT_PAGE_NUMBER;
        }
        if (pageSize == null){
            queryPageSize = DEFAULT_PAGE_SIZE;
        }else {
            if (pageSize > 1000){
                queryPageSize = 1000;
            }else {
                queryPageSize = pageSize;
            }
        }

        return PageRequest.of(queryPageNumber,queryPageSize);
    }
}
