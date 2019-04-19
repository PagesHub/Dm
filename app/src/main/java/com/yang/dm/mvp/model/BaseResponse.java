package com.yang.dm.mvp.model;

import java.util.List;

/**
 * Describe:
 * Created by Yang on 2019/1/22.
 */
public class BaseResponse<T> {

    private int count;
    private boolean error;
    private List<T> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
