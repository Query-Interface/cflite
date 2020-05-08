package com.queryinterface.cflite.cloudcontroller.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.List;

public class PaginatedResult<T> {
    private Pagination pagination;
    private final Collection<T> resources;
    @JsonIgnore
    private final String resourceName;

    public PaginatedResult(String resourceName, Collection<T> results) {
        this.resourceName = resourceName;
        this.resources = results;
        this.pagination = new Pagination();
    }

    public Pagination getPagination() {
        return pagination;
    }

    public Collection<T> getResources() {
        return resources;
    }

    private class Pagination {
        private int total_pages = 1;
        private String next = null;
        private String previous = null;

        public int getTotal_pages() {
            return total_pages;
        }

        public String getNext() {
            return next;
        }

        public String getPrevious() {
            return previous;
        }

        @JsonProperty("total_results")
        public int getTotal_results() {
            return resources.size();
        }

        @JsonProperty("first")
        public InfoLink getFirst() {
            return new InfoLink("http://localhost:8080/api/v3/"+resourceName+"?page=1&per_page=50");
        }

        @JsonProperty("last")
        public InfoLink getLast() {
            return new InfoLink("http://localhost:8080/api/v3/"+resourceName+"?page=1&per_page=50");
        }
    }
}
