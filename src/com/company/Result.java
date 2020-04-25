package com.company;

public class Result {
    private String hash;
    private String successfullThread;
    private boolean found;

    public Result() {
        hash = null;
        successfullThread = null;
        found = false;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getSuccessfullThread() {
        return successfullThread;
    }

    public void setSuccessfullThread(String successfullThread) {
        this.successfullThread = successfullThread;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }
}
