package com.biraj;

import java.util.Objects;

public class ApiResponse implements Comparable {

    // 5. Need to add attributes here.
    private String id;
    private String page;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiResponse that = (ApiResponse) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(page, that.page);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, page);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

   // @Override
    public int compare(Object o1, Object o2) {

        ApiResponse s1=(ApiResponse)o1;
        ApiResponse s2=(ApiResponse)o2;
        if((s1.getId()==s2.getId())&& s1.getPage() == s2.getPage())
            return 0;
        else
            return -1;
    }


    @Override
    public int compareTo(Object o) {
        ApiResponse s1=(ApiResponse)o;
        if((s1.getId()==this.getId())&& s1.getPage() == this.getPage())
            return 0;
        else
            return -1;
    }
}
