package com.yf.pet.entity.epo;


import com.yf.pet.entity.ResponseVO;
import com.yf.pet.entity.ReturnMessageEnum;

public class EpoResponse extends ResponseVO {

    private static final long serialVersionUID = -4362980248281050355L;

    private boolean needUpdate;
    private String[] epoFileUrls;
    private String[] epoFileMd5s;

    public EpoResponse(ReturnMessageEnum returnMessage) {
        super(returnMessage);
    }

    public boolean isNeedUpdate() {
        return needUpdate;
    }

    public void setNeedUpdate(boolean needUpdate) {
        this.needUpdate = needUpdate;
    }

    public String[] getEpoFileUrls() {
        return epoFileUrls;
    }

    public void setEpoFileUrls(String[] epoFileUrls) {
        this.epoFileUrls = epoFileUrls;
    }

    public String[] getEpoFileMd5s() {
        return epoFileMd5s;
    }

    public void setEpoFileMd5s(String[] epoFileMd5s) {
        this.epoFileMd5s = epoFileMd5s;
    }


}
